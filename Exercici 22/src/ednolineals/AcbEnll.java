package ednolineals;

import edlineals.Cua;

public class AcbEnll<E> extends AbEnll<E> {

	public Cua<E> cua;

	/* TODO Solució Exercici 4 : equipMesJugadors (AcbEnll)*/
	public String equipMesJugadors() {
		if (arrel == null) return null;
		return (arrel.equipMesJugadors()).getNom();
	}

}
