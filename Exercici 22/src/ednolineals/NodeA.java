package ednolineals;

class NodeA<E>{
	E inf;
	NodeA<E> esq, dret;
	NodeA(){this(null);}
	NodeA(E o){this(o,null,null);}
	NodeA(E o,NodeA<E> e, NodeA<E> d){
		inf=o; esq=e; dret=d;
	}
}
