package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import connector.Conexion;
import models.ArticuloModel;
import models.CategoriaModel;
import services.CheckArticuloService;

//Clase DAO que se encarga de obtener los artículos desde la base de datos
public class ArticuloDAO {
	// private static Connection conexion;

	/*
	 * public static List<ArticuloModelo> catalogo;
	 * 
	 * public static List<ArticuloModelo> obtenerCatalogoDemo() {
	 * 
	 * catalogo = new ArrayList<ArticuloModelo>();
	 * 
	 * catalogo.add(new ArticuloModelo(1, "Sombrilla", "De pueblo", 14.59, "", 0, 5,
	 * false)); catalogo.add(new ArticuloModelo(2, "Toalla", "Para verano", 8.99,
	 * "", 0, 3, false)); catalogo.add(new ArticuloModelo(3, "Patatas", "Nuevas",
	 * 1.19, "", 0, 0, false)); catalogo.add(new ArticuloModelo(4, "Televisión",
	 * "Samsung SmartTV", 444.00, "", 0, 2, false)); catalogo.add(new
	 * ArticuloModelo(5, "Libro", "Libro tapa dura", 18.99, "", 0, 10, false));
	 * catalogo.add(new ArticuloModelo(6, "Almohada", "de ensueño", 19.99, "", 0,
	 * 20, false)); catalogo.add(new ArticuloModelo(7, "Piedras", "Muy bonitas",
	 * 999.99, "", 0, 1000, false)); catalogo.add(new ArticuloModelo(7,
	 * "Ropa ninja", "Misteriosa", 1.00, "", 0, 1, true));
	 * 
	 * return catalogo;
	 * 
	 * }
	 */

	/*
	 * public ArticuloDAO() { try { Class.forName("com.mysql.cj.jdbc.Driver");
	 * String url =
	 * "jdbc:mysql://localhost:3306/tiendaserbatic?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 * String usuario = "root"; String contrasenia = ""; conexion =
	 * DriverManager.getConnection(url, usuario, contrasenia); } catch
	 * (ClassNotFoundException | SQLException e) { e.printStackTrace(); } }
	 */

	public static List<ArticuloModel> obtenerCatalogo(String filtroCategoria) {
		PreparedStatement pstmt;
		ResultSet resultSet;
		Connection con = Conexion.getConexion();
		List<ArticuloModel> catalogo = new ArrayList<>();

		try {
			if (filtroCategoria == "" || filtroCategoria == null) {
				String sql = "SELECT * FROM producto p JOIN categoria_producto cp on p.id_categoria= cp.id_categoria limit 24";
				pstmt = con.prepareStatement(sql);
				resultSet = pstmt.executeQuery();
			} else {
				String sql = "SELECT * FROM producto p JOIN categoria_producto cp on p.id_categoria= cp.id_categoria where id_categoria = ? limit 24";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, filtroCategoria);
				resultSet = pstmt.executeQuery();
			}

			/*
			 * Statement statement = con.createStatement(); ResultSet resultSet = statement
			 * .executeQuery("SELECT * FROM producto JOIN categoria_producto on categoria=id_categoria limit 24"
			 * );
			 */
			while (resultSet.next()) {
				int id = resultSet.getInt("id_producto");
				String nombre = resultSet.getString("nombre");
				String descripcion = resultSet.getString("descripcion");
				int idCategoria = resultSet.getInt("id_categoria");
				String categoria = resultSet.getString("nombre_categoria");
				double precio = resultSet.getDouble("precio");
				String imagen = resultSet.getString("imagen");
				double impuesto = resultSet.getDouble("impuesto");
				int stock = resultSet.getInt("stock");
				boolean baja = resultSet.getBoolean("baja");
				// ArticuloModelo articulo = new ArticuloModelo(id, nombre, descripcion, precio,
				// imagen);
				ArticuloModel articulo = new ArticuloModel(id, nombre, descripcion,
						new CategoriaModel(idCategoria, categoria), precio, imagen, 0, impuesto, stock, baja);
				catalogo.add(articulo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return catalogo;
	}

	public static ArticuloModel obtenerArticulo(int id) {
		Connection con = Conexion.getConexion();
		ArticuloModel articulo = null;
		String sql = "SELECT * FROM producto p join categoria_producto cp WHERE p.id_categoria=cp.id_categoria and id_producto = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				articulo = new ArticuloModel(resultSet.getInt("id_producto"), resultSet.getString("nombre"),
						resultSet.getString("descripcion"),
						new CategoriaModel(resultSet.getInt("id_categoria"), resultSet.getString("nombre_categoria")),
						resultSet.getDouble("precio"), resultSet.getString("imagen"), 0,
						resultSet.getDouble("impuesto"), resultSet.getInt("stock"), resultSet.getBoolean("baja"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return articulo;
	}

	public static ArticuloModel obtenerStockArticulo(int id) {
		Connection con = Conexion.getConexion();

		ArticuloModel articulo = null;
		String sql = "SELECT * FROM producto WHERE id_producto = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				articulo = new ArticuloModel();
				articulo.setId(rs.getInt("id_producto"));
				// articulo.setIdCategoria(rs.getInt("id_categoria"));
				articulo.setNombre(rs.getString("nombre"));
				articulo.setDescripcion(rs.getString("descripcion"));
				articulo.setPrecio(rs.getDouble("precio"));
//	            articulo.setStock(rs.getInt("stock"));
//	            articulo.setFechaAlta(rs.getTimestamp("fecha_alta"));
//	            articulo.setFechaBaja(rs.getTimestamp("fecha_baja"));
//	            articulo.setImpuesto(rs.getFloat("impuesto"));
//	            articulo.setImagen(rs.getString("imagen"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return articulo;
	}

	public static void main(String[] args) {

	}
}