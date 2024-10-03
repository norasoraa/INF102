package lecture2;

import java.util.ArrayList;
import java.util.List;

public class ArrayListReverser2 implements ListReverser {

	@Override
	public <T> List<T> reverse(List<T> list) {
		List<T> revList = new ArrayList<T>();
		for(int i=list.size()-1; i>=0; i--)
			revList.add(list.get(i));
		return revList;
	}

}
