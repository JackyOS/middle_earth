package controller.itinerary;

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

@WebServlet("/itineraries/index.do")
public class ListItineraryServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private ItineraryService itineraryService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.itineraryService = new ItineraryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		User user = (User) req.getSession().getAttribute("user");
		
		List<Ofertable> itineraries = itineraryService.list(user);
			
		req.setAttribute("itineraries", itineraries);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/itineraries/index.jsp");
		dispatcher.forward(req, resp);

	}

}
