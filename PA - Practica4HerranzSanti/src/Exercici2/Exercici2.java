package Exercici2;

public class Exercici2 {


	public static void main(String[] args) {

		System.out.println("Exercici2 - Polvorons");
		
        int[] caixa = new int[1000000];
        for (int i = 0; i < caixa.length; i++) {
            caixa[i] = (int)(2 * Math.random())+1;
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
		
		//caixa = caixaManual;
		
        long inici = System.currentTimeMillis();
        int element = esAvorrida(caixa); // obtenir element majoritari amb la técnica divideix i venç
        long temps = System.currentTimeMillis() - inici;

        //System.out.println(java.util.Arrays.toString(caixa));
        System.out.println(caixa.length);
        if(element==-1)
            System.out.println("La caixa no és avorrida");
        else
            System.out.println("La caixa és avorrida, element majoritari: " + Tipus.fromInteger(element) + "");
        System.out.println("" + temps + " ms");
    }

    /**
     * Get majority with Divide and Conquer.
     */
    public static int esAvorrida(int[] caixa) {
        return esAvorrida(caixa, 0, caixa.length - 1);
    }

    private static int esAvorrida(int[] subcaixa, int esquerra, int dreta) {
    	
        //System.out.println(esquerra +" - "+ dreta);
    	
        int tamany = dreta - esquerra + 1;

        if (tamany == 1) return subcaixa[esquerra]; // cas d'ús bàsic

        int mid = (esquerra + dreta) / 2;
        int partEsquerra = esAvorrida(subcaixa, esquerra, mid);
        int partDreta = esAvorrida(subcaixa, mid + 1, dreta);
        
        if (partEsquerra == partDreta) return partEsquerra; // optimering

        int quantesEsquerra = quantes(subcaixa, esquerra, dreta, partEsquerra);
        int quantesDreta = quantes(subcaixa, esquerra, dreta, partDreta);

        if (quantesEsquerra > tamany / 2) return partEsquerra;
        if (quantesDreta > tamany / 2) return partDreta;

        return -1;
    }

    private static int quantes(int[] a, int left, int right, int comp) {
        int vegades = 0;
        for (int i = left; i <= right; i++) 
        	if (a[i] == comp) 
        		vegades++;
        return vegades;
    }	

}
