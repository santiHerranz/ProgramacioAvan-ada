
public class Agent extends Persona {

	private int antiguitat; //any alta
	private float sou;
	private String compteBancari;

	public int getAntiguitat() {
		return antiguitat;
	}
	public void setAntiguitat(int antiguitat) {
		this.antiguitat = antiguitat;
	}

	public float getSou() {
		return sou;
	}
	public void setSou(float sou) {
		this.sou = sou;
	}

	public String getCompteBancari() {
		return compteBancari;
	}
	public void setCompteBancari(String compteBancari) {
		this.compteBancari = compteBancari;
	}
	
	public Agent(String nom, long DNI) {
		super(nom, DNI);
	}
	
	public Agent(String nom,String adreça, long telefon, long DNI, String poblacio, int antiguitat, float sou, String compteBancari){
		super(nom, DNI);
		this.nom = nom;
		this.adreça = adreça;
		this.telefon = telefon;
		this.DNI = DNI;
		this.poblacio = poblacio;		

		this.antiguitat = antiguitat;
		this.sou = sou;
		this.compteBancari = compteBancari;
	}
	
	public String toString(){
		return super.toString()
		+ "\n antiguitat: "+ this.getAntiguitat()
		+ "\n sou: "+ this.getSou()
		+ "\n compteBancari: "+ this.getCompteBancari()
		;
	}
	public boolean equals(Object o) {
		if (getClass() != o.getClass()) return false;
		return( ((Agent)o).getDNI() ==  this.getDNI());
	}
	
}
