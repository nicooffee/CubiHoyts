package ventana;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import clases.Arriendo;
import clases.Cliente;
import clases.CubiHoyts;
import clases.Historial;
import clases.Juego;
import clases.Negocio;
import clases.Pelicula;
import clases.ProductoVideoClub;
import clases.Venta;
import excepciones.InformacionRegistradaException;
import excepciones.RUTException;
import logo.PanelLogo;
import memento.Memento;
import memento.Originador;
import memento.Vigilante;
import métodosGlobales.EsNumerico;
import panelesClientes.PanelClientes2A;
import panelesClientes.PanelClientes2C;
import panelesClientes.PanelClientes2C1;
import panelesClientes.PanelClientes2D1;
import panelesClientes.PanelClientes2D1A;
import panelesClientes.PanelClientesArriendoPB;
import panelesClientes.PanelClientesPB;
import panelesClientes.PanelClientesVentaPB;
import panelesJuegos.JuegosObservados;
import panelesJuegos.PanelJuegos4A;
import panelesJuegos.PanelJuegos4B;
import panelesJuegos.PanelJuegos4C;
import panelesJuegos.PanelJuegos4DX;
import panelesNegocio.PanelNegocios3A;
import panelesNegocio.PanelNegocios3A1;
import panelesNegocio.PanelNegocios3A1A;
import panelesNegocio.PanelNegocios3A1A1;
import panelesNegocio.PanelNegocios3B;
import panelesNegocio.PanelNegocios3C;
import panelesNegocio.PanelNegocios3D;
import panelesNegocio.PanelVentaArriendo;
import panelesNegocio.ReporteArriendo;
import panelesNegocio.ReporteVenta;
import panelesPeliculas.PanelPeliculas1AXPC;
import panelesPeliculas.PanelPeliculas1C;
import panelesPeliculas.PanelPeliculas1D1;
import panelesPeliculas.PanelPeliculas1E1;
import panelesPeliculas.PeliculasObservadas;
import panelesProductos.PanelProductos;
import panelesProductos.PanelProductos1E;
import panelesProductos.PanelProductosPB;
import panelesProductos.ProductosRecomendados;
import utilidades.Exportable;
import utilidades.ModificadorDeTabla;
/**
 * Clase VentanaJFrame:
 * 
 * Clase contenedora de todos los componentes
 * y estructuras del programa.
 * 
 * La clase extiende de la clase JFrame e
 * implementa la interface ActionListener.
 * 
 * La clase posee como atributos todos los
 * paneles que utiliza el programa, como también 
 * variables auxiliares e indices utilizados en el 
 * método actionPerformed.
 * 
 * La clase posee un atributo de tipo JFrame que 
 * corresponde a la ventana que muestra los gráficos
 * y las recomendaciones.
 * 
 * La clase utiliza dos JPanel de los
 * cuales uno corresponde a panelLogo
 * que muestra los botones salir, volver y 
 * el otro que es cambiado constantemente 
 * en la utilización del programa.
 * 
 * La clase está implementada con el patrón de diseño
 * Singleton, para asegurar la integridad del programa
 * (la existencia de solo una clase VentanaJFrame).
 * 
 * Características de la ventana principal:
 * 
 * Tamaño: 800x600 píxeles.
 * Layout: null/AbsoluteLayout.
 * Bordes: inactivos.
 * Operación salir predeterminada: desactivada.
 * Utilización: todo.
 * 
 * 
 * Características de la ventana secundaria:
 * 
 * Tamaño: 640x480 píxeles.
 * Layout: null/AbsoluteLayout.
 * Bordes: inactivos.
 * Operación salir predeterminada: activada.
 * Utilización: mostrar gráficos y pel. recomendadas.
 * 
 * @author Nicolás Honorato
 *
 */
