import edlineals.*;

public class Soci implements Comparable{
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

public void addQuota(Rebut t){ 
	/*No cal controlar la repetició de rebut*/
	quotes.encuar(t);
}

public void remQuota(Rebut t) throws Exception{
/*Llançament d’excepció si el rebut no està al magatzem*/ 
	boolean trobat = false;
	
	Node n = quotes.consulta();
	while(!trobat && n != null){
		if(n.inf.equals(t)) trobat = true;
		n = n.seg;
	}
	if (!trobat) throw new Exception("");
	
}

public int quantesQuotesPendents(){
/*mètode consultor, el magatzem ha de quedar intacte */ 
	return 0;
}

public boolean MenorQue(Comparable c){
	if (c instanceof Soci) return (numeroSoci < ((Soci) c).numeroSoci);
	else return false; }
	public boolean MajorQue(Comparable c){
	if (c instanceof Soci) return (numeroSoci > ((Soci) c).numeroSoci);
	else return false; 
}

@Override
public int compareTo(Object o) {
	// TODO Auto-generated method stub
	return 0;
}
} // fi classe