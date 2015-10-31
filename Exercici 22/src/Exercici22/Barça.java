package Exercici22;

import edlineals.*;
import ednolineals.*;

public class Barça {
	private Acb<Soci>[]socis; 
	/* l’Acb de la primera posició emmagatzema els socis amb un número de soci acabat en 0, 
	 * el de la posició 1 els acabats en 1, ...... Els Acb’s emmagatzemen objectes Soci. */
	private Pila seccions; /* Magatzem de les diferents seccions del club */

	/*
	 * TODO Solució Exercici 5.1
	 */
	public Barça(){ // Class Barça
		socis = new Acb[10];
		for (int i=0; i<10; i++) 
			socis[i]=new AcbEnll<Soci>();
		seccions=new PilaEnll();		
	}
	/*
	 * TODO Solució Exercici 5.2
	 */
	public void addSoci(Soci p) throws Exception{ // Class Barça
		socis[(int) (p.getNumeroSoci()%10)].Inserir(p);
	}

	/*
	 * TODO Solució Exercici 5.3
	 */
	public void remSoci(Soci q) throws Exception{ // Class Barça
		socis[(int) (q.getNumeroSoci()%10)].Esborrar(q);
	}
	
	@Override
	public String toString(){
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int i=0; i< socis.length; ++i){
			stringBuilder.append("Socis["+ i +"] ");
			stringBuilder.append(" "+((AcbEnll)socis[i]).inordre().toString());
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}	
}