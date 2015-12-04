package domini;

import java.util.Stack;

public class Historial {
	
	public int getMoviments () {
		return historial.size();
	}

	Historial(){
		this.historial = new Stack<Moviment>();
	}

	private Stack<Moviment> historial ;
	
	public void guardar(int x, int y, int novaX, int novaY, int menjaX, int menjaY) {
		historial.push(new Moviment(new int[]{x,y}, new int[]{novaX,novaY}, new int[]{menjaX,menjaY}));
	}
	
	/**
	 * 
	 * @return null quan no hi ha moviments
	 */
	public Moviment obtenirUltimMoviment(){
		if (historial.empty())
			return null;
		
		return historial.peek();
	}

	/**
	 * @return la posició de l'ultim moviment
	 * @throws Exception
	 */
	public Moviment desferUltimMoviment() throws Exception{
		if (historial.empty())
			throw new Exception ("Error desfer últim moviment:  no hi ha cap moviment");
		
		return historial.pop();
	}
	
	public Stack<Moviment> getllistaMoviments(){
		return this.historial;
	}
}
