package controller.promotions;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.PromotionService;

@WebServlet("/admin/promotions/delete.admin")
public class DeletePromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		//se puede hacer validaciones para que, por ejemplo, 
		//no se pueda borrar una atraccion que esta en una promo
		
		promotionService.delete(id);

		resp.sendRedirect("/turismo/admin/promotions/index.admin");
	}


}



