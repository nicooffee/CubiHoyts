package panelesPeliculas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import clases.Pelicula;
import clases.ProductoVideoClub;
import utilidades.ButtonCellEditor;
import utilidades.JScrollPaneCellRenderer;

public class PanelPeliculas1AXPC extends JPanel implements Observer,ActionListener{
	public JTable tabla;
	public JLabel generos;
	public DefaultTableModel modelo;
	public JScrollPane tablaScroll;
	public JComboBox<String> comboGeneros;
	private PeliculasObservadas array;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PanelPeliculas1AXPC(PeliculasObservadas arrayObs) {
		this.setSize(800, 600);
		this.setLayout(null);
		array=arrayObs;
		String[] dataG= {"Todas","Accion","Romance","Suspenso","Horror","Deportes","Infantil y familiares","Dramas","Documentales","Ciencia ficcion y fantasia","Comedia","Independientes"};
		ArrayList<ProductoVideoClub> pelis=array.getProductos();
		comboGeneros=new JComboBox<String>(dataG);
		generos=new JLabel("Seleccionar genero: ");
		add(comboGeneros);
		add(generos);
		comboGeneros.setBounds(200, 500, 100, 25);
		comboGeneros.addActionListener(this);
		generos.setBounds(50,500, 150, 25);
		modelo=new DefaultTableModel();
		tabla=new JTable(modelo);
		tabla.setDefaultEditor(Object.class,new ButtonCellEditor());
		String columnas[]= {"Nombre","Código","C","Géneros","Descripción","Estreno","Año","PArr.","PVen."};
		modelo.setColumnIdentifiers(columnas);
		tabla.setRowHeight(75);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(25);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(175);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
		tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(7).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(3).setCellRenderer(new JScrollPaneCellRenderer());
		tabla.getColumnModel().getColumn(4).setCellRenderer(new JScrollPaneCellRenderer());
		for(int i=0;i<pelis.size();i++) {
			JList<String> listaTipos= new JList<String>((pelis.get(i).getGeneros()).toArray(new String[pelis.get(i).getGeneros().size()]));
			JTextArea descripcion=new JTextArea(pelis.get(i).getDescripcion());
			descripcion.setLineWrap(true);
			descripcion.setWrapStyleWord(true);
			descripcion.setEditable(false);
			JScrollPane paneGeneros=new JScrollPane(listaTipos,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JScrollPane paneDescripcion=new JScrollPane(descripcion,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			String op="NO";
			if(((Pelicula) pelis.get(i)).isEsEstreno())
				op="SI";
			Object row[]= {pelis.get(i).getNombre(),pelis.get(i).getCodigo(),pelis.get(i).getCantidad(),paneGeneros,paneDescripcion,op,((Pelicula) pelis.get(i)).getAnoEstreno(),pelis.get(i).getPrecioArriendo(),pelis.get(i).getPrecioVenta()};
			modelo.addRow(row);
		}
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(25, 180, 750,300);
		add(tablaScroll);
	}
	
	
	
	
	
	
	
	
	
	
	public void setGenero(String genero) {
		array.filtrarArray(genero);
	}










	@Override
	public void update(Observable arg0, Object arg1) {
		ArrayList<ProductoVideoClub> pelis=array.getProductos();
		for(int i=modelo.getRowCount()-1;i>=0;i--) {
			modelo.removeRow(i);
		}
		for(int i=0;i<pelis.size();i++) {
			JList<String> listaTipos= new JList<String>((pelis.get(i).getGeneros()).toArray(new String[pelis.get(i).getGeneros().size()]));
			JTextArea descripcion=new JTextArea(pelis.get(i).getDescripcion());
			descripcion.setLineWrap(true);
			descripcion.setWrapStyleWord(true);
			descripcion.setEditable(false);
			JScrollPane paneGeneros=new JScrollPane(listaTipos,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JScrollPane paneDescripcion=new JScrollPane(descripcion,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			String op="NO";
			if(((Pelicula) pelis.get(i)).isEsEstreno())
				op="SI";
			Object row[]= {pelis.get(i).getNombre(),pelis.get(i).getCodigo(),pelis.get(i).getCantidad(),paneGeneros,paneDescripcion,op,((Pelicula) pelis.get(i)).getAnoEstreno(),pelis.get(i).getPrecioArriendo(),pelis.get(i).getPrecioVenta()};
			modelo.addRow(row);
		}
	}










	@Override
	public void actionPerformed(ActionEvent arg0) {
		array.filtrarArray((String) comboGeneros.getSelectedItem());
		
	}
}
