package panelesNegocio;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.ProductoVideoClub;

public class PanelNegocios3A1 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JLabel tituloPel;
	public JLabel tituloJug;
	public JButton siguiente;
	public JLabel idPelicula;
	public JFormattedTextField campoIdPelicula;
	public JTable tabla;
	public DefaultTableModel modelo;
	public JScrollPane tablaScroll;
	public JTable tabla2;
	public DefaultTableModel modelo2;
	public JScrollPane tablaScroll2;
	
	public PanelNegocios3A1(ArrayList<ProductoVideoClub> pelis,ArrayList<ProductoVideoClub> juegos) {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Venta/arriendo de productos");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 175, 300, 25);
		tituloPel=new JLabel("Películas disponibles");
		tituloPel.setFont(new Font("Tahoma",0,18));
		add(tituloPel);
		tituloPel.setBounds(100,210,200,25);
		tituloJug=new JLabel("Juegos disponibles");
		tituloJug.setFont(new Font("Tahoma",0,18));
		add(tituloJug);
		tituloJug.setBounds(100,385,200,25);
		
		siguiente=new JButton("Siguiente =>");
		idPelicula=new JLabel("Código del producto: ");
		campoIdPelicula=new JFormattedTextField();
		add(siguiente);
		add(idPelicula);
		add(campoIdPelicula);
		siguiente.setBounds(550, 450, 200, 100);
		idPelicula.setBounds(475,350,150,25);
		campoIdPelicula.setBounds(600,350,150,25);
		
		
		modelo=new DefaultTableModel();
		tabla=new JTable(modelo);
		String columnas[]= {"Nombre producto","Código"};
		modelo.setColumnIdentifiers(columnas);
		tabla.setEnabled(false);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
		for(int i=0;i<pelis.size();i++) {
			Object row[]= {
					pelis.get(i).getNombre(),
					pelis.get(i).getCodigo()
			};
			modelo.addRow(row);
		}
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(100, 235, 300,125);
		add(tablaScroll);
		
		
		modelo2=new DefaultTableModel();
		tabla2=new JTable(modelo2);
		modelo2.setColumnIdentifiers(columnas);
		tabla2.setEnabled(false);
		tabla2.getColumnModel().getColumn(0).setPreferredWidth(200);
		for(int i=0;i<juegos.size();i++) {
			Object row[]= {
					juegos.get(i).getNombre(),
					juegos.get(i).getCodigo()
			};
			modelo2.addRow(row);
		}
		tablaScroll2=new JScrollPane(tabla2);
		tablaScroll2.setBounds(100, 410, 300,125);
		add(tablaScroll2);
	}	
}
