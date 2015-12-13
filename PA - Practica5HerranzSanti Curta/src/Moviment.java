public class Moviment {
	
	private Coordenada coordInici;
	private Coordenada coordFinal;
	private Coordenada coordMenjada;
	
	private Taulell taulell;

	public Coordenada getInici() {
		return coordInici;
	}

	public Coordenada getFinal() {
		return coordFinal;
	}

	public Coordenada getMenjada() {
		return coordMenjada;
	}
	
	public Moviment(Coordenada ini, Coordenada fin, Coordenada menja, Taulell t) {
		this.coordInici = ini;
		this.coordFinal = fin;
		this.coordMenjada = menja;
		this.taulell = t;
	}

	public Taulell getTaulell() {
		return taulell;
	}
	
	
}
