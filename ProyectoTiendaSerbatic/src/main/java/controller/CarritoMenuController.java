package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ArticuloDAO;
import models.ArticuloModel;
import services.CheckArticuloService;

/**
 * Servlet implementation class CarritoServlet
 */
@WebServlet("/carrito")
public class CarritoMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarritoMenuController() {
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
		System.out.println("ServletCarrito");

		CheckArticuloService checkArticulo = new CheckArticuloService();
		ArticuloModel articulo;

		HttpSession sesion = request.getSession();
		Map<Integer, ArticuloModel> carrito = (HashMap<Integer, ArticuloModel>) sesion.getAttribute("carrito");
		int id = Integer.parseInt(request.getParameter("id"));

		int cantidadTotal = (int) sesion.getAttribute("cantidadTotalCarrito");
		int cantidad = 1;
		String nombreProd = request.getParameter("nombre");

		if (carrito.containsKey(id)) {

			articulo = carrito.get(id);
			cantidad = articulo.getCantidad();

			if (checkArticulo.sufStockDeBaja(id, cantidad + 1)) {

				cantidad++;
				articulo.setCantidad(cantidad);
				carrito.put(id, articulo);
				cantidadTotal++;
			} else {
				request.setAttribute("errorAddArticulo", nombreProd + " no tiene stock suficiente.");
			}
		} else {

			if (checkArticulo.suficienteStock(id, cantidad) && checkArticulo.articuloDeBaja(id) == false) {
				double precio = Double.parseDouble(request.getParameter("precio"));
				articulo = new ArticuloModel(id, request.getParameter("nombre"), "", precio,
						request.getParameter("imagen"), cantidad, Integer.parseInt(request.getParameter("stock")));

				carrito.put(id, articulo);
				cantidadTotal++;
			} else {
				request.setAttribute("errorAddArticulo", nombreProd + " no tiene stock suficiente.");
			}
		}

		System.out.println("Error stock: " + request.getAttribute("errorAddArticulo"));
		System.out.println("CantidadTotal: " + cantidadTotal);

		sesion.setAttribute("cantidadTotalCarrito", cantidadTotal);
		sesion.setAttribute("carrito", carrito);
		System.out.println("Id prod: " + id + ". CantidadTotalCarrito: " + carrito.get(id));

		request.getRequestDispatcher("./").forward(request, response);
	}
}
