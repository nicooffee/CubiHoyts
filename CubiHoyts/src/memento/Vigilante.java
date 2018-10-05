package memento;

public class Vigilante {
	private Memento anterior;
	
	public Vigilante() {
		anterior=null;
	}
	public void agregarMemento(Memento m) {
		anterior=m;
	}
	public Memento getMemento() {
		Memento aux=anterior;
		anterior =null;
		return aux;
	}
	
}
