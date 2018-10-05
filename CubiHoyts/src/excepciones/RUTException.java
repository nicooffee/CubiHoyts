package excepciones;

import javax.swing.JOptionPane;

import m�todosGlobales.ModuloOnce;
/**
 * RUTException:
 * 
 * Excepci�n para controlar el ingreso de RUT inv�lidos.
 * 
 * Es lanzada cuando alg�n rut no cumple con el m�dulo 11.
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
		super("El rut es inv�lido");
		JOptionPane.showMessageDialog(null, "�Ha ingresado un rut inv�lido!","Error",JOptionPane.ERROR_MESSAGE);
	}

}
