package Exercici22;

import ednolineals.*;
import ednolineals.Comparable;

public class Seccio {
	
private String nom;
Acb<Equip> equips; //magatzem dels equips d’aquesta secció
public Seccio(String nom){
	equips = new AcbEnll<Equip>(); 
	this.nom=nom; 
	}
public void addEquip(Equip p) throws Exception{ 
	equips.Inserir((Comparable<Equip>) p);
}
public void remEquip(Equip p) throws Exception{ 
	equips.Esborrar(p);
}


public String equipMesJugadors(){ // Class Seccio 
	/* Cal determinar lequip de la Secció que té més jugadors contractats, 
	 * concretament ens interessa el nom daquest equip. Si en hi ha més dun amb el mateix nombre
	 *  és irrellevant el que es retorni
	 *  
	 * TODO Solució Exercici 4.1 : equipMesJugadors
	 * */
	return ((AcbEnll<Equip>) equips).equipMesJugadors().getNom();
}

public boolean equals(Object o){ // Class Seccio
	/* Dues seccions són la mateixa, són iguals si a més de tenir el mateix nom de Secció, 
	 * larbre que conté els equips és idèntic, és a dir no basta que tinguin els mateixos elements,
	 * aquests dins de larbre han destar situats al mateix lloc. La implementació ha de ser recursiva
	 * 
	 * TODO Solució Exercici 4.2 : equals
	 * */
	if (!(o instanceof Seccio)) return false;
	return nom.equals(((Seccio)o).nom) && ((AcbEnll<Equip>)equips).equals(((Seccio)o).equips);

}

	public String trobaNomEquipMajorCodi() throws Exception{ // Class Seccio
		/* ha de trobar el nom de lequip de la secció amb número de codi dequip més elevat. 
		 * La implementació NO pot ser recursiva. Cal llançar una excepció si la secció no té cap equip.
		 * 
		 * TODO Solució Exercici 4.3 : trobaNomEquipMajorCodi
		 * */
		return ((AcbEnll<Equip>)equips).trobaNomMajorCodi();
	}
	
	public String toString(){
		return nom;
	}
	
}
