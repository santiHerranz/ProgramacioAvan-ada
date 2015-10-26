import java.util.Date;
import java.util.Map;
import persona.*;
import assegurança.*;
import companyia.*;

public class Practica1 {

	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws Exception {
		
		String version = "1.0";
		System.out.println("app ver "+ version);

		Companyia companyia = new Companyia("Nova asseguradora", "c/ Itaca", 93000010, 100);
		
		Agent agent1 = new Agent("agent1", "Carrer tal i qual", 345230495, 1001, "Barcelona", 1996, 1200, "ES348912834174171");
		Agent agent2 = new Agent("agent2", "Pg graciosa", 34535649, 1002, "Barcelona", 1983, 1100, "ES34891174179931");
		Agent agent3 = new Agent("agent3", "Carrer munt", 345435255, 1003, "Barcelona", 2010, 800, "ES348234924174171");

		companyia.addAgent(agent1);
		companyia.addAgent(agent2);
		companyia.addAgent(agent3);

		// Test esborrar Agent
		//companyia.remAgent(agent1);

		// Test Error Agent no existeix
		//companyia.remAgent(new Agent("Ningu", 9999));		
		
		
		Client client1 = new Client("Santi Herranz", 3001, "adreça", 9303001, "Canet de mar", "ES12343453498798347" );
		Client client2 = new Client("Víctor Herranz", 3002, "adreça", 9303002, "Arenys de mar", "ES312374519874512");
		Client client3 = new Client("Martina Herranz", 3003, "adreça", 9303003, "Sant Pol de mar", "ES10293123901829038");
		Client client4 = new Client("Mar Mir", 3004, "adreça", 9303004, "Arenys de munt", "ES00231029301923091");

		

		AssegurançaVehicle a1 = new AssegurançaVehicle(new Date("10/2/2015"), client1, 600, agent1, "B00000", 40, 0.3f);
		companyia.addAssegurança(a1);

	
		//Assegurançes de Vida
		AssegurançaVida a2 = new AssegurançaVida(new Date("10/14/2015"), client2, 2200, agent1, 40, "Informàtic", 150000);
		// Cas d'us: afegir un beneficiari
		a2.afegirBeneficiari("Martina");
		a2.afegirBeneficiari("Víctor");
		// Cas d'us: afegir i treure un beneficiari
		a2.afegirBeneficiari("Pere");
		a2.eliminarBeneficiari("Pere");
		// Cas d'us: treure un beneficiari que no està
		a2.eliminarBeneficiari("Boby");
		companyia.addAssegurança(a2);

		AssegurançaVida a3 = new AssegurançaVida(new Date("5/2/2015"), client4, 1000, agent1, 37, "Infermera", 220000);
		a3.afegirBeneficiari("Jaime");
		a3.afegirBeneficiari("Rocio");
		companyia.addAssegurança(a3);

		//Assegurançes de la Llar
//		companyia.addAssegurança(new AssegurançaLlar(new Date("1/1/2015") , client2, 2200, agent1, "Diagonal 277, Barcelona", 10000, 15000));
//		companyia.addAssegurança(new AssegurançaLlar(new Date("2/4/2015"), client4,232445.6f, agent1, "pepe", 566.0f, 212.0f));
		
		//Assegurançes de vehicles
//		companyia.addAssegurança(new AssegurançaVehicle(new Date("8/12/2015"), client1, 1800, agent1, "B00000", 40, 0.3f));
//		companyia.addAssegurança(new AssegurançaVehicle(new Date("6/25/2015"), client1, 2200, agent2, "B00000", 40, 0.3f));
//		companyia.addAssegurança(new AssegurançaVehicleTotRisc(new Date("3/10/2015"), client1, 2000, agent3, "B00000", 40, 0.3f, 300, 8));
//		companyia.addAssegurança(new AssegurançaVehicle(new Date("10/12/2015"), client2, 2200, agent1, "B00000", 40, 0.3f));
//		companyia.addAssegurança(new AssegurançaVehicleTotRisc(new Date("4/12/2015"), client4, 700, agent1, "X00000", 40, 0.15f, 300, 8 ));
//		companyia.addAssegurança(new AssegurançaVehicleTotRisc(new Date("3/31/2015"), client2, 2000, agent1, "X00045", 25, 0.10f, 350, 3 ));
//		companyia.addAssegurança(new AssegurançaVehicleTotRisc(new Date("3/31/2015"), client1, 2000, agent1, "X00045", 25, 0.10f, 0, 3 ));
		
		// Test eliminar assegurança  
//		companyia.remAssegurança(a2);
//		companyia.remAssegurança(a1);
		
		System.out.println();
		System.out.println(companyia.toString());
		

		System.out.format("BonificacioPenalitzacio: %.2f\n", a1.getBonificacions()*100.0f);
		companyia.modificacioBonificacioPenalitzacio(1, 5);
		System.out.format("BonificacioPenalitzacio: %.2f\n", a1.getBonificacions()*100.0f);
		companyia.modificacioBonificacioPenalitzacio(1, -7);
		System.out.format("BonificacioPenalitzacio: %.2f\n", a1.getBonificacions()*100.0f);
		
		
		System.out.format("\n assegurançaMesCobertura: %s = %.2f\n", agent1.getNom(), companyia.assegurançaMesCobertura(agent1));
		
		System.out.println("\n tipusAssegurançaMesContractada:" + companyia.tipusAssegurançaMesContractada());
		
		Map<Integer, Agent> taulaComisions =companyia.calculComissio(10);
		//System.out.println("\ntaulaComisions (mes = 10): "+ " "+ taulaComisions.values().toArray().length +" polisses venudes"); 
		//System.out.println(taulaComisions.toString());
		
	}
}
