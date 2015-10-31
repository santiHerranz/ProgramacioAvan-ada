package Exercici22;
import edlineals.*;
import ednolineals.Comparable;

public class Soci implements Comparable<Soci>{
private long numeroSoci; //identificador
private String nom;
private Cua<Rebut> quotes; //conté objectes Rebut, que són els que té pendents de pagament el soci
public Soci(long numeroSoci, String nom){ 
	this.numeroSoci=numeroSoci; 
	this.nom=nom;
	quotes=new CuaEnll<Rebut>(); 
	}
public long getNumeroSoci(){return numeroSoci;}
public String getNom(){ return nom;}




public void addQuota(Rebut t){ // Class Soci.java
	/* No cal controlar la repetició de rebut 
	 * 
	 * TODO Solució Exercici 1.1: addQuota
	 * Encuar directament el rebut a la cua 
	 * Com que el mètode no pot generar excepcions, hem de fer el tractament 
	 */
	try{quotes.encuar(t);}
	catch(Exception e){}
}

public void remQuota(Rebut t) throws Exception{ // Class Soci.java
	/* Llançament d’excepció si el rebut no està al magatzem
	 * 
	 * TODO Solució Exercici 1.2: remQuota
	 * Recorregut de cerca amb cua de suport
	 * Si no es troba llança excepció
	 */ 
	
	boolean trobat = false;
	Cua<Rebut> c = new CuaEnll<Rebut>(); //Cua de support
	while (!quotes.cuaBuida()){ // Recurregut fins a l'ultim enllaçat
		Rebut aux= (Rebut)(quotes.desEncuar()); // element desencuat FORÇAR casting 
		if (aux.equals(t)) // Aplicar el mètode equals redefinit a la classe Rebut
				trobat=true; // no encuem l'element a la cua de suport
		else c.encuar(aux); // encuem tots els altres
	}
	quotes=c; // canviem la referencia
	if (!trobat) throw new Exception("No hi és");
	
}

public int quantesQuotesPendents(){ // Class Soci.java
	/* mètode consultor, el magatzem ha de quedar intacte
	 * 
	 * TODO Solució Exercici 1.3: quantesQuotesPendents 
	 * Recorregut de cerca amb cua de suport 
	 * La nova cua de suport té el mateix ordre que la cua original
	 */ 
	int cont=0;
	try{
		Cua<Rebut> c = new CuaEnll<Rebut>(); //Cua de support
		while (!quotes.cuaBuida()){ // Recurregut fins a l'ultim enllaçat
			c.encuar(quotes.desEncuar()); // encuar l'element desencuat
			cont++; // increment del contador
		}
		quotes = c; // canviem la referencia
	} catch(Exception e) { }
	return cont;
}

@Override
public boolean MenorQue(Comparable<Soci> c){
	if (c instanceof Soci) return (numeroSoci < ((Soci) c).numeroSoci);
	else return false; }
	public boolean MajorQue(Comparable<Soci> c){
	if (c instanceof Soci) return (numeroSoci > ((Soci) c).numeroSoci);
	else return false; 
}

	
	public String toString(){
		return numeroSoci +" "+ nom;
	}	
}
