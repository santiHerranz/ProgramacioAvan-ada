package edlineals;

public interface Cua<E> {

	/**
	 * Afegeix un element a la FI de la cua.
	 * @param  node  Node a afegir
	 * @return      void
	 */
	void encuar(E node);

	/**
	 * Desencuar: elimina l’element del CAP de la cua.
	 * @return      Node
	 */
	E desEncuar() throws Exception;

	/**
	 * Cua buida: determina si l’estructura és buida.
	 * @return      boolean
	 */
	boolean cuaBuida();

	/**
	* Inicialitzar cua: realitza la tasca constructora, creant una cua sense cap element.
	 * @return      void
	 */
	void inicialitzar();

	/**
	*	Consulta cua: operació de consulta que permet consultar l’element que hi ha al
	*	CAP de la cua, sense extreure’l. Aquesta operació no és bàsica, es pot aconseguir
	*	la mateixa funcionalitat fent crides a les operacions d’encuar i desencuar.
	 * @return      Node
	 * @throws Exception
	 */
	E consulta() throws Exception;

	/**
	* Buidar: elimina tot el contingut de la cua.
	 * @return      void
	 */
	void buidar();


}