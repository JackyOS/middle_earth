package controller.users;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import services.UserService;

//por convención los serlet terminan en do y los jsp son .jsp si los accedes directo
//si veo un .admin, es un servlet, y si veo un .jsp es una vista a la cual accedo directamente
@WebServlet("/admin/users/edit.admin")
public class EditUserServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override //trae los datos
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		User tmp_user = userService.find(id);
		req.setAttribute("tmp_user", tmp_user);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/users/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override //envia las modificaciones
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Double coins = Double.parseDouble(req.getParameter("coins"));
		// Integer cost = req.getParameter("cost").trim() == "" ? null : Integer.parseInt(req.getParameter("cost"));
		Double duration = Double.parseDouble(req.getParameter("time"));

		User tmp_user = userService.update(id,password, username, coins, duration);

		if (tmp_user.isValid()) {
			
			if(tmp_user.getId() == ( (User) req.getSession().getAttribute("user")).getId()) {
				req.getSession().setAttribute("user", tmp_user);
			}
			resp.sendRedirect("/turismo/admin/users/index.admin");
		} else { //si pusimos algun valor no valido en el edit
			req.setAttribute("tmp_user", tmp_user); //pone la atraccion en request y me manda al edit

		
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/users/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
