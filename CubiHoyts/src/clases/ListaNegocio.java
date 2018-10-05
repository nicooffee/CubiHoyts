package clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import utilidades.Exportable;
/**
 * Clase ListaNegocio:
 *
 * Clase que tiene como único atributo un ArrayList de objetos {@link Negocio}.
 *
 * Posee métodos para manejar el procesamiento de datos del ArrayList.
 * 
 * Implementa la Interfaz {@link Exportable} la cual se encarga de exportar
 * los datos de negocios a un archivo pdf.
 * 
 * Hereda a dos clases las cuales corresponden a {@link ListaArriendo} y
 * {@link ListaVenta}.
 * 
 * @author Benjamín Herrera
 *
 */
public abstract class ListaNegocio implements Exportable{
	private final ArrayList<Negocio> negocios;
	
	
	
	
	
	
	
	
	
	
	
	public ListaNegocio() {
		negocios=new ArrayList<Negocio>();
	}
	public ListaNegocio(ArrayList<Negocio> negocios) {
		this.negocios=negocios;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * agregarNegocio:
	 *
	 * Método que agrega un objeto {@link Negocio} al ArrayList, el cual es recibido por
	 * parámetro.
	 *
	 * @param neg
	 *            Referencia al negocio.
	 */
	public void agregarNegocio(final Negocio neg) {
		this.negocios.add(neg);
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * buscarNegocio:
	 *
	 * Método que busca el objeto {@link Negocio} que se encuentra en la posición "in"
	 * recibida por parámetro.
	 *
	 * @param in
	 *            Índice para buscar el negocio.
	 * @return El objeto negocio que está en la posición "in".
	 *
	 */
	public Negocio buscarNegocio(final int in) {
		if ((this.negocios != null) && (in < this.negocios.size()))
			return this.negocios.get(in);
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * buscarNegocio:
	 *
	 * Método que busca un negocio que posea el código enviado por parámetro.
	 *
	 * @param cod
	 *            Código de la venta (String).
	 * @return El objeto {@link Negocio} si es encontrado.
	 */
	public Negocio buscarNegocio(final String cod) {
		if (this.negocios != null)
			for (int i = 0; i < this.negocios.size(); i++)
				if (this.negocios.get(i).getCodigo().equals(cod))
					return this.negocios.get(i);
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadNegocios:
	 *
	 *
	 * @return El largo del ArrayList de objetos {@link Negocio}.
	 */
	public int cantidadNegocios() {
		return this.negocios.size();
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadArriendosPorMes:
	 *
	 * Método que cuenta los negocios realizados en un mes determinado.
	 *
	 * @param month
	 *            Mes a comparar (int).
	 * @return La cantidad de negocios correspondientes al mes entregado por
	 *         parámetro.
	 */
	public abstract int cantidadNegociosPorMes(final int month);
	
	
	
	
	
	
	
	
	
	
	/**
	 * cantidadNegociosPorProducto:
	 *
	 * Método que cuenta la cantidad de negocios realizados de un producto
	 * determinado.
	 * 
	 * @param cod
	 *            Código del producto
	 * @return La cantidad de negocios de una película determinada.
	 */
	public int cantidadNegociosPorProducto(final int cod) {
		int cont = 0;
		for (int i = 0; i < this.negocios.size(); i++) {
			final Negocio neg = this.negocios.get(i);
			if (neg.getCodigoProducto() == cod)
				cont++;
		}
		return cont;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * clonarListaNegocios:
	 *
	 * Método que inserta todos los objetos del ArrayList negocios en un nuevo
	 * ArrayList.
	 * 
	 * @return El nuevo ArrayList con los objetos de {@link Negocio}.
	 */
	public ArrayList<Negocio> clonarListaNegocios() {
		final ArrayList<Negocio> nuevo = new ArrayList<Negocio>();
		for (final Negocio negs : this.negocios)
			nuevo.add(negs);
		Collections.sort(nuevo, new Comparator<Object>() {
			@Override
			public int compare(final Object p1, final Object p2) {
				return ((Negocio) p1).getRutCliente().compareTo(((Negocio) p2).getRutCliente());
			}
		});
		return nuevo;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * clonar:
	 * 
	 * Método que crea un nuevo objeto a partir de los datos del creador.
	 * 
	 * @return
	 * El nuevo objeto creado.
	 */
	public ArrayList<Negocio> clonar(){
		final ArrayList<Negocio> nuevo = new ArrayList<Negocio>();
		nuevo.addAll(negocios);
		return negocios;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * eliminarNegocio:
	 * 
	 * Método que elimina un negocio del ArrayList.
	 * 
	 * @param cod
	 * 				Código del negocio.
	 */
	public void eliminarNegocio(String cod) {
		for(int i=0;i<negocios.size();i++) {
			if(negocios.get(i).getCodigo().equals(cod))
				negocios.remove(i);
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * gananciaTotal:
	 *
	 * Método que suma las ganancias de todas los negocios del ArrayList.
	 *
	 * @return La suma total de las ganancias.
	 */
	public int gananciaTotal() {
		int suma = 0;
		for (int i = 0; i < this.negocios.size(); i++)
			suma += this.negocios.get(i).getGanancia();
		return suma;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * gananciaTotalPorDia:
	 * 
	 * Método que suma las ganancias de todos los negocios del ArrayList
	 * por fecha.
	 * 
	 * @param fecha
	 *            Fecha a comparar.
	 * @return La suma total de las ganancias.
	 */
	public abstract int gananciaTotalPorDia(final Calendar fecha);
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * listaNegociosPorRut:
	 *
	 * Método que crea un objeto {@link ListaNegocio} con todas los negocios de un cliente
	 * determinado.
	 * 
	 * @param rut
	 *            Rut del cliente (String).
	 * @return El objeto {@link ListaNegocio} con los Negocios del cliente.
	 */
	public abstract ListaNegocio listaNegociosPorRut(final String rut);
	
	
	
	
	
	/**
	 * reemplazar:
	 * 
	 * Método que recibe un negocio y reemplaza al negocio que posea el mismo código.
	 * @param neg
	 * 				Negocio de reemplazo.
	 */
	public void reemplazar(Negocio neg) {
		for(int i=0;i<negocios.size();i++) {
			if(negocios.get(i).getCodigo().equals(neg.getCodigo())) {
				negocios.remove(i);
				negocios.add(neg);
				break;
			}
		}
	}
}
