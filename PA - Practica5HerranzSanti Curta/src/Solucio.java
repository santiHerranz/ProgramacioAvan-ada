import java.awt.EventQueue;

public class Solucio {
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Joc joc = new Joc();
					joc.setMode(Joc.TAULELL_4_FITXES);
					
					joc.imprimir();
					
			        long t1 = System.currentTimeMillis();
			        int n = 2;
			        if (joc.solucio.trobarNSolucions(n)) {
			        	
			        	long t2 = System.currentTimeMillis();

			        	System.out.println("");
						joc.imprimir();
			        	
			        	System.out.println("");
			            System.out.println(String.format(n+ " solucions trobades en " + (t2 - t1) + " ms [%,d iteracions]", joc.solucio.getIteracions())) ;
			            
			        } else {
			        	System.out.println("");
			        	System.out.println("No hi ha més solucions!!");
			        }	
			        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	

	private Joc joc;				// referència al joc
	private long iteracions = 0L; 	// contador d'iteracions per informació
	private int contador = 1;
	
	Solucio(Joc joc){
		// establir referència al joc
		this.joc = joc;
	}
	
	
    public long getIteracions() {
		return iteracions;
	}

	public boolean trobarNSolucions(int n) throws Exception {
		return trobarSolucio(1,n);
	}
	
	
	/**
     * Tècnica Backtracking per resoldre el joc del Continental
     * 
     * @param mov moviment actual, començant per 1
     * @throws Exception 
     */
    public boolean trobarSolucio(int nivell, int sol_cont) throws Exception {

		// The base case: if it's already solved, we're done
	    if (joc.esSolucio()){

	    	System.out.println();
	    	System.out.println();
    		System.out.println("SOLUCIÓ "+ contador);
    		System.out.println("***************");

    		//imprimir_sequencia();
    		joc.historial.imprimir();
    		
        	if(contador==sol_cont) {
        		
                return true;
        	} else {
        		contador++;
                return false;
        	}	    	
	    }    	
    	
    	for (int x = 0; x < joc.getTaulellMida(); x++)					// per totes les files
    		for (int y = 0; y < joc.getTaulellMida(); y++)				// per totes les columnes
    			for (int direccio : Joc.direccions)						// per totes les direccions
             	{ 
                	iteracions++;
                	if(iteracions%100000==0) System.out.print(".");

                	// Calcular nova posició al fer el salt
                    int novaX = joc.getNovaX(x, direccio);
                    int novaY = joc.getNovaY(y, direccio);
                    
        	        if (joc.esMovimentAcceptable(x, y, novaX, novaY)) {
        	        	
        	        	joc.ferMoviment(x, y, novaX, novaY); 
        	            
        	            if (trobarSolucio(nivell+1,sol_cont)) { // Crida recursiva
        	                // That move led to success :-)
                            return true;
        	            } else {
        	                // That move led to failure :-(
        	                joc.desferMoviment(x, y, direccio);
        	            }
        	        }
                }
        return false;
    }	
    
}
