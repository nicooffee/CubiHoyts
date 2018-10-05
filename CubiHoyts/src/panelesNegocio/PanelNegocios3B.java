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

import clases.Negocio;

public class PanelNegocios3B extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton confirmar;
	public JLabel rutCliente;
	public JFormattedTextField campoCodNegocio;
	public JTable tabla;
	public DefaultTableModel modelo;
	public JScrollPane tablaScroll;
	
	public PanelNegocios3B(ArrayList<Negocio> negocios) {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Cancelar negocio");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 200, 250, 25);
		rutCliente=new JLabel("Código del negocio: ");
		campoCodNegocio=new JFormattedTextField();
		confirmar=new JButton("Confirmar");
		add(confirmar);
		add(rutCliente);
		add(campoCodNegocio);
		confirmar.setBounds(550, 450, 200, 100);
		rutCliente.setBounds(100,275,150,25);
		campoCodNegocio.setBounds(250,275,150,25);
		
		
		modelo=new DefaultTableModel();
		tabla=new JTable(modelo);
		String columnas[]= {"Código","Producto","Cliente"};
		modelo.setColumnIdentifiers(columnas);
		//tabla.setEnabled(false);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
		for(int i=0;i<negocios.size();i++) {
			Object row[]= {
				negocios.get(i).getCodigo(),
				negocios.get(i).getNombreProducto(),
				negocios.get(i).getRutCliente()
			};
			modelo.addRow(row);
		}
		tablaScroll=new JScrollPane(tabla);
		tablaScroll.setBounds(100, 400, 425,150);
		add(tablaScroll);
	}
}
