package domini;

public class Joc {
	
	public String status = new String("Estat del joc"); 

	private Solucio solucio;
	
	
	public String CASELLA_BUIDA = " ";
	public String CASELLA_OCUPADA = "O";
	public String CASELLA_SELECCIONADA = "I";
	public String CASELLA_NOVALIDA = "X";

	Coord fitxa_seleccionada = null;
	
	public int mida = 7;
	private Taulell taulell;
	private Apuntador apuntador;
	
	
	public Joc(){
		inici();
	}

	public void reset(){
		inici();
	}	
	
	private void inici(){
		this.taulell = new Taulell(mida);
		this.solucio = new Solucio();
		this.fitxa_seleccionada = null;

		// Coloquem les fitxes
		try {
			
			for (int i = 0; i < this.mida; ++i){
				for (int j = 0; j < this.mida; ++j){
					this.taulell.setContingut(i,j,this.CASELLA_NOVALIDA);
				}
			}
			
			for (int j = 0; j < this.mida; ++j){
				this.taulell.setContingut(2,j,this.CASELLA_OCUPADA);
				this.taulell.setContingut(3,j,this.CASELLA_OCUPADA);
				this.taulell.setContingut(4,j,this.CASELLA_OCUPADA);
			}
			for (int i = 0; i < this.mida; ++i){
				this.taulell.setContingut(i,2,this.CASELLA_OCUPADA);
				this.taulell.setContingut(i,3,this.CASELLA_OCUPADA);
				this.taulell.setContingut(i,4,this.CASELLA_OCUPADA);
			}
			this.taulell.setContingut(3,3,this.CASELLA_BUIDA);

		} catch(Exception e) {
			e.printStackTrace();
		}

		this.apuntador = new Apuntador();		
	}
	
	/*
	 * Métode per obtenir la representació del taulell
	 */
	public String[][] estatTaulell() throws Exception {
		return this.taulell.estatTaulell();
	}	
	
	public void procesar( int x, int y) throws Exception {
		
		String sel = new String("");
		String c0 = this.taulell.getContingut(x, y); 

		if(c0.equals(CASELLA_NOVALIDA)) throw new Exception("Casella no vàlida");
		
		if(fitxa_seleccionada == null) {
			if( c0.equals(CASELLA_OCUPADA)) { // AGAFAR FITXA
				this.taulell.setContingut(x, y, CASELLA_SELECCIONADA);
				fitxa_seleccionada = new Coord(x,y);
				
				this.apuntador.guardar(x,y);
				System.out.println(String.format("%d. (%s,%s) AGAFAR FITXA", this.apuntador.moviments , x,y));
			}
		} else {
			
			
			if(fitxa_seleccionada.x == x & fitxa_seleccionada.y == y) { // DEIXAR FITXA
				this.taulell.setContingut(x, y, CASELLA_OCUPADA);
				fitxa_seleccionada = null;

			} else if((fitxa_seleccionada.x != x | fitxa_seleccionada.y != y) 
					& c0.equals(CASELLA_OCUPADA)) { // DEIXAR FITXA I AGAFAR NOVA FITXA 
				
				this.taulell.setContingut(fitxa_seleccionada.x, fitxa_seleccionada.y, CASELLA_OCUPADA);
				this.taulell.setContingut(x, y, CASELLA_SELECCIONADA);
				fitxa_seleccionada = new Coord(x,y);
				
				this.apuntador.guardar(x,y);
				System.out.println(String.format("%d. (%s,%s) DEIXAR I AGAFAR NOVA FITXA", this.apuntador.moviments , x,y));

			} else if(moviment_posible(x,y)) // MENJAR FITXA

				if(fitxa_menjable(x,y)){
					
					int xIni = fitxa_seleccionada.x;
					int yIni = fitxa_seleccionada.y;
					int xFin = x;
					int yFin = y;
					
					String menjada = "";
					Coord c = menjar_fitxa(x, y);
					if(c!= null) menjada = String.format("X(%s,%s)X", c.x, c.y);
					
					this.taulell.setContingut(fitxa_seleccionada.x, fitxa_seleccionada.y, CASELLA_BUIDA);
					this.taulell.setContingut(x, y, CASELLA_SELECCIONADA);
					fitxa_seleccionada = new Coord(x,y);
					
					this.apuntador.guardar(x,y);
					System.out.println(String.format("%d. (%s,%s) MENJAR FITXA ->(%s,%s) %s", this.apuntador.moviments , xIni, yIni, xFin, yFin, menjada));
				}
		}

		if(fitxa_seleccionada!= null)
			sel = String.format("[x:%s y:%s]", fitxa_seleccionada.x, fitxa_seleccionada.y);

		String c1 = this.taulell.getContingut(x, y); 
		status = String.format("(x:%s y:%s)=%s->%s", x, y, c0, c1);
		if(sel!= null) status += " "+ sel;
	}
	
	private boolean moviment_posible(int x, int y) {
		
		if(this.apuntador.moviments==0) return true; //El primer moviment sempre és vàlid
		
		Coord actual;
		try {
			if(this.taulell.getContingut(x, y).equals(CASELLA_OCUPADA)) return false;

			actual = this.apuntador.ultimMoviment();
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
		
		if(this.apuntador.moviments==0) return true; //El primer moviment sempre és vàlid

		Coord actual;
		try {
			actual = this.apuntador.ultimMoviment();
			
			int dirX = x-actual.x;
			int dirY = y-actual.y;

			if(dirX<0) dirX = -1;
			if(dirX>1) dirX = 1;
			if(dirY<0) dirY = -1;
			if(dirY>1) dirY = 1;
			
			if(this.taulell.getContingut(actual.x+dirX, actual.y+dirY).equals(CASELLA_OCUPADA)) return true;
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
			actual = this.apuntador.ultimMoviment();
			
			int dirX = x-actual.x;
			int dirY = y-actual.y;

			if(dirX<0) dirX = -1;
			if(dirX>1) dirX = 1;
			if(dirY<0) dirY = -1;
			if(dirY>1) dirY = 1;
			
			if(this.taulell.getContingut(actual.x+dirX, actual.y+dirY).equals(CASELLA_OCUPADA)) { 
				this.taulell.setContingut(actual.x+dirX,actual.y+dirY,this.CASELLA_BUIDA);
				return new Coord(actual.x+dirX,actual.y+dirY);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}	


	
	public void solucio(){
		
		Coord cc[] = new Coord[this.mida*this.mida];
		int count=0;
		for (int i = 0; i < this.mida; i++){
			for (int j = 0; j < this.mida; j++){
				try {
					cc[count++] = new Coord(i,j);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		for (int i = 0; i < cc.length; i++){
				try {
					procesar(cc[i].x,cc[i].y);

					for (int ii = 0; ii < this.mida; ii++){
						for (int jj = 0; jj < this.mida; jj++){
							try {
								if(moviment_posible(ii,jj))
									if(fitxa_menjable(ii,jj)){
										
										procesar(ii,jj);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
								this.status = e.getMessage();
							}
						}
					}		

				
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					this.status = e.getMessage();
				}
		}		
		
		//this.status = "No hi ha solució";
	}
	
}