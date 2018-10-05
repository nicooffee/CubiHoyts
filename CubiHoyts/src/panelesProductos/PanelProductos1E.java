package panelesProductos;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelProductos1E extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton buscarProducto;
	public JLabel codigoProducto;
	public JFormattedTextField campoCodigoProducto;
	
	public PanelProductos1E() {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Buscar película");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 175, 200, 25);
		buscarProducto=new JButton("Buscar Pelicula");
		codigoProducto=new JLabel("Codigo de la pelicula: ");
		campoCodigoProducto=new JFormattedTextField();
		add(buscarProducto);
		add(codigoProducto);
		add(campoCodigoProducto);
		buscarProducto.setBounds(550, 450, 200, 100);
		codigoProducto.setBounds(100,275,150,25);
		campoCodigoProducto.setBounds(250,275,150,25);
	}
}
