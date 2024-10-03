package lecture4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Sorting {

	public static void main(String[] args) {
		List<Integer> list = generate(10);
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		int index = Collections.binarySearch(list, 440);
		System.out.println(index);
		if(index <0)
			list.add(-index-1, 440);
		else
			list.add(index,440);
		System.out.println(list);
		
		Collections.sort(list, new TverrComp());
		System.out.println(list);
		
	}
	
	public static List<Integer> generate(int n){
		Random rnd = new Random();
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<n; i++) {
			list.add(rnd.nextInt(1000));
		}
		return list;
	}
	

}
class TverrComp implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		int tverr1 = 0;
		int tverr2 = 0;
		while(o1>0) {
			tverr1 += o1%10;
			o1 /=10;
		}
		while(o2>0) {
			tverr2 += o2%10;
			o2 /=10;
		}
		return Integer.compare(tverr1, tverr2);
	}
	
}

