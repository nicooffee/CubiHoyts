package panelesNegocio;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clases.Arriendo;
import clases.Negocio;

public class PanelNegocios3D extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton devolver;
	//public JLabel rutCliente;
	//public JLabel idPelicula;
	public JLabel codArriendo;
	//public JTextField campoRutCliente;
	//public JTextField campoIdPelicula;
	public JTextField campoCodArriendo;
	public JTable tabla;
	public DefaultTableModel modelo;
	public JScrollPane tablaScroll;
	
	public PanelNegocios3D(ArrayList<Negocio> arriendos) {
		titulo=new JLabel("Registro de devolución");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 200, 250, 25);
		setSize(800,600);
		setLayout(null);
		devolver=new JButton("Devolver película");
		//rutCliente=new JLabel("Ingrese RUT de cliente: ");
		//idPelicula=new JLabel("ID película rentada: ");
		codArriendo=new JLabel("Código arriendo: ");
		//campoRutCliente=new JTextField();
		//campoIdPelicula=new JTextField();
		campoCodArriendo=new JTextField();
		add(devolver);
		//add(rutCliente);
		//add(idPelicula);
		add(codArriendo);
		//add(campoRutCliente);
		//add(campoIdPelicula);
		add(campoCodArriendo);
		devolver.setBounds(550, 450, 200, 100);
		//rutCliente.setBounds(100, 275, 150, 25);
		//idPelicula.setBounds(100, 325, 150, 25);
		codArriendo.setBounds(100, 275, 150, 25);
		//campoRutCliente.setBounds(250, 275, 150, 25);
		//campoIdPelicula.setBounds(250, 325, 150, 25);
		campoCodArriendo.setBounds(250,275,150,25);
		
		
		modelo=new DefaultTableModel();
		tabla=new JTable(modelo);
		String columnas[]= {"Código","Producto","Cliente","Cplto"};
		modelo.setColumnIdentifiers(columnas);
		//tabla.setEnabled(false);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(15);
		for(int i=0;i<arriendos.size();i++) {
			String opcion;
			if(((Arriendo) arriendos.get(i)).isArriendoCompletado())
				opcion="SI";
			else
				opcion="NO";
			Object row[]= {
					arriendos.get(i).getCodigo(),
					arriendos.get(i).getNombreProducto(),
					arriendos.get(i).getRutCliente(),
					opcion
			};
			modelo.addRow(row);
		}
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(100, 400, 425,150);
		add(tablaScroll);
	}
}
