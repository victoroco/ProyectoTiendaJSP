package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ArticuloModel;
import models.LineaPedidoModel;
import models.PedidoModel;
import models.UserModel;
import models.UserSessionModel;
import services.PedidosService;

/**
 * Servlet implementation class DetallePedido
 */
@WebServlet("/DetallePedido")
public class DetallePedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetallePedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextJSP = "/WEB-INF/DetallePedido.jsp";
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
		} else {
			System.out.println(request.getAttribute("idPedido"));

			if (request.getParameter("idPedido") != null) {
				PedidosService registerService = new PedidosService();

				UserSessionModel userSessionModel = (UserSessionModel) request.getSession()
						.getAttribute("sesionUsuario");
				UserModel userModelSesion = userSessionModel.getUsuario();

				int idPedido = Integer.parseInt(request.getParameter("idPedido"));
				// int idPedido = 32;

				List<LineaPedidoModel> lineasPedido = registerService.obtenerLineasPedido(idPedido,
						userModelSesion.getIdUsuario());

				for (LineaPedidoModel lineaPedidoModel : lineasPedido) {
					System.out.println("Linea: " + lineaPedidoModel.toString());
				}

				request.setAttribute("idPedido", idPedido);
				request.setAttribute("lineasPedido", lineasPedido);
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
		doGet(request, response);
	}

}
