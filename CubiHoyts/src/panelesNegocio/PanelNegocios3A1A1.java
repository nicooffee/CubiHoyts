package panelesNegocio;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelNegocios3A1A1 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton guardar;
	public JLabel monto;
	public JFormattedTextField campoMonto;
	
	public PanelNegocios3A1A1() {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Venta/arriendo de productos");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 200, 300, 25);
		guardar=new JButton("Guardar");
		monto=new JLabel("Monto total a pagar: ");
		campoMonto=new JFormattedTextField();
		campoMonto.setEditable(false);
		add(guardar);
		add(monto);
		add(campoMonto);
		guardar.setBounds(550, 450, 200, 100);
		monto.setBounds(100,275,150,25);
		campoMonto.setBounds(250,275,150,25);
	}
}