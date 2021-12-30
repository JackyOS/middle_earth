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
import model.Attraction;
import model.Ofertable;
import model.Promotion;
import model.User;
import services.AttractionService;
import services.ItineraryService;
import services.PromotionService;
import services.UserService;


@WebServlet("/admin/index.admin")
public class IndexServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private UserService userService;
	private AttractionService attractionService;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
		this.attractionService = new AttractionService();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute("user");
		
		List<User> users = userService.list();
		req.setAttribute("users", users);
		
		List<Attraction> attractions = attractionService.list();
		req.setAttribute("attractions", attractions);
		
		List<Promotion> promotions = promotionService.list();
		req.setAttribute("promotions", promotions);

				
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/index.jsp");
		dispatcher.forward(req, resp);

	}

}