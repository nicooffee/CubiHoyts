package clases;

import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Clase MapaHistorial:
 *
 * Clase que tiene como único atributo un HashMap que guarda objetos {@link Historial}
 * con clave rut.
 *
 * Posee métodos para manejar el procesamiento de datos del HashMap.
 *
 * @author Nicolás Honorato
 *
 */
public class MapaHistorial {
	private final HashMap<String, Historial> historiales;

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor sin parámetros de la clase:
	 *
	 * Constructor que dimensiona el HashMap de objetos {@link Historial}.
	 */
	public MapaHistorial() {
		this.historiales = new HashMap<String, Historial>();
	}
	public MapaHistorial(HashMap<String, Historial> hist) {
		historiales=hist;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * agregarHistorial:
	 *
	 * Método que agrega un nuevo objeto {@link Historial} al HashMap.
	 *
	 * @param rut
	 *            Clave del historial (rut).
	 * @param hist
	 *            Historial a agregar.
	 */
	public void agregarHistorial(final String rut, final Historial hist) {
		this.historiales.put(rut, hist);
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * buscarHistorial:
	 *
	 * Método que toma el objeto que le corresponde a la clave enviada por
	 * parámetro.
	 *
	 * @param rut
	 *            Clave del historial (rut).
	 * @return El historial correspondiente a la clave.
	 */
	public Historial buscarHistorial(final String rut) {
		return this.historiales.get(rut);
	}


	
	
	
	
	
	
	
	
	
	
	/**
	 * crearListaHistorial:
	 *
	 * Método que traspasa todos los objetos {@link Historial} del HashMap a un nuevo
	 * ArrayList.
	 *
	 * @return ArrayList con los objetos {@link Historial}.
	 *
	 */
	public ArrayList<Historial> crearListaHistorial() {
		final ArrayList<Historial> clo = new ArrayList<Historial>();
		clo.addAll(this.historiales.values());
		return clo;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * clonar:
	 * 
	 * Método que crea un nuevo objeto a partir de los datos del creador.
	 * 
	 * @return
	 * El nuevo objeto creado.
	 */
	public HashMap<String, Historial> clonar(){
		HashMap<String, Historial> nuevo=new HashMap<String, Historial>();
		nuevo.putAll(historiales);
		return nuevo;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * eliminarHistorial:
	 *
	 * Método que elimina del HashMap el historial que posea la clave enviada por
	 * parámetro.
	 *
	 * @param rut
	 *            Clave del historial a eliminar.
	 */
	public void eliminarHistorial(final String rut) {
		this.historiales.remove(rut);
	}
	
	
	
	
	
	
	
	
	
	
	
	public void eliminarNegocio(final String cod) {
		ArrayList<Historial> hists=this.crearListaHistorial();
		for(int i=0;i<hists.size();i++) {
			hists.get(i).eliminarNegocio(cod);
		}
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * mostrarGraficoVecesArriendoVenta:
	 *
	 * Método que crea un gráfico de barras vertical con la cantidad de veces que un
	 * cliente realizó una venta o un arriendo de una película.
	 *
	 * Recorre todo el HashMap de clientes y lo añade al dataset del gráfico.
	 *
	 * @return ChartPanel con el gráfico
	 */
	public ChartPanel mostrarGraficoVecesArriendoVenta() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		final ArrayList<Historial> historiales = this.crearListaHistorial();
		for (int i = 0; i < historiales.size(); i++) {
			final Historial hist = historiales.get(i);

			dataset.addValue(hist.getNumArriendos(), "Arriendos", hist.getRutCliente());

			dataset.addValue(hist.getNumVentas(), "Ventas", hist.getRutCliente());
		}
		final JFreeChart chart = ChartFactory.createBarChart(
				"Gráfico histórico de la cantidad de veces que compró o arrendó un cliente (por RUT).", "Clientes",
				"Cantidad", dataset, PlotOrientation.VERTICAL, true, true, false);
		final ChartPanel chPanel = new ChartPanel(chart);
		return chPanel;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * reemplazarNegocio:
	 * 
	 * Método que recorre todos los historiales haciendo una llamada al método
	 * {@link Historial#reemplazarNegocio(Negocio)}
	 * 
	 * @param neg
	 * 				Negocio de reemplazo.
	 */
	public void reemplazarNegocio(final Negocio neg) {
		ArrayList<Historial> hists=this.crearListaHistorial();
		for(int i=0;i<hists.size();i++) {
			hists.get(i).reemplazarNegocio(neg);
		}
	}
}
