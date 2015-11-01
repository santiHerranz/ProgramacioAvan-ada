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
	    	 System.out.print("Doc1: "+ paraula);
	         if(paraula.length()>=10) {
	 	 		try {
					arbre.inserir(paraula.toUpperCase());
					System.out.print(" + Afegit");
				} catch (ArbreException e) {
					System.out.print(" ! REPETIT");
					//e.printStackTrace();
				}
	         }
			System.out.print("\n");
	     }
	     
//	     // TEST: No s’ha invocat el mètode iniInordre
//	     try {
//			arbre.segInordre();
//		} catch (ArbreException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
			 System.out.println("---ARBRE-----");
	     
			System.out.println("Paraules: "+ arbre.cardinalitat());
			System.out.print(""+ arbre.toString());
			
			 System.out.println("---EXCLUSIO----");

			pathFile = classpathRoot +"\\src\\Excl.txt";
			String exclusion = readFile(pathFile);
			
			StringTokenizer st2 = new StringTokenizer(exclusion, "():.,; \t\n\r\f");
		     while (st2.hasMoreTokens()) {
		         String paraula = st2.nextToken();
		    	 System.out.println("Excl: "+ paraula);
					try {
						arbre.esborrar(paraula);
					} catch (ArbreException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
		     }

			 System.out.println("---RESULTAT---");
		     
			System.out.println(""+ arbre.toString());
			
		
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
