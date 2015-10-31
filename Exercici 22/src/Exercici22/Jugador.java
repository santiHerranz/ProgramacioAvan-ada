package Exercici22;

import ednolineals.Comparable;

public class Jugador implements Comparable<Jugador> {
private String nom;
private String nacionalitats[]; //màxim 3

public Jugador(String nom){
	this.nom=nom;
	String []p={null,null,null}; 
	nacionalitats=p;
}
public String getNom(){ return nom;}

public void addNacionalitat(String a) throws Exception{ // Class Jugador
/* ha dafegir una nova nacionalitat al jugador. 
 * Cal controlar la repetició de nacionalitat, en cas de repetició cal llançar una excepció. 
 * També cal controlar la disposició despai dins del magatzem
 * 
 * TODO Solució Exercici 2 : addNacionalitat
 * Cercar si existeix
 * Si existeix llençar excepció
 * Cercar primera posicio buida
 * Si no hi ha llençar excepció
 * Omplir la posició amb la nacionalitat
 * */
	int posicion = this.hiEsNacionalitat(a);
	if (posicion != -1) throw new Exception("La nacionalitat ja hi és.");

	int posicioLliure = this.posicioLliure();
	if (posicioLliure == -1) throw new Exception("La nacionalitat ja està plena.");

	this.nacionalitats[posicioLliure] = a;
}

private int hiEsNacionalitat(String nom) {
	int i = 0;
	while (i < this.nacionalitats.length) {
		if (this.nacionalitats[i] == null) ++i;
		else if (this.nacionalitats[i].equals(nom)) return i;
		else ++i;
	}
	return -1;
}

private int posicioLliure() {
	int i = 0;
	while (i < this.nacionalitats.length) {
		if (this.nacionalitats[i]==null) return i;
		else ++i;
	}
	return -1;
}


@Override
public boolean MenorQue(Comparable<Jugador> c){
	if (c instanceof Jugador) return (nom.compareTo(((Jugador) c).nom)<0);
	else return false; 
}

@Override
public boolean MajorQue(Comparable<Jugador> c){
	if (c instanceof Jugador) return (nom.compareTo(((Jugador) c).nom)>0);
	else return false; 
}


public String toString(){
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append(nom);
	//stringBuilder.append(" ");
	//stringBuilder.append(String.join(",",nacionalitats));
	stringBuilder.append("\n");
	return stringBuilder.toString();
}	

} //fi classe