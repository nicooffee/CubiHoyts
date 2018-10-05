package panelesJuegos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import clases.Juego;
import clases.ProductoVideoClub;
import utilidades.ButtonCellEditor;
import utilidades.JScrollPaneCellRenderer;

public class PanelJuegos4DX extends JPanel implements Observer,ActionListener{
	public JTable tabla;
	public JLabel generos;
	public JLabel consolas;
	public JComboBox<String> comboGeneros;
	public JComboBox<String> comboConsolas;
	public DefaultTableModel modelo;
	public JScrollPane tablaScroll;
	private JuegosObservados array;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PanelJuegos4DX(JuegosObservados arrayObs) {
		this.setSize(800, 600);
		this.setLayout(null);
		array=arrayObs;
		String[] dataG= {"Todas","Accion","Aventura","Baile","Baloncesto","Belico","Carreras","Combate","Construccion de imperios","Crossover","Deportes","Estrategia","FPS","Futbol","Horror","Minijuegos","MMORPG","Pelicula interactiva","Plataformas","Rol","Sandbox","Shooter","Sigilo","Simulacion","Simulacion de vida","Simulacion social","Sobrevivencia","Suspenso","TPS"};
		String[] dataC= {"Todas","PS2","PS3","PS4","XBOX360","XBOXONE","WII","WIIU","DS","DS2","DS3","SWITCH","PC","LINUX","OSX"};
		comboGeneros=new JComboBox<String>(dataG);
		comboConsolas=new JComboBox<String>(dataC);
		generos=new JLabel("Seleccionar genero: ");
		consolas=new JLabel("Seleccionar consola: ");
		add(comboGeneros);
		add(comboConsolas);
		add(generos);
		add(consolas);
		comboGeneros.setBounds(200, 500, 100, 25);
		comboGeneros.addActionListener(this);
		comboConsolas.setBounds(500, 500, 100, 25);
		comboConsolas.addActionListener(this);
		generos.setBounds(50,500, 150, 25);
		consolas.setBounds(350,500, 150, 25);
		modelo=new DefaultTableModel();
		tabla=new JTable(modelo);
		tabla.setDefaultEditor(Object.class,new ButtonCellEditor());
		String columnas[]= {"Nombre","Código","C","Géneros","Plataformas","Descripción","Máx. Jug.","Class","PArr.","PVen."};
		modelo.setColumnIdentifiers(columnas);
		tabla.setRowHeight(75);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(25);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(175);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(5).setPreferredWidth(200);
		tabla.getColumnModel().getColumn(6).setPreferredWidth(25);
		tabla.getColumnModel().getColumn(7).setPreferredWidth(25);
		tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(9).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(3).setCellRenderer(new JScrollPaneCellRenderer());
		tabla.getColumnModel().getColumn(4).setCellRenderer(new JScrollPaneCellRenderer());
		tabla.getColumnModel().getColumn(5).setCellRenderer(new JScrollPaneCellRenderer());
		ArrayList<ProductoVideoClub> pelis=array.getProductos();
		for(int i=0;i<pelis.size();i++) {
			JList<String> listaTipos= new JList<String>((pelis.get(i).getGeneros()).toArray(new String[pelis.get(i).getGeneros().size()]));
			JList<String> listaPlataformas= new JList<String>((((Juego) pelis.get(i)).getConsolas()).toArray(new String[pelis.get(i).getGeneros().size()]));
			JTextArea descripcion=new JTextArea(pelis.get(i).getDescripcion());
			descripcion.setLineWrap(true);
			descripcion.setWrapStyleWord(true);
			descripcion.setEditable(false);
			JScrollPane paneGeneros=new JScrollPane(listaTipos,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JScrollPane paneConsolas=new JScrollPane(listaPlataformas,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JScrollPane paneDescripcion=new JScrollPane(descripcion,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			String op="N/A";
			if(((Juego) pelis.get(i)).getJugadoresMaximos()!=0)
				op=Integer.toString(((Juego) pelis.get(i)).getJugadoresMaximos());
			Object row[]= {pelis.get(i).getNombre(),pelis.get(i).getCodigo(),pelis.get(i).getCantidad(),paneGeneros,paneConsolas,paneDescripcion,op,((Juego) pelis.get(i)).getClasificacion(),pelis.get(i).getPrecioArriendo(),pelis.get(i).getPrecioVenta()};
			modelo.addRow(row);
		}
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(25, 180, 750,300);
		add(tablaScroll);
	}

	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		array.filtrarArray((String) comboGeneros.getSelectedItem(),(String) comboConsolas.getSelectedItem());
		
	}
	@Override
	public void update(Observable o, Object arg) {
		try {
			ArrayList<ProductoVideoClub> pelis=array.getProductos();
			for(int i=modelo.getRowCount()-1;i>=0;i--) {
				modelo.removeRow(i);
			}
			for(int i=0;i<pelis.size();i++) {
				JList<String> listaTipos= new JList<String>((pelis.get(i).getGeneros()).toArray(new String[pelis.get(i).getGeneros().size()]));
				JList<String> listaPlataformas= new JList<String>((((Juego) pelis.get(i)).getConsolas()).toArray(new String[pelis.get(i).getGeneros().size()]));
				JTextArea descripcion=new JTextArea(pelis.get(i).getDescripcion());
				descripcion.setLineWrap(true);
				descripcion.setWrapStyleWord(true);
				descripcion.setEditable(false);
				JScrollPane paneGeneros=new JScrollPane(listaTipos,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				JScrollPane paneConsolas=new JScrollPane(listaPlataformas,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				JScrollPane paneDescripcion=new JScrollPane(descripcion,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				String op="N/A";
				if(((Juego) pelis.get(i)).getJugadoresMaximos()!=0)
					op=Integer.toString(((Juego) pelis.get(i)).getJugadoresMaximos());
				Object row[]= {pelis.get(i).getNombre(),pelis.get(i).getCodigo(),pelis.get(i).getCantidad(),paneGeneros,paneConsolas,paneDescripcion,op,((Juego) pelis.get(i)).getClasificacion(),pelis.get(i).getPrecioArriendo(),pelis.get(i).getPrecioVenta()};
				modelo.addRow(row);
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "¡No existe películas con esas características!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
