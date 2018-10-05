package panelesClientes;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Cliente;

public class PanelClientes2C1 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton confirmar;
	public JLabel nombreCliente;
	public JLabel rutCliente;
	public JLabel dirCliente;
	public JTextField campoNombreCliente;
	public JTextField campoRutCliente;
	public JTextField campoDirCliente;
	public PanelClientes2C1(Cliente cliente) {
		setSize(800,600);
		setLayout(null);
		nombreCliente=new JLabel("Nombre del cliente: ");
		rutCliente=new JLabel("RUT del cliente: ");
		dirCliente=new JLabel("Direccion del cliente: ");
		campoNombreCliente=new JTextField(cliente.getNombreCliente());
		campoRutCliente=new JTextField(cliente.getRut());
		campoRutCliente.setEditable(false);
		campoDirCliente=new JTextField(cliente.getDireccion());
		confirmar=new JButton("Guardar cliente");
		add(nombreCliente);
		add(rutCliente);
		add(dirCliente);
		add(campoNombreCliente);
		add(campoRutCliente);
		add(campoDirCliente);
		add(confirmar);
		nombreCliente.setBounds(100,225,150,15);
		rutCliente.setBounds(100,275,150,15);
		dirCliente.setBounds(100, 325, 150, 15);
		campoNombreCliente.setBounds(250, 225, 275, 25);
		campoRutCliente.setBounds(250, 275, 150, 25);
		campoDirCliente.setBounds(250, 325, 275, 25);
		confirmar.setBounds(550, 450, 200, 100);
	}
}
