package lecture1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashDuplicateFinder implements DuplicateFinder<Object> {

	@Override
	public <T> T findDuplicate(List<T> list) {
		//create a set to keep all items seen so far
		Set<T> set = new HashSet<T>(list.size());

		for(T item : list) {
			if(set.contains(item))
				return item;
			else
				set.add(item);
		}
		return null;
	}
}