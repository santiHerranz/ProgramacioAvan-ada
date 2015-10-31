package ednolineals;


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

}
