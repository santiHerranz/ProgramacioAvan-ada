package assegurança;

import java.util.Date;
import persona.*;

public class AssegurançaVehicleTotRisc extends AssegurançaVehicle {

	private float franquicia;
	private int anysVehicle;

	public float getFranquicia() {
		return franquicia;
	}
	public void setFranquicia(float franquícia) {
		this.franquicia = franquícia;
	}

	public int getAnysVehicle() {
		return anysVehicle;
	}
	public void setAnysVehicle(int anysVehicle) {
		this.anysVehicle = anysVehicle;
	}

	public AssegurançaVehicleTotRisc(Date dateEmissio, Client client, float valorImport, Agent corredor,
			String matricula, int edatConductorHabitual, float bonificacions,float franquícia, int anysVehicle) {
		super(dateEmissio, client, valorImport, corredor, matricula, edatConductorHabitual, bonificacions);
		this.franquicia = franquícia;
		this.anysVehicle = anysVehicle;
	}
	
	public String toString(){
		return super.toString()
	    + "\n franquícia: "+ this.getFranquicia() +" (€)"
		+ "\n anysVehicle: "+ this.getAnysVehicle()
		;
	}	
}
