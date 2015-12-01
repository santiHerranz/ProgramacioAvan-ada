package domini;

public class Solucio {

	private Joc joc;
	private int moviment = 0;

    /**
     * the solution given as a sequence of board situations
     */
    private Taulell [] sequencia = new Taulell[31];
	
	public Taulell getSequenciaActual(){
		return sequencia[moviment];
	}	
	
	public Taulell getSequencia(int move){
		return sequencia[move];
	}	    

	
    Solucio(Joc joc){
		this.joc = joc;
		
        for (int i = 0; i < sequencia.length; i++) 
            sequencia[i] = new Taulell(joc.mida);		
		
	}
    

	public void guardarTaulell() {
		sequencia[moviment].setContingut(copiaMatriu(joc.getTaulell().caselles()));
	}		

	
	public int getMoviment(){
		return sequencia.length;
	}
	

	
	/**
     * Backtracking algorithm to solve the solitare puzzle
     * 
     * @param mov current number of move, first move must be 1
     * @throws Exception 
     */
    public boolean trobarSolucio(int mov) throws Exception {
            for (int x = 0; mov <= 31 && x < joc.mida; x++) {
                    for (int y = 0; y < joc.mida; y++) {
                            for (int direccio : joc.directions) {
                                    if (saltar(x, y, direccio)) {
                                    	
                                    	    joc.historial.guardarMoviment(x, y);
                                    		guardarTaulell();
                                    		moviment++;
                                            
                                            if (! (mov >= 31 && this.esCasellaOcupada(3, 3))) {
                                                    if ( trobarSolucio(mov + 1)) {
                                                            return true;
                                                    } else {
                                                    	saltarEnrere(x, y, direccio);
                                                            
                                                    	joc.historial.desferUltimMoviment();
                                                    	moviment--;
                                                    }
                                            } else {
                                                    return true;
                                            }
                                    }
                            }
                    }                       
            }
            
            return false;
    }	
	
    
    
    
    /**
     * Comprova si hi ha fitxa a la casella (x,y)
     * , està buida la casella (NovaX, NovaY) 
     * i hi ha una fitxa per saltar entre mig
     */
    private boolean esMovimentValid(int x, int y, int novaX, int novaY) {
            return     0 <= x 
                    && 0 <= y 
            		&& x < joc.getTaulell().caselles().length 
                    && y < joc.getTaulell().caselles()[x].length
                    && 0 <= novaX 
                    && 0 <= novaY 
                    && novaX < joc.getTaulell().caselles().length 
                    && novaY < joc.getTaulell().caselles()[novaX].length
                    && esCasellaBuida(novaX,novaY)
                    && esCasellaOcupada((x + novaX) / 2, (y + novaY) / 2)
                    && esCasellaOcupada(x,y);
                    
    }	
    
    
    /**
     * Jumps the peg from (x,y) over the neighbouring peg in the given <code>direction</code>
     * and removes the peg we have jumped over. 
     * Returns true if the move was according to the game rules; and false otherwise.
     * The game board only changes state, if the move was valid.
     *       */
    public boolean saltar(int x, int y, int direccio) {
            int novaX = getNovaX(x, direccio);
            int novaY = getNovaY(y, direccio);

            if ( esMovimentValid(x, y, novaX, novaY)) {
                    setFitxa(novaX, novaY);
                    buidarCasella(x, y);
                    buidarCasella((x + novaX) / 2, (y + novaY) / 2);
                    
                    return true;
            }
            
            return false;
    }
    

    /**
     * A peg "jumps back" and the previously removed peg is returned at
     * its proper position.
     */
    public void saltarEnrere(int x, int y, int direction) {
            int newX = getNovaX(x, direction);
            int newY = getNovaY(y, direction);
            
            buidarCasella(newX, newY);
            setFitxa(x, y);
            setFitxa((x + newX) / 2, (y + newY) / 2);
    }
    
    private int getNovaX(int x, int direction) {
            int newX = x;
            switch (direction) {
            case Joc.DRETA: newX += 2;
                        break;
            case Joc.ESQUERRA: newX -= 2;
            }
            return newX;
    }
    
    private int getNovaY(int y, int direccio) {
            int novaY = y;
            
            switch (direccio) {
            case Joc.AMUNT: novaY -= 2;
                            break;
            case Joc.ABAIX: novaY += 2;
            }
            
            return novaY;
    } 


    public void buidarCasella(int x, int y) {
    	joc.getTaulell().caselles()[x][y] = Joc.CASELLA_BUIDA;
    }

    public void setFitxa(int x, int y) {
    	joc.getTaulell().caselles()[x][y] = Joc.CASELLA_OCUPADA;
    }    
    public void setFitxaSeleccionada(int x, int y) {
    	joc.getTaulell().caselles()[x][y] = Joc.CASELLA_SELECCIONADA;
    }    
    
    /**
     * Returns true if there is a peg at (x,y).
     */
    public boolean esCasellaOcupada(int x, int y) {
    	int value = joc.getTaulell().caselles()[x][y];
        return  value == Joc.CASELLA_OCUPADA || value == Joc.CASELLA_SELECCIONADA;
    }    
    public boolean esCasellaBuida(int x, int y) {
    	int value = joc.getTaulell().caselles()[x][y];
        return value == Joc.CASELLA_BUIDA;
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
        for (Coord c: joc.historial.getllistaMoviments()) {
            System.out.println(String.format("%d.(%s,%s)", i++, c.x,c.y));
        }
    
    }

	public static int[][] copiaMatriu(int[][] input) {
	    if (input == null)
	        return null;
	    int[][] result = new int[input.length][];
	    for (int r = 0; r < input.length; r++) {
	        result[r] = input[r].clone();
	    }
	    return result;
	}	
	
    
}
