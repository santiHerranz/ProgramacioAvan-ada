package Exercici2;

public class Exercici2 {


	public static void main(String[] args) {

		System.out.println("Exercici2 - Polvorons");
		
		
		System.out.println("\n\n**********\nCaixa manual\n**********");
		
        int[] caixaManual = {
        		Tipus.VAINILLA.intValue(),
//        		Tipus.COCO.intValue(),
//        		Tipus.ATMELLA.intValue(),
        		Tipus.VAINILLA.intValue(),
        		Tipus.XOCOLATA.intValue(),
        		Tipus.VAINILLA.intValue(),
        		Tipus.LLIMONA.intValue(),
        		Tipus.VAINILLA.intValue(),
        		Tipus.VAINILLA.intValue()
        		};

        
		Caixa novaCaixa = new Caixa(caixaManual);
		novaCaixa.explicar=true;
		
		System.out.println(novaCaixa.toString());
		
        int element = novaCaixa.esAvorrida(); // obtenir element majoritari amb la técnica divideix i venç
        if(element==-1)
            System.out.println("La caixa no és avorrida");
        else
            System.out.println("La caixa és avorrida, element majoritari: " + Tipus.fromInteger(element) + "");
		
        //System.exit(0);

        
		System.out.println("\n\n**********\nPalet de Caixes aleatories\n**********");

        // Omplir caixes
		int caixesPalet = (int)(10 * Math.random())+4;
        Caixa[] palet = new Caixa[caixesPalet];
        for(int i=0; i<palet.length; i++){
           int polvoronsCaixa = (int)(10 * Math.random())+3;
     	   int[] contingut = new int[polvoronsCaixa];
           for (int j = 0; j < contingut.length; j++) {
        	   contingut[j] = (int)(3 * Math.random())+1;
           }        	
        	palet[i] = new Caixa(contingut);
        }
        
        // Mirar caixes
        for(int i=0; i < palet.length; i++){
        	System.out.println();
    		System.out.println(palet[i].toString());
        	int element2 = palet[i].esAvorrida();
   		
            if(element2==-1)
                System.out.println("La caixa no és avorrida");
            else
                System.out.println("La caixa és avorrida, element majoritari: " + Tipus.fromInteger(element2) + "");
        }
        

		
        
		System.out.println("\n\n**********\nCaixa aleatoria molt gran\n**********");
        
		Caixa caixa = new Caixa(1000); // MAX 100000000
		
        long inici = System.currentTimeMillis();
        int element3 = caixa.esAvorrida(); // obtenir element majoritari amb la técnica divideix i venç
        long temps = System.currentTimeMillis() - inici;

        if(caixa.contingut.length<=100){
        	System.out.println(caixa.toString());
        }
        System.out.println(caixa.contingut.length);
        if(element3==-1)
            System.out.println("La caixa no és avorrida");
        else
            System.out.println("La caixa és avorrida, element majoritari: " + Tipus.fromInteger(element3) + "");
        System.out.println("" + temps + " ms");
        
        
	
	}



}
