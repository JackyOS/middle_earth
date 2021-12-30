package controller.promotions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attraction;
import model.PromocionPorcentual;
import model.Promotion;
import persistence.impl.AttractionDAOImpl;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/admin/promotions/createPorcentual.admin")
public class CreatePromotionPorcentageServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private PromotionService promotionService;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Promotion> promotions = promotionService.list();
		req.setAttribute("promotions", promotions);

		List<Attraction> attractions = attractionService.list();
		req.setAttribute("attractions", attractions);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/promotions/createPorcentual.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Attraction> attractions = attractionService.list();
		req.setAttribute("attractions", attractions);

		String name = req.getParameter("name");
		String type = req.getParameter("type");
		Double discount = Double.parseDouble(req.getParameter("discount"));
		String description = req.getParameter("description");
		String image = req.getParameter("image");
		String[] newAttractions = req.getParameterValues("attractions");

		List<Attraction> a = new ArrayList<Attraction>();
		for (String attraction : newAttractions) {
			int id = AttractionDAOImpl.findId(attraction);
			a.add(attractionService.find(id));
			}	
		
		// crea una promocion me la guarda en el dao
		PromocionPorcentual promotion = promotionService.createPromoPorcentual(name,type, discount, a, description, image);


	
		if (promotion.isValid()) { // pregunta si es valida
			resp.sendRedirect("/turismo/admin/promotions/index.admin");
		} else {
			req.setAttribute("promotion", promotion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/admin/promotions/createPorcentual.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
