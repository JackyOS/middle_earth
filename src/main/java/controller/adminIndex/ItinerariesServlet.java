package controller.adminIndex;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ofertable;
import model.User;
import services.ItineraryService;
import services.UserService;


@WebServlet("/admin/itineraries/index.admin")
public class ItinerariesServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private UserService userService;
	private ItineraryService itineraryService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
		this.itineraryService = new ItineraryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		List<User> users = userService.list();
		req.setAttribute("users", users);
		
		String usuarioName = req.getParameter("usuario");
		
		User userItinerary = userService.findByName(usuarioName);
		req.setAttribute("userItinerary", userItinerary);
		
		List<Ofertable> itineraries = itineraryService.list(userItinerary);
		req.setAttribute("itineraries", itineraries);
				
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/itineraries/index.jsp");
		dispatcher.forward(req, resp);

	}

}