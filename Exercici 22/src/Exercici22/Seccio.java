package Exercici22;

import ednolineals.*;
import ednolineals.Comparable;

public class Seccio {
private String nom;
private Acb equips; //magatzem dels equips d’aquesta secció
public Seccio(String nom){
	equips = new AcbEnll(); 
	this.nom=nom; 
	}
public void addEquip(Equip p) throws Exception{ 
	equips.Inserir((Comparable) p);
}
public void remEquip(Equip p) throws Exception{ 
	equips.Esborrar(p);
}


public String equipMesJugadors(){ 
	/* Cal determinar lequip de la Secció que té més jugadors contractats, 
	 * concretament ens interessa el nom daquest equip. Si en hi ha més dun amb el mateix nombre
	 *  és irrellevant el que es retorni
	 *  
	 * TODO Solució Exercici 4.1 : equipMesJugadors
	 * Trobar l'equip amb mes jugadors dins a l'arbre de cerca binari
	 * 1. Fer recorregut complert i obtenir quants jugadors té cada equip
	 * 1.1 començem amb l'arrel
	 * 1.2 fem recorregut inordre per exemple, es indiferent
	 * 2. guardar el nom de l'equip que te més jugadors
	 * */
	return ((AcbEnll) equips).equipMesJugadors().getNom();
}

	public boolean equals(Object o){
		/* Dues seccions són la mateixa, són iguals si a més de tenir el mateix nom de Secció, 
		 * larbre que conté els equips és idèntic, és a dir no basta que tinguin els mateixos elements,
		 * aquests dins de larbre han destar situats al mateix lloc. La implementació ha de ser recursiva
		 * 
		 * TODO Solució Exercici 4.2 : equals
		 * */
		return false;
	}

	public String trobaNomEquipMajorCodi() throws Exception{ 
		/* ha de trobar el nom de lequip de la secció amb número de codi dequip més elevat. 
		 * La implementació NO pot ser recursiva. Cal llançar una excepció si la secció no té cap equip.
		 * 
		 * TODO Solució Exercici 4.3 : trobaNomEquipMajorCodi
		 * */
		return "";
	}
	
	public String toString(){
		return nom;
	}
	
}
