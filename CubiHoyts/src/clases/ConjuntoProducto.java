package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

import utilidades.Exportable;

/**
 * Clase ConjuntoProducto.
 *
 *
 * Clase abstracta para almacenar objetos {@link ProductoVideoClub}.
 *
 * Posee un único atributo el cual corresponde a un HashSet de objetos
 * ProductoVideoClub.
 * 
 * Implementa la Interfaz {@link Exportable} la cual se encarga de exportar
 * los datos de productos a un archivo pdf.
 *
 * Hereda a dos clases las cuales corresponden a {@link ConjuntoPelicula} y
 * {@link ConjuntoJuego}.
 *
 * @author Benjamín Herrera
 *
 */
public abstract class ConjuntoProducto implements Exportable {
	private final HashSet<ProductoVideoClub> productos; // HashSet de objetos {@link ProductoVideoClub}.

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor sin parámetros:
	 *
	 * Constructor que dimensiona el Set de la clase como un HashSet que almacena
	 * objetos {@link Pelicula}.
	 *
	 */
	public ConjuntoProducto() {
		this.productos = new HashSet<ProductoVideoClub>();
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor con parámetros:
	 * 
	 * Constructor que copia la referencia del parámetro lista en el atributo
	 * del objeto.
	 * @param lista
	 * HashSet de productos.
	 */
	public ConjuntoProducto(HashSet<ProductoVideoClub> lista) {
		productos=lista;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * agregar:
	 *
	 * Método que añade un nuevo objeto {@link ProductoVideoClub} al hashSet de la clase.
	 *
	 * @param prod
	 *            ProductoVideoClub a agregar.
	 * @return True si el objeto fue agregado con exito.
	 */
	public boolean agregar(final ProductoVideoClub prod)  {
		return this.productos.add(prod);
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * agregarProducto:
	 *
	 * Método que recibe por parámetro un objeto {@link ProductoVideoClub} y lo agrega al
	 * HashSet de ProductoVideoClub solo si es distinto de null y no exista un
	 * producto con el mismo codigo dentro del HashSet.
	 *
	 * @param prod
	 *            Referencia al producto.
	 */
	public void agregarProducto(final ProductoVideoClub prod  ) {
		if ((prod != null) && (this.buscarProducto(prod.getCodigo()).getCodigo() == 0)) {
			this.productos.add(prod);
			prod.agregarDatos();
		}
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * buscarProducto:
	 *
	 * Método que busca un producto que posea el código enviado por parámetro en el
	 * HashSet de {@link ProductoVideoClub}.
	 * 
	 * @param cod
	 *            Código del producto (int).
	 * @return Una nueva instancia de {@link ProductoVideoClub} o el objeto
	 *         {@link ProductoVideoClub} si es que lo encuentra.
	 *
	 */
	public ProductoVideoClub buscarProducto(final int cod) {
		if ((this.productos != null) && !this.productos.isEmpty())
			for (final ProductoVideoClub prod : this.productos)
				if (prod.getCodigo() == cod)
					return prod;
		if ((cod >= 100000) && (cod <= 999999))
			return new Juego();
		else
			return new Pelicula();
	}

		
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadProductos:
	 *
	 * Método que cuenta la cantidad de productos almacenados en el HashSet.
	 *
	 * @return La cantidad de productos almacenados.
	 */
	public int cantidadProductos() {
		return this.productos.size();
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * clonar:
	 *
	 * Método que añade todos los objetos del HashSet a uno nuevo.
	 *
	 * @return El nuevo HashSet con todos los objetos del antiguo.
	 */
	public HashSet<ProductoVideoClub> clonar() {
		final HashSet<ProductoVideoClub> nuevo = new HashSet<ProductoVideoClub>();
		nuevo.addAll(this.productos);
		return nuevo;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * clonarListaPelicula:
	 *
	 * Método que crea un ArrayList de objetos {@link Pelicula} y le inserta todos los
	 * objetos del HashSet de la clase.
	 *
	 * @return El ArrayList que contiene los objetos {@link Pelicula} del HashSet.
	 */
	public ArrayList<ProductoVideoClub> clonarListaProducto() {
		final ArrayList<ProductoVideoClub> nuevo = new ArrayList<ProductoVideoClub>();
		for (final ProductoVideoClub prod : this.productos)
			nuevo.add(prod);
		Collections.sort(nuevo, new Comparator<Object>() {
			@Override
			public int compare(final Object p1, final Object p2) {
				return ((ProductoVideoClub) p1).getNombre().compareTo(((ProductoVideoClub) p2).getNombre());
			}
		});
		return nuevo;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * eliminarProducto:
	 *
	 * Método que recibe por parámetro un código de producto, busca la producto con
	 * ese código y lo elimina del HashSet si lo encuentra.
	 *
	 * @param cod
	 *            Código del producto (int).
	 */
	public void eliminarProducto(final int cod)   {
		if ((this.productos != null) && !this.productos.isEmpty())
			for (final ProductoVideoClub prod : this.productos)
				if (prod.getCodigo() == cod) {
					this.productos.remove(prod);
					return;
				}
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * generarReporte:
	 *
	 * Método implementado perteneciente a la interface Exportable.
	 *
	 * @return true si el archivo fue generado con éxito.
	 */
	@Override
	public abstract boolean generarReporte(String directorio);

	
	
	
	
	
	
	
	
	
	
	/**
	 * listaProductosDisponibles:
	 *
	 * Guarda en un ArrayList todos los productos de los cuales se tenga stock.
	 *
	 * @return El ArrayList con los productos disponibles.
	 */
	public ArrayList<ProductoVideoClub> listaProductosDisponibles() {
		ArrayList<ProductoVideoClub> nuevo = new ArrayList<ProductoVideoClub>();
		nuevo = this.clonarListaProducto();
		for (int i = 0; i < nuevo.size(); i++)
			if (nuevo.get(i).getCantidad() == 0)
				nuevo.remove(i);
		return nuevo;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * recomendacionPeliculas:
	 *
	 * Método que recibe un objeto {@link ProductoVideoClub} y busca en el HashSet los 3
	 * productos más similares a esta (cantidad de datos en común), excluyendo a la
	 * enviada si es que es encontrada.
	 *
	 * Crea un vector de tamaño 3 y le añade los 3 productos ordenados
	 * ascendentemente por similitud.
	 *
	 * @param prod
	 *            Referencia al producto.
	 * @return El vector con los 3 productos similares al enviado.
	 */
	public ProductoVideoClub[] recomendacionProductos(final ProductoVideoClub prod) {
		final ProductoVideoClub[] prods = new ProductoVideoClub[3];
		if (this.productos != null) {
			final Iterator<ProductoVideoClub> it = this.productos.iterator();
			if (it.hasNext()) {
				ProductoVideoClub ptr = it.next();
				if (ptr != null)
					prods[0] = ptr;
				ptr = it.next();
				if (ptr != null)
					prods[1] = ptr;
				ptr = it.next();
				if (ptr != null)
					prods[2] = ptr;
			}
			while (it.hasNext()) {
				final ProductoVideoClub ptr = it.next();
				if (ptr != null)
					if (ptr.datosEnComun(prod) > prods[0].datosEnComun(prod))
						prods[0] = ptr;
					else if (ptr.datosEnComun(prod) > prods[1].datosEnComun(prod))
						prods[1] = ptr;
					else if (ptr.datosEnComun(prod) > prods[2].datosEnComun(prod))
						prods[2] = ptr;
			}
		}
		if (prods[0].datosEnComun(prod) == 0)
			prods[0] = null;
		if (prods[1].datosEnComun(prod) == 0)
			prods[1] = null;
		if (prods[2].datosEnComun(prod) == 0)
			prods[2] = null;
		return prods;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * setListaProductoPorTipo:
	 *
	 * Método que recibe un string tipo, crea un ArrayList de ProductoVideoClub, al
	 * cual le inserta todos los productos que posean el tipo en su ArrayList de
	 * tipos.
	 *
	 * @param tipo
	 *            Tipo del producto (String).
	 * @return El nuevo ArrayList con todos los productos que posean el tipo enviado
	 *         por parámetro.
	 */
	public ArrayList<ProductoVideoClub> setListaProductoPorTipo(final String tipo) {
		final ArrayList<ProductoVideoClub> peliculaTipos = new ArrayList<ProductoVideoClub>();
		for (final ProductoVideoClub prod : this.productos)
			if(prod.buscarTipo(tipo))
				peliculaTipos.add(prod);
		Collections.sort(peliculaTipos, new Comparator<Object>() {
			@Override
			public int compare(final Object p1, final Object p2) {
				return ((ProductoVideoClub) p1).getNombre().compareTo(((ProductoVideoClub) p2).getNombre());
			}
		});
		return peliculaTipos;
	}
}
