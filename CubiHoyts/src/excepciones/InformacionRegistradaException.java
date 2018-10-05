package excepciones;

import javax.swing.JOptionPane;

/**
 * InformacionRegistradaException:
 * 
 * Excepción para controlar intentos de redundancia de datos.
 * 
 * La excepción es lanzada siempre que se quiera agregar información
 * ya registrada en la estructura.
 * 
 * @author Nicolás Honorato.
 *
 */
public class InformacionRegistradaException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InformacionRegistradaException() {
		super("La información ya ha sido registrada anteriormente");
		JOptionPane.showMessageDialog(null, "¡La información ya ha sido registrada anteriormente!","Error",JOptionPane.ERROR_MESSAGE);
	}
}
