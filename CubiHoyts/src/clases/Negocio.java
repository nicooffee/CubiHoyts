package clases;

import utilidades.ModificadorDeTabla;

/**
 * Clase Negocio
 *
 * Clase abstracta que posee los datos del cliente y película asociados.
 *
 * Además posee la ganancia que provee y los datos del producto vendido o
 * arrendado.
 *
 * @author Benjamín Herrera
 *
 */
public abstract class Negocio implements ModificadorDeTabla {
	private String codigo; // Código del negocio
	private Cliente datosCliente; // Referencia al cliente que realizó el negocio
	private ProductoVideoClub datosProducto; // Referencia a la película arrendada/vendida.
	private int ganancia; // Ganancia que generó el negocio.

	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#agregarDatos()
	 */
	@Override
	public abstract void agregarDatos();
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#eliminarDatos()
	 */
	public abstract void eliminarDatos();
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * cancelar:
	 *
	 * Método que cancela un tipo de Negocio.
	 * 
	 * @return True si el negocio es cancelado exitósamente.
	 */
	public abstract boolean cancelar();

	
	
	
	
	
	
	
	
	
	
	public int getCantidadProducto() {
		return this.datosProducto.getCantidad();
	}

	public String getCodigo() {
		return this.codigo;
	}

	public int getCodigoProducto() {
		return this.datosProducto.getCodigo();
	}

	public int getGanancia() {
		return this.ganancia;
	}

	public String getNombreCliente() {
		return this.datosCliente.getNombreCliente();
	}
	protected Cliente getCliente() {
		return this.datosCliente;
	}
	protected ProductoVideoClub getProducto() {
		return this.datosProducto;
	}

	public String getNombreProducto() {
		return this.datosProducto.getNombre();
	}

	public String getRutCliente() {
		return this.datosCliente.getRut();
	}

	public String getTipoProducto() {
		return this.datosProducto.getClass().getSimpleName();
	}

	public void setCantidadProducto(final int cant) {
		this.datosProducto.setCantidad(cant);
	}

	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	public void setGanancia(final int ganancia) {
		this.ganancia = ganancia;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * setNegocio:
	 *
	 * Método utilizado en todos los casos de creación de tipos de negocios, recibe
	 * como parámetros 4 datos que se le asignan a los atributos del objeto.
	 *
	 * @param codigo
	 *            Código del negocio (String).
	 * @param datosCliente
	 *            Referencia al cliente.
	 * @param datosProducto
	 *            Referencia a la película.
	 * @param ganancia
	 *            Ganancia del negocio (int).
	 */
	public void setNegocio(final String codigo, final Cliente datosCliente, final ProductoVideoClub datosProducto,
			final int ganancia) {
		this.codigo = codigo;
		this.datosCliente = datosCliente;
		this.datosProducto = datosProducto;
		this.ganancia = ganancia;

	}
}
