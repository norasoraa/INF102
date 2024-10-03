package lecture2;

import java.util.ArrayList;
import java.util.List;

public class ArrayListReverser implements ListReverser {

	@Override
	public <T> List<T> reverse(List<T> list) {
		List<T> revList = new ArrayList<T>();
		for(T t : list)
			revList.add(0,t);
		return revList;
	}

}
