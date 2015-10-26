package asseguran�a;

import java.util.Date;
import persona.*;

public class Asseguran�aLlar extends Asseguran�a {
	
	private String adre�aAssegurat;
	private float valorContingut;
	private float valorContinent;

	public String getAdre�aAssegurat() {
		return adre�aAssegurat;
	}
	public void setAdre�aAssegurat(String adre�aAssegurat) {
		this.adre�aAssegurat = adre�aAssegurat;
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

	public Asseguran�aLlar(Date dateEmissio, Client client, float valorImport, Agent corredor) {
		super(dateEmissio, client, valorImport, corredor);
	}
	
	public Asseguran�aLlar(Date dateEmissio, Client client, float valorImport, Agent corredor
			,String adre�aAssegurat
			, float valorContingut
			, float valorContinent) {
		super(dateEmissio, client, valorImport, corredor);
		this.adre�aAssegurat = adre�aAssegurat;
		this.valorContingut = valorContingut;
		this.valorContinent = valorContinent;
	}

	public String toString(){
		return super.toString()
	    + "\n adre�aAssegurat: "+ this.getAdre�aAssegurat()
		+ "\n valorContingut: "+ this.getValorContingut()
		+ "\n valorContinent: "+ this.getValorContinent()
		;
	}	
	

}
