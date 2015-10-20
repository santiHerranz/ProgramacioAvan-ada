
/**
 * @author Developer
 *
 * @param <E>
 */
public class CuaEnll<E> implements Cua<E> {

	private Node<E> fi;
	
	CuaEnll(){
		super();
		this.inicialitzar();
	}	
	
	@Override
	public void encuar(E value) {

		//System.out.println(value);
		
		Node<E> node = new Node<E>(value);

		if(fi == null){
			this.fi = node;
			this.fi.setSeguent(node); //Referencia circular
		} else {
			node.setSeguent(this.fi.getSeguent());
			this.fi.setSeguent(node);
			this.fi = node;
		}
	}

	@Override
	public E desEncuar() throws Exception {
		if(cuaBuida() == true) throw new Exception("La cua està buida");
		
		Node<E> node = this.fi.getSeguent();
		E value = node.inf;

		if(!this.fi.equals(this.fi.seg))
			this.fi.setSeguent(node.getSeguent());
		else
			this.fi = null;
		
		return value;
	}

	@Override
	public boolean cuaBuida() {
		return (this.fi == null);
	}

	@Override
	public void inicialitzar() {
		if(!cuaBuida()) buidar();
		this.fi = null;
	}

	@Override
	public E consulta() throws Exception {
		if(cuaBuida() == true) throw new Exception("La cua està buida");
		return fi.getSeguent().getValue();
	}

	@Override
	public void buidar() {

		while(!cuaBuida()) {
			try {
				desEncuar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	/*
	 * Metode que calcula i retorna el nombre d’elements que té la Cua
	 * la implementació ha de ser recursiva
	 */
	public int quants(){
		//TODO
		return 0;
	}
	
	public Object clone(){
		return fi;
		
	}
	
/*
 * dues cues són iguals si les seves corresponents seqüències enllaçades fan referència 
 * a objectes iguals (equals) i en el mateix ordre.
 */
	public boolean equals(Object o) {
		//TODO
		return false;
	}
	
	public String toString() {
		
		StringBuilder aux = new StringBuilder();

		if(this.fi == null) return "";

		if(this.fi.getSeguent().equals(this.fi))
			aux.append(this.fi.getValue().toString());
		else {
			Node<E> item = this.fi.getSeguent();
			while(!item.equals(this.fi)){
				aux.append(item.getValue().toString());
				item = item.getSeguent();
			}
			aux.append(item.getValue().toString());
		}

		return aux.toString();
	}

	
	private class Node<K> {
		private K inf;
		private Node<K> seg;

		Node(K data){
			this.inf = data;
		}
		public Node<K> getSeguent() {
			return seg;
		}
		public void setSeguent(Node<K> seguent) {
			this.seg = seguent;
		}
		public K getValue() {
			return inf;
		}
	}


}
