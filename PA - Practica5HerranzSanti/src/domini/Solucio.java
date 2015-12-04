package domini;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Solucio {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Joc joc = new Joc();
					final String resultat = joc.trobar2Solucions();
		            System.out.println(resultat);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
		
	

	private Joc joc;				// referència al joc
	private int moviment = 0;		// contador de moviments
	private long iteracions = 0L; 	// contador d'iteracions per informació

	private int contador = 1;
	
    public long getIteracions() {
		return iteracions;
	}
    /**
     * Guardarem les 2 solució com seqüències de taulells per poder fer veure el resultat
     */
    private ArrayList<Taulell> sequencia;

    
	Solucio(Joc joc){
		// establir referència al joc
		this.joc = joc;
		
		sequencia = new ArrayList<Taulell>();

	}
    
	
	public Taulell getSequenciaActual(){
		return sequencia.get(sequencia.size());
	}	
	
	public Taulell getSequencia(int move){
		return sequencia.get(move);
	}	    

	public void guardarTaulell() {
		
		Taulell t = null;
		if (moviment>0 && moviment<sequencia.size()) 
			t = sequencia.get(moviment);
		if (t == null) {
			t = new Taulell(joc.getTaulellMida());
			sequencia.add(t);
		}
		t.setContingut(Joc.copiaMatriu(joc.getTaulell().caselles()));
		moviment++;
	}		

	
	public int getMoviment(){
		return sequencia.size();
	}
	public int getMovimentActual(){
		return moviment;
	}

	public boolean  trobar1Solucio() throws Exception {

//		return trobarSolucio_metode1();
		return trobarSolucio_metode2(1,1);
	}
		
	
	public boolean  trobar2Solucions() throws Exception {

//		return trobarSolucio_metode1();
		return trobarSolucio_metode2(1,2);
	}
	

	private boolean trobarSolucio_metode1() {

		// The base case: if it's already solved, we're done
	    if (Joc.equal(joc.getTaulell().caselles(),joc.getTaulellFinal()))
	        return true;

	    // Get all possible moves from this point
	    ArrayList<int[]> movs = new ArrayList<int[]>();
    	for (int x = 0; x < joc.getTaulellMida(); x++)
            for (int y = 0; y < joc.getTaulellMida(); y++) 
            	if(joc.getTaulell().caselles()[x][y] == Joc.CASELLA_OCUPADA) 
            		movs.add(new int[]{x,y});

		//Collections.shuffle(movs);

    	int movs_count = movs.size();
    	
    	if(iteracions%100000000==1) System.out.print("1");
    	
    	
	    for (int[] movement : movs) 
	        for (int direccio : Joc.direccions) 	// per totes les direccions
	    {
	    	
        	// Calcular nova posició al fer el salt
            int novaX = joc.getNovaX(movement[0], direccio);
            int novaY = joc.getNovaY(movement[1], direccio);
            
	        if (joc.esMovimentAcceptable(movement[0], movement[1], novaX, novaY)) {
	            joc.ferMoviment(movement[0], movement[1], novaX, novaY); 

	            Taulell t = new Taulell(joc.getTaulellMida());
	            t.setContingut(Joc.copiaMatriu(joc.getTaulell().caselles()));
	            sequencia.add(t);
	            
	            if (trobarSolucio_metode1()) {
	                // That move led to success :-)
//                    return true;
	            	
                	if(contador==2) {
                		System.out.println("***************");
                		System.out.println("SEGONA SOLUCIÓ");
                		System.out.println("***************");
                    	imprimir();
                    	
                        return true;
                	} else {
                		System.out.println("***************");
                		System.out.println("PRIMERA SOLUCIÓ");
                		System.out.println("***************");
                    	imprimir();
                		sequencia.clear();;
                		
                		contador++;
                		return false;
                	}
	            } else {
	                // That move led to failure :-(
	            	sequencia.remove(t);
	                joc.desferMoviment(movement[0], movement[1], direccio);
	            }
	        }
	    }
	    return false;
	}	
	
	
	/**
     * Tècnica Backtracking per resoldre el joc del Continental
     * 
     * @param mov moviment actual, començant per 1
     * @throws Exception 
     */
    private boolean trobarSolucio_metode2(int mov, int sol_cont) throws Exception {
    	
        	for (int x = 0; x < joc.getTaulellMida(); x++)					// per totes les files
        		for (int y = 0; y < joc.getTaulellMida(); y++)				// per totes les columnes
        			for (int direccio : Joc.direccions)			// per totes les direccions
                     	{ 
                        	iteracions++;

                        	if(iteracions%100000000==1) System.out.print("2");

                        	// Calcular nova posició al fer el salt
                            int novaX = joc.getNovaX(x, direccio);
                            int novaY = joc.getNovaY(y, direccio);
                        	
                        	if (joc.esMovimentAcceptable(x, y, novaX, novaY)) {
                            	
                        		joc.ferMoviment(x, y, direccio);
                            	
                        	    //joc.historial.guardar(x, y, novaX, novaY);
                	            Taulell t = new Taulell(joc.getTaulellMida());
                	            t.setContingut(Joc.copiaMatriu(joc.getTaulell().caselles()));
                	            sequencia.add(t);
                                
                        		// Condició de solució 
                        		if (Joc.equal(joc.getTaulell().caselles(),joc.getTaulellFinal())) { //

//                        			 return true;
                        			
                                	if(contador==sol_cont) {
                                		System.out.println("***************");
                                		System.out.println("    SOLUCIÓ" + contador);
                                		System.out.println("***************");

                                		imprimir();
                                    	
                                        return true;
                                	} else {
                                		
                                		System.out.println("***************");
                                		System.out.println(" SOLUCIÓ" + contador);
                                		System.out.println("***************");
                                    	imprimir();
                                    	
                                		sequencia.clear();
                                        
                                		contador++;
                                		
                                		return false;
                                	}                               
                                    
                                } else {
                                        if ( trobarSolucio_metode2(mov + 1,sol_cont)) {  // Crida recursiva al següent moviment
                                                return true;
                                        } else {
                                        	//
                                        	joc.desferMoviment(x, y, direccio);
                                        	//joc.historial.desferUltimMoviment();
                                        	sequencia.remove(t);
                                        }
                                }
                            }
                        }
        
        return false;
    }	
	
    
    

    void imprimir() {
        
        for (int mov = 0; mov < sequencia.size(); mov++) {
            System.out.println(String.format("Moviment %s", mov+1));

            int[][] caselles =  sequencia.get(mov).caselles();
            for (int x = 0; x < caselles.length; x++) {
                    for (int y = 0; y < caselles[x].length; y++) {
                            System.out.print(caselles[x][y]);
                    }
                    System.out.println();
            }
            System.out.println();
        	
        }

        int i=1;
        for (Moviment c: joc.historial.getllistaMoviments()) {
            System.out.println(
            		String.format("%d. fitxa a moure:(%s,%s)  casella moguda:(%s,%s)   fitxa menjada:(%s,%s)"
            		, i++
            		, c.coordInici[0],c.coordInici[1]
					, c.coordFinal[0],c.coordFinal[1]
					, c.coordMenjada[0],c.coordMenjada[1]
            				));
        }
    
    }


    
}
