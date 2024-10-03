package lecture14;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CountProducts {

	public static int countProducts(int[] a) {
		int total = 0;
		
		for(int i=0; i<a.length; i++) {
			for(int j=i; j<a.length; j++) {
				int prod = a[i]*a[j];
				for(int k=0; k<a.length; k++) {
					if(a[k]==prod)
						total++;
				}
			}
		}
		
		return total;
	}

	public static int countProducts2(int[] a) {
		int total = 0;
		
		HashMap<Integer,Integer> count = new HashMap<>();
		for(int num : a)
			count.put(num,count.getOrDefault(num, 0)+1);
		
		for(int i=0; i<a.length; i++) {
			for(int j=i; j<a.length; j++) {
				int prod = a[i]*a[j];
				total += count.getOrDefault(prod, 0);
			}
		}
		
		return total;
	}

}
