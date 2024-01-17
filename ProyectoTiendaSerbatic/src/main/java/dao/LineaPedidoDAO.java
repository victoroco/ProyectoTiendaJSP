package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import connector.Conexion;
import models.ArticuloModel;
import models.CategoriaModel;
import models.LineaPedidoModel;
import models.PedidoModel;
import models.UserModel;

public class LineaPedidoDAO {

	public static boolean insertarLineaPedido(ArrayList<LineaPedidoModel> lineasPedido) {

		Connection con = Conexion.getConexion();
		try {
			int num = 0;

			for (int i = 0; i < lineasPedido.size(); i++) {

				PreparedStatement stmt = con.prepareStatement(
						"insert into detalle_pedido (pedido_id, producto_id, unidades, preciounidad, precioEnvio,impuesto,total) values(?,?,?,?,?,?,?)");

				stmt.setInt(1, lineasPedido.get(i).getPedido_id());
				stmt.setInt(2, lineasPedido.get(i).getProducto_id());
				stmt.setInt(3, lineasPedido.get(i).getUnidades());
				stmt.setDouble(4, lineasPedido.get(i).getPreciounidad());
				stmt.setDouble(5, lineasPedido.get(i).getPrecioEnvio());
				stmt.setDouble(6, lineasPedido.get(i).getImpuesto());
				stmt.setDouble(7, lineasPedido.get(i).getTotal());

				num += stmt.executeUpdate();

			}
			System.out.println("LineasPedido insertadas: " + num);
			// System.out.println(i + " records inserted");

			con.commit();

			if (num > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<LineaPedidoModel> obtenerLineasPedido(int idPedido, int idUsuario) {
		Connection con = Conexion.getConexion();
		List<LineaPedidoModel> lineasPedido = new ArrayList<LineaPedidoModel>();
		try {

			String sql = "SELECT * FROM producto join detalle_pedido on producto.id_producto=detalle_pedido.producto_id join pedido on detalle_pedido.pedido_id=pedido.id_pedido where pedido_id = ? and usuario_id = ? ";
			PreparedStatement prprdStmt = con.prepareStatement(sql);
			prprdStmt.setInt(1, idPedido);
			prprdStmt.setInt(2, idUsuario);

			ResultSet rs = prprdStmt.executeQuery();

			while (rs.next()) {
				lineasPedido.add(new LineaPedidoModel(rs.getInt("pedido_id"), rs.getInt("producto_id"),
						rs.getInt("unidades"), rs.getDouble("preciounidad"), rs.getDouble("precioEnvio"),
						rs.getDouble("impuesto"), rs.getDouble("total"),
						new ArticuloModel(rs.getInt("id_producto"), rs.getString("nombre"), rs.getString("descripcion"), null,
								rs.getDouble("precio"), rs.getString("imagen"), 0, rs.getDouble("impuesto"),
								rs.getInt("stock"), rs.getBoolean("baja"))));
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lineasPedido;
	}

}
