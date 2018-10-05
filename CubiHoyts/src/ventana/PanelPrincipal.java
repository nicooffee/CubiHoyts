package ventana;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image logo;
	JButton peliculas;
	JButton clientes;
	JButton ventaArriendo;
	public PanelPrincipal() {
		setLayout(null);
		setSize(800,600);		
		peliculas=new JButton("Productos");
		clientes=new JButton("Clientes");
		ventaArriendo=new JButton("Venta/Arriendo");
		add(peliculas);
		peliculas.setBounds(100, 200, 200, 100);
		add(clientes);
		clientes.setBounds(500, 200, 200, 100);
		add(ventaArriendo);
		ventaArriendo.setBounds(300, 350, 200, 100);
	}
	
	
}
