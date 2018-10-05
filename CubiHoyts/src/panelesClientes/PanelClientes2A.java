package panelesClientes;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelClientes2A extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JLabel nombreCliente;
	public JLabel rutCliente;
	public JLabel dirCliente;
	public JTextField campoNombreCliente;
	public JTextField campoRutCliente;
	public JTextField campoDirCliente;
	public JButton guardar;
	public PanelClientes2A() {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Agregar cliente");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 200, 400, 25);
		nombreCliente=new JLabel("Nombre del cliente: ");
		rutCliente=new JLabel("RUT del cliente: ");
		dirCliente=new JLabel("Direccion del cliente: ");
		campoNombreCliente=new JTextField();
		campoRutCliente=new JTextField();
		campoDirCliente=new JTextField();
		guardar=new JButton("Guardar cliente");
		add(nombreCliente);
		add(rutCliente);
		add(dirCliente);
		add(campoNombreCliente);
		add(campoRutCliente);
		add(campoDirCliente);
		add(guardar);
		nombreCliente.setBounds(100,230,150,15);
		rutCliente.setBounds(100,280,150,15);
		dirCliente.setBounds(100, 330, 150, 15);
		campoNombreCliente.setBounds(250, 230, 150, 25);
		campoRutCliente.setBounds(250, 280, 150, 25);
		campoDirCliente.setBounds(250, 330, 150, 25);
		guardar.setBounds(550, 450, 200, 100);
	}
}
