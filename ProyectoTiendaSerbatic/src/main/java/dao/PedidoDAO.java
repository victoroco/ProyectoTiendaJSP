package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import connector.Conexion;
import models.PedidoModel;

public class PedidoDAO {

	public static boolean insertarPedido(PedidoModel pedidoModel) {

		Connection con = Conexion.getConexion();
		try {

			
			
			PreparedStatement stmt = con
					.prepareStatement("insert into pedido (usuario_id, id_metodo_pago, total) values(?,?,?)");

			stmt.setInt(1, pedidoModel.getUsuario_id());
			stmt.setInt(2, pedidoModel.getMetodoPago());
			stmt.setDouble(3, pedidoModel.getTotal());

			int i = stmt.executeUpdate();

			// System.out.println(i + " records inserted");

			con.commit();

			if (i > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int obtenerIdUltimoPedido(int idUsuario) {
		Connection con = Conexion.getConexion();
		try {

			String sql = "select id_pedido from pedido where usuario_id = ? order by id_pedido DESC limit 1";
			PreparedStatement prprdStmt = con.prepareStatement(sql);
			prprdStmt.setInt(1, idUsuario);

			ResultSet rs = prprdStmt.executeQuery();

			if (rs.next())

				return rs.getInt(1);
			else
				return 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//public static List<PedidoModel> obtenerPedidosUsuario(int idUsuario, String fechaInicio, String fechaFin) {
	public static List<PedidoModel> obtenerPedidosUsuario(int idUsuario) {
		Connection con = Conexion.getConexion();
		List<PedidoModel> listaPedidos = new ArrayList<PedidoModel>();
		try {

			String sql = "select * from pedido join metodo_pago where pedido.id_metodo_pago = metodo_pago.id_metodo_pago and usuario_id = ? ";
			PreparedStatement prprdStmt = con.prepareStatement(sql);
			prprdStmt.setInt(1, idUsuario);

			ResultSet rs = prprdStmt.executeQuery();

			while (rs.next()) {
				listaPedidos.add(new PedidoModel(rs.getInt("id_pedido"), rs.getTimestamp("fecha"), rs.getInt("id_metodo_pago"),
						rs.getString("nombre_pago"), rs.getDouble("total")));
				System.out.println(rs.getInt("id_pedido") + ", " + rs.getInt("id_metodo_pago") + ", " + ", "
						+ rs.getString("nombre_pago") + ", " + rs.getDouble("total"));

			}
			System.out.println(listaPedidos.get(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPedidos;
	}

}
