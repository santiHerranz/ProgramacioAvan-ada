package Exercici22;

public class SociAmbAbonament extends Soci{
private Seccio abonada;
private char tipusAbonament; //A, B ó C;
private String seient;

public SociAmbAbonament(long numeroSoci, String nom, Seccio abonada, char tipus, String cadira){
	super(numeroSoci, nom); 
	this.abonada=abonada; 
	tipusAbonament=tipus;
	seient=cadira;
}
public Seccio getSeccio(){ return abonada;}
public char getTipusAbonament(){ return tipusAbonament;}
public String getSeient(){ return seient;}
} //fi classe