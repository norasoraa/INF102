package lecture5and6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MergeSort implements Sorter {

	@Override
	public <T extends Comparable<? super T>> void sort(List<T> list) { //O(n log n)
		if(list.size()<=1)
			return;
		//divide
		int mid = list.size()/2; //O(1)
		ArrayList<T> first = new ArrayList<>(list.subList(0, mid)); //O(n)
		ArrayList<T> second = new ArrayList<>(list.subList(mid, list.size())); //O(n)

		//recurse
		sort(first);
		sort(second);

		//conquer
		ArrayList<T> listToMerge = new ArrayList<T>(list); //O(n)
		merge(new LinkedList<T>(first),new LinkedList<T>(second),listToMerge); //O(n)
		list.clear(); //O(1)
		for(T t : listToMerge) //O(n)
			list.add(t); //O(1)
	}

	private <T extends Comparable<? super T>> void merge(LinkedList<T> first, LinkedList<T> second, ArrayList<T> list) {
		for(int i=0; i<list.size(); i++) { //n iterasjoner O(n)
			if(second.isEmpty() || (!first.isEmpty() && first.get(0).compareTo(second.get(0))<0)) { //O(1)
				list.set(i, first.get(0)); //O(1)
				first.remove(0); //O(1)
			}
			else {
				list.set(i, second.get(0)); //O(1)
				second.remove(0); //O(1)
			}
		}
	}

}
