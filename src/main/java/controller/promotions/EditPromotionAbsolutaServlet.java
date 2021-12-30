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
import model.PromocionAbsoluta;
import persistence.impl.AttractionDAOImpl;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/admin/promotions/editAbsoluta.admin")
public class EditPromotionAbsolutaServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private PromotionService promotionService;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
		this.attractionService = new AttractionService();
	}

	@Override // trae los datos
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		List<Attraction> attractions = attractionService.list();
		req.setAttribute("attractions", attractions);

		PromocionAbsoluta promotion = (PromocionAbsoluta) promotionService.find(id);
		req.setAttribute("promotion", promotion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/promotions/editAbsoluta.jsp");
		dispatcher.forward(req, resp);
	}

	@Override // envia las modificaciones
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Attraction> attractions = attractionService.list();
		req.setAttribute("attractions", attractions);

		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		Double cost = Double.parseDouble(req.getParameter("cost"));
		String[] newAttractions = req.getParameterValues("attractions");
		String description = req.getParameter("description");
		String image = req.getParameter("image");

		List<Attraction> a = new ArrayList<Attraction>();
		for (String attraction : newAttractions) {
			int idAttraction = AttractionDAOImpl.findId(attraction);
			a.add(attractionService.find(idAttraction));
		}

		// crea una promocion me la guarda en el dao
		PromocionAbsoluta promotion = promotionService.updatePromoAbsoluta(id, name, cost, a, description,
				image);

		if (promotion.isValid()) {
			resp.sendRedirect("/turismo/admin/promotions/index.admin");
		} else { // si pusimos algun valor no valido en el edit
			req.setAttribute("promotion", promotion); // pone la atraccion en request y me manda al edit

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/admin/promotions/editAbsoluta.jsp");
			dispatcher.forward(req, resp);

		}
	}
}
