package panelesProductos;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.ProductoVideoClub;

public class ProductosRecomendados extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JLabel peliculaRec1;
	public JLabel peliculaRec2;
	public JLabel peliculaRec3;
	public JTextField campoPeliculaRec1;
	public JTextField campoPeliculaRec2;
	public JTextField campoPeliculaRec3;
	public JTextField campoCodRec1;
	public JTextField campoCodRec2;
	public JTextField campoCodRec3;
	public ProductosRecomendados(ProductoVideoClub[] pelis) {
		titulo=new JLabel("Productos similares a la comprada/arrendada");
		titulo.setFont(new Font("Tahoma",0,24));
		add(titulo);
		titulo.setBounds(100, 50, 500, 50);
		setSize(640,480);
		setLayout(null);
		peliculaRec1=new JLabel("Producto Recomendado 1: ");
		peliculaRec2=new JLabel("Producto Recomendado 2: ");
		peliculaRec3=new JLabel("Producto Recomendado 3: ");
		campoPeliculaRec1=new JTextField();
		campoPeliculaRec1.setEditable(false);
		campoPeliculaRec2=new JTextField();
		campoPeliculaRec2.setEditable(false);
		campoPeliculaRec3=new JTextField();
		campoPeliculaRec3.setEditable(false);
		campoCodRec1=new JTextField();
		campoCodRec1.setEditable(false);
		campoCodRec2=new JTextField();
		campoCodRec2.setEditable(false);
		campoCodRec3=new JTextField();
		campoCodRec3.setEditable(false);
		add(peliculaRec1);
		add(peliculaRec2);
		add(peliculaRec3);
		add(campoPeliculaRec1);
		add(campoPeliculaRec2);
		add(campoPeliculaRec3);
		add(campoCodRec1);
		add(campoCodRec2);
		add(campoCodRec3);
		if(pelis[0]!=null) {
			campoPeliculaRec1.setText(pelis[0].getNombre());
			campoCodRec1.setText(""+pelis[0].getCodigo());
		}
		if(pelis[1]!=null) {
			campoPeliculaRec2.setText(pelis[1].getNombre());
			campoCodRec2.setText(""+pelis[1].getCodigo());
		}
		if(pelis[0]!=null) {
			campoPeliculaRec3.setText(pelis[2].getNombre());
			campoCodRec3.setText(""+pelis[2].getCodigo());
		}
		peliculaRec1.setBounds(100, 125, 150, 25);
		peliculaRec2.setBounds(100, 200, 150, 25);
		peliculaRec3.setBounds(100, 275, 150, 25);
		campoPeliculaRec1.setBounds(275, 125, 200, 25);
		campoPeliculaRec2.setBounds(275, 200, 200, 25);
		campoPeliculaRec3.setBounds(275, 275, 200, 25);
		campoCodRec1.setBounds(275,160,200,25);
		campoCodRec2.setBounds(275,235,200,25);
		campoCodRec3.setBounds(275,310,200,25);
	}
}
