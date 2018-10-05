package panelesPeliculas;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class PanelPeliculas1C extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton agregarPelicula;
	public JList<String> listaTipos;
	public JLabel nombrePelicula;
	public JLabel codigoPelicula;
	public JLabel cantidadPelicula;
	public JLabel tiposPelicula;
	public JLabel descripcionPelicula;
	//JLabel esEstreno;
	public JLabel anoEstreno;
	public JLabel precioArriendo;
	public JLabel precioVenta;
	public JTextField campoNombrePelicula;
	public JTextField campoCodigoPelicula;
	public JTextField campoCantidadPelicula;
	public JTextArea campoTiposPelicula;
	public JTextArea campodescripcionPelicula;
	public JCheckBox campoEsEstreno;
	public JTextField campoAnoEstreno;
	public JTextField campoPrecioArriendo;
	public JTextField campoPrecioVenta;
	
	public PanelPeliculas1C() {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Agregar película");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 175, 200, 25);
		setLabels();
		setCampos();
		agregarPelicula=new JButton("Agregar pelicula");
		add(agregarPelicula);
		agregarPelicula.setBounds(550, 450, 200, 100);
	}

	public void setLabels() {
		nombrePelicula=new JLabel("Nombre de la pelicula: ");
		codigoPelicula=new JLabel("Codigo: ");
		cantidadPelicula=new JLabel("Cantidad: ");
		tiposPelicula=new JLabel("Generos: ");
		descripcionPelicula=new JLabel("Descripcion: ");
		//esEstreno=new JLabel("Estreno: ");
		anoEstreno=new JLabel("Año estreno: ");
		precioArriendo=new JLabel("Precio Arriendo: ");
		precioVenta=new JLabel("Precio Venta: ");
		add(nombrePelicula);
		add(codigoPelicula);
		add(cantidadPelicula);
		add(tiposPelicula);
		add(descripcionPelicula);
		//add(esEstreno);
		add(anoEstreno);
		add(precioArriendo);
		add(precioVenta);
		nombrePelicula.setBounds(100, 200, 1000, 11);
		codigoPelicula.setBounds(100, 250, 1000, 11);
		cantidadPelicula.setBounds(100, 300, 1000, 11);
		tiposPelicula.setBounds(100, 350, 1000, 11);
		descripcionPelicula.setBounds(100, 400, 1000, 11);
		//esEstreno.setBounds(450, 200, 1000, 11);
		anoEstreno.setBounds(450, 250, 1000, 11);
		precioArriendo.setBounds(450, 300, 1000, 11);
		precioVenta.setBounds(450, 350, 1000, 11);
	}
public void setCampos() {
		
		campoNombrePelicula=new JTextField();
		campoCodigoPelicula=new JTextField();
		campoCantidadPelicula=new JTextField();
		campoTiposPelicula=new JTextArea(4,22);
		campoTiposPelicula.setLineWrap(true);
		campoTiposPelicula.setWrapStyleWord(true);
		campoTiposPelicula.setEditable(true);
		setList();
		JScrollPane paneTipos=new JScrollPane(listaTipos,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		campodescripcionPelicula=new JTextArea();
		campodescripcionPelicula.setLineWrap(true);
		campodescripcionPelicula.setWrapStyleWord(true);
		campodescripcionPelicula.setEditable(true);
		JScrollPane paneDescripcion=new JScrollPane(campodescripcionPelicula,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		campoEsEstreno=new JCheckBox("Estreno");
		campoAnoEstreno=new JTextField();
		campoPrecioArriendo=new JTextField();
		campoPrecioVenta=new JTextField();
		add(campoNombrePelicula);
		add(campoCodigoPelicula);
		add(campoCantidadPelicula);
		add(paneTipos);
		add(paneDescripcion);
		add(campoEsEstreno);
		add(campoAnoEstreno);
		add(campoPrecioArriendo);
		add(campoPrecioVenta);
		campoNombrePelicula.setBounds(250,200,150,25);
		campoNombrePelicula.setEditable(true);
		campoCodigoPelicula.setBounds(250,250,150,25);
		campoCodigoPelicula.setEditable(true);
		campoCantidadPelicula.setBounds(250,300,150,25);
		campoCantidadPelicula.setEditable(true);
		paneTipos.setBounds(250,350,150,50);
		paneDescripcion.setBounds(100,425,300,125);
		campoEsEstreno.setBounds(600,210,150,25);
		campoAnoEstreno.setBounds(600,250,150,25);
		campoAnoEstreno.setEditable(true);
		campoPrecioArriendo.setBounds(600,300,150,25);
		campoPrecioArriendo.setEditable(true);
		campoPrecioVenta.setBounds(600,350,150,25);
		campoPrecioVenta.setEditable(true);
		
	}
	public void setList() {
		String[] data= {"Accion","Romance","Suspenso","Horror","Deportes","Infantil y familiares","Dramas","Documentales","Ciencia ficcion y fantasia","Comedias","Independientes"};
		listaTipos= new JList<String>(data);
	}
	
	
}