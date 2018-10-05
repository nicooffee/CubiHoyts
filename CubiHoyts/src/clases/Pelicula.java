package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Conexion.Conexion;

/**
 * Clase película:
 *
 * Clase que extiende de la clase abstracta {@link ProductoVideoClub}.
 * 
 * Añade atributos para el estreno y el año de estreno de una
 * película.
 *
 * @author Constanza Cerda
 *
 */

public class Pelicula extends ProductoVideoClub {
	private boolean esEstreno; // Es o no una película recién estrenada.
	private int anoEstreno; // Año del estreno.

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor sin parámetros de la clase película.
	 *
	 * Se usa para asignarle una película a un arriendo/venta que tiene una película
	 * eliminada
	 */
	public Pelicula() {

		this.setGeneros(new ArrayList<String>());
		this.setDatos("*ELIMINADO*", 0, 0, "*ELIMINADO*", 0, 0);
		this.getGeneros().add("Accion");
		this.getGeneros().add("Romance");
		this.getGeneros().add("Horror");
		this.esEstreno = false;
		this.anoEstreno = 0;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor con parámetros de la clase película
	 *
	 * Se utiliza en todos los casos de creación de una nueva película.
	 *
	 * @param cantidadPelicula
	 *            Cantidad de la película (int).
	 * @param codigoPelicula
	 *            Código de la película (int).
	 * @param nombrePelicula
	 *            Nombre de la película (String).
	 * @param tipo
	 *            Tipos de la película (Array).
	 * @param descripcionPelicula
	 *            Descripción de la película (String).
	 * @param esEstreno
	 *            La película es estreno (boolean).
	 * @param anoEstreno
	 *            Año estreno de la película (int).
	 * @param precioArriendo
	 *            Precio arriendo de la película (int).
	 * @param precioVenta
	 *            Precio venta de la película (int).
	 */
	public Pelicula(final int cantidadPelicula, final int codigoPelicula, final String nombrePelicula,
			final ArrayList<String> tipo, final String descripcionPelicula, final boolean esEstreno,
			final int anoEstreno, final int precioArriendo, final int precioVenta) {
		this.setDatos(nombrePelicula, cantidadPelicula, codigoPelicula, descripcionPelicula, precioVenta,
				precioArriendo);
		this.setGeneros(tipo);
		this.esEstreno = esEstreno;
		this.anoEstreno = anoEstreno;
	}

	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see utilidades.Guardable#agregarDatos()
	 */
	@Override
	public void agregarDatos(){
		final Conexion conexion =Conexion.getInstance();
		final Connection con = conexion.getConexion();

		final String sql = "INSERT INTO peliculas(cantidad,codigo,nombre,genero,descripcion,estreno,anioEstreno,precioArriendo,precioVenta) VALUES(?,?,?,?,?,?,?,?,?)";

		try {
			final PreparedStatement rs = con.prepareStatement(sql);
			rs.setInt(1, this.getCantidad());
			rs.setInt(2, this.getCodigo());
			rs.setString(3, this.getNombre());
			rs.setString(4, this.listaGenerosToString());
			rs.setString(5, this.getDescripcion());
			rs.setBoolean(6, this.esEstreno);
			rs.setInt(7, this.getAnoEstreno());
			rs.setInt(8, this.getPrecioArriendo());
			rs.setInt(9, this.getPrecioVenta());
			rs.executeUpdate();
			//conexion.cerrarConexion();
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * datosEnComun:
	 *
	 * Método de la clase producto.
	 *
	 * Compara los datos en común con otro producto enviado por parámetro.
	 *
	 * @param prod
	 *            Producto a comparar.
	 * @return La cantidad de similitudes con el producto enviado por parámetro.
	 */
	public int datosEnComun(ProductoVideoClub prod) {
		int cont = 0;
		if (this.getCodigo() != prod.getCodigo())
			for (int i = 0; i < ((Pelicula) prod).getGeneros().size(); i++)
				for (int j = 0; j < this.getGeneros().size(); j++)
					if (((Pelicula) prod).getGeneros().get(i).equals(this.getGeneros().get(j)))
						cont++;
		if(this.esEstreno)
			cont+=2;
		return cont;
	}
	

	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see clases.ProductoVideoClub#eliminarDatos()
	 */
	@Override
	public void eliminarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "DELETE FROM peliculas WHERE codigo=" +this.getCodigo()+";";

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

	
	
	
	
	
	
	
	
	
	
	public int getAnoEstreno() {
		return this.anoEstreno;
	}

	public boolean isEsEstreno() {
		return this.esEstreno;
	}

	public void setAnoEstreno(final int anoEstreno) {
		this.anoEstreno = anoEstreno;
	}

	public void setEsEstreno(final boolean esEstreno) {
		this.esEstreno = esEstreno;
	}










	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#modificarDatos()
	 */
	@Override
	public void modificarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "UPDATE peliculas SET cantidad=?,codigo=?,nombre=?,genero=?,descripcion=?,estreno=?,anioEstreno=?,precioArriendo=?,precioVenta=? WHERE codigo='" + this.getCodigo() + "'";

		try {
			final PreparedStatement rs = conn.prepareStatement(sql);
			rs.setInt(1, this.getCantidad());
			rs.setInt(2, this.getCodigo());
			rs.setString(3, this.getNombre());
			rs.setString(4, this.listaGenerosToString());
			rs.setString(5, this.getDescripcion());
			rs.setBoolean(6, this.esEstreno);
			rs.setInt(7, this.getAnoEstreno());
			rs.setInt(8, this.getPrecioArriendo());
			rs.setInt(9, this.getPrecioVenta());
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
		return new Pelicula(this.getCantidad(),this.getCodigo(),this.getNombre(),this.getGeneros(),this.getDescripcion(),this.esEstreno,this.anoEstreno,this.getPrecioArriendo(),this.getPrecioVenta());
	}
}
