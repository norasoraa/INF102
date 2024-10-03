package lecture5and6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectionSort implements Sorter {

	@Override
	public <T extends Comparable<? super T>> void sort(List<T> list) { //O(n^2)
		List<T> sorted = new ArrayList<>(list.size()); //O(n)
		while(!list.isEmpty()) { //n iterasjoner O(n^2)
			T min = Collections.min(list); //O(n)
			sorted.add(min); //O(1)
			list.remove(min); //O(n)
		}
		list.addAll(sorted); //O(n)

	}

}
