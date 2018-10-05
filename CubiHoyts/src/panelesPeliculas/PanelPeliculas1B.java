package panelesPeliculas;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPeliculas1B extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton eliminar;
	public JLabel texto1;
	public JFormattedTextField campo1;
	
	public PanelPeliculas1B() {
		setLayout(null);
		setSize(800,600);
		titulo=new JLabel("Eliminar película");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 200, 200, 25);
		eliminar=new JButton("Eliminar");
		texto1= new JLabel("Ingrede ID pelicula: ");
		campo1=new JFormattedTextField();
		add(eliminar);
		add(texto1);
		add(campo1);
		eliminar.setBounds(550, 450, 200, 100);
		texto1.setBounds(100,275,150,25);
		campo1.setBounds(250,275,150,25);
	}
	
}
