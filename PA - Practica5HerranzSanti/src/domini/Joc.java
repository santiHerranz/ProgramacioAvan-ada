package domini;

/**
 * El continental és un joc solitari de taula que consta de 32 peces que es col∙loquen en 
 * un taulell que té forma de creu i que consta de 33 caselles. Al començar el joc es 
 * col∙loquen totes les peces sobre el taulell deixant una única casella buida, la central
 */
public class Joc {
	
	
    public static final int CASELLA_OCUPADA = 1;
    public static final int CASELLA_NO_VALIDA = 8;
    public static final int CASELLA_BUIDA = 0;
    public static final int CASELLA_SELECCIONADA = 2;

    public static final int DRETA = 0;
    public static final int AMUNT = 1;
    public static final int ESQUERRA = 2;
    public static final int ABAIX = 3;
    
    /**
     * Direccions posibles del moviment
     */
    public static final int [] direccions = {Joc.DRETA, Joc.AMUNT, Joc.ESQUERRA, Joc.ABAIX};	

	public int mida = 7;
	public String status = new String("Estat del joc"); 

	private Taulell taulell;
	int[] fitxa_seleccionada = null;
	Historial historial;
	Solucio solucio;
	
    /**
     * Taulell amb les posicions inicials de les fitxes
     */
	private int[][] getTaulellInicial(){

		int [][] caselles_inicial = {
                {8, 8, 1, 1, 1, 8, 8},
                {8, 8, 1, 1, 1, 8, 8},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {8, 8, 1, 1, 1, 8, 8},
                {8, 8, 1, 1, 1, 8, 8}
		};
		return caselles_inicial;
	}
	
	public Taulell getTaulell() {
		return taulell;
	}
	public void setTaulell(Taulell taulell) {
		this.taulell = taulell;
	}
	public Solucio getSolucio() {
		return solucio;
	}
	public void setSolucio(Solucio solucio) {
		this.solucio = solucio;
	}
	public Historial getHistorial() {
		return historial;
	}
	

	public Joc(){
		inici();
	}

	public void reset(){
		inici();
	}	
	
	private void inici(){

		this.taulell = new Taulell(mida);
		this.taulell.setContingut(getTaulellInicial());		
		this.fitxa_seleccionada = null;
		
		this.historial = new Historial();
		this.solucio = new Solucio(this);

		this.imprimir();
	}
	
/**
 * controladorJoc gestiona la lógica dels moviments del joc
 * @param x
 * @param y
 * @throws Exception quan la casella seleccionada no es pot jugar
 */
	public void controladorJoc( int x, int y) throws Exception {
		
		String sel = new String("");
		int c0 = this.getTaulell().getContingut(x, y); 

		if(c0 == Joc.CASELLA_NO_VALIDA) throw new Exception("Casella no vàlida");

		int[] actual = this.historial.obtenirUltimMoviment();
		
		if(fitxa_seleccionada == null) {
			if( c0 == Joc.CASELLA_OCUPADA) { // AGAFAR FITXA
				this.getTaulell().setContingut(x, y, Joc.CASELLA_SELECCIONADA);
				fitxa_seleccionada = new int[]{x,y};
				
				this.historial.guardar(x,y);
				System.out.println(String.format("%d. (%s,%s) AGAFAR FITXA", this.historial.getMoviments() , x,y));
			}
		} else {
			
			
			if(fitxa_seleccionada[0] == x & fitxa_seleccionada[1] == y) { // DEIXAR FITXA
				this.getTaulell().setContingut(x, y, Joc.CASELLA_OCUPADA);
				fitxa_seleccionada = null;
				this.historial.desferUltimMoviment();

				System.out.println(String.format("%d. (%s,%s) DEIXAR FITXA", this.historial.getMoviments() , x,y));

			} else if((fitxa_seleccionada[0] != x | fitxa_seleccionada[1] != y) 
					& c0 == Joc.CASELLA_OCUPADA) { // DEIXAR FITXA I AGAFAR NOVA FITXA 
				
				this.getTaulell().setContingut(fitxa_seleccionada[0], fitxa_seleccionada[1], Joc.CASELLA_OCUPADA);
				fitxa_seleccionada = null;
				this.historial.desferUltimMoviment();

				this.getTaulell().setContingut(x, y, Joc.CASELLA_SELECCIONADA);
				fitxa_seleccionada = new int[]{x,y};
				
				this.historial.guardar(x,y);
				System.out.println(String.format("%d. (%s,%s) DEIXAR I AGAFAR NOVA FITXA", this.historial.getMoviments() , x,y));

			} else if(actual != null) { // MENJAR FITXA
				
            	if (this.esMovimentAcceptable(actual[0], actual[1], x, y)) {

            		this.ferMoviment(actual[0], actual[1], x, y);
            		
					int xIni = fitxa_seleccionada[0];
					int yIni = fitxa_seleccionada[1];
					int xFin = x;
					int yFin = y;
					
					this.getTaulell().setContingut(x, y, Joc.CASELLA_SELECCIONADA);
					fitxa_seleccionada = new int[] {x,y};
					
					this.historial.guardar(x,y);
            		this.solucio.guardarTaulell();
					
					System.out.println(String.format("%d. (%s,%s) MENJAR FITXA ->(%s,%s)", this.historial.getMoviments() , xIni, yIni, xFin, yFin));

					this.imprimir();
            	}
			}
            	
		}

		if(fitxa_seleccionada!= null)
			sel = String.format("[x:%s y:%s]", fitxa_seleccionada[0], fitxa_seleccionada[1]);

		int c1 = this.getTaulell().getContingut(x, y); 
		status = String.format("Moviment %d. (x:%s y:%s)=%s->%s", this.historial.getMoviments()-1, x, y, c0, c1);
		if(sel!= null) status += " "+ sel;
		
	}

