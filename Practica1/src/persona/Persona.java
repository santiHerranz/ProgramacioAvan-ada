package persona;

public abstract class Persona {
	
	String nom; 
	String adre�a;
	long telefon; 
	long DNI; 
	String poblacio;
	
	public Persona(String nom, long DNI) {
		this.nom = nom;
		this.DNI = DNI;
	}
	
	public Persona(String nom,String adre�a, long telefon, long DNI, String poblacio) {
		this.nom = nom;
		this.adre�a = adre�a;
		this.telefon = telefon;
		this.DNI = DNI;
		this.poblacio = poblacio;
	}
	
	public void setNom(String value){nom = value;}
	public String getNom(){	return nom;	}		
	
	public void setAdre�a(String value){ adre�a = value;}
	public String getAdre�a(){ return adre�a;}		

	public void setTelefon(long value){ telefon = value;}
	public long getTelefon(){ return telefon;	}
	
	public void setDNI(long value){ DNI = value;}
	public long getDNI(){ return DNI;	}	

	public void setPoblacio(String value){ poblacio = value;}
	public String getPoblacio(){ return poblacio; }	
	

	
	public String toString(){
		return "\n Nom: "+  this.getNom()
		+"\n DNI: "+ this.getDNI()
		+"\n Adre�a: "+ this.getAdre�a()
		+"\n Poblacio: "+ this.getPoblacio()
		;
		
	}	
}
