package ednolineals;

import Exercici22.Equip;
import edlineals.Cua;
import edlineals.CuaEnll;

public class NodeA{
	Comparable inf;
	AcbEnll esq, dret;
	
	NodeA(){this(null);}
	NodeA(Comparable o){this(o,null,null);}
	NodeA(Comparable o,AcbEnll e, AcbEnll d){
		setInf(o); esq=e; dret=d;
	}
	public Comparable getInf() {
		return inf;
	}
	public void setInf(Comparable inf) {
		this.inf = inf;
	}	
	
	

//	void preordre(Cua c){
//		try{c.encuar(inf);}catch(Exception e){}
//		if (esq!=null) esq.preordre(c);
//		if (dret!=null) dret.preordre(c);
//	}
//	void postordre(Cua c){
//		if (esq!=null) esq.postordre(c);
//		if (dret!=null) dret.postordre(c);
//		try{c.encuar(inf);}catch(Exception e){}
//	}
//	void inordre(Cua c){
//		if (esq!=null) esq.inordre(c);
//		try{c.encuar(inf);}catch(Exception e){}
//		if (dret!=null) dret.inordre(c);
//	}	

	
	

}
