import java.util.ArrayList;

public class Solucio {
	
	private Continental joc;		// refer�ncia al joc
	private long iteracions = 0L; 	// contador d'iteracions per informaci�
	private int comptador_solucions_trobades = 0;
	
	Solucio(Continental joc){
		// establir refer�ncia al joc
		this.joc = joc;
		this.historial = new ArrayList<Moviment>();
		
	}
	
	
    public long getIteracions() {
		return iteracions;
	}

	public boolean trobarNSolucions(int n) throws Exception {
		return trobarSolucio(1,n);
	}
	
	
	/**
     * T�cnica Backtracking per resoldre el joc del Continental
     * @param nivell actual, comen�ant per 1
     * @param comptador de solucions
     * @throws Exception 
     */
    public boolean trobarSolucio(int nivell, int solucions_a_trobar) throws Exception {

		// The base case: if it's already solved, we're done
	    if (joc.esSolucio()){

	    	System.out.println();
	    	System.out.println();
    		System.out.println("SOLUCI� "+ (comptador_solucions_trobades+1));
    		System.out.println("***************");

    		//imprimir_sequencia();
//    		joc.historial.imprimir();
    		this.imprimir();
    		
    		comptador_solucions_trobades++;

    		if(comptador_solucions_trobades==solucions_a_trobar) {
                return true;
        	} else {
                return false;
        	}	    	
	    }    	
    	
    	for (int x = 0; x < joc.getTaulellMida(); x++)			// per totes les files
    		for (int y = 0; y < joc.getTaulellMida(); y++)		// per totes les columnes
    			for (int direccio : Continental.direccions)		// per totes les direccions
             	{ 
                	iteracions++;
                	if(iteracions%100000==0) System.out.print(".");

                	// Calcular nova posici� per fer el salt
                    int novaX = joc.getNovaX(x, direccio);
                    int novaY = joc.getNovaY(y, direccio);
                    
        	        if (joc.esMovimentAcceptable(x, y, novaX, novaY)) {
        	        	
        	        	joc.ferMoviment(x, y, novaX, novaY); 
        	            
        	            if (trobarSolucio(nivell+1,solucions_a_trobar)) { // Crida recursiva
        	                // Moviment cap a la soluci�
                            return true;
        	            } else {
        	                // Moviment que no arriba a la soluci�
        	                joc.desferMoviment(x, y, direccio);
        	            }
        	        }
                }
        return false;
    }	
    
    
    
    private ArrayList<Moviment> historial;
    
	public void ferMoviment(int x, int y, int novaX, int novaY) {
    	int menjaX = (x + novaX) / 2;
    	int menjaY = (y + novaY) / 2;

    	// Copiar taulell actual per guardar a la seq��ncia 
    	Taulell t = new Taulell(joc.getTaulellMida());
        t.setContingut(Continental.copiaMatriu(joc.getTaulell().caselles()));
    	
    	historial.add(new Moviment(new Coordenada(x,y)
				, new Coordenada(novaX,novaY)
				, new Coordenada(menjaX,menjaY)
				, t
				));
		
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
	 * @return l'ultim moviment
	 * @throws Exception
	 */
	public Moviment desferUltimMoviment() throws Exception{
		if (historial.isEmpty())
			throw new Exception ("Error desfer �ltim moviment:  no hi ha cap moviment");
		Moviment m = obtenirUltimMoviment();
		historial.remove(m);
		return m;
	}

	public void imprimir(){
        int i=1;
        for (Moviment c: this.historial) {
            System.out.println(
            		String.format("%d. Inici:%s\tFinal:%s\tMenjada:%s"
            		, i++
            		, c.getInici()
					, c.getFinal()
					, c.getMenjada()
			));
            c.getTaulell().imprimir();
        }
    }	    
    
}
