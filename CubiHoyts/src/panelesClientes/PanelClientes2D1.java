package panelesClientes;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Cliente;
import clases.Historial;

public class PanelClientes2D1 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton historial;
	public JLabel nombreCliente;
	public JLabel rutCliente;
	public JLabel dirCliente;
	public JLabel tipoCliente;
	public JLabel penalizacion;
	public JLabel arrSeguidos;
	public JLabel cantArriendos;
	public JLabel cantVentas;
	public JTextField campoNombreCliente;
	public JTextField campoRutCliente;
	public JTextField campoDirCliente;
	public JTextField campoTipoCliente;
	public JTextField campoPenalizacion;
	public JTextField campoArrSeguidos;
	public JTextField campoCantidadArriendos;
	public JTextField campoCantidadVentas;
	public PanelClientes2D1(Cliente cliente,Historial hist) {
		setSize(800,600);
		setLayout(null);
		nombreCliente=new JLabel("Nombre del cliente: ");
		rutCliente=new JLabel("RUT del cliente: ");
		dirCliente=new JLabel("Direccion del cliente: ");
		tipoCliente=new JLabel("Tipo del cliente: ");
		penalizacion=new JLabel("N° penalizaciones: ");
		arrSeguidos=new JLabel("Arriendos seguidos: ");
		cantArriendos=new JLabel("Cantidad Arriendos: ");
		cantVentas=new JLabel("Cantidad ventas: ");
		campoNombreCliente=new JTextField(cliente.getNombreCliente());
		campoNombreCliente.setEditable(false);
		campoRutCliente=new JTextField(cliente.getRut());
		campoRutCliente.setEditable(false);
		campoDirCliente=new JTextField(cliente.getDireccion());
		campoDirCliente.setEditable(false);
		campoTipoCliente=new JTextField(Integer.toString(cliente.getTipoCliente()));
		campoTipoCliente.setEditable(false);
		campoPenalizacion=new JTextField(Integer.toString(hist.getPenalizacion()));
		campoPenalizacion.setEditable(false);
		campoArrSeguidos=new JTextField(Integer.toString(hist.getArriendosSeguidos()));
		campoArrSeguidos.setEditable(false);
		campoCantidadArriendos=new JTextField(Integer.toString(hist.getNumArriendos()));
		campoCantidadArriendos.setEditable(false);
		campoCantidadVentas=new JTextField(Integer.toString(hist.getNumVentas()));
		campoCantidadVentas.setEditable(false);
		historial=new JButton("Historial cliente");
		add(nombreCliente);
		add(rutCliente);
		add(dirCliente);
		add(tipoCliente);
		add(campoNombreCliente);
		add(campoRutCliente);
		add(campoDirCliente);
		add(campoTipoCliente);
		add(penalizacion);
		add(arrSeguidos);
		add(cantArriendos);
		add(cantVentas);
		add(campoPenalizacion);
		add(campoArrSeguidos);
		add(campoCantidadArriendos);
		add(campoCantidadVentas);
		add(historial);
		nombreCliente.setBounds(100,180,150,15);
		rutCliente.setBounds(100,220,150,15);
		dirCliente.setBounds(100, 260, 150, 15);
		tipoCliente.setBounds(100, 300, 150, 15);
		campoNombreCliente.setBounds(250, 180, 275, 25);
		campoRutCliente.setBounds(250, 220, 150, 25);
		campoDirCliente.setBounds(250, 260, 275, 25);
		campoTipoCliente.setBounds(250, 300, 150, 25);
		penalizacion.setBounds(100, 340, 150, 15);
		arrSeguidos.setBounds(100, 380, 150, 15);
		cantArriendos.setBounds(100, 420, 150, 15);
		cantVentas.setBounds(100, 460, 150, 15);
		campoPenalizacion.setBounds(250,340,150,25);
		campoArrSeguidos.setBounds(250, 380, 150, 25);
		campoCantidadArriendos.setBounds(250, 420, 150, 25);
		campoCantidadVentas.setBounds(250, 460, 150, 25);
		historial.setBounds(550, 450, 200, 100);
	}
}
