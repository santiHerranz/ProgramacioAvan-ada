import ednolineals.*;

public class Seccio {
private String nom;
private Acb equips; //magatzem dels equips d’aquesta secció
public Seccio(String nom){
	equips=(Acb) new AcbEnll(); 
	this.nom=nom; 
	}
public void addEquip(Equip p) throws Exception{ equips.inserir(p);}
public void remEquip(Equip p) throws Exception{ equips.esborrar(p);}
public String equipMesJugadors(){ 
	/*sentències Exercici 4*/ 
	return "";
}
public boolean equals(Object o){
/* sentències Exercici 4. Redefinició del mètode equals*/
	return false;
}
public String trobaNomEquipMajorCodi() throws Exception{ 
	/*sentències Exercici 4*/ 
	return "";
	}
}