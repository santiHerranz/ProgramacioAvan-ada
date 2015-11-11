package practica3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import ednolineals.*;

public class Practica3 {
	

	

	public static void main(String[] args) {
		System.out.println("Practica3");
		
		String classpathRoot = System.getProperty("user.dir");
		String pathFile = classpathRoot +"\\src\\Doc1.txt";
		System.out.println(pathFile);
		String content = readFile(pathFile);
//		System.out.println(content);
		
		AcbRecorrible<String> arbre = new AcbRecorrible<String>(AcbRecorrible.ORDRE_ASCENDENT);

		System.out.println("---FITXER---");

		StringTokenizer st = new StringTokenizer(content, "():.,; \t\n\r\f");
	     while (st.hasMoreTokens()) {
	         String paraula = st.nextToken();
	         String info = "Doc1: "+ paraula;
	         if(paraula.length()>=10) {
	 	 		try {
					arbre.inserir(paraula.toUpperCase());
					info += " + Afegit";
				} catch (ArbreException e) {
					info += " ! REPETIT";
					//e.printStackTrace();
				}
	         }
			//System.out.print(info+"\n");
	     }
	     
		
			 System.out.println("---ARBRE-----");
	     
			System.out.println("Paraules: "+ arbre.cardinalitat());
			System.out.print(""+ arbre.toString());
			
			 System.out.println("---EXCLUSIO----");

			pathFile = classpathRoot +"\\src\\Excl.txt";
			String exclusion = readFile(pathFile);
			
			StringTokenizer st2 = new StringTokenizer(exclusion, "():.,; \t\n\r\f");
		     while (st2.hasMoreTokens()) {
		         String paraula = st2.nextToken();
		    	 System.out.print("Excl: "+ paraula);
					try {
						boolean done = false;
						done = arbre.esborrar(paraula);
						System.out.print(" - eliminat");
					} catch (ArbreException e) {
						System.out.print(" ! NO TROBAT");
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					System.out.print("\n");
		     }

			 System.out.println("---RESULTAT---");
		     
			System.out.println("Paraules: "+ arbre.cardinalitat());

			// Establir l'ordre lexicogàfic descendent
			arbre.setOrdre(AcbRecorrible.ORDRE_DESCENDENT);

			System.out.println(""+ arbre.toString());
			
		
			 System.out.println("---EXCEPCIONS---");
			 
			 
		     // TEST: No s’ha invocat el mètode iniInordre
			 arbre = new AcbRecorrible<String>(AcbRecorrible.ORDRE_ASCENDENT);
			 Comparable<String> c = null;

			 
				 System.out.println("---EXCEP 1 ---");
		        // Inserir
				try {
					arbre.inserir("TEST1");
				} catch (ArbreException e1) {
				}
				// Inserir repetit
				try {
					arbre.inserir("TEST1");
				} catch (ArbreException e1) {
					System.out.println(e1.getMessage());
				}

				
				 System.out.println("---EXCEP 2 ---");
					try {
						arbre.segInordre();
					} catch (ArbreException e1) {
						 System.out.println("No s’ha invocat el mètode iniInordre abans del métode segInordre");
					}

					
				 System.out.println("---EXCEP 3 ---");
				//Buidar
				arbre.iniInordre();
				try {
					while (!arbre.finalInordre()) {
						c = arbre.segInordre();
					}	     
				} catch (ArbreException e) {
					System.out.println(e.getMessage());
				}
				// TODO: el recorregut ja ha finalitzat i no
				// s’ha tornat a inicialitzar invocant iniInordre
				// Solució: cridar abans  arbre.iniInordre();
				try {
					c = arbre.segInordre();
				} catch (ArbreException e1) {
					System.out.println(e1.getMessage());
				}
				
				 System.out.println("---EXCEP 4 ---");
				// TODO Excepció: l’arbre s’ha modificat abans d’acabar el recorregut
				try {
					arbre.inserir("TEST2.1");
					arbre.inserir("TEST2.2");
					arbre.inserir("TEST2.3");
					arbre.iniInordre();
					c = arbre.segInordre();
					arbre.inserir("TEST2.4");
					c = arbre.segInordre();

				} catch (ArbreException e1) {
					System.out.println(e1.getMessage());
				}
		     
			 
	}

	
	public static String readFile(String filename)
	{
	    String content = null;
	    File file = new File( filename);
	    FileReader reader = null;
	    try {
	        reader = new FileReader(file);
	        char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        content = new String(chars);
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if(reader != null){
	        	try {
	    			reader.close();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
        	}
	    }
	    return content;
	}	

}
