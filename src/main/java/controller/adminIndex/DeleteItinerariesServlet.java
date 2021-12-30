/*objetivo a lograr: eliminar itinerario desde el admin y sumarle la plata y el tiempo al usuario por esa oferta que no va a realizar
package controller.adminIndex;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AttractionService;
import services.ItineraryService;

@WebServlet("/admin/itineraries/delete.do")
public class DeleteItinerariesServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private ItineraryService itineraryService;


	@Override
	public void init() throws ServletException {
		super.init();
		this.itineraryService = new ItineraryService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		//se puede hacer validaciones para que, por ejemplo, 
		//no se pueda borrar una atraccion que esta en una promo
		
		itineraryService.delete(id);

		resp.sendRedirect("/turismo/admin/itineraries/index.do");
	}


}
*/