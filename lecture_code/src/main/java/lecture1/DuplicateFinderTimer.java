package lecture1;


import java.util.List;

/**
 * This class takes the time of a 
 * @author Martin Vatshelle
 *
 */
public class DuplicateFinderTimer {
	
	public static void main(String[] args) {
		DuplicateFinder<Object> df1 = new SimpleDuplicateFinder();
		test(df1,10000);
		DuplicateFinder<String> df2 = new FastDuplicateFinder<String>();
		test(df2,1000000);
//		DuplicateFinder<Object> df3 = new HashDuplicateFinder();
//		test(df3,10000);
		
	}

	/**
	 * Generates a list and times the execution of the findDuplicate on a list of Strings
	 * @param finder - the implementation to time
	 */
	public static void test(DuplicateFinder<? super String> finder,int n) {
		List<String> list = StringListGenerator.generateStringList(n);
		test(finder,list);
		list = StringListGenerator.generateStringList(n);
		list.set(n-1,"Martin");
		list.set(n/2,"Martin");
		test(finder,list);
	}

	/**
	 * Times the execution of the findDuplicate on a list of Strings
	 * @param finder
	 * @param list
	 */
	public static <T> void test(DuplicateFinder<? super T> finder, List<T> list) {
		long start = System.currentTimeMillis();
		T duplicate = finder.findDuplicate(list);
		long stop = System.currentTimeMillis();
		System.out.println(finder.getName()+" took "+(stop-start)+" ms to search list with "+ list.size()+" elements and find "+duplicate);
	}



}
