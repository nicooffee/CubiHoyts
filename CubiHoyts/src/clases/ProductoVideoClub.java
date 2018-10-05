package clases;

import java.util.ArrayList;

import utilidades.ModificadorDeTabla;

/**
 * Clase ProductoVideoClub.
 *
 * Clase abstracta para almacenar datos de un producto correspondiente a un
 * videoclub.
 *
 * Posee nombre, cantidad, código, generos descripción y precios para la venta y
 * el arriendo.
 *
 * Posee dos extensiones, las cuales son la clase Pelicula y la clase Juego.
 *
 * @author Benjamín Herrera
 *
 */
public abstract class ProductoVideoClub implements ModificadorDeTabla {
	private String nombre; // Nombre del producto.
	private int cantidad; // Cantidad del producto (Stock).
	private int codigo; // Código del producto.
	private ArrayList<String> generos; // Géneros del producto.
	private String descripcion; // Descripción del producto.
	private int precioVenta; // Precio arriendo del producto.
	private int precioArriendo; // Precio venta del producto.

	
	
	
	
	
	
	
	
	
	
	/**
	 * buscarTipos:
	 *
	 * Método que recibe un string Tipo. Busca el tipo
	 * dentro de la lista de tipos del producto.
	 *
	 * @param tipo
	 *            Tipo del producto.
	 * @return
	 *
	 * 		true si es que encuentra el tipo dentro de la lista de tipos del
	 *         producto.
	 *
	 */
	public boolean buscarTipo(final String tipo) {
		for (int i = 0; i < this.getGeneros().size(); i++)
			if (this.getGeneros().get(i).equals(tipo))
				return true;
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * datosEnComun:
	 *
	 * Método abstracto de la clase producto.
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
		return cont;
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see utilidades.ModificadorDeTabla#eliminarDatos()
	 */
	public abstract void eliminarDatos();
	
	
	
	
	
	
	
	
	
	
	/**
	 * clonarObjeto:
	 * 
	 * Método que crea un nuevo objeto a partir de los datos del creador.
	 * 
	 * @return
	 * El nuevo objeto creado.
	 */
	public abstract ProductoVideoClub clonarObjeto();
	
	
	
	
	
	
	
	
	
	public int getCantidad() {
		return this.cantidad;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public ArrayList<String> getGeneros() {
		return this.generos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getPrecioArriendo() {
		return this.precioArriendo;
	}

	public int getPrecioVenta() {
		return this.precioVenta;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * listaGenerosToString:
	 *
	 * Método que transforma el ArrayList de generos a un único String.
	 *
	 * @return Una concatenación de todos los tipos del ArrayList.
	 */
	public String listaGenerosToString() {
		if (this.generos != null) {
			String sGeneros = new String();
			for (int i = 0; i < this.generos.size(); i++)
				if (this.generos.get(i) != null)
					if (i < (this.generos.size() - 1))
						sGeneros += (this.generos.get(i) + ",");
					else
						sGeneros += (this.generos.get(i));
			return sGeneros;
		}
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	public void setCantidad(final int cantidad) {
		this.cantidad = cantidad;
	}

	public void setCodigo(final int codigo) {
		this.codigo = codigo;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * setDatos:
	 *
	 * Método que reemplaza todos los atributos de la clase con los que son enviados
	 * por parámetro.
	 *
	 * @param nombre
	 *   		Nombre del producto(String).
	 * @param cantidad
	 *   		Cantidad del producto(int).
	 * @param codigo
	 *   		Código del producto(int).
	 * @param descripcion
	 *   		Descripción del producto(String).
	 * @param precioVenta
	 *   		Precio de venta del producto(int).
	 * @param precioArriendo
	 *   		Precio de arriendo del producto(int).
	 */
	public void setDatos(final String nombre, final int cantidad, final int codigo, final String descripcion,
			final int precioVenta, final int precioArriendo) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precioArriendo = precioArriendo;
		this.precioVenta = precioVenta;
	}

	
	
	
	
	
	
	
	
	
	
	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public void setGeneros(final ArrayList<String> generos) {
		this.generos = generos;
	}
	
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public void setPrecioArriendo(final int precioArriendo) {
		this.precioArriendo = precioArriendo;
	}

	public void setPrecioVenta(final int precioVenta) {
		this.precioVenta = precioVenta;
	}
}
