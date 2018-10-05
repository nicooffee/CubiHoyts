 package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import excepciones.RUTException;
import métodosGlobales.ModuloOnce;
import utilidades.ModificadorDeTabla;

/**
 * Clase Cliente:
 *
 * Clase que posee atributos correspondientes a un cliente de videclub. Cada
 * cliente posee un tipo que define el rango del cliente.
 *
 * @author Nicolás Honorato
 *
 */
public class Cliente implements ModificadorDeTabla {
	private String nombreCliente; // Nombre del cliente
	private String rut; // RUT del cliente
	private String direccion; // Dirección del cliente
	private int tipoCliente; // Tipo del cliente (0,1,2,3).

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor 2 con parámetros de la clase Cliente:
	 *
	 * Este constructor crea un objeto {@link Cliente} con datos "default". Se utiliza en
	 * negocios que poseen un cliente eliminado.
	 * 
	 * @param rut
	 *            RUT del cliente(String).
	 */
	public Cliente(final String rut) {
		this.nombreCliente = "*ELIMINADO*";
		this.rut = rut;
		this.direccion = "*ELIMINADO*";
		this.tipoCliente = 0;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor con parámetros de la clase Cliente:
	 *
	 * Este constructor es utilizado al momento de crear un objeto de {@link} en la
	 * ejecución del programa.
	 * 
	 * @param nombreCliente
	 *            Nombre del cliente (String).
	 * @param rut
	 *            RUT del cliente (String).
	 * @param tipoCliente
	 *            Tipo del cliente (int).
	 * @param direccion
	 *            Dirección del cliente (String).
	 */
	public Cliente(final String nombreCliente, final String rut, final int tipoCliente, final String direccion) throws RUTException{
		if(ModuloOnce.comprobar(rut)) {
			this.nombreCliente = nombreCliente;
			this.rut = rut;
			this.direccion = direccion;
			this.tipoCliente = tipoCliente;
		}
		else {
			throw new RUTException();
		}
	}

	
	
	
	
	
	
	
	
	
	/**
	 * clonarObjeto:
	 * 
	 * Método que crea un nuevo objeto a partir de los datos del creador.
	 * 
	 * @return
	 * El nuevo objeto creado.
	 */
	public Cliente clonarObjeto() {
		try {
			return new Cliente(nombreCliente,rut,tipoCliente,direccion);
		} catch (RUTException e) {
			return new Cliente("00000000-0");
		}
	}
	
	
	
	
	
	
	
	
	
	
	 
	 /**
	 * actualizarTipoCliente:
	 *
	 * Método para actualizar el tipo del cliente.
	 *
	 * El cliente subirá de rango solo cuando se cumplan los siguientes casos:
	 *
	 * Para pasar a tipo 1: El cliente debe ser tipo 0, tener 5 arriendos y 10
	 * ventas.
	 *
	 * Para pasar a tipo 2: El cliente debe ser tipo 1, tener 15 arriendos y 25
	 * ventas.
	 *
	 * Para pasar a tipo 3: El cliente debe ser tipo 2, tener 30 arriendos y 40
	 * ventas.
	 *
	 *
	 * Todos los clientes deben tener una penalización de 0 para subir de rango.
	 * 
	  * @param numArr
	  * 		  Numero de arriendos del cliente.
	  * @param numVen
	  * 		  Numero de ventas del cliente.
	  * @param pen
	  * 		  Numero de penalizaciones del cliente
	  * @return True si el cliente subió de rango con la ultima venta/arriendo
	 *         realizado/a.
	 *
	 */
	public boolean actualizarTipoCliente(final int numArr, final int numVen, final int pen) {
		if ((numArr >= 30) && (numVen >= 40) && (this.tipoCliente == 2) && (pen == 0)) {
			this.tipoCliente = 3;
			return true;
		} else if ((numArr >= 15) && (numVen >= 25) && (this.tipoCliente == 1) && (pen == 0)) {
			this.tipoCliente = 2;
			return true;
		} else if ((numArr >= 5) && (numVen >= 10) && (this.tipoCliente == 0) && (pen == 0)) {
			this.tipoCliente = 1;
			return true;
		} else
			return false;
	}

	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#agregarDatos()
	 */
	@Override
	public void agregarDatos()   {
		final Conexion conexion = Conexion.getInstance();
		final Connection con = conexion.getConexion();

		final String sql = "INSERT INTO clientes(nombre,rut,dir,tipo) VALUES(?,?,?,?)";

		try {
			final PreparedStatement rs = con.prepareStatement(sql);
			rs.setString(1, this.getNombreCliente());
			rs.setString(2, this.getRut());
			rs.setString(3, this.getDireccion());
			rs.setInt(4, this.getTipoCliente());
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
	@Override
	public void eliminarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "DELETE FROM clientes WHERE rut='" +this.getRut()+"';";

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
	
	
	
	
	
	
	
	
	
	
	public String getDireccion() {
		return this.direccion;
	}

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public String getRut() {
		return this.rut;
	}

	public int getTipoCliente() {
		return this.tipoCliente;
	}

	public void setDireccion(final String direccion) {
		this.direccion = direccion;
	}

	public void setNombreCliente(final String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public void setRut(final String rut) throws RUTException{
		if(ModuloOnce.comprobar(rut))
			this.rut = rut;
		else
			throw new RUTException();
	}

	public void setTipoCliente(final int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}










	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#modificarDatos()
	 */
	@Override
	public void modificarDatos() {
		final Conexion conexion = Conexion.getInstance();
		final Connection conn = conexion.getConexion();

		final String sql = "UPDATE clientes SET nombre=?,rut=?,dir=?,tipo=? WHERE rut='" + this.getRut() + "'";

		try {
			final PreparedStatement rs = conn.prepareStatement(sql);
			rs.setString(1, this.getNombreCliente());
			rs.setString(2, this.getRut());
			rs.setString(3, this.getDireccion());
			rs.setInt(4, this.getTipoCliente());
			if (rs.executeUpdate() == 1) {
			}
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
