package utilidades;

import javax.swing.JFileChooser;

/**
 * Interfaz Exportable:
 * 
 * Interfaz que prototipa un �nico m�todo {@link #generarReporte(String)}
 * el cu�l asegura que una clase puede guardar sus datos dentro de un
 * archivo pdf, sin importar qu� tipo de clase sea.
 * 
 * @author Nicol�s Honorato.
 *
 */
public interface Exportable {

	/**
	 * generarReporte:
	 * 
	 * Prototipo de m�todo que se encarga de generar un archivo
	 * pdf que simule un reporte con los datos de la clase que
	 * implemente el m�todo.
	 * 
	 * @param directorio
	 * Directorio en donde se deber� guardar el archivo pdf.
	 * @return
	 * True si el archivo fue creado exit�samente.
	 */
	 public boolean generarReporte(String directorio);
	 
	 
	 
	 @SuppressWarnings("static-access")
	public static String directorio() {
			String ruta="";
			JFileChooser chooser=new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(chooser.showSaveDialog(null)==chooser.APPROVE_OPTION) {
				ruta=chooser.getSelectedFile().getAbsolutePath();
			}
			return ruta;
	 }
}
