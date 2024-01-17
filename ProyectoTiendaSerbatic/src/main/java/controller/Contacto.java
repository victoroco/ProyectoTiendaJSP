package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Session;
import models.ArticuloModel;
import models.UserSessionModel;
import services.EnviarMailSerbatic;

/**
 * Servlet implementation class Contacto
 */
@WebServlet("/Contacto")
public class Contacto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Contacto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextJSP = "/WEB-INF/Contacto.jsp";

		// Crear la sesion
		if (request.getSession(false) == null) {
			request.getSession();
			System.out.println("Sesion creada.");
		}
		// Resetear carrito si la sesión ha caducado
		if (request.getSession().getAttribute("carrito") == null) {
			request.getSession().setAttribute("carrito", new HashMap<Integer, ArticuloModel>());
		}
		// Resetear cifra carrito si la sesión ha caducado
		if (request.getSession().getAttribute("cantidadTotalCarrito") == null) {
			request.getSession().setAttribute("cantidadTotalCarrito", 0);
		}
		if (request.getSession().getAttribute("sesionUsuario") != null) {
			UserSessionModel userSessionModel = (UserSessionModel) request.getSession().getAttribute("sesionUsuario");
			request.setAttribute("nombreUsuario", userSessionModel.getUsuario().getNombre());
		}

		// forward to JSP to show result
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextJSP = "/WEB-INF/Contacto.jsp";

		// Crear la sesion
		if (request.getSession(false) == null) {
			request.getSession();
			System.out.println("Sesion creada.");
		}
		// Resetear carrito si la sesión ha caducado
		if (request.getSession().getAttribute("carrito") == null) {
			request.getSession().setAttribute("carrito", new HashMap<Integer, ArticuloModel>());
		}
		// Resetear cifra carrito si la sesión ha caducado
		if (request.getSession().getAttribute("cantidadTotalCarrito") == null) {
			request.getSession().setAttribute("cantidadTotalCarrito", 0);
		}
		if (request.getSession().getAttribute("sesionUsuario") != null) {
			UserSessionModel userSessionModel = (UserSessionModel) request.getSession().getAttribute("sesionUsuario");
			request.setAttribute("nombreUsuario", userSessionModel.getUsuario().getNombre());
		}

		String asunto = request.getParameter("asunto");
		String cuerpoMensaje = request.getParameter("mensaje");
		String destinatario = "vvergara@serbatic.es";

		EnviarMailSerbatic.enviarEmail(asunto, cuerpoMensaje, destinatario);

		// forward to JSP to show result
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);

	}

}
