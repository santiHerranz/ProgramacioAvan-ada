package domini;

public class Solucio {

	private Joc joc;				// referència al joc
	private int moviment = 0;		// contador de moviments
	private long iteracions = 0L; 	// contador d'iteracions per informació

	private int mov_max = 0;
	private int posicioFinal[] = null;
	
    public long getIteracions() {
		return iteracions;
	}
    /**
     * Guardarem la solució com una seqüència de taulells per poder fer l'animació
     */
    private Taulell [] sequencia;

    
	Solucio(Joc joc){
		// establir referència al joc
		this.joc = joc;
		
		sequencia = new Taulell[joc.getTaulell().getMovimentsPosibles()];

		// Crear la seqüència de taulells
        for (int i = 0; i < sequencia.length; i++) 
            sequencia[i] = new Taulell(joc.mida);
        
    	mov_max = sequencia.length;
		posicioFinal = joc.getTaulell().getPosicioFinal();
		
        
	}
    
	
	public Taulell getSequenciaActual(){
		return sequencia[moviment];
	}	
	
	public Taulell getSequencia(int move){
		return sequencia[move];
	}	    

	public void guardarTaulell() {
		sequencia[moviment++].setContingut(Joc.copiaMatriu(joc.getTaulell().caselles()));
	}		

	
	public int getMoviment(){
		return sequencia.length;
	}
	

	
	/**
     * Tècnica Backtracking per resoldre el joc del Continental
     * 
     * @param mov moviment actual, començant per 1
     * @throws Exception 
     */
    public boolean trobarSolucio(int mov) throws Exception {
    	
    	
    	if(posicioFinal== null) return false; // Si no hi ha una posició buida, no hi ha solució posible 
    	
        if(mov <= mov_max)										// mentre hi hagin fitxes a moure
        														// Recorregut:
        	for (int x = 0; x < joc.mida; x++)					// per totes les files
                for (int y = 0; y < joc.mida; y++)				// per totes les columnes
                        for (int direccio : Joc.direccions) {	// per totes les direccions
                        	iteracions++;

                        	// Calcular nova posició al fer el salt
                            int novaX = joc.getNovaX(x, direccio);
                            int novaY = joc.getNovaY(y, direccio);
                        	
                        	if (joc.esMovimentAcceptable(x, y, novaX, novaY)) {
                            	
                        		joc.ferMoviment(x, y, direccio);
                            	
                        	    joc.historial.guardar(x, y);
                        		guardarTaulell();
                                
                        		// Condició de solució 
                                if ( (mov == mov_max && joc.esCasellaOcupada(posicioFinal[0], posicioFinal[1]))) {
                                    return true;
                                } else {
                                        if ( trobarSolucio(mov + 1)) {  // Crida recursiva al següent moviment
                                                return true;
                                        } else {
                                        	//
                                        	joc.desferMoviment(x, y, direccio);
                                        	joc.historial.desferUltimMoviment();
                                        	moviment--;
                                        }
                                }
                            }
                        }
        
        return false;
    }	
	
    
    
    void imprimir() {
    	
        for (int mov = 0; mov < sequencia.length; mov++) {
    		int[][] estatActual =  sequencia[mov].caselles();
            for (int x = 0; x < estatActual.length; x++) {
                    for (int y = 0; y < estatActual[x].length; y++) {
                            System.out.print(estatActual[x][y]);
                    }
                    System.out.println();
            }
            System.out.println();
        	
        }

        int i=1;
        for (int[] c: joc.historial.getllistaMoviments()) {
            System.out.println(String.format("%d.(%s,%s)", i++, c[0],c[1]));
        }
    
    }


    
}
