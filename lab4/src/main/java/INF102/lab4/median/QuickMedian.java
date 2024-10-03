package INF102.lab4.median;

import java.util.ArrayList;
import java.util.List;

public class QuickMedian implements IMedian {

    @Override
    public <T extends Comparable<T>> T median(List<T> list) {
        List<T> listCopy = new ArrayList<>(list); // Method should not alter list
        int midIndex = listCopy.size()/2;
        int low = 0;
        int high = listCopy.size()-1;
        return partition(listCopy, low, high, midIndex);
    }
    
    private <T extends Comparable<T>> T partition(List<T> list, int low, int high, int mid) {
        int pivotIndex = mid;
        pivotIndex = partitionAroundPivot(list, low, high, pivotIndex);
        if (mid == pivotIndex) {
            return list.get(mid);
        } else if (mid < pivotIndex) {
            return partition(list, low, pivotIndex - 1, mid);
        } else {
            return partition(list, pivotIndex + 1, high, mid);
        }
    }
    
    private <T extends Comparable<T>> int partitionAroundPivot(List<T> list, int low, int high, 
    int pivotIndex) {
        T pivotValue = list.get(pivotIndex);
        int newPivotIndex = low;
        // Move the pivot to the end
        swap(list, pivotIndex, high);
        for (int i = low; i < high; i++) {
            if (list.get(i).compareTo(pivotValue) < 0) {
                swap(list, i, newPivotIndex);
                newPivotIndex++;
            }
        }
        // Move the pivot to the right position
        swap(list, newPivotIndex, high);
        return newPivotIndex;
    }
    
    private <T extends Comparable<T>> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    
    // Source: GeeksforGeeks. (2023, January 10). Quickselect Algorithm. Retrieved September 11, 2023, from https://www.geeksforgeeks.org/quickselect-algorithm/.
}

