package ednolineals;

import Exercici22.Equip;
import edlineals.Cua;
import edlineals.CuaEnll;

public class AcbEnll<E> implements Acb<E>  {

	
	//Atributs
	protected NodeA arrel; // arrel de l'arbre
	
	// Constructor
	public AcbEnll(){
		this.arrel = null;
	}
	public AcbEnll(Comparable<E> e){
		this.arrel = new NodeA();
		this.arrel.setInf(e);
	}

	// Constructor arrelador
	public AcbEnll(AcbEnll<E> a_esq, Comparable<E> e, AcbEnll<E> a_dreta ){
		this.arrel = new NodeA();
		this.arrel.setInf(e);
		this.arrel.esq = a_esq;
		this.arrel.dret = a_dreta;
	}
	

	// Métodes d'un arbre
	
	@SuppressWarnings("unchecked")
	@Override
	public Comparable<E> arrel() throws ArbreException {
		if (this.arrel == null) throw new ArbreException("L'arbre és buit");
		return (Comparable<E>) this.arrel.inf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Acb<E> fillEsquerre() throws ArbreException {
		if(arrel== null) throw new ArbreException("L'arbre és buit, no té fills!");
//		AcbEnll<E> resultat = new AcbEnll<E>();
//		resultat.arrel = this.arrel.esq;
//		return resultat;
		return (Acb<E>)arrel.esq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Acb<E> fillDret() throws ArbreException {
		if(arrel == null) throw new ArbreException("L'arbre és buit, no té fills!");
//		AcbEnll<E> resultat = new AcbEnll<E>();
//		resultat.arrel = this.arrel.dret;
//		return resultat;
		return (Acb<E>)arrel.dret;
	}

	@Override
	public boolean ArbreBuit() {
		return null == arrel;
	}

	@Override
	public void Buidar() {
		arrel = null;
	}	
	
	// RECORREGUTS	
	
	public Cua<E> preordre(){
	/* retorna sobre la cua el recorregut en preordre de l’arbre donat, es llença una
	excepció si no és possible. */
	Cua<E> cu=new CuaEnll<E>();
	// Cridem a un mètode que faci la recursivitat, pot ser ell mateix ?
		if (arrel!=null) this.preordre(cu);
		return cu;
	}
	
	public Cua<E> postordre(){
		/* retorna sobre la cua el recorregut en postordre de l’arbre donat, es llença una
		excepció si no és possible. */
		Cua<E> cu=new CuaEnll<E>();
		if (arrel!=null) this.postordre(cu);
		return cu;
	}
		
	public Cua<E> inordre (){
		/* retorna sobre la cua el recorregut en inordre de l’arbre donat, es llença una
		excepció si no és possible.*/
		Cua<E> cu=new CuaEnll<E>();
		if (arrel!=null) this.inordre(cu);
		return cu;
	}
	
	
/* preordre ()
 * 1. arrel
 * 2. fill esquerra
 * 3. fill dreta	
 */
void preordre(Cua<E> c){
	try{c.encuar((E) arrel.inf);}catch(Exception e){}
	if (arrel.esq!=null) arrel.esq.preordre(c);
	if (arrel.dret!=null) arrel.dret.preordre(c);
}

/* postordre (ascendent)
 * 1. fill esquerra
 * 2. fill dreta
 * 3. arrel 
 */
void postordre(Cua<E> c){
	if (arrel.esq!=null) arrel.esq.postordre(c);
	if (arrel.dret!=null) arrel.dret.postordre(c);
	try{c.encuar((E)arrel.inf);}catch(Exception e){}
}

/* inordre
 * 1. fill esquerra
 * 2. arrel
 * 3. dreta
 */
void inordre(Cua<E> c){
	if (arrel.esq!=null) arrel.esq.inordre(c);
	try{c.encuar((E)arrel.inf);}catch(Exception e){}
	if (arrel.dret!=null) arrel.dret.inordre(c);
}	



	/*
	 * llença una excepció si l’element que s’insereix està repetit
	 */
	@Override
	public void Inserir (Comparable<E> p) throws ArbreException{
		this.arrel = inserirRecursiu(this.arrel, p);
	}
	private NodeA inserirRecursiu(NodeA a, Comparable<E> e) throws ArbreException {
		if(a == null) {
			a = new NodeA(e);
		} else {
			if (e.MenorQue(a.inf)) {
				if(a.esq == null) a.esq = new AcbEnll<E>();
				a.esq.arrel = inserirRecursiu(a.esq.arrel, e);
			} else if (e.MajorQue(a.inf)) {
				if(a.dret == null) a.dret = new AcbEnll<E>();
				a.dret.arrel = inserirRecursiu(a.dret.arrel, e);
			} else {
				throw new ArbreException("Repetit " + e);
			}
		}
		return a;
	}
	
	/*
	 * @return retorna true si ha trobat l'element i l'ha esborrat
	 * 		   ull!!!! retorna false en cas contrari 
	 */
	@Override
	public void Esborrar (Comparable e) throws ArbreException {
		if (this.arrel==null) throw new ArbreException("L'arbre es buit");
		this.arrel=esborrarRecursiu(this.arrel,e);
		
	}
	private NodeA esborrarRecursiu( NodeA d, Comparable e) throws ArbreException {
		if (d==null) throw new ArbreException("l’element no hi és");
		else if (((Comparable)(d.inf)).MajorQue(e))
		d.esq.arrel=esborrarRecursiu(d.esq.arrel,e);
		else if (((Comparable)d.inf).MenorQue(e))
		d.dret.arrel=esborrarRecursiu(d.dret.arrel,e);
		else /*l'hem trobat*/
		if (d.esq!=null && d.dret!=null)
		{ //sabem segur que d no es null
		d.inf = BuscarMinim(d.dret);
		d.dret = EsborrarMinim(d.dret);
		}
		else if (d.esq==null && d.dret==null) d=null;
		else if (d.esq==null) d=d.dret.arrel;
		else d=d.esq.arrel;
		return d;		
	}
	
	private static Comparable BuscarMinim(AcbEnll d){
		//la d no es nul.la
		while (d.arrel.esq!=null) 
			d=d.arrel.esq;
		return (Comparable)d.arrel.inf;
	}	
		
	private static AcbEnll EsborrarMinim( AcbEnll d){
		if (d.arrel.esq==null) { d=d.arrel.dret; return d;}
		else {d.arrel.esq=EsborrarMinim(d.arrel.esq); return d;}
	}
	
	

	/*
	 * @return retorna true si l’arbre conté un element com el donat com a paràmetre
	 */
	@Override
	public boolean Membre (Comparable e) {
		return(MembreRecursiu(arrel,e));
	}
	private static boolean MembreRecursiu (NodeA d, Comparable e){
		if (d==null) return(false);
		else if (e.MenorQue((Comparable)d.getInf())) return(MembreRecursiu(d.esq.arrel,e));
		else if (e.MajorQue((Comparable)d.getInf()))
		return(MembreRecursiu(d.dret.arrel,e));
		else return(true);
		}	
	
	
	/* TODO Solució Exercici 4.1 : equipMesJugadors
	 * tornar el nom de l'equip que te més jugadors
	 * */
	public String nomEquipMesJugadors() {// Class AcbEnll.java
		return (this.equipMesJugadors()).getNom();
	}


	public Equip equipMesJugadors(){ // Class AcbEnll.java
		if (arrel == null) return null;
		/* Els equips dins de l’arbre NO estan ordenats pel nombre de jugadors de l’equip, 
		 * hi estan pel seu codi, per tant no podem usar l’ordenació entre subarbre dret 
		 * i esquerra que hi ha dins d’un ACB
		 * 
		 * TODO Solució Exercici 4.1 : equipMesJugadors 
		 * Trobar l'equip amb mes jugadors dins a l'arbre de cerca binari
		 * 1. Fer recorregut complert i obtenir quants jugadors té cada equip
		 * 1.1 començem amb l'arrel
		 * 1.2 fem recorregut inordre per exemple, es indiferent
		 * */
		Equip esqE=null, dretE=null;

		int quants=((Equip) this.arrel.inf).getQuantsJugadors();
		if (this.arrel.esq!=null)
			esqE= (Equip) this.arrel.esq.equipMesJugadors(); // crida recursiva
		if (this.arrel.dret!=null)
			dretE= (Equip) this.arrel.dret.equipMesJugadors(); // crida recursiva

		if (esqE==null && dretE==null)
			return (Equip)this.arrel.inf;
		else if (esqE==null){
			if (quants>dretE.getQuantsJugadors())
				return (Equip)this.arrel.inf;
			else return dretE;
		}
		else if (dretE==null){
		if (quants>esqE.getQuantsJugadors()) 
			return (Equip)this.arrel.inf;
		else return esqE;
		}
		
	else{ //els 3 valors, cal determinar el major
		int qdret= dretE.getQuantsJugadors(),
		qesq= esqE.getQuantsJugadors();
		if (quants>qdret && quants>qesq)
		return (Equip)this.arrel.inf;
		else if (qesq>qdret) return esqE;
		else return dretE;
		} //fi else
	}	
	
	
	
	
	/* 
	 * TODO Solució Exercici 4.3 : trobaNom
	 * */	
	public String trobaNomMajorCodi()  throws Exception { //classe AcbEnll
		if (arrel == null) throw new Exception("No hi ha equips");
		//s’ha d’anar cap a la dreta. Així aprofitem l’ordenació!!!
		if (arrel.dret==null) return ((Equip)arrel.inf).getNom();
		AcbEnll<E> aux = arrel.dret;
		while (aux.arrel.dret!=null) aux=aux.arrel.dret;
		return ((Equip)aux.arrel.inf).getNom();
	}	
	
	
	

	/* 
	 * TODO Solució Exercici 4.2 : equals
	 * */	
	public boolean equals(Object o){ // A la classe AcbEnll
		if (!(o instanceof AcbEnll)) return false;
		AcbEnll p=(AcbEnll)o;
		if (arrel==null && p.arrel== null) return true;
		if (arrel==null && p.arrel!= null || arrel!=null && p.arrel==null) return false;
		//Sabem que els 2 són diferents de null, no són buits
		if (((Exercici22.Seccio)arrel.inf).toString() != ((Exercici22.Seccio)p.arrel.inf).toString() ) return false;
		
		try {
			return ((AcbEnll)fillDret()).equals(p.fillDret()) && ((AcbEnll)fillEsquerre()).equals(p.fillEsquerre());
		} catch (ArbreException e) {
			e.printStackTrace();
		}
		return false;
	}	
	
}
