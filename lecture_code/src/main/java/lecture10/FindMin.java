package lecture10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FindMin<T extends Comparable<T>> {

	List<T> findMin(List<T> elements, int m){ //O(m*n)
		List<T> smallest = new ArrayList<T>();
		for(int i=0; i<m; i++) { //m iterasjoner
			T min = Collections.min(elements); //O(n)
			smallest.add(min);
			elements.remove(min);
		}
		return smallest;
	}

	List<T> findMin2(List<T> elements, int m){ //O(n +m*log n) 
		List<T> smallest = new ArrayList<T>();
		PriorityQueue<T> heap = new PriorityQueue<>(elements); //O(n)
		for(int i=0; i<m; i++) { //m iterasjoner
			T min = heap.remove(); //O(log n)
			smallest.add(min);	//O(1)
		}
		return smallest;
	}

}
