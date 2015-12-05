package domini;

public class Taulell {

	private int[][] caselles; // El taulell és un array de dos dimensions de enters
	
	/**
	 * Constructor de taulell
	 */
	public Taulell(int mida) {
		this.caselles = new int[mida][mida];
	}
	
	public void setContingut(int fila, int columna, int value) throws Exception {
		if (foraLimits(fila, columna))
			throw new Exception("Error fila i/o columna fora del taullel");

		this.caselles[fila][columna] = value;
	}		

	public int getContingut(int fila, int columna) throws Exception {
		if (foraLimits(fila, columna))
			throw new Exception("Error fila i/o columna fora del taullel");

		return this.caselles[fila][columna];
	}	

	public int[][] caselles() {
		return caselles;
	}
	
	public void setContingut(int[][] contingut){
		caselles = contingut;
	}
	
	private boolean foraLimits(int fila, int columna) throws Exception{
		return ((fila < 0 || fila > this.caselles.length) || (columna < 0 || columna > this.caselles.length)); 
			
	}


}
	
