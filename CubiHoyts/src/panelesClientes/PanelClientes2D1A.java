package panelesClientes;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelClientes2D1A extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton ventas;
	public JButton arriendos;
	public JLabel cantidadArriendos;
	public JLabel cantidadVentas;
	public PanelClientes2D1A(int cantA,int cantV) {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Historial venta/arriendo de cliente");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 200, 400, 25);
		ventas=new JButton("Ventas");
		arriendos=new JButton("Arriendos");
		cantidadArriendos=new JLabel("Cantidad de arriendos: "+cantA);
		cantidadVentas=new JLabel("Cantidad de ventas: "+cantV);
		add(ventas);
		add(arriendos);
		add(cantidadArriendos);
		add(cantidadVentas);
		ventas.setBounds(100, 250, 200, 100);
		arriendos.setBounds(100, 450, 200, 100);
		cantidadArriendos.setBounds(325, 450, 200, 25);
		cantidadVentas.setBounds(325,250,200,25);
	}
}
