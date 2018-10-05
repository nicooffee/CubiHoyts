package factory;

import clases.ConjuntoJuego;
import clases.ConjuntoPelicula;
import clases.ConjuntoProducto;
import clases.ListaArriendo;
import clases.ListaNegocio;
import clases.ListaVenta;

public class Creator {
	public static int crearConjuntoJuego=1;
	public static int crearConjuntoPelicula=2;
	public static int crearListaArriendo=3;
	public static int crearListaVenta=4;
	
	public static ConjuntoProducto crearConjunto(int e) {
		if(e==crearConjuntoJuego)
			return new ConjuntoJuego();
		else if(e==crearConjuntoPelicula)
			return new ConjuntoPelicula();
		else
			return null;
	}
	public static ListaNegocio crearLista(int e) {
		if(e==crearListaArriendo)
			return new ListaArriendo();
		else if(e==crearListaVenta)
			return new ListaVenta();
		else
			return null;
	}
}
