package Exercici2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Caixa {

	List<Polvoro> contingut;
	
	Caixa(int n){
	   if (n <= 0)
		     throw new IllegalArgumentException("n ha de ser positiu");

	   Random rand = new Random(System.currentTimeMillis()); // would make this static to the class    
	   
		contingut = new ArrayList<Polvoro>();
		
		for(int i = 0; i < n; i++)
			contingut.add(new Polvoro(Math.abs(rand.nextInt())%8));
	}
	
	boolean esAvorrida() {
		int[] numbers = {2,2,3};
		
		int repetit = getOcurrence(2,numbers,0,numbers.length-1);
		
		return (repetit > numbers.length/2);
	}
	
	int getOcurrence(int k, int[] numbers, int startIndex, int endIndex) {
		if(endIndex<startIndex)
		return 0;
		if(numbers[startIndex]>k)
		return 0;
		if(numbers[endIndex]>k)
		return 0;
		if(numbers[startIndex]==k && numbers[endIndex]==k)
		return endIndex-startIndex + 1;
		
		int midInd = (startIndex+endIndex)/2;
		if (numbers[midInd]==k)
		return 1 + getOcurrence(k, numbers, startIndex, midInd-1) + getOcurrence(k, numbers, midInd+1, endIndex);
		else if (numbers[midInd]>k)
			return getOcurrence(k, numbers, startIndex, midInd-1);
		else
			return getOcurrence(k, numbers, midInd+1, endIndex);
	}
	
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("("+ contingut.size() + ")\n");
		for(Polvoro p: contingut)
			sb.append(p.tipus +",");
		
		return sb.toString();
	}
	
}
