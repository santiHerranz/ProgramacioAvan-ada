package persona;

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
	public Client(String nom, long DNI, String adre�a, long telefon, String poblacio, String compteBancari) {
		super(nom, DNI);
		this.adre�a = adre�a;
		this.telefon = telefon;
		this.poblacio = poblacio;
		this.compteBancari = compteBancari;
	}
	
	public String toString(){
		return super.toString()
		+"\n compteBancari: "+ this.getCompteBancari()
		;
	}
	
	/* Equals */
	
	public boolean equals(Client c) {
		if (c == null || this == null) return false;
		if (this.getDNI() == c.getDNI()) return true;
		else return false;
	}	
}
