package controller.ofertables;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import persistence.commons.DAOFactory;
import services.BuyOfertableService;


@WebServlet("/ofertables/buy.do")
public class BuyOfertableServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private BuyOfertableService buyOfertableService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.buyOfertableService = new BuyOfertableService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Boolean soyPromo = Boolean.parseBoolean(req.getParameter("soyPromo"));
		Integer ofertableId =  Integer.parseInt(req.getParameter("id"));
		
		User user = (User) req.getSession().getAttribute("user");
		
		Map<String, String> errors = buyOfertableService.buy(user.getId(), ofertableId, soyPromo);
		
		User user2 = DAOFactory.getUserDAO().find(user.getId());
		req.getSession().setAttribute("user", user2);
		
		if (errors.isEmpty()) {
			req.setAttribute("success", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/ofertables/index.do");
		dispatcher.forward(req, resp);
	}
}
