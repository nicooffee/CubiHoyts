package panelesJuegos;

import java.util.ArrayList;
import java.util.Observable;

import clases.Juego;
import clases.ProductoVideoClub;

public class JuegosObservados extends Observable{
	private ArrayList<ProductoVideoClub> juegos;
	private ArrayList<ProductoVideoClub> aux;
	
	public JuegosObservados(ArrayList<ProductoVideoClub> productos) {
		this.juegos=productos;
		aux=new ArrayList<ProductoVideoClub>();
		aux.addAll(productos);
	}
	public void filtrarArray(String genero,String consola) {
		if(!genero.equals("Todas") && !consola.equals("Todas")) {
			aux=new ArrayList<ProductoVideoClub>();
			for (final ProductoVideoClub prod : this.juegos) {
				if(prod.buscarTipo(genero) && ((Juego) prod).buscarConsola(consola))
					aux.add(prod);
			}
		}
		else if(!genero.equals("Todas")) {
			aux=new ArrayList<ProductoVideoClub>();
			for (final ProductoVideoClub prod : this.juegos) {
				if(prod.buscarTipo(genero))
					aux.add(prod);
			}
		}
		else if(!consola.equals("Todas")) {
			aux=new ArrayList<ProductoVideoClub>();
			for (final ProductoVideoClub prod : this.juegos) {
				if(((Juego) prod).buscarConsola(consola))
					aux.add(prod);
			}
		}
		else {
			aux=juegos;
		}
		setChanged();
	    notifyObservers();
	}
	public ArrayList<ProductoVideoClub> getProductos(){
		return aux;
	}
}
