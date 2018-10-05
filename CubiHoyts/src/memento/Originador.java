package memento;

import clases.CubiHoyts;

public class Originador {
	private Object estado;
	
	
	
	public Originador(Object estado) {
		this.estado=estado;
	}
	
	
	
	
	public void restaurar(Memento m) {
		estado=m.getEstado();
	}
	
	
	
	public Memento crearMemento() {
		return new Memento(((CubiHoyts) estado).clonar());
	}
	
	
	
	public Object getEstado() {
		return estado;
	}
}
