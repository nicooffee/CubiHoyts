package panelesClientes;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelClientes2C extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton buscarCliente;
	public JLabel rutCliente;
	public JFormattedTextField campoRutCliente;
	
	public PanelClientes2C() {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Buscar cliente");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 200, 200, 25);
		buscarCliente=new JButton("Buscar cliente");
		rutCliente=new JLabel("RUT del cliente: ");
		campoRutCliente=new JFormattedTextField();
		add(buscarCliente);
		add(rutCliente);
		add(campoRutCliente);
		buscarCliente.setBounds(550, 450, 200, 100);
		rutCliente.setBounds(100,275,150,25);
		campoRutCliente.setBounds(250,275,150,25);
	}
}