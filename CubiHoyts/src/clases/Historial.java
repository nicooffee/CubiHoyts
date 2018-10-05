package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import factory.Creator;
import utilidades.ModificadorDeTabla;

/**
 * Clase Historial:
 *
 * Clase que almacena una lista de arriendos y una lista de ventas, además de
 * tener atributos para la cantidad de ventas, arriendos, arriendos seguidos y
 * penalizaciones.
 *
 * @author Nicolás Honorato
 *
 */
public class Historial implements ModificadorDeTabla{
	private String rutCliente; // Rut del cliente al cual pertenece el historia.
	private int penalizacion; // Penalizaciones del cliente(si se atrasó con un arriendo).
	private int arriendosSeguidos; // Numero de arriendos seguidos.
	private ListaNegocio listaArriendos = Creator.crearLista(Creator.crearListaArriendo); // Lista de arriendos del cliente.
	private ListaNegocio listaVentas = Creator.crearLista(Creator.crearListaVenta); // Lista de ventas del cliente.

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor sin parámetros:
	 *
	 * Este constructor dimensiona las listas de arriendo y venta del objeto y
	 * asigna valores "default" a cada atributo primitivo.
	 *
	 */
	public Historial() {
		this.rutCliente = "*ELIMINADO*";
		this.penalizacion = 0;
		this.arriendosSeguidos = 0;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor con parámetros:
	 *
	 * Constructor que recibe 6 parámetros y los asigna a los atributos del objeto.
	 * @param rutCliente
	 *   		  Rut del cliente(String).
	 * @param penalizacion
	 *            Cantida de penalizaciones (int).
	 * @param arriendosSeguidos
	 *            Número de arriendos seguidos (int).
	 */
	public Historial(final String rutCliente,
			final int penalizacion, final int arriendosSeguidos) {
		this.rutCliente = rutCliente;
		this.penalizacion = penalizacion;
		this.arriendosSeguidos = arriendosSeguidos;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * agregarArriendo:
	 *
	 * Método que recibe un objeto {@link Arriendo} y lo agrega a la listaArriendos.
	 *
	 * @param arr
	 *            Referencia al arriendo.
	 */
	public void agregarArriendo(final Arriendo arr) {
		if (this.listaArriendos == null)
			this.listaArriendos = Creator.crearLista(Creator.crearListaArriendo);
		this.listaArriendos.agregarNegocio(arr);
	}


	
	
	
	
	
	
	
	
	
	
	/**
	 * agregarVenta:
	 *
	 * Método que dimensiona un objeto {@link Venta} y le asigna el {@link Cliente} y el {@link ProductoVideoClub}
	 * que fueron enviados por parámetros, además de asignarle fecha, código y
	 * ganancia.
	 *
	 * El método retorna el código de la venta.
	 *
	 * @param cli
	 *            Referencia al cliente.
	 * @param prod
	 *            Referencia al Producto.
	 * @return El código de la venta.
	 */
	public Venta agregarVenta(final Cliente cli, final ProductoVideoClub prod)  {
		Venta ptrVenta=new Venta(cli,prod);
		this.listaVentas.agregarNegocio(ptrVenta);
		return ptrVenta;
	}


	
	
	
	
	
	
	
	
	
	
	/**
	 * buscarArriendo:
	 *
	 * Método que hace una llamada a buscarArriendo de la clase ListaArriendo.
	 *
	 * @param cod
	 *            Código del arriendo.
	 * @return Objeto Arriendo con código enviado por parámetro.
	 */
	public Negocio buscarArriendo(final String cod) {
		return this.listaArriendos.buscarNegocio(cod);
	}


	
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadArriendos:
	 *
	 * Método que retorna la cantidad de arriendos que existen dentro de
	 * listaArriendos.
	 *
	 * @return La cantidad de arriendos en listaArriendos.
	 */
	public int cantidadArriendos() {
		if (this.listaArriendos != null)
			return this.listaArriendos.cantidadNegocios();
		return 0;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadArriendosCompletados:
	 *
	 * Método que cuenta los arriendos completados dentro del ArrayList.
	 *
	 * @return La cantidad de arriendos completados.
	 */
	public int cantidadArriendosCompletados() {
		if (this.listaArriendos != null)
			return ((ListaArriendo) this.listaArriendos).cantidadArriendosCompletados();
		return 0;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadArriendosPorMes:
	 *
	 * Método que llama al método cantidadArriendosPorMes de la clase
	 * ListaArriendos.
	 *
	 * @param month
	 *            Mes a comparar (int).
	 * @return La cantidad de arriendos de un mes determinado.
	 */
	public int cantidadArriendosPorMes(final int month) {
		return this.listaArriendos.cantidadNegociosPorMes(month);
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadVentaArriendoPorPelicula:
	 *
	 * Método que realiza una llamada a cantidadArriendosPorPelicula y
	 * cantidadVentasPorPelicula de la clase ListaArriendo y ListaVenta
	 * respectivamente.
	 *
	 * @param cod
	 *            Código de la película
	 * @return La suma del retorno de ambos métodos.
	 */
	public int cantidadVentaArriendoPorPelicula(final int cod) {
		return this.listaArriendos.cantidadNegociosPorProducto(cod) + this.listaVentas.cantidadNegociosPorProducto(cod);
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadVentasPorMes:
	 *
	 * Método que llama al método cantidadVentasPorMes de la clase ListaVentas.
	 *
	 * @param month
	 *            Mes a comparar (int).
	 * @return La cantidad de ventas de un mes determinado.
	 */
	public int cantidadVentasPorMes(final int month) {
		return this.listaVentas.cantidadNegociosPorMes(month);
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * clonarListaArriendo:
	 *
	 * Método que llama a clonarListaArriendos de la clase ListaArriendos.
	 *
	 * @return Lo que retorna el método clonarListaArriendos de la clase
	 *         ListaArriendos.
	 */
	public ArrayList<Negocio> clonarListaArriendo() {
		if (this.listaArriendos.cantidadNegocios() != 0)
			return this.listaArriendos.clonarListaNegocios();
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * clonarListaVenta:
	 *
	 * Método que llama a clonarListaVentas de la clase ListaVentas.
	 * 
	 * @return Lo que retorna el método clonarListaVentas de la clase ListaVentas.
	 */
	public ArrayList<Negocio> clonarListaVenta() {
		return this.listaVentas.clonarListaNegocios();
	}
	
	
	
	
	
	

	
	
	
	
	/**
	 * clonarObjeto:
	 * 
	 * Método que crea un nuevo objeto a partir de los datos del creador.
	 * 
	 * @return
	 * El nuevo objeto creado.
	 */
	public Historial clonarObjeto() {
		return new Historial(this.getRutCliente(),this.penalizacion,this.arriendosSeguidos);
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * devolverArriendo:
	 *
	 * Método que cambia los datos del historial al momento de devolver un arriendo.
	 *
	 * @param cod
	 *            Código del arriendo devuelto.
	 */
	public void devolverArriendo(final String cod) {
		final Arriendo arr =(Arriendo) ((ListaArriendo) listaArriendos).devolverArriendo(cod);
		if (arr.getFechaEntrega().after(Calendar.getInstance())) {
			this.setArriendosSeguidos(this.getArriendosSeguidos() + 1);
			if ((this.getArriendosSeguidos() % 5) == 0)
				if (this.getPenalizacion() > 0)
					this.setPenalizacion(this.getPenalizacion() - 1);
		} else {
			this.setPenalizacion(this.getPenalizacion() + 1);
			this.setArriendosSeguidos(0);
		}
	}


	
	
	
	
	
	
	
	
	
	
	public int getArriendosSeguidos() {
		return this.arriendosSeguidos;
	}

	public int getNumArriendos() {
		return this.listaArriendos.cantidadNegocios();
	}

	public int getNumVentas() {
		return this.listaVentas.cantidadNegocios();
	}

	public int getPenalizacion() {
		return this.penalizacion;
	}

	public String getRutCliente() {
		return this.rutCliente;
	}

	public void setArriendosSeguidos(final int arriendosSeguidos) {
		this.arriendosSeguidos = arriendosSeguidos;
	}

	public void setListaArriendos(final ListaNegocio listaArriendos) {
		this.listaArriendos = listaArriendos;
	}

	public void setListaVentas(final ListaNegocio listaVentas) {
		this.listaVentas = listaVentas;
	}

	public void setPenalizacion(final int penalizacion) {
		this.penalizacion = penalizacion;
	}

	public void setRutCliente(final String rutCliente) {
		this.rutCliente = rutCliente;
	}










	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#agregarDatos()
	 */
	@Override
	public void agregarDatos( ){
		final Conexion conexion =Conexion.getInstance();
		final Connection con = conexion.getConexion();

		final String sql = "INSERT INTO historiales(rut,penalizacion,arrSeguidos) VALUES(?,?,?)";

		try {
			final PreparedStatement rs = con.prepareStatement(sql);
			rs.setString(1, this.getRutCliente());
			rs.setInt(2, this.getPenalizacion());
			rs.setInt(3, this.getArriendosSeguidos());
			rs.executeUpdate();
			//conexion.cerrarConexion();
		} catch (final SQLException e) {
			this.modificarDatos();
		}
	}
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#eliminarDatos()
	 */
	public void eliminarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "DELETE FROM historiales WHERE rut='" +this.getRutCliente()+"';";

		try {
			final PreparedStatement ps = conn.prepareStatement(sql);
			if (ps.executeUpdate() == 1) {
			}
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * eliminarNegocio:
	 * 
	 * Método que hace una llamada a {@link ListaNegocio#eliminarNegocio(String)}
	 * de listaArrienods y ListaVentas.
	 * @param cod
	 * 				Código del negocio.
	 */
	public void eliminarNegocio(final String cod) {
		listaArriendos.eliminarNegocio(cod);
		listaVentas.eliminarNegocio(cod);
	}










	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#revertir(int)
	 */
	@Override
	public void revertir(int estado) {
		if(estado==1)
			this.eliminarDatos();
		else if(estado==0)
			this.agregarDatos();
		else if(estado==2)
			this.modificarDatos();
		
	}
	
	
	
	
	
	
	
	
	
	/**
	 * reemplazarNegocio:
	 * 
	 * Método que hace una llamada a {@link ListaNegocio#reemplazar(Negocio)}
	 * de listaArriendos y ListaVentas.
	 * 
	 * @param neg
	 * 				Negocio a reemplazar.
	 */
	public void reemplazarNegocio(Negocio neg) {
		listaArriendos.reemplazar(neg);
		listaVentas.reemplazar(neg);
	}









	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#modificarDatos()
	 */
	@Override
	public void modificarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "UPDATE historiales SET rut=?,penalizacion=?,arrSeguidos=? WHERE rut='" + this.getRutCliente() + "'";

		try {
			final PreparedStatement rs = conn.prepareStatement(sql);
			rs.setString(1, this.getRutCliente());
			rs.setInt(2, this.getPenalizacion());
			rs.setInt(3, this.getArriendosSeguidos());
			if (rs.executeUpdate() == 1) {
			}
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
