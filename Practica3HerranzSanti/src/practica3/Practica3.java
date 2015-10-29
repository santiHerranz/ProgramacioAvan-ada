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
		
		StringTokenizer st = new StringTokenizer(content, "():.,; \t\n\r\f");
	     while (st.hasMoreTokens()) {
	         String paraula = st.nextToken();
	    	 //System.out.println(paraula);
	         if(paraula.length()>=10) {
	 	 		try {
					arbre.inserir(paraula.toUpperCase());
				} catch (ArbreException e) {
					//e.printStackTrace();
				}
	         }
	     }
		
			System.out.println("Paraules: "+ arbre.cardinalitat());
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
