package lecture7;

import java.util.List;
import java.util.PriorityQueue;

import lecture5and6.Sorter;

public class HeapSort implements Sorter {

	@Override
	public <T extends Comparable<? super T>> void sort(List<T> list) { //O(n log n)
		PriorityQueue<T> pq = new PriorityQueue<>(); //O(1)
		for(T t : list) { //n iterasjoner
			pq.add(t); //O(log n)
		}
		for(int i=0; i<list.size(); i++) { //n iterasjoner
			T min = pq.poll(); //O(log n)
			list.set(i, min); //O(1) i ArrayList, O(n) i LinkedList
		}
	}

}
