package panelesNegocio;

import java.awt.Font;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Negocio;
import clases.Venta;

public class ReporteVenta extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JLabel cantidad;
	public JButton reporte;
	public JTable tabla;
	public DefaultTableModel modelo;
	public JScrollPane tablaScroll;
	
	
	public ReporteVenta(ArrayList<Negocio> ventas) {
		reporte=new JButton ("Reporte");
		add(reporte);
		reporte.setBounds(625,500,125,50);
		
		cantidad=new JLabel ("Cantidad de ventas: "+ventas.size());
		cantidad.setFont(new Font("Tahoma",0,18));
		add(cantidad);
		cantidad.setBounds(50, 500, 300, 25);
		
		this.setSize(800, 600);
		this.setLayout(null);
		modelo=new DefaultTableModel();
		tabla=new JTable(modelo);
		
		String columnas[]= {"RUT cliente","CÓDIGO producto","Nombre producto","Código venta","Ganancia","Fecha","Cancelado"};
		modelo.setColumnIdentifiers(columnas);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(125);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
		tabla.setEnabled(false);
		for(int i=0;i<ventas.size();i++) {
			String opcion;
			if(((Venta) ventas.get(i)).isVentaCancelada())
				opcion="SI";
			else
				opcion="NO";
			
			Object row[]= {
					ventas.get(i).getRutCliente(),
					ventas.get(i).getCodigoProducto(),
					ventas.get(i).getNombreProducto(),
					ventas.get(i).getCodigo(),
					ventas.get(i).getGanancia(),
					new Date (((Venta) ventas.get(i)).getFechaVenta().getTimeInMillis()).toString(),
					opcion
			};
			modelo.addRow(row);
		}
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(50, 180, 700,300);
		add(tablaScroll);
	}
}
