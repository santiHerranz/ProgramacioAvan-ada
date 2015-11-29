package domini;

public class Backtraking {

	public static void main(String[] args) {

		System.out.println("Backtracking");
		
		// Quina fitxa mou
		int fitxa[] = {2,4,6,8};//{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32};
		int solucio[] = new int[32];

		BackTotesSolucions(fitxa,20, 0);
		
	}
	
	private static void BackTotesSolucions(int [] x, int suma, int k){
		int j=0; //seleccionem el primer valor del domini
		while (j<x.length){
			x[k]=x[j]; //agafem valor
			
			// Es solució quan no queden fitxes
			if (x[0]+x[1]+x[2]+x[3]==suma) //solucio
				System.out.println("Solucio:"+ x[0]+"+"+x[1]+"+"+x[2]+"+"+x[3]+"="+ suma);
			else if (x[0]+x[1]+x[2]+x[3]<suma & k<3) //anem bé queden fitxes i es completable
				BackTotesSolucions(x, suma, k+1);
			
			x[k]=0; //treiem valor
			
			j++; //seleccionem al següent valor
		} 
	}	

}
