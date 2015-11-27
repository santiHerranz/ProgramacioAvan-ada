package Exercici2;

import java.util.Scanner;

import Exercici1.Gas;

public class Exercici2 {


	public static void main(String[] args) {

		System.out.println("Exercici2 - Tècnica del divideix i venç - Polvorons");
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Menú:");
		System.out.println("  1 - Aleatori");
		System.out.println("  2 - Predefinit");
		System.out.println("  3 - Manual");
		System.out.println("  4 - Megacaixa");
		System.out.println("Tria una opció:");
		String opcio = reader.next();
		opcio = opcio.toLowerCase();
		

		if(opcio.equals("1")) {
			System.out.println("1 - Aleatori");

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
	        
			
		}
        

		if(opcio.equals("2")) {
			System.out.println("2 - Predefinit");
			
	        int[] caixaPredefinit = {
	        		Tipus.VAINILLA.intValue(),
//	        		Tipus.COCO.intValue(),
//	        		Tipus.ATMELLA.intValue(),
	        		Tipus.VAINILLA.intValue(),
	        		Tipus.XOCOLATA.intValue(),
	        		Tipus.VAINILLA.intValue(),
	        		Tipus.LLIMONA.intValue(),
	        		Tipus.VAINILLA.intValue(),
	        		Tipus.VAINILLA.intValue()
	        		};

	        
			Caixa novaCaixa = new Caixa(caixaPredefinit);
			novaCaixa.explicar=true;
			
			System.out.println(novaCaixa.toString());
			
	        int element = novaCaixa.esAvorrida(); // obtenir element majoritari amb la técnica divideix i venç
	        if(element==-1)
	            System.out.println("La caixa no és avorrida");
	        else
	            System.out.println("La caixa és avorrida, element majoritari: " + Tipus.fromInteger(element) + "");
			
	        //System.exit(0);			
		}		

		if(opcio.equals("3")) {		
			System.out.println("3 - Manual");

			int numero_polvorons = 0;
			while(numero_polvorons == 0) {
				System.out.println("Entra la quantitat de polvorons de la caixa:");
				String value = reader.next();
				try{
					numero_polvorons = Integer.parseInt(value);
					if(numero_polvorons<1)
						throw new Exception("Error: La quantitat de polvorons no pot ser inferior a 1");
					
				} catch(NumberFormatException e) {
					System.out.println("Error: La quantitat de polvorons ha de ser numèrica");
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			} ;
			
			int[] caixaManual = new int[numero_polvorons];
			for(int i=0; i<numero_polvorons;i++){
				do {
					System.out.println(String.format("Entra el tipus de polvoro %s:", i+1));
					System.out.println(String.format("%d - %s:", Tipus.VAINILLA.intValue(), Tipus.VAINILLA));
					System.out.println(String.format("%d - %s:", Tipus.XOCOLATA.intValue(), Tipus.XOCOLATA));
					System.out.println(String.format("%d - %s:", Tipus.COCO.intValue(), Tipus.COCO));
					System.out.println(String.format("%d - %s:", Tipus.ATMELLA.intValue(), Tipus.ATMELLA));
					String value = reader.next();
					try{
						int tipus = Integer.parseInt(value);
						if(tipus != Tipus.VAINILLA.intValue() && tipus != Tipus.XOCOLATA.intValue() &&tipus != Tipus.COCO.intValue() &&tipus != Tipus.ATMELLA.intValue())
							throw new Exception("Error: Tipus de polvoró no reconegut");
						caixaManual[i] = tipus;
					} catch(NumberFormatException e) {
						System.out.println("Error: El tipus de polvoró ha de ser numèric");
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
				} while(caixaManual[i] == 0);
			}			
			
			
			Caixa novaCaixa = new Caixa(caixaManual);
			
			System.out.println(novaCaixa.toString());
			
	        int element = novaCaixa.esAvorrida(); // obtenir element majoritari amb la técnica divideix i venç
	        if(element==-1)
	            System.out.println("La caixa no és avorrida");
	        else
	            System.out.println("La caixa és avorrida, element majoritari: " + Tipus.fromInteger(element) + "");
			
		}		

		if(opcio.equals("4")) {
			System.out.println("4 - Megacaixa");

			int numero_polvorons = 0;
			while(numero_polvorons == 0) {
				System.out.println("Entra la quantitat de polvorons de la caixa (prova 1 millió o més):");
				String value = reader.next();
				try{
					numero_polvorons = Integer.parseInt(value);
					if(numero_polvorons<1)
						throw new Exception("Error: La quantitat de polvorons no pot ser inferior a 1");
					
				} catch(NumberFormatException e) {
					System.out.println("Error: La quantitat de polvorons ha de ser numèrica");
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			} ;			
			
			System.out.println("Calculant... esperi, si us plau...");
			Caixa caixa = new Caixa(numero_polvorons); // MAX 100000000
			
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



}
