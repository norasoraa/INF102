package lecture5and6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort implements Sorter {

	Random rnd = new Random();
	@Override
	public <T extends Comparable<? super T>> void sort(List<T> list) { //O(n^2) expected O(n log n)
		if(list.size()<=1)
			return;
		T pivot = list.get(rnd.nextInt(list.size()));
		List<T> first = new ArrayList<>();
		List<T> pivots = new ArrayList<>();
		List<T> last = new ArrayList<>();
		for(T t : list) {
			int cmp = t.compareTo(pivot);
			if(cmp<0) {
				first.add(t);
			}
			else {
				if(cmp>0) {
					last.add(t);
				}
				else {
					pivots.add(t);
				}
			}
		}
		sort(first);
		sort(last);
		list.clear();
		list.addAll(first);
		list.addAll(pivots);
		list.addAll(last);

	}

}
