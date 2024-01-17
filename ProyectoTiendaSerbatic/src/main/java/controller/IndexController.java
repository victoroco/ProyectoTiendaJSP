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

import connector.Conexion;
import dao.ArticuloDAO;
import models.ArticuloModel;
import models.UserSessionModel;
import services.CheckArticuloService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(value = (""))
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private ArticuloDAO articuloDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		// articuloDAO = new ArticuloDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filtro = request.getParameter("filtros");

		// Catalogo BBDD
		List<ArticuloModel> catalogo = ArticuloDAO.obtenerCatalogo(filtro);
		request.setAttribute("catalogo", catalogo);

		/*
		 * if (catalogo == null) {
		 * 
		 * CheckArticulo.removeArticulos();
		 * 
		 * request.setAttribute("catalogo", catalogo); } else {
		 * request.setAttribute("catalogo", catalogo);
		 * 
		 * }
		 */

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

		if (request.getSession().getAttribute("sesionUsuario") != null) {
			UserSessionModel userSessionModel = (UserSessionModel) request.getSession().getAttribute("sesionUsuario");
			request.setAttribute("nombreUsuario", userSessionModel.getUsuario().getNombre());
		}

		// forward to JSP to show result
		String nextJSP = "/WEB-INF/index.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);

		// request.getRequestDispatcher("index.jsp").forward(request, response);
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
