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
 * Clase ListaVenta:
 *
 * Clase que extiende la clase abstracta {@link ListaNegocio}
 *
 * Posee métodos para manejar el procesamiento de datos de un ArrayList con
 * referencias a {@link Venta}.
 * 
 * @author Benjamín Herrera.
 *
 */
public class ListaVenta extends ListaNegocio{

	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor sin parámetros:
	 *
	 * Constructor que llama al constructo de ListaNegocio,
	 * el cual dimensiona el ArrayList de Negocio que posee
	 * como atributo.
	 *
	 */
	public ListaVenta() {
		super();
	}
	public ListaVenta(ArrayList<Negocio> negocios) {
		super(negocios);
	}

	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see clases.ListaNegocio#cantidadNegociosPorMes(int)
	 */
	@Override
	public int cantidadNegociosPorMes(final int month) {
		int cont = 0;
		for (int i = 0; i < this.cantidadNegocios(); i++) {
			final Calendar fechaVenta = ((Venta) this.buscarNegocio(i)).getFechaVenta();
			if (fechaVenta.get(Calendar.MONTH) == month)
				cont++;
		}
		return cont;
	}


	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see clases.ListaNegocio#gananciaTotalPorDia(java.util.Calendar)
	 */
	@Override
	public int gananciaTotalPorDia(final Calendar fecha) {
		int suma = 0;
		for (int i = 0; i < this.cantidadNegocios(); i++) {
			final Calendar fechaVenta = ((Venta) this.buscarNegocio(i)).getFechaVenta();
			if ((fechaVenta.get(Calendar.DATE) == fecha.get(Calendar.DATE))
					&& (fechaVenta.get(Calendar.MONTH) == fecha.get(Calendar.MONTH))
					&& (fechaVenta.get(Calendar.YEAR) == fecha.get(Calendar.YEAR)))
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
		String master = "src/archivos/reporteVentas.jasper";
        final ArrayList<Negocio> ventas=this.clonarListaNegocios();
        try {
        	JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(ventas);
            JasperPrint informe = JasperFillManager.fillReport(master, null,ds);
            JasperExportManager.exportReportToPdfFile(informe,directorio+"/Reporte Ventas CubiHoyts "+new Date(Calendar.getInstance().getTimeInMillis()).toString()+".pdf");
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
		final ListaNegocio nuevo =Creator.crearLista(Creator.crearListaVenta);
		for (int i = 0; i < this.cantidadNegocios(); i++)
			if (this.buscarNegocio(i).getRutCliente().equals(rut))
				nuevo.agregarNegocio(this.buscarNegocio(i));
		return nuevo;
	}

}
