package lecture3;

import java.util.ArrayList;

public class BigOAnalysis {

	//n er lengden av numbers
	public static void useList(ArrayList<Integer> numbers) { //O(n) 
		int a = numbers.get(0); //O(1)
		int b = numbers.get(1); //O(1)
		int sum = a+b; //O(1)
		numbers.add(0,sum); //O(n)
	}
	
	public static void order1(int a, int b) { //O(1)
		int sum = a + b; //O(1)
		System.out.println("Summen av "+a+" + "+b+" er "+sum); //O(1)
	}
	
	public static void loopDeLoop(int n) { //O(n)
		int step =0;
		for(int i=0; i<n; i++) { //O(n) iterasjoner 
			System.out.println("Loop de loop, flip flop.");  //O(1)
			System.out.println("Flying in an aeroplane."); //O(1)
			step++; //O(1)
		}
		System.out.println(step);
	}

	public static void whatIf(int n) { //O(n)
		if(n%2==0) {
			loopDeLoop(n); //O(n)
		}
		else {
			order1(n, n); //O(1)
		}		
	}

	public static void whatIf2(int n) { //O(1)
		if(n<=10) {
			loopDeLoop(n);//O(1)
		}
		else {
			order1(n, n); //O(1)
		}		
	}

	public static void recursivePrint(int n) { //O(n)
		if(n==1) {
			System.out.print(1); //O(1)
		}
		else {
			System.out.print(n+" "); //O(n) times O(1) each
			recursivePrint(n-1);//O(n) times O(1) each
		}
	}

	public static String StringOperation(int n) { //O(n^2)
		String tekst = "Hello"; //O(1)
		
		for(int i=0; i<n; i++) {
			tekst = tekst+"!"; //O(5+6+7+8+....+(n+4)+(n+5)) ca. n/2*n = O(n^2)
		}
		return tekst+"!";
	}

	public static String StringOperation2(int n) { //O(n)
		String tekst = "Hello"; //O(1)
		StringBuffer sb = new StringBuffer(tekst);
		
		for(int i=0; i<n; i++) { //O(n) iterations
			sb = sb.append("!"); //O(1)
		}
		return sb.toString()+"!";
	}

}
