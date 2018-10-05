package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Conexion.Conexion;

/**
 * Clase Juego:
 *
 * Clase para almacenar datos de un juego.
 *
 * Extiende de la clase {@link ProductoVideoClub} y le añade 3 nuevos atributos los cuales
 * corresponden a una lista de consolas disponibles, una clasificación ESRB y un
 * máximo de jugadores.
 *
 * @author Constanza Cerda.
 *
 */
public class Juego extends ProductoVideoClub{
	private ArrayList<String> consolas; // Lista de consolas en donde el juego está disponible.
	private String clasificacion; // Clasificación ESRB del juego (vease: http://www.esrb.org/about/).
	private int jugadoresMaximos; // Máximo de jugadores del juego.

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor sin parámetros de la clase Juego.
	 *
	 * Se usa para asignarle un juego a un arriendo/venta que tiene un juego
	 * eliminado.
	 */
	public Juego() {
		this.setDatos("*ELIMINADO*", 0, 0, "*ELIMINADO*", 0, 0);
		this.setGeneros(new ArrayList<String>());
		this.consolas = new ArrayList<String>();
		this.jugadoresMaximos = 1;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor con parámetros de la clase Juego:
	 *
	 * Constructor que reemplaza todos los atributos de la clase Juego (tanto los
	 * heredados como los implementados).
	 *
	 * @param nombre
	 *            Nombre del producto (String).
	 * @param cantidad
	 *            Cantidad del producto (int).
	 * @param codigo
	 *            Código (int),
	 * @param descripcion
	 *            Descripción del producto (String).
	 * @param generos
	 *            Géneros del producto (Array).
	 * @param consolas
	 *            Consolas disponibles del juego (Array).
	 * @param precioArriendo
	 *            Precio arriendo del producto (int).
	 * @param precioVenta
	 *            Precio venta del producto (int).
	 * @param clasificacion
	 *            Clasificación del juego (String).
	 * @param jugadoresMaximos
	 *            Jugadores máximos del juego (int).
	 */
	public Juego(final String nombre, final int cantidad, final int codigo, final String descripcion,
			final ArrayList<String> generos, final ArrayList<String> consolas, final int precioArriendo,
			final int precioVenta, final String clasificacion, final int jugadoresMaximos) {
		this.setDatos(nombre, cantidad, codigo, descripcion, precioVenta, precioArriendo);
		this.setGeneros(generos);
		this.consolas = consolas;
		this.clasificacion = clasificacion;
		this.jugadoresMaximos = jugadoresMaximos;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * buscarConsola:
	 *
	 * Método que recibe un string Consola. Busca la consola
	 * dentro de la lista de consolas del juego.
	 *
	 * @param consola
	 *            Consola del juego
	 * @return
	 *
	 * 		true si es que encuentra la consola dentro de la lista de consolas del
	 *         juego.
	 *
	 */
	public boolean buscarConsola(final String consola) {
		for (int i = 0; i < this.getConsolas().size(); i++)
			if (this.getConsolas().get(i).equals(consola))
				return true;
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see utilidades.Guardable#agregarDatos()
	 */
	@Override
	public void agregarDatos(){
		final Conexion conexion = Conexion.getInstance();
		final Connection con = conexion.getConexion();

		final String sql = "INSERT INTO juegos(nombre,cantidad,codigo,descripcion,genero,consolas,precioArriendo,precioVenta,clasificacion,maxJugadores) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {
			final PreparedStatement rs = con.prepareStatement(sql);
			rs.setInt(2, this.getCantidad());
			rs.setInt(3, this.getCodigo());
			rs.setString(1, this.getNombre());
			rs.setString(5, this.listaGenerosToString());
			rs.setString(4, this.getDescripcion());
			rs.setString(6, this.listaConsolasToString());
			rs.setInt(10, this.jugadoresMaximos);
			rs.setString(9, this.getClasificacion());
			rs.setInt(7, this.getPrecioArriendo());
			rs.setInt(8, this.getPrecioVenta());
			rs.executeUpdate();
			//conexion.cerrarConexion();
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see clases.ProductoVideoClub#eliminarDatos()
	 */
	@Override
	public void eliminarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "DELETE FROM juegos WHERE codigo=" +this.getCodigo()+";";

		try {
			final PreparedStatement ps = conn.prepareStatement(sql);
			if (ps.executeUpdate() == 1) {
				//conexion.cerrarConexion();
			}
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
		}
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
	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see clases.Producto#datosEnComun(clases.Producto)
	 */
	@Override
	public int datosEnComun(final ProductoVideoClub prod) {
		float cont = 0;
		if (this.getCodigo() != prod.getCodigo()) {
			for (int i = 0; i < ((Juego) prod).getGeneros().size(); i++)
				for (int j = 0; j < this.getGeneros().size(); j++)
					if (((Juego) prod).getGeneros().get(i).equals(this.getGeneros().get(j)))
						cont++;
			for (int i = 0; i < ((Juego) prod).getConsolas().size(); i++)
				for (int j = 0; j < this.consolas.size(); j++)
					if (((Juego) prod).getConsolas().get(i).equals(this.consolas.get(j)))
						cont+=0.5;
		}
		return (int) cont;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * listaConsolasToString:
	 *
	 * Método que transforma el ArrayList de consolas a un único String.
	 *
	 * @return Una concatenación de todos las consolas del ArrayList.
	 */
	public String listaConsolasToString() {
		if (this.consolas != null) {
			String sConsolas = new String();
			for (int i = 0; i < this.consolas.size(); i++)
				if (this.consolas.get(i) != null)
					if (i < (this.consolas.size() - 1))
						sConsolas += (this.consolas.get(i) + ",");
					else
						sConsolas += (this.consolas.get(i));
			return sConsolas;
		}
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	public String getClasificacion() {
		return this.clasificacion;
	}

	public ArrayList<String> getConsolas() {
		return this.consolas;
	}

	public int getJugadoresMaximos() {
		return this.jugadoresMaximos;
	}
	
	public void setClasificacion(final String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public void setConsolas(final ArrayList<String> consolas) {
		this.consolas = consolas;
	}

	public void setJugadoresMaximos(final int jugadoresMaximos) {
		this.jugadoresMaximos = jugadoresMaximos;
	}










	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#modificarDatos()
	 */
	@Override
	public void modificarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "UPDATE juegos SET nombre=?,cantidad=?,codigo=?,descripcion=?,genero=?,consolas=?,precioArriendo=?,precioVenta=?,clasificacion=?,maxJugadores=? WHERE codigo='" + this.getCodigo() + "'";

		try {
			final PreparedStatement rs = conn.prepareStatement(sql);
			rs.setInt(2, this.getCantidad());
			rs.setInt(3, this.getCodigo());
			rs.setString(1, this.getNombre());
			rs.setString(5, this.listaGenerosToString());
			rs.setString(4, this.getDescripcion());
			rs.setString(6, this.listaConsolasToString());
			rs.setInt(10, this.jugadoresMaximos);
			rs.setString(9, this.getClasificacion());
			rs.setInt(7, this.getPrecioArriendo());
			rs.setInt(8, this.getPrecioVenta());
			if (rs.executeUpdate() == 1) {
			}
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}









	/*
	 * (non-Javadoc)
	 * @see clases.ProductoVideoClub#clonarObjeto()
	 */
	@Override
	public ProductoVideoClub clonarObjeto() {
		return new Juego(this.getNombre(),this.getCantidad(),this.getCodigo(),this.getDescripcion(),this.getGeneros(),this.getConsolas(),this.getPrecioArriendo(),this.getPrecioVenta(),this.getClasificacion(),this.jugadoresMaximos);
	}
}
