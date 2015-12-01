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
    

	public int mida = 7;
	public String status = new String("Estat del joc"); 

	private Taulell taulell;
	Coord fitxa_seleccionada = null;
	Historial historial;
	Solucio solucio;

    /**
     * all four possible directions for a move (jump of a peg over another peg)
     */
    int [] directions = {Joc.DRETA, Joc.AMUNT, Joc.ESQUERRA, Joc.ABAIX};	

	
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

		this.setTaulell(new Taulell(mida));
		this.historial = new Historial(this);
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
				fitxa_seleccionada = new Coord(x,y);
				
				this.historial.guardarMoviment(x,y);
				System.out.println(String.format("%d. (%s,%s) AGAFAR FITXA", this.historial.moviments , x,y));
			}
		} else {
			
			
			if(fitxa_seleccionada.x == x & fitxa_seleccionada.y == y) { // DEIXAR FITXA
				this.getTaulell().setContingut(x, y, Joc.CASELLA_OCUPADA);
				fitxa_seleccionada = null;
				this.historial.desferUltimMoviment();

				System.out.println(String.format("%d. (%s,%s) DEIXAR FITXA", this.historial.moviments , x,y));

			} else if((fitxa_seleccionada.x != x | fitxa_seleccionada.y != y) 
					& c0 == Joc.CASELLA_OCUPADA) { // DEIXAR FITXA I AGAFAR NOVA FITXA 
				
				this.getTaulell().setContingut(fitxa_seleccionada.x, fitxa_seleccionada.y, Joc.CASELLA_OCUPADA);
				fitxa_seleccionada = null;
				this.historial.desferUltimMoviment();

				this.getTaulell().setContingut(x, y, Joc.CASELLA_SELECCIONADA);
				fitxa_seleccionada = new Coord(x,y);
				
				this.historial.guardarMoviment(x,y);
				System.out.println(String.format("%d. (%s,%s) DEIXAR I AGAFAR NOVA FITXA", this.historial.moviments , x,y));

			} else if(esPosibleMoure(x,y)) // MENJAR FITXA

				if(fitxa_menjable(x,y)){
					
					int xIni = fitxa_seleccionada.x;
					int yIni = fitxa_seleccionada.y;
					int xFin = x;
					int yFin = y;
					
					String menjada = "";
					Coord c = menjar_fitxa(x, y);
					if(c!= null) menjada = String.format("X(%s,%s)X", c.x, c.y);
					
					this.getTaulell().setContingut(fitxa_seleccionada.x, fitxa_seleccionada.y, Joc.CASELLA_BUIDA);
					this.getTaulell().setContingut(x, y, Joc.CASELLA_SELECCIONADA);
					fitxa_seleccionada = new Coord(x,y);
					
					this.historial.guardarMoviment(x,y);
					
					System.out.println(String.format("%d. (%s,%s) MENJAR FITXA ->(%s,%s) %s", this.historial.moviments , xIni, yIni, xFin, yFin, menjada));

					this.imprimir();
				}
		}

		if(fitxa_seleccionada!= null)
			sel = String.format("[x:%s y:%s]", fitxa_seleccionada.x, fitxa_seleccionada.y);

		int c1 = this.getTaulell().getContingut(x, y); 
		status = String.format("Moviment %d. (x:%s y:%s)=%s->%s", this.historial.moviments-1, x, y, c0, c1);
		if(sel!= null) status += " "+ sel;
	}

	private boolean esPosibleMoure(int x, int y) {
		
		if(this.historial.moviments==0) return true; //El primer moviment sempre és vàlid
		
		Coord actual;
		try {
			if(this.getTaulell().getContingut(x, y) == Joc.CASELLA_OCUPADA) return false;

			actual = this.historial.ultimMoviment();
			if(actual.y-2 == y && actual.x == x) return true;
			if(actual.y == y && actual.x-2 == x) return true;
			if(actual.y == y && actual.x+2 == x) return true;
			if(actual.y+2 == y && actual.x == x) return true;
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	private boolean fitxa_menjable(int x, int y) {
		
		if(this.historial.moviments==0) return true; //El primer moviment sempre és vàlid

		Coord actual;
		try {
			actual = this.historial.ultimMoviment();
			
			int dirX = x-actual.x;
			int dirY = y-actual.y;

			if(dirX<0) dirX = -1;
			if(dirX>1) dirX = 1;
			if(dirY<0) dirY = -1;
			if(dirY>1) dirY = 1;
			
			if(this.getTaulell().getContingut(actual.x+dirX, actual.y+dirY) == Joc.CASELLA_OCUPADA) return true;
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	private Coord menjar_fitxa(int x, int y) {
		
		Coord actual;
		try {
			actual = this.historial.ultimMoviment();
			
			int dirX = x-actual.x;
			int dirY = y-actual.y;

			if(dirX<0) dirX = -1;
			if(dirX>1) dirX = 1;
			if(dirY<0) dirY = -1;
			if(dirY>1) dirY = 1;
			
			if(this.getTaulell().getContingut(actual.x+dirX, actual.y+dirY) == Joc.CASELLA_OCUPADA) { 
				this.getTaulell().setContingut(actual.x+dirX,actual.y+dirY,Joc.CASELLA_BUIDA);
				return new Coord(actual.x+dirX,actual.y+dirY);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	


	public Coord desferUltimMoviment() throws Exception{
		return this.historial.desferUltimMoviment();
	}
	
	
	public void solucio() throws Exception{
		
        long t1 = System.currentTimeMillis();
        if (this.solucio.trobarSolucio(1)) {
        	long t2 = System.currentTimeMillis();
            System.out.println("Solució trobada en in " + (t2 - t1) + " milisegons");
                
            this.solucio.imprimir();
            
        } else {
                System.out.println("No hi ha solució!!");
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