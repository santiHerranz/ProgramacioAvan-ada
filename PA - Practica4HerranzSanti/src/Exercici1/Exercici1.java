package Exercici1;

import java.util.Scanner;

public class Exercici1 {
	/*
	 * Quins són els candidats del problema?
	 *  Totes les gasolineres del recorregut
	 * 
	 * Quina funció de selecció aplicarà el vostre algorisme?
	 *  El viatjant ha de repostar sempre que al diposit no li quedin més quilómetres per arribar a la següent gasolinera 
	 * 
	 * La vostra funció de selecció, garanteix trobar sempre la millor solució?
	 *  Si
	 * 
	 * Perquè?
	 *  L'enunciat garanteix que les distancies a les gasolineres son sempre més petites que la capacitat
	 *  del dipósit i la funció de selecció fa que no s'aturi si pot arribar a la següent gasolinera
	 * 
	 */
	
	public static void main(String[] args) {
		System.out.println("Exercici1 - Tècnica Voraç - viatjant");

		String ciutat_origen = "";
		String ciutat_desti = "";
		int desti = 0;
		int tamany_diposit = 0;
		int numero_gasolineres = 0;
		Gas gasolineres[] = null;
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Menú:");
		System.out.println("  1 - Aleatori");
		System.out.println("  2 - Predefinit");
		System.out.println("  3 - Manual");
		System.out.println("Tria una opció:");
		String opcio = reader.next();
		opcio = opcio.toLowerCase();


		if(opcio.equals("1")) {
			System.out.println("1 - Aleatori");

			ciutat_origen = "Barcelona";
			ciutat_desti = "Mataró";
			tamany_diposit = 100+((int)(Math.random()*100)+1);

			numero_gasolineres = 1+(int)(Math.random()*10);
			Gas gasolineresAuto[] = new Gas[numero_gasolineres+1];
			gasolineresAuto[0] = new Gas("Sortida",0);
			for(int i=1; i<=numero_gasolineres;i++)
				gasolineresAuto[i] = new Gas("Gas "+ Character.toString ((char) ('A'+i-1)),(int)(Math.random()*tamany_diposit)+1);

			gasolineres = gasolineresAuto;
			numero_gasolineres = gasolineres.length;
			
			for(Gas g: gasolineres)
				desti += g.km;
			
			desti += (int)(Math.random()*tamany_diposit)+1;
		}
		
		if(opcio.equals("2")) {
			System.out.println("2 - Predefinit");

			ciutat_origen = "Barcelona";
			ciutat_desti = "Mataró";
			tamany_diposit = 50;

			Gas gasolineresPredefinit[] = {
					new Gas("Sortida",0),
					new Gas("Gas A", 40),
					new Gas("Gas B", 20),
					new Gas("Gas C", 10),
					new Gas("Gas D", 20)
					};				
			gasolineres = gasolineresPredefinit;
			numero_gasolineres = gasolineres.length;
			
			for(Gas g: gasolineres)
				desti += g.km;
			
			desti += 10;
		}		

		if(opcio.equals("3")) {
			System.out.println("3 - Manual");
		}

		while(ciutat_origen == "") {
			System.out.println("Entra la ciutat origen:");
			ciutat_origen = reader.next();
		} ;

		while(ciutat_desti == "") {
			System.out.println("Entra la ciutat destí:");
			ciutat_desti = reader.next();
		} ;
		
		while(tamany_diposit == 0) {
			System.out.println("Entra el tamany del dipósit:");
			String value = reader.next();
			try{
				tamany_diposit = Integer.parseInt(value);
			} catch(NumberFormatException e) {
				System.out.println("Error: El tamany del dipósit ha de ser numèric");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		};

		while(numero_gasolineres == 0) {
			System.out.println("Entra la quantitat de gasolineres al recorregut:");
			String value = reader.next();
			try{
				numero_gasolineres = Integer.parseInt(value);
			} catch(NumberFormatException e) {
				System.out.println("Error: La quantitat de gasolineres ha de ser numèrica");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		} ;

		if(gasolineres == null) {
			gasolineres = new Gas[numero_gasolineres+1];
			gasolineres[0] = new Gas("Sortida",0);
			for(int i=1; i<numero_gasolineres+1;i++){
				do {
					System.out.println(String.format("Entra la distancia a la gasolinera %s:", i));
					String value = reader.next();
					try{
						int km = Integer.parseInt(value);
						if(km>tamany_diposit)
							throw new Exception(String.format("La distancia no pot ser superior a tamany diposit (%d)", tamany_diposit));
						if(km<1)
							throw new Exception("Error: La distancia no pot ser inferior a 1");
						gasolineres[i] = new Gas("Gas "+ Character.toString ((char) ('A'+i-1)), km);
					} catch(NumberFormatException e) {
						System.out.println("Error: La distancia ha de ser numèrica");
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
				} while(gasolineres[i] == null);
			}

			int acum = 0;
			for(int i=0; i<gasolineres.length; i++) {
				acum += gasolineres[i].km;
			}
			
			System.out.println("Entra la distancia al destí:");
			String value = reader.next();
			try{
				int km = Integer.parseInt(value);
				if(km>tamany_diposit)
					throw new Exception(String.format("La distancia no pot ser superior a tamany diposit (%d)", tamany_diposit));
				if(km<1)
					throw new Exception("Error: La distancia no pot ser inferior a 1");
				desti = acum + km;
			} catch(NumberFormatException e) {
				System.out.println("Error: La distancia ha de ser numèrica");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}

		}
		
		//Parades posibles
		Gas parades[] = new Gas[gasolineres.length];

		System.out.println("\n---Dades-------------");
		System.out.println("Ciutat origen: "+ ciutat_origen);
		System.out.println("Ciutat destí: "+ ciutat_desti);
		System.out.println("Distancia al destí: "+ desti +" km");
		
		int resultat;
		int distanciaDeposit = tamany_diposit;
		System.out.println("Tamany depósit: "+ distanciaDeposit +" km");

		int acum = 0;
		int delta = 0;
		for(int i=0; i<gasolineres.length; i++) {
			delta= gasolineres[i].km;
			acum += delta;
			System.out.println(i+" - "+ gasolineres[i].nom 
					+"\t acum:"+ acum +" km"
					+"\t a "+ gasolineres[i].km +" km"
					);
		}
		System.out.println(gasolineres.length +" - Desti"
				+"\t acum:"+ desti +" km"
				+"\ta "+ (desti-acum) +" km"
				);
		
		System.out.println("\n---Càlcul amb técnica voraç -------------");
		resultat=calcula_aturades(distanciaDeposit, gasolineres, parades, desti);
		
		System.out.println("\n---Resultat-------------");
		if (resultat!=0){
			System.out.println("Aturades: "+ (resultat-1) +" de "+ gasolineres.length);
			for(int i=1; i<resultat; i++)
				System.out.println(i+" - "+ parades[i].nom);
		} else 
			System.out.println("No existeix solucio");
	}
	
	
	private static int calcula_aturades(int kmDiposit, Gas Candidats[], Gas Solucio[], int desti){
			int resultat=0;
			int kmAcumulats = 0;
			// Els candidats venen en seqüència del recorregut
			int index = 0; // índex del candidat a considerar
			
			// Omplir dipósit
			int diposit = kmDiposit;
			
			System.out.println(index+" - "+ Candidats[index].nom 
					+"\tacum:"+ kmAcumulats +" km"
					+"\tdiposit: queden "+ diposit +" km"
					+"\tsegüent a "+ Candidats[index+1].km +" km" );			

			Solucio[resultat++] = Candidats[index++];	// afegir la gasolinera de sortida a la solució

			// Estructura voraç
			while (index < Candidats.length) { // mentre hi hagi candidats
				
				int distancia_actual = Candidats[index].km;
				int distancia_seguent = 0;

				// descomptem la distancia actual al dipósit
				diposit -= distancia_actual;

				// Acumulem la distància actual
				kmAcumulats += distancia_actual;

				if(index == Candidats.length-1) // Cas especial ultima gasolinera
					distancia_seguent = desti-kmAcumulats;
				else
					distancia_seguent = Candidats[index+1].km;
				
				System.out.print(index+" - "+ Candidats[index].nom 
						+"\tacum:"+ kmAcumulats +" km"
						+"\tdiposit: queden "+ diposit +" km"
						+"\tsegüent a "+ distancia_seguent +" km" );

				// funció de selecció: 
				//	 Si el dipósit no té prou gasolina per arribar a la següent gasolinera haig d'aturar i omplir el dipósit
				if (diposit < distancia_seguent ){
					System.out.print(" - ATURADA");
					// Omplir dipósit
					diposit = kmDiposit;
					// Afegir a la solució
					Solucio[resultat++] = Candidats[index];
				} else {
					System.out.print(" - sense aturada");
				}
				
				index++;
				System.out.println("");
			} 

			// Últim tram fins al destí
			int distancia_actual = desti-kmAcumulats;
			
			// descomptem la distancia actual al dipósit
			diposit -= distancia_actual;

			// Acumulem la distància actual
			kmAcumulats += distancia_actual;
			
			System.out.print(index+" - Destí" 
					+"\tacum:"+ kmAcumulats +" km"
					+"\tdiposit: queden "+ diposit +" km"
					);
			
			return resultat;
	} 


}


