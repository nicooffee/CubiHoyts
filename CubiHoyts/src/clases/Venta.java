package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;

import Conexion.Conexion;

/**
 * Clase Venta
 *
 * Esta clase es una extensión de la clase {@link Negocio}, la cual además posee una
 * fecha, que corresponde a la fecha en la cual se realizó la venta.
 *
 * @author Benjamín Herrera
 *
 */
public class Venta extends Negocio {
	private Calendar fechaVenta; // Fecha en la cual se realizó la venta.
	private boolean ventaCancelada;

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor con parámetros de la clase {@link Venta}
	 *
	 * Es utilizado en todos los casos de creación de ventas.
	 *
	 * @param cliente
	 *            Referencia al cliente.
	 * @param producto
	 *            Referencia a la película.
	 * @param ganancia
	 *            Ganancia de la venta (int).
	 * @param codigoVenta
	 *            Código de la venta (String).
	 * @param fechaVenta
	 *            Fecha de la venta (Calendar).
	 * @param ventaCancelada
	 * 			  Estado de venta cancelada (boolean).
	 */
	public Venta(final Cliente cliente, final ProductoVideoClub producto, final int ganancia, final String codigoVenta,
			final Calendar fechaVenta, final boolean ventaCancelada) {
		this.setNegocio(codigoVenta, cliente, producto, ganancia);
		this.fechaVenta = fechaVenta;
		this.ventaCancelada = ventaCancelada;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor 2 con parámetros:
	 * 
	 * Constructor que recibe un cliente y un producto, los cuales corresponden a la venta.
	 * Se encarga de setear los demás atributos con solo los dos parámetros recibidos.
	 * @param cli
	 * 				Cliente de la venta.
	 * @param prod
	 * 				Producto de la venta.
	 */
	public Venta(final Cliente cli, final ProductoVideoClub prod) {
		final Calendar fecha = Calendar.getInstance();
		int ganancia = prod.getPrecioVenta() - (prod.getPrecioVenta() * (cli.getTipoCliente() / 10));
		if ((prod instanceof Pelicula) && ((Pelicula) prod).isEsEstreno())
			ganancia += (prod.getPrecioVenta() / 2);
		this.setNegocio(Integer.toString(prod.getCodigo()) + Long.toString(fecha.getTimeInMillis()), cli, prod, ganancia);
		this.fechaVenta=fecha;
		this.ventaCancelada=false;
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see clases.Negocio#agregarDatosCSV(java.lang.String)
	 */
	@Override
	public void agregarDatos()   {
		final Conexion conexion =Conexion.getInstance();
		final Connection con = conexion.getConexion();

		final String sql = "INSERT INTO ventas(rutCliente,codProducto,ganancia,codigo,fecha,cancelado) VALUES(?,?,?,?,?,?)";

		try {
			final PreparedStatement rs = con.prepareStatement(sql);
			rs.setString(1,this.getRutCliente());
			rs.setInt(2, this.getCodigoProducto());
			rs.setInt(3, this.getGanancia());
			rs.setBoolean(6,this.isVentaCancelada());
			rs.setString(4, this.getCodigo());
			rs.setString(5, Long.toString(this.getFechaVenta().getTimeInMillis()));
			rs.executeUpdate();
			//conexion.cerrarConexion();
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	
	
	
	
	
	
	
	

	/*
	 * (non-Javadoc)
	 * @see clases.Negocio#eliminarDatos()
	 */
	@Override
	public void eliminarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "DELETE FROM ventas WHERE codigo='" +this.getCodigo()+"';";

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
	 * @see clases.Negocio#cancelar()
	 */
	@Override
	public boolean cancelar() {
		if (!this.ventaCancelada)
			if ((Calendar.getInstance().getTimeInMillis() - this.getFechaVenta().getTimeInMillis()) <= 600000) {
				this.setGanancia(0);
				this.setCantidadProducto(this.getCantidadProducto() + 1);
				this.ventaCancelada = true;
				return true;
			} else if ((Calendar.getInstance().getTimeInMillis() - this.getFechaVenta().getTimeInMillis()) <= 1800000) {
				this.setGanancia((this.getGanancia() * 20) / 100);
				this.setCantidadProducto(this.getCantidadProducto() + 1);
				this.ventaCancelada = true;
				return true;
			} else if ((Calendar.getInstance().getTimeInMillis() - this.getFechaVenta().getTimeInMillis()) <= 3600000) {
				this.setGanancia(this.getGanancia() / 2);
				this.setCantidadProducto(this.getCantidadProducto() + 1);
				this.ventaCancelada = true;
				return true;
			}
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	public Calendar getFechaVenta() {
		return this.fechaVenta;
	}

	public boolean isVentaCancelada() {
		return this.ventaCancelada;
	}

	public void setFechaVenta(final Calendar fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public void setVentaCancelada(final boolean ventaCancelada) {
		this.ventaCancelada = ventaCancelada;
	}










	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#modificarDatos()
	 */
	@Override
	public void modificarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "UPDATE ventas SET rutCliente=?,codProducto=?,codigo=?,fecha=?,cancelado=? WHERE codigo='" + this.getCodigo() + "'";

		try {
			final PreparedStatement rs = conn.prepareStatement(sql);
			rs.setString(1,this.getRutCliente());
			rs.setInt(2, this.getCodigoProducto());
			rs.setInt(3, this.getGanancia());
			rs.setBoolean(6,this.isVentaCancelada());
			rs.setString(4, this.getCodigo());
			rs.setString(5, Long.toString(this.getFechaVenta().getTimeInMillis()));
			if (rs.executeUpdate() == 1) {
			}
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
