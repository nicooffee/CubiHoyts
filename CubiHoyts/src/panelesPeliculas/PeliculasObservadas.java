package panelesPeliculas;

import java.util.ArrayList;
import java.util.Observable;

import clases.ProductoVideoClub;

public class PeliculasObservadas extends Observable{
	private ArrayList<ProductoVideoClub> peliculas;
	private ArrayList<ProductoVideoClub> aux;
	
	public PeliculasObservadas(ArrayList<ProductoVideoClub> productos) {
		this.peliculas=productos;
		aux=new ArrayList<ProductoVideoClub>();
		aux.addAll(productos);
	}
	
	public void filtrarArray(String genero) {
		if(!genero.equals("Todas")) {
			aux=new ArrayList<ProductoVideoClub>();
			for (final ProductoVideoClub prod : this.peliculas) {
				if(prod.buscarTipo(genero))
					aux.add(prod);
			}
		}
		else {
			aux=peliculas;
		}
		setChanged();
	    notifyObservers();
	}
	public ArrayList<ProductoVideoClub> getProductos(){
		return aux;
	}
}
