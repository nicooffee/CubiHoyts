package clases;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import factory.Creator;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Clase ListaArriendo:
 *
 * Clase que extiende la clase abstracta {@link ListaNegocio}
 *
 * Posee métodos para manejar el procesamiento de datos de un ArrayList con
 * referencias a {@link Arriendo}.
 * 
 * @author Benjamín Herrera
 *
 */
public class ListaArriendo extends ListaNegocio {

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor sin parámetros:
	 *
	 * Constructor que llama al constructo de ListaNegocio,
	 * el cual dimensiona el ArrayList de Negocio que posee
	 * como atributo.
	 *
	 */
	public ListaArriendo() {
		super();
	}
	public ListaArriendo(ArrayList<Negocio> negocios) {
		super(negocios);
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadArriendosCompletados:
	 *
	 * Método que cuenta los arriendos completados dentro del ArrayList.
	 *
	 * @return La cantidad de arriendos completados.
	 */
	public int cantidadArriendosCompletados() {
		int cont = 0;
		for (int i = 0; i < this.cantidadNegocios(); i++)
			if (((Arriendo) this.buscarNegocio(i)).isArriendoCompletado())
				cont++;
		return cont;
	}

	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see clases.ListaNegocio#cantidadNegociosPorMes(int)
	 */
	@Override
	public int cantidadNegociosPorMes(final int month) {
		int cont = 0;
		for (int i = 0; i < this.cantidadNegocios(); i++) {
			final Calendar fechaArriendo = ((Arriendo) this.buscarNegocio(i)).getFechaInicio();
			if (fechaArriendo.get(Calendar.MONTH) == month)
				cont++;
			}
		return cont;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * devolverArriendo:
	 * 
	 * Método que busca un arriendo y luego llama a {@link Arriendo#devolver()}.
	 * @param cod
	 * 				Código del arriendo.
	 * @return
	 * 				El arriendo devuelto.
	 */
	public Arriendo devolverArriendo(String cod) {
		Arriendo arr=(Arriendo) this.buscarNegocio(cod);
		arr.devolver();
		return arr;
	}


	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see clases.ListaNegocio#gananciaTotalPorDia(java.util.Calendar)
	 */
	public int gananciaTotalPorDia(final Calendar fecha) {
		int suma = 0;
		for (int i = 0; i < this.cantidadNegocios(); i++) {
			final Calendar fechaArriendo = ((Arriendo) this.buscarNegocio(i)).getFechaInicio();
			if ((fechaArriendo.get(Calendar.DATE) == fecha.get(Calendar.DATE))
					&& (fechaArriendo.get(Calendar.MONTH) == fecha.get(Calendar.MONTH))
					&& (fechaArriendo.get(Calendar.YEAR) == fecha.get(Calendar.YEAR)))
				suma += this.buscarNegocio(i).getGanancia();
		}
		return suma;
	}

	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see utilidades.Exportable#generarReporte()
	 */
	@Override
	public boolean generarReporte(String directorio){
		String master = "src/archivos/reporteArriendos.jasper";
        final ArrayList<Negocio> arriendos=this.clonarListaNegocios();
        try {
        	JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(arriendos);
            JasperPrint informe = JasperFillManager.fillReport(master, null,ds);
            JasperExportManager.exportReportToPdfFile(informe,directorio+"/Reporte Arriendos CubiHoyts "+new Date(Calendar.getInstance().getTimeInMillis()).toString()+".pdf");
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

	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see clases.ListaNegocio#listaNegociosPorRut(java.lang.String)
	 */
	@Override
	public ListaNegocio listaNegociosPorRut(final String rut) {
		final ListaNegocio nuevo = Creator.crearLista(Creator.crearListaArriendo);
		for (int i = 0; i < this.cantidadNegocios(); i++)
			if (this.buscarNegocio(i).getRutCliente().equals(rut))
				nuevo.agregarNegocio(this.buscarNegocio(i));
		return nuevo;
	}
}
