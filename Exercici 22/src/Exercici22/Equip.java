package Exercici22;

import edlineals.Cua;
import ednolineals.Acb;
import ednolineals.AcbEnll;
import ednolineals.Comparable;

public class Equip implements Comparable<Equip>{
	
	
	private int codi; //identificador
	private String nom;
	
	private class Node{
		Jugador jug;
		Node seg;
		public Node(Jugador jug, Node seguent){
			this.jug=jug;
			seg=seguent;
			} //fi constructor
	} // fi classe privada

	private Node jugadors; // seqüència enllaçada lineal dels jugadors fitxats per l’equip.
	// NO TÉ NODE CAPÇALERA
	private int quants; //cardinalitat de la seqüència anterior

	/* de moment no té jugadors assignats
	 * 
	 * TODO Solució Exercici 3.1 : Constructor Equip*/
	public Equip(String nom, int codi){ // Class Equip
		this.nom = nom;
		this.codi = codi;
		jugadors = null;
		quants = 0;
	}
	
	/* els jugadors de l'equip ens arriben dins dun magatzem Acb, el constructor sha 
	 * dencarregar de posar-los en el magatzem de la classe que té aquesta funcionalitat. 
	 * Som usuaris de la interfície Acb
	 * 
	 * TODO Solució Exercici 3.2 : Constructor Equip */
	public Equip(String nom, Acb<Jugador> arbreJugadors, int codi){ // Class Equip
		this(nom, codi);
		
		if (arbreJugadors != null) {

			AcbEnll<Jugador> acb = (AcbEnll<Jugador>)arbreJugadors;
			Cua<Jugador> cua = acb.preordre();

			try{
				Jugador j = cua.desEncuar();
				while(j != null) {
					addJugador(j);
					j = cua.desEncuar();
				}
			} catch(Exception e){}
		}		
	}

	public int getCodi(){ return codi;}
	public String getNom(){ return nom;}
	public int getQuantsJugadors(){ return quants;}

	
	/* 
	 * TODO Solució Exercici 3.3 : addJugador
	 *  Esquema de cerca amb auxiliar per no perdre la referencia a la cua enllaçada
	 *  Atenció: retorna el node anterior al node trobat
	 */	
	private Node onEs(Jugador j){
		// la seqüència no és buida s’ha comprovat abans de fer la crida
	
		boolean trobat=false;
		Node aux=jugadors;
		while (!trobat & aux.seg!=null){
			trobat=aux.seg.jug.getNom().equals(j.getNom());
			if (!trobat) aux=aux.seg;
		}
		if (trobat) return aux;
		else return null;
	}	
	
	public void addJugador(Jugador jug) throws Exception { 		
		/* Afegeix un jugador a lequip. Cal controlar la repetició
		 * 
		 * TODO Solució Exercici 3.3 : addJugador 
		 */
		if (jugadors!=null && jugadors.jug.getNom().equals(jug.getNom()))
				throw new Exception("Repe");
		Node on=null;
		if (jugadors!=null) 
			on=onEs(jug); //mètode privat que afegeixo
		if (on != null)
			throw new Exception("Ja hi és");
		else { //afegim davant de tot
			jugadors = new Node(jug, jugadors);
			quants++;
		}
	}
	
	public void addJugador(String nom, String nacionalitat) throws Exception {
		/* Sobrecarrega del mètode anterior. Ha de tenir la mateixa funcionalitat
		 * 
		 * TODO Solució Exercici 3.4 : addJugador 
		 */
		Jugador jug = new Jugador(nom);
		jug.addNacionalitat(nacionalitat);
		this.addJugador(jug);
	}
	
	public void remJugador(Jugador jug) throws Exception{
		/* Afegeix un jugador a lequip. Cal controlar la repetició
		 * 
		 * TODO Solució Exercici 3.5 : remJugador 
		 */
		if (jugadors==null) throw new Exception("No hi és");
		if (jugadors.jug.getNom().equals(jug.getNom())){ // Mirar si es el primer
			jugadors=jugadors.seg;
			quants--;
			return;
		}
		Node a = onEs(jug); // Trobar el node
		if (a!=null){ 
			a.seg=a.seg.seg; // desenganxar els nodes
			quants--;
		}
		else throw new Exception("“No hi és");
	}
	

	
	
	@Override	
	public boolean MenorQue(Comparable<Equip> c){
		if (c instanceof Equip) return (codi < ((Equip)c).codi);else return false; 
	}
	
	@Override
	public boolean MajorQue(Comparable<Equip> c){
		if (c instanceof Equip) return (codi > ((Equip)c).codi);else return false;
	}

	public String toString(){
		
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(nom);
		stringBuilder.append("\n");
		stringBuilder.append("+ Jugadors ("+ this.quants +")\n");
		Node item = jugadors;
		while(item != null) {
			stringBuilder.append(" "+ item.jug);
			item = item.seg;
		}
		return stringBuilder.toString();
	}


	
} // fi classe