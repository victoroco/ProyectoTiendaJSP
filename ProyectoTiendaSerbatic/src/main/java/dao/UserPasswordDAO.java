package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import connector.Conexion;

public class UserPasswordDAO {

	static private Connection con;
	static private PreparedStatement prprdStmt;

	/*
	 * public static boolean checkLogin(String usuario, String campoUsuario, String
	 * password, String campoPassword, String tabla) { if
	 * (checkUserPasswordDataDB(usuario, campoUsuario, password, campoPassword,
	 * tabla)) { return true; } return false; }
	 */
	public static boolean checkUserPasswordDataDB(String dato1, String campo1, String dato2, String campo2,
			String tabla) {
		con = Conexion.getConexion();
		try {
			String sql = "select * from " + tabla + " where " + campo1 + " = " + dato1 + " and " + campo2 + " = "
					+ dato2;
			prprdStmt = con.prepareStatement(sql);
			ResultSet rs = prprdStmt.executeQuery();

			if (rs.next())
				return true;

			if (con != null) {
				Conexion.desconectar();
			} else {
				System.out.println("Conexion no realizada");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
