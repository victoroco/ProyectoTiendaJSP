package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ArticuloModel;
import services.CheckArticuloService;

/**
 * Servlet implementation class DetalleProducto
 */
@WebServlet("/DetalleProducto")
public class DetalleProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetalleProducto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CheckArticuloService chckArtServ = new CheckArticuloService();
		int articuloId = Integer.parseInt(request.getParameter("articuloId"));

		ArticuloModel articuloModel = chckArtServ.getArticulo(articuloId);

		if (articuloModel != null) {
			request.setAttribute("articulo", articuloModel);
		}

		// forward to JSP to show result
		String nextJSP = "/WEB-INF/DetalleProducto.jsp";
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
