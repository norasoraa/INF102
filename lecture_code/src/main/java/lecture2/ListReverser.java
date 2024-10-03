package lecture2;

import java.util.List;

public interface ListReverser {

	/**
	 * This method should make a copy of the given list in reversed order.
	 * The input list should not be changed
	 * @param <T>
	 * @param list
	 * @return
	 */
	public <T> List<T> reverse(List<T> list);
}
