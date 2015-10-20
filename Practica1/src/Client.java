
public class Client extends Persona {

	private String compteBancari;
	
	public String getCompteBancari() {
		return compteBancari;
	}
	public void setCompteBancari(String compteBancari) {
		this.compteBancari = compteBancari;
	}
	
	public Client(String nom, long DNI, String compteBancari) {
		super(nom, DNI);
		this.compteBancari = compteBancari;
	}
	public Client(String nom, long DNI, String adreça, long telefon, String poblacio, String compteBancari) {
		super(nom, DNI);
		this.adreça = adreça;
		this.telefon = telefon;
		this.poblacio = poblacio;
		this.compteBancari = compteBancari;
	}
	
	public String toString(){
		return super.toString()
		+"\n compteBancari: "+ this.getCompteBancari()
		;
	}		
}
