package domini;

/**
 * El continental és un joc solitari de taula que consta de 32 peces que es col∙loquen en 
 * un taulell que té forma de creu i que consta de 33 caselles. Al començar el joc es 
 * col∙loquen totes les peces sobre el taulell deixant una única casella buida, la central
 */
public class Joc {
	
	/**
	 * Constants de contingut de la casella
	 */
    public static final int CASELLA_OCUPADA = 1,
    						CASELLA_NO_VALIDA = 8,
    						CASELLA_BUIDA = 0,
    						CASELLA_SELECCIONADA = 2;
    /**
     * Constants de moviments
     */
    public static final int AMUNT = 0, DRETA = 1, ABAIX = 2, ESQUERRA = 3;
    
    /**
     * Direccions posibles del moviment
     */
    public static final int [] direccions = {Joc.AMUNT, Joc.ESQUERRA, Joc.ABAIX, Joc.DRETA}; // 3.970.805

	public static final int TAULELL_31_FITXES = 1, TAULELL_4_FITXES = 4;
    
	private int mode;
	public String status = new String("Estat del joc"); 

	private Taulell taulell;
	int[] fitxa_seleccionada = null;
	int[] ultim_moviment = null;

	Historial historial;
	Solucio solucio;
	

	
	
	public Joc(){
		inici();
	}
	
	
	public void setMode(int value) {
		mode = value;
		inici();
	}
	
	/** Comprova si la situació del taulell es la situació final de la solució
	 * @return Cert si es la solució
	 */
	public boolean esSolucio(){
	    return (Joc.equal(this.getTaulell().caselles(),this.getTaulellFinal()));
	}

	public int getTaulellMida(){
		return getTaulellInicial().length;
	}
    /**
     * Taulell amb les posicions inicials de les fitxes
     */
	public int[][] getTaulellInicial(){

		int [][] caselles_inicial = {
			{8,8,1,1,1,8,8},
			{8,8,1,1,1,8,8},
			{1,1,1,1,1,1,1},
			{1,1,1,0,1,1,1},
			{1,1,1,1,1,1,1},
			{8,8,1,1,1,8,8},
			{8,8,1,1,1,8,8}
		};
				
		int [][] caselles_inicial_4 = {
			{8,8,0,0,0,8,8},
			{8,8,0,1,0,8,8},
			{0,0,0,1,0,0,0},
			{0,0,1,1,1,0,0},
			{0,0,0,0,0,0,0},
			{8,8,0,0,0,8,8},
			{8,8,0,0,0,8,8}
		};				
				
		if(this.mode == 1)
			return caselles_inicial;
		else
			return caselles_inicial_4;
	}
    /**
     * Taulell amb les posicions finals de les fitxes
     */
	int[][] getTaulellFinal(){

		int [][] caselles_final = {
			{8,8,0,0,0,8,8},
			{8,8,0,0,0,8,8},
			{0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0},
			{0,0,0,0,0,0,0},
			{8,8,0,0,0,8,8},
			{8,8,0,0,0,8,8}
		};

		return caselles_final;
	}

	
	
	public Taulell getTaulell() {
		return taulell;
	}

	public Solucio getSolucio() {
		return solucio;
	}

	public Historial getHistorial() {
		return historial;
	}



	public void reset(){
		inici();
	}	
	
