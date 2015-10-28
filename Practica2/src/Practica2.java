import java.util.Scanner;
import java.util.Stack;
import edlineals.CuaEnll;

public class Practica2 {

	public static void main(String[] args) throws Exception {
		
		Scanner reader = new Scanner(System.in);
		
		Stack<Character> pila = new Stack<Character>();
		CuaEnll<Character> cua = new CuaEnll<Character>();
		
		System.out.println("Entra una frase caràcter a caràcter i indica la finalització amb ‘.’:");
		char ch = reader.next().charAt(0);
		while ('.' != ch) {
			cua.encuar(ch);
			pila.push(ch);
			ch = reader.next().charAt(0);
		}
		reader.close();
		
		String frase = cua.toString();
		
		boolean esPalindrom = false;

		if (cua.quants()>1) {
			esPalindrom = true;
			while( esPalindrom  && cua.quants()>0) {
				char a , b;
				b = pila.pop();
				a = cua.desEncuar();
				if(a != b) {
					// Calcular i mostrar quants elements té la cua
					System.out.format("Queden %d elements a la cua, \"%s\"\n", cua.quants(), cua.toString());
					esPalindrom = false;
				}
			}
		}
		
		if(esPalindrom){
			System.out.format("La frase \"%s\" és palíndrom\n", frase);
		} else {
			System.out.format("La frase \"%s\" NO és palíndrom\n", frase);
		}
		
	}

}
