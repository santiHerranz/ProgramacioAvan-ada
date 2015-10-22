import java.util.Stack;
import edlineals.CuaEnll;

public class Practica2 {

	public static void main(String[] args) {

		String version = "1.0";
		System.out.println("app ver "+ version);
		
		String[] frases = {
				  ""
				, "Anul·la la lluna"
				, "Catala, a l'atac"
				, "palíndrom"
				}; 
		
		for (String frase : frases) {
			if(esPalindrom(frase)){
				System.out.format("La frase \"%s\" és palíndrom\n", frase);
			} else {
				System.out.format("La frase \"%s\" NO és palíndrom\n", frase);
			}
		}
		
		
		CuaEnll<String> cua1 = new CuaEnll<String>();
		cua1.inicialitzar();
		cua1.encuar("A1");
		cua1.encuar("B2");
		cua1.encuar("C3");

		System.out.println("Cua: "+ cua1);
		
		try {
			CuaEnll<String> cua2 = cua1.clone();
			System.out.println("Clone: "+ cua2);

			System.out.println("cua1.equals(cua2): " + cua1.equals(cua2));
			
			cua2.buidar();
			cua2.encuar("FF");
			System.out.println("cua2: "+ cua2);
			
			System.out.println("cua1.equals(cua2): " + cua1.equals(cua2));
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		

	}
	
	
	static boolean esPalindrom(String frase){

		if(frase.length()< 1) return false;

		CuaEnll<String> cua = new CuaEnll<String>();
		cua.inicialitzar();
		
		Stack<String> pila = new Stack<String>();
		
		for(int index=0; index< frase.length();index++) {
			String value = String.valueOf(frase.charAt(index));
			value = value.trim();
			value = value.replace("·", "");
			value = value.replace("'", "");
			value = value.replace("-", "");
			value = value.replace(",", "");
			value = value.toLowerCase();

			if(!value.isEmpty()){
				cua.encuar(value);
				pila.push(value);
			}
		}
		
		System.out.format("%s (%d)\n",frase, cua.quants());
		

		//System.out.println("cua.consulta "+ cua.toString());
		//System.out.println("pila.toString "+ pila.toString());		
		
		boolean result=true;
		
		String a = "", b ="";
		
		while(result && !pila.isEmpty()) {
			b = pila.pop();

			try {
				a = cua.desEncuar();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			System.out.format("%s %s \n", a, b);		
			
			if(!a.equals(b)) {
				// Calcular i mostrar quants elements té la cua
				System.out.println(cua.toString());
				result = false;
			}
		}
		
		
		return result;
		
	}
	

}