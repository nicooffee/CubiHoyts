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

import clases.Arriendo;
import clases.Negocio;

public class ReporteArriendo extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JButton reporte;
	public JTable tabla;
	public DefaultTableModel modelo;
	public JScrollPane tablaScroll;
	public JLabel cantidad;
	
	
	public ReporteArriendo(ArrayList<Negocio> arriendos) {
		reporte=new JButton ("Reporte");
		add(reporte);
		reporte.setBounds(625,500,125,50);
		
		cantidad=new JLabel ("Cantidad de arriendos: "+arriendos.size());
		cantidad.setFont(new Font("Tahoma",0,18));
		add(cantidad);
		cantidad.setBounds(50, 500, 300, 25);
		
		this.setSize(800, 600);
		this.setLayout(null);
		modelo=new DefaultTableModel();
		tabla=new JTable(modelo);
		
		String columnas[]= {"RUT cliente","CÓDIGO producto","Nombre producto","Código arriendo","Ganancia","Fecha inicio","Fecha plazo","Arriendo completo"};
		modelo.setColumnIdentifiers(columnas);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(125);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
		tabla.setEnabled(false);
		for(int i=0;i<arriendos.size();i++) {
			String op;
			if(((Arriendo) arriendos.get(i)).isArriendoCompletado())
				op="SI";
			else
				op="NO";
			Object row[]= 
					{arriendos.get(i).getRutCliente(),
						arriendos.get(i).getCodigoProducto(),
						arriendos.get(i).getNombreProducto(),
						arriendos.get(i).getCodigo(),
						arriendos.get(i).getGanancia(),
					new Date (((Arriendo) arriendos.get(i)).getFechaInicio().getTimeInMillis()).toString(),
					new Date (((Arriendo) arriendos.get(i)).getFechaEntrega().getTimeInMillis()).toString(),
					op};
				
			modelo.addRow(row);
		}
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(50, 180, 700,300);
		add(tablaScroll);
	}
	
}
