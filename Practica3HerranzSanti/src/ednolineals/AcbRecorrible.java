package ednolineals;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AcbRecorrible<E extends Comparable<E>> extends AcbEnll<E> implements Acb<E> {

	private int ordre;
	public Queue<E> cua;

	private boolean Modificat = false;

	public static final int ORDRE_ASCENDENT= 1789;
	public static final int ORDRE_DESCENDENT= -7895;

	public AcbRecorrible() {
		this(AcbRecorrible.ORDRE_ASCENDENT);
	}

	public AcbRecorrible(int ordre) {
		this.setOrdre(ordre);
	}
	public void setOrdre (int ordre) {
		if (ordre!=AcbRecorrible.ORDRE_ASCENDENT && ordre!=AcbRecorrible.ORDRE_DESCENDENT)
		throw new IllegalArgumentException("ordre: "+ ordre);
		this.ordre=ordre;
		this.cua = null;
	}
	/*
	 * prepara l’arbre per a ser recorregut en inordre. Després d’invocar
	 * aquest mètode, la invocació del mètode segInordre retornarà el primer
	 * element en Inordre de l’arbre
	 */
	public void iniInordre () {
		if(cua == null) cua = new LinkedList<E>();
		if (!cua.isEmpty()) cua.clear();
        cua = this.inordre();
        Stack<E> pila = new Stack<E>();
        if(this.ordre == AcbRecorrible.ORDRE_DESCENDENT ) {
        	while (!cua.isEmpty())
        		pila.push(cua.poll());
        	while (!pila.isEmpty())
        		cua.add(pila.pop());
        }

		Modificat = false;
	}

	/*
	 * retorna true si ja s’ha arribat al final del recorregut en inordre
	 * de l’arbre. Això és si:
	 * ? l’arbre és buit
	 * ? la darrera vegada que es va invocar segInordre aquest mètode
	 * ja va retornar el darrer element en inordre de l’arbre.
	 * Tot això és el mateix que dir que retorna true quan no té sentit
	 * invocar el mètode segInordre
	 */
	public boolean finalInordre () {
		if (this.arrel == null) return true;
		if (this.cua == null) return true;
		if (this.cua.isEmpty()) return true;
		return false;
	}

	/*retorna el següent element en Inordre, si n’hi ha.
	llença una excepció si:
	? abans d’invocar?lo no s’ha invocat el mètode iniInordre
	? la darrera vegada que es va invocar ja va retornar
	el darrer element en inordre (finalInordre retornaria true)
	? s’invoca quan entre la invocació de iniInordre i la del mètode
	s’ha produït una modificació de l’arbre, això és, s’ha fet ús del
	mètode inserir, esborrar, buidar o setOrdre */
	public E segInordre () throws ArbreException {
		if(cua == null) throw new ArbreException("No s’ha invocat el mètode iniInordre");
		if(finalInordre()) throw new ArbreException("La darrera vegada que es va invocar ja va retornar el darrer element en inordre");
		if( Modificat == true)
			 throw new ArbreException("l’arbre s’ha modificat abans d’acabar el recorregut");
		Modificat = false;
		try {
			return cua.poll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String cardinalitat() {
		if(this.arrel == null) return String.format("%d",0) ;
		return String.format("%d", this.arrel.cardinalitat());
	}

	// toString realitza un recorregut en inordre
    @Override
    public String toString() {
        String result = new String();

		 Comparable<E> c = null;
		 this.iniInordre();
		 try {
			int i=0;
			while (!this.finalInordre()) {
				c = this.segInordre();
				result += ++i +": "+ c.toString() + "\n";
			}
		} catch (ArbreException e) {
			e.printStackTrace();
		}

        return result;
    }

  	// Métodes d'un arbre de cerca binari

    public Queue<E> inordre() {
    	Queue<E> c = new LinkedList<E>();
        if (arrel != null) {
            arrel.inordre(c);
        }
        return c;
    }

    public Queue<E> preordre() {
    	Queue<E> c = new LinkedList<E>();
        if (arrel != null) {
            arrel.preordre(c);
        }
        return c;
    }

    public Queue<E> postordre() {
    	Queue<E> c = new LinkedList<E>();
        if (arrel != null) {
            arrel.postordre(c);
        }
        return c;
    }

	@Override
	public void inserir(E e) throws ArbreException {
		Modificat = true;
		this.arrel = inserirRecursiu(this.arrel, e);
	}

	private NodeA<E> inserirRecursiu(NodeA<E> a, E e) throws ArbreException {
		if(a == null) {
			a = new NodeA<E>(e);
		} else {
			int cmp = e.compareTo(a.inf);
			if (cmp == 0)
				throw new ArbreException("Repetit " + e +", un arbre de cerca binari no pot guardar elements repetits.");
			if (cmp < 0) {
				a.esq = inserirRecursiu(a.esq, e);
			} else {
				a.dret = inserirRecursiu(a.dret, e);
			}
		}
		return a;
	}

	@Override
	public boolean esborrar(E e) throws ArbreException {
        arrel = EsborrarRecursiu(arrel, (Comparable<E>)e);
		return true;
	}

    private NodeA<E> EsborrarRecursiu(NodeA<E> d, Comparable<E> c) throws ArbreException {

        if (d == null) {
            throw new ArbreException("l'element no hi és");
        } else if (c.compareTo(d.inf) < 0) {
            d.esq = EsborrarRecursiu(d.esq, c);
        } else if (c.compareTo(d.inf) > 0) {
            d.dret = EsborrarRecursiu(d.dret, c);
        } else {
            // Es una fulla
            if (d.esq == null && d.dret == null) {
                d = null; //esborra la referencia
            } else if (d.esq != null && d.dret != null) {
                // Dos fills
            	NodeA<E> node = d.dret;
            	while (node.esq != null) {
            		node = node.esq;
                }
                d.inf = node.inf;
                d.dret = node;
            } else if (d.esq == null){
                d = d.dret;  // Unic fill dret
            } else {
                d = d.esq; // Unic fill esquerre
            }
        }
        return d;
    }

	@Override
	public boolean membre(E e) throws ArbreException {
		return (MembreRecursiva(arrel, e));
	}

    private boolean MembreRecursiva(NodeA<E> d, Comparable<E> c) throws ArbreException {
        if (d == null) return false;
        if (c.compareTo(d.inf) == 0) return true;

        if (c.compareTo(d.inf) < 0)
            return (MembreRecursiva(d.esq, c));
        else if (c.compareTo(d.inf) > 0)
            return (MembreRecursiva(d.dret, c));
		return false;
    }
}
