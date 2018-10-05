package ventana;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class PanelCarga extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JProgressBar progreso;
	public JLabel frase=new JLabel("");
	public Timer t;
	public ActionListener ac;
	public int x=0;
	
	
	public PanelCarga() {
		setSize(800,600);
		setLayout(null);	
		progreso=new JProgressBar();
		add(progreso);
		progreso.setBounds(100, 450, 600, 75);
		
	}
}
