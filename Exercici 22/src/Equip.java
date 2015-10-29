import ednolineals.*;

public class Equip implements Comparable{
private int codi; //identificador
private String nom;
private class Node{
Jugador jug;
Node seg;
public Node(Jugador jug, Node seguent){this.jug=jug;seg=seguent;} //fi constructor
} // fi classe privada
private Node jugadors; // seqüència enllaçada lineal dels jugadors fitxats per l’equip.
// NO TÉ NODE CAPÇALERA
private int quants; //cardinalitat de la seqüència anterior
public Equip(String nom, int codi){
/*sentències Exercici 3*/
}
public Equip(String nom, Acb jugadors, int codi){
/*sentències Exercici 3*/
}
public int getCodi(){ return codi;}
public String getNom(){ return nom;}
public int getQuantsJugadors(){ return quants;}
public void addJugador(Jugador jug) throws Exception { /*sentències
Exercici 3*/ }
public void addJugador(String nom, String nacionalitat) throws Exception { /*sentències
Exercici 3*/ }
public void remJugador(Jugador jug) throws Exception{ /*sentències Exercici 3*/}
public boolean MenorQue(Comparable c){
if (c instanceof Equip) return (codi<((Equip) c).codi);else return false; }
public boolean MajorQue(Comparable c){
if (c instanceof Equip) return (codi>((Equip) c).codi);else return false;}
@Override
public int compareTo(Object o) {
	// TODO Auto-generated method stub
	return 0;
}
} // fi classe