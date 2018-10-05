package panelesClientes;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Negocio;
import clases.Venta;

public class PanelClientesVentaPB extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTable tabla;
	public DefaultTableModel modelo;
	public JScrollPane tablaScroll;
	
	public PanelClientesVentaPB(ArrayList<Negocio> ven) {
		this.setSize(800, 600);
		this.setLayout(null);
		modelo=new DefaultTableModel();
		tabla=new JTable(modelo);
		String columnas[]= {"Nombre producto","Código venta","Ganancia","Fecha","Cancelado"};
		modelo.setColumnIdentifiers(columnas);
		//tabla.getColumnModel().getColumn(0).setPreferredWidth(125);
		//tabla.getColumnModel().getColumn(1).setPreferredWidth(125);
		tabla.setEnabled(false);
		for(int i=0;i<ven.size();i++) {
			String opcion;
			if(((Venta) ven.get(i)).isVentaCancelada())
				opcion="SI";
			else
				opcion="NO";
			
			Object row[]= {
					ven.get(i).getNombreProducto(),
					ven.get(i).getCodigo(),
					ven.get(i).getGanancia(),
					new Date (((Venta) ven.get(i)).getFechaVenta().getTimeInMillis()).toString(),
					opcion
			};
			modelo.addRow(row);
		}
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(50, 180, 700,300);
		add(tablaScroll);
	}
		
}
