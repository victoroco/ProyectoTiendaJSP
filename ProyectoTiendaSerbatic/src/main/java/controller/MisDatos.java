package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ArticuloModel;
import models.UserModel;
import models.UserSessionModel;
import services.RegisterService;

/**
 * Servlet implementation class MisDatos
 */
@WebServlet("/MisDatos")
public class MisDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MisDatos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextJSP = "/WEB-INF/MisDatos.jsp";
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
		if (request.getSession(false).getAttribute("sesionUsuario") == null) {

			nextJSP = "/Login1";
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

		if (request.getSession(false).getAttribute("sesionUsuario") != null) {

			RegisterService registerService = new RegisterService();

			UserSessionModel userSessionModel = (UserSessionModel) request.getSession().getAttribute("sesionUsuario");
			UserModel userModelSesion = userSessionModel.getUsuario();

			UserModel userModel = new UserModel(request.getParameter("nombre"), request.getParameter("apellidos"),
					request.getParameter("direccion"), request.getParameter("provincia"),
					request.getParameter("ciudad"), request.getParameter("telefono"), request.getParameter("dni"));
			boolean error = false;

			if (registerService.actualizarDatos(userModel)) {
				request.setAttribute("errRegistVacio", "Los campos marcados con * son obligatorios.");
				error = true;
			}

			if (error) {
				request.setAttribute("datosCambiados", userModel);

			} else {

				userModelSesion = registerService.integrarUpdateDatosUserSesion(userModelSesion, userModel);

				if (registerService.actualizarDatosUsuario(userModelSesion)) {
					System.out.println("Usuario insertado con éxito.");
					userSessionModel.setUsuario(userModelSesion);
					request.getSession().setAttribute("sesionUsuario", userSessionModel);

				}
			}
		}

		doGet(request, response);
	}

}
