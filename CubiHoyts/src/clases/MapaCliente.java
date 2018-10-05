package clases;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import excepciones.RUTException;
import m�todosGlobales.ModuloOnce;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import utilidades.Exportable;

/**
 * Clase MapaCliente
 *
 * Clase que tiene como �nico atributo un HashMap que guarda objetos {@link Cliente} con
 * clave rut.
 *
 * Posee m�todos para manejar el procesamiento de datos del HashMap.
 *
 * Implementa la Interfaz {@link Exportable} la cual se encarga de exportar
 * los datos de clientes a un archivo pdf.
 *
 * @author Nicol�s Honorato
 *
 */
public class MapaCliente implements Exportable {
	private final HashMap<String, Cliente> mapaClientes; // HashMap de objetos {@link Cliente}

	
	
	
	
	
	
	
	
	
	/**
	 * Constructor mapaClientes:
	 *
	 * Constructor que dimensiona el HashMap de Cliente de la clase.
	 *
	 */
	public MapaCliente() {
		this.mapaClientes = new HashMap<String, Cliente>();
	}
	public MapaCliente(HashMap<String,Cliente> clientes) {
		mapaClientes=clientes;
	}

	
	
	
	
	
	
	
	
	
	/**
	 * agregarCliente:
	 *
	 * M�todo que dimensiona un objeto {@link Cliente} con todos los datos que se le env�an
	 * por par�metro y luego lo agrega al HashMap.
	 *
	 * @param nombre
	 *            Nombre del cliente (String).
	 * @param rut
	 *            RUT del cliente (String).
	 * @param dir
	 *            Direcci�n del cliente (String).
	 * @param tipo
	 *            Tipo del cliente (int).
	 */
	public void agregarCliente(final String nombre, final String rut, final String dir, final int tipo) throws RUTException{
		if(ModuloOnce.comprobar(rut)) {
			final Cliente ptrCliente = new Cliente(nombre, rut, tipo, dir);
			this.mapaClientes.put(ptrCliente.getRut(), ptrCliente);
		}
		else
			throw new RUTException();
	}

	
	
	
	
	
	
	
	
	
	/**
	 * agregarCliente:
	 *
	 * M�todo que recibe los datos de un cliente y dimensiona un objeto {@link Cliente} con
	 * estos, luego lo agrega al HashMap y llama al m�todo {@link Cliente#agregarDatos()}
	 *
	 * @param cli
	 *            Cliente a agregar.
	 */
	public void agregarCliente(Cliente cli)  {
		this.mapaClientes.put(cli.getRut(), cli);
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * buscarCliente (�ndice):
	 *
	 * M�todo que recibe un n�mero entero y busca el objeto que est� en esa posici�n
	 * (transforma el HashMap en un ArrayList).
	 *
	 * @param indice
	 *            �ndice para buscar al cliente.
	 * @return El objeto encontrado o null si no existe.
	 */
	public Cliente buscarCliente(final int indice) {
		try {
			return this.clonarMapaClientes().get(indice);
		} catch (final Exception e) {
			return null;
		}
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * buscarCliente(rut):
	 *
	 * M�todo que recibe un rut y busca al cliente que posea ese rut.
	 *
	 * @param rut
	 *            RUT del cliente (String).
	 * @return Si encuentra el objeto {@link Cliente} lo retorna, de lo contrario retorna
	 *         una nueva instancia de {@link Cliente}.
	 */
	public Cliente buscarCliente(String rut) throws RUTException{
		if(ModuloOnce.comprobar(rut)) {
			rut=rut.replace("-", "");
			rut=rut.replace(".","");
			if (this.mapaClientes.get(rut) == null)
				return new Cliente(rut);
			else
				return this.mapaClientes.get(rut);
		}
		else {
			throw new RUTException();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadClientes:
	 *
	 * M�todo que retorna la cantidad de objetos que posee el HashMap.
	 *
	 * @return Retorna 0 si el HashMap no ha sido dimensionado.
	 */
	public int cantidadClientes() {
		if (this.mapaClientes != null)
			return this.mapaClientes.size();
		return 0;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * clonarMapaClientes:
	 *
	 * M�todo que traspasa los objetos {@link Cliente} del HashMap a un nuevo ArrayList.
	 *
	 * @return Un nuevo ArrayList con los datos de los clientes
	 */
	public ArrayList<Cliente> clonarMapaClientes() {
		final ArrayList<Cliente> clo = new ArrayList<Cliente>();
		clo.addAll(this.mapaClientes.values());
		return clo;
	}
	public HashMap<String,Cliente> clonar(){
		HashMap<String,Cliente> clientes=new HashMap<String,Cliente>();
		clientes.putAll(mapaClientes);
		return clientes;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * contarTipoClientes:
	 *
	 * M�todo que cuenta la cantidad de clientes de un determinado tipo.
	 *
	 * @param tipo
	 *            Tipo a contar (int).
	 * @return La cantidad de clientes con un determinado tipo.
	 */
	public int contarTipoClientes(final int tipo) {
		int cont = 0;
		final ArrayList<Cliente> clientes = this.clonarMapaClientes();
		for (int i = 0; i < clientes.size(); i++) {
			final Cliente auxCliente = clientes.get(i);
			if (auxCliente.getTipoCliente() == tipo)
				cont++;
		}
		return cont;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * eliminarClientePorRut:
	 *
	 * M�todo que recibe una String rut y borra al cliente que posea ese rut en el
	 * hashMap.
	 *
	 * @param rut
	 *            RUT del cliente (String).
	 */
	public void eliminarClientePorRut(final String rut) throws RUTException{
		if(ModuloOnce.comprobar(rut)) {
			if ((rut != null) && (this.mapaClientes != null) && !this.mapaClientes.isEmpty())
				this.mapaClientes.remove(rut);
		}
		else
			throw new RUTException();
				
	}

	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see utilidades.Exportable#generarReporte(java.lang.String)
	 */
	@Override
	public boolean generarReporte(String directorio){
		String master = "src/archivos/reporteClientes.jasper";
        final ArrayList<Cliente> clientes=this.clonarMapaClientes();
        try {
        	JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(clientes);
            JasperPrint informe = JasperFillManager.fillReport(master, null,ds);
            JasperExportManager.exportReportToPdfFile(informe,directorio+"/Reporte Clientes CubiHoyts "+new Date(Calendar.getInstance().getTimeInMillis()).toString()+".pdf");
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


	
	
	
	
	
	
	
	
	
	
	/**
	 * mostrarGraficoTipoCliente:
	 *
	 * M�todo que crea un gr�fico de barras vertical con la cantidad de clientes en
	 * cada tipo.
	 *
	 * @return ChartPanel con el gr�fico.
	 */
	public ChartPanel mostrarGraficoTipoCliente() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < 4; i++)
			dataset.addValue(this.contarTipoClientes(i), "Tipo", "Tipo " + i);
		final JFreeChart chart = ChartFactory.createBarChart("Gr�fico hist�rico de la cantidad de tipos de clientes",
				"Tipo", "Cantidad", dataset, PlotOrientation.VERTICAL, true, true, false);
		final ChartPanel chPanel = new ChartPanel(chart);
		return chPanel;
	}
}
