package lecture5and6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertionSort implements Sorter {

	@Override
	public <T extends Comparable<? super T>> void sort(List<T> list) { //O(n^2)
		List<T> sorted = new ArrayList<T>(); //O(1)
		for(T t : list) { //n iterations
			int index = Collections.binarySearch(sorted, t); //O(log n)
			if(index<0)
				index = -index-1; //O(1)
			sorted.add(index,t); //O(n)
		}
		
		for(int i=0; i<list.size(); i++) { //O(n)
			list.set(i, sorted.get(i));
		}
	}

}
