package Exercici1;

public class Exercici1 {

	
	/*
	 * Entrada:
	 * Ciutat origen
	 * Ciutat destí
	 * Quilómetres de distància que pot recòrrer amb el dipósit ple
	 * Nombre de gasolineres
	 * Distancia entre gasolineres
	 *  
	 * Quins són els candidats del problema?
	 *  Les gasolineres per reposar combustible
	 * 
	 * Quina funció de selecció aplicarà el vostre algorisme?
	 *  
	 * 
	 * La vostra funció de selecció, garanteix trobar sempre la millor solució? 
	 * 
	 * Perquè?
	 * 
	 * Análisi descendent
	 * 
	 */
	
	public static void main(String[] args) {
		System.out.println("Exercici1 - Tècnica Voraç - viatjant");
		
		int TAMANY_DIPOSIT = 250;
		
		int desti = 0;
		
		int numero =  (int)(Math.random()*8)+3;
		
		Gas gasolineres[]= new Gas[numero];
		gasolineres[0] = new Gas("Sortida", 0);

		for(int i=1;i<numero-1;i++) {
			int delta = (int)(Math.random()*TAMANY_DIPOSIT)+5;
			gasolineres[i] = new Gas("Gas "+ Character.toString ((char) ('A'+i-1)), delta) ;
			desti += delta;
		}
		desti += (int)(Math.random()*TAMANY_DIPOSIT)+5;
		System.out.println("desti: "+ desti +" km");
		
		//Parades posibles
		Gas [] Parades=new Gas[numero+1];

		int resultat;
		int distanciaDeposit = TAMANY_DIPOSIT;
		System.out.println("distanciaDeposit: "+ distanciaDeposit +" km");

		int acum = 0;
		for(int i=0; i<gasolineres.length-1; i++) {
			acum += gasolineres[i].km;
			System.out.println(i+1+" - "+ gasolineres[i].nom +" \ta  "+ gasolineres[i].km +" km \t acum: "+ acum +" km");
		}
		System.out.println(numero+" - Desti \ta  "+ (desti-acum) +" km \t acum: "+ desti +" km");
		
		resultat=calcula_parades(distanciaDeposit, gasolineres, Parades, desti);

		
		System.out.println("\n---Resultat-------------");
		if (resultat!=0){
			System.out.println("Aturades: "+ (resultat-1) +" de "+ gasolineres.length);
			for(int i=0; i<resultat; i++)
				System.out.println(i+" - "+ Parades[i].nom);
			System.out.println(resultat+ " - Destí");
		} else 
			System.out.println("No existeix solucio");
	}
	
	private static int calcula_parades(int distanciaDeposit, Gas []Candidats, Gas []Solucio, int desti){
			int resultat=0;
			// Els candidats venen en seqüència del recorregut
			int index = 0; //índex del següent candidat a considerar

			int recorregut = 0; 						// acumulat solució
			Solucio[resultat++] = Candidats[index++];	// Gasolinera de sortida
			int diposit = distanciaDeposit;				// Reposta

			while (index < Candidats.length-2){
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

			// Cas ultima gasolinera abans del destí
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


