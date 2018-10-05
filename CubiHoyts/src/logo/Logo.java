package logo;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Logo extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Logo() {
		setLayout(null);
		setSize(300,150);
	}
	@Override
	public void paintComponent(Graphics g) {
		Dimension tamano=getSize();
		ImageIcon imagenFondo=new ImageIcon(getClass().getResource("/logo/logo.png"));
		setOpaque(false);
		g.drawImage(imagenFondo.getImage(),0,0,tamano.width, tamano.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
