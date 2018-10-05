package panelesClientes;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Cliente;
import utilidades.ButtonCellEditor;
import utilidades.ButtonCellRenderer;

public class PanelClientesPB extends JPanel{
	
	public JTable tabla;
	public DefaultTableModel modelo;
	public JButton buscar;
	public JButton agregar;
	public JButton reporte;
	public JScrollPane tablaScroll;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelClientesPB(ArrayList<Cliente> clis) {
		this.setSize(800, 600);
		this.setLayout(null);
		
		modelo=new DefaultTableModel();
		agregar=new JButton ("Agregar");
		buscar=new JButton ("Buscar");
		reporte=new JButton ("Reporte");
		add(agregar);
		add(buscar);
		add(reporte);
		agregar.setBounds(50, 500, 200, 50);
		buscar.setBounds(300, 500, 200, 50);
		reporte.setBounds(550,500,200,50);
		tabla=new JTable(modelo);
		tabla.setDefaultEditor(Object.class,new ButtonCellEditor());
		String columnas[]= {"Nombre","Rut","Dirección","Tipo","Datos extra","Modificar","Eliminar"};
		modelo.setColumnIdentifiers(columnas);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(10);
		for(int i=4;i<7;i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(new ButtonCellRenderer());
		}
		
		for(int i=0;i<clis.size();i++) {
			JButton d=new JButton("Datos");
			JButton m=new JButton("Modificar");
			JButton e=new JButton("Eliminar");
			Object row[]= {clis.get(i).getNombreCliente(),clis.get(i).getRut(),clis.get(i).getDireccion(),clis.get(i).getTipoCliente(),d,m,e};
			modelo.addRow(row);
		}
		tabla.getColumnModel().getColumn(4).setCellRenderer(new ButtonCellRenderer());
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(50, 180, 700,300);
		add(tablaScroll);
		
	}
}
