package domini;

public class Taulell {

	private int mida = 0;
	private Casella[][] taulell; // El taulell és un array de dos dimensions de caselles
	
	/*
	 * Constructor de taulell
	 */
	public Taulell(int mida) {
		this.mida = mida;
		this.taulell = new Casella[mida][mida];

		//Crear caselles del taulell
		for (int i = 0; i < this.mida; ++i){
			for (int j = 0; j < this.mida; ++j){
				this.taulell[i][j] = new Casella();
			}
		}
	}
	
	public void setContingut(int x, int y, String value) throws Exception {
		if ((x < 0 || x > this.mida) || (y < 0 || y > this.mida)) 
			throw new Exception("Error fila i/o columna fora del taullel");
		this.taulell[x][y].setContingut(value);
	}		

	public String getContingut(int x, int y) throws Exception {
		if ((x < 0 || x > this.mida) || (y < 0 || y > this.mida)) 
			throw new Exception("Error fila i/o columna fora del taullel");
		return this.taulell[x][y].getContingut();
	}	

	public String[][] estatTaulell() throws Exception{
		String[][] sb = new String[this.mida][this.mida];
			
		for (int x = 0; x < this.mida; x++) {
			for (int y = 0; y < this.mida; y++) {
				sb[x][y] = String.valueOf(getContingut(x,y));
			}
		}
		return sb;
	}	


	
	
}
	
