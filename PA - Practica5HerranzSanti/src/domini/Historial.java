package domini;

import java.util.Stack;

public class Historial {
	
	private Joc joc;
	public int moviments = 0;
	private Stack<Coord> historial ;
	
	Historial(Joc joc){
		this.joc = joc;
		this.moviments = 0;
		this.historial = new Stack<Coord>();
		
	}	
    



	
	
	public void guardarMoviment(int x, int y) {
		historial.push(new Coord(x,y));
		moviments++;
	}
	

	public Coord ultimMoviment() throws Exception{
		if (historial.empty())
			throw new Exception ("Error últim moviment:  no hi ha cap moviment");
		
		return historial.peek();
	}

	public Coord desferUltimMoviment() throws Exception{
		if (historial.empty())
			throw new Exception ("Error desfer últim moviment:  no hi ha cap moviment");

		moviments--;
		return historial.pop();
	}

	public Stack<Coord> getllistaMoviments(){
		return this.historial;
	}

}
