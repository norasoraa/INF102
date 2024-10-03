package lecture2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lecture1.StringListGenerator;

public class TimeListReverser {

	public static void main(String[] args) {
		List<String> list = StringListGenerator.generateStringList(1000000);
		LinkedList<String> linked_list = new LinkedList<String>(list);
		ArrayList<String> array_list = new ArrayList<String>(list);

		//TODO: add implementations here and call timeReverser()
		//ListReverser linkedImpl = null;//new LinkedListReverser();
		ListReverser arrayImpl = new ArrayListReverser();
		ListReverser arrayImpl2 = new ArrayListReverser2();
		
		//timeReverser(linked_list, linkedImpl);
		//timeReverser(array_list, arrayImpl);
		timeReverser(array_list, arrayImpl2);
	
	}
	
	public static <T> List<T> timeReverser(List<T> input, ListReverser implementation) {
		long start = System.currentTimeMillis();
		List<T> reversed = implementation.reverse(input);
		long end = System.currentTimeMillis();
		double time = (end-start)/1000.0;
		System.out.println(implementation.getClass().getName()+" reversed a list with "+input.size()+" elements in "+time+"seconds.");
		return reversed;
	}

}
