package controller.ofertables;

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
import services.OfertableService;


@WebServlet("/ofertables/index.do")
public class ListOfertableServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private OfertableService ofertableService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.ofertableService = new OfertableService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute("user");
		
		
		List<Ofertable> ofertables = ofertableService.list(user);
		req.setAttribute("ofertables", ofertables);		
			
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/ofertables/index.jsp");
		dispatcher.forward(req, resp);

	}

}
