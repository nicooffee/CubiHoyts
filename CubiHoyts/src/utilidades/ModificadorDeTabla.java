package utilidades;



/**
 * Clase ModificadorDeTabla:
 * 
 * Clase que posee método capaces de modificar una tabla
 * de una base de datos. 
 * 
 * Cada clase debe implementar los métodos indicando a qué
 * tabla accederá y cómo modificará los datos.
 * 
 * @author Nicolás Honorato.
 *
 */
public interface ModificadorDeTabla {
	/**
	 * agregarDatos:
	 * 
	 * Método que se encarga de agregar una
	 * tupla a la base de datos.
	 * 
	 * Cada clase genera una tupla con sus atributos
	 * y la agrega a su tabla correspondiente.
	 */
	public void agregarDatos();
	
	
	
	
	
	
	/**
	 * eliminarDatos:
	 * 
	 * Método que se encarga de eliminar una tupla de la base
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
	 * Método que se encarga de modificar los datos de una tupla
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
	 * Método que revierte una acción ya hecha en la base de datos.
	 * 
	 * El estado indica qué acción se realizó previamente.
	 * 
	 * Si estado es 0:
	 * 	La acción previa es: eliminar.
	 * 
	 * Si estado es 1:
	 * 	La acción previa es: agregar.
	 * 
	 * Si estado es 2:
	 * 	La acción previa es: modificar.
	 * @param estado
	 * Estado que indica qué acción se realizó previamente.
	 */
	public  void revertir(int estado);

}
