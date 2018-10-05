package excepciones;

import javax.swing.JOptionPane;

import métodosGlobales.ModuloOnce;
/**
 * RUTException:
 * 
 * Excepción para controlar el ingreso de RUT inválidos.
 * 
 * Es lanzada cuando algún rut no cumple con el módulo 11.
 * Ver: {@link ModuloOnce#comprobar(String)}
 * @author Nico
 *
 */
public class RUTException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RUTException() {
		super("El rut es inválido");
		JOptionPane.showMessageDialog(null, "¡Ha ingresado un rut inválido!","Error",JOptionPane.ERROR_MESSAGE);
	}

}
