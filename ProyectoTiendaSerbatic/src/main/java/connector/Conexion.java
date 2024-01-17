package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
	static String bd = "tiendaserbatic?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String login = "root";
	static String password = "";
	static String host = "127.0.0.1"; //localhost
	
	static String url = "jdbc:mysql://";
	static Connection conexion; //atributo para  guardar el objeto Connection
        
    
	public static Connection getConexion() {
	   //SINGLETON
    	if (conexion == null) {
	    	crearConexion();
	    }
    	
	    return conexion;
	    
    }
    
    // devuelve true si se ha creado correctamente
    public static boolean crearConexion() {
	    try {
	        //cargo el driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexion = DriverManager.getConnection(url + host + "/"+ bd, login, password);    
                
            conexion.setAutoCommit(false);
	        
	    } catch (SQLException e) {
	    	System.err.println(e);
	    	return false;
	    }
	    catch (Exception e) {
	    	System.err.println(e);
	    	return false;
	    }
	    return true;
    }

    public static void desconectar(){
    	try {
            conexion.close();
            conexion = null;
            System.out.println("La conexion a la  base de datos " + bd + " ha terminado");
    	
    	} catch (SQLException e) {
    		System.out.println("Error al cerrar la conexion");
        }
    }
   
    public static void main(String[] args) {
		getConexion();
	}
}
