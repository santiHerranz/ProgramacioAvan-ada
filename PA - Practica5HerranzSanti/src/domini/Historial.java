package domini;

import java.util.Stack;

public class Historial {
	
	public int getMoviments () {
		return historial.size();
	}

	Historial(){
		this.historial = new Stack<int[]>();
	}

	private Stack<int[]> historial ;
	
	public void guardar(int x, int y) {
		historial.push(new int[]{x,y});
	}
	
	/**
	 * 
	 * @return null quan no hi ha moviments
	 */
	public int[] obtenirUltimMoviment(){
		if (historial.empty())
			return null;
		
		return historial.peek();
	}

	/**
	 * @return la posició de l'ultim moviment
	 * @throws Exception
	 */
	public int[] desferUltimMoviment() throws Exception{
		if (historial.empty())
			throw new Exception ("Error desfer últim moviment:  no hi ha cap moviment");
		
		return historial.pop();
	}
	
	public Stack<int[]> getllistaMoviments(){
		return this.historial;
	}
}
