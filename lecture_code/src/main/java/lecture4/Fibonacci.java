package lecture4;

import java.util.HashMap;

public class Fibonacci {

	static HashMap<Integer,Long> memory = new HashMap<>();

	public static void main(String[] args) {
		System.out.println(fib(5));
		System.out.println(fib(42));
		System.out.println(fib(1000));

	}

	public static long fib(int n) {
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		if(memory.containsKey(n))
			return memory.get(n);
		long num = fib(n-1)+fib(n-2);
		memory.put(n, num);
		return num;
	}
}
