package domini;

import java.awt.EventQueue;
import java.util.ArrayList;

public class Solucio {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Joc joc = new Joc();
					joc.setMode(Joc.TAULELL_31_FITXES); //Joc.TAULELL_4_FITXES //Joc.TAULELL_31_FITXES
					
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
	
    public long getIteracions() {
		return iteracions;
	}
    /**
     * Guardarem les 2 solució com seqüències de taulells per poder fer veure el resultat
     */
    private ArrayList<Taulell> sequencia;
    private ArrayList<Taulell> solucio_final;

    
	Solucio(Joc joc){
		// establir referència al joc
		this.joc = joc;
		
		sequencia = new ArrayList<Taulell>();
		solucio_final = new ArrayList<Taulell>();
	}
	

    
	public ArrayList<Taulell> getSequencia() {
		return solucio_final;
	}
	
	public Taulell getSequenciaActual(){
		return solucio_final.get(solucio_final.size());
	}	
	
	public Taulell getSequencia(int move){
		return solucio_final.get(move);
	}	    
	public int getMoviments(){
		return solucio_final.size();
	}


	public boolean trobarNSolucions(int n) throws Exception {
		sequencia.clear();
		solucio_final.clear();
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
    		System.out.println("SOLUCIÓ "+ contador);
    		System.out.println("***************");

        	Taulell it = new Taulell(joc.getTaulellMida());
            it.setContingut(Joc.copiaMatriu(joc.getTaulellInicial()));
            solucio_final.add(it);
        	
    		for (Taulell tt : sequencia)
    			solucio_final.add(tt);	

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
    			for (int direccio : Joc.direccions)			// per totes les direccions
             	{ 
                	iteracions++;
                	if(iteracions%100000000==1) System.out.print(".");

                	// Calcular nova posició al fer el salt
                    int novaX = joc.getNovaX(x, direccio);
                    int novaY = joc.getNovaY(y, direccio);
                    
        	        if (joc.esMovimentAcceptable(x, y, novaX, novaY)) {
        	        	
        	        	
        	        	joc.ferMoviment(x, y, novaX, novaY); 

        	        	// Copiar taulell actual per guardar a la seqüència 
        	        	Taulell t = new Taulell(joc.getTaulellMida());
        	            t.setContingut(Joc.copiaMatriu(joc.getTaulell().caselles()));

        	            sequencia.add(t);
        	            
        	            if (trobarSolucio(nivell+1,sol_cont)) { // Crida recursiva
        	                // That move led to success :-)
                            return true;
        	            } else {
        	                // That move led to failure :-(
        	                joc.desferMoviment(x, y, direccio);
        	            	sequencia.remove(t);
        	            }
        	        }
                }
        return false;
    }	
	

    void imprimir_sequencia() {
        
        for (int mov = 0; mov < sequencia.size(); mov++) {
            int[][] caselles =  sequencia.get(mov).caselles();
            System.out.println("int [][] S"+ contador +"_M"+ (sequencia.size()-mov) +" = {");
            for (int x = 0; x < caselles.length; x++) {
                System.out.print("{");
                    for (int y = 0; y < caselles[x].length; y++) {
                            System.out.print(caselles[x][y]);
                            if(y<caselles[x].length-1)
                            	System.out.print(",");
                    }
                    System.out.print("}");
                    if(x<caselles.length-1)
                    	System.out.print(",");
                	System.out.println();
            }
            System.out.println("};");
            
        }
    }




    
}
