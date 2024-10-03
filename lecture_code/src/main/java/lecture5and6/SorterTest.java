package lecture5and6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import lecture1.StringListGenerator;
import lecture7.HeapSort;

class SorterTest {

	static List<Sorter> toTest;
	static List<String> inputStrings;
	
	/**
	 * Initialize the different sorting algorithms you want to test
	 * This is something JUnit offer instead of constructor. 
	 */
	@BeforeAll
	static void setUpBeforeClass() {
		toTest = new ArrayList<Sorter>();
		toTest.add(new JavaSort());
		//toTest.add(new SelectionSort());
		//toTest.add(new InsertionSort());
		toTest.add(new QuickSort());
		toTest.add(new MergeSort());
		toTest.add(new HeapSort());
		inputStrings = StringListGenerator.generateStringList(1000000);
	}

	/**
	 * Makes a copy of the input so the same input is available for each test
	 */
	private ArrayList<String> getInputStringsAsArrayList(){
		ArrayList<String> list = new ArrayList<String>(inputStrings.size()); 
		list.addAll(inputStrings);
		return list;
	}

	private ArrayList<String> getInputStringsAsArrayList(int n){
		n = Math.min(n, inputStrings.size());
		ArrayList<String> list = new ArrayList<String>(n); 
		for(int i=0; i<n; i++)
			list.add(inputStrings.get(i));
		return list;
	}

	@Test
	void testSortOrder() {
		for(Sorter s : toTest) {
			System.out.println("Testing "+s.getClass());
			testSortOrder(s,getInputStringsAsArrayList());
			System.out.println("passed");
		}
	}

	private void testSortOrder(Sorter s, ArrayList<String> list) {
		CodeTimer.timeMethod(s::sort, list);
		verifyOrder(list);
	}

	@Test
	void testSortSameElements() {
		for(Sorter s : toTest) {
			testSortSameElements(s);
		}
	}

	private void testSortSameElements(Sorter s) {
		AbstractList<String> input = getInputStringsAsArrayList(1000);
		ArrayList<String> list = getInputStringsAsArrayList(1000);
		s.sort(list);
		
		assertTrue(input.containsAll(list),"Using "+ s.getClass() +" The list contains an element not in the original list");
		assertTrue(list.containsAll(input),"Using "+ s.getClass() +" The list is missing an element that was in input");
	}
	
	@Test
	void testcanHandleNull() {
		for(Sorter s : toTest) {
			List<String> input = StringListGenerator.generateStringList(10);
			input.set(5, null);
			testcanHandleNull(s,input);
		}
	}

	private void testcanHandleNull(Sorter s, List<String> list) {
		boolean gotException = false;
		try {
			s.sort(list);
		} catch (NullPointerException e) {
			gotException = true;
		}
		assertTrue(gotException,"Should throw NullpointerException when null is in the list");
	}
	
	private <T extends Comparable<? super T>> void verifyOrder(List<T> list) {
		for(int i=1; i<list.size(); i++) {
			assertTrue(list.get(i-1)!= null && list.get(i-1).compareTo(list.get(i)) <= 1, 
					list.get(i-1)+" and " + list.get(i) +" are in wrong order for index "+i);
		}
	}
}
