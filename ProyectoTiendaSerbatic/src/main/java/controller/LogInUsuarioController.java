package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import models.UserModel;
import models.UserSessionModel;
import services.PasswordUtilsService;
import services.UserLoginService;

/**
 * Servlet implementation class LogInController
 */
@WebServlet("/Login")
public class LogInUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private UserDAO userDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInUsuarioController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		// userDAO = new UserDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtenemos los valores de los campos
		String emailUser = request.getParameter("email");
		String password = request.getParameter("password");

		// SESION
		HttpSession sesion = request.getSession();
		UserLoginService userLoginService = new UserLoginService();
		System.out.println("Intento de login: " + emailUser + "  " + password);

		// Si se ha rellenado los campos
		if (emailUser.length() > 0 && password.length() > 0) {
			System.out.println("Usuario y contraseña no están vacíos.");

			

			// 2 ACCEDER Y COMPROBAR LOS DATOS
			if (userLoginService.loginSuccess(emailUser, password)) {
				System.out.println("El usuario y la contraseña son correctas.");

				UserModel u = userLoginService.getUserData(emailUser);

				UserSessionModel userSessionModel = new UserSessionModel(true, u);

				sesion.setAttribute("sesionUsuario", userSessionModel);
				sesion.setAttribute("usuarioObj", u);

				// boolean prueba = (boolean) sesion.getAttribute("sesionUsuario");

				// 3 REDIRIGIR
				request.getRequestDispatcher("./").forward(request, response);

			} else {
				request.setAttribute("email", emailUser);
				request.setAttribute("password", password);

				request.setAttribute("mensajeErrorLogin", "El usuario o password son incorrectos");
				request.getRequestDispatcher("Login1").forward(request, response);
			}
		} else {
			request.setAttribute("email", emailUser);
			request.setAttribute("password", password);
			request.setAttribute("mensajeErrorLogin", "El usuario o password se encuentran vacíos");
			request.getRequestDispatcher("Login1").forward(request, response);
		}

		// Obtener la URL de la página anterior (si existe)
		// String referer = request.getHeader("Referer");
		// response.sendRedirect(referer);
		// System.out.println(referer);

		// doGet(request, response);
	}
}
