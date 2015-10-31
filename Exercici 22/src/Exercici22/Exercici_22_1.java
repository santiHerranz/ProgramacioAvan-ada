package Exercici22;

import edlineals.Cua;
import ednolineals.*;

public class Exercici_22_1 {

	public static void main(String[] args) throws Exception {

		Seccio seccio = new Seccio("Basquet");
		
		AcbEnll<Jugador> jugs = new AcbEnll<Jugador>();

		for(int i=1;i< 12;++i) {
			Jugador j3 = new Jugador("Pere "+i);
			j3.addNacionalitat("Catalunya");
			jugs.Inserir(j3);
		}

		Equip equip = new Equip("Primera divisió", jugs, 10);
		seccio.addEquip(equip);


		jugs = new AcbEnll<Jugador>();
		
		Jugador j1 = new Jugador("Jordi 31");
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

		Jugador j3 = null;
		for(int i=1;i< 10;++i) {
			j3 = new Jugador("Jordi "+ i);
			j3.addNacionalitat("Catalunya");
			jugs.Inserir(j3);
		}

//		Jugador j2 = new Jugador(" Jordi 5");
//		j2.addNacionalitat("Catalunya");
//		jugs.Inserir(j2);

		
		System.out.println("postordre:\n"+ jugs.postordre().toString());
		System.out.println("inordre:\n"+ jugs.inordre().toString());
		System.out.println("preordre:\n"+ jugs.preordre().toString());
		
		
		
		Equip equip2 = new Equip("Segona divisió", jugs, 20);
		seccio.addEquip(equip2);

		seccio.addEquip(new Equip("Hundred",100));
		seccio.addEquip(new Equip("Quart",44));
		seccio.addEquip(new Equip("Tercer",33));
		
		equip.addJugador("Messi", "Argentina");
		
		System.out.println(seccio);
		System.out.println(equip);
		System.out.println(equip2);
		
		equip2.remJugador(j1);
		
		System.out.println(equip2);

		System.out.println(((AcbEnll)seccio.equips).nomEquipMesJugadors());
		
		System.out.println(seccio.trobaNomEquipMajorCodi());
		
		
		Barça barça = new Barça();
		Soci deleteMe = null;
		for(int j=0; j<= 10; ++j) {
			for(int i=1; i<= 10; ++i) {
				if( j == 5 && i == 5){
					deleteMe = new Soci(10*j+i, "A"+10*j+i);
					barça.addSoci(deleteMe);
				}else {
					barça.addSoci(new Soci(10*j+i, "A"+10*j+i));
				}
			}
		}
		if (deleteMe!= null)
			barça.remSoci(deleteMe); 

		System.out.println(barça);
	}

}
