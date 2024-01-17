package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import models.ArticuloModel;
import models.UserModel;
import models.UserSessionModel;
import services.RegisterService;

/**
 * Servlet implementation class MisDatos
 */
@WebServlet("/MisDatosEmailPassword")
public class MisDatosEmailPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MisDatosEmailPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextJSP = "/WEB-INF/MisDatosEmailPassword.jsp";
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
			UserModel userModelSes = userSessionModel.getUsuario();

			String email = request.getParameter("email");
			String password = request.getParameter("passwordActual");
			String passwordNueva = request.getParameter("passwordNueva");
			String repetirPasswordNueva = request.getParameter("repetirPasswordNueva");
			String emailActual = userModelSes.getEmail();

			
			boolean error = false;

			// Validar si la contraseña actual coincide con el email actual
			if (!registerService.validarEmailPasswordViejos(emailActual, password)) {
				request.setAttribute("errPasswordActual", "La contraseña actual es incorrecta.");
				error = true;
			}

			// Comprobar email y password vacios
			if (registerService.emailPasswordCamposVacios(email, password)) {
				request.setAttribute("errRegistVacio", "Email y contraseña vacios.");
				error = true;
			}
			// Comprobar si las contraseñas nuevas estan vacias
			if (registerService.validarPasswordNuevasSiVacias(passwordNueva, repetirPasswordNueva)) {

				// Comprobar si las password nuevas coinciden
			} else if (passwordNueva.equals(repetirPasswordNueva)) {
				// Si coinciden comparar si son iguales que la contraseña anterior
				if (passwordNueva.equals(userModelSes.getPassword())) {
					request.setAttribute("errPasswordRepetida", "La contraseña no puede ser igual a la anterior.");
					error = true;
				}
			} else {
				request.setAttribute("errPasswordNueva", "Las nuevas contraseñas no coinciden.");
				error = true;
			}

			// Si el correo nuevo ya se encuentra en uso
			if (!error) {
				if (!emailActual.equals(email)) {
					if (UserDAO.getAllUserData(email) != null) {
						request.setAttribute("errEmailExistente", "Ese email ya se encuentra en uso.");
						error = true;
					}
				}
			}

			if (error) {
				// request.setAttribute("datosCambiados", userModel);

			} else {
				if (email.equals(emailActual) && passwordNueva == "") {
				} else {
					UserModel userModel = UserDAO.getAllUserData(userModelSes.getEmail());

					if (registerService.actualizarEmailPassword(userModel, email, passwordNueva)) {
						System.out.println("Email o password modificada con éxito.");

						userModel = UserDAO.getAllUserData(email);

						userSessionModel.setUsuario(userModel);
						request.getSession().setAttribute("sesionUsuario", userSessionModel);

					}
				}
			}
		}

		doGet(request, response);
	}

}
