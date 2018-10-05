package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private Connection conexion = null;
	private final String db = "cubihoyts";
	private final String user = "root";
	private final String pass = "1234";
	private final String url = "jdbc:mysql://127.0.0.1/" + db;
	private static Conexion con;
	
	public static Conexion getInstance() {
		if(con==null)
			con=new Conexion();
		return con;
	}

	private Conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, pass);
			if (conexion != null) {
				System.out.println("Conexion a base de datos " + url + " . . . Ok");
			}
		} catch (final SQLException ex) {
			System.out.println("Hubo un problema al intentar conectarse a la base de datos " + url);
			System.out.println(ex);
		} catch (final ClassNotFoundException ex) {
			System.out.println(ex);
		}
	}
	public Connection getConexion() {
		return conexion;
	}

}
		