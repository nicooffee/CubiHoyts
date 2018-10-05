package utilidades;

import javax.swing.JFileChooser;

/**
 * Interfaz Exportable:
 * 
 * Interfaz que prototipa un único método {@link #generarReporte(String)}
 * el cuál asegura que una clase puede guardar sus datos dentro de un
 * archivo pdf, sin importar qué tipo de clase sea.
 * 
 * @author Nicolás Honorato.
 *
 */
public interface Exportable {

	/**
	 * generarReporte:
	 * 
	 * Prototipo de método que se encarga de generar un archivo
	 * pdf que simule un reporte con los datos de la clase que
	 * implemente el método.
	 * 
	 * @param directorio
	 * Directorio en donde se deberá guardar el archivo pdf.
	 * @return
	 * True si el archivo fue creado exitósamente.
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
