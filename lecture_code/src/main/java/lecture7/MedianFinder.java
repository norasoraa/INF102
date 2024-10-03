package lecture7;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class MedianFinder<T extends Comparable<T>> implements IMedianFinder<T> {
	// Data invariant
	// n/2 elements in low
	// all elements in low is <= median
	
	PriorityQueue<T> low = new PriorityQueue<>(Comparator.reverseOrder());
	PriorityQueue<T> hi = new PriorityQueue<>();
	
	@Override
	public T getMedian() { //O(1)
		return hi.peek();
	}

	@Override
	public void add(T element) { //O( log n)
		if(element.compareTo(getMedian())<0) {
			low.add(element); //O(log n)
		}
		else {
			hi.add(element); //O( log n)
		}
		balance(); //O( log n)
	}

	int size() { //O(1)
		return low.size()+hi.size();
	}
	
	private void balance() { //O( log n)
		if(low.size()<size()/2) {
			low.add(hi.remove()); //O( log n)
		}
		if(low.size()>size()/2) {
			hi.add(low.remove()); //O( log n)
		}
	}
}
