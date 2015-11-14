package Exercici2;


public class Caixa {

	int[] contingut;
	
	Caixa(int n){
	   if (n <= 0)
		     throw new IllegalArgumentException("n ha de ser positiu");

	   contingut = new int[n];
       for (int i = 0; i < contingut.length; i++) {
    	   contingut[i] = (int)(2 * Math.random())+1;
       }
       
       int[] caixaManual = {
       		Tipus.CANELA.intValue(),
       		Tipus.COCO.intValue(),
       		Tipus.ATMELLA.intValue(),
       		Tipus.ATMELLA.intValue(),
       		Tipus.COCO.intValue(),
       		Tipus.ATMELLA.intValue(),
       		Tipus.COCO.intValue(),
       		Tipus.ATMELLA.intValue(),
       		Tipus.ATMELLA.intValue(),
       		Tipus.ATMELLA.intValue()
       		};
       
	   if(n==0)
	       contingut = caixaManual;
	}
	
	Caixa(int[] values){
	 this.contingut = values;
	}
	
	
    /**
     * Get majority with Divide and Conquer.
     */
    public int esAvorrida() {
        return esAvorrida(contingut, 0, contingut.length - 1);
    }

    private int esAvorrida(int[] caixa, int esquerra, int dreta) {
    	
        //System.out.println(esquerra +" - "+ dreta);
    	
        int tamany = dreta - esquerra + 1;

        if (tamany == 1) return caixa[esquerra]; // cas d'ús bàsic

        int mid = (esquerra + dreta) / 2;
        int partEsquerra = esAvorrida(caixa, esquerra, mid);
        int partDreta = esAvorrida(caixa, mid + 1, dreta);
        
        if (partEsquerra == partDreta) return partEsquerra; // optimering

        int quantesEsquerra = quantes(caixa, esquerra, dreta, partEsquerra);
        int quantesDreta = quantes(caixa, esquerra, dreta, partDreta);

        if (quantesEsquerra > tamany / 2) return partEsquerra;
        if (quantesDreta > tamany / 2) return partDreta;

        return -1;
    }

    private int quantes(int[] a, int left, int right, int comp) {
        int vegades = 0;
        for (int i = left; i <= right; i++) 
        	if (a[i] == comp) 
        		vegades++;
        return vegades;
    }	
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("("+ contingut.length + "): ");
		for(int p: contingut)
			sb.append(Tipus.fromInteger(p) +",");
		
		return sb.toString();
	}
	
}
