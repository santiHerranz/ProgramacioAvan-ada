package domini;

public class Moviment {
	
	private int[] coordInici;
	private int[] coordFinal;
	private int[] coordMenjada;

	public int[] getCoordInici() {
		return coordInici;
	}

	public int[] getCoordFinal() {
		return coordFinal;
	}

	public int[] getCoordMenjada() {
		return coordMenjada;
	}
	
	Moviment(int[] ini, int[] fin, int[] menja){
		this.coordInici = ini;
		this.coordFinal = fin;
		this.coordMenjada = menja;
	}
}
