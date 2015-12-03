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
	}
	
	
	public int getMovimentsPosibles(){
		return getFitxes()-1;
	}
	
	public int getFitxes(){
		int contador = 0;
        for (int x = 0; x < caselles.length; x++) 
            for (int y = 0; y < caselles[x].length; y++) {
                    if( caselles[x][y] == Joc.CASELLA_OCUPADA || caselles[x][y] == Joc.CASELLA_SELECCIONADA )
                    	contador++;
            }
		return contador;
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
	
