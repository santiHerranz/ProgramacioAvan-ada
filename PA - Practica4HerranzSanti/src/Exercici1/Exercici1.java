package Exercici1;

import java.util.Scanner;

public class Exercici1 {

	
	/*
	 * Entrada:
	 * Ciutat origen
	 * Ciutat dest�
	 * Quil�metres de dist�ncia que pot rec�rrer amb el dip�sit ple
	 * Nombre de gasolineres
	 * Distancia entre gasolineres
	 *  
	 * Quins s�n els candidats del problema?
	 *  Les gasolineres per reposar combustible
	 * 
	 * Quina funci� de selecci� aplicar� el vostre algorisme?
	 *  El viatjant ha de repostar sempre que la propera gasolinera estiqui m�s lluny que els quil�metres
	 *  que li queden de bezina al dip�sit
	 * 
	 * La vostra funci� de selecci�, garanteix trobar sempre la millor soluci�?
	 *  No sempre, perque pot haver dues  
	 * 
	 * Perqu�?
	 * 
	 * An�lisi descendent
	 * 
	 */
	
	public static void main(String[] args) {
		System.out.println("Exercici1 - T�cnica Vora� - viatjant");

		String ciutat_origen = "";
		String ciutat_desti = "";
		int desti = 0;
		int TAMANY_DIPOSIT = 0;
		int numero_gasolineres = 0;
		Gas gasolineres[] = null;
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Entrada AUTOM�TICA(A) o qualsevol altra tecla per MANUAL:");
		String opcio = reader.next();
		if(opcio.equals("A") || opcio.equals("a")) {
			ciutat_origen = "Barcelona";
			ciutat_desti = "Matar�";
			TAMANY_DIPOSIT = 150;
			
			Gas[] gasolineresAuto = {
					new Gas("Sortida",0),
					new Gas("1",(int)(Math.random()*TAMANY_DIPOSIT)+1),
					new Gas("2",(int)(Math.random()*TAMANY_DIPOSIT)+1),
					new Gas("3",(int)(Math.random()*TAMANY_DIPOSIT)+1),
					new Gas("4",(int)(Math.random()*TAMANY_DIPOSIT)+1),
					new Gas("5",(int)(Math.random()*TAMANY_DIPOSIT)+1),
					new Gas("6",(int)(Math.random()*TAMANY_DIPOSIT)+1),
					new Gas("7",(int)(Math.random()*TAMANY_DIPOSIT)+1)
					};	
			gasolineres = gasolineresAuto;
			numero_gasolineres = gasolineres.length;
			
			for(Gas g: gasolineres)
				desti += g.km;
		}
		

		while(ciutat_origen == "") {
			System.out.println("Entra la ciutat origen:");
			ciutat_origen = reader.next();
		} ;

		while(ciutat_desti == "") {
			System.out.println("Entra la ciutat dest�:");
			ciutat_desti = reader.next();
		} ;
		
		while(TAMANY_DIPOSIT == 0) {
			System.out.println("Entra el tamany del dip�sit:");
			String value = reader.next();
			try{
				TAMANY_DIPOSIT = Integer.parseInt(value);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		};
		

		while(numero_gasolineres == 0) {
			System.out.println("Entra el n�mero de gasolineres:");
			String value = reader.next();
			try{
				numero_gasolineres = Integer.parseInt(value);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		} ;

		if(gasolineres == null) {
			gasolineres = new Gas[numero_gasolineres];
			for(int i=0; i<numero_gasolineres;i++){
				do {
					System.out.println(String.format("Entra la distancia a la gasolinera %s:", i));
					String value = reader.next();
					try{
						gasolineres[i] = new Gas("Gas "+ Character.toString ((char) ('A'+i-1)), Integer.parseInt(value));
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
				} while(gasolineres[i] == null);
			}
		}
		
		//Parades posibles
		Gas [] Parades=new Gas[gasolineres.length];

		int resultat;
		int distanciaDeposit = TAMANY_DIPOSIT;
		System.out.println("distanciaDeposit: "+ distanciaDeposit +" km");

		int acum = 0;
		int delta = 0;
		for(int i=0; i<gasolineres.length-1; i++) {
			delta= gasolineres[i].km;
			acum += delta;
			System.out.println(i+1+" - "+ gasolineres[i].nom +" \ta  "+ gasolineres[i].km +" km \t acum: "+ acum +" km");
		}
		System.out.println(gasolineres.length+" - Desti \ta  "+ (desti-acum) +" km \t acum: "+ desti +" km");
		System.out.println("Desti: "+ desti +" km");
		
		resultat=calcula_parades(distanciaDeposit, gasolineres, Parades, desti);

		
		System.out.println("\n---Resultat-------------");
		if (resultat!=0){
			System.out.println("Aturades: "+ (resultat-1) +" de "+ gasolineres.length);
			for(int i=0; i<resultat; i++)
				System.out.println(i+" - "+ Parades[i].nom);
			System.out.println(resultat+ " - Dest�");
		} else 
			System.out.println("No existeix solucio");
	}
	
	private static int calcula_parades(int distanciaDeposit, Gas []Candidats, Gas []Solucio, int desti){
			int resultat=0;
			int recorregut = 0; 						// acumulat soluci�
			int diposit = distanciaDeposit;				// Reposta

			
			// Els candidats venen en seq��ncia del recorregut
			int index = 0; //�ndex del seg�ent candidat a considerar
			Solucio[resultat++] = Candidats[index++];	// Gasolinera de sortida
			while (index < Candidats.length-2){ // Valorem tots els candidats
				recorregut += Candidats[index].km;
				
				System.out.print(Candidats[index].nom +" -\t acum: "+ recorregut +" \t diposit: "+ (diposit-Candidats[index].km) +" km \t seguent a "+ Candidats[index+1].km +" km" );
				
				if (Candidats[index].km+Candidats[index+1].km < diposit){
					System.out.println("");
					diposit -= Candidats[index].km;
				} else {
					System.out.println(" - Aturada");
					Solucio[resultat++] = Candidats[index];
					diposit = distanciaDeposit; 		// Reposta
				}
				index++;
			} 

			// Cas ultima gasolinera abans del dest�
			recorregut += Candidats[index].km;
			System.out.print(Candidats[index].nom +" -\t acum: "+ recorregut +" \t diposit: "+ (diposit-Candidats[index].km) +" km  \t desti a "+ (desti-recorregut) +" km" );
			if (Candidats[index].km + desti-recorregut < diposit){
				System.out.println("");
				diposit -= Candidats[index].km;
			} else {
				System.out.println(" - Aturada");
				Solucio[resultat++] = Candidats[index];
				diposit = distanciaDeposit;				// Reposta
			}
			
			return resultat;
	} 


}


