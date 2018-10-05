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
 * Posee m�todos para manejar el procesamiento de datos de un HashSet con
 * referencias a {@link Pelicula}
 * 
 * @author Constanza Cerda
 *
 */
public class ConjuntoPelicula extends ConjuntoProducto {

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor sin par�metros:
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
	 * M�todo que dimensiona un nuevo objeto {@link Pelicula} con todos los datos que recibe
	 * por par�metro y lo agrega al HashSet de Producto.
	 *
	 * @param cant
	 *            Cantidad de la pel�cula (int).
	 * @param cod
	 *            C�digo de la pel�cula (int).
	 * @param nom
	 *            Nombre de la pel�cula (String).
	 * @param tipo
	 *            Tipos de la pel�cula (Array).
	 * @param descrip
	 *            Descripci�n de la pel�cula (String).
	 * @param est
	 *            La pel�cula es estreno (boolean).
	 * @param anio
	 *            A�o estreno de la pel�cula (int).
	 * @param pArr
	 *            Precio arriendo de la pel�cula (int).
	 * @param pVent
	 *            Precio venta de la pel�cula (int).
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
            JasperExportManager.exportReportToPdfFile(informe,directorio+"/Reporte Pel�culas CubiHoyts "+new Date(Calendar.getInstance().getTimeInMillis()).toString()+".pdf");
        }
        catch(JRException e) {
        	return false;
        }
        catch(NullPointerException e1) {
        	JOptionPane.showMessageDialog(null, "�No existe informaci�n para generar el reporte!","Error",JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        catch(JRRuntimeException e2) {
        	return false;
        }
        return true;
	}

}
