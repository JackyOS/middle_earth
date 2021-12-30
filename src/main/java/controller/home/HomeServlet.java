package controller.home;

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
import model.Promotion;
import services.AttractionService;
import services.PromotionService;


@WebServlet("/home")
public class HomeServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private AttractionService attractionService;
	private PromotionService promotionService;


	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.promotionService = new PromotionService();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Attraction> attractions = attractionService.list();
		req.setAttribute("attractions", attractions);
		
		List<Promotion> promotions = promotionService.list();
		 req.setAttribute("promotions", promotions);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		dispatcher.forward(req, resp);
	}

}