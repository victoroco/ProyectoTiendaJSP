package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ArticuloModel;
import models.UserModel;
import services.PasswordUtilsService;
import services.RegisterService;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet("/registrosubmit")
public class RegistroUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistroUsuarioController() {
		super();
		// TODO Auto-generated constructor stub
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

		UserModel userModel = new UserModel(request.getParameter("email"), request.getParameter("password"),
				request.getParameter("nombre"), request.getParameter("apellidos"), request.getParameter("direccion"),
				request.getParameter("provincia"), request.getParameter("ciudad"), request.getParameter("telefono"),
				request.getParameter("dni"));

		String password2 = request.getParameter("repetirPassword");

		RegisterService registerService = new RegisterService();
		boolean error = false;
		if (registerService.registroVacio(userModel)) {
			request.setAttribute("errRegistVacio", "Los campos marcados con * son obligatorios.");
			error = true;
		} else if (registerService.emailExiste(userModel.getEmail())) {
			request.setAttribute("errRegistEmailExist", "Ese email ya se encuentra en uso.");
			error = true;
		}

		if (!userModel.getPassword().equals(password2)) {
			request.setAttribute("errRegistPasswords", "Las contraseñas no coinciden.");
			error = true;
		}

		if (error) {
			request.setAttribute("datosRegistro", userModel);

			request.getRequestDispatcher("registro1").forward(request, response);
		} else {

			request.setAttribute("exitoRegistro", true);

			// Guardar password encriptada
			/*
			 * try { userModel.setPassword(PasswordUtilsService.generateStrongPasswordHash(
			 * userModel.getPassword())); } catch (NoSuchAlgorithmException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } catch
			 * (InvalidKeySpecException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */

			if (registerService.insertarNuevoUsuario(userModel)) {
				System.out.println("Usuario insertado con éxito.");
			}

			request.getRequestDispatcher("Login1").forward(request, response);

		}

	}

}
