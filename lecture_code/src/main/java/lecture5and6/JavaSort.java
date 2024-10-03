package lecture5and6;

import java.util.Collections;
import java.util.List;

import lecture1.StringListGenerator;

public class JavaSort implements Sorter {

	public static void main(String[] args) {
		JavaSort sorter = new JavaSort();
		List<String> inputStrings = StringListGenerator.generateStringList(100000);
		CodeTimer.timeMethod(sorter::sort, inputStrings);
		CodeTimer.timeMethod(sorter::sort, inputStrings);
	}
	
	@Override
	public <T extends Comparable<? super T>> void sort(List<T> list) {
		Collections.sort(list);
	}

}
