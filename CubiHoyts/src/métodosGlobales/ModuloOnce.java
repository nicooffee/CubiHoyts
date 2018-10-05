package métodosGlobales;

public class ModuloOnce {
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * comprobar:
	 * 
	 * Método que comprueba si el rut recibido
	 * por parámetro cumple con el algoritmo de
	 * modulo once.
	 * 
	 * Más información en:
	 * https://es.wikipedia.org/wiki/Rol_%C3%9Anico_Tributario#Algoritmo
	 * 
	 * @param rut
	 * RUT a comprobar (String).
	 * @return
	 * True si el rut cumple con el algoritmo
	 * de modulo once.
	 */
	public static boolean comprobar(String rut)  {
		boolean validacion = false;
		try {
			rut =rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
			final char dv = rut.charAt(rut.length() - 1);
			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + ((rutAux % 10) * (9 - (m++ % 6)))) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				validacion = true;
			}
		} 
		catch (final java.lang.NumberFormatException e) {
		} 
		catch (final Exception e) {
		}
		return validacion;
	}
}
