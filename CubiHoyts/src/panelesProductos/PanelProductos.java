package panelesProductos;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelProductos extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton juegos;
	public JButton peliculas;
	
	public PanelProductos() {
		titulo=new JLabel("Tipos de productos");
		titulo.setFont(new Font("Tahoma",0,24));
		add(titulo);
		titulo.setBounds(70, 175, 350, 25);
		setSize(800,600);
		setLayout(null);
		juegos=new JButton("Juegos");
		peliculas=new JButton("Películas");
		juegos.setFont(new Font("Times",0,20));
		peliculas.setFont(new Font("Times",0,20));
		add(juegos);
		add(peliculas);
		juegos.setBounds(75, 250, 300, 250);
		peliculas.setBounds(425, 250, 300, 250);
	}
}
