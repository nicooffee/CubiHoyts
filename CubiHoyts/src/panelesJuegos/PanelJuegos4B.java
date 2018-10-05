package panelesJuegos;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import clases.Juego;

public class PanelJuegos4B extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JButton btnCantidad;
	public JTextField campoCant;
	public JLabel cantidad;
	
	public JLabel titulo;
	public JList<String> listaTipos;
	public JList<String> listaConsolas;
	public JLabel nombreJuego;
	public JLabel codigoJuego;
	public JLabel cantidadJuego;
	public JLabel tiposJuego;
	public JLabel consolasJuego;
	public JLabel descripcionJuego;
	public JLabel clasificacion;
	public JLabel maximoJugadores;
	public JLabel precioArriendo;
	public JLabel precioVenta;
	public JTextField campoNombreJuego;
	public JTextField campoCodigoJuego;
	public JTextField campoCantidadJuego;
	public JTextArea campoTiposJuego;
	public JTextArea campoConsolasJuego;
	public JTextArea campoDescripcionJuego;
	public JTextField campoClasificacion;
	public JTextField campoMaximoJugadores;
	public JTextField campoPrecioArriendo;
	public JTextField campoPrecioVenta;
	
	public String tipos="";
	public String consolas="";
	
	public PanelJuegos4B(Juego jug) {
		setLayout(null);
		setSize(800,600);
		setLabels();
		setCampos(jug);
		
	}
	public void setLabels() {
		nombreJuego=new JLabel("Nombre del juego: ");
		codigoJuego=new JLabel("Codigo: ");
		cantidadJuego=new JLabel("Cantidad: ");
		tiposJuego=new JLabel("Géneros: ");
		descripcionJuego=new JLabel("Descripcion: ");
		maximoJugadores=new JLabel("Máx. jugadores: ");
		clasificacion=new JLabel("Clasificación: ");
		precioArriendo=new JLabel("Precio Arriendo: ");
		precioVenta=new JLabel("Precio Venta: ");
		consolasJuego=new JLabel("Plataformas: ");
		add(nombreJuego);
		add(codigoJuego);
		add(cantidadJuego);
		add(tiposJuego);
		add(descripcionJuego);
		add(maximoJugadores);
		add(clasificacion);
		add(precioArriendo);
		add(precioVenta);
		add(consolasJuego);
		nombreJuego.setBounds(100, 210, 1000, 15);
		codigoJuego.setBounds(100, 250, 1000, 15);
		cantidadJuego.setBounds(100, 290, 1000, 15);
		tiposJuego.setBounds(100, 330, 1000, 15);
		descripcionJuego.setBounds(100, 395, 1000, 15);
		maximoJugadores.setBounds(450, 200, 1000, 15);
		clasificacion.setBounds(450, 240, 1000, 15);
		precioArriendo.setBounds(450, 280, 1000, 15);
		precioVenta.setBounds(450, 320, 1000, 15);
		consolasJuego.setBounds(450, 360, 1000, 15);
		
		cantidad=new JLabel("Sumar Cantidad: ");
		btnCantidad=new JButton("Sumar");
		campoCant=new JTextField();
		add(cantidad);
		add(btnCantidad);
		add(campoCant);
		cantidad.setBounds(450, 500, 150,25);
		btnCantidad.setBounds(650, 500, 75, 25);
		campoCant.setBounds(560, 500, 75, 25);
	}
public void setCampos(Juego jug) {
		
	campoNombreJuego=new JTextField(jug.getNombre());
	campoNombreJuego.setEnabled(false);
	campoCodigoJuego=new JTextField(Integer.toString(jug.getCodigo()));
	campoCodigoJuego.setEnabled(false);
	campoCantidadJuego=new JTextField(Integer.toString(jug.getCantidad()));
	campoCantidadJuego.setEnabled(false);
	campoTiposJuego=new JTextArea(4,22);
	campoConsolasJuego=new JTextArea(4,22);
	for(int i=0;i<jug.getGeneros().size();i++) {
		tipos=tipos+"-"+jug.getGeneros().get(i)+"\n";
	}
	for(int i=0;i<jug.getConsolas().size();i++) {
		consolas=consolas+"-"+jug.getConsolas().get(i)+"\n";
	}
	campoTiposJuego.setText(tipos);
	campoConsolasJuego.setText(consolas);
	campoTiposJuego.setEditable(false);
	campoTiposJuego.setLineWrap(true);
	campoTiposJuego.setWrapStyleWord(true);
	campoConsolasJuego.setEditable(false);
	campoConsolasJuego.setLineWrap(true);
	campoConsolasJuego.setWrapStyleWord(true);
	JScrollPane paneTipos=new JScrollPane(campoTiposJuego,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	JScrollPane paneConsolas=new JScrollPane(campoConsolasJuego,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	campoDescripcionJuego=new JTextArea(jug.getDescripcion());
	campoDescripcionJuego.setEditable(false);
	campoDescripcionJuego.setLineWrap(true);
	campoDescripcionJuego.setWrapStyleWord(true);
	JScrollPane paneDescripcion=new JScrollPane(campoDescripcionJuego,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
	if(jug.getJugadoresMaximos()==0)
		campoMaximoJugadores=new JTextField("Indefinido");
	else
		campoMaximoJugadores=new JTextField(Integer.toString(jug.getJugadoresMaximos()));
	campoMaximoJugadores.setEnabled(false);
	campoClasificacion=new JTextField(jug.getClasificacion());
	campoClasificacion.setEnabled(false);
	campoPrecioArriendo=new JTextField(Integer.toString(jug.getPrecioArriendo()));
	campoPrecioArriendo.setEnabled(false);
	campoPrecioVenta=new JTextField(Integer.toString(jug.getPrecioVenta()));
	campoPrecioVenta.setEnabled(false);
	add(campoNombreJuego);
	add(campoCodigoJuego);
	add(campoCantidadJuego);
	add(paneTipos);
	add(paneConsolas);
	add(paneDescripcion);
	add(campoMaximoJugadores);
	add(campoClasificacion);
	add(campoPrecioArriendo);
	add(campoPrecioVenta);
	campoNombreJuego.setBounds(250,210,150,25);
	campoNombreJuego.setEditable(true);
	campoCodigoJuego.setBounds(250,250,150,25);
	campoCodigoJuego.setEditable(true);
	campoCantidadJuego.setBounds(250,290,150,25);
	campoCantidadJuego.setEditable(true);
	paneTipos.setBounds(250,330,150,75);
	paneConsolas.setBounds(600,360,150,75);
	paneDescripcion.setBounds(100,425,300,125);
	campoMaximoJugadores.setBounds(600,200,150,25);
	campoClasificacion.setBounds(600,240,150,25);
	campoClasificacion.setEditable(true);
	campoPrecioArriendo.setBounds(600,280,150,25);
	campoPrecioArriendo.setEditable(true);
	campoPrecioVenta.setBounds(600,320,150,25);
	campoPrecioVenta.setEditable(true);
		
	}
}
