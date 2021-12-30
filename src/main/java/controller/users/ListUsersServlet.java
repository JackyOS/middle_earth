package controller.users;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import services.UserService;

@WebServlet("/admin/users/index.admin")
public class ListUsersServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		List<User> users = userService.list();

		//serive son los intermediarios => con el dao
		//cada uno tiene su responsabilidad
		//los controllers/serlet no tienen logica
		req.setAttribute("users", users);
	

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/users/index.jsp");
		dispatcher.forward(req, resp);

	}

}
