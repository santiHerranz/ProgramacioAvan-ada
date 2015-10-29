package ednolineals;

import edlineals.*;

public class NodeA<E> {
	E inf;
	public NodeA<E> esq, dret;
	
	protected NodeA() {
		this(null);
	}
	protected NodeA(E o) {
		this(null, o,null);
	}
	protected NodeA( NodeA<E> e, E o, NodeA<E> d) {
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
        	NodeA<E> oo = (NodeA<E>)o;
            //es suposen iguals
        	iguals = inf.equals(oo.inf);        	
        } 
        return iguals;
    }


    //Per poder utilitzar el constructor copia
    @Override
    public Object clone(){
       NodeA<E> copia = new NodeA<E>(this.esq,this.inf,this.dret);
       if (esq != null)  copia.esq = (NodeA<E>) esq.clone();
       if (dret != null) copia.dret = (NodeA<E>) dret.clone();
       return copia;
    }

    void preordre(Cua<E> c) {
        try {c.encuar(inf);} catch (Exception ex) { }
        if(esq!=null)esq.preordre(c);
        if(dret!=null)dret.preordre(c);
    }

    void postordre(Cua<E> c) {
        if(esq!=null)esq.postordre(c);
        if(dret!=null)dret.postordre(c);
        try {c.encuar(inf);} catch (Exception ex) { }
    }

    void inordre(Cua<E> c) {
        if(esq!=null)esq.inordre(c);
        try {c.encuar(inf);} catch (Exception ex) { }
        if(dret!=null)dret.inordre(c);
    }	
}
