package domini;

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
     * all four possible directions for a move (jump of a peg over another peg)
     */
    public static final int [] directions = {Joc.DRETA, Joc.AMUNT, Joc.ESQUERRA, Joc.ABAIX};	

	public int mida = 7;
	public String status = new String("Estat del joc"); 

	private Taulell taulell;
	int[] fitxa_seleccionada = null;
	Historial historial;
	Solucio solucio;


	
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

	
	
	
	public Joc(){
		inici();
	}

	public void reset(){
		inici();
	}	
	
	private void inici(){

		this.taulell = new Taulell(mida);
		this.historial = new Historial();
		this.solucio = new Solucio(this);

		this.fitxa_seleccionada = null;

		// Coloquem les fitxes
		try {
			
			for (int i = 0; i < this.mida; ++i){
				for (int j = 0; j < this.mida; ++j){
					this.getTaulell().setContingut(i,j,Joc.CASELLA_NO_VALIDA);
				}
			}
			
			for (int j = 0; j < this.mida; ++j){
				this.getTaulell().setContingut(2,j,Joc.CASELLA_OCUPADA);
				this.getTaulell().setContingut(3,j,Joc.CASELLA_OCUPADA);
				this.getTaulell().setContingut(4,j,Joc.CASELLA_OCUPADA);
			}
			for (int i = 0; i < this.mida; ++i){
				this.getTaulell().setContingut(i,2,Joc.CASELLA_OCUPADA);
				this.getTaulell().setContingut(i,3,Joc.CASELLA_OCUPADA);
				this.getTaulell().setContingut(i,4,Joc.CASELLA_OCUPADA);
			}
			this.getTaulell().setContingut(3,3,Joc.CASELLA_BUIDA);

		} catch(Exception e) {
			e.printStackTrace();
		}


		try {
			this.imprimir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	

	public void moureFitxa( int x, int y) throws Exception {
		
		String sel = new String("");
		int c0 = this.getTaulell().getContingut(x, y); 

		if(c0 == Joc.CASELLA_NO_VALIDA) throw new Exception("Casella no vàlida");
		
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

			} else if(esPosibleMoure(x,y)) // MENJAR FITXA

				if(fitxa_menjable(x,y)){
					
					int xIni = fitxa_seleccionada[0];
					int yIni = fitxa_seleccionada[1];
					int xFin = x;
					int yFin = y;
					
					String menjada = "";
					int[] c = menjar_fitxa(x, y);
					if(c!= null) menjada = String.format("X(%s,%s)X", c[0], c[1]);
					
					this.getTaulell().setContingut(fitxa_seleccionada[0], fitxa_seleccionada[1], Joc.CASELLA_BUIDA);
					this.getTaulell().setContingut(x, y, Joc.CASELLA_SELECCIONADA);
					fitxa_seleccionada = new int[] {x,y};
					
					this.historial.guardar(x,y);
					
					System.out.println(String.format("%d. (%s,%s) MENJAR FITXA ->(%s,%s) %s", this.historial.getMoviments() , xIni, yIni, xFin, yFin, menjada));

					this.imprimir();
				}
		}

		if(fitxa_seleccionada!= null)
			sel = String.format("[x:%s y:%s]", fitxa_seleccionada[0], fitxa_seleccionada[1]);

		int c1 = this.getTaulell().getContingut(x, y); 
		status = String.format("Moviment %d. (x:%s y:%s)=%s->%s", this.historial.getMoviments()-1, x, y, c0, c1);
		if(sel!= null) status += " "+ sel;
	}

	public Historial getHistorial() {
		return historial;
	}
	private boolean esPosibleMoure(int x, int y) {
		
		if(this.historial.getMoviments()==0) return true; //El primer moviment sempre és vàlid
		
		int[] actual;
		try {
			if(this.getTaulell().getContingut(x, y) == Joc.CASELLA_OCUPADA) return false;

			actual = this.historial.obtenirUltimMoviment();
			int actualX = actual[0];
			int actualY = actual[1];
			
			
			if(actualY-2 == y && actualX == x) return true;
			if(actualY == y && actualX-2 == x) return true;
			if(actualY == y && actualX+2 == x) return true;
			if(actualY+2 == y && actualX == x) return true;
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	private boolean fitxa_menjable(int x, int y) {
		
		if(this.historial.getMoviments()==0) return true; //El primer moviment sempre és vàlid

		int[] actual;
		try {
			actual = this.historial.obtenirUltimMoviment();
			int actualX = actual[0];
			int actualY = actual[1];
			
			int dirX = x-actualX;
			int dirY = y-actualY;

			if(dirX<0) dirX = -1;
			if(dirX>1) dirX = 1;
			if(dirY<0) dirY = -1;
			if(dirY>1) dirY = 1;
			
			if(this.getTaulell().getContingut(actualX+dirX, actualY+dirY) == Joc.CASELLA_OCUPADA) return true;
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	private int[] menjar_fitxa(int x, int y) {
		
		int[] actual;
		try {
			actual = this.historial.obtenirUltimMoviment();
			int actualX = actual[0];
			int actualY = actual[1];
			
			int dirX = x-actualX;
			int dirY = y-actualY;

			if(dirX<0) dirX = -1;
			if(dirX>1) dirX = 1;
			if(dirY<0) dirY = -1;
			if(dirY>1) dirY = 1;
			
			if(this.getTaulell().getContingut(actualX+dirX, actualY+dirY) == Joc.CASELLA_OCUPADA) { 
				this.getTaulell().setContingut(actualX+dirX,actualY+dirY,Joc.CASELLA_BUIDA);
				return new int[] {actualX+dirX,actualY+dirY};
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	


	public int[] desferUltimMoviment() throws Exception{
		return this.historial.desferUltimMoviment();
	}
	
	
	public String solucio() throws Exception{
		
        long t1 = System.currentTimeMillis();
        if (this.solucio.trobarSolucio(1)) {
        	long t2 = System.currentTimeMillis();
                
            this.solucio.imprimir();
            return "Solució trobada en " + (t2 - t1) + " milisegons ["+ this.solucio.getIteracions() +" iteracions]" ;
            
        } else {
                return "No hi ha solució!!";
        }		
		
	}

	
	

	public void imprimir() throws Exception{
		int[][] estatActual = this.getTaulell().caselles();
		
            for (int x = 0; x < estatActual.length; x++) {
                    for (int y = 0; y < estatActual[x].length; y++) {
                            System.out.print(estatActual[x][y]);
                    }
                    System.out.println();
            }
            System.out.println();
    }	
	
	

	


}