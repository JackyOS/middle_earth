package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import services.AdminLoginService;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;
	private AdminLoginService adminLoginService;

	@Override
	public void init() throws ServletException {
		super.init();
		adminLoginService = new AdminLoginService();
	}
	
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	
    	User user = adminLoginService.login(username, password);
    	
    	if (!user.isNull() && user.isAdmin()) {
    		req.getSession().setAttribute("user", user);
    		resp.sendRedirect("/turismo/admin/index.admin");    		
       	} else {
    		req.setAttribute("flash", "Nombre de usuario o contraseña incorrectos");
    		
    		RequestDispatcher dispatcher = getServletContext()
      		      .getRequestDispatcher("/views/admin/login.jsp");
      		    dispatcher.forward(req, resp);
    	}
    }
}
