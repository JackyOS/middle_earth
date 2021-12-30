package controller.attractions;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attraction;
import services.AttractionService;

//por convención los serlet terminan en do y los jsp son .jsp si los accedes directo
//si veo un .do, es un servlet, y si veo un .jsp es una vista a la cual accedo directamente
@WebServlet("/admin/attractions/edit.admin")
public class EditAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override //trae los datos
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Attraction attraction = attractionService.find(id);
		req.setAttribute("attraction", attraction);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/attractions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override //envia las modificaciones
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		Double cost = Double.parseDouble(req.getParameter("cost"));
		// Integer cost = req.getParameter("cost").trim() == "" ? null : Integer.parseInt(req.getParameter("cost"));
		Double duration = Double.parseDouble(req.getParameter("duration"));
		Integer capacity = Integer.parseInt(req.getParameter("capacity"));
		String description = req.getParameter("description");
		String image = req.getParameter("image");

		Attraction attraction = attractionService.update(id, name, cost, duration, capacity,description,image);

		if (attraction.isValid()) {
			resp.sendRedirect("/turismo/admin/attractions/index.admin");
		} else { //si pusimos algun valor no valido en el edit
			req.setAttribute("attraction", attraction); //pone la atraccion en request y me manda al edit

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/attractions/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
