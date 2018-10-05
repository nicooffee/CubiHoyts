package panelesPeliculas;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import clases.Pelicula;

public class PanelPeliculas1B1 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipos;
	public JButton eliminar;
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
	//JTextField campoEsEstreno;
	public JCheckBox campoEsEstreno;
	public JTextField campoAnoEstreno;
	public JTextField campoPrecioArriendo;
	public JTextField campoPrecioVenta;
	
	public PanelPeliculas1B1(Pelicula pel) {
		setLayout(null);
		setSize(800,600);
		setLabels();
		setCampos(pel);
		
	}
	public void setLabels() {
		tipos="";
		eliminar=new JButton("Eliminar Pelicula");
		nombrePelicula=new JLabel("Nombre de la pelicula: ");
		codigoPelicula=new JLabel("Codigo: ");
		cantidadPelicula=new JLabel("Cantidad: ");
		tiposPelicula=new JLabel("Generos: ");
		descripcionPelicula=new JLabel("Descripcion: ");
		//esEstreno=new JLabel("Estreno: ");
		anoEstreno=new JLabel("Año estreno: ");
		precioArriendo=new JLabel("Precio Arriendo: ");
		precioVenta=new JLabel("Precio Venta: ");
		add(eliminar);
		add(nombrePelicula);
		add(codigoPelicula);
		add(cantidadPelicula);
		add(tiposPelicula);
		add(descripcionPelicula);
		//add(esEstreno);
		add(anoEstreno);
		add(precioArriendo);
		add(precioVenta);
		eliminar.setBounds(550,450,200,100);
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
public void setCampos(Pelicula pel) {
		
		campoNombrePelicula=new JTextField(pel.getNombre());
		campoCodigoPelicula=new JTextField(Integer.toString(pel.getCodigo()));
		campoCantidadPelicula=new JTextField(Integer.toString(pel.getCantidad()));
		for(int i=0;i<pel.getGeneros().size();i++) {
			tipos=tipos+"-"+pel.getGeneros().get(i)+"\n";
		}
		campoTiposPelicula=new JTextArea(4,22);
		campoTiposPelicula.setText(tipos);
		campoTiposPelicula.setLineWrap(true);
		campoTiposPelicula.setWrapStyleWord(true);
		campoTiposPelicula.setEditable(false);
		JScrollPane paneTipos=new JScrollPane(campoTiposPelicula,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		campodescripcionPelicula=new JTextArea(pel.getDescripcion());
		campodescripcionPelicula.setLineWrap(true);
		campodescripcionPelicula.setWrapStyleWord(true);
		campodescripcionPelicula.setEditable(false);
		JScrollPane paneDescripcion=new JScrollPane(campodescripcionPelicula,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		//campoEsEstreno=new JTextField(String.valueOf(pel.isEsEstreno()));
		campoEsEstreno=new JCheckBox("Estreno");
		campoEsEstreno.setSelected(pel.isEsEstreno());
		campoAnoEstreno=new JTextField(Integer.toString(pel.getAnoEstreno()));
		campoPrecioArriendo=new JTextField(Integer.toString(pel.getPrecioArriendo()));
		campoPrecioVenta=new JTextField(Integer.toString(pel.getPrecioVenta()));
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
		campoNombrePelicula.setEditable(false);
		campoCodigoPelicula.setBounds(250,250,150,25);
		campoCodigoPelicula.setEditable(false);
		campoCantidadPelicula.setBounds(250,300,150,25);
		campoCantidadPelicula.setEditable(false);
		paneTipos.setBounds(250,350,150,50);
		paneDescripcion.setBounds(100,425,300,125);
		campoEsEstreno.setBounds(600,210,150,25);
		campoEsEstreno.setEnabled(false);
		campoAnoEstreno.setBounds(600,250,150,25);
		campoAnoEstreno.setEditable(false);
		campoPrecioArriendo.setBounds(600,300,150,25);
		campoPrecioArriendo.setEditable(false);
		campoPrecioVenta.setBounds(600,350,150,25);
		campoPrecioVenta.setEditable(false);
		
	}
}
