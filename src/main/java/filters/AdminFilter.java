package filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import model.User;

@WebFilter(urlPatterns = "*.admin")  //cambiar punto admin para todas los view admiiiin
public class AdminFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		User user = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
		if (user != null && user.isAdmin()) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("flash", "Por favor, ingrese al sistema.");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/admin/login");
			dispatcher.forward(request, response);
		}

	}

}

/*

hacer un filtro para que cualquier usuario no pueda entrar al create.do 
o hacer un filtro admin para que solo el admin entre a tales vistas
*/