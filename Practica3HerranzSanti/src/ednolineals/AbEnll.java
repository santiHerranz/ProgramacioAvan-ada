package ednolineals;

import java.util.Queue;

public class AbEnll<E> implements Ab<E> {


  protected class NodeA<K> {
	K inf;
	public NodeA<K> esq, dret;

	protected NodeA() {
		this(null);
	}
	protected NodeA(K o) {
		this(null, o,null);
	}
	protected NodeA( NodeA<K> e, K o, NodeA<K> d) {
		inf = o;
		esq = e;
		dret = d;
	}

	public int cardinalitat() {
		int cont = 1;
		if(this.esq!=null) cont += esq.cardinalitat();
		if(this.dret!=null) cont += dret.cardinalitat();
		return cont;
	}

    @Override
    public boolean equals(Object o){
        boolean iguals = false;
        if(o instanceof NodeA){
        	NodeA<K> oo = (NodeA<K>)o;
            //es suposen iguals
        	iguals = inf.equals(oo.inf);
        }
        return iguals;
    }

    //Per poder utilitzar el constructor copia
    @Override
    public Object clone(){
       NodeA<K> copia = new NodeA<K>(esq,inf,dret);
       if (esq != null)  copia.esq = (NodeA<K>) esq.clone();
       if (dret != null) copia.dret = (NodeA<K>) dret.clone();
       return copia;
    }

    // inf - esq - dre
    void preordre(Queue<K> c) {
        try {c.add(inf);} catch (Exception ex) { }
        if (esq!=null) esq.preordre(c);
        if (dret!=null) dret.preordre(c);
    }

    // esq - dret - inf
    void postordre(Queue<K> c) {
        if (esq!=null) esq.postordre(c);
        if (dret!=null) dret.postordre(c);
        try {
        	c.add(inf);
        } catch (Exception ex) { }
    }

    // esq - inf - dret
    void inordre(Queue<K> c) {
        if(esq!=null)esq.inordre(c);
        try {c.add(inf);} catch (Exception ex) { }
        if(dret!=null)dret.inordre(c);
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
	public E arrel() throws ArbreException {
		if (this.arrel == null) throw new ArbreException("L'arbre és buit");
		return this.arrel.inf;
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
