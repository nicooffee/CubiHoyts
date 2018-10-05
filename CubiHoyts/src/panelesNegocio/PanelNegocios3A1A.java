package panelesNegocio;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelNegocios3A1A extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton siguiente;
	public JLabel dias;
	public JFormattedTextField campoDias;
	
	public PanelNegocios3A1A() {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Venta/arriendo de productos");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 200, 300, 25);
		siguiente=new JButton("Siguiente =>");
		dias=new JLabel("Días de arriendo del productos: ");
		campoDias=new JFormattedTextField();
		add(siguiente);
		add(dias);
		add(campoDias);
		siguiente.setBounds(550, 450, 200, 100);
		dias.setBounds(100,275,150,25);
		campoDias.setBounds(250,275,150,25);
	}
}
