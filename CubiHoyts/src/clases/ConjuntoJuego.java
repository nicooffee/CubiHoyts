package clases;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Clase ConjuntoJuego:
 *
 * Clase que extiende la clase abstracta {@link ConjuntoProducto}
 *
 * Posee métodos para manejar el procesamiento de datos de un HashSet con
 * referencias a {@link Juego}
 * 
 * @author Constanza Cerda
 *
 */
public class ConjuntoJuego extends ConjuntoProducto {

	
	
	
	
	
	
	
	
	/**
	 * Constructor sin parámetros:
	 *
	 * Constructor que hace una llamada al constructor de la clase abstracta
	 * ConjuntoProducto para dimensionar el HashSet de Producto
	 *
	 */
	public ConjuntoJuego() {
		super();
	}
	public ConjuntoJuego(HashSet<ProductoVideoClub> productos) {
		super(productos);
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * agregarJuego:
	 *
	 * Método que dimensiona un nuevo objeto {@link Juego} con todos los datos que recibe
	 * por parámetro y lo agrega al HashSet de Producto.
	 *
	 * @param nom
	 *            Nombre del juego(String).
	 * @param cant
	 *            Cantidad (int).
	 * @param cod
	 *            Código (int).
	 * @param desc
	 *            Descripción (String).
	 * @param tipo
	 *            Tipos del juego (Array).
	 * @param consola
	 *            Consolas del juego (Array).
	 * @param precioA
	 *            Precio arriendo (int).
	 * @param precioV
	 *            Precio venta (int).
	 * @param clas
	 *            Clasificación (String).
	 * @param maxCantJ
	 *            Máxima cantidad de jugadores (int).
	 */
	public void agregarJuego(final String nom, final int cant, final int cod, final String desc, final String[] tipo,
			final String[] consola, final int precioA, final int precioV, final String clas, final int maxCantJ) {
		final ArrayList<String> tipos = new ArrayList<String>();
		for (int i = 0; i < tipo.length; i++)
			if (tipo[i] != null)
				tipos.add(tipo[i]);
		final ArrayList<String> consolas = new ArrayList<String>();
		for (int i = 0; i < consola.length; i++)
			if (consola[i] != null)
				consolas.add(consola[i]);
		final Juego juego = new Juego(nom, cant, cod, desc, tipos, consolas, precioA, precioV, clas, maxCantJ);
		this.agregar(juego);
	}

	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see clases.ConjuntoProducto#generarReporte()
	 */
	@Override
	public boolean generarReporte(String directorio){
		String master = "src/archivos/reporteJuegos.jasper";
        final ArrayList<ProductoVideoClub> juegos=this.clonarListaProducto();
        try {
        	JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(juegos);
            JasperPrint informe = JasperFillManager.fillReport(master, null,ds);
            JasperExportManager.exportReportToPdfFile(informe,directorio+"/Reporte Juegos CubiHoyts "+new Date(Calendar.getInstance().getTimeInMillis()).toString()+".pdf");
        }
        catch(JRException e) {
        	return false;
        }
        catch(NullPointerException e1) {
        	JOptionPane.showMessageDialog(null, "¡No existe información para generar el reporte!","Error",JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        catch(JRRuntimeException e2) {
        	return false;
        }
        return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * setListaJuegosPorConsola:
	 *
	 * Método que recibe un string consola, crea un ArrayList de ProductoVideoClub, al
	 * cual le inserta todos los productos que posean la consola en su ArrayList de
	 * consolas.
	 *
	 * @param consola
	 *            Consola del juego (String).
	 * @return El nuevo ArrayList con todos los juegos que posean la consola enviada
	 *         por parámetro.
	 */
	public ArrayList<ProductoVideoClub> setListaJuegosPorConsola(final String consola) {
		final ArrayList<ProductoVideoClub> juegoConsolas = new ArrayList<ProductoVideoClub>();
		for (final ProductoVideoClub prod : this.clonar())
			if(((Juego) prod).buscarConsola(consola))
				juegoConsolas.add(prod);
		Collections.sort(juegoConsolas, new Comparator<Object>() {
			@Override
			public int compare(final Object p1, final Object p2) {
				return ((ProductoVideoClub) p1).getNombre().compareTo(((ProductoVideoClub) p2).getNombre());
			}
		});
		return juegoConsolas;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * setListaJuegosPorConsolaYGenero:
	 *
	 * Método que recibe un string consola, crea un ArrayList de ProductoVideoClub, al
	 * cual le inserta todos los productos que posean la consola en su ArrayList de
	 * consolas.
	 *
	 * @param consola
	 *            Consola del juego (String).
	 * @return El nuevo ArrayList con todos los juegos que posean la consola enviada
	 *         por parámetro.
	 */
	public ArrayList<ProductoVideoClub> setListaJuegosPorConsolaYGenero(final String consola,final String genero) {
		final ArrayList<ProductoVideoClub> juegoConsolas = new ArrayList<ProductoVideoClub>();
		for (final ProductoVideoClub prod : this.clonar())
			if(((Juego) prod).buscarConsola(consola) && prod.buscarTipo(genero))
				juegoConsolas.add(prod);
		Collections.sort(juegoConsolas, new Comparator<Object>() {
			@Override
			public int compare(final Object p1, final Object p2) {
				return ((ProductoVideoClub) p1).getNombre().compareTo(((ProductoVideoClub) p2).getNombre());
			}
		});
		return juegoConsolas;
	}
}
