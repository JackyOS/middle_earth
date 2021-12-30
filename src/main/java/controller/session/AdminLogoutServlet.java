package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/logout")
public class AdminLogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 7030858525525416320L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getSession().removeAttribute("user");
		req.setAttribute("success", "¡Hasta pronto!"); 
		//success es un nombre que elegimos nosotros
		//y se suele usar flash para los errores, porque es un mensaje rapido de dice asi
		
		RequestDispatcher dispatcher = getServletContext()
  		      .getRequestDispatcher("/home");
  		    dispatcher.forward(req, resp); 	
    }
}
