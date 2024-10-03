package lecture5and6;

import java.util.List;

/**
 * Implement a sorting algorithm
 * This interface will be used to test out different sorting algorithms
 * @author Martin Vatshelle
 *
 */
public interface Sorter {

	/**
	 * Sort the list
	 * @param <T> - Type of elements in the list
	 * @param list - 
	 */
	public <T extends Comparable<? super T>> void sort(List<T> list);
}
