import ednolineals.*;

public class Seccio {
private String nom;
private Acb equips; //magatzem dels equips d�aquesta secci�
public Seccio(String nom){
	equips=(Acb) new AcbEnll(); 
	this.nom=nom; 
	}
public void addEquip(Equip p) throws Exception{ equips.inserir(p);}
public void remEquip(Equip p) throws Exception{ equips.esborrar(p);}
public String equipMesJugadors(){ 
	/*sent�ncies Exercici 4*/ 
	return "";
}
public boolean equals(Object o){
/* sent�ncies Exercici 4. Redefinici� del m�tode equals*/
	return false;
}
public String trobaNomEquipMajorCodi() throws Exception{ 
	/*sent�ncies Exercici 4*/ 
	return "";
	}
}