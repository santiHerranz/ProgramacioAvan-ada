package asseguran�a;

import java.util.Arrays;
import java.util.Date;
import persona.*;

public class Asseguran�aVida extends Asseguran�a {

	private int edat;
	private String professi�;
	private String beneficiaris[]; 
	private float cobertura;
	
	public int getEdat() {
		return edat;
	}
	public void setEdat(int edat) {
		this.edat = edat;
	}

	public String getProfessi�() {
		return professi�;
	}
	public void setProfessi�(String professi�) {
		this.professi� = professi�;
	}

	public String[] getBeneficiaris() {
		return beneficiaris;
	}
	public void setBeneficiaris(String[] beneficiaris) {
		this.beneficiaris = beneficiaris;
	}

	public float getCobertura() {
		return cobertura;
	}
	public void setCobertura(float cobertura) {
		this.cobertura = cobertura;
	}

	public Asseguran�aVida(Date dateEmissio, Client client, float valorImport, Agent corredor
			,int edat, String professio, float cobertura) {
		super(dateEmissio, client, valorImport, corredor);
		this.edat = edat;
		this.professi� = professio;
		this.cobertura = cobertura;		
		
		this.beneficiaris = new String[0];
	}
	
	public void afegirBeneficiari(String nom){
		String[] s = new String[beneficiaris.length+1];
		for(int i = 0; i< beneficiaris.length;i++) {
			s[i] = beneficiaris[i];
		}
		s[s.length-1] = nom;
		beneficiaris = s;
		
	}
	public void eliminarBeneficiari(String nom){

		int index = -1;
		for(int i = 0; i< beneficiaris.length;i++) {
			if(beneficiaris[i].equals(nom)) {
				index = i;
				break;
			}
		}
		if(index>=0){
			int added = 0;
			String[] s = new String[beneficiaris.length-1];
			for(int i = 0; i< beneficiaris.length;i++) {
				if(index != i) {
					s[added] = beneficiaris[i];
					added++;
				}
			}
			beneficiaris = s;
		}
	}

	public String toString(){
		return  super.toString()
		+ "\n edat: "+ this.getEdat()
		+ "\n professi�: "+ this.getProfessi�()
		+ "\n beneficiaris: "+ Arrays.toString(this.beneficiaris)
		+ "\n cobertura: "+ this.getCobertura()
		;
	}	


}
