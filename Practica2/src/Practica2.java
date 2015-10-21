import java.util.Stack;

public class Practica2 {

	public static void main(String[] args) {

		String version = "1.0";
		System.out.println("app ver "+ version);
		
		String[] frases = {"s"
				, "S� com no �s"
				, "S�n lletres que es repeteixen en el mateix ordre quan s�n llegides en la direcci� inversa"
				, "Anul�la la lluna"
				, "Catala, a l'atac"
				, "Roma tibi subito motibus ibit amor"
				, "S� on no �s"
				}; 
		
		for (String frase : frases)
			if(esPalindrom(frase)){
				System.out.format("La frase \"%s\" �s pal�ndrom\n", frase);
			} else {
				System.out.format("La frase \"%s\" NO �s pal�ndrom\n", frase);
			}
	}
	
	
	static boolean esPalindrom(String frase){

		if(frase.length()< 2) return false;

		CuaEnll<String> cua = new CuaEnll<String>();
		cua.inicialitzar();
		
		Stack<String> pila = new Stack<String>();
		
		for(int index=0; index< frase.length();index++) {
			String value = String.valueOf(frase.charAt(index));
			value = value.trim();
			value = value.replace("�", "");
			value = value.replace("'", "");
			value = value.replace("-", "");
			value = value.replace(",", "");
			value = value.toLowerCase();

			if(!value.isEmpty()){
				cua.encuar(value);
				pila.push(value);
			}
		}

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
			
			//System.out.format("%s %s \n", a, b);		
			
			if(!a.equals(b)) {
				// Calcular i mostrar quants elements t� la cua
				System.out.println(cua.toString());
				result = false;
			}
		}
		
		
		return result;
		
	}
	

}
