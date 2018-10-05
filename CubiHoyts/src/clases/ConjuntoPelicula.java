package clases;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Clase ConjuntoPelicula:
 *
 * Clase que extiende la clase abstracta {@link ConjuntoProducto}
 *
 * Posee métodos para manejar el procesamiento de datos de un HashSet con
 * referencias a {@link Pelicula}
 * 
 * @author Constanza Cerda
 *
 */
public class ConjuntoPelicula extends ConjuntoProducto {

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor sin parámetros:
	 *
	 * Constructor que hace una llamada al constructor de la clase abstracta
	 * ConjuntoProducto para dimensionar el HashSet de Producto
	 *
	 */
	public ConjuntoPelicula() {
		super();
	}
	public ConjuntoPelicula(HashSet<ProductoVideoClub> productos) {
		super(productos);
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * agregarPelicula:
	 *
	 * Método que dimensiona un nuevo objeto {@link Pelicula} con todos los datos que recibe
	 * por parámetro y lo agrega al HashSet de Producto.
	 *
	 * @param cant
	 *            Cantidad de la película (int).
	 * @param cod
	 *            Código de la película (int).
	 * @param nom
	 *            Nombre de la película (String).
	 * @param tipo
	 *            Tipos de la película (Array).
	 * @param descrip
	 *            Descripción de la película (String).
	 * @param est
	 *            La película es estreno (boolean).
	 * @param anio
	 *            Año estreno de la película (int).
	 * @param pArr
	 *            Precio arriendo de la película (int).
	 * @param pVent
	 *            Precio venta de la película (int).
	 */
	public void agregarPelicula(final int cant, final int cod, final String nom, final String[] tipo,
			final String descrip, final boolean est, final int anio, final int pArr, final int pVent)
			  {
		final ArrayList<String> tipos = new ArrayList<String>();
		for (int i = 0; i < tipo.length; i++)
			if (tipo[i] != null)
				tipos.add(tipo[i]);
		final Pelicula pelicula = new Pelicula(cant, cod, nom, tipos, descrip, est, anio, pArr, pVent);
		this.agregar(pelicula);
	}

	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see clases.ConjuntoProducto#generarReporte()
	 */
	@Override
	public boolean generarReporte(String directorio){
		String master = "src/archivos/reportePeliculas.jasper";
        final ArrayList<ProductoVideoClub> peliculas=this.clonarListaProducto();
        try {
        	JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(peliculas);
            JasperPrint informe = JasperFillManager.fillReport(master, null,ds);
            JasperExportManager.exportReportToPdfFile(informe,directorio+"/Reporte Películas CubiHoyts "+new Date(Calendar.getInstance().getTimeInMillis()).toString()+".pdf");
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

}
