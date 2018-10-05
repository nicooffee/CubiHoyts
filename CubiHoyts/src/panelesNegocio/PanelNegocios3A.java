package panelesNegocio;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelNegocios3A extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton siguiente;
	public JLabel rutCliente;
	public JFormattedTextField campoRutCliente;
	
	public PanelNegocios3A() {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Venta/arriendo de productos");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 200, 250, 25);
		siguiente=new JButton("Siguiente =>");
		rutCliente=new JLabel("RUT del cliente: ");
		campoRutCliente=new JFormattedTextField();
		add(siguiente);
		add(rutCliente);
		add(campoRutCliente);
		siguiente.setBounds(550, 450, 200, 100);
		rutCliente.setBounds(100,275,150,25);
		campoRutCliente.setBounds(250,275,150,25);
	}
}
