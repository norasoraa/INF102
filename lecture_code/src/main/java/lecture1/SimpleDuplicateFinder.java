package lecture1;

import java.util.List;

/**
 * This implementation tests all pairs in a list to see if any pair is a duplicate
 * @author Martin Vatshelle
 *
 */
public class SimpleDuplicateFinder implements DuplicateFinder<Object>{

	public <T extends Object> T findDuplicate(List<T> list) { 

		for(int i=0; i<list.size(); i++) { 
			for(int j=0; j<i; j++) {
				if(list.get(i).equals(list.get(j))) { 
					return list.get(i); 
				}
			}
		}
		return null;
	}

}
