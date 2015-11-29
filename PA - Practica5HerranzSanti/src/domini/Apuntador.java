package domini;

import java.util.Stack;

public class Apuntador {
	
	
	public int moviments = 0;

	Apuntador(){
		this.moviments = 0;
		this.historial = new Stack<Coord>();
	}

	private Stack<Coord> historial ;
	
	
	public void guardar(int x, int y) {
		historial.push(new Coord(x,y));
		moviments++;
	}
	
	public Coord ultimMoviment() throws Exception{
		return historial.peek();
	}

	public Coord desferUltimMoviment(){
		moviments--;
		return historial.pop();
	}
}