	/**
	 * 
	 * @return Torna un missatge amb el resultat
	 * @throws Exception
	 */
	public String solucio() throws Exception{
		
        long t1 = System.currentTimeMillis();
        
        int inici = 1;
        if(this.historial.getMoviments()>1)
        	inici = this.historial.getMoviments();
        
        if (this.solucio.trobarSolucio(inici)) {
        	long t2 = System.currentTimeMillis();
                
            this.solucio.imprimir();
            return "Solució trobada en " + (t2 - t1) + " milisegons ["+ this.solucio.getIteracions() +" iteracions]" ;
            
        } else {
                return "No hi ha solució!!";
        }		
		
	}

	
	


	

    
    

    /**
     * Comprova si la posició està dins dels límits, hi ha fitxa a la casella indicada,
     * està buida la casella (NovaX, NovaY) i hi ha una fitxa per saltar entre mig
     * Retorna true si el moviment compleix amb aquestes regles del joc.
     */
    boolean esMovimentAcceptable(int x, int y, int novaX, int novaY) {
            return     
            		   x >= 0 
                    && x >= 0
                    && x < this.getTaulell().caselles().length 
                    && y < this.getTaulell().caselles()[x].length
                    && esCasellaOcupada(x,y)								// Casella ha d'estar ocupada
                    && novaX >= 0 
                    && novaY >= 0 
                    && novaX < this.getTaulell().caselles().length 
                    && novaY < this.getTaulell().caselles()[novaX].length
                    && esCasellaBuida(novaX,novaY) 							// Casella nova ha d'estar buida
            		&& esCasellaOcupada((x + novaX) / 2, (y + novaY) / 2)	// Casella entre mig ha d'estar ocupada
            		;
    }
    
    /**
     * Salta la fitxa desde la posició indicada sobre la fitxa veina amb la direcció donada
     * i elimina la fitxa que ha saltat per sobre. 
     * */

    void ferMoviment(int x, int y, int direction) {
        int novaX = getNovaX(x, direction);
        int novaY = getNovaY(y, direction);
        ferMoviment(x, y, novaX, novaY);
    }   
    void ferMoviment(int x, int y, int novaX, int novaY) {
        setFitxa(novaX, novaY);
        buidarCasella(x, y);
        buidarCasella((x + novaX) / 2, (y + novaY) / 2);
    }

    /**
     * La fitxa salta enrere amb la direcció donada i la fitxa menjada torna al seu lloc.
     */
    void desferMoviment(int x, int y, int direction) {
            int newX = getNovaX(x, direction);
            int newY = getNovaY(y, direction);
            
            buidarCasella(newX, newY);
            setFitxa(x, y);
            setFitxa((x + newX) / 2, (y + newY) / 2);
    }
    
    int getNovaX(int x, int direction) {
            int newX = x;
            switch (direction) {
            case Joc.DRETA: newX += 2;
                        break;
            case Joc.ESQUERRA: newX -= 2;
            }
            return newX;
    }
    
    int getNovaY(int y, int direccio) {
            int novaY = y;
            
            switch (direccio) {
            case Joc.AMUNT: novaY -= 2;
                            break;
            case Joc.ABAIX: novaY += 2;
            }
            
            return novaY;
    } 


    void buidarCasella(int x, int y) {
    	this.getTaulell().caselles()[x][y] = Joc.CASELLA_BUIDA;
    }

    void setFitxa(int x, int y) {
    	this.getTaulell().caselles()[x][y] = Joc.CASELLA_OCUPADA;
    }    
    void setFitxaSeleccionada(int x, int y) {
    	this.getTaulell().caselles()[x][y] = Joc.CASELLA_SELECCIONADA;
    }    
    
    /**
     * Retorna cert si hi ha una fitxa a la posició indicada.
     */
    boolean esCasellaOcupada(int x, int y) {
    	int value = this.getTaulell().caselles()[x][y];
        return  value == Joc.CASELLA_OCUPADA || value == Joc.CASELLA_SELECCIONADA;
    }    
    boolean esCasellaBuida(int x, int y) {
        return this.getTaulell().caselles()[x][y] == Joc.CASELLA_BUIDA;
    }   
    	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void imprimir(){
		int[][] caselles = this.getTaulell().caselles();
		
            for (int x = 0; x < caselles.length; x++) {
                for (int y = 0; y < caselles[x].length; y++) {
                        System.out.print(caselles[x][y]);
                }
                System.out.println();
            }
            System.out.println();
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