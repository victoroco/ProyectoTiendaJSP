package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connector.Conexion;
import models.UserModel;

public class UserDAO {

	// static private Connection con;
	static private PreparedStatement prprdStmt;

	/*
	 * public UserDAO() { try { Class.forName("com.mysql.cj.jdbc.Driver"); String
	 * url =
	 * "jdbc:mysql://localhost:3306/tiendaserbatic?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 * String usuario = "root"; String contrasenia = ""; con =
	 * DriverManager.getConnection(url, usuario, contrasenia); } catch
	 * (ClassNotFoundException | SQLException e) { e.printStackTrace(); } }
	 */

	public static UserModel getAllUserData(String email) {
		Connection con = Conexion.getConexion();

		UserModel u = null;
		try {
			System.out.println("Email: " + email + " Para obtener todo el user data.");
			String sql = "select * from usuario where email = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				u = new UserModel(rs.getInt("usuario_id"), rs.getInt("rol_id"), rs.getString("email"), rs.getString("password"),
						rs.getString("nombre"), rs.getString("apellidos"), rs.getString("direccion"),
						rs.getString("provincia"), rs.getString("ciudad"), rs.getString("telefono"),
						rs.getString("dni"), rs.getBoolean("baja"));
			}

			return u;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	public static boolean checkUserPasswordDataDB(String email, String password) {
		Connection con = Conexion.getConexion();
		try {

			String sql = "select * from usuario where email = ? and password = ?";
			PreparedStatement prprdStmt = con.prepareStatement(sql);
			prprdStmt.setString(1, email);
			prprdStmt.setString(2, password);

			ResultSet rs = prprdStmt.executeQuery();

			if (rs.next())
				if (rs.getInt("rol_id") == 1) {
					return true;
				} else {
					return false;
				}
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean insertarUsuario(UserModel userModel) {

		Connection con = Conexion.getConexion();
		try {

			PreparedStatement stmt = con.prepareStatement(
					"insert into usuario (rol_id, email, password, nombre, apellidos,direccion,provincia,ciudad, telefono,dni,baja) values(?,?,?,?,?,?,?,?,?,?,?)");

			stmt.setInt(1, 1);
			stmt.setString(2, userModel.getEmail());// 1 specifies the first parameter in the query
			stmt.setString(3, userModel.getPassword());
			stmt.setString(4, userModel.getNombre());
			stmt.setString(5, userModel.getApellidos());
			stmt.setString(6, userModel.getDireccion());
			stmt.setString(7, userModel.getProvincia());
			stmt.setString(8, userModel.getCiudad());
			stmt.setString(9, userModel.getTelefono());
			stmt.setString(10, userModel.getDni());
			stmt.setBoolean(11, false);

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

	public static boolean updateDatosUsuario(UserModel userModel) {
		Connection con = Conexion.getConexion();

		try {
			// create our java preparedstatement using a sql update query
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE usuario SET nombre = ?, apellidos = ?, direccion = ?, provincia = ?, ciudad = ?, telefono = ?, dni = ? WHERE usuario_id = ?");

			// set the preparedstatement parameters
			stmt.setString(1, userModel.getNombre());
			stmt.setString(2, userModel.getApellidos());
			stmt.setString(3, userModel.getDireccion());
			stmt.setString(4, userModel.getProvincia());
			stmt.setString(5, userModel.getCiudad());
			stmt.setString(6, userModel.getTelefono());
			stmt.setString(7, userModel.getDni());
			stmt.setInt(8, userModel.getIdUsuario());

			int i = stmt.executeUpdate();

			con.commit();

			if (i > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean updateEmailPassword(UserModel userModel, String email, String passwordNueva) {
		Connection con = Conexion.getConexion();
		PreparedStatement stmt;
		try {
			// create our java preparedstatement using a sql update query
			if (!userModel.getEmail().equals(email)) {
				stmt = con.prepareStatement("UPDATE usuario SET email = ?, password = ? WHERE usuario_id = ?");

				// set the preparedstatement parameters
				stmt.setString(1, email);
				stmt.setString(2, passwordNueva);
				stmt.setInt(3, userModel.getIdUsuario());
			} else {

				stmt = con.prepareStatement("UPDATE usuario SET password = ? WHERE usuario_id = ?");

				// set the preparedstatement parameters
				stmt.setString(1, passwordNueva);
				stmt.setInt(2, userModel.getIdUsuario());

			}

			int i = stmt.executeUpdate();

			con.commit();

			if (i > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
