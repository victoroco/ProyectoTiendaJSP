//package filters;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpFilter;
//
//import com.mysql.cj.Session;
//
///**
// * Servlet Filter implementation class FiltroLogin
// */
//@WebFilter("/Login")
//public class FiltroLogin extends HttpFilter implements Filter {
//
//	/**
//	 * @see HttpFilter#HttpFilter()
//	 */
//	public FiltroLogin() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		// place your code here
//
//		Map<String, String[]> lista = request.getParameterMap();
//		boolean validado = true;
//		String error = "mensajeError";
//		String error_1 = " esta vacio.";
//		for (Map.Entry<String, String[]> entry : lista.entrySet()) {
//			// System.out.println("clave=" + entry.getKey() + ", valor=" +
//			// entry.getValue());
//
//			String campo = entry.getKey();
//			String[] valor = entry.getValue();
//			// error = error.concat(campo.toString());
//			if (valor[0].equals("")) {
//				System.out.println("El Campo=" + new String(campo) + ", tiene un valor VACIO=" + new String(valor[0]));
//				System.out.println("Error: " + error.concat(entry.getKey()) + ". Mensaje de error: "
//						+ entry.getKey().concat(error_1));
//
//				request.setAttribute(error.concat(entry.getKey()), entry.getKey().concat(error_1));
//				System.out.println("asfas" + request.getAttribute("mensajeErroremail"));
//				// request.getRequestDispatcher("Login.jsp").forward(request, response);
//				// return;
//			} else {
//				System.out.println("El Campo=" + new String(campo) + ", tiene un valor=" + new String(valor[0]));
//			}
//
//		}
//
//		// pass the request along the filter chain
//		chain.doFilter(request, response);
//	}
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//}