public class VentanaJFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Cliente auxCliente;												//Auxiliar de Cliente utilizado en ActionPerformed.
	private int auxGanancia;												//Auxiliar de ganancias utilizado en ActionPerformed.
	private ProductoVideoClub auxProducto;									//Auxiliar de Pelicula utilizado en ActionPerformed.
	
	
	private Object panelActual;												//Auxiliar para guardar distintos paneles como "actuales".
	
	
	private final JFrame panelSecundaria=new JFrame();						//Ventana secundaria, es utilizada para mostrar gráficos y pel. recomendadas.
	private PanelLogo panelLogo;											//Panel superior del programa, muestra el logo, botón salir y volver.
	private PanelPrincipal panelPrincipal;									//Panel de inicio del programa.
	private PanelVentaArriendo panelVentaArriendo;							//Panel principal de negocios.
	private PanelPeliculas1C panelPeliculas1C;								//Panel agregar película
	private PanelProductos1E panelPeliculas1E;								//Panel buscar película
	private PanelPeliculas1E1 panelPeliculas1E1;							//Panel siguiente al 1E, muestra la película si es encontrada.
	private PanelPeliculas1D1 panelPeliculas1D1;							//Panel siguiente al 1D, muestra los datos para modificar de la película.
	private PanelClientes2A panelClientes2A;								//Panel agregar cliente
	private PanelClientes2C panelClientes2D;								//Panel buscar cliente
	private PanelClientes2C1 panelClientes2C1;								//Panel siguiente a 2C, sobreeescribe los datos del cliente si son modificados en los campos
	private PanelClientes2D1 panelClientes2D1;								//Panel siguiente a 2D, muestra los datos del cliente buscado.
	private PanelClientes2D1A panelClientes2D1A;							//Panel siguiente a 2D1, muestra la cantidad de ventas y arriendos del cliente.
	private PanelNegocios3A panelNegocios3Aarriendo;						//Panel realizar arriendo, es donde se pide el rut del cliente.
	private PanelNegocios3A1 panelNegocios3A1arriendo;						//Panel siguiente a 3Aarriendo, es donde se pide el código de la película. 
	private PanelNegocios3A1A panelNegocios3A1Aarriendo;					//Panel siguiente a 3A1arriendo, es donde se piden los días del arriendo.
	private PanelNegocios3A1A1 panelNegocios3A1A1arriendo;					//Panel siguiente a 3A1Aarriendo, es donde se muestra la ganancia del arriendo y se confirma.
	private PanelNegocios3A panelNegocios3Aventa;							//Panel realizar venta, es donde se pide el rut del cliente.
	private PanelNegocios3A1 panelNegocios3A1venta;							//Panel siguiente a 3Aventa, es donde se pide el código de la película.
	private PanelNegocios3A1A1 panelNegocios3A1Aventa;						//Panel siguiente a 3A1venta, es donde se muestra la ganancia de la venta y se confirma.
	private PanelNegocios3C panelNegocios3C;								//Panel que muestra los datos más generales del videoclub.
	private PanelNegocios3D panelNegocios3D;								//Panel donde se piden los datos para devolver un arriendo.

	//Atributos parte B

	private PanelClientesPB panelClientesPB;								//Panel principal de clientes, se muestra la lista completa.
	private PanelProductosPB panelPeliculasPB;								//Panel principal de películas, se muestra la lista completa.
	private PanelProductosPB panelJuegosPB;									//Panel principal de juegos, se muestra la lista completa.
	private PanelClientesArriendoPB panelClientesArriendoPB;				//Panel de listado de arriendos de algún cliente.
	private PanelClientesVentaPB panelClientesVentaPB;						//Panel de lista de ventas de algún cliente.
	private PanelNegocios3B panelNegocios3B;								//Panel donde se piden los datos para cancelar un negocio.
	private PanelProductos panelProductos;									//Panel principal de productos.
	private PanelJuegos4A panelJuegos4A;									//Panel agregar juego.
	private PanelJuegos4B panelJuegos4B;									//Panel mostrar datos juego.
	private PanelJuegos4C panelJuegos4C;									//Panel modificar juego.
	private PanelProductos1E panelJuegos4D;									//Panel buscar juego (por código).
	private ReporteArriendo reporteArriendo;								//Panel de listado de arriendos y guardar reporte.
	private ReporteVenta reporteVenta;										//Panel de listado de ventas y guardar reporte.
	private static VentanaJFrame panel;										//Instancia estática de la misma clase.
	
	
	//Atributos parte C
	
	private PanelPeliculas1AXPC panelPeliculas1AXPC;
	private PanelJuegos4DX panelJuegos4DX;
	private ArrayList<ModificadorDeTabla> restaurables;
	private Originador original;
	private Vigilante anterior;
	private int estado;
	/**
	 * getInstance:
	 * 
	 * Método que entrega la instancia de la clase.
	 * 
	 * Si no ha sido creada la dimensiona y luego la envía.
	 * @return
	 * Instancia única de VentanaJFrame.
	 */
	public static VentanaJFrame getInstance() {
		if(panel==null)
			panel=new VentanaJFrame();
		return panel;
	}
	
	
	
	
	
	
	
	/**
	 * Constructor privado de la clase VentanaJFrame:
	 * 
	 * Constructor que carga todos los datos del programa,
	 * además dimensiona la ventana y asigna paneles iniciales.
	 * 
	 * Además, añade las acciones de los botones de paneles estáticos
	 * al ActionListener.
	 * 
	 */
	private  VentanaJFrame() {
		/**
		 * Se dimensiona la estructura del videoclub
		 * y se le cargan todos los datos contenidos
		 * en la base de datos.
		 */
		restaurables=new ArrayList<ModificadorDeTabla>();
		CubiHoyts cubi=new CubiHoyts();
		cubi.cargarDatos();
		original=new Originador(cubi);
		anterior=new Vigilante();
		/**
		 * Se le asignan características a la ventana
		 * y a la ventana secundaria.
		 * 
		 * Además, se le agrega el panel
		 * inicial y el que contiene el logo
		 * a la ventana.
		 */
		this.setTitle("CubiHoyts");
		this.setBounds(0,0,800,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		final Container panel=this.getContentPane();
		panel.setLayout(null);
		this.setVentanas();
		this.panelSecundaria.setBounds(0,0,640,480);
		this.panelSecundaria.setLocationRelativeTo(null);
		this.panelSecundaria.setResizable(false);
		this.panelLogo.volver.setVisible(false);
		this.panelLogo.undo.setVisible(false);
		/**
		 * ACCIONES GENERALES
		 * 
		 * Se agregan todas las acciones de los componentes
		 * de todas los paneles estáticos.
		 * 
		 * Todas las acciones recaen en esta misma clase.
		 * 
		 */
		this.panelLogo.salir.addActionListener(this);
		this.panelLogo.volver.addActionListener(this);
		this.panelLogo.undo.addActionListener(this);
		this.panelPrincipal.peliculas.addActionListener(this);
		this.panelPrincipal.clientes.addActionListener(this);
		this.panelPrincipal.ventaArriendo.addActionListener(this);
		this.panelProductos.peliculas.addActionListener(this);
		this.panelProductos.juegos.addActionListener(this);
		this.panelPeliculas1C.agregarPelicula.addActionListener(this);
		this.panelPeliculas1E.buscarProducto.addActionListener(this);
		this.panelClientes2A.guardar.addActionListener(this);
		this.panelClientes2D.buscarCliente.addActionListener(this);
		this.panelVentaArriendo.registrarArriendo.addActionListener(this);
		this.panelVentaArriendo.registrarVenta.addActionListener(this);
		this.panelVentaArriendo.gananciaTotal.addActionListener(this);
		this.panelVentaArriendo.devolucionArriendo.addActionListener(this);
		this.panelVentaArriendo.cancelarNegocio.addActionListener(this);
		this.panelVentaArriendo.reporteArriendo.addActionListener(this);
		this.panelVentaArriendo.reporteVenta.addActionListener(this);
		this.panelNegocios3Aarriendo.siguiente.addActionListener(this);
		this.panelNegocios3A1Aarriendo.siguiente.addActionListener(this);
		this.panelNegocios3A1A1arriendo.guardar.addActionListener(this);
		this.panelNegocios3Aventa.siguiente.addActionListener(this);
		this.panelNegocios3A1Aventa.guardar.addActionListener(this);
		this.panelNegocios3C.grafCantPel.addActionListener(this);
		this.panelNegocios3C.grafCantVAClientes.addActionListener(this);
		this.panelNegocios3C.grafVAMeses.addActionListener(this);
		this.panelNegocios3C.grafTipoClientes.addActionListener(this);
		this.panelNegocios3C.grafGananciaSemanal.addActionListener(this);
		this.panelJuegos4A.agregarJuego.addActionListener(this);
		this.panelJuegos4D.buscarProducto.addActionListener(this);
		/**
		 * Fin adición de acciones (botones).
		 * 
		 * Fin constructor.
		 */
	}












	/**
	 * actionPerformed:
	 * 
	 * Se sobreescribe el método actionPerformed
	 * para asignar la ejecución de acciones al momento
	 * en que un evento añadido en el constructor sea activado.
	 * 
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		CubiHoyts principal=(CubiHoyts) original.getEstado();
		final Container panel=this.getContentPane();
		if(panelLogo.undo.isVisible())
			panelLogo.undo.setVisible(false);
		
		if(e.getSource()!=null) {
			/**
			 * 
			 * Acciones de los botones de la tabla
			 * de clientes.
			 * 
			 * Recorre todos los campos de botones de la tabla 
			 * hasta encontrar el botón que generó la acción.
			 * 
			 * Luego verifica en qué posición está para 
			 * determinar con qué cliente se trabajará.
			 * 
			 * Si el índice i es 4 significa que se accionó
			 * un botón "Datos".
			 * 
			 * Si el índice i es 5 significa que se accionó
			 * un botón "Modificar".
			 * 
			 * Si el índice i es 6 significa que se accionó
			 * un botón "Eliminar".
			 * 
			 */
			if((this.panelActual==this.panelClientesPB) && (e.getSource()!=this.panelLogo.volver)) {
				for(int i=4;i<7;i++) {
					final int size=principal.cantidadClientes();
					for(int j=0;j<size;j++) {
						if(e.getSource()==this.panelClientesPB.modelo.getValueAt(j, i)) {
							if(i==4) {
								try {
									this.auxCliente=principal.buscarCliente((String) this.panelClientesPB.modelo.getValueAt(j, 1));
									final Historial hist=principal.buscarHistorial(this.auxCliente.getRut());
									this.panelClientes2D1=new PanelClientes2D1(this.auxCliente,hist);
									this.panelClientes2D1.historial.addActionListener(this);
									panel.add(this.panelClientes2D1);
									panel.remove(this.panelClientesPB);
									this.panelActual=this.panelClientes2D1;
								} catch (RUTException e1) {
								}
								break;
							}
							else if(i==5) {
								panel.remove(this.panelClientesPB);
								try {
									this.auxCliente=principal.buscarCliente((String) this.panelClientesPB.modelo.getValueAt(j, 1));
									this.panelClientes2C1=new PanelClientes2C1(this.auxCliente);
									this.panelClientes2C1.confirmar.addActionListener(this);
									panel.add(this.panelClientes2C1);
									this.panelActual=this.panelClientes2C1;
								} catch (RUTException e1) {
								}
								break;
							}
							else if(i==6) {
								final int opcion=JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar al cliente con rut: "+this.panelClientesPB.modelo.getValueAt(j, 1)+"?","Seleccione una opción...", JOptionPane.WARNING_MESSAGE);
								if(opcion==JOptionPane.YES_OPTION) {
									final String rut=(String) this.panelClientesPB.modelo.getValueAt(j, 1);	
									try {
										Cliente auxC=principal.buscarCliente(rut);
										Historial auxH=principal.buscarHistorial(rut);
										
										this.agregarMemento();
										restaurables.add(auxC);
										restaurables.add(auxH);
										estado=0;
										
										auxC.eliminarDatos();
										auxH.eliminarDatos();
										
										principal.eliminarClientePorRut(rut);
										principal.eliminarHistorial(rut);
										
										panel.remove(this.panelClientesPB);
										this.setVentanaClientesPB();
										panel.add(this.panelClientesPB);
										JOptionPane.showMessageDialog(null, "¡Cliente eliminado exitósamente!");
										this.panelActual=this.panelClientesPB;
									} catch (RUTException e1) {
									}
								}
								else {
									panel.remove(this.panelClientesPB);
									this.setVentanaClientesPB();
									panel.add(this.panelClientesPB);
									this.panelActual=this.panelClientesPB;
									JOptionPane.showMessageDialog(null, "¡El cliente no ha sido eliminado!");
								}
								break;
							}
						}
					}
				}
			}





			/**
			 * 
			 * Acciones de los botones de la tabla
			 * de películas.
			 * 
			 * Recorre todos los campos de botones de la tabla 
			 * hasta encontrar el botón que generó la acción.
			 * 
			 * Luego verifica en qué posición está para 
			 * determinar con qué película se trabajará.
			 * 
			 * Si el índice i es 4 significa que se accionó
			 * un botón "Datos".
			 * 
			 * Si el índice i es 5 significa que se accionó
			 * un botón "Modificar".
			 * 
			 * Si el índice i es 6 significa que se accionó
			 * un botón "Eliminar".
			 * 
			 */
			else if((this.panelActual==this.panelPeliculasPB) && (e.getSource()!=this.panelLogo.volver)) {
				for(int i=4;i<7;i++) {
					final int size=principal.cantidadPeliculas();
					for(int j=0;j<size;j++) {
						if(e.getSource()==this.panelPeliculasPB.modelo.getValueAt(j, i)) {
							if(i==4) {
								this.auxProducto=principal.buscarPelicula((int) this.panelPeliculasPB.modelo.getValueAt(j, 1));
								this.panelPeliculas1E1=new PanelPeliculas1E1((Pelicula) this.auxProducto);
								this.panelPeliculas1E1.btnCantidad.addActionListener(this);
								panel.add(this.panelPeliculas1E1);
								panel.remove(this.panelPeliculasPB);
								this.panelActual=this.panelPeliculas1E1;
								break;
							}
							else if(i==5) {
								this.auxProducto=principal.buscarPelicula((int) this.panelPeliculasPB.modelo.getValueAt(j, 1));
								this.panelPeliculas1D1=new PanelPeliculas1D1((Pelicula) this.auxProducto);
								this.panelPeliculas1D1.modificar.addActionListener(this);
								panel.add(this.panelPeliculas1D1);
								panel.remove(this.panelPeliculasPB);
								this.panelActual=this.panelPeliculas1D1;
								break;
							}
							else if(i==6) {
								final int opcion=JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar la película: "+this.panelPeliculasPB.modelo.getValueAt(j, 0)+"?","Error",JOptionPane.WARNING_MESSAGE);
								if(opcion==JOptionPane.YES_OPTION) {
									panel.remove(this.panelPeliculasPB);
									Pelicula pel=(Pelicula) principal.buscarPelicula((int) this.panelPeliculasPB.modelo.getValueAt(j, 1));
									
									this.agregarMemento();
									restaurables.add(pel);
									estado=0;
									
									pel.eliminarDatos();
									
									principal.eliminarProducto(pel.getCodigo());
									this.setVentanaPeliculasPB();
									this.add(this.panelPeliculasPB);
									JOptionPane.showMessageDialog(null, "¡Película eliminada exitósamente!");
									this.panelActual=this.panelPeliculasPB;
								}
								else {
									panel.remove(this.panelPeliculasPB);
									this.setVentanaPeliculasPB();
									this.add(this.panelPeliculasPB);
									this.panelActual=this.panelPeliculasPB;
									JOptionPane.showMessageDialog(null, "¡La película no ha sido eliminada!");
								}
								break;
							}
						}
					}
				}
			}







			/**
			 * 
			 * Acciones de los botones de la tabla
			 * de juegos.
			 * 
			 * Recorre todos los campos de botones de la tabla 
			 * hasta encontrar el botón que generó la acción.
			 * 
			 * Luego verifica en qué posición está para 
			 * determinar con qué juego se trabajará.
			 * 
			 * Si el índice i es 4 significa que se accionó
			 * un botón "Datos".
			 * 
			 * Si el índice i es 5 significa que se accionó
			 * un botón "Modificar".
			 * 
			 * Si el índice i es 6 significa que se accionó
			 * un botón "Eliminar".
			 * 
			 */
			else if((this.panelActual==this.panelJuegosPB) && (e.getSource()!=this.panelLogo.volver)) {
				for(int i=4;i<7;i++) {
					final int size=principal.cantidadJuegos();
					for(int j=0;j<size;j++) {
						if(e.getSource()==this.panelJuegosPB.modelo.getValueAt(j, i)) {
							if(i==4) {
								this.auxProducto=principal.buscarJuego((int) this.panelJuegosPB.modelo.getValueAt(j, 1));
								this.panelJuegos4B=new PanelJuegos4B((Juego) this.auxProducto);
								this.panelJuegos4B.btnCantidad.addActionListener(this);
								panel.add(this.panelJuegos4B);
								panel.remove(this.panelJuegosPB);
								this.panelActual=this.panelJuegos4B;
								break;
							}
							else if(i==5) {
								this.auxProducto=principal.buscarJuego((int) this.panelJuegosPB.modelo.getValueAt(j, 1));
								this.panelJuegos4C=new PanelJuegos4C((Juego) this.auxProducto);
								this.panelJuegos4C.modificar.addActionListener(this);
								panel.remove(this.panelJuegosPB);
								panel.add(this.panelJuegos4C);
								this.panelActual=this.panelJuegos4C;
								break;
							}
							else if(i==6) {
								final int opcion=JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar el juego: "+this.panelJuegosPB.modelo.getValueAt(j, 0)+"?","Error",JOptionPane.WARNING_MESSAGE);
								if(opcion==JOptionPane.YES_OPTION) {
									panel.remove(this.panelJuegosPB);
									Juego jug=(Juego) principal.buscarJuego((int) this.panelJuegosPB.modelo.getValueAt(j, 1));
									
									this.agregarMemento();
									restaurables.add(jug);
									estado=0;
									
									jug.eliminarDatos();
									principal.eliminarProducto(jug.getCodigo());
									this.setVentanaJuegosPB();
									this.add(this.panelJuegosPB);
									JOptionPane.showMessageDialog(null, "¡Juego eliminado exitósamente!");
									this.panelActual=this.panelJuegosPB;
								}
								else {
									panel.remove(this.panelJuegosPB);
									this.setVentanaJuegosPB();
									this.add(this.panelJuegosPB);
									this.panelActual=this.panelJuegosPB;
									JOptionPane.showMessageDialog(null, "¡El juego no ha sido eliminado!");
								}
								break;
							}
						}
					}
				}
			}





			/**
			 * Accion para ingresar al panel panelPeliculas
			 * desde panelPrincipal
			 */
			if(e.getSource()==this.panelPrincipal.peliculas) {
				this.panelLogo.volver.setVisible(true);
				panel.remove(this.panelPrincipal);
				panel.add(this.panelProductos);
				this.panelActual=this.panelProductos;
			}





			/**
			 * Accion para ingresar al panel panelClientes
			 * desde panelPrincipal
			 */
			if(e.getSource()==this.panelPrincipal.clientes) {
				this.panelLogo.volver.setVisible(true);
				panel.remove(this.panelPrincipal);
				this.setVentanaClientesPB();
				panel.add(this.panelClientesPB);
				this.panelActual=this.panelClientesPB;
			}





			/**
			 * Accion para ingresar al panel panelVentaArriendo
			 * desde panelPrincipal
			 */
			if(e.getSource()==this.panelPrincipal.ventaArriendo) {
				this.panelLogo.volver.setVisible(true);
				panel.remove(this.panelPrincipal);
				panel.add(this.panelVentaArriendo);
				this.panelActual=this.panelVentaArriendo;
			}





			/**
			 * Accion para ingresar al panel panelPeliculasPB
			 * desde panelProductos
			 */
			if(e.getSource()==this.panelProductos.peliculas) {
				panel.remove(this.panelProductos);
				panel.add(this.panelPeliculasPB);
				this.panelActual=this.panelPeliculasPB;
			}





			/**
			 * Accion para ingresar al panel panelJuegosPB
			 * desde panelProductos
			 */
			if(e.getSource()==this.panelProductos.juegos) {
				panel.remove(this.panelProductos);
				panel.add(this.panelJuegosPB);
				this.panelActual=this.panelJuegosPB;
			}






			/**
			 * Accion para ingresar al panel panelPeliculas1A
			 * desde panelPeliculas
			 */
			if(e.getSource()==this.panelPeliculasPB.categorias) {
				panel.remove(this.panelPeliculasPB);
				PeliculasObservadas array=new PeliculasObservadas(principal.clonarListaPelicula());
				panelPeliculas1AXPC=new PanelPeliculas1AXPC(array);
				array.addObserver(this.panelPeliculas1AXPC);
				panel.add(this.panelPeliculas1AXPC);
				this.panelActual=this.panelPeliculas1AXPC;
			}





			/**
			 * Accion para ingresar al panel panelPeliculas1C
			 * desde panelPeliculasPB
			 * 
			 * Además, borra todos los textos dentro de los campos
			 * del panel panelPeliculas1C.
			 */
			if(e.getSource()==this.panelPeliculasPB.agregar) {
				panel.remove(this.panelPeliculasPB);
				this.panelPeliculas1C.campoAnoEstreno.setText("");
				this.panelPeliculas1C.campoCantidadPelicula.setText("");
				this.panelPeliculas1C.campoCodigoPelicula.setText("");
				this.panelPeliculas1C.campodescripcionPelicula.setText("");
				this.panelPeliculas1C.campoEsEstreno.setSelected(false);
				this.panelPeliculas1C.campoNombrePelicula.setText("");
				this.panelPeliculas1C.campoPrecioArriendo.setText("");
				this.panelPeliculas1C.campoPrecioVenta.setText("");
				this.panelPeliculas1C.listaTipos.clearSelection();
				panel.add(this.panelPeliculas1C);
				this.panelActual=this.panelPeliculas1C;
			}






			/**
			 * Accion para ingresar al panel panelJuegos4A
			 * desde panelJuegosPB
			 * 
			 * Además, borra todos los textos dentro de los campos
			 * del panel panelJuegos4A.
			 */
			if(e.getSource()==this.panelJuegosPB.agregar) {
				panel.remove(this.panelJuegosPB);
				this.panelJuegos4A.campoCantidadJuego.setText("");
				this.panelJuegos4A.campoClasificacion.setText("");
				this.panelJuegos4A.campoCodigoJuego.setText("");
				this.panelJuegos4A.campoDescripcionJuego.setText("");
				this.panelJuegos4A.campoNombreJuego.setText("");
				this.panelJuegos4A.campoPrecioArriendo.setText("");
				this.panelJuegos4A.campoPrecioVenta.setText("");
				this.panelJuegos4A.campoMaximoJugadores.setText("");
				this.panelJuegos4A.listaTipos.clearSelection();
				this.panelJuegos4A.listaConsolas.clearSelection();
				panel.add(this.panelJuegos4A);
				this.panelActual=this.panelJuegos4A;
			}





			/**
			 * Accion para agregar peliculas desde
			 * panel panelPeliculas1C.
			 * 
			 * En esta acción se crea una película con
			 * los datos escritos en los campos de la ventana
			 * panelPeliculas1C. Se comprueba si no están vacíos
			 * y si es que son tipos de datos válidos.
			 * 
			 * Finalmente se agrega al conjunto de películas de
			 * CubiHoyts si se pudo crear exitósamente.
			 * 
			 */
			if(e.getSource()==this.panelPeliculas1C.agregarPelicula) {
				if(!this.panelPeliculas1C.listaTipos.isSelectionEmpty() && !this.panelPeliculas1C.campoCantidadPelicula.getText().equals("") && !this.panelPeliculas1C.campoAnoEstreno.getText().equals("") && !this.panelPeliculas1C.campoCodigoPelicula.getText().equals("") && !this.panelPeliculas1C.campodescripcionPelicula.getText().equals("") && !this.panelPeliculas1C.campoNombrePelicula.getText().equals("") && !this.panelPeliculas1C.campoPrecioArriendo.getText().equals("") && !this.panelPeliculas1C.campoPrecioVenta.getText().equals("")) {
					if(EsNumerico.comprobar(this.panelPeliculas1C.campoCodigoPelicula.getText())) {
						final int codigo=Integer.parseInt(this.panelPeliculas1C.campoCodigoPelicula.getText());
						if((codigo>=1000) && (codigo<=99999)) {
							if(principal.buscarPelicula(Integer.parseInt(this.panelPeliculas1C.campoCodigoPelicula.getText())).getCodigo()==0) {
								try {
									final int cant=Integer.parseInt(this.panelPeliculas1C.campoCantidadPelicula.getText());
									final int cod=Integer.parseInt(this.panelPeliculas1C.campoCodigoPelicula.getText());
									final String nom=this.panelPeliculas1C.campoNombrePelicula.getText();
									final ArrayList<String> tipos=(ArrayList<String>) this.panelPeliculas1C.listaTipos.getSelectedValuesList();
									final String descrp=this.panelPeliculas1C.campodescripcionPelicula.getText();
									final boolean est=this.panelPeliculas1C.campoEsEstreno.isSelected();
									final int anio=Integer.parseInt(this.panelPeliculas1C.campoAnoEstreno.getText());
									final int pArr=Integer.parseInt(this.panelPeliculas1C.campoPrecioArriendo.getText());
									final int pVen=Integer.parseInt(this.panelPeliculas1C.campoPrecioVenta.getText());
									this.auxProducto=new Pelicula(cant,cod,nom,tipos,descrp,est,anio,pArr,pVen);
									
									principal.agregarPelicula(this.auxProducto);
									JOptionPane.showMessageDialog(null, "¡Película agregada exitósamente!");
									this.setVentanaPeliculasPB();
									this.panelLogo.volver.doClick();
								}
								catch(final Exception ex) {
									JOptionPane.showMessageDialog(null, "¡Error al agregar película: tipos de datos incorrectos!","Error",JOptionPane.ERROR_MESSAGE);
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "¡Película ya registrada con ese código!","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "¡El código debe ser de 4 a 5 dígitos!","Error",JOptionPane.ERROR_MESSAGE);
						}

					}
					else {
						JOptionPane.showMessageDialog(null, "¡El código solo debe contener números!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Existe algún campo sin completar!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}






			/**
			 * Accion para agregar juegos desde
			 * panel panelJuegos4A.
			 * 
			 * En esta acción se crea un juego con
			 * los datos escritos en los campos de la ventana
			 * panelJuegos4A. Se comprueba si no están vacíos
			 * y si es que son tipos de datos válidos.
			 * 
			 * Finalmente se agrega al conjunto de juegos de
			 * CubiHoyts si se pudo crear exitósamente.
			 * 
			 */
			if(e.getSource()==this.panelJuegos4A.agregarJuego) {
				if(!this.panelJuegos4A.existeCampoVacio()) {
					if(EsNumerico.comprobar(this.panelJuegos4A.campoCodigoJuego.getText())) {
						final int codigo=Integer.parseInt(this.panelJuegos4A.campoCodigoJuego.getText());
						if((codigo>=100000) && (codigo<=999999)) {
								try {
									final String nombre=this.panelJuegos4A.campoNombreJuego.getText();
									final int cant=Integer.parseInt(this.panelJuegos4A.campoCantidadJuego.getText());
									final int cod=Integer.parseInt(this.panelJuegos4A.campoCodigoJuego.getText());
									final String descrp=this.panelJuegos4A.campoDescripcionJuego.getText();
									final ArrayList<String> tipos=(ArrayList<String>) this.panelJuegos4A.listaTipos.getSelectedValuesList();
									final ArrayList<String> consolas=(ArrayList<String>) this.panelJuegos4A.listaConsolas.getSelectedValuesList();
									final int precioA=Integer.parseInt(this.panelJuegos4A.campoPrecioArriendo.getText());
									final int precioV=Integer.parseInt(this.panelJuegos4A.campoPrecioVenta.getText());
									final String clas=this.panelJuegos4A.campoClasificacion.getText();
									final int max=Integer.parseInt(this.panelJuegos4A.campoMaximoJugadores.getText());
									this.auxProducto=new Juego(nombre,cant,cod,descrp,tipos,consolas,precioA,precioV,clas,max);
									
									principal.agregarJuego(this.auxProducto);
									JOptionPane.showMessageDialog(null, "¡Juego agregado exitósamente!");
									this.setVentanaJuegosPB();
									this.panelLogo.volver.doClick();
								}catch(InformacionRegistradaException ex) {
								}
						}
						else {
							JOptionPane.showMessageDialog(null, "¡El código debe ser de 6 dígitos!","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "¡El código solo debe contener números!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Existe algún campo sin completar!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}





			/**
			 * Accion para ingresar al panel panelPeliculas1E
			 * desde panelPeliculasPB
			 */
			if(e.getSource()==this.panelPeliculasPB.buscar) {
				panel.remove(this.panelPeliculasPB);
				this.panelPeliculas1E.campoCodigoProducto.setText("");
				panel.add(this.panelPeliculas1E);
				this.panelActual=this.panelPeliculas1E;
			}





			/**
			 * Accion para ingresar al panel panelJuegos4D
			 * desde panelJuegosPB
			 */
			if(e.getSource()==this.panelJuegosPB.buscar) {
				panel.remove(this.panelJuegosPB);
				this.panelJuegos4D.campoCodigoProducto.setText("");
				panel.add(this.panelJuegos4D);
				this.panelActual=this.panelJuegos4D;
			}




			/**
			 * Acción del botón buscarPelicula de la ventana
			 * panelPeliculas1E.
			 * 
			 * Al activarse el botón se extrae el código de la película
			 * del campo campoCodigoProducto para buscar una película
			 * dentro de la lista. Si existe se dimensiona el panel
			 * panelPeliculas1E1 y se muestra en la ventana.
			 * 
			 */
			if(e.getSource()==this.panelPeliculas1E.buscarProducto) {
				if(!this.panelPeliculas1E.campoCodigoProducto.getText().equals("")) {
					if(EsNumerico.comprobar(this.panelPeliculas1E.campoCodigoProducto.getText())) {
						final int codigo=Integer.parseInt(this.panelPeliculas1E.campoCodigoProducto.getText());
						if((codigo>=1000) && (codigo<=99999)) {
							this.auxProducto=principal.buscarPelicula(Integer.parseInt(this.panelPeliculas1E.campoCodigoProducto.getText()));
							if(this.auxProducto.getCodigo()!=0) {
								this.panelPeliculas1E1= new PanelPeliculas1E1((Pelicula) this.auxProducto);
								this.panelPeliculas1E1.btnCantidad.addActionListener(this);
								panel.remove(this.panelPeliculas1E);
								panel.add(this.panelPeliculas1E1);
								this.panelActual=this.panelPeliculas1E1;
							}
							else {
								JOptionPane.showMessageDialog(null, "¡Película no encontrada!","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "¡El código debe ser de 4 a 5 dígitos!","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "¡El código solo debe contener números!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}





			/**
			 * Acción del botón buscarPelicula de la ventana
			 * panelJuegos4D.
			 * 
			 * Al activarse el botón se extrae el código del juego
			 * del campo campoCodigoProducto para buscar una película
			 * dentro de la lista. Si existe se dimensiona el panel
			 * panelJuegos4B y se muestra en la ventana.
			 * 
			 */
			if(e.getSource()==this.panelJuegos4D.buscarProducto) {
				if(!this.panelJuegos4D.campoCodigoProducto.getText().equals("")) {
					if(EsNumerico.comprobar(this.panelJuegos4D.campoCodigoProducto.getText())) {
						final int codigo=Integer.parseInt(this.panelJuegos4D.campoCodigoProducto.getText());
						if((codigo>=100000) && (codigo<=999999)) {
							this.auxProducto=principal.buscarJuego(codigo);
							if(this.auxProducto.getCodigo()!=0) {
								this.panelJuegos4B=new PanelJuegos4B((Juego) this.auxProducto);
								this.panelJuegos4B.btnCantidad.addActionListener(this);
								panel.remove(this.panelJuegos4D);
								panel.add(this.panelJuegos4B);
								this.panelActual=this.panelJuegos4B;
							}
							else {
								JOptionPane.showMessageDialog(null, "¡Juego no encontrado!","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "¡El código debe ser de 6 dígitos!","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "¡El código solo debe contener números!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}




			/**
			 * Acción del botón btnCantidad del panel panelPeliculas1E1.
			 * 
			 * Al activarse el btnCantidad se le suma a la película mostrada
			 * la cantidad que está escrita en el campo campoCant.
			 */
			if((this.panelPeliculas1E1!=null) && (e.getSource()==this.panelPeliculas1E1.btnCantidad)){
				if(!this.panelPeliculas1E1.campoCant.getText().equals("")) {
					if(EsNumerico.comprobar(this.panelPeliculas1E1.campoCant.getText())) {
						int cant=Integer.parseInt(this.panelPeliculas1E1.campoCant.getText());
						if(cant>=0 || (cant<0 && Math.abs(cant)<auxProducto.getCantidad())) {
							this.auxProducto.setCantidad(this.auxProducto.getCantidad()+Integer.parseInt(this.panelPeliculas1E1.campoCant.getText()));
							this.panelPeliculas1E1.campoCant.setText("");
							this.panelPeliculas1E1.campoCantidadPelicula.setText(Integer.toString(this.auxProducto.getCantidad()));
							JOptionPane.showMessageDialog(null, "¡Cantidad sumada exitósamente!");
							auxProducto.modificarDatos();
						}
						else {
							this.auxProducto.setCantidad(0);
							this.panelPeliculas1E1.campoCant.setText("");
							this.panelPeliculas1E1.campoCantidadPelicula.setText(Integer.toString(this.auxProducto.getCantidad()));
							JOptionPane.showMessageDialog(null, "¡Cantidad sumada exitósamente!");
							auxProducto.modificarDatos();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "¡El campo solo debe contener números!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}




			


			/**
			 * Acción del botón btnCantidad del panel panelJuegos4B.
			 * 
			 * Al activarse el btnCantidad se le suma al juego mostrado
			 * la cantidad que está escrita en el campo campoCant.
			 */
			if((this.panelJuegos4B!=null) && (e.getSource()==this.panelJuegos4B.btnCantidad)){
				if(!this.panelJuegos4B.campoCant.getText().equals("")) {
					if(EsNumerico.comprobar(this.panelJuegos4B.campoCant.getText())) {
						int cant=Integer.parseInt(panelJuegos4B.campoCant.getText());
						if(cant>=0 || (cant<0 && Math.abs(cant)<auxProducto.getCantidad())) {
							this.auxProducto.setCantidad(this.auxProducto.getCantidad()+Integer.parseInt(this.panelJuegos4B.campoCant.getText()));
							this.panelJuegos4B.campoCant.setText("");
							this.panelJuegos4B.campoCantidadJuego.setText(Integer.toString(this.auxProducto.getCantidad()));
							JOptionPane.showMessageDialog(null, "¡Cantidad sumada exitósamente!");
							auxProducto.modificarDatos();
						}
						else {
							this.auxProducto.setCantidad(0);
							this.panelJuegos4B.campoCant.setText("");
							this.panelJuegos4B.campoCantidadJuego.setText(Integer.toString(this.auxProducto.getCantidad()));
							JOptionPane.showMessageDialog(null, "¡Cantidad sumada exitósamente!");
							auxProducto.modificarDatos();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "¡El campo solo debe contener números!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}







			/**
			 * Acción del botón modificar del panel panelPeliculas1D1
			 * 
			 * Al activar este botón se sobreescriben todos los datos de ptrPelicula
			 * (la cual fue buscada en el panel panelPeliculasPB) con los datos
			 * de los campos del panel panelPeliculas1D1.
			 * 
			 */
			if((this.panelPeliculas1D1!=null) && (e.getSource()==this.panelPeliculas1D1.modificar)) {
				if(!this.panelPeliculas1D1.campoCantidadPelicula.getText().equals("") && !this.panelPeliculas1D1.campoAnoEstreno.getText().equals("") && !this.panelPeliculas1D1.campoCodigoPelicula.getText().equals("") && !this.panelPeliculas1D1.campodescripcionPelicula.getText().equals("") && !this.panelPeliculas1D1.campoNombrePelicula.getText().equals("") && !this.panelPeliculas1D1.campoPrecioArriendo.getText().equals("") && !this.panelPeliculas1D1.campoPrecioVenta.getText().equals("")) {
					final int cod=Integer.parseInt(this.panelPeliculas1D1.campoCodigoPelicula.getText());
					if((cod>=1000) && (cod<=99999)) {
						int op=0;
						try {
							this.auxProducto.setCantidad(Integer.parseInt(this.panelPeliculas1D1.campoCantidadPelicula.getText()));
							((Pelicula) this.auxProducto).setEsEstreno(this.panelPeliculas1D1.campoEsEstreno.isSelected());
							((Pelicula) this.auxProducto).setAnoEstreno(Integer.parseInt(this.panelPeliculas1D1.campoAnoEstreno.getText()));
							this.auxProducto.setCodigo(Integer.parseInt(this.panelPeliculas1D1.campoCodigoPelicula.getText()));
							this.auxProducto.setDescripcion(this.panelPeliculas1D1.campodescripcionPelicula.getText());
							this.auxProducto.setNombre(this.panelPeliculas1D1.campoNombrePelicula.getText());
							this.auxProducto.setPrecioArriendo(Integer.parseInt(this.panelPeliculas1D1.campoPrecioArriendo.getText()));
							this.auxProducto.setPrecioVenta(Integer.parseInt(this.panelPeliculas1D1.campoPrecioVenta.getText()));
						}
						catch(final Exception e2) {
							JOptionPane.showMessageDialog(null,"¡Formato de datos incorrecto!","Error",JOptionPane.ERROR_MESSAGE);
							op=1;
						}
						if(op==0) {
							this.auxProducto.modificarDatos();
							if(op==0) {
								this.setVentanaPeliculasPB();
								JOptionPane.showMessageDialog(null, "¡Película modificada exitósamente!");
								this.panelLogo.volver.doClick();
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"El código debe ser de 4 a 5 dígitos","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Existe un campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}






			/**
			 * Acción del botón modificar del panel panelJuegos4C
			 * 
			 * Al activar este botón se sobreescriben todos los datos de ptrJuego
			 * (la cual fue buscada en el panel panelJuegosPB) con los datos
			 * de los campos del panel panelJuegos4C.
			 * 
			 */
			if((this.panelJuegos4C!=null) && (e.getSource()==this.panelJuegos4C.modificar)) {
				if(!this.panelJuegos4C.existeCampoVacio()) {
					final int cod=Integer.parseInt(this.panelJuegos4C.campoCodigoJuego.getText());
					if((cod>=100000) && (cod<=999999)) {
						int op=0;
						try {
							this.auxProducto.setCantidad(Integer.parseInt(this.panelJuegos4C.campoCantidadJuego.getText()));
							((Juego) this.auxProducto).setClasificacion(this.panelJuegos4C.campoClasificacion.getText());
							this.auxProducto.setNombre(this.panelJuegos4C.campoNombreJuego.getText());
							this.auxProducto.setCodigo(Integer.parseInt(this.panelJuegos4C.campoCodigoJuego.getText()));
							((Juego) this.auxProducto).setJugadoresMaximos(Integer.parseInt(this.panelJuegos4C.campoMaximoJugadores.getText()));
							this.auxProducto.setDescripcion(this.panelJuegos4C.campoDescripcionJuego.getText());
							this.auxProducto.setPrecioArriendo(Integer.parseInt(this.panelJuegos4C.campoPrecioArriendo.getText()));
							this.auxProducto.setPrecioVenta(Integer.parseInt(this.panelJuegos4C.campoPrecioVenta.getText()));
							this.auxProducto.setGeneros((ArrayList<String>) this.panelJuegos4C.listaTipos.getSelectedValuesList());
							((Juego) this.auxProducto).setConsolas((ArrayList<String>) this.panelJuegos4C.listaConsolas.getSelectedValuesList());
						}
						catch(final Exception e2) {
							JOptionPane.showMessageDialog(null,"¡Formato de datos incorrecto!","Error",JOptionPane.ERROR_MESSAGE);
							op=1;
						}
						if(op==0) {
							this.auxProducto.modificarDatos();
							this.setVentanaJuegosPB();
							JOptionPane.showMessageDialog(null, "¡Juego modificado exitósamente!");
							this.panelLogo.volver.doClick();
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"El código debe ser de 6 dígitos","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Existen campos sin completar!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}




			/**
			 * Acción del botón reporte del panel panelPeliculasPB.
			 * 
			 * Al activar este botón se hace una llamada a {@link Exportable)}
			 * (método implementado) para generar un reporte de películas.
			 * 
			 * Se muestra con ventanas emergentes si fue creado con éxito.
			 * 
			 */
			if(e.getSource()==this.panelPeliculasPB.reporte) {
				boolean flag=false;
				String ruta=Exportable.directorio();
				flag = principal.generarReportePeliculas(ruta);
				if(flag) 
					JOptionPane.showMessageDialog(null, "Reporte almacenado exitósamente en: "+ruta);
				else
					JOptionPane.showMessageDialog(null, "¡Error al generar el reporte!","Error",JOptionPane.ERROR_MESSAGE);
			}




			/**
			 * Acción del botón reporte del panel panelClientesPB.
			 * 
			 * Al activar este botón se hace una llamada a a {@link Exportable)}
			 * (método implementado) para generar un reporte de clientes.
			 * 
			 * Se muestra con ventanas emergentes si fue creado con éxito.
			 * 
			 */
			if((this.panelClientesPB!=null) && (e.getSource()==this.panelClientesPB.reporte)) {
				boolean flag=false;
				String ruta=Exportable.directorio();
				flag = principal.generarReporteClientes(ruta);
				if(flag) 
					JOptionPane.showMessageDialog(null, "Reporte almacenado exitósamente en: "+ruta);
				else
					JOptionPane.showMessageDialog(null, "¡Error al generar el reporte!","Error",JOptionPane.ERROR_MESSAGE);
			}





			
			/**
			 * Accion para ingresar al panel reporteArriendo
			 * desde panelVentaArriendo
			 */
			if(e.getSource()==this.panelVentaArriendo.reporteArriendo) {
				panel.remove(panelVentaArriendo);
				reporteArriendo=new ReporteArriendo(principal.clonarListaArriendos());
				reporteArriendo.reporte.addActionListener(this);
				panel.add(reporteArriendo);
				panelActual=reporteArriendo;
			}
			
			
			
			
			
			
			/**
			 * Acción del botón reporte del panel reporteArriendo.
			 * 
			 * Al activar este botón se hace una llamada a a {@link Exportable)}
			 * (método implementado) para generar un reporte de arriendos.
			 * 
			 * Se informa con ventanas emergentes si fue creado con éxito.
			 * 
			 */
			if(reporteArriendo!=null && e.getSource()==this.reporteArriendo.reporte) {
				boolean flag=false;
				String ruta=Exportable.directorio();
				flag = principal.generarReporteArriendos(ruta);
				if(flag) 
					JOptionPane.showMessageDialog(null, "Reporte almacenado exitósamente en: "+ruta);
				else
					JOptionPane.showMessageDialog(null, "¡Error al generar el reporte!","Error",JOptionPane.ERROR_MESSAGE);
			}


			
		
			
			
			/**
			 * Accion para ingresar al panel reporteVenta
			 * desde panelVentaArriendo
			 */
			if(e.getSource()==this.panelVentaArriendo.reporteVenta) {
				panel.remove(panelVentaArriendo);
				reporteVenta=new ReporteVenta(principal.clonarListaVenta());
				reporteVenta.reporte.addActionListener(this);
				panel.add(reporteVenta);
				panelActual=reporteVenta;
			}
			
			
			
			
			
			
			
			
			/**
			 * Acción del botón reporte del panel reporteVenta.
			 * 
			 * Al activar este botón se hace una llamada a a {@link Exportable)}
			 * (método implementado) para generar un reporte de ventas.
			 * 
			 * Se informa con ventanas emergentes si fue creado con éxito.
			 * 
			 */
			if(reporteVenta!=null && e.getSource()==this.reporteVenta.reporte) {
				boolean flag=false;
				String ruta=Exportable.directorio();
				flag = principal.generarReporteVentas(ruta);
				if(flag) 
					JOptionPane.showMessageDialog(null, "Reporte almacenado exitósamente en: "+ruta);
				else
					JOptionPane.showMessageDialog(null, "¡Error al generar el reporte!","Error",JOptionPane.ERROR_MESSAGE);
			}





			/**
			 * Acción del botón reporte del panel panelJuegosPB.
			 * 
			 * Al activar este botón se hace una llamada a a {@link Exportable)}
			 * (método implementado) para generar un reporte de juegos.
			 * 
			 * Se informa con ventanas emergentes si fue creado con éxito.
			 * 
			 */
			if(e.getSource()==this.panelJuegosPB.reporte) {
				boolean flag=false;
				String ruta=Exportable.directorio();
				flag = principal.generarReporteJuegos(ruta);
				if(flag) 
					JOptionPane.showMessageDialog(null, "Reporte almacenado exitósamente en: "+ruta);
				else
					JOptionPane.showMessageDialog(null, "¡Error al generar el reporte!","Error",JOptionPane.ERROR_MESSAGE);
			}





			/**
			 * Accion para ingresar al panel panelClientes2A
			 * desde panelClientesPB
			 */
			if(e.getSource()==this.panelClientesPB.agregar) {
				panel.remove(this.panelClientesPB);
				panel.add(this.panelClientes2A);
				this.panelClientes2A.campoNombreCliente.setText("");
				this.panelClientes2A.campoRutCliente.setText("");
				this.panelClientes2A.campoDirCliente.setText("");
				this.panelActual=this.panelClientes2A;
			}






			/**
			 * Accion para ingresar al panel panelClientes2D
			 * desde panelClientesPB
			 */
			if(e.getSource()==this.panelClientesPB.buscar) {
				this.panelClientes2D.campoRutCliente.setText("");
				panel.remove(this.panelClientesPB);
				panel.add(this.panelClientes2D);
				this.panelActual=this.panelClientes2D;
			}






			/**
			 * Acción del botón confirmar del panel panelClientes2C1
			 * 
			 * Al activar el botón se reemplazan los datos de ptrCliente
			 * (el cual fue buscado en el panel panelClientesPB) por los
			 * datos de los campos del panel panelClientes2C1. Se comprueba
			 * que los datos sean válidos.
			 */
			if((this.panelClientes2C1!=null) && (e.getSource()==this.panelClientes2C1.confirmar)){
				if((this.panelClientes2C1.campoNombreCliente.getText()!="") && (this.panelClientes2C1.campoDirCliente.getText()!="")) {
					this.auxCliente.setNombreCliente(this.panelClientes2C1.campoNombreCliente.getText());
					this.auxCliente.setDireccion(this.panelClientes2C1.campoDirCliente.getText());
	
					this.auxCliente.modificarDatos();
					
					JOptionPane.showMessageDialog(null, "¡Cliente modificado exitósamente!");
					this.panelLogo.volver.doClick();
				}
			}





			/**
			 * Acción del botón guardar del panel panelClientes2A
			 * 
			 * Al activar este botón se crea un nuevo objeto {@link Cliente}
			 * con los datos de los campos del panel panelClientes2A.
			 * 
			 * Si el creado fue exitoso (datos de los campos son válidos)
			 * el objeto es agregado a la lista de clientes de Principal.
			 * 
			 */
			if(e.getSource()==this.panelClientes2A.guardar) {
				final String ptrNomCli=this.panelClientes2A.campoNombreCliente.getText();
				String ptrRut=this.panelClientes2A.campoRutCliente.getText();
				ptrRut=ptrRut.replace("-", "");
				ptrRut=ptrRut.replace(".","");
				final String ptrDir=this.panelClientes2A.campoDirCliente.getText();
				if(!ptrNomCli.equals("") && !ptrDir.equals("")) {
					if(!ptrNomCli.equals("*ELIMINADO*")) {
						try {
							final Historial hist=new Historial(ptrRut,0,0);
							final Cliente cli=new Cliente(ptrNomCli,ptrRut,0,ptrDir);
							
							cli.agregarDatos();
							hist.agregarDatos();
							
							principal.agregarHistorial(hist);
							principal.agregarCliente(cli);
							hist.setListaArriendos(principal.listaArriendosPorRUT(ptrRut));
							hist.setListaVentas(principal.listaVentasPorRUT(ptrRut));
							
							JOptionPane.showMessageDialog(null, "¡Cliente agregado exitósamente!\n\n"
									+ "Datos restaurados: \n"
									+ "Cantidad de arriendos : "+Integer.toString(hist.cantidadArriendos())
									+ "\nCantidad de ventas: "+Integer.toString(hist.getNumVentas()));
							if(!this.panelNegocios3Aventa.isVisible()) {
								this.remove(this.panelClientes2A);
								this.panelNegocios3Aventa.setVisible(true);
							}
							else {
								if(!this.panelNegocios3Aarriendo.isVisible()) {
									this.remove(this.panelClientes2A);
									this.panelNegocios3Aarriendo.setVisible(true);
								}
								else {
									this.panelLogo.volver.doClick();
								}
							}
							
						}
						catch(RUTException ex) {
						}
						catch(InformacionRegistradaException ex2) {
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "¡Nombre de cliente inválido!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Existe algún campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}





			/**
			 * Acción del botón buscarCliente del panel panelClientes2D
			 * 
			 * Al activar este botón se busca un cliente que posea el rut 
			 * escrito en el campo campoRutCliente del panel panelClientes2D.
			 * 
			 * Si es encontrado se dimensiona el panel panelClientes2D1 con
			 * los datos del cliente buscado.
			 * 
			 */
			if(e.getSource()==this.panelClientes2D.buscarCliente) {
				try {
					
					this.auxCliente=principal.buscarCliente(this.panelClientes2D.campoRutCliente.getText());
					if(!this.panelClientes2D.campoRutCliente.getText().equals("")) {
						if(!this.auxCliente.getNombreCliente().equals("*ELIMINADO*")) {
							final Historial hist=principal.buscarHistorial(this.auxCliente.getRut());
							this.panelClientes2D1=new PanelClientes2D1(this.auxCliente,hist);
							this.panelClientes2D1.historial.addActionListener(this);
							this.remove(this.panelClientes2D);
							this.add(this.panelClientes2D1);
							this.panelActual=this.panelClientes2D1;
						}
						else {
							JOptionPane.showMessageDialog(null, "¡Error al buscar cliente!","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}catch(RUTException ex) {
				}
			}





			/**
			 * Acción del botón historial del panel panelClientes2D1
			 * 
			 * Se dimensiona el panel panelClientes2D1A con los datos
			 * correspondientes al historial del cliente buscado en el panel
			 * panelClientes2D.
			 * 
			 */
			if((this.panelClientes2D1!=null) && (e.getSource()==this.panelClientes2D1.historial)) {
				final Historial hist=principal.buscarHistorial(this.auxCliente.getRut());
				this.setVentanaHistorial(hist.getNumArriendos(),hist.getNumVentas());
				this.add(this.panelClientes2D1A);
				this.remove(this.panelClientes2D1);
				this.panelActual=this.panelClientes2D1A;
			}





			/**
			 * Acción del botón arriendos de la ventanaClientes2D1A
			 * 
			 * Al activar este botón se inicializa un indice a 0
			 * para luego buscar un arriendo con esa posición en la
			 * lista de arriendos del cliente.
			 * 
			 * Si el arriendo es encontrado se dimensiona el panel
			 * panelClientesArriendo con los datos del arriendo.
			 * 
			 */
			if((this.panelClientes2D1A!=null) && (e.getSource()==this.panelClientes2D1A.arriendos)) {
				final Historial hist=principal.buscarHistorial(this.auxCliente.getRut());
				if((hist!=null) && (hist.getNumArriendos()!=0)) {
					if(hist.clonarListaArriendo()!=null) {
						this.panelClientesArriendoPB= new PanelClientesArriendoPB(hist.clonarListaArriendo());
					}
					this.remove(this.panelClientes2D1A);
					this.add(this.panelClientesArriendoPB);
					this.panelActual=this.panelClientesArriendoPB;
				}
				else {
					JOptionPane.showMessageDialog(null, "¡No existen arriendos realizados!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}






			/**
			 * Acción del botón ventas de la ventanaClientes2D1A
			 * 
			 * Al activar este botón se inicializa un indice a 0
			 * para luego buscar una venta con esa posición en la
			 * lista de ventas del cliente.
			 * 
			 * Si la venta es encontrada se dimensiona el panel
			 * panelClientesVentas con los datos de la venta.
			 * 
			 */
			if((this.panelClientes2D1A!=null) && (e.getSource()==this.panelClientes2D1A.ventas)) {
				final Historial hist=principal.buscarHistorial(this.auxCliente.getRut());
				if((hist!=null) && (hist.getNumVentas()!=0)) {
					this.panelClientesVentaPB=new PanelClientesVentaPB(hist.clonarListaVenta());
					this.remove(this.panelClientes2D1A);
					this.add(this.panelClientesVentaPB);
					this.panelActual=this.panelClientesVentaPB;
				}
				else {
					JOptionPane.showMessageDialog(null, "¡No existen ventas realizadas!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}





			/**
			 * Accion para ingresar al panel panelNegocios3Aarriendo
			 * desde panelVentaArriendo
			 */
			if(e.getSource()==this.panelVentaArriendo.registrarArriendo) {
				panel.remove(this.panelVentaArriendo);
				panel.add(this.panelNegocios3Aarriendo);
				panelNegocios3Aarriendo.setVisible(true);
				this.panelActual=this.panelNegocios3Aarriendo;
			}





			/**
			 * Acción del botón siguiente del panel panelNegocios3Aarriendo
			 * 
			 * Al activar este botón se busca un cliente con el texto del
			 * campo campoRutCliente del panel panelNegocios3Aarriendo.
			 * 
			 * Si es encontrado se guarda en ptrCliente y se cambia el panel
			 * por el panel panelNegocios3A1arriendo. 
			 * 
			 * Además se comprueba si el cliente cumple con los requisitos
			 * para subir de rango(tipo).
			 * 
			 */
			if(e.getSource()==this.panelNegocios3Aarriendo.siguiente) {
				if(!this.panelNegocios3Aarriendo.campoRutCliente.getText().equals("")) {
						try {
							this.auxCliente=principal.buscarCliente(this.panelNegocios3Aarriendo.campoRutCliente.getText());
							final Historial hist=principal.buscarHistorial(this.auxCliente.getRut());
							if(!this.auxCliente.getNombreCliente().equals("*ELIMINADO*")) {
								if(hist.cantidadArriendosCompletados()==hist.cantidadArriendos()) {
									this.panelNegocios3Aarriendo.campoRutCliente.setText("");
									if(this.auxCliente.actualizarTipoCliente(hist.getNumArriendos(),hist.getNumVentas(),hist.getPenalizacion())) {
										JOptionPane.showMessageDialog(null, "¡El cliente ha subido de rango!\nNuevo rango: "+this.auxCliente.getTipoCliente());
										auxCliente.modificarDatos();
									}
									this.remove(this.panelNegocios3Aarriendo);
									this.panelNegocios3A1arriendo=new PanelNegocios3A1(principal.listaPeliculasDisponibles(),principal.listaJuegosDisponibles());
									this.panelNegocios3A1arriendo.siguiente.addActionListener(this);
									this.add(this.panelNegocios3A1arriendo);
									this.panelActual=this.panelNegocios3A1arriendo;
								}
								else {
									JOptionPane.showMessageDialog(null, "¡El cliente no puede realizar un arriendo ya que posee un arriendo pendiente!","Error",JOptionPane.ERROR_MESSAGE);
								}
							}
							else {
								final int opcion=JOptionPane.showConfirmDialog(null,"!El cliente no existe!\n¿desea agregarlo?");
								if(opcion==JOptionPane.YES_OPTION) {
									this.panelClientes2A.campoRutCliente.setText(this.panelNegocios3Aarriendo.campoRutCliente.getText());
									this.panelClientes2A.campoRutCliente.setEditable(false);
									this.add(this.panelClientes2A);
									this.panelNegocios3Aarriendo.setVisible(false);
								}
								else {
									if(opcion==JOptionPane.NO_OPTION) {
										JOptionPane.showMessageDialog(null, "¡Debe registrar el RUT para realizar el arriendo!","Error",JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						}catch(RUTException ex) {
						}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}





			/**
			 * Acción del botón siguiente del panel panelNegocios3A1arriendo
			 * 
			 * Al activar este botón se busca una película con el texto del
			 * campo campoIdPelicula del panel panelNegocios3A1arriendo.
			 * 
			 * Si es encontrada se guarda en ptrPelicula y se cambia el panel
			 * por el panel panelNegocios3A1Aarriendo.
			 * 
			 */
			if((this.panelNegocios3A1arriendo!=null) && (e.getSource()==this.panelNegocios3A1arriendo.siguiente)) {
				if(!this.panelNegocios3A1arriendo.campoIdPelicula.getText().equals("")) {
					try {
						final int cod=Integer.parseInt(this.panelNegocios3A1arriendo.campoIdPelicula.getText());
						if((cod>=1000) && (cod<=99999))
							this.auxProducto=principal.buscarPelicula(cod);
						else
							this.auxProducto=principal.buscarJuego(cod);
					}
					catch(final java.lang.NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "¡Tipo de dato erroneo (solo números)!","Error",JOptionPane.ERROR_MESSAGE);
						this.auxProducto=null;
					}
					if((this.auxProducto!=null) && (this.auxProducto.getCantidad()>0)) {
						this.panelNegocios3A1arriendo.campoIdPelicula.setText("");
						this.add(this.panelNegocios3A1Aarriendo);
						this.remove(this.panelNegocios3A1arriendo);
						this.panelActual=this.panelNegocios3A1Aarriendo;
					}
					else {
						JOptionPane.showMessageDialog(null, "¡Película no encontrada/no hay stock!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!");
				}
				this.panelNegocios3A1Aarriendo.campoDias.setText("");
			}





			/**
			 * Acción del botón siguiente del panel panelNegocios3A1Aarriendo 
			 * 
			 * Al activar este botón se extrae el texto del campo campoDias
			 * del panel panelNegocios3A1Aarriendo el cual corresponde a los
			 * días del arriendo de la película.
			 * 
			 * Si los días son válidos se calcula la ganancia o precio que traerá
			 * realizar el arriendo y se guarda en ptrGanancia.
			 * 
			 * Formula: ganancia = PRECIO_ARR_PEL+((DIAS-5)*500)-(PRECIO_ARR_PEL*((TIPO_CLIENTE)/10);
			 */
			if(e.getSource()==this.panelNegocios3A1Aarriendo.siguiente) {
				if(!this.panelNegocios3A1Aarriendo.campoDias.getText().equals("")) {
					int dias=0;
					try {
						dias=Integer.parseInt(this.panelNegocios3A1Aarriendo.campoDias.getText());
					}
					catch(final Exception ex) {
						JOptionPane.showMessageDialog(null, "¡Dato inválido!");
						dias=0;
					}
					if(dias>=5) {
						this.auxGanancia=(this.auxProducto.getPrecioArriendo()+((dias-5)*500))-((this.auxProducto.getPrecioArriendo()*this.auxCliente.getTipoCliente())/10);
						if((this.auxProducto instanceof Pelicula) && ((Pelicula) this.auxProducto).isEsEstreno()) {
							this.auxGanancia+=(this.auxProducto.getPrecioArriendo()/2);
						}
						this.remove(this.panelNegocios3A1Aarriendo);
						this.add(this.panelNegocios3A1A1arriendo);
						this.panelNegocios3A1A1arriendo.campoMonto.setText(Integer.toString(this.auxGanancia));
						this.panelActual=this.panelNegocios3A1A1arriendo;
					}
					else
						JOptionPane.showMessageDialog(null, "¡Días mínimos de arriendo: 5!","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!");
				}
			}





			/**
			 * Acción del botón guardar del panel panelNegocios3A1A1arriendo
			 * 
			 * Al activar el botón se dimensiona un nuevo arriendo con los datos 
			 * guardados anteriormente (ptrCliente,ptrPelicula,ptrGanancia).
			 * 
			 * Si el creado es exitoso, se guarda el arriendo en el cliente y el
			 * negocio en la lista de negocios.
			 * 
			 * Además se hacen las llamadas a los métodos correspondientes para 
			 * la actualización de los datos en la base de datos.
			 * 
			 */
			if(e.getSource()==this.panelNegocios3A1A1arriendo.guardar) {
				try {
					final Arriendo arr=new Arriendo(this.auxCliente,this.auxProducto,Integer.parseInt(this.panelNegocios3A1Aarriendo.campoDias.getText()));
					final Historial hist=principal.buscarHistorial(this.auxCliente.getRut());
					
					this.panelLogo.volver.doClick();
					this.panelLogo.volver.doClick();
					this.panelLogo.volver.doClick();
					this.panelLogo.volver.doClick();
					
					Historial hist2=hist.clonarObjeto();
					ProductoVideoClub prod2=auxProducto.clonarObjeto();
					
					this.agregarMemento();
					restaurables.add(hist2);
					restaurables.add(prod2);
					restaurables.add(arr);
					
					auxProducto.setCantidad(auxProducto.getCantidad()-1);
					hist.agregarArriendo(arr);
					principal.agregarArriendo(arr);
					
					JOptionPane.showMessageDialog(null, "¡Arriendo agregado exitósamente!\nCódigo arriendo: "+arr.getCodigo());
					
					estado=1;
					
					hist.modificarDatos();
					auxProducto.modificarDatos();
					arr.agregarDatos();
					
					this.panelSecundaria.getContentPane().removeAll();
					this.panelSecundaria.setSize(640, 480);
					this.panelSecundaria.setLocationRelativeTo(null);
					if(this.auxProducto instanceof Pelicula)
						this.panelSecundaria.add(new ProductosRecomendados(principal.recomendacionPeliculas((Pelicula) this.auxProducto)));
					else
						this.panelSecundaria.add(new ProductosRecomendados(principal.recomendacionJuegos((Juego) this.auxProducto)));
					this.panelSecundaria.setVisible(true);
					System.out.println("Codigo arriendo :"+arr.getCodigo());
					System.out.println("Fecha inicio: "+arr.getFechaInicio().getTimeInMillis());
					System.out.println("Fecha entrega: "+arr.getFechaEntrega().getTimeInMillis());
				}catch(InformacionRegistradaException ex) {
					
				}
				
			}





			/**
			 * Accion para ingresar al panel panelNegocios3Aventa
			 * desde panelVentaArriendo
			 */			
			if(e.getSource()==this.panelVentaArriendo.registrarVenta) {
				panel.remove(this.panelVentaArriendo);
				panel.add(this.panelNegocios3Aventa);
				panelNegocios3Aventa.setVisible(true);
				this.panelActual=this.panelNegocios3Aventa;
			}





			/**
			 * Acción del botón siguiente del panel panelNegocios3Aventa
			 * 
			 * Al activar este botón se busca un cliente con el texto del
			 * campo campoRutCliente del panel panelNegocios3Aventa.
			 * 
			 * Si es encontrado se guarda en ptrCliente y se cambia el panel
			 * por el panel panelNegocios3A1venta. 
			 * 
			 * Además se comprueba si el cliente cumple con los requisitos
			 * para subir de rango(tipo).
			 */
			if(e.getSource()==this.panelNegocios3Aventa.siguiente) {
				if(!this.panelNegocios3Aventa.campoRutCliente.getText().equals("")) {
					try {
						this.auxCliente=principal.buscarCliente(this.panelNegocios3Aventa.campoRutCliente.getText());
						final Historial hist=principal.buscarHistorial(this.auxCliente.getRut());
						if(!this.auxCliente.getNombreCliente().equals("*ELIMINADO*")) {
							if(hist.cantidadArriendosCompletados()==hist.cantidadArriendos()) {
								if(this.auxCliente.actualizarTipoCliente(hist.getNumArriendos(),hist.getNumVentas(),hist.getPenalizacion())) {
									JOptionPane.showMessageDialog(null, "¡El cliente ha subido de rango!\nNuevo rango: "+this.auxCliente.getTipoCliente());
									auxCliente.eliminarDatos();
									auxCliente.agregarDatos();
								}
								this.panelNegocios3Aventa.campoRutCliente.setText("");
								this.remove(this.panelNegocios3Aventa);
								this.panelNegocios3A1venta=new PanelNegocios3A1(principal.listaPeliculasDisponibles(),principal.listaJuegosDisponibles());
								this.panelNegocios3A1venta.siguiente.addActionListener(this);
								this.add(this.panelNegocios3A1venta);
								this.panelActual=this.panelNegocios3A1venta;
							}
							else {
								JOptionPane.showMessageDialog(null, "¡El cliente no puede realizar una venta ya que posee un arriendo pendiente!","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							final int opcion=JOptionPane.showConfirmDialog(null,"!El cliente no existe!\n¿desea agregarlo?");
							if(opcion==JOptionPane.YES_OPTION) {
								this.panelClientes2A.campoRutCliente.setText(this.panelNegocios3Aventa.campoRutCliente.getText());
								this.panelClientes2A.campoRutCliente.setEditable(false);
								this.add(this.panelClientes2A);
								this.panelNegocios3Aventa.setVisible(false);
							}
							else {
								if(opcion==JOptionPane.NO_OPTION) {
									JOptionPane.showMessageDialog(null, "¡Debe registrar el RUT para realizar la venta!","Error",JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}catch(RUTException ex) {
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}





			/**
			 * Acción del botón siguiente del panel panelNegocios3A1venta
			 * 
			 * Al activar este botón se busca una película con el texto del
			 * campo campoIdPelicula del panel panelNegocios3A1venta.
			 * 
			 * Si es encontrada se guarda en ptrPelicula y se cambia el panel
			 * por el panel panelNegocios3A1Aarriendo.
			 * 
			 * Además, se calcula la ganancia que dará la venta de la película 
			 * la cual se guarda en ptrGanancia.
			 * 
			 * formula: ganancia = PRECIO_VEN_PEL-(PRECIO_VEN_PEL*(TIPO_CLIENTE/10))+IF(ES_ESTRENO)->{PRECIO_VEN_PEL/2}
			 * 
			 */
			if((this.panelNegocios3A1venta!=null) && (e.getSource()==this.panelNegocios3A1venta.siguiente)) {
				if(!this.panelNegocios3A1venta.campoIdPelicula.getText().equals("")) {
					final int cod=Integer.parseInt(this.panelNegocios3A1venta.campoIdPelicula.getText());
					try {
						if((cod>=1000) && (cod<=9999))
							this.auxProducto=principal.buscarPelicula(cod);
						else
							this.auxProducto=principal.buscarJuego(cod);
					}
					catch(final java.lang.NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "¡Tipo de dato erroneo (solo números)!","Error",JOptionPane.ERROR_MESSAGE);
						this.auxProducto=null;
						this.auxProducto=null;
					}
					if((this.auxProducto!=null) && (this.auxProducto.getCantidad()>0)) {
						this.panelNegocios3A1venta.campoIdPelicula.setText("");
						this.auxGanancia=this.auxProducto.getPrecioVenta()-((this.auxProducto.getPrecioVenta()*this.auxCliente.getTipoCliente())/10);
						if((this.auxProducto instanceof Pelicula) && ((Pelicula) this.auxProducto).isEsEstreno()) {
							this.auxGanancia+=(this.auxProducto.getPrecioVenta()/2);
						}
						this.panelNegocios3A1Aventa.campoMonto.setText(Integer.toString(this.auxGanancia));
						this.add(this.panelNegocios3A1Aventa);
						this.remove(this.panelNegocios3A1venta);
						this.panelActual=this.panelNegocios3A1Aventa;
					}
					else {
						JOptionPane.showMessageDialog(null, "¡Película no encontrada/sin stock!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}





			/**
			 * Acción del botón guardar del panel panelNegocios3A1Aventa
			 * 
			 * Al activar el botón se dimensiona una nueva venta con los datos 
			 * guardados anteriormente (ptrCliente,ptrPelicula,ptrGanancia).
			 * 
			 * Si el creado es exitoso, se guarda el venta en el cliente y el
			 * negocio en la lista de negocios.
			 * 
			 * Además se hacen las llamadas a los métodos correspondientes para 
			 * la actualización de los datos en la base de datos.
			 * 
			 */
			if(e.getSource()==this.panelNegocios3A1Aventa.guardar) {
				try {
					final Historial hist=principal.buscarHistorial(this.auxCliente.getRut());
					
					Historial hist2=hist.clonarObjeto();
					ProductoVideoClub prod2=auxProducto.clonarObjeto();
					
					this.panelLogo.volver.doClick();
					this.panelLogo.volver.doClick();
					this.panelLogo.volver.doClick();
					
					this.agregarMemento();
					restaurables.add(hist2);
					restaurables.add(prod2);
					estado=1;
					
					final Venta ven=hist.agregarVenta(this.auxCliente, this.auxProducto);
					
					restaurables.add(ven);
					
					principal.agregarVenta(ven);
					auxProducto.setCantidad(auxProducto.getCantidad()-1);
					
					ven.agregarDatos();
					auxProducto.modificarDatos();
					hist.modificarDatos();
					
					JOptionPane.showMessageDialog(null, "¡Venta agregada exitósamente!");
					this.panelSecundaria.getContentPane().removeAll();
					this.panelSecundaria.setSize(640, 480);
					this.panelSecundaria.setLocationRelativeTo(null);
					if(this.auxProducto instanceof Pelicula)
						this.panelSecundaria.add(new ProductosRecomendados(principal.recomendacionPeliculas((Pelicula) this.auxProducto)));
					else
						this.panelSecundaria.add(new ProductosRecomendados(principal.recomendacionJuegos((Juego) this.auxProducto)));
					this.panelSecundaria.setVisible(true);
				}catch(InformacionRegistradaException ex) {
				}
			}





			/**
			 * Acción del botón gananciaTotal del panel panelVentaArriendo
			 * 
			 * Al activar este botón se insertan los datos totales de
			 * la lista de negocios y se muestran en el panel panelNegocios3C.
			 * 
			 */
			if(e.getSource()==this.panelVentaArriendo.gananciaTotal) {
				panel.remove(this.panelVentaArriendo);
				this.panelNegocios3C.campoTotalGanancia.setText(Integer.toString(principal.gananciaArriendos()+principal.gananciaVentas())+"$ CLP");
				this.panelNegocios3C.campoNumeroCliente.setText(Integer.toString(principal.cantidadClientes()));
				this.panelNegocios3C.campoNumeroNegocios.setText(Integer.toString(principal.cantidadArriendos()+principal.cantidadVentas()));
				panel.add(this.panelNegocios3C);
				this.panelActual=this.panelNegocios3C;
			}



			/**
			 * Accion para ingresar al panel panelNegocios3B
			 * desde panelVentaArriendo
			 */
			if(e.getSource()==this.panelVentaArriendo.cancelarNegocio) {
				this.panelNegocios3B=new PanelNegocios3B(principal.listaArriendoVenta());
				this.panelNegocios3B.confirmar.addActionListener(this);
				panel.remove(this.panelVentaArriendo);
				panel.add(this.panelNegocios3B);
				this.panelActual=this.panelNegocios3B;
			}




			/**
			 * Acción del botón confirmar del panel panelNegocios3B
			 * 
			 * Al activar este botón se busca el arriendo o venta correspondiente
			 * al código escrito en el campoCodNegocio.
			 * 
			 * Luego se verifica si el código es una venta o un arriendo y 
			 * se cancela. 
			 * 
			 * Si la cancelación es exitosa, se actualizan los datos 
			 * de películas y arriendos o ventas según corresponda.
			 * 
			 */
			if((this.panelNegocios3B!= null) && (e.getSource()==this.panelNegocios3B.confirmar)) {
				final String cod=this.panelNegocios3B.campoCodNegocio.getText();
				if(cod!=null) {
					final Arriendo arr=(Arriendo )principal.buscarArriendo(cod);
					final Venta ven=(Venta) principal.buscarVenta(cod);
					if((arr!=null) && (ven==null)) {
						if(arr.cancelar()) {
							ProductoVideoClub prod=principal.buscarProducto(arr.getCodigoProducto());
							prod.setCantidad(prod.getCantidad()+1);
							arr.modificarDatos();
							prod.modificarDatos();
							JOptionPane.showMessageDialog(null, "¡Arriendo cancelado exitósamente!");
							this.panelLogo.volver.doClick();
						}
						else {
							JOptionPane.showMessageDialog(null, "¡Error al cancelar el arriendo!","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						if((arr==null) && (ven!=null)) {
							if(ven.cancelar()) {
								ven.eliminarDatos();
								ven.agregarDatos();
								principal.buscarProducto(ven.getCodigoProducto()).eliminarDatos();
								principal.buscarProducto(ven.getCodigoProducto()).agregarDatos();
								JOptionPane.showMessageDialog(null, "¡Venta cancelada exitósamente!");
								this.panelLogo.volver.doClick();
							}
							else {
								JOptionPane.showMessageDialog(null, "¡Error al cancelar la venta!","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "¡Negocio no encontrado!","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}






			/**
			 * Acción del botón grafCantPel del panel panelNegocio3C
			 * 
			 * Al activar este botón se pide a la lista de negocios
			 * un gráfico el cual se muestra en una panel
			 * emergente llamada graficos.
			 * 
			 */
			if(e.getSource()==this.panelNegocios3C.grafCantPel) {
				this.panelSecundaria.getContentPane().removeAll();
				this.panelSecundaria.setSize(640, 480);
				this.panelSecundaria.setLocationRelativeTo(null);
				this.panelSecundaria.add(principal.mostrarGraficoPorCantidad());
				this.panelSecundaria.setVisible(true);
			}





			/**
			 * Acción del botón grafCantVAClientes del panel panelNegocios3C
			 * 
			 * Al activar este botón se pide a la lista de clientes
			 * un gráfico el cual se muestra en una panel
			 * emergente llamada graficos.
			 * 
			 */
			if(e.getSource()==this.panelNegocios3C.grafCantVAClientes) {
				this.panelSecundaria.getContentPane().removeAll();
				this.panelSecundaria.setSize(640, 480);
				this.panelSecundaria.setLocationRelativeTo(null);
				this.panelSecundaria.add(principal.mostrarGraficoVecesArriendoVenta());
				this.panelSecundaria.setVisible(true);
			}





			/**
			 * Acción del botón grafVAMeses del panel panelNegocios3C
			 * 
			 * Al activar este botón se pide a la lista de clientes
			 * un gráfico el cual se muestra en una panel
			 * emergente llamada graficos.
			 * 
			 */
			if(e.getSource()==this.panelNegocios3C.grafVAMeses) {
				this.panelSecundaria.getContentPane().removeAll();
				this.panelSecundaria.setSize(640, 480);
				this.panelSecundaria.setLocationRelativeTo(null);
				this.panelSecundaria.add(principal.mostrarGraficoVentaArriendoPorMes());
				this.panelSecundaria.setVisible(true);
			}




			/**
			 * Acción del botón grafTipoClientes del panel panelNegocios3C
			 * 
			 * Al activar este botón se pide a la lista de clientes
			 * un gráfico el cual se muestra en una panel
			 * emergente llamada graficos.
			 * 
			 */
			if(e.getSource()==this.panelNegocios3C.grafTipoClientes) {
				this.panelSecundaria.getContentPane().removeAll();
				this.panelSecundaria.setSize(640, 480);
				this.panelSecundaria.setLocationRelativeTo(null);
				this.panelSecundaria.add(principal.mostrarGraficoTipoCliente());
				this.panelSecundaria.setVisible(true);
			}





			/**
			 * Acción del botón grafGananciaSemanal del panel panelNegocios3C
			 * 
			 * Al activar este botón se pide a la lista de clientes
			 * un gráfico el cual se muestra en una panel
			 * emergente llamada graficos.
			 * 
			 */
			if(e.getSource()==this.panelNegocios3C.grafGananciaSemanal) {
				this.panelSecundaria.getContentPane().removeAll();

				final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				screenSize.setSize(screenSize.getWidth()-100, screenSize.getHeight()-100);
				this.panelSecundaria.setSize(screenSize);
				this.panelSecundaria.setLocationRelativeTo(null);
				this.panelSecundaria.add(principal.mostrarGraficoGananciaUltimaSemana());
				this.panelSecundaria.setVisible(true);
			}




			/**
			 * Accion para ingresar al panel panelNegocios3D
			 * desde panelVentaArriendo
			 */
			if(e.getSource()==this.panelVentaArriendo.devolucionArriendo) {
				panel.remove(this.panelVentaArriendo);
				this.panelNegocios3D=new PanelNegocios3D(principal.clonarListaArriendos());
				this.panelNegocios3D.devolver.addActionListener(this);
				this.panelNegocios3D.campoCodArriendo.setText("");
				panel.add(this.panelNegocios3D);
				this.panelActual=this.panelNegocios3D;
			}





			/**
			 * Acción del botón devolver del panel panelNegocios3D
			 * 
			 * Al activar este botón se extraen los datos de los campos
			 * del panel panelNegocios3D (correspondientes a un rut de 
			 * cliente, código de película y código de arriendo) para buscar
			 * el arriendo correcto.
			 * 
			 * Si se encuentra el arriendo es devuelto (cambiando su estado a
			 * arriendoCompletado).
			 * 
			 * Además, se hacen llamadas a métodos 
			 * 
			 */
			if((this.panelNegocios3D!=null) && (e.getSource()==this.panelNegocios3D.devolver)) {
				final String cod=this.panelNegocios3D.campoCodArriendo.getText();
				if(!cod.equals("")) {
					final Arriendo arr=(Arriendo) principal.buscarArriendo(cod);
					if(arr!=null) {
						if(!arr.isArriendoCompletado()) {
							Historial hist=principal.buscarHistorial(arr.getRutCliente());
							ProductoVideoClub prod=principal.buscarProducto(arr.getCodigoProducto());
							this.panelLogo.volver.doClick();
							
							Historial hist2=hist.clonarObjeto();
							ProductoVideoClub prod2=prod.clonarObjeto();
							Arriendo arr2=arr.clonarObjeto();

							this.agregarMemento();
							restaurables.add(hist2);
							restaurables.add(prod2);
							restaurables.add(arr2);
							estado=2;
							
							hist.devolverArriendo(cod);
							prod.setCantidad(prod.getCantidad()+1);
							
							
							prod.modificarDatos();
							arr.modificarDatos();
							hist.modificarDatos();
							
							JOptionPane.showMessageDialog(null, "¡Arriendo completado exitósamente!");
						}
						else {
							JOptionPane.showMessageDialog(null, "¡El arriendo ya ha sido completado!","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "¡El arriendo no existe!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Campo de texto vacío!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
			
			
			
			
			
			
			/**
			 * Accion para ingresar al panel panelJuegos4DX
			 * desde panelJuegosPB
			 */
			if(e.getSource()==panelJuegosPB.categorias) {
				panel.remove(panelJuegosPB);
				JuegosObservados array=new JuegosObservados(principal.clonarListaJuego());
				panelJuegos4DX=new PanelJuegos4DX(array);
				array.addObserver(panelJuegos4DX);
				panel.add(panelJuegos4DX);
				panelActual=panelJuegos4DX;
			}








			/*ACCIONES DE BOTONES VOLVER*/
			/**
			 * Para intercambiar paneles se guarda el panel
			 * actual en una variable llamada actual.
			 * 
			 * Al momento de activar el botón volver del panel
			 * paneLogo se intercambia el panel por el anterior
			 * dependiendo de qué panel está guardado en actual.
			 * 
			 */
			 if(e.getSource()==this.panelLogo.volver) {
				panelLogo.undo.setVisible(false);
				restaurables=new ArrayList<ModificadorDeTabla>();
				anterior.getMemento();
				/**
				 * si actual es panelPeliculas
				 * volver a panelPrincipal
				 */
				 if(this.panelActual.equals(this.panelProductos)) {
					panel.remove(this.panelProductos);
					panel.add(this.panelPrincipal);
					this.panelLogo.volver.setVisible(false);
					this.panelActual=this.panelPrincipal;
				}
				/**
				 * si actual es panelPeliculasPB
				 * volver a panelProductos
				 */
				else if(this.panelActual.equals(this.panelPeliculasPB)) {
					panel.remove(this.panelPeliculasPB);
					panel.add(this.panelProductos);
					this.panelActual=this.panelProductos;
				}
				/**
				 * si actual es panelJuegosPB
				 * volver a panelProductos
				 */
				else if(this.panelActual.equals(this.panelJuegosPB)) {
					panel.remove(this.panelJuegosPB);
					panel.add(this.panelProductos);
					this.panelActual=this.panelProductos;
				}
				/**
				 * si actual es panelClientesPB
				 * volver a panelPrincipal
				 */
				else if(this.panelActual.equals(this.panelClientesPB)) {
					panel.remove(this.panelClientesPB);
					panel.add(this.panelPrincipal);
					this.panelLogo.volver.setVisible(false);
					this.panelActual=this.panelPrincipal;
				}
				/**
				 * si actual es panelVentaArriendo
				 * volver a panelPrincipal
				 */
				else if(this.panelActual.equals(this.panelVentaArriendo)) {
					panel.remove(this.panelVentaArriendo);
					panel.add(this.panelPrincipal);
					this.panelLogo.volver.setVisible(false);
					this.panelActual=this.panelPrincipal;
				}
				/**
				 * si actual es panelPeliculas1A
				 * volver a panelPeliculas
				 */
				else if(this.panelActual.equals(this.panelPeliculas1AXPC)) {
					panel.remove(this.panelPeliculas1AXPC);
					panel.add(this.panelPeliculasPB);
					this.panelActual=this.panelPeliculasPB;
				}
				/**
				 * si actual es panelPeliculas1C
				 * volver a panelPeliculasPB
				 */
				else if(this.panelActual.equals(this.panelPeliculas1C)) {
					panel.remove(this.panelPeliculas1C);
					panel.add(this.panelPeliculasPB);
					this.panelActual=this.panelPeliculasPB;
				}
				/**
				 * si actual es panelJuegos4A
				 * volver a panelJuegosPB
				 */
				else if(this.panelActual.equals(this.panelJuegos4A)) {
					panel.remove(this.panelJuegos4A);
					panel.add(this.panelJuegosPB);
					this.panelActual=this.panelJuegosPB;
				}
				 /**
				 * si actual es panelJuegos4DX
				 * volver a panelJuegosPB
				 */
				else if((this.panelJuegos4DX!=null) && this.panelActual.equals(this.panelJuegos4DX)) {
					panel.remove(this.panelJuegos4DX);
					panel.add(this.panelJuegosPB);
					this.panelActual=this.panelJuegosPB;
				}
				/**
				 * si actual es panelPeliculas1D1
				 * volver a panelPeliculasPB
				 */
				else if(this.panelActual.equals(this.panelPeliculas1D1)) {
					panel.remove(this.panelPeliculas1D1);
					this.setVentanaPeliculasPB();
					panel.add(this.panelPeliculasPB);
					this.panelActual=this.panelPeliculasPB;
				}
				/**
				 * si actual es panelJuegos4C
				 * volver a panelJuegosPB
				 */
				else if(this.panelActual.equals(this.panelJuegos4C)) {
					panel.remove(this.panelJuegos4C);
					this.setVentanaJuegosPB();
					panel.add(this.panelJuegosPB);
					this.panelActual=this.panelJuegosPB;
				}
				/**
				 * si actual es panelPeliculas1E
				 * volver a panelPeliculasPB
				 */
				else if(this.panelActual.equals(this.panelPeliculas1E)) {
					panel.remove(this.panelPeliculas1E);
					this.panelPeliculas1E.campoCodigoProducto.setText("");
					panel.add(this.panelPeliculasPB);
					this.panelActual=this.panelPeliculasPB;
				}
				/**
				 * si actual es panelJuegos4D
				 * volver a panelJuegosPB
				 */
				else if(this.panelActual.equals(this.panelJuegos4D)) {
					panel.remove(this.panelJuegos4D);
					panel.add(this.panelJuegosPB);
					this.panelActual=this.panelJuegosPB;
				}
				/**
				 * si actual es panelPeliculas1E1
				 * volver a panelPeliculasPB
				 */
				else if(this.panelActual.equals(this.panelPeliculas1E1)) {
					panel.remove(this.panelPeliculas1E1);
					panel.add(this.panelPeliculasPB);
					this.panelActual=this.panelPeliculasPB;
				}
				/**
				 * si actual es panelJuegos4B
				 * volver a panelJuegosPB
				 */
				else if(this.panelActual.equals(this.panelJuegos4B)) {
					panel.remove(this.panelJuegos4B);
					panel.add(this.panelJuegosPB);
					this.panelActual=this.panelJuegosPB;
				}
				/**
				 * si actual es panelClientes2A
				 * volver a panelClientes
				 */
				else if(this.panelActual.equals(this.panelClientes2A)) {
					panel.remove(this.panelClientes2A);
					this.setVentanaClientesPB();
					panel.add(this.panelClientesPB);
					this.panelActual=this.panelClientesPB;
				}
				/**
				 * si actual es panelClientes2D
				 * volver a panelClientes
				 */
				else if(this.panelActual.equals(this.panelClientes2D)) {
					panel.remove(this.panelClientes2D);
					this.panelClientes2D.campoRutCliente.setText("");
					panel.add(this.panelClientesPB);
					this.panelActual=this.panelClientesPB;
				}
				/**
				 * si actual es panelClientes2C1
				 * volver a panelClientesPB
				 */
				else if(this.panelActual.equals(this.panelClientes2C1)) {
					panel.remove(this.panelClientes2C1);
					this.setVentanaClientesPB();
					panel.add(this.panelClientesPB);
					this.panelActual=this.panelClientesPB;
				}
				/**
				 * si actual es reporteArriendo o reporteVenta
				 * volver a panelVentaArriendo
				 */
				else if(this.panelActual.equals(reporteArriendo) || this.panelActual.equals(reporteVenta)) {
					panel.removeAll();
					panel.add(this.panelLogo);
					panel.add(this.panelVentaArriendo);
					panelActual=this.panelVentaArriendo;
				}
				/**
				 * si actual es panelNegocios3Aarriendo
				 * volver a panelVentaArriendo
				 */
				else if(this.panelActual.equals(this.panelNegocios3Aarriendo)) {
					panel.remove(this.panelNegocios3Aarriendo);
					panel.remove(this.panelClientes2A);
					this.panelNegocios3Aarriendo.campoRutCliente.setText("");
					panel.add(this.panelVentaArriendo);
					this.panelActual=this.panelVentaArriendo;
				}
				/**
				 * si actual es panelNegocios3Aventa
				 * volver a panelVentaArriendo
				 */
				else if(this.panelActual.equals(this.panelNegocios3Aventa)) {
					panel.remove(this.panelNegocios3Aventa);
					panel.remove(this.panelClientes2A);
					this.panelNegocios3Aventa.campoRutCliente.setText("");
					panel.add(this.panelVentaArriendo);
					this.panelActual=this.panelVentaArriendo;
				}
				/**
				 * si actual es panelNegocios3B
				 * volver a panelVentaArriendo
				 */
				else if(this.panelActual.equals(this.panelNegocios3B)) {
					panel.remove(this.panelNegocios3B);
					panel.add(this.panelVentaArriendo);
					this.panelActual=this.panelVentaArriendo;
				}
				/**
				 * si actual es panelNegocios3C
				 * volver a panelVentaArriendo
				 */
				else if(this.panelActual.equals(this.panelNegocios3C)) {
					panel.remove(this.panelNegocios3C);
					panel.add(this.panelVentaArriendo);
					this.panelActual=this.panelVentaArriendo;
				}
				/**
				 * si actual es panelNegocios3D
				 * volver a panelVentaArriendo
				 */
				else if(this.panelActual.equals(this.panelNegocios3D)) {
					panel.remove(this.panelNegocios3D);
					panel.add(this.panelVentaArriendo);
					this.panelActual=this.panelVentaArriendo;
				}
				/**
				 * si actual es panelClientes2D1
				 * volver a panelClientesPB
				 */
				else if(this.panelActual.equals(this.panelClientes2D1)) {
					panel.remove(this.panelClientes2D1);
					panel.add(this.panelClientesPB);
					this.panelActual=this.panelClientesPB;
				}
				/**
				 * si actual es panelClientes2D1A
				 * volver a panelClientes2D1
				 */
				else if((this.panelClientes2D1A!=null) && this.panelActual.equals(this.panelClientes2D1A)) {
					panel.remove(this.panelClientes2D1A);
					panel.add(this.panelClientes2D1);
					this.panelActual=this.panelClientes2D1;
				}
				/**
				 * si actual es panelClientesArriendoPB
				 * volver a panelClientes2D1A
				 */
				else if(this.panelActual.equals(this.panelClientesArriendoPB)) {
					this.remove(this.panelClientesArriendoPB);
					panel.add(this.panelClientes2D1A);
					this.panelActual=this.panelClientes2D1A;
				}
				/**
				 * si actual es panelClientesVentaPB
				 * volver a panelClientes2D1A
				 */
				else if(this.panelActual.equals(this.panelClientesVentaPB)) {
					this.remove(this.panelClientesVentaPB);
					panel.add(this.panelClientes2D1A);
					this.panelActual=this.panelClientes2D1A;
				}
				/**
				 * si actual es panelNegocios3A1arriendo
				 * volver a panelNegocios3Aarriendo
				 */
				else if(this.panelActual.equals(this.panelNegocios3A1arriendo)) {
					this.remove(this.panelNegocios3A1arriendo);
					this.add(this.panelNegocios3Aarriendo);
					this.panelActual=this.panelNegocios3Aarriendo;
				}
				/**
				 * si actual es panelNegocios3A1Aarriendo
				 * volver a panelNegocios3A1arriendo
				 */
				else if(this.panelActual.equals(this.panelNegocios3A1Aarriendo)) {
					this.remove(this.panelNegocios3A1Aarriendo);
					this.add(this.panelNegocios3A1arriendo);
					this.panelActual=this.panelNegocios3A1arriendo;
				}
				/**
				 * si actual es panelNegocios3A1A1arriendo
				 * volver a panelNegocios3A1Aarriendo
				 */
				else if(this.panelActual.equals(this.panelNegocios3A1A1arriendo)) {
					this.remove(this.panelNegocios3A1A1arriendo);
					this.add(this.panelNegocios3A1Aarriendo);
					this.panelActual=this.panelNegocios3A1Aarriendo;
				}
				/**
				 * si actual es panelNegocios3A1venta
				 * volver a panelNegocios3Aventa
				 */
				else if(this.panelActual.equals(this.panelNegocios3A1venta)) {
					this.remove(this.panelNegocios3A1venta);
					this.panelNegocios3Aventa.campoRutCliente.setText("");
					this.add(this.panelNegocios3Aventa);
					this.panelActual=this.panelNegocios3Aventa;
				}
				/**
				 * si actual es panelNegocios3A1Aventa
				 * volver a panelNegocios3A1venta
				 */
				else if(this.panelActual.equals(this.panelNegocios3A1Aventa)) {
					this.remove(this.panelNegocios3A1Aventa);
					this.panelNegocios3A1venta.campoIdPelicula.setText("");
					this.add(this.panelNegocios3A1venta);
					this.panelActual=this.panelNegocios3A1venta;
				}
			}



			 if(e.getSource()==panelLogo.undo) {
				Memento m=anterior.getMemento();
				if(m!=null)
					original.restaurar(m);
				panelLogo.undo.setVisible(false);
				for(int i=0;i<restaurables.size();i++) {
					if(panelActual==this.panelVentaArriendo) {
						if((restaurables.get(i) instanceof Negocio)) {
							if(estado==1)
								principal.eliminarNegocio(((Negocio) restaurables.get(i)).getCodigo());
							else {
								principal.reemplazarNegocio((Negocio) restaurables.get(i));
							}
							(restaurables.get(i)).revertir(estado);
						}else {
							(restaurables.get(i)).modificarDatos();
						}
					}
					else {
						(restaurables.get(i)).revertir(estado);
					}
						
				}
			 	panel.removeAll();
			 	panel.add(panelLogo);
			 	if(panelActual==panelClientesPB) {
			 		this.setVentanaClientesPB();
			 		panel.add(panelClientesPB);
			 		panelActual=panelClientesPB;
			 	}
			 	else if(panelActual==panelPeliculasPB) {
			 		this.setVentanaPeliculasPB();
			 		panel.add(panelPeliculasPB);
			 		panelActual=panelPeliculasPB;
			 	}
			 	else if(panelActual==panelJuegosPB) {
			 		this.setVentanaJuegosPB();
			 		panel.add(panelJuegosPB);
			 		panelActual=panelJuegosPB;
			 	}
			 	else if(panelActual==panelVentaArriendo) {
			 		panel.add(panelVentaArriendo);
			 	}
				 JOptionPane.showMessageDialog(null, "La acción ha sido cancelada, los datos han sido restaurados","Exito",JOptionPane.INFORMATION_MESSAGE);
				 restaurables=new ArrayList<ModificadorDeTabla>();
			 }


			/*ACCION BOTONES SALIR*/
			/**
			 * 
			 * Este botón es el único que puede cerrar el programa
			 * dentro de la ventana. 
			 * 
			 * El panelLogo siempre está visible y se puede usar en
			 * cualquier lugar del programa.
			 * 
			 * 
			 */
			else if(e.getSource()==this.panelLogo.salir) {
				this.dispose();
				System.exit(0);
			}

			SwingUtilities.updateComponentTreeUI(this);
		}
	}

	
	
	
	
	
	
	
	
	
	
	public void agregarMemento() {
		anterior.agregarMemento(original.crearMemento());
		restaurables=new ArrayList<ModificadorDeTabla>();
		panelLogo.undo.setVisible(true);
	}









	/**
	 * setVentanaHistorial:
	 * 
	 * Método que dimensiona el panel panelClientes2D1A
	 * con la cantidad de arriendos y ventas recibidas por
	 * parámetro y agrega las acciones de los botones de
	 * la ventana.
	 * 
	 * @param cantA
	 * Cantidad de arriendos (int).
	 * @param cantV
	 * Cantidad de ventas (int).
	 */
	public void setVentanaHistorial(final int cantA,final int cantV) {
		this.panelClientes2D1A=new PanelClientes2D1A(cantA,cantV);
		this.panelClientes2D1A.arriendos.addActionListener(this);
		this.panelClientes2D1A.ventas.addActionListener(this);
	}










	/**
	 * setVentanas:
	 * 
	 * Método que dimensiona todas los paneles
	 * estáticas que ocupará el programa.
	 * 
	 */
	public void setVentanas() {
		this.panelLogo= new PanelLogo();
		this.panelPrincipal=new PanelPrincipal();
		this.panelProductos=new PanelProductos();
		this.panelVentaArriendo=new PanelVentaArriendo();
		this.panelPeliculas1C=new PanelPeliculas1C();
		this.panelPeliculas1E=new PanelProductos1E();
		this.panelClientes2A=new PanelClientes2A();
		this.panelClientes2D=new PanelClientes2C();
		this.panelNegocios3Aarriendo=new PanelNegocios3A();
		this.panelNegocios3Aventa=new PanelNegocios3A();
		this.panelNegocios3A1Aarriendo=new PanelNegocios3A1A();
		this.panelNegocios3A1Aventa=new PanelNegocios3A1A1();
		this.panelNegocios3A1A1arriendo=new PanelNegocios3A1A1();
		this.panelNegocios3C=new PanelNegocios3C();
		this.panelJuegos4A=new PanelJuegos4A();
		this.panelJuegos4D=new PanelProductos1E();
		this.panelJuegos4D.titulo.setText("Buscar juegos");
		this.panelJuegos4D.buscarProducto.setText("Buscar juego");
		this.panelJuegos4D.codigoProducto.setText("Código del juego: ");
		this.setVentanaClientesPB();
		this.setVentanaPeliculasPB();
		this.setVentanaJuegosPB();
	}










	/**
	 * setVentanaClientesPB:
	 * 
	 * Método que instancia un panel 
	 * panelClientesPB con los datos
	 * de la lista de clientes del videoclub.
	 */
	public void setVentanaClientesPB() {
		CubiHoyts principal=(CubiHoyts) original.getEstado();
		final int size=principal.cantidadClientes();
		this.panelClientesPB=new PanelClientesPB(principal.clonarMapaClientes());
		this.panelClientesPB.agregar.addActionListener(this);
		this.panelClientesPB.buscar.addActionListener(this);
		this.panelClientesPB.reporte.addActionListener(this);
		for(int i=4;i<7;i++) {
			for(int j=0;j<size;j++) {
				((JButton) this.panelClientesPB.modelo.getValueAt(j, i)).addActionListener(this);
			}
		}
	}










	/**
	 * setVentanaPeliculasPB:
	 * 
	 * Método que instancia un panel
	 * panelPeliculasPB con los datos
	 * del conjunto de películas del videoclub.
	 */
	public void setVentanaPeliculasPB() {
		CubiHoyts principal=(CubiHoyts) original.getEstado();
		final int size=principal.clonarListaPelicula().size();
		this.panelPeliculasPB=new PanelProductosPB(principal.clonarListaPelicula());
		this.panelPeliculasPB.agregar.addActionListener(this);
		this.panelPeliculasPB.buscar.addActionListener(this);
		this.panelPeliculasPB.categorias.addActionListener(this);
		this.panelPeliculasPB.reporte.addActionListener(this);
		for(int i=4;i<7;i++) {
			for(int j=0;j<size;j++) {
				((JButton) this.panelPeliculasPB.modelo.getValueAt(j, i)).addActionListener(this);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * setVentanaJuegosPB:
	 * 
	 * Método que instancia un panel
	 * panelPeliculasPB con los datos
	 * del conjunto de películas del videoclub.
	 */
	public void setVentanaJuegosPB() {
		CubiHoyts principal=(CubiHoyts) original.getEstado();
		final int size=principal.clonarListaJuego().size();
		this.panelJuegosPB=new PanelProductosPB(principal.clonarListaJuego());
		this.panelJuegosPB.agregar.addActionListener(this);
		this.panelJuegosPB.buscar.addActionListener(this);
		this.panelJuegosPB.categorias.addActionListener(this);
		this.panelJuegosPB.reporte.addActionListener(this);
		for(int i=4;i<7;i++) {
			for(int j=0;j<size;j++) {
				((JButton) this.panelJuegosPB.modelo.getValueAt(j, i)).addActionListener(this);
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	public void stage7() {
		Container panel=this.getContentPane();
		panel.removeAll();
		panel.add(panelLogo);
		panel.add(panelPrincipal);
		panelActual=panelPrincipal;
		SwingUtilities.updateComponentTreeUI(this);
	}
}

