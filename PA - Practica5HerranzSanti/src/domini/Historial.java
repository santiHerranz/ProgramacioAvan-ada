package domini;

import java.util.ArrayList;

public class Historial {
	
	public int getMoviments () {
		return historial.size();
	}

	Historial(){
		this.historial = new ArrayList<Moviment>();
	}

    private ArrayList<Moviment> historial;
	
	public void ferMoviment(int x, int y, int novaX, int novaY) {
    	int menjaX = (x + novaX) / 2;
    	int menjaY = (y + novaY) / 2;
		historial.add(new Moviment(new int[]{x,y}, new int[]{novaX,novaY}, new int[]{menjaX,menjaY}));
	}
	
	/**
	 * 
	 * @return null quan no hi ha moviments
	 */
	public Moviment obtenirUltimMoviment(){
		if (historial.isEmpty())
			return null;
		
		return historial.get(historial.size()-1);
	}

	/**
	 * @return la posició de l'ultim moviment
	 * @throws Exception
	 */
	public Moviment desferUltimMoviment() throws Exception{
		if (historial.isEmpty())
			throw new Exception ("Error desfer últim moviment:  no hi ha cap moviment");
		Moviment m = obtenirUltimMoviment();
		historial.remove(m);
		return m;
	}
	
	public ArrayList<Moviment> getllistaMoviments(){
		return this.historial;
	}

public void imprimir(){
		
        int i=1;
        for (Moviment c: getllistaMoviments()) {
            System.out.println(
            		String.format("%d. Salt de (%s,%s) a (%s,%s) i (%s,%s) menjada"
            		, i++
            		, c.getCoordInici()[0],c.getCoordInici()[1]
					, c.getCoordFinal()[0],c.getCoordFinal()[1]
					, c.getCoordMenjada()[0],c.getCoordMenjada()[1]
			));
        }
    }	
}
