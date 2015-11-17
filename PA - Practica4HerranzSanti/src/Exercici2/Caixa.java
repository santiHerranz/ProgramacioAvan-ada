package Exercici2;

public class Caixa {
	
	public boolean explicar = false;

	int[] contingut;
	
	Caixa(int n){
		
	   if (n <= 0)
		     throw new IllegalArgumentException("n ha de ser positiu");

	   contingut = new int[n];
       for (int i = 0; i < contingut.length; i++) {
    	   contingut[i] = (int)(2 * Math.random())+1;
       }
	}
	
	Caixa(int[] values){
	 this.contingut = values;
	}
	
	
    /**
     * Obtenir element majoritari amb mètode Divideix i Venç.
     */
    public int esAvorrida() {
        return esAvorrida(contingut, 0, contingut.length - 1, 0);
    }

    /*
     * -----------------------------------------
     * | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
     * |  --  Esquerra --  |    --  Dreta --   |
     * -----------------------------------------
     */
    private int esAvorrida(int[] caixa, int posicioEsquerra, int posicioDreta, int nivell) {
    	
        int tamany = posicioDreta - posicioEsquerra + 1;
        if (tamany == 1) return caixa[posicioEsquerra]; // cas d'ús bàsic


    	int mid = (posicioEsquerra + posicioDreta) / 2;  // calcular la meitat

    	if (explicar) System.out.println("Nivell " + ++nivell +" ["+ posicioEsquerra +"-"+ mid +"|"+ (mid+1) +"-"+ posicioDreta +"]" );

        int partEsquerra = esAvorrida(caixa, posicioEsquerra, mid, nivell); // obtenir recursivament element majoritari de la part esquerra 
    	if (explicar) System.out.println("Nivell " + nivell +" Element majoritari part esquerra ["+ posicioEsquerra +"-"+ mid +"]="+ Tipus.fromInteger(partEsquerra) );
    	
        int partDreta = esAvorrida(caixa, mid + 1, posicioDreta, nivell); // obtenir recursivament element majoritari de la part dreta
        if (explicar) System.out.println("Nivell " + nivell +" Element majoritari part dreta ["+ (mid + 1) +"-"+ posicioDreta +"]="+ Tipus.fromInteger(partDreta));

        
        if (partEsquerra == partDreta){        	
        	if (explicar) System.out.println("Nivell " + nivell +" Optimitzem si part esquerra == dreta: " + Tipus.fromInteger(partEsquerra));
        	return partEsquerra; // optimitzem
        }

        int quantesEsquerra = quantes(caixa, posicioEsquerra, posicioDreta, partEsquerra);
        int quantesDreta = quantes(caixa, posicioEsquerra, posicioDreta, partDreta);
        
        if (quantesEsquerra > tamany / 2){
        	if (explicar) System.out.println("Nivell " + nivell +" Guanya la part Esquerra=" + Tipus.fromInteger(partEsquerra));
        	return partEsquerra;
        }
        if (quantesDreta > tamany / 2) {
        	if (explicar) System.out.println("Nivell " + nivell +" Guanya la part Dreta=" + Tipus.fromInteger(partDreta));
        	return partDreta;
        }

        if (explicar) System.out.println("Nivell " + nivell +" No hi ha element majoritari");
        return -1;
    }

    private int quantes(int[] a, int left, int right, int comp) {
    	if (comp==-1) return 0;
        int vegades = 0;
        for (int i = left; i <= right; i++) 
        	if (a[i] == comp) 
        		vegades++;
        if (explicar) System.out.println("  quantes vegades hi ha " + Tipus.fromInteger(comp) +" en ["+ left+"-"+ right +"]? "+ vegades +" vegades");
        return vegades;
    }	

	
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Caixa de "+ contingut.length + " polvorons:\n");
		for(int p: contingut)
			sb.append(Tipus.fromInteger(p) +",");
		
		return sb.toString();
	}
	
}
