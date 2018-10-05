package memento;

public class Memento {
	private Object estado;
	
	public Memento(Object estado) {
		this.estado=estado;
	}
	
	
	public Object getEstado() {
		return estado;
	}
}
