package excepciones;

import javax.swing.JOptionPane;

/**
 * InformacionRegistradaException:
 * 
 * Excepci�n para controlar intentos de redundancia de datos.
 * 
 * La excepci�n es lanzada siempre que se quiera agregar informaci�n
 * ya registrada en la estructura.
 * 
 * @author Nicol�s Honorato.
 *
 */
public class InformacionRegistradaException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InformacionRegistradaException() {
		super("La informaci�n ya ha sido registrada anteriormente");
		JOptionPane.showMessageDialog(null, "�La informaci�n ya ha sido registrada anteriormente!","Error",JOptionPane.ERROR_MESSAGE);
	}
}
