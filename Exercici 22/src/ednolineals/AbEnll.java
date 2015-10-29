package ednolineals;

public class AbEnll<E> implements Ab<E> {

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
