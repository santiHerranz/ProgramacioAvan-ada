package domini;

public class Casella {

	public Estat estat;
	
	private String contingut;
	
	public Casella(){
		this.setContingut(new String(""));
	}

	public Casella(String contingut){
		this.setContingut(contingut);
	}

	public String getContingut() {
		return contingut;
	}
	public void setContingut(String contingut) {
		this.contingut = contingut;
	}


}
