package assegurança;

import java.util.Date;
import persona.*;

public class AssegurançaLlar extends Assegurança {
	
	private String adreçaAssegurat;
	private float valorContingut;
	private float valorContinent;

	public String getAdreçaAssegurat() {
		return adreçaAssegurat;
	}
	public void setAdreçaAssegurat(String adreçaAssegurat) {
		this.adreçaAssegurat = adreçaAssegurat;
	}

	public float getValorContingut() {
		return valorContingut;
	}
	public void setValorContingut(float valorContingut) {
		this.valorContingut = valorContingut;
	}

	public float getValorContinent() {
		return valorContinent;
	}
	public void setValorContinent(float valorContinent) {
		this.valorContinent = valorContinent;
	}

	public AssegurançaLlar(Date dateEmissio, Client client, float valorImport, Agent corredor) {
		super(dateEmissio, client, valorImport, corredor);
	}
	
	public AssegurançaLlar(Date dateEmissio, Client client, float valorImport, Agent corredor
			,String adreçaAssegurat
			, float valorContingut
			, float valorContinent) {
		super(dateEmissio, client, valorImport, corredor);
		this.adreçaAssegurat = adreçaAssegurat;
		this.valorContingut = valorContingut;
		this.valorContinent = valorContinent;
	}

	public String toString(){
		return super.toString()
	    + "\n adreçaAssegurat: "+ this.getAdreçaAssegurat()
		+ "\n valorContingut: "+ this.getValorContingut()
		+ "\n valorContinent: "+ this.getValorContinent()
		;
	}	
	

}
