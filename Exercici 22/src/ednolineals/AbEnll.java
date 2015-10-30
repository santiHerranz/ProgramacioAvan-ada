package ednolineals;

import Exercici22.Equip;

public class AbEnll<E> implements Ab<E> {


	class NodeA<E>{
		E inf;
		NodeA<E> esq, dret;
		NodeA(){this(null);}
		NodeA(E o){this(o,null,null);}
		NodeA(E o,NodeA<E> e, NodeA<E> d){
			inf=o; esq=e; dret=d;
		}
	
		public Equip equipMesJugadors(){ 
			/* Els equips dins de l’arbre NO estan ordenats pel nombre de jugadors de l’equip, 
			 * hi estan pel seu codi, per tant no podem usar l’ordenació entre subarbre dret 
			 * i esquerra que hi ha dins d’un ACB
			 * 
			 * TODO Solució Exercici 4 : equipMesJugadors (AbEnll - NodeA)
			 * Crida recursiva
			 * */
			Equip esqE=null, dretE=null;

			int quants=((Equip) inf).getQuantsJugadors();
			if (esq!=null)
				esqE= (Equip) esq.equipMesJugadors();
			if (dret!=null)
				dretE= (Equip) dret.equipMesJugadors();

			if (esqE==null && dretE==null)
				return (Equip)inf;
			else if (esqE==null){
				if (quants>dretE.getQuantsJugadors())
					return (Equip)inf;
				else return dretE;
			}
			else if (dretE==null){
			if (quants>esqE.getQuantsJugadors()) 
				return (Equip)inf;
			else return esqE;
			}
			
		else{ //els 3 valors, cal determinar el major
			int qdret= dretE.getQuantsJugadors(),
			qesq= esqE.getQuantsJugadors();
			if (quants>qdret && quants>qesq)
			return (Equip)inf;
			else if (qesq>qdret) return esqE;
			else return dretE;
			} //fi else
		}
	}

	
	//Atributs
	protected NodeA<E> arrel; // arrel de l'arbre
	
	// Constructor
	public AbEnll(){
		this.arrel = null;
	}
	public AbEnll(E e){
		this.arrel = new NodeA<E>();
		this.arrel.inf = e;
	}

	// Constructor arrelador
	public AbEnll(AbEnll<E> a_esq, E e, AbEnll<E> a_dreta ){
		this.arrel = new NodeA<E>();
		this.arrel.inf = e;
		this.arrel.esq = a_esq.arrel;
		this.arrel.dret = a_dreta.arrel;
	}
	

	// Métodes d'un arbre
	
	@Override
	public Comparable<E> arrel() throws ArbreException {
		if (this.arrel == null) throw new ArbreException("L'arbre és buit");
		return (Comparable<E>) this.arrel.inf;
	}

	@Override
	public Ab<E> fillEsquerre() throws ArbreException {
		if(arrel== null) throw new ArbreException("L'arbre és buit, no té fills!");
		AbEnll<E> resultat = new AbEnll<E>();
		resultat.arrel = this.arrel.esq;
		return resultat;
	}

	@Override
	public Ab<E> fillDret() throws ArbreException {
		if(arrel == null) throw new ArbreException("L'arbre és buit, no té fills!");
		AbEnll<E> resultat = new AbEnll<E>();
		resultat.arrel = this.arrel.dret;
		return resultat;
	}

	@Override
	public boolean abBuit() {
		return null == arrel;
	}

	@Override
	public void buidar() {
		arrel = null;
	}

}
