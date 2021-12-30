package controller.users;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UserService;

@WebServlet("/admin/users/delete.admin")
public class DeleteUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		//se puede hacer validaciones para que, por ejemplo, que 
		//no se pueda borrar una atraccion que esta en una promo
		
		userService.delete(id);

		resp.sendRedirect("/turismo/admin/users/index.admin");
	}


}
