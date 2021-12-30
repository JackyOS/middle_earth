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

@WebServlet("/admin/attractions/create.admin")
public class CreateAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/admin/attractions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		Double cost = Double.parseDouble(req.getParameter("cost"));
		Double duration = Double.parseDouble(req.getParameter("duration"));
		Integer capacity = Integer.parseInt(req.getParameter("capacity"));
		String description = req.getParameter("description");
		String image = req.getParameter("image");


		
		//crea una atraccion me la guarda en el dao
		Attraction attraction = attractionService.create(name, cost, duration, capacity, description,image);
		if (attraction.isValid()) { //pregunta si es valida
			resp.sendRedirect("/turismo/admin/attractions/index.admin");
		} else {
			req.setAttribute("attraction", attraction);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/admin/attractions/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
