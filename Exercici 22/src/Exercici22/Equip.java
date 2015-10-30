package Exercici22;
import ednolineals.*;

public class Equip implements Comparable{

	private class Node{
		Jugador jug;
		Node seg;
		public Node(Jugador jug, Node seguent){
			this.jug=jug;
			seg=seguent;
			} //fi constructor
	} // fi classe privada
	
	
	private int codi; //identificador
	private String nom;
	private Node jugadors; // seqüència enllaçada lineal dels jugadors fitxats per l’equip.
	// NO TÉ NODE CAPÇALERA
	private int quants; //cardinalitat de la seqüència anterior

	public Equip(String nom, int codi){
		/* de moment no té jugadors assignats
		 * 
		 * TODO Solució Exercici 3.1 : Constructor Equip*/
	}
	public Equip(String nom, Acb<Jugador> jugadors, int codi){
		/* els jugadors de lequip ens arriben dins dun magatzem Acb, el constructor sha 
		 * dencarregar de posar-los en el magatzem de la classe que té aquesta funcionalitat. 
		 * Som usuaris de la interfície Acb
		 * 
		 * TODO Solució Exercici 3.2 : Constructor Equip */

	}
	public int getCodi(){ return codi;}
	public String getNom(){ return nom;}
	public int getQuantsJugadors(){ return quants;}

	public void addJugador(Jugador jug) throws Exception { 		
		/* Afegeix un jugador a lequip. Cal controlar la repetició
		 * 
		 * TODO Solució Exercici 3.3 : addJugador 
		 */
		Node n = new Node(jug, jugadors);
	}
	
	public void addJugador(String nom, String nacionalitat) throws Exception {
		/* Sobrecarrega del mètode anterior. Ha de tenir la mateixa funcionalitat
		 * 
		 * TODO Solució Exercici 3.4 : addJugador 
		 */
		
		}
	public void remJugador(Jugador jug) throws Exception{
		/* Afegeix un jugador a lequip. Cal controlar la repetició
		 * 
		 * TODO Solució Exercici 3.5 : remJugador 
		 */
}
	
	public boolean MenorQue(Comparable c){
	if (c instanceof Equip) return (codi<((Equip) c).codi);else return false; }
	public boolean MajorQue(Comparable c){
	if (c instanceof Equip) return (codi>((Equip) c).codi);else return false;}

} // fi classe