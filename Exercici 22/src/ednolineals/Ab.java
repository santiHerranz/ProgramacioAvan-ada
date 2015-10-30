package ednolineals;

public interface Ab<E> {

	/*
	 * cal llençar una excepció si es demana l’arrel d’un arbre buit
	 */
	Comparable<E> arrel () throws ArbreException;

	/*
	 * Exception si l’arbre this és buit. Si no té fill esquerre retorna un arbre buit.
	 */
	Ab<E> fillEsquerre()throws ArbreException;

	/*
	 * Excepcion si l’arbre this és buit. Si no té fill dret retorna una arbre buit.
	 */
	Ab<E> fillDret()throws ArbreException;

	/*
	 * 
	 */
	boolean abBuit();

	/*
	 * 
	 */
	void buidar();
}
