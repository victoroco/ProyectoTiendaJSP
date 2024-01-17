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

/**
 * Servlet implementation class Carrito
 */
@WebServlet("/Carrito1")
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextJSP = "/WEB-INF/carrito.jsp";
		
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

		// forward to JSP to show result
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
