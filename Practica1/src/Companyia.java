import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Companyia {

	//taula d’una dimensió on s’emmagatzemen seqüencialment totes les assegurances que la companyia té contractades
	private Assegurança[] assegurances;
	private int maxAssegurances; // Límit d'assegurançes que es poden contractar
	private int quantesAssegurances; // número d’assegurances contractades, és a dir la dimensió real de la taula
	
	// objecte de la collecció parametritzada List<Agent>
	private List<Agent> agents;
	
	//seqüència enllaçada de nodes, aquesta no té node capçalera i és lineal
	private Node clients;

	private String nom;
	private String adreça;
	private long telefon;
	
	private int contadorPolisses;
	
	public Companyia(String nom, String adreça, long telefon, int maxAssegurances){

		this.contadorPolisses = 0;
		
		this.setNom(nom);
		this.setAdreça(adreça);
		this.setTelefon(telefon);

		//
		this.maxAssegurances = maxAssegurances;
		this.assegurances = new Assegurança[maxAssegurances];
		this.agents = new ArrayList<Agent>();
		//this.clients // La seqüència enllaçada no té capçelera
		
	}
	
		public void addAssegurança(Assegurança a) throws Exception {
			
			// Hi ha lloc?
			if( this.quantesAssegurances+1 > this.maxAssegurances)
				throw new Exception("La companyia no pot contractar més assegurançes (Max:"+ this.maxAssegurances +")");
			
			// Està repetit?
			for( Assegurança item : this.assegurances){
				if(item != null){
					if(item.equals(a)) {
						throw new Exception("Ja existeix l'assegurança "+ a.toString());
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
				throw new Exception("Agent no està contractat "+ a.toString());
			};
			
			
//			Si la persona que fa la contractació no era client de la companyia també s’ha d’afegir al corresponent	magatzem de clients
				if(!this.existsClient(a.getClient())) {
					Node nouClient = new Node(a.getClient(), null);

					// Si no hi ha clients, afegir la referencia
					if(this.clients == null)
						this.clients = nouClient;
					else {
						// Afegir el node a l'ultim de la seqüencia enllaçada.
						this.getLastNode().setSeguent(nouClient);
					}
						
				}
				
				this.contadorPolisses++;
				a.setNumeroPolissa(this.contadorPolisses);
			
			this.assegurances[quantesAssegurances] = a;
			quantesAssegurances++;
			
			
		};
		public void remAssegurança(Assegurança a) throws Exception {
			
			// Existeix?
			boolean trobat = false;
			for(int i=0;i<this.assegurances.length;i++) {
				Assegurança item = this.assegurances[i];		
				if(item != null){
					if(item.equals(a)){
						trobat = true;

						Client client = item.getClient();
						// Donar de baixa el client??
						if(this.assegurançesClient(client)==1) {
							remClient(client);
						}
						
						//Com que no importa l'ordre a la taula es pot posar l'ultim sobre l'element a eliminar
						// Si l'element a eliminar és l'ultim, possar null
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
				Assegurança a = this.assegurances[i];
				if(a != null) {
					int AssegurançaMes = a.getDateEmissio().getMonth()+1;
					if( AssegurançaMes == mes) {
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
			for(Assegurança a : this.assegurances) {
				if(a instanceof AssegurançaVehicleTotRisc){
					AssegurançaVehicleTotRisc b = (AssegurançaVehicleTotRisc)a;
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
			
			for( Assegurança item : this.assegurances){
				if(item != null){
					if(item instanceof AssegurançaVehicle) {
						AssegurançaVehicle av = ((AssegurançaVehicle) item);
						if( item.getNumeroPolissa() == Bonificacio){
							av.setBonificacions(av.getBonificacions()+ Penalitzacio/100.0f);
						}
					}
				}
			}
			
		}
		public float assegurançaMesCobertura(Agent agent){
			float maximaCobertura = 0.0f;
			for( Assegurança item : this.assegurances){
				if(item != null){
					if(item instanceof AssegurançaVida) {
						AssegurançaVida av = ((AssegurançaVida) item);
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
		
		public String tipusAssegurançaMesContractada(){
			int tipusVida = 0;
			int tipusLlar = 0;
			int tipusVehicle = 0;
			String tipus = "Assegurança";
			
			for( Assegurança item : this.assegurances){
				if(item != null){
					if(item instanceof AssegurançaVida)tipusVida++;
					if(item instanceof AssegurançaLlar)tipusLlar++;
					if(item instanceof AssegurançaVehicle) tipusVehicle++;
				}
			}
			if(tipusVida>=tipusLlar && tipusVida>=tipusVehicle) 
				tipus = String.format("AssegurançaVida (%d)",tipusVida);
			if(tipusLlar>=tipusVida && tipusLlar>=tipusVehicle)
				tipus = String.format("AssegurançaLlar (%d)",tipusLlar);
			if(tipusVehicle>=tipusLlar && tipusVehicle>=tipusVida) 
				tipus = String.format("AssegurançaVehicle (%d)",tipusVehicle);
			
			return tipus;
		}
		
	
		
		public boolean existsClient(Client client){

			Node item = this.clients;

			// Hi han clients?
			if( item == null) return false;
			
			// es el primer?
			if(item.getClient().equals(client)) return true;
			
			// Métode de cerca a seqüència enllaçada
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
		
		
		private int assegurançesClient(Client client){

			int counter = 0;
			for(Assegurança a : this.assegurances) {
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
			
			// Recòrrer la seqüència enllaçada fins al últim node
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

		public String getAdreça() {
			return adreça;
		}
		public void setAdreça(String adreça) {
			this.adreça = adreça;
		}

		public long getTelefon() {
			return telefon;
		}
		public void setTelefon(long telefon) {
			this.telefon = telefon;
		}
		
		public String toString(){
			
			String assegurançaListString = "";
			for(Assegurança a : this.assegurances) {
				if(a != null){
					if (assegurançaListString!="") assegurançaListString = assegurançaListString +"\n";
					assegurançaListString = assegurançaListString +"\n"+ a.getClass().getSimpleName() + a.toString();
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
			+ "\n Adreça: "+ this.getAdreça() 
			+ "\n\n----- quantsClients: "+ this.quantsClients("")
			+ "\n----- quantsClients (poblacio= Canet de mar): "+ this.quantsClients("Canet de mar")
			+ "\n\n----- Cartera de clients: "+ clientListString
			+ "\n\n----- Agents d’assegurances: "+ agentListString
			+ "\n\n----- Assegurances:"+ this.quantesAssegurances +" polisses (Max:"+ this.maxAssegurances +")"
			+ "\n quantesAsssegurancesVehicleTotRisc: "+ this.quantesAsssegurancesVehicleTotRisc()
			+ "\n quantesAsssegurancesVehicleTotRisc (franquicia>=200): "+ this.quantesAsssegurancesVehicleTotRisc(200)
			+ "\n "+ assegurançaListString
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

