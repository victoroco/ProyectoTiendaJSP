package services;

import models.LineaPedidoModel;
import models.PedidoModel;

import java.util.List;

import dao.LineaPedidoDAO;
import dao.PedidoDAO;

public class PedidosService {

	public List<PedidoModel> obtenerListadoPedidos(int idUsuario) {

		List<PedidoModel> listadoPedidos = PedidoDAO.obtenerPedidosUsuario(idUsuario);

		return listadoPedidos;
	}

	public List<LineaPedidoModel> obtenerLineasPedido(int idPedido, int idUsuario) {
		
		List<LineaPedidoModel> lineasPedido = LineaPedidoDAO.obtenerLineasPedido(idPedido, idUsuario);

		return lineasPedido;
	}

}
