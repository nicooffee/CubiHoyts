package utilidades;



/**
 * Clase ModificadorDeTabla:
 * 
 * Clase que posee m�todo capaces de modificar una tabla
 * de una base de datos. 
 * 
 * Cada clase debe implementar los m�todos indicando a qu�
 * tabla acceder� y c�mo modificar� los datos.
 * 
 * @author Nicol�s Honorato.
 *
 */
public interface ModificadorDeTabla {
	/**
	 * agregarDatos:
	 * 
	 * M�todo que se encarga de agregar una
	 * tupla a la base de datos.
	 * 
	 * Cada clase genera una tupla con sus atributos
	 * y la agrega a su tabla correspondiente.
	 */
	public void agregarDatos();
	
	
	
	
	
	
	/**
	 * eliminarDatos:
	 * 
	 * M�todo que se encarga de eliminar una tupla de la base
	 * de datos cubihoyts.
	 * 
	 * Cada clase accede a su tabla correspondiente y elimina
	 * los datos que le correspondan.
	 * 
	 */
	public void eliminarDatos();
	
	
	
	
	
	/**
	 * modificarDatos:
	 * 
	 * M�todo que se encarga de modificar los datos de una tupla
	 * de la base de datos.
	 * 
	 * Cada clase accede a su tabla correspondiente,busca y modifica
	 * los datos que le correspondan.
	 * 
	 */
	public void modificarDatos();
	
	
	
	
	/**
	 * revertir:
	 * 
	 * M�todo que revierte una acci�n ya hecha en la base de datos.
	 * 
	 * El estado indica qu� acci�n se realiz� previamente.
	 * 
	 * Si estado es 0:
	 * 	La acci�n previa es: eliminar.
	 * 
	 * Si estado es 1:
	 * 	La acci�n previa es: agregar.
	 * 
	 * Si estado es 2:
	 * 	La acci�n previa es: modificar.
	 * @param estado
	 * Estado que indica qu� acci�n se realiz� previamente.
	 */
	public  void revertir(int estado);

}
