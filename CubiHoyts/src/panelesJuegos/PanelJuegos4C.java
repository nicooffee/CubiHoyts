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

public class PanelJuegos4C extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton modificar;
	
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
	
	
	public PanelJuegos4C(Juego jug) {
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
		

		modificar=new JButton("Modificar juego");
		add(modificar);
		modificar.setBounds(550, 450, 200, 100);
	}
public void setCampos(Juego jug) {
		
	campoNombreJuego=new JTextField(jug.getNombre());
	campoNombreJuego.setEditable(true);
	campoCodigoJuego=new JTextField(Integer.toString(jug.getCodigo()));
	campoCodigoJuego.setEnabled(false);
	campoCantidadJuego=new JTextField(Integer.toString(jug.getCantidad()));
	campoCantidadJuego.setEditable(true);
	campoTiposJuego=new JTextArea(4,22);
	campoConsolasJuego=new JTextArea(4,22);
	setList();
	setListC();
	campoTiposJuego.setText(tipos);
	campoConsolasJuego.setText(consolas);
	campoTiposJuego.setEditable(false);
	campoTiposJuego.setLineWrap(true);
	campoTiposJuego.setWrapStyleWord(true);
	campoTiposJuego.setEditable(true);
	campoConsolasJuego.setEditable(false);
	campoConsolasJuego.setLineWrap(true);
	campoConsolasJuego.setWrapStyleWord(true);
	campoConsolasJuego.setEditable(true);
	JScrollPane paneTipos=new JScrollPane(listaTipos,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	JScrollPane paneConsolas=new JScrollPane(listaConsolas,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	campoDescripcionJuego=new JTextArea(jug.getDescripcion());
	campoDescripcionJuego.setEditable(true);
	campoDescripcionJuego.setLineWrap(true);
	campoDescripcionJuego.setWrapStyleWord(true);
	JScrollPane paneDescripcion=new JScrollPane(campoDescripcionJuego,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
	campoMaximoJugadores=new JTextField(Integer.toString(jug.getJugadoresMaximos()));
	campoMaximoJugadores.setEditable(true);
	campoClasificacion=new JTextField(jug.getClasificacion());
	campoClasificacion.setEditable(true);
	campoPrecioArriendo=new JTextField(Integer.toString(jug.getPrecioArriendo()));
	campoPrecioArriendo.setEditable(true);
	campoPrecioVenta=new JTextField(Integer.toString(jug.getPrecioVenta()));
	campoPrecioVenta.setEditable(true);
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
	public boolean existeCampoVacio() {
		if(!listaTipos.isSelectionEmpty() &&
			!listaConsolas.isSelectionEmpty()&&
			!campoCantidadJuego.getText().equals("") &&
			!campoClasificacion.getText().equals("") &&
			!campoCodigoJuego.getText().equals("") &&
			!campoDescripcionJuego.getText().equals("") &&
			!campoMaximoJugadores.getText().equals("") &&
			!campoNombreJuego.getText().equals("") &&
			!campoPrecioArriendo.getText().equals("") &&
			!campoPrecioVenta.getText().equals("")
		) {
			return false;
		}
		return true;
	}
	public void setList() {
		String[] data= {"Accion","Aventura","Baile","Baloncesto","Belico","Carreras","Combate","Construccion de imperios","Crossover","Deportes","Estrategia","FPS","Futbol","Horror","Minijuegos","MMORPG","Pelicula interactiva","Plataformas","Rol","Sandbox","Shooter","Sigilo","Simulacion","Simulacion de vida","Simulacion social","Sobrevivencia","Suspenso","TPS"};
		listaTipos= new JList<String>(data);
	}
	public void setListC() {
		String[] data= {"PS2","PS3","PS4","XBOX360","XBOXONE","WII","WIIU","DS","DS2","DS3","SWITCH","PC","LINUX","OSX"};
		listaConsolas= new JList<String>(data);
	}
}
