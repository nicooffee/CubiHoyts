package métodosGlobales;

public class EsNumerico {
	
	
	
	
	/**
	 * isNumeric: 
	 * 
	 * Método que comprueba si una 
	 * string recibida por parámetro
	 * se compone solo de dígitos.
	 * 
	 * @param str
	 * String a revisar.
	 * @return
	 * True si la string recibida solo 
	 * se compone de dígitos.
	 */
	public static boolean comprobar(final String str) {
		return (str.matches("[+-]?\\d*(\\.\\d+)?") && (str.equals("")==false));
	}
}
