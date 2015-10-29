package ednolineals;

public interface Acb<E extends Comparable<E>> extends Ab<E> {

	/*
	 * llença una excepció si l’element que s’insereix està repetit
	 */
	public void inserir (E e) throws ArbreException;
	
	/*
	 * @return retorna true si ha trobat l'element i l'ha esborrat
	 * 		   ull!!!! retorna false en cas contrari 
	 */
	public boolean esborrar (E e) throws ArbreException;

	/*
	 * @return retorna true si l’arbre conté un element com el donat com a paràmetre
	 */
	public boolean membre (E e) throws ArbreException;
	
}
