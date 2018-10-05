package m�todosGlobales;

public class EsNumerico {
	
	
	
	
	/**
	 * isNumeric: 
	 * 
	 * M�todo que comprueba si una 
	 * string recibida por par�metro
	 * se compone solo de d�gitos.
	 * 
	 * @param str
	 * String a revisar.
	 * @return
	 * True si la string recibida solo 
	 * se compone de d�gitos.
	 */
	public static boolean comprobar(final String str) {
		return (str.matches("[+-]?\\d*(\\.\\d+)?") && (str.equals("")==false));
	}
}
