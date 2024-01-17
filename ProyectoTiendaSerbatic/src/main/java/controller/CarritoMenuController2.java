package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
@WebServlet("/DetalleProductoAdd")
public class CarritoMenuController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CarritoMenuController2() {
		super();
	}

	// GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ServletCarrito");

		CheckArticuloService checkArticulo = new CheckArticuloService();

		HttpSession sesion = request.getSession();
		Map<Integer, ArticuloModel> carrito = (HashMap<Integer, ArticuloModel>) sesion.getAttribute("carrito");

		int id = Integer.parseInt(request.getParameter("id"));
		ArticuloModel articulo = checkArticulo.getArticulo(id);
		int cantidadTotal = (int) sesion.getAttribute("cantidadTotalCarrito");
		int cantidad = 1;

		// Si existe en el carrito
		if (carrito.containsKey(id)) {
			ArticuloModel articuloCarrito = carrito.get(id);
			cantidad = articuloCarrito.getCantidad();
			cantidad++;
		}

		articulo.setCantidad(cantidad);
		
		// Stock
		if (checkArticulo.sufStockDeBaja(id, cantidad)) {

			// Actualizar articulo
			articulo = checkArticulo.getArticulo(id);
			articulo.setCantidad(cantidad);

			carrito.put(id, articulo);
			cantidadTotal++;
		} else {
			request.setAttribute("errorAddArticulo", articulo.getNombre() + " no tiene stock suficiente.");
			System.out.println("Error stock: " + request.getAttribute("errorAddArticulo"));
		}

		if (articulo != null) {
			request.setAttribute("articulo", articulo);
		}
		
		System.out.println("CantidadTotal: " + cantidadTotal);
		sesion.setAttribute("cantidadTotalCarrito", cantidadTotal);

		sesion.setAttribute("carrito", carrito);
		System.out.println("Id prod: " + id + ". CantidadTotalCarrito: " + carrito.get(id));

		request.setAttribute("articulo", articulo);

		// forward to JSP to show result
		String nextJSP = "/WEB-INF/DetalleProducto.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);

		// request.getRequestDispatcher("./").forward(request, response);
	}
}
