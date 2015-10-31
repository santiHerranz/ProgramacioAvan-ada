package Exercici22;

public class Rebut {
	private int identificador;
	private int quantia;
	private int any;
	
	public Rebut(int identificador, int quantia, int any){
		this.identificador=identificador; this.quantia=quantia;
		this.any=any;
	}
	public int getIdentificador(){return identificador;}
	public int getQuantia(){ return quantia;}
	public int getAny(){ return any;}
	
	public boolean equals(Object c){
		if (!(c instanceof Rebut)) return false;
		Rebut v=(Rebut)c;
		return identificador==v.identificador && any==v.any && quantia==v.quantia;
	}
} //fi classe