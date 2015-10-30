package Exercici22;

import ednolineals.*;

public class Exercici_22_1 {

	public static void main(String[] args) throws Exception {

		Seccio seccio = new Seccio("Basquet");
		
		AcbEnll<Jugador> jugs = new AcbEnll<Jugador>();

		for(int i=0;i< 6;++i) {
			Jugador j3 = new Jugador(" Pere "+i);
			j3.addNacionalitat("Catalunya");
			jugs.Inserir(j3);
		}

		Equip equip = new Equip("Primera divisió", jugs, 10);
		seccio.addEquip(equip);


		jugs = new AcbEnll<Jugador>();
		
		Jugador j1 = new Jugador(" Jordi 31");
		j1.addNacionalitat("Catalunya");
		j1.addNacionalitat("Alemanya");
		j1.addNacionalitat("Andorra");
		try{
			j1.addNacionalitat("Catalunya");
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		try{
			j1.addNacionalitat("Suecia");
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		jugs.Inserir(j1);

		
		for(int i=0;i< 6;++i) {
			Jugador j3 = new Jugador(" Jordi "+ i);
			j3.addNacionalitat("Catalunya");
			jugs.Inserir(j3);
		}

//		Jugador j2 = new Jugador(" Jordi 5");
//		j2.addNacionalitat("Catalunya");
//		jugs.Inserir(j2);

		
		Equip equip2 = new Equip("Segona divisió", jugs, 20);
		
		
		System.out.println(seccio);
		System.out.println(equip);
		System.out.println(equip2);
		
		
	}

}
