package panelesProductos;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.ProductoVideoClub;
import utilidades.ButtonCellEditor;
import utilidades.ButtonCellRenderer;

public class PanelProductosPB extends JPanel{
	
	public JTable tabla;
	public DefaultTableModel modelo;
	public JButton buscar;
	public JButton agregar;
	public JButton reporte;
	public JButton categorias;
	public JScrollPane tablaScroll;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PanelProductosPB(ArrayList<ProductoVideoClub> pelis) {
		this.setSize(800, 600);
		this.setLayout(null);
		
		modelo=new DefaultTableModel();
		agregar=new JButton ("Agregar");
		buscar=new JButton ("Buscar");
		reporte=new JButton ("Reporte");
		categorias=new JButton ("Categorías");
		add(agregar);
		add(buscar);
		add(reporte);
		add(categorias);
		agregar.setBounds(50, 500, 125, 50);
		buscar.setBounds(225, 500, 125, 50);
		reporte.setBounds(625,500,125,50);
		categorias.setBounds(450,500,125,50);
		tabla=new JTable(modelo);
		tabla.setDefaultEditor(Object.class,new ButtonCellEditor());
		String columnas[]= {"Nombre","Código","Precio arriendo","Precio venta","Datos extra","Modificar","Eliminar"};
		modelo.setColumnIdentifiers(columnas);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(175);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(25);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(25);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(25);
		
		for(int i=4;i<7;i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(new ButtonCellRenderer());
		}
		
		for(int i=0;i<pelis.size();i++) {
			JButton d=new JButton("Datos");
			JButton m=new JButton("Modificar");
			JButton e=new JButton("Eliminar");
			Object row[]= {pelis.get(i).getNombre(),pelis.get(i).getCodigo(),pelis.get(i).getPrecioArriendo(),pelis.get(i).getPrecioVenta(),d,m,e};
			modelo.addRow(row);
		}
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(50, 180, 700,300);
		add(tablaScroll);
	}
	
}
