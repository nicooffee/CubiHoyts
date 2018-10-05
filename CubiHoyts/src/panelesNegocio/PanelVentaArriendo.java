package panelesNegocio;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelVentaArriendo extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton registrarVenta;
	public JButton registrarArriendo;
	public JButton gananciaTotal;
	public JButton devolucionArriendo;
	public JButton cancelarNegocio;
	public JButton reporteArriendo;
	public JButton reporteVenta;
	
	public PanelVentaArriendo() {
		setSize(800,600);
		setLayout(null);
		registrarVenta=new JButton("Registrar Venta");
		registrarArriendo=new JButton("Registrar Arriendo");
		gananciaTotal=new JButton("Ganancia Total");
		devolucionArriendo=new JButton("Devolucion Arriendo");
		cancelarNegocio=new JButton("Cancelar Negocio");
		reporteArriendo=new JButton("Reporte arriendo");
		add(reporteArriendo);
		reporteArriendo.setBounds(425, 500, 150, 50);
		reporteVenta=new JButton("Reporte venta");
		add(reporteVenta);
		reporteVenta.setBounds(600, 500, 150, 50);
		add(registrarVenta);
		add(registrarArriendo);
		add(gananciaTotal);
		add(devolucionArriendo);
		add(cancelarNegocio);
		registrarVenta.setBounds(100, 200, 200, 75);
		registrarArriendo.setBounds(100, 300, 200, 75);
		gananciaTotal.setBounds(500, 200, 200, 75);
		devolucionArriendo.setBounds(500, 300, 200, 75);
		cancelarNegocio.setBounds(300, 400, 200, 75);
	}
	

}
