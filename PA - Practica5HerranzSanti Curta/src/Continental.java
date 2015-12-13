import java.awt.EventQueue;

/**
 * PROGRAMACIÓ AVANÇADA - PRÀCTICA 5 - EL CONTINENTAL
 * El continental és un joc solitari de taula que consta de 32 peces que es col∙loquen en 
 * un taulell que té forma de creu i que consta de 33 caselles. Al començar el joc es 
 * col∙loquen totes les peces sobre el taulell deixant una única casella buida, la central
 */
public class Continental {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Continental joc = new Continental();
					//joc.setMode(Continental.TAULELL_4_FITXES); //Proves amb 4 Fitxes
					
					joc.imprimir();
					
			        long t1 = System.currentTimeMillis();

			        int n = 2; // Número de solucions a trobar
			        if (joc.solucio.trobarNSolucions(n)) {
			        	
			        	long t2 = System.currentTimeMillis();

			        	System.out.println("");
			        	
			            System.out.println(String.format(n+ " solucions trobades en " + (t2 - t1) + " ms [%,d iteracions]", joc.solucio.getIteracions())) ;
			            
			        	System.out.println("NOTA: La diferència es troba al moviment 24");
			        	System.out.println("      Es considera solució diferent encara que siguin els mateixos moviments canviant l'ordre");
			        	System.out.println("      Segons bibliografia trobada, descartant les rotacions i canvis d'ordre, només hi han dos solucions possibles");
			        	System.out.println("      http://www.recmath.org/pegsolitaire/diagonal/index.html");
			            
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
	
	/**
	 * Constants de contingut de la casella
	 */
    public static final int CASELLA_OCUPADA = 1,
    						CASELLA_NO_VALIDA = 8,
    						CASELLA_BUIDA = 0;
    /**
     * Constants de moviments
     */
    public static final int AMUNT = 0, 
				    		DRETA = 1, 
				    		ABAIX = 2, 
				    		ESQUERRA = 3;
    
    /**
     * Direccions posibles del moviment ordenades
     */
    public static final int [] direccions = { 
    		Continental.AMUNT ,
    		Continental.DRETA , 
    		Continental.ABAIX , 
    		Continental.ESQUERRA   
    		}; // 4.288.329 Iteracions

    // Mode de joc: Complert 31 fitxes, Proves amb 4 fitxes
    public static final int TAULELL_31_FITXES = 1, TAULELL_4_FITXES = 4;
	private int mode = Continental.TAULELL_31_FITXES;

	private Taulell taulell;
	Solucio solucio;
	

	public Continental(){
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
	    return (Continental.equal(this.getTaulell().caselles(),this.getTaulellFinal()));
	}

	public int getTaulellMida(){
		return getTaulellInicial().length;
	}
    /**
     * Taulell amb les posicions inicials de les fitxes
     */
	public int[][] getTaulellInicial(){

		int [][] caselles_inicial_31 = {
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
				
		if(this.mode == Continental.TAULELL_31_FITXES)
			return caselles_inicial_31;
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

	public void reset(){
		inici();
	}	
	
	private void inici(){
		this.taulell = new Taulell(this.getTaulellMida());
		this.taulell.setContingut(getTaulellInicial());		

		this.solucio = new Solucio(this);
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
        	for (int direccio : Continental.direccions)			// per totes les direccions
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
     * Salta la fitxa desde la posició indicada sobre la fitxa veïna amb la direcció donada
     * i elimina la fitxa que ha saltat per sobre. 
     * */
    void ferMoviment(int x, int y, int novaX, int novaY) {
        setFitxa(novaX, novaY);
        buidarCasella(x, y);
        buidarCasella((x + novaX) / 2, (y + novaY) / 2);
        solucio.ferMoviment(x, y, novaX, novaY);
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
				solucio.desferUltimMoviment();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    int getNovaX(int x, int direction) {
            int newX = x;
            switch (direction) {
            case Continental.DRETA: newX += 2;
                        break;
            case Continental.ESQUERRA: newX -= 2;
            }
            return newX;
    }
    
    int getNovaY(int y, int direccio) {
            int novaY = y;
            
            switch (direccio) {
            case Continental.AMUNT: novaY -= 2;
                            break;
            case Continental.ABAIX: novaY += 2;
            }
            
            return novaY;
    } 


    void buidarCasella(int x, int y) {
    	this.getTaulell().caselles()[x][y] = Continental.CASELLA_BUIDA;
    }

    void setFitxa(int x, int y) {
    	this.getTaulell().caselles()[x][y] = Continental.CASELLA_OCUPADA;
    }    

    
    /**
     * Retorna cert si hi ha una fitxa a la posició indicada.
     */
    boolean esCasellaOcupada(int x, int y) {
    	int value = this.getTaulell().caselles()[x][y];
        return  value == Continental.CASELLA_OCUPADA;
    }    
    boolean esCasellaBuida(int x, int y) {
        return this.getTaulell().caselles()[x][y] == Continental.CASELLA_BUIDA;
    }   

	public void imprimir(){
		int[][] caselles = this.getTaulell().caselles();
		
            for (int x = 0; x < caselles.length; x++) {
                for (int y = 0; y < caselles[x].length; y++) {
                	if(caselles[x][y]== Continental.CASELLA_NO_VALIDA )
                        System.out.print(" ");
                	else
                        System.out.print(caselles[x][y]);
                }
                System.out.println();
            }
    }	


	/**
	 * Comprovació del contingut casella per casella
	 * @param array1
	 * @param array2
	 * @return
	 */
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