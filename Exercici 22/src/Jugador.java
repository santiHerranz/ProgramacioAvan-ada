
public class Jugador implements Comparable{
private String nom;
private String nacionalitats[]; //màxim 3
public Jugador(String nom){
this.nom=nom;
String []p={null,null,null}; nacionalitats=p;
}
public String getNom(){ return nom;}
public void addNacionalitat(String a) throws Exception{ /*sentències 2*/ }
public boolean MenorQue(Comparable c){
if (c instanceof Jugador) return (nom.compareTo(((Jugador) c).nom)<0);
else return false; }
public boolean MajorQue(Comparable c){
if (c instanceof Jugador) return (nom.compareTo(((Jugador) c).nom)>0);
else return false; }
@Override
public int compareTo(Object o) {
	// TODO Auto-generated method stub
	return 0;
}
} //fi classe