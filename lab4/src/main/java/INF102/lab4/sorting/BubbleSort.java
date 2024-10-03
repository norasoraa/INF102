package INF102.lab4.sorting;

import java.util.List;

public class BubbleSort implements ISort {

    @Override
    public <T extends Comparable<T>> void sort(List<T> list) {
        for (int i = 0; i < list.size()-1; i++) {
            for (int j = 0; j < list.size()-i-1; j++) {
                T current = list.get(j);
                T next = list.get(j+1);
                if (current.compareTo(next) > 0) {
                    list.set(j, next);
                    list.set(j+1, current);
                }
            }
        }
    }
}
 