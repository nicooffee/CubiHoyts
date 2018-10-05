package panelesClientes;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Arriendo;
import clases.Negocio;

public class PanelClientesArriendoPB extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTable tabla;
	public DefaultTableModel modelo;
	public JScrollPane tablaScroll;
	
	public PanelClientesArriendoPB(ArrayList<Negocio> arr) {
		this.setSize(800, 600);
		this.setLayout(null);
		modelo=new DefaultTableModel();
		tabla=new JTable(modelo);
		String columnas[]= {"Nombre producto","Código arriendo","Ganancia","Fecha inicio","Fecha plazo","Arriendo completo"};
		modelo.setColumnIdentifiers(columnas);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(125);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(125);
		//tabla.setEnabled(false);
		for(int i=0;i<arr.size();i++) {
			String op;
			if(((Arriendo) arr.get(i)).isArriendoCompletado())
				op="SI";
			else
				op="NO";
			Object row[]= 
					{arr.get(i).getNombreProducto(),
					arr.get(i).getCodigo(),
					arr.get(i).getGanancia(),
					new Date (((Arriendo) arr.get(i)).getFechaInicio().getTimeInMillis()).toString(),
					new Date (((Arriendo) arr.get(i)).getFechaEntrega().getTimeInMillis()).toString(),
					op};
				
			modelo.addRow(row);
		}
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(50, 180, 700,300);
		add(tablaScroll);
	}
}
