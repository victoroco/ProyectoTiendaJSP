package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
import services.FinalizarPedidoService;

/**
 * Servlet implementation class ProcesarPago
 */
@WebServlet("/ProcesarPago")
public class ProcesarPago extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcesarPago() {
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
		FinalizarPedidoService finalizarPedidoService = new FinalizarPedidoService();
		String nextJSP = "/Login1";

		if (request.getSession().getAttribute("sesionUsuario") != null) {

			nextJSP = "/carrito";

			HashMap<Integer, ArticuloModel> carrito = (HashMap<Integer, ArticuloModel>) request.getSession()
					.getAttribute("carrito");

			// Actualizar info prod carrito
			if (carrito.size() > 0) {
				// nextJSP = "/WEB-INF/FinalizandoPago.jsp";

				if (request.getParameter("pagos") != null) {

					// Calcular el precio total del carrito
					double precioTotal = 0;
					precioTotal = checkArticuloService.calcularTotalCarrito(carrito);

					BigDecimal bd = new BigDecimal(precioTotal).setScale(2, RoundingMode.HALF_UP);
				    //double newInput = bd.doubleValue();
					precioTotal = bd.doubleValue();
					
					carrito = checkArticuloService.actualizarProdCarrito(carrito);

					// Comprobamos si algún producto se ha quedado sin stock
					ArrayList<Integer> prodSinStock = checkArticuloService.comprobaStockCarrito(carrito);

					UserSessionModel userSessionModel = (UserSessionModel) request.getSession()
							.getAttribute("sesionUsuario");
					int userId = userSessionModel.getUsuario().getIdUsuario();
					int metodoPago = Integer.parseInt(request.getParameter("pagos"));

					// Ultima comprobacion si hay problemas de stock
					if (prodSinStock.size() == 0) {

						// Insertamos pedido y lineas
						if (finalizarPedidoService.insertarPedidoLineas(userId, metodoPago, precioTotal, carrito)) {

							// nextJSP = "/PagoCompletado.jsp";
							nextJSP = "/WEB-INF/PagoCompletado.jsp";
							carrito = new HashMap<Integer, ArticuloModel>();
							request.getSession().setAttribute("carrito", carrito);
							System.out.println("Carrito limpiado");
							request.getSession().setAttribute("cantidadTotalCarrito", 0);
						}
					}
				}
			}
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
		// request.getRequestDispatcher("PagoCompletado.jsp").forward(request,
		// response);
	}

}
