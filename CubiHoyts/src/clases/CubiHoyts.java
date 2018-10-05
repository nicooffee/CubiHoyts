package clases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import excepciones.InformacionRegistradaException;
import excepciones.RUTException;
import factory.Creator;
import utilidades.CargadorDeDatos;
import utilidades.Exportable;

/**
 * Clase Principal:
 *
 * Clase cabeza que posee una estructura completa de un videoclub.
 *
 * Posee los siguiente atributos:
 *
 * -MapaCliente (HashMap). -MapaHistorial (HashMap). -ConjuntoPelicula
 * (HashSet). -ConjuntoJuego (HashSet). -ListaArriendo (ArrayList). -ListaVenta
 * (ArrayList).
 * 
 *
 * @author Nicolás Honorato
 *
 */

public class CubiHoyts {
	private MapaCliente clientes; // Mapa de clientes del videoclub.
	private MapaHistorial historiales; // Mapa de historiales del videoclub.
	private ConjuntoProducto peliculas; // Conjunto de películas del videoclub.
	private ConjuntoProducto juegos; // Conjunto de juegos del videoclub.
	private ListaNegocio arriendos; // Lista de arriendos del videoclub
	private ListaNegocio ventas; // Lista de ventas del videoclub.
	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor sin parámetros de la clase CubiHoyts.
	 *
	 * Dimensiona todos los atributos de la clase.
	 */
	public CubiHoyts() {
		this.clientes = new MapaCliente();
		this.historiales = new MapaHistorial();
		this.peliculas = Creator.crearConjunto(Creator.crearConjuntoPelicula);
		this.juegos = Creator.crearConjunto(Creator.crearConjuntoJuego);
		this.arriendos = Creator.crearLista(Creator.crearListaArriendo);
		this.ventas = Creator.crearLista(Creator.crearListaVenta);
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor con parámetros:
	 * 
	 * Constructor que copia todas las referencia de los parámetros en los atributos
	 * del objeto.
	 * @param cli
	 * 				Referencia a un MapaCliente.
	 * @param hist
	 * 				Referencia a un MapaHistorial.
	 * @param pelis
	 * 				Referencia a un ConjuntoPelicula.
	 * @param jug
	 * 				Referencia a un ConjuntoJuego.
	 * @param arr
	 * 				Referecnia a una ListaArriendo.
	 * @param ven
	 * 				Referecnia a una ListaVenta.
	 */
	public CubiHoyts(MapaCliente cli,MapaHistorial hist,ConjuntoPelicula pelis,ConjuntoJuego jug,ListaNegocio arr,ListaNegocio ven) {
		this.clientes=cli;
		this.historiales=hist;
		this.peliculas=pelis;
		this.juegos=jug;
		this.arriendos=arr;
		this.ventas=ven;
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * A continuación se declaran métodos que se encargan de llamar a cada método de
	 * cada atributo de la clase, retornando así lo mismo que estos métodos, si es
	 * que se requiere.
	 */
	
	/**
	 * {@link ListaNegocio#agregarNegocio(Negocio)}
	 */
	public void agregarArriendo(final Arriendo arr) throws InformacionRegistradaException{
		if(arriendos.buscarNegocio(arr.getCodigo())==null)
			this.arriendos.agregarNegocio(arr);
		else
			throw new InformacionRegistradaException();
	}
	/**
	 * {@link MapaCliente#agregarCliente(Cliente)}
	 */
	public void agregarCliente(Cliente cli) throws RUTException,InformacionRegistradaException{
		if(clientes.buscarCliente(cli.getRut()).getNombreCliente().equals("*ELIMINADO*"))
			this.clientes.agregarCliente(cli);
		else 
			throw new InformacionRegistradaException();
	}
	/**
	 * {@link MapaHistorial#agregarHistorial(String, Historial)}
	 */
	public void agregarHistorial(final Historial hist) throws InformacionRegistradaException{
		if(historiales.buscarHistorial(hist.getRutCliente())==null)
			this.historiales.agregarHistorial(hist.getRutCliente(), hist);
		else
			throw new InformacionRegistradaException();
	}
	/**
	 * {@link ConjuntoProducto#agregarProducto(ProductoVideoClub)}
	 */
	public void agregarJuego(final ProductoVideoClub jug) throws InformacionRegistradaException{
		if(juegos.buscarProducto(jug.getCodigo()).getCodigo()==0)	
			this.juegos.agregarProducto(jug);
		else
			throw new InformacionRegistradaException();
	}
	/**
	 * {@link ConjuntoProducto#agregarProducto(ProductoVideoClub)}
	 */
	public void agregarPelicula(final ProductoVideoClub pel) throws InformacionRegistradaException{
		if(peliculas.buscarProducto(pel.getCodigo()).getCodigo()==0)
			this.peliculas.agregarProducto(pel);
		else
			throw new InformacionRegistradaException();
	}
	/**
	 * {@link ListaVenta#agregarNegocio(Negocio)}
	 */
	public void agregarVenta(final Venta ven) throws InformacionRegistradaException{
		if(ventas.buscarNegocio(ven.getCodigo())==null)
			this.ventas.agregarNegocio(ven);
		else
			throw new InformacionRegistradaException();
	}
	/**
	 * {@link ListaNegocio#buscarNegocio(String)}
	 */
	public Negocio buscarArriendo(final String cod) {
		return this.arriendos.buscarNegocio(cod);
	}
	/**
	 * {@link MapaCliente#buscarCliente(int)}
	 */
	public Cliente buscarCliente(final int i) {
		return this.clientes.buscarCliente(i);
	}
	/**
	 * {@link MapaCliente#buscarCliente(String)}
	 */
	public Cliente buscarCliente(final String rut) throws RUTException{
		return this.clientes.buscarCliente(rut);
	}
	/**
	 * {@link MapaHistorial#buscarHistorial(String)}
	 */
	public Historial buscarHistorial(final String rut) {
		return this.historiales.buscarHistorial(rut);
	}
	/**
	 * {@link ConjuntoProducto#buscarProducto(int)}
	 */
	public ProductoVideoClub buscarJuego(final int cod) {
		return this.juegos.buscarProducto(cod);
	}
	/**
	 * {@link ConjuntoProducto#buscarProducto(int)}
	 */
	public ProductoVideoClub buscarPelicula(final int cod) {
		return this.peliculas.buscarProducto(cod);
	}
	/**
	 * {@link ConjuntoProducto#buscarProducto(int)}
	 */
	public ProductoVideoClub buscarProducto(final int cod) {
		return (cod>=100000 && cod<=999999)?juegos.buscarProducto(cod):peliculas.buscarProducto(cod);
	}
	/**
	 * {@link ListaNegocio#buscarNegocio(String)}
	 */
	public Negocio buscarVenta(final String cod) {
		return this.ventas.buscarNegocio(cod);
	}
	/**
	 * {@link ListaNegocio#cantidadNegocios()}
	 */
	public int cantidadArriendos() {
		return this.arriendos.cantidadNegocios();
	}
	/**
	 * {@link MapaCliente#cantidadClientes()}
	 */
	public int cantidadClientes() {
		return this.clientes.cantidadClientes();
	}
	/**
	 * {@link ConjuntoProducto#cantidadProductos()}
	 */
	public int cantidadJuegos() {
		return this.juegos.cantidadProductos();
	}
	/**
	 * {@link ConjuntoProducto#cantidadProductos()}
	 */
	public int cantidadPeliculas() {
		return this.peliculas.cantidadProductos();
	}
	/**
	 * {@link ListaNegocio#cantidadNegocios()}
	 */
	public int cantidadVentas() {
		return this.ventas.cantidadNegocios();
	}
	/**
	 * {@link ListaNegocio#clonarListaNegocios()}
	 */
	public ArrayList<Negocio> clonarListaArriendos() {
		return this.arriendos.clonarListaNegocios();
	}
	/**
	 * {@link ConjuntoProducto#clonarListaProducto()}
	 */
	public ArrayList<ProductoVideoClub> clonarListaJuego() {
		return this.juegos.clonarListaProducto();
	}
	/**
	 * {@link ConjuntoProducto#clonarListaProducto()}
	 */
	public ArrayList<ProductoVideoClub> clonarListaPelicula() {
		return this.peliculas.clonarListaProducto();
	}
	/**
	 * {@link ListaNegocio#clonarListaNegocios()}
	 */
	public ArrayList<Negocio> clonarListaVenta() {
		return this.ventas.clonarListaNegocios();
	}
	/**
	 * {@link MapaCliente#clonarMapaClientes()}
	 */
	public ArrayList<Cliente> clonarMapaClientes() {
		return this.clientes.clonarMapaClientes();
	}
	/**
	 * {@link MapaCliente#eliminarClientePorRut(String)}
	 */
	public void eliminarClientePorRut(final String rut)   {
		try {
			this.clientes.eliminarClientePorRut(rut);
		}catch(RUTException e) {
		}
	}
	/**
	 * {@link ListaNegocio#eliminarNegocio(String)}
	 */
	public void eliminarNegocio(String cod) {
		arriendos.eliminarNegocio(cod);
		ventas.eliminarNegocio(cod);
		historiales.eliminarNegocio(cod);
	}
	/**
	 * {@link ListaNegocio#reemplazar(Negocio)}
	 */
	public void reemplazarNegocio(Negocio neg) {
		arriendos.reemplazar(neg);
		ventas.reemplazar(neg);
		historiales.reemplazarNegocio(neg);
	}
	/**
	 * {@link MapaHistorial#eliminarHistorial(String)}
	 */
	public void eliminarHistorial(final String rut) {
		this.historiales.eliminarHistorial(rut);
	}
	/**
	 * {@link ConjuntoProducto#eliminarProducto(int)}
	 */
	public void eliminarProducto(final int cod)   {
		this.juegos.eliminarProducto(cod);
		this.peliculas.eliminarProducto(cod);
	}
	/**
	 * {@link ListaNegocio#gananciaTotal()}
	 */
	public int gananciaArriendos() {
		return this.arriendos.gananciaTotal();
	}
	/**
	 * {@link ListaNegocio#gananciaTotal()}
	 */
	public int gananciaVentas() {
		return this.ventas.gananciaTotal();
	}
	
	/**
	 * {@link Exportable#generarReporte(String)}
	 */
	public boolean generarReporteArriendos(String directorio)   {
		return this.arriendos.generarReporte(directorio);
	}
	/**
	 * {@link Exportable#generarReporte(String)}
	 */
	public boolean generarReporteClientes(String directorio)   {
		return this.clientes.generarReporte(directorio);
	}
	/**
	 * {@link Exportable#generarReporte(String)}
	 */
	public boolean generarReporteJuegos(String directorio)   {
		return this.juegos.generarReporte(directorio);
	}
	/**
	 * {@link Exportable#generarReporte(String)}
	 */
	public boolean generarReportePeliculas(String directorio)   {
		return this.peliculas.generarReporte(directorio);
	}
	/**
	 * {@link Exportable#generarReporte(String)}
	 */
	public boolean generarReporteVentas(String directorio)   {
		return this.ventas.generarReporte(directorio);
	}
	
	public MapaCliente getClientes() {
		return this.clientes;
	}

	public ConjuntoProducto getJuegos() {
		return this.juegos;
	}

	public ConjuntoProducto getPeliculas() {
		return this.peliculas;
	}
	/**
	 * {@link ListaNegocio#listaNegociosPorRut(String)}
	 */
	public ListaNegocio listaArriendosPorRUT(final String rut) {
		return this.arriendos.listaNegociosPorRut(rut);
	}
	/**
	 * {@link ListaNegocio#listaNegociosPorRut(String)}
	 */
	public ArrayList<Negocio> listaArriendoVenta() {
		final ArrayList<Negocio> lista = new ArrayList<Negocio>();
		lista.addAll(this.arriendos.clonarListaNegocios());
		lista.addAll(this.ventas.clonarListaNegocios());
		return lista;
	}
	/**
	 * {@link ConjuntoProducto#listaProductosDisponibles()}
	 */
	public ArrayList<ProductoVideoClub> listaJuegosDisponibles() {
		return this.juegos.listaProductosDisponibles();
	}
	/**
	 * {@link ConjuntoProducto#listaProductosDisponibles()}
	 */
	public ArrayList<ProductoVideoClub> listaPeliculasDisponibles() {
		return this.peliculas.listaProductosDisponibles();
	}
	/**
	 * {@link ConjuntoProducto#setListaProductoPorTipo(String)}
	 */
	public ArrayList<ProductoVideoClub> listaPeliculasPorTipo(final String tipo) {
		return this.peliculas.setListaProductoPorTipo(tipo);
	}
	/**
	 * {@link ConjuntoProducto#setListaProductoPorTipo(String)}
	 */
	public ArrayList<ProductoVideoClub> listaJuegosPorTipo(final String tipo){
		return this.juegos.setListaProductoPorTipo(tipo);
	}
	/**
	 * {@link ConjuntoJuego#setListaJuegosPorConsola(String)}
	 */
	public ArrayList<ProductoVideoClub> listaJuegosPorConsola(final String consola){
		return ((ConjuntoJuego) this.juegos).setListaJuegosPorConsola(consola);
	}
	/**
	 * {@link ConjuntoJuego#setListaJuegosPorConsolaYGenero(String)}
	 */
	public ArrayList<ProductoVideoClub> listaJuegosPorConsolaYGenero(final String consola,final String genero){
		return ((ConjuntoJuego) this.juegos).setListaJuegosPorConsolaYGenero(consola, genero);
	}
	/**
	 * {@link ListaNegocio#listaNegociosPorRut(String)}
	 */
	public ListaNegocio listaVentasPorRUT(final String rut) {
		return this.ventas.listaNegociosPorRut(rut);
	}	
	/**
	 * {@link MapaHistorial#mostrarGraficoVecesArriendoVenta()}
	 */
	public ChartPanel mostrarGraficoVecesArriendoVenta() {
		return this.historiales.mostrarGraficoVecesArriendoVenta();
	}
	/**
	 * {@link ConjuntoProducto#recomendacionProductos(ProductoVideoClub)}
	 */
	public ProductoVideoClub[] recomendacionJuegos(final Juego jug) {
		return this.juegos.recomendacionProductos(jug);
	}
	/**
	 * {@link ConjuntoProducto#recomendacionProductos(ProductoVideoClub)}
	 */
	public ProductoVideoClub[] recomendacionPeliculas(final Pelicula pel) {
		return this.peliculas.recomendacionProductos(pel);
	}
	
	
	
	public void setArriendos(final ListaNegocio arriendos) {
		this.arriendos = arriendos;
	}
	public void setClientes(final MapaCliente clientes) {
		this.clientes = clientes;
	}
	public void setHistoriales(final MapaHistorial historiales) {
		this.historiales = historiales;
	}
	public void setJuegos(final Exportable juegos) {
		this.juegos = (ConjuntoProducto) juegos;
	}
	public void setPeliculas(final ConjuntoProducto peliculas) {
		this.peliculas = peliculas;
	}
	public void setVentas(final ListaNegocio ventas) {
		this.ventas = ventas;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * mostrarGraficoGananciaUltimaSemana:
	 *
	 * Método que crea un polígono de frecuencia con las ganancias de los últimos 25
	 * días.
	 * 
	 * @return ChartPanel con el gráfico.
	 */
	public ChartPanel mostrarGraficoGananciaUltimaSemana() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 24; i >= 0; i--) {
			final Calendar fecha = Calendar.getInstance();
			fecha.add(Calendar.DAY_OF_YEAR, -i);
			final String fechita = (fecha.get(Calendar.DAY_OF_MONTH)) + "/" + (fecha.get(Calendar.MONTH) + 1);
			dataset.addValue(this.arriendos.gananciaTotalPorDia(fecha) + this.ventas.gananciaTotalPorDia(fecha),
					"Ganancia", fechita);
		}
		final JFreeChart chart = ChartFactory.createLineChart("Gráfico de ganancias de los últimos 25 días", "Día/Mes",
				"Ganancia", dataset, PlotOrientation.VERTICAL, true, true, false);
		final ChartPanel chPanel = new ChartPanel(chart);
		return chPanel;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * mostrarGraficoPorCantidad:
	 *
	 * Método que genera un gráfico de torta con la cantidad de veces que existe un
	 * negocio con cada película.
	 *
	 * @return El gráfico con cantidad de películas arrendadas/compradas.
	 */
	public ChartPanel mostrarGraficoPorCantidad() {
		final DefaultPieDataset dataset = new DefaultPieDataset();
		final ArrayList<ProductoVideoClub> peliculas = this.clonarListaPelicula();
		final ArrayList<ProductoVideoClub> juegos = this.clonarListaJuego();
		for (int i = 0; i < peliculas.size(); i++) {
			final int cod = peliculas.get(i).getCodigo();
			final int cantidad = this.arriendos.cantidadNegociosPorProducto(cod)
					+ this.ventas.cantidadNegociosPorProducto(cod);
			if (cantidad != 0)
				dataset.setValue(peliculas.get(i).getNombre(), cantidad);
		}
		for (int i = 0; i < juegos.size(); i++) {
			final int cod = juegos.get(i).getCodigo();
			final int cantidad = this.arriendos.cantidadNegociosPorProducto(cod)
					+ this.ventas.cantidadNegociosPorProducto(cod);
			if (cantidad != 0)
				dataset.setValue(juegos.get(i).getNombre(), cantidad);
		}
		final JFreeChart chart = ChartFactory.createPieChart(
				"Gráfico histórico de cantidad de productos comprados/arrendados", dataset, true, true, true);
		final ChartPanel chPanel = new ChartPanel(chart);
		return chPanel;
	}

	public ChartPanel mostrarGraficoTipoCliente() {
		return this.clientes.mostrarGraficoTipoCliente();
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * mostrarGraficosVentaArriendoPorMes:
	 *
	 * Método que crea un gráficos de barras horizontal con la cantidad de ventas y
	 * arriendos de los últimos 5 meses.
	 * 
	 * @return ChartPanel con el gráfico.
	 */
	public ChartPanel mostrarGraficoVentaArriendoPorMes() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		final String meses[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre" };
		for (int i = Calendar.getInstance().get(Calendar.MONTH); i > (Calendar.getInstance().get(Calendar.MONTH)
				- 5); i--) {
			dataset.addValue(this.arriendos.cantidadNegociosPorMes(i), "Arriendos", meses[i]);

			dataset.addValue(this.ventas.cantidadNegociosPorMes(i), "Ventas", meses[i]);
		}
		final JFreeChart chart = ChartFactory.createBarChart(
				"Gráfico de compra o arriendo de productos de los últimos 5 meses", "Mes", "Cantidad", dataset,
				PlotOrientation.HORIZONTAL, true, true, false);
		final ChartPanel chPanel = new ChartPanel(chart);
		return chPanel;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * cargarDatos:
	 * 
	 * Método que carga todos los datos 
	 * de todos los datos del videoclub.
	 * 
	 * Para lograr cargar los datos exitósamente
	 * utiliza los siguientes métodos de la clase
	 * {@link CargadorDeDatos}:
	 * 
	 * {@link CargadorDeDatos#cargarDatosArriendos(MapaCliente, ConjuntoPelicula, ConjuntoJuego)}
	 * {@link CargadorDeDatos#cargarDatosClientes()}
	 * {@link CargadorDeDatos#cargarDatosHistorial()}
	 * {@link CargadorDeDatos#cargarDatosJuegos()}
	 * {@link CargadorDeDatos#cargarDatosPeliculas()}
	 * {@link CargadorDeDatos#cargarDatosVentas(MapaCliente, ConjuntoPelicula, ConjuntoJuego)}
	 * 
	 */
	public void cargarDatos() {
		CargadorDeDatos cargador=new CargadorDeDatos();
		try {
			this.setClientes(cargador.cargarDatosClientes());
			this.setPeliculas(cargador.cargarDatosPeliculas());
			this.setJuegos(cargador.cargarDatosJuegos());
			this.setHistoriales(cargador.cargarDatosHistorial());
			this.setArriendos(cargador.cargarDatosArriendos(this.getClientes(), this.getPeliculas(),this.getJuegos()));
			this.setVentas(cargador.cargarDatosVentas(this.getClientes(), this.getPeliculas(),this.getJuegos()));
			for(int i=0;i<this.cantidadClientes();i++) {
				final String rut=this.buscarCliente(i).getRut();
				this.buscarHistorial(rut).setListaArriendos(this.listaArriendosPorRUT(rut));
				this.buscarHistorial(rut).setListaVentas(this.listaVentasPorRUT(rut));
			}

		}
		catch(final SQLException e) {
			this.setClientes(new MapaCliente());
			this.setPeliculas(Creator.crearConjunto(Creator.crearConjuntoPelicula));
			this.setJuegos(Creator.crearConjunto(Creator.crearConjuntoJuego));
			this.setHistoriales(new MapaHistorial());
			this.setArriendos(Creator.crearLista(Creator.crearListaArriendo));
			this.setVentas(Creator.crearLista(Creator.crearListaVenta));
			JOptionPane.showMessageDialog(null, "¡Error al conectar con la base de datos!");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * clonar:
	 * 
	 * Método que clona toda la estructura del objeto CubiHoyts.
	 * @return
	 * El nuevo objeto CubiHoyts creado.
	 */
	public CubiHoyts clonar() {
		CubiHoyts clone=new CubiHoyts(new MapaCliente(clientes.clonar()),
				new MapaHistorial(historiales.clonar()),
				new ConjuntoPelicula(peliculas.clonar()),
				new ConjuntoJuego(juegos.clonar()),
				new ListaArriendo(arriendos.clonar()),
				new ListaVenta(ventas.clonar()));
		return clone;
	}
}