	private void inici(){
		this.taulell = new Taulell(this.getTaulellMida());
		this.taulell.setContingut(getTaulellInicial());		
		this.fitxa_seleccionada = null;
		
		this.historial = new Historial();
		this.solucio = new Solucio(this);
	}
	
/**
 * controladorJoc gestiona la lógica dels moviments del joc
 * @param x
 * @param y
 * @throws Exception quan la casella seleccionada no es pot jugar
 */
	public void controladorJoc( int x, int y) throws Exception {
		
		int c0 = this.getTaulell().getContingut(x, y); 
		if(c0 == Joc.CASELLA_NO_VALIDA) throw new Exception("Casella no vàlida");
		
		if(fitxa_seleccionada == null) {
			if( c0 == Joc.CASELLA_OCUPADA) { // AGAFAR FITXA
				this.getTaulell().setContingut(x, y, Joc.CASELLA_SELECCIONADA);
				fitxa_seleccionada = new int[]{x,y};
				ultim_moviment = new int[] {x,y};
				
				System.out.println(String.format("AGAFAR FITXA (%s,%s)", x, y));
			}
		} else {
			
			if(fitxa_seleccionada[0] == x && fitxa_seleccionada[1] == y) { // DEIXAR FITXA

				this.getTaulell().setContingut(x, y, Joc.CASELLA_OCUPADA);
				fitxa_seleccionada = null;
				ultim_moviment = null;

				System.out.println(String.format("DEIXAR FITXA (%s,%s)", x, y));

			} else if((fitxa_seleccionada[0] != x | fitxa_seleccionada[1] != y) 
					& c0 == Joc.CASELLA_OCUPADA) { // DEIXAR FITXA I AGAFAR NOVA FITXA 
				
				this.getTaulell().setContingut(fitxa_seleccionada[0], fitxa_seleccionada[1], Joc.CASELLA_OCUPADA);
				fitxa_seleccionada = null;
				ultim_moviment = null;

				this.getTaulell().setContingut(x, y, Joc.CASELLA_SELECCIONADA);
				fitxa_seleccionada = new int[]{x,y};
				ultim_moviment = new int[] {x,y};
				
				System.out.println(String.format("%d. (%s,%s) DEIXAR I AGAFAR NOVA FITXA", this.historial.getMoviments() , x,y));

			} else if(ultim_moviment != null) { // MENJAR FITXA
				
            	if (this.esMovimentAcceptable(ultim_moviment[0], ultim_moviment[1], x, y)) {

            		this.ferMoviment(ultim_moviment[0], ultim_moviment[1], x, y);
            		
					int xIni = ultim_moviment[0];
					int yIni = ultim_moviment[1];
					int xFin = x;
					int yFin = y;

					this.historial.ferMoviment(ultim_moviment[0], ultim_moviment[1], x,y);
					System.out.println(String.format("%d. (%s,%s) MENJAR FITXA ->(%s,%s)", this.historial.getMoviments() , xIni, yIni, xFin, yFin));

					this.imprimir();

					fitxa_seleccionada = new int[] {x,y};
					ultim_moviment = new int[] {x,y};
					
					if(this.esSolucio()) {
						this.status = "JOC ACABAT !!!";
						System.out.println("JOC ACABAT !!!");
						return;
					}
					
					this.getTaulell().setContingut(x, y, Joc.CASELLA_SELECCIONADA);
            	}
			}
		}

		String sel = " ";
		if(fitxa_seleccionada!= null)
			sel = String.format("[x:%s y:%s]", fitxa_seleccionada[0], fitxa_seleccionada[1]);
		status = sel;
	}

	/**
	 * 
	 * @param n Número de solucions a trobar
	 * @throws Exception
	 */
	public void trobarNSolucions(int n) throws Exception{
		this.solucio.trobarNSolucions(n);
	}
    

    /**
     * Comprova si la posició està dins dels límits, hi ha fitxa a la casella indicada,
     * està buida la casella (NovaX, NovaY) i hi ha una fitxa per saltar entre mig
     * Retorna true si el moviment compleix amb aquestes regles del joc.
     */
    boolean esMovimentAcceptable(int x, int y, int novaX, int novaY) {
    	
            boolean inside =     
    		   x >= 0 
            && x >= 0
            && x < this.getTaulell().caselles().length 
            && y < this.getTaulell().caselles()[x].length
            && novaX >= 0 
            && novaY >= 0 
            && novaX < this.getTaulell().caselles().length 
            && novaY < this.getTaulell().caselles()[novaX].length
            ;

            boolean validDirection = false;
        	for (int direccio : Joc.direccions)			// per totes les direccions
         	{ 
                int validX = this.getNovaX(x, direccio);
                int validY = this.getNovaY(y, direccio);    	

                validDirection = (validX == novaX && validY == novaY);
            	if (validDirection) break;
         	}
                    
            return     inside                     
                    && validDirection 
            		&& esCasellaOcupada(x,y)								// Casella ha d'estar ocupada
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
    	historial.ferMoviment(x, y, novaX, novaY);
        
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
            try {
				historial.desferUltimMoviment();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
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




public static boolean equal(int[][] array1, int[][] array2) {

        if (array1 == null || array2 == null) return false;

    	if (array1.length != array2.length)
        	  return false;
      else
              for (int i = 0; i < array2.length; i++){
                  if (array1[i].length != array2[i].length) return false;
                  for (int j = 0; j < array2[i].length; j++) {
                  if (array2[i][j] != array1[i][j]) {
                	  return false;    
                  }                 
              }
            }
        return true;
    }


	
	


}