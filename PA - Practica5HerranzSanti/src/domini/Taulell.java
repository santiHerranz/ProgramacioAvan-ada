package domini;

public class Taulell {

	private int mida = 0;
	private int[][] caselles; // El taulell és un array de dos dimensions de enters
	
	/*
	 * Constructor de taulell
	 */
	public Taulell(int mida) {
		this.mida = mida;
		this.caselles = new int[mida][mida];

		//Crear caselles del taulell
		for (int i = 0; i < this.mida; ++i){
			for (int j = 0; j < this.mida; ++j){
				this.caselles[i][j] = Joc.CASELLA_BUIDA;
			}
		}
	}
	
	public void setContingut(int x, int y, int value) throws Exception {
		if ((x < 0 || x > this.mida) || (y < 0 || y > this.mida)) 
			throw new Exception("Error fila i/o columna fora del taullel");

		this.caselles[x][y] = value;
	}		

	public int getContingut(int x, int y) throws Exception {
		if ((x < 0 || x > this.mida) || (y < 0 || y > this.mida)) 
			throw new Exception("Error fila i/o columna fora del taullel");

		return this.caselles[x][y];
	}	

	public int[][] caselles() {
		return caselles;
	}
	
	public void setContingut(int[][] contingut){
		caselles = contingut;
	}

}
	
