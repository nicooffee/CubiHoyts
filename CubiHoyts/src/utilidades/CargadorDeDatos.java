package utilidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import Conexion.Conexion;
import clases.Arriendo;
import clases.Cliente;
import clases.ConjuntoJuego;
import clases.ConjuntoPelicula;
import clases.ConjuntoProducto;
import clases.Historial;
import clases.ListaNegocio;
import clases.MapaCliente;
import clases.MapaHistorial;
import clases.ProductoVideoClub;
import clases.Venta;
import excepciones.RUTException;
import factory.Creator;
/**
 * Clase Lectura:
 * 
 * Esta clase se encarga de manejar
 * los archivos del programa, se utiliza
 * al momento de carga de archivos.
 * 
 * Todos los errores que puedan generar los
 * métodos de esta clase son manejados con
 * la excepción {@link SQLException}.
 * 
 * @author Nicolás Honorato
 *
 */
public class CargadorDeDatos {


	
	
	
	
	
	
	
	
	/**
	 * cargarDatosClientes:
	 * 
	 * Método que conecta con la base de datos de CubiHoyts
	 * y extrae datos que corresponden a los clientes
	 * del videoclub. Además, dimensiona
	 * un MapaCliente y le ingresa todos los datos
	 * de cliente que existen en la base de datos.
	 * 
	 * @return
	 * El mapa de clientes creado.
	 * @throws SQLException
	 * Se debe manejar la excepción ya que el método se conecta
	 * con una base de datos externa.
	 */
	public MapaCliente cargarDatosClientes() throws SQLException{
		MapaCliente clientes=new MapaCliente();
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "SELECT * FROM clientes";

		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			try {
				clientes.agregarCliente(rs.getString("nombre"), rs.getString("rut"), rs.getString("dir"), rs.getInt("tipo"));
			}
			catch(RUTException e) {
				e.printStackTrace();
			}
		}
		////conexion.cerrarConexion();
		return clientes;
	}


	
	
	
	
	
	
	
	
	
	/**
	 * cargarDatosPeliculas:
	 * 
	 * Método que conecta con la base de datos de CubiHoyts
	 * y extrae datos que corresponden a las películas
	 * del videoclub. Además, dimensiona
	 * un ConjuntoPelicula y le ingresa todos los datos
	 * de película que existen en la base de datos.
	 * 
	 * @return
	 * El conjunto de películas creado.
	 * @throws SQLException
	 * Se debe manejar la excepción ya que el método se conecta
	 * con una base de datos externa.
	 */
	public ConjuntoProducto cargarDatosPeliculas() throws SQLException{
		ConjuntoPelicula peliculas=(ConjuntoPelicula) Creator.crearConjunto(Creator.crearConjuntoPelicula);
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "SELECT * FROM peliculas";

		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			String tipos[]=rs.getString("genero").split(",");
			peliculas.agregarPelicula(rs.getInt("cantidad"),rs.getInt("codigo"),rs.getString("nombre"),tipos,rs.getString("descripcion"),rs.getBoolean("estreno"),rs.getInt("anioEstreno"),rs.getInt("precioArriendo"),rs.getInt("precioVenta"));
			
		}
		////conexion.cerrarConexion();
		return peliculas;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * cargarDatosJuegos:
	 * 
	 * Método que conecta con la base de datos de CubiHoyts
	 * y extrae datos que corresponden a los juegos
	 * del videoclub. Además, dimensiona
	 * un ConjuntoJuego y le ingresa todos los datos
	 * de juegos que existen en la base de datos.
	 * 
	 * @return
	 * El conjunto de juegos creado.
	 * @throws SQLException
	 * Se debe manejar la excepción ya que el método se conecta
	 * con una base de datos externa.
	 */
	public ConjuntoProducto cargarDatosJuegos() throws SQLException{
		ConjuntoJuego juegos=(ConjuntoJuego) Creator.crearConjunto(Creator.crearConjuntoJuego);
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "SELECT * FROM juegos";

		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			String tipos[]=rs.getString("genero").split(",");
			String consolas[]=rs.getString("consolas").split(",");
			juegos.agregarJuego(rs.getString("nombre"),rs.getInt("cantidad"),rs.getInt("codigo"),rs.getString("descripcion"),tipos,consolas, rs.getInt("precioArriendo"), rs.getInt("precioVenta"),rs.getString("clasificacion"),rs.getInt("maxJugadores"));
		}
		return juegos;
	}


	
	
	
	


	
	
	/**
	 * cargarDatosHistorial:
	 * 
	 * Método que conecta con la base de datos de CubiHoyts
	 * y extrae datos que corresponden a los historiales
	 * del videoclub. Además, dimensiona
	 * un MapaHistorial y le ingresa todos los datos
	 * de historiales que existen en la base de datos.
	 * 
	 * @return
	 * El mapa de historiales creado.
	 * @throws SQLException
	 * Se debe manejar la excepción ya que el método se conecta
	 * con una base de datos externa.
	 */
	public MapaHistorial cargarDatosHistorial()  throws SQLException{
		MapaHistorial historiales=new MapaHistorial();
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "SELECT * FROM historiales";

		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Historial hist=new Historial();
			hist.setRutCliente(rs.getString("rut"));
			hist.setPenalizacion(rs.getInt("penalizacion"));
			hist.setArriendosSeguidos(rs.getInt("arrSeguidos"));
			historiales.agregarHistorial(hist.getRutCliente(), hist);
		}
		////conexion.cerrarConexion();
		return historiales;
	}
	
	

	
	
	
	
	
	
	
	

	/**
	 * cargarDatosArriendos:
	 * 
	 * Método que conecta con la base de datos de CubiHoyts
	 * y extrae datos que corresponden a los arriendos
	 * del videoclub. Además, dimensiona
	 * una ListaArriendo y le ingresa todos los datos
	 * de arriendos que existen en la base de datos.
	 * 
	 * Busca en un MapaCliente y ConjuntoProducto los
	 * clientes y productos que le corresponden a cada
	 * arriendo, luego los agrega.
	 * 
	 * @param clientes
	 * MapaCliente con los clientes del videoclub.
	 * @param peliculas
	 * ConjuntoPelicula con las películas del videoclub.
	 * @param juegos
	 * ConjuntoJuego con los juegos del videoclub
	 * @return
	 * La lista de arriendos creada.
	 * @throws SQLException
	 * Se debe manejar la excepción ya que el método se conecta
	 * con una base de datos externa.
	 */
	public ListaNegocio cargarDatosArriendos(MapaCliente clientes, ConjuntoProducto peliculas,ConjuntoProducto juegos) throws SQLException{
		ListaNegocio arriendos=Creator.crearLista(Creator.crearListaArriendo);
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "SELECT * FROM arriendos";
		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Calendar inicio=Calendar.getInstance();
			inicio.setTimeInMillis(rs.getLong("fechaInicio"));
			Calendar fin=Calendar.getInstance();
			fin.setTimeInMillis(rs.getLong("fechaEntrega"));
			int cod=rs.getInt("codProducto");
			ProductoVideoClub prod;
			if(cod>=1000 && cod<=99999)
				prod=peliculas.buscarProducto(cod);
			else
				prod=juegos.buscarProducto(cod);
			Cliente auxCliente;
			try {
				auxCliente=clientes.buscarCliente(rs.getString("rutCliente"));
			}
			catch(RUTException e) {
				auxCliente=new Cliente("00000000-0");
				e.printStackTrace();
			}
			Arriendo arr=new Arriendo(auxCliente,prod,rs.getInt("ganancia"),rs.getInt("dias"),rs.getString("codigo"),inicio,fin,rs.getBoolean("completado"));																
			arriendos.agregarNegocio(arr);
			
		}
		////conexion.cerrarConexion();
		return arriendos;
	}
	
	
	
	
	
	
	
	
	
		
	/**
	 * cargarDatosVentas:
	 * 
	 * Método que conecta con la base de datos de CubiHoyts
	 * y extrae datos que corresponden a las ventas
	 * del videoclub. Además, dimensiona
	 * una ListaVenta y le ingresa todos los datos
	 * de ventas que existen en la base de datos.
	 * 
	 * Busca en un MapaCliente y ConjuntoProducto los
	 * clientes y productos que le corresponden a cada
	 * venta, luego los agrega.
	 * 
	 * @param clientes
	 * MapaCliente con los clientes del videoclub.
	 * @param peliculas
	 * ConjuntoPelicula con las películas del videoclub.
	 * @param juegos
	 * ConjuntoJuego con los juegos del videoclub
	 * @return
	 * La lista de ventas creada.
	 * @throws SQLException
	 * Se debe manejar la excepción ya que el método se conecta
	 * con una base de datos externa.
	 */
	public ListaNegocio cargarDatosVentas(MapaCliente clientes, ConjuntoProducto peliculas,ConjuntoProducto juegos)  throws SQLException{
		ListaNegocio ventas=Creator.crearLista(Creator.crearListaVenta);
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "SELECT * FROM ventas";
		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Calendar fecha=Calendar.getInstance();
			fecha.setTimeInMillis(rs.getLong("fecha"));
			int cod=rs.getInt("codProducto");
			ProductoVideoClub prod;
			if(cod>=1000 && cod<=99999)
				prod=peliculas.buscarProducto(cod);
			else
				prod=juegos.buscarProducto(cod);
			Cliente auxCliente;
			try {
				auxCliente=clientes.buscarCliente(rs.getString("rutCliente"));
			}
			catch(RUTException e) {
				auxCliente=new Cliente("00000000-0");
				e.printStackTrace();
			}
			Venta ptrVenta=new Venta(auxCliente,prod,rs.getInt("ganancia"),rs.getString("codigo"),fecha,rs.getBoolean("cancelado"));
			ventas.agregarNegocio(ptrVenta);
			
		}
		////conexion.cerrarConexion();
		return ventas;
	}
}

