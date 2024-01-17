package services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.LineaPedidoDAO;
import dao.PedidoDAO;
import models.ArticuloModel;
import models.LineaPedidoModel;
import models.PedidoModel;

public class FinalizarPedidoService {

	// Insertar pedido y lineas
	public boolean insertarPedidoLineas(int userId, int metodoPago, double precioTotal,
			HashMap<Integer, ArticuloModel> carrito) {

		PedidoModel pedidoModel = new PedidoModel(userId, metodoPago, precioTotal);

		// Insertar pedido
		if (PedidoDAO.insertarPedido(pedidoModel)) {

			int idPedido = PedidoDAO.obtenerIdUltimoPedido(userId);
					
			if (idPedido > 0) {
				// Obtener lineas de pedido
				ArrayList<LineaPedidoModel> listaLineasPedido = getListaLineasPedido(idPedido, carrito);

				if (listaLineasPedido.size() > 0) {
					// Insertar lineas de pedido
					if (LineaPedidoDAO.insertarLineaPedido(listaLineasPedido)) {
						return true;
					}

				}

			}

		}
		return false;
	}

	public ArrayList<LineaPedidoModel> getListaLineasPedido(int idPedido, HashMap<Integer, ArticuloModel> carrito) {

		ArrayList<LineaPedidoModel> listaLineasPedido = new ArrayList<LineaPedidoModel>();

		for (Map.Entry<Integer, ArticuloModel> entry : carrito.entrySet()) {

			ArticuloModel articulo = entry.getValue();

			double totalLinea = 0;
			
			totalLinea = articulo.getCantidad() * articulo.getPrecio();
			
			BigDecimal bd = new BigDecimal(totalLinea).setScale(2, RoundingMode.HALF_UP);
			totalLinea = bd.doubleValue();
			
			
			listaLineasPedido.add(new LineaPedidoModel(idPedido, articulo.getId(), articulo.getCantidad(),
					articulo.getPrecio(), 0, articulo.getImpuesto(), totalLinea));

		}

		return listaLineasPedido;
	}

}
