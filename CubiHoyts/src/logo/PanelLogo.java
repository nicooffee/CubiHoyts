package logo;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelLogo extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Logo logo;
	public JButton salir;
	public JButton volver;
	public JButton undo;
	public PanelLogo() {
		setSalirVolver();
		setLayout(null);
		setSize(800,175);
	}
	
	public void setSalirVolver() {
		logo=new Logo();
		add(logo);
		logo.setBounds(250, 25,	300, 150);
		salir=new JButton("Salir");
		volver=new JButton("<= Volver");
		undo=new JButton("<-Undo");
		add(salir);
		add(volver);
		add(undo);
		salir.setBounds(650, 25, 100, 50);
		volver.setBounds(650, 125, 100, 50);
		undo.setBounds(550, 125, 90, 50);
	}
}
