package lecture10;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreeSum {

	public static void main(String[] args) {
		int n=2000;
		List<Integer> numbers = generateOddNumbers(n,1000*n);
		long start = System.currentTimeMillis();
		threeSum(numbers);
		long time = System.currentTimeMillis()-start;
		System.out.println("Took: "+time+"ms");
	}

	public static List<Integer> generateEvenNumbers(int n,int max) {
		Random rnd = new Random();
		List<Integer> numbers = new ArrayList<>();
		for(int i=0; i<n; i++) {
			numbers.add((rnd.nextInt(max)-max/2)*2);
		}
		return numbers;
	}

	public static List<Integer> generateOddNumbers(int n,int max) {
		Random rnd = new Random();
		List<Integer> numbers = new ArrayList<>();
		for(int i=0; i<n; i++) {
			numbers.add((rnd.nextInt(max)-max/2)*2+1);
		}
		return numbers;
	}

	private static void threeSum(List<Integer> numbers) {
		int n = numbers.size();
		for(int i=0; i<n; i++) {
			for(int j=i+1;j<n;j++) {
				for(int k=j+1; k<n; k++) {
					if(numbers.get(i)+numbers.get(j)+numbers.get(k)==0) {
						System.out.println(numbers.get(i)+"+"+numbers.get(j)+"+"+numbers.get(k)+" = 0");
						return;
					}
				}
			}
		}
		System.out.println("No triplet sums to 0");
	}

}
