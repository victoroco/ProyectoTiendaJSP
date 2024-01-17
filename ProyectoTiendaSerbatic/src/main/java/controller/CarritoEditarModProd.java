package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ArticuloModel;
import services.CheckArticuloService;

/**
 * Servlet implementation class CarritoEditarModProd
 */
@WebServlet("/carritoeditarmodprod")
public class CarritoEditarModProd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarritoEditarModProd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

		CheckArticuloService checkArticulo = new CheckArticuloService();
		ArticuloModel articulo;
		HttpSession sesion = request.getSession();

		Map<Integer, ArticuloModel> carrito = (HashMap<Integer, ArticuloModel>) sesion.getAttribute("carrito");
		int id = Integer.parseInt(request.getParameter("idProdModificar"));
		int cantidadTotal = (int) sesion.getAttribute("cantidadTotalCarrito");
		int cantidadProductoNueva = Integer.parseInt(request.getParameter("cantidadProducto"));
		int cantidadProductoVieja;
		articulo = carrito.get(id);

		System.out.println("Carrito. Intendando modificar producto con id: " + id);

		if (carrito.containsKey(id)) {

			if (checkArticulo.sufStockDeBaja(id, cantidadProductoNueva)) {

				cantidadProductoVieja = articulo.getCantidad();
				articulo.setCantidad(cantidadProductoNueva);

				carrito.put(id, articulo);

				cantidadTotal -= cantidadProductoVieja;
				cantidadTotal += cantidadProductoNueva;
				System.out.println("Producto modificado del carrito.");

				sesion.setAttribute("cantidadTotalCarrito", cantidadTotal);
				sesion.setAttribute("carrito", carrito);
			} else {
				request.setAttribute("errorAddArticulo", articulo.getNombre() + " no tiene stock suficiente.");
			}

		}

		request.getRequestDispatcher("redirIndexCarritoController").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession();
		Map<Integer, ArticuloModel> carrito = (HashMap<Integer, ArticuloModel>) sesion.getAttribute("carrito");

		int id = Integer.parseInt(request.getParameter("idProdEliminar"));
		int cantidadTotal = (int) sesion.getAttribute("cantidadTotalCarrito");

		System.out.println("Carrito. Intendando eliminar producto con id: " + id);

		if (carrito.containsKey(id)) {
			int cantidad = carrito.get(id).getCantidad();
			carrito.remove(id);
			cantidadTotal -= cantidad;
			System.out.println("Producto eliminado del carrito.");
		}

		sesion.setAttribute("cantidadTotalCarrito", cantidadTotal);
		sesion.setAttribute("carrito", carrito);
		request.getRequestDispatcher("redirIndexCarritoController").forward(request, response);
	}

}
