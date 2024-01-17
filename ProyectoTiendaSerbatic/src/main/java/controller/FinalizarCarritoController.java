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
import models.UserSessionModel;
import services.CheckArticuloService;

/**
 * Servlet implementation class FinalizarCarrito
 */

@WebServlet("/finalizarcarrito")
public class FinalizarCarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FinalizarCarritoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CheckArticuloService checkArticuloService = new CheckArticuloService();

		String nextJSP = "/Login1";

		if (request.getSession().getAttribute("sesionUsuario") != null) {

			// UserSessionModel userSessionModel = (UserSessionModel)
			// request.getSession().getAttribute("sesionUsuario");

			HashMap<Integer, ArticuloModel> carrito = (HashMap<Integer, ArticuloModel>) request.getSession()
					.getAttribute("carrito");

			// Actualizar info prod carrito
			if (carrito.size() > 0) {

				// Calcular el precio total del carrito
				double precioTotal = 0;
				precioTotal = checkArticuloService.calcularTotalCarrito(carrito);

				nextJSP = "/WEB-INF/FinalizandoPago.jsp";

			}

			// request.getRequestDispatcher("FinalizandoPago.jsp").forward(request,
			// response);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
