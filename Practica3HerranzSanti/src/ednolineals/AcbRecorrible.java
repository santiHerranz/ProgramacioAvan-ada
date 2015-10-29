package ednolineals;

import java.util.Queue;
import edlineals.*;

public class AcbRecorrible<E extends Comparable<E>> extends AbEnll<E> implements Acb<E> {

	private int ordre;
	private Cua<String> cua;
	
	public static final int ORDRE_ASCENDENT= 1789;
	public static final int ORDRE_DESCENDENT= -7895;
	
	public AcbRecorrible() {
		this(AcbRecorrible.ORDRE_ASCENDENT);
	}
	
	public AcbRecorrible(int ordre) {
		this.setOrdre(ordre);
	}
	public void setOrdre (int ordre) {
		if (ordre!=AcbRecorrible.ORDRE_ASCENDENT &&
		ordre!=AcbRecorrible.ORDRE_DESCENDENT)
		throw new IllegalArgumentException("ordre: "+ ordre);
		this.ordre=ordre;
		this.cua=null;
	}
	/*
	 * prepara l�arbre per a ser recorregut en inordre. Despr�s d�invocar
	 * aquest m�tode, la invocaci� del m�tode segInordre retornar� el primer
	 * element en Inordre de l�arbre
	 */
	public void iniInordre () {
		
	}

	/*
	 * retorna true si ja s�ha arribat al final del recorregut en inordre
	 * de l�arbre. Aix� �s si:
	 * ? l�arbre �s buit
	 * ? la darrera vegada que es va invocar segInordre aquest m�tode
	 * ja va retornar el darrer element en inordre de l�arbre.
	 * Tot aix� �s el mateix que dir que retorna true quan no t� sentit
	 * invocar el m�tode segInordre     
	 */
	public boolean finalInordre () {
		return false;
		
	}

	public E segInordre () throws ArbreException {
		return null;
		
	}
	/*retorna el seg�ent element en Inordre, si n�hi ha.
	llen�a una excepci� si:
	? abans d�invocar?lo no s�ha invocat el m�tode iniInordre
	? la darrera vegada que es va invocar ja va retornar
	el darrer element en inordre (finalInordre retornaria true)
	? s�invoca quan entre la invocaci� de iniInordre i la del m�tode
	s�ha produ�t una modificaci� de l�arbre, aix� �s, s�ha fet �s del
	m�tode inserir, esborrar, buidar o setOrdre */	




	public String cardinalitat() {
		if(this.arrel == null) return String.format("%d",0) ;
		return String.format("%d", this.arrel.cardinalitat());
	}

	// toString realitza un recorregut en inordre
    @Override
    public String toString() {
        String result = new String();
        Cua<E> c = this.inordre();
        while (!c.cuaBuida()) {
            try {
				result += ((E)c.desEncuar()).toString() + "\n";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
        }
        return result;
    }
	
    
    
    
    
    public Cua<E> inordre() {
        Cua<E> c = new CuaEnll<E>();
        if (arrel != null) {
            arrel.inordre(c);
        }
        return c;
    }    
    
    
    
    
	
	// M�todes d'un arbre de cerca binari
	
    public Cua<E> preordre() {
    	Cua<E> c = new CuaEnll<E>();
        if (arrel != null) {
            arrel.preordre(c);
        }
        return c;
    }	

	@Override
	public void inserir(E e) throws ArbreException {
		this.arrel = inserirRecursiu(this.arrel, e);
	}
	private NodeA<E> inserirRecursiu(NodeA<E> a, E e) throws ArbreException {
		if(a == null) {
			a = new NodeA<E>(e);
		} else {
			int cmp = e.compareTo(a.inf);
			if (cmp == 0) throw new ArbreException("Repetit " + e);
			if (cmp < 0) {
				a.esq = inserirRecursiu(a.esq, e);
			} else {
				a.dret = inserirRecursiu(a.dret, e);
			}
		}
		return a;
	}
	

	@Override
	public boolean esborrar(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean membre(E e) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
