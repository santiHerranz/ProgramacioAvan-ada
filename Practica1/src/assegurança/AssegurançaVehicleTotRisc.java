package asseguran�a;

import java.util.Date;
import persona.*;

public class Asseguran�aVehicleTotRisc extends Asseguran�aVehicle {

	private float franquicia;
	private int anysVehicle;

	public float getFranquicia() {
		return franquicia;
	}
	public void setFranquicia(float franqu�cia) {
		this.franquicia = franqu�cia;
	}

	public int getAnysVehicle() {
		return anysVehicle;
	}
	public void setAnysVehicle(int anysVehicle) {
		this.anysVehicle = anysVehicle;
	}

	public Asseguran�aVehicleTotRisc(Date dateEmissio, Client client, float valorImport, Agent corredor,
			String matricula, int edatConductorHabitual, float bonificacions,float franqu�cia, int anysVehicle) {
		super(dateEmissio, client, valorImport, corredor, matricula, edatConductorHabitual, bonificacions);
		this.franquicia = franqu�cia;
		this.anysVehicle = anysVehicle;
	}
	
	public String toString(){
		return super.toString()
	    + "\n franqu�cia: "+ this.getFranquicia() +" (�)"
		+ "\n anysVehicle: "+ this.getAnysVehicle()
		;
	}	
}
