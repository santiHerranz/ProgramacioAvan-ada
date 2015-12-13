

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

	public int[][] caselles() {
		return caselles;
	}
	
	public void setContingut(int[][] contingut){
		caselles = contingut;
	}
	
	private boolean foraLimits(int fila, int columna) throws Exception{
		return ((fila < 0 || fila > this.caselles.length) || (columna < 0 || columna > this.caselles.length)); 
			
	}

	
	public void imprimir(){
		
            for (int x = 0; x < this.caselles.length; x++) {
                for (int y = 0; y < this.caselles[x].length; y++) {
                	if(this.caselles[x][y]== Continental.CASELLA_NO_VALIDA )
                        System.out.print(" ");
                	else
                        System.out.print(this.caselles[x][y]);
                }
                System.out.println();
            }
    }		

}
	
