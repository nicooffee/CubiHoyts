package panelesJuegos;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class PanelJuegos4A extends JPanel{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JButton agregarJuego;
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
	
	public PanelJuegos4A() {
		setSize(800,600);
		setLayout(null);
		titulo=new JLabel("Agregar juego");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 175, 200, 25);
		setLabels();
		setCampos();
		agregarJuego=new JButton("Agregar juego");
		add(agregarJuego);
		agregarJuego.setBounds(550, 450, 200, 100);
	}

	public void setLabels() {
		nombreJuego=new JLabel("Nombre del juego: ");
		codigoJuego=new JLabel("Codigo: ");
		cantidadJuego=new JLabel("Cantidad: ");
		tiposJuego=new JLabel("Elegir g�neros: ");
		descripcionJuego=new JLabel("Descripcion: ");
		maximoJugadores=new JLabel("M�x. jugadores: ");
		clasificacion=new JLabel("Clasificaci�n: ");
		precioArriendo=new JLabel("Precio Arriendo: ");
		precioVenta=new JLabel("Precio Venta: ");
		consolasJuego=new JLabel("Elegir plataformas: ");
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
	}
public void setCampos() {
		
	campoNombreJuego=new JTextField();
	campoCodigoJuego=new JTextField();
	campoCantidadJuego=new JTextField();
	campoTiposJuego=new JTextArea(4,22);
	campoTiposJuego.setLineWrap(true);
	campoTiposJuego.setWrapStyleWord(true);
	campoTiposJuego.setEditable(true);
	setList();
	JScrollPane paneTipos=new JScrollPane(listaTipos,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	setListC();
	JScrollPane paneConsolas=new JScrollPane(listaConsolas,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	campoDescripcionJuego=new JTextArea();
	campoDescripcionJuego.setLineWrap(true);
	campoDescripcionJuego.setWrapStyleWord(true);
	campoDescripcionJuego.setEditable(true);
	JScrollPane paneDescripcion=new JScrollPane(campoDescripcionJuego,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
	campoMaximoJugadores=new JTextField();
	campoClasificacion=new JTextField();
	campoPrecioArriendo=new JTextField();
	campoPrecioVenta=new JTextField();
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
	public void setList() {
		String[] data= {"Accion","Aventura","Baile","Baloncesto","Belico","Carreras","Combates","Construccion de imperios","Crossover","Deportes","Estrategia","FPS","Futbol","Horror","Minijuegos","MMORPG","Pelicula interactiva","Plataformas","Rol","Sandbox","Shooter","Sigilo","Simulacion","Simulacion de vida","Simulacion social","Sobrevivencia","Suspenso","TPS"};
		listaTipos= new JList<String>(data);
	}
	public void setListC() {
		String[] data= {"PS2","PS3","PS4","XBOX360","XBOXONE","WII","WIIU","DS","DS2","DS3","SWITCH","PC","LINUX","OSX"};
		listaConsolas= new JList<String>(data);
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
}
