package Exercici2;

public class Exercici2 {


	public static void main(String[] args) {

		System.out.println("Exercici2 - Polvorons");
		
	
        Caixa[] palet = new Caixa[(int)(20 * Math.random())+1];
        
        for(int i=0; i<palet.length; i++){

           int n = (int)(10 * Math.random())+1;
     	   int[] contingut = new int[n];
           for (int j = 0; j < contingut.length; j++) {
        	   contingut[j] = (int)(6 * Math.random())+1;
           }        	
        	palet[i] = new Caixa(contingut);
        }

        for(int i=0; i < palet.length; i++){
        	System.out.println();
        	int element2 = palet[i].esAvorrida();
    		System.out.println(palet[i].toString());
            if(element2==-1)
                System.out.println("La caixa no és avorrida");
            else
                System.out.println("La caixa és avorrida, element majoritari: " + Tipus.fromInteger(element2) + "");
            
        }
        

		System.out.println("\n\n**********\nCaixa molt gran");
        
		Caixa caixa = new Caixa(1000);
		
        long inici = System.currentTimeMillis();
        int element = caixa.esAvorrida(); // obtenir element majoritari amb la técnica divideix i venç
        long temps = System.currentTimeMillis() - inici;

        //System.out.println(java.util.Arrays.toString(caixa));
        System.out.println(caixa.contingut.length);
        if(element==-1)
            System.out.println("La caixa no és avorrida");
        else
            System.out.println("La caixa és avorrida, element majoritari: " + Tipus.fromInteger(element) + "");
        System.out.println("" + temps + " ms");
        
	
	}



}
