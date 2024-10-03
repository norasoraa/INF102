package lecture8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//we are trying to solve this problem:
//https://open.kattis.com/problems/alphabetanimals
public class AlphabetAnimal {

	public static void main(String[] args) {
		//First we need to read input
		Scanner sc = new Scanner(System.in);
		String previous = sc.next();
		int n = sc.nextInt();
		ArrayList<String> animals = new ArrayList<>();
		HashMap<Character,List<String>> startWith= new HashMap<>();
		
		for(int i=0; i<n; i++) {
			String animal = sc.next();
			animals.add(animal);
			List<String> sameFirst = startWith.getOrDefault(animal.charAt(0),new ArrayList<>());
			sameFirst.add(animal);
			startWith.put(animal.charAt(0), sameFirst);
		}

		//no longer need to read input
		sc.close();
		//here we solve the problem
		System.out.println(solve(previous, animals,startWith));

	}

	private static String solve(String previous, ArrayList<String> animals, HashMap<Character, List<String>> startWith) {
		//if we can win we prefer to to that
		String winningWord = getWinning(previous, animals, startWith);
		if(winningWord!="")
			return winningWord+"!";
		
		//if we can not win we look for the first compatible word
		for(String animal : animals) { //n iterations
			if(animal.charAt(0) == getLast(previous)) { //O(1)
				return animal;
			}
		}
		//if no valid response is found we return a ?
		return "?";
	}
	
	private static String getWinning(String previous, ArrayList<String> animals, HashMap<Character, List<String>> startWith) { //O(n^2)

		//in lecture on Monday we will see how to use HashMap
		//HashMap<Character,String> startsWith;

		for(String animal : startWith.getOrDefault(getLast(previous), new ArrayList<String>())) { //n iterations
			if(animal.equals(previous))
				continue;
			boolean isWinning = true;
			for(String animal2 : startWith.getOrDefault(getLast(animal),new ArrayList<>())){//O(1)
				if(animal.equals(animal2))
					continue;
				isWinning = false;
				break;
			}
			if(isWinning) {
				return animal;
			}
		}
		return "";
	}

	public static char getLast(String s) {
		return s.charAt(s.length()-1);
	}

}
