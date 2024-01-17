package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class RedirIndexCarritoController
 */
@WebServlet("/redirIndexCarritoController")
public class RedirIndexCarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RedirIndexCarritoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession(false) == null) {
			request.getSession();
			request.getSession().setAttribute("carrito", new HashMap<Integer, ArticuloModel>());
			request.getSession().setAttribute("cantidadTotalCarrito", 0);

			System.out.println("Sesion creada.");
		}
		if (request.getSession().getAttribute("sesionUsuario") != null) {
			UserSessionModel userSessionModel = (UserSessionModel) request.getSession().getAttribute("sesionUsuario");
			request.setAttribute("nombreUsuario", userSessionModel.getUsuario().getNombre());
		}

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("RedirCarrito");
		String nextJSP = "/WEB-INF/carrito.jsp";

		CheckArticuloService checkArticuloService = new CheckArticuloService();
		HashMap<Integer, ArticuloModel> carrito = (HashMap<Integer, ArticuloModel>) request.getSession()
				.getAttribute("carrito");

		// Actualizar info prod carrito
		if (carrito.size() > 0) {
			carrito = checkArticuloService.actualizarProdCarrito(carrito);

			// Comprobamos si algún producto se ha quedado sin stock
			ArrayList<Integer> prodSinStock = checkArticuloService.comprobaStockCarrito(carrito);

			// Si > 0 quitamos esos productos del carrito
			if (prodSinStock.size() > 0) {
				// Eliminar prod sin stock
				carrito = checkArticuloService.quitarProdSinStockCarrito(carrito, prodSinStock);
				//request.setAttribute("prodCarritoSinStock", prodSinStock);
			}
		}
		// Calcular el precio total del carrito
		double precioTotal = 0;
		precioTotal = checkArticuloService.calcularTotalCarrito(carrito);

		BigDecimal bd = new BigDecimal(precioTotal).setScale(2, RoundingMode.HALF_UP);
	    double precioTotal2Dec = bd.doubleValue();
		
		System.out.println("precioTotal:" + precioTotal2Dec);
		request.setAttribute("precioTotal", precioTotal2Dec);

		// forward to JSP to show result
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);

		// request.getRequestDispatcher("carrito.jsp").forward(request, response);

	}
}