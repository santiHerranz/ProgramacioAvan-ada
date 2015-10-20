import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Companyia {

	//taula d�una dimensi� on s�emmagatzemen seq�encialment totes les assegurances que la companyia t� contractades
	private Asseguran�a[] assegurances;
	private int maxAssegurances; // L�mit d'asseguran�es que es poden contractar
	private int quantesAssegurances; // n�mero d�assegurances contractades, �s a dir la dimensi� real de la taula
	
	// objecte de la collecci� parametritzada List<Agent>
	private List<Agent> agents;
	
	//seq��ncia enlla�ada de nodes, aquesta no t� node cap�alera i �s lineal
	private Node clients;

	private String nom;
	private String adre�a;
	private long telefon;
	
	private int contadorPolisses;
	
	public Companyia(String nom, String adre�a, long telefon, int maxAssegurances){

		this.contadorPolisses = 0;
		
		this.setNom(nom);
		this.setAdre�a(adre�a);
		this.setTelefon(telefon);

		//
		this.maxAssegurances = maxAssegurances;
		this.assegurances = new Asseguran�a[maxAssegurances];
		this.agents = new ArrayList<Agent>();
		//this.clients // La seq��ncia enlla�ada no t� cap�elera
		
	}
	
		public void addAsseguran�a(Asseguran�a a) throws Exception {
			
			// Hi ha lloc?
			if( this.quantesAssegurances+1 > this.maxAssegurances)
				throw new Exception("La companyia no pot contractar m�s asseguran�es (Max:"+ this.maxAssegurances +")");
			
			// Est� repetit?
			for( Asseguran�a item : this.assegurances){
				if(item != null){
					if(item.equals(a)) {
						throw new Exception("Ja existeix l'asseguran�a "+ a.toString());
					};
				}
			}

			// Agent contractat?
			boolean trobat = false;
			for( Agent item : this.agents){
				if(item.equals(a.getCorredor())) {
					trobat = true;
				}
			}
			if(!trobat) {
				throw new Exception("Agent no est� contractat "+ a.toString());
			};
			
			
//			Si la persona que fa la contractaci� no era client de la companyia tamb� s�ha d�afegir al corresponent	magatzem de clients
				if(!this.existsClient(a.getClient())) {
					Node nouClient = new Node(a.getClient(), null);

					// Si no hi ha clients, afegir la referencia
					if(this.clients == null)
						this.clients = nouClient;
					else {
						// Afegir el node a l'ultim de la seq�encia enlla�ada.
						this.getLastNode().setSeguent(nouClient);
					}
						
				}
				
				this.contadorPolisses++;
				a.setNumeroPolissa(this.contadorPolisses);
			
			this.assegurances[quantesAssegurances] = a;
			quantesAssegurances++;
			
			
		};
		public void remAsseguran�a(Asseguran�a a) throws Exception {
			
			// Existeix?
			boolean trobat = false;
			for(int i=0;i<this.assegurances.length;i++) {
				Asseguran�a item = this.assegurances[i];		
				if(item != null){
					if(item.equals(a)){
						trobat = true;

						Client client = item.getClient();
						// Donar de baixa el client??
						if(this.asseguran�esClient(client)==1) {
							remClient(client);
						}
						
						//Com que no importa l'ordre a la taula es pot posar l'ultim sobre l'element a eliminar
						// Si l'element a eliminar �s l'ultim, possar null
						if(item.equals(this.assegurances[this.quantesAssegurances-1]))
							this.assegurances[i] = null;
						else {						
							this.assegurances[i] = this.assegurances[this.quantesAssegurances-1];
							this.assegurances[this.quantesAssegurances-1] = null;
						}
						this.quantesAssegurances--;
						
						break;
					}
				}
			}			
			if(!trobat) {
				throw new Exception("Error: No s'ha trobat la polissa a eliminar "+ a.toString());
			};

			
			
		}

		public void addAgent(Agent a) throws Exception {
			if( this.agents.contains(a)) throw new Exception("L'agent ja existeix "+ a.toString() );
			agents.add(a);
		}
		public void remAgent(Agent a) throws Exception {
			if( !this.agents.contains(a)) throw new Exception("L'agent no existeix "+ a.toString() );
			agents.remove(a);
		}
		
		public Map<Integer, Agent> calculComissio(int mes) {
			Map<Integer, Agent> m1 = new TreeMap<Integer, Agent>();
			for(int i = 0; i < this.assegurances.length; i++) {
				Asseguran�a a = this.assegurances[i];
				if(a != null) {
					int Asseguran�aMes = a.getDateEmissio().getMonth()+1;
					if( Asseguran�aMes == mes) {
						m1.put(a.getNumeroPolissa(), a.getCorredor());
					}
				}
			}
			return m1;
		}
		
		public int quantesAsssegurancesVehicleTotRisc(){
			return quantesAsssegurancesVehicleTotRisc(0);
		}
		public int quantesAsssegurancesVehicleTotRisc(int franquicia){
			int counter = 0;
			for(Asseguran�a a : this.assegurances) {
				if(a instanceof Asseguran�aVehicleTotRisc){
					Asseguran�aVehicleTotRisc b = (Asseguran�aVehicleTotRisc)a;
					if(b.getFranquicia() >= franquicia )
						counter++;
				}
			}
			return counter;
		}
		
		public int quantsClients(String poblacio){
			if (this.clients == null) return 0;
			return this.clients.getCount(poblacio);
		}
		public void modificacioBonificacioPenalitzacio(int Bonificacio, float Penalitzacio){
			
			for( Asseguran�a item : this.assegurances){
				if(item != null){
					if(item instanceof Asseguran�aVehicle) {
						Asseguran�aVehicle av = ((Asseguran�aVehicle) item);
						if( item.getNumeroPolissa() == Bonificacio){
							av.setBonificacions(av.getBonificacions()+ Penalitzacio/100.0f);
						}
					}
				}
			}
			
		}
		public float asseguran�aMesCobertura(Agent agent){
			float maximaCobertura = 0.0f;
			for( Asseguran�a item : this.assegurances){
				if(item != null){
					if(item instanceof Asseguran�aVida) {
						Asseguran�aVida av = ((Asseguran�aVida) item);
						if( item.getCorredor().equals(agent)){
							if(av.getCobertura() > maximaCobertura) {
								maximaCobertura = av.getCobertura();
							}
						}
					}
				}
			}
			return maximaCobertura;
		}
		
		public String tipusAsseguran�aMesContractada(){
			int tipusVida = 0;
			int tipusLlar = 0;
			int tipusVehicle = 0;
			String tipus = "Asseguran�a";
			
			for( Asseguran�a item : this.assegurances){
				if(item != null){
					if(item instanceof Asseguran�aVida)tipusVida++;
					if(item instanceof Asseguran�aLlar)tipusLlar++;
					if(item instanceof Asseguran�aVehicle) tipusVehicle++;
				}
			}
			if(tipusVida>=tipusLlar && tipusVida>=tipusVehicle) 
				tipus = String.format("Asseguran�aVida (%d)",tipusVida);
			if(tipusLlar>=tipusVida && tipusLlar>=tipusVehicle)
				tipus = String.format("Asseguran�aLlar (%d)",tipusLlar);
			if(tipusVehicle>=tipusLlar && tipusVehicle>=tipusVida) 
				tipus = String.format("Asseguran�aVehicle (%d)",tipusVehicle);
			
			return tipus;
		}
		
	
		
		public boolean existsClient(Client client){

			Node item = this.clients;

			// Hi han clients?
			if( item == null) return false;
			
			// es el primer?
			if(item.getClient().equals(client)) return true;
			
			// M�tode de cerca a seq��ncia enlla�ada
			boolean trobat = false;
			while(item != null){
				if(item.getClient().equals(client)) {
					trobat = true;
					break;
				}
				item = item.getSeguent();
			}
			return trobat;
		}
		
		
		private int asseguran�esClient(Client client){

			int counter = 0;
			for(Asseguran�a a : this.assegurances) {
				if(a!=null)
					if(a.getClient().equals(client) )
						counter++;
			}			
			return counter;
		}
		
		private void remClient(Client client){

			Node item = this.clients;
			while(item!= null){
				if(item.client.equals(client)) {
					// Eliminar client i Node
					// Si es el node cap, apuntar al seguent
					if(item.equals(this.clients)) {

						this.clients = item.getSeguent();
						item = null;
						
						return;
					} 
					// Si no es el node cap, trobar anterior i apuntar al seguent
					else {
						Node anterior = this.clients;
						while(anterior.getSeguent()!= null){
							if(anterior.getSeguent().client.equals(client)) {
								
								anterior.setSeguent(item.getSeguent());
								item = null;
								
								return;
							} 
						}
					}
				}
				item = item.getSeguent();
			}		
		}
		
		private Node getLastNode(){

			Node item = this.clients;
			if (item == null) return null;

			if(item.getSeguent() == null) return item;
			
			// Rec�rrer la seq��ncia enlla�ada fins al �ltim node
			while(item.getSeguent() != null){
				item = item.getSeguent();
			}
			return item;
		}		


		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getAdre�a() {
			return adre�a;
		}
		public void setAdre�a(String adre�a) {
			this.adre�a = adre�a;
		}

		public long getTelefon() {
			return telefon;
		}
		public void setTelefon(long telefon) {
			this.telefon = telefon;
		}
		
		public String toString(){
			
			String asseguran�aListString = "";
			for(Asseguran�a a : this.assegurances) {
				if(a != null){
					if (asseguran�aListString!="") asseguran�aListString = asseguran�aListString +"\n";
					asseguran�aListString = asseguran�aListString +"\n"+ a.getClass().getSimpleName() + a.toString();
				}
			}
			String agentListString = "";
			for(Agent a : this.agents) {
				if(a != null){
					if (agentListString!="") agentListString = agentListString +"\n";
					agentListString = agentListString +"\nAgent"+ a.toString();
				}
			}
			String clientListString = "";
			Node item = this.clients;
			while(item!= null){
				if (clientListString!="") clientListString = clientListString +"\n";
				clientListString = clientListString +"\nClient"+ item.toString();
				item = item.getSeguent();
			}			
			
			
			//TODO Afegir tots els atributs
			return "Companyia: "+ getNom() 
			+ "\n Adre�a: "+ this.getAdre�a() 
			+ "\n\n----- quantsClients: "+ this.quantsClients("")
			+ "\n----- quantsClients (poblacio= Canet de mar): "+ this.quantsClients("Canet de mar")
			+ "\n\n----- Cartera de clients: "+ clientListString
			+ "\n\n----- Agents d�assegurances: "+ agentListString
			+ "\n\n----- Assegurances:"+ this.quantesAssegurances +" polisses (Max:"+ this.maxAssegurances +")"
			+ "\n quantesAsssegurancesVehicleTotRisc: "+ this.quantesAsssegurancesVehicleTotRisc()
			+ "\n quantesAsssegurancesVehicleTotRisc (franquicia>=200): "+ this.quantesAsssegurancesVehicleTotRisc(200)
			+ "\n "+ asseguran�aListString
			;
			
		}



		private class Node {

			//Atributs
			private Client client;
			private Node seguent;

			public Node( Client client, Node node) {
				setClient(client);
				setSeguent(node);
			}

			public Node getSeguent() {
				return this.seguent;
			}
			public void setSeguent(Node node) {
				this.seguent = node;
			}

			public Client getClient() {
				return client;
			}
			public void setClient(Client client) {
				this.client = client;
			}
			
			public int getCount(String poblacio){

				int i = 0;
				Node item = this;
				if(item.getSeguent()== null &&(item.getClient().getPoblacio() == poblacio || poblacio == ""))
					return 1;
				while(item != null){
					if(item.getClient().getPoblacio() == poblacio || poblacio == "")
						i++;
					item = item.getSeguent();
				}
				return i;
				
			}			
			
			public String toString(){

				return this.getClient().toString();
				
			}
			
		}	
		
	}

