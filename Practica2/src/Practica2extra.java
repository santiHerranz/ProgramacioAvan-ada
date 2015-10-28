import java.util.Stack;
import edlineals.CuaEnll;

public class Practica2extra {

	public static void main(String[] args) throws Exception {
		provaFrases();
		provaCues();
	}

	static void provaFrases() throws Exception{

		String[] frases = {
				  ""
				, "Anul·la la lluna"
				, "A cavall, la vaca"
				, "Catala, a l'atac"
				, "A dit, sorpres, a l'obert avenc nevat, rebo la serp rostida"
				, "A l'olla vota la tovallola "
				, "A una llarga gralla nua"
				, "Santi té petit nas"
				, "palíndrom"
				}; 
		
		for (String frase : frases) {
			if(esPalindrom(frase)){
				System.out.format("La frase \"%s\" és palíndrom\n", frase);
			} else {
				System.out.format("La frase \"%s\" NO és palíndrom\n", frase);
			}
		}
	}
	
	static void provaCues(){
		
		CuaEnll<Character> cua1 = new CuaEnll<Character>();
		cua1.inicialitzar();
		cua1.encuar('A');
		cua1.encuar('B');
		cua1.encuar('C');
		cua1.encuar('D');
		cua1.encuar('e');
		cua1.encuar('f');
		cua1.encuar('g');

		System.out.println("Cua: "+ cua1);
		
		try {
			CuaEnll<Character> cua2 = cua1.clone();
			System.out.println("Clone: "+ cua2);

			System.out.println("cua1.equals(cua2): " + cua1.equals(cua2));
			
			cua2.buidar();
			cua2.encuar('e');
			cua2.encuar('f');
			cua2.encuar('g');
			System.out.println("cua2: "+ cua2);
			
			System.out.println("cua1.equals(cua2): " + cua1.equals(cua2));
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	static boolean esPalindrom(String frase) throws Exception{

		if(frase.length()< 1) return false;

		CuaEnll<Character> cua = new CuaEnll<Character>();
		cua.inicialitzar();
		
		Stack<Character> pila = new Stack<Character>();
		
		for(int index=0; index< frase.length();index++) {
			String value = String.valueOf(frase.charAt(index));
			value = value.replace(" ", "");
			value = value.replace("·", "");
			value = value.replace("'", "");
			value = value.replace("-", "");
			value = value.replace(",", "");
			value = value.toLowerCase();

			if(!value.isEmpty()){
				cua.encuar(value.charAt(0));
				pila.push(value.charAt(0));
			}
		}
		
		System.out.format("%s (%d)\n",frase, cua.quants());

		//System.out.println("cua.consulta "+ cua.toString());
		//System.out.println("pila.toString "+ pila.toString());		
		
		boolean palindrom=true;
		
		char a = 0, b = 0;
		while(palindrom && cua.quants()>0) {
			b = pila.pop();
			a = cua.desEncuar();
			
			//System.out.format("%s %s \n", a, b);		
			
			if(a != b) {
				// Calcular i mostrar quants elements té la cua
				System.out.format("Queden %d elements a la cua, \"%s\"\n", cua.quants(), cua.toString());
				palindrom = false;
			}
		}
		return palindrom;
	}
	

}