package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import Conexion.Conexion;

/**
 * Clase Arriendo
 *
 * Clase que extiende de la clase abstracta {@link Negocio}
 *
 * Posee atributos exclusivos de un arriendo
 * 
 * @author Benjamín Herrera
 *
 */
public class Arriendo extends Negocio  {
	private int diasArriendo; // Numeros de días de arriendo.
	private Calendar fechaInicio; // Fecha de inicio del arriendo.
	private Calendar fechaEntrega; // Fecha plazo del arriendo.
	private boolean arriendoCompletado; // Es o no un arriendo ya finalizado.

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor 2 con parámetros de la clase Arriendo
	 *
	 * Es utilizado para crear objetos de la clase {@link Arriendo} al momento de agregar un
	 * arriendo en la ejecución del programa.
	 * 
	 * @param cli
	 *            Referencia al cliente.
	 * @param prod
	 *            Referencia al producto.
	 * @param dias
	 *            Días del arriendo.
	 */
	public Arriendo(final Cliente cli, final ProductoVideoClub prod, final int dias) {
		this.setNegocio("nocod", cli, prod, 0);
		final int ganancia = (prod.getPrecioArriendo() + ((dias - 5) * 500))
				- (prod.getPrecioArriendo() * (cli.getTipoCliente() / 10));
		this.setGanancia(ganancia);
		this.diasArriendo = dias;
		this.fechaInicio = Calendar.getInstance();
		final Date date = new Date();
		this.fechaInicio.setTime(date);
		this.fechaEntrega = Calendar.getInstance();
		this.fechaEntrega.setTime(date);
		this.fechaEntrega.add(Calendar.DATE, dias);
		this.setCodigo(Integer.toString(prod.getCodigo()) + this.fechaInicio.getTimeInMillis());
		this.arriendoCompletado = false;
		
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor con parámetros de la clase Arriendo
	 *
	 * Es utilizado para crear objetos de {@link Arriendo} al momento de cargar los datos
	 * desde los archivos.
	 *
	 * @param cliente
	 *            Referencia al cliente.
	 * @param producto
	 *            Referencia al producto.
	 * @param ganancia
	 *            Ganancia del arriendo.
	 * @param diasArriendo
	 *            Días del arriendo.
	 * @param codArriendo
	 *            Código del arriendo.
	 * @param fechaInicio
	 *            Fecha inicio del arriendo.
	 * @param fechaEntrega
	 *            Fecha plazo del arriendo.
	 * @param arriendoCompletado
	 *            Es un arriendo completado.
	 */
	public Arriendo(final Cliente cliente, final ProductoVideoClub producto, final int ganancia, final int diasArriendo,
			final String codArriendo, final Calendar fechaInicio, final Calendar fechaEntrega,
			final boolean arriendoCompletado) {
		this.setNegocio(codArriendo, cliente, producto, ganancia);
		this.diasArriendo = diasArriendo;
		this.fechaInicio = fechaInicio;
		this.fechaEntrega = fechaEntrega;
		this.arriendoCompletado = arriendoCompletado;

	}

	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see clases.Negocio#agregarDatosCSV(java.lang.String)
	 */
	@Override
	public void agregarDatos() {
		final Conexion conexion =Conexion.getInstance();
		final Connection con = conexion.getConexion();

		final String sql = "INSERT INTO arriendos(rutCliente,codProducto,ganancia,dias,codigo,fechaInicio,fechaEntrega,completado) VALUES(?,?,?,?,?,?,?,?)";

		try {
			final PreparedStatement rs = con.prepareStatement(sql);
			rs.setString(1,this.getRutCliente());
			rs.setInt(2, this.getCodigoProducto());
			rs.setInt(3, this.getGanancia());
			rs.setInt(4, this.getDiasArriendo());
			rs.setString(5, this.getCodigo());
			rs.setString(6, Long.toString(this.getFechaInicio().getTimeInMillis()));
			rs.setString(7, Long.toString(this.getFechaEntrega().getTimeInMillis()));
			rs.setBoolean(8, this.isArriendoCompletado());
			rs.executeUpdate();
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

		final String sql = "DELETE FROM arriendos WHERE codigo='" +this.getCodigo()+"';";

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
	 * 
	 * @see clases.Negocio#cancelar()
	 */
	@Override
	public boolean cancelar() {
		if (!this.isArriendoCompletado())
			if ((Calendar.getInstance().getTimeInMillis() - this.getFechaInicio().getTimeInMillis()) <= 600000) {
				this.setArriendoCompletado(true);
				this.setGanancia(0);
				return true;
			} else if ((Calendar.getInstance().getTimeInMillis()
					- this.getFechaInicio().getTimeInMillis()) <= 1800000) {
				this.setArriendoCompletado(true);
				this.setGanancia((this.getGanancia() * 20) / 100);
				return true;
			} else if ((Calendar.getInstance().getTimeInMillis()
					- this.getFechaInicio().getTimeInMillis()) <= 3600000) {
				this.setArriendoCompletado(true);
				this.setGanancia(this.getGanancia() / 2);
				return true;
			}
		return false;
	}

	
	
	
	
	
	
	
	
	
	/**
	 * clonarObjeto:
	 * 
	 * Método que crea un nuevo objeto a partir de los datos del creador.
	 * 
	 * @return
	 * El nuevo objeto creado.
	 */
	public Arriendo clonarObjeto() {
		return new Arriendo(this.getCliente(),this.getProducto(),this.getGanancia(),this.diasArriendo,this.getCodigo(),this.fechaInicio,this.fechaEntrega,this.arriendoCompletado);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * devolver:
	 *
	 * Método que cambia el estado de completado del arriendo a true.
	 */
	public void devolver() {
		this.setArriendoCompletado(true);
	}

	
	
	
	
	
	
	
	
	
	
	public int getDiasArriendo() {
		return this.diasArriendo;
	}

	public Calendar getFechaEntrega() {
		return this.fechaEntrega;
	}

	public Calendar getFechaInicio() {
		return this.fechaInicio;
	}

	public boolean isArriendoCompletado() {
		return this.arriendoCompletado;
	}

	public void setArriendoCompletado(final boolean arriendoCompletado) {
		this.arriendoCompletado = arriendoCompletado;
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
	 * @see utilidades.ModificadorDeTabla#modificarDatos()
	 */
	@Override
	public void modificarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "UPDATE arriendos SET rutCliente=?,codProducto=?,ganancia=?,dias=?,codigo=?,fechaInicio=?,fechaEntrega=?,completado=? WHERE codigo='" + this.getCodigo() + "'";

		try {
			final PreparedStatement rs = conn.prepareStatement(sql);
			rs.setString(1,this.getRutCliente());
			rs.setInt(2, this.getCodigoProducto());
			rs.setInt(3, this.getGanancia());
			rs.setInt(4, this.getDiasArriendo());
			rs.setString(5, this.getCodigo());
			rs.setString(6, Long.toString(this.getFechaInicio().getTimeInMillis()));
			rs.setString(7, Long.toString(this.getFechaEntrega().getTimeInMillis()));
			rs.setBoolean(8, this.isArriendoCompletado());
			if (rs.executeUpdate() == 1) {
			}
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
