package INF102.lab3.peakElement;

import java.util.List;

public class PeakRecursive implements IPeak {

    @Override
    public int peakElement(List<Integer> numbers) {
        int index = numbers.size()/2;
        int prev = numbers.get(index-1);
        int current = numbers.get(index);
        int next = numbers.get(index+1);
        int lastIndex = numbers.size()-1;
        if (numbers.isEmpty()) {
            return 0;
        }
        else if (numbers.get(0) > numbers.get(1)) {
            return numbers.get(0);
        }
        else if (numbers.get(lastIndex) > numbers.get(lastIndex-1)) {
            return numbers.get(lastIndex);
        }
        else if (isPeak(prev, current, next)) {
            return numbers.get(index);
        }
        else if (numbers.get(index) < numbers.get(index+1)) {
            List<Integer> subList = numbers.subList(index, lastIndex);
            return peakElement(subList);
        }
        else if (numbers.get(index) < numbers.get(index-1)) {
            List<Integer> subList = numbers.subList(0, index);
            return peakElement(subList);
        }
        return 0;
    }

    private boolean isPeak(int prev, int current, int next) {
        return current >= prev && current >= next;
    }

    //fra oppgavegjennomgang 
    public int fasitPeakElement(List<Integer> numbers) {
        if (numbers.isEmpty())
            throw new IllegalArgumentException("Empty list");

        // Edge cases
        int n = numbers.size();
        if (n == 1) {
            return numbers.get(0);
        }
        if (numbers.get(0) > numbers.get(1)) {
            return numbers.get(0);
        }
        if (numbers.get(n-1) > numbers.get(n-2)) {
            return numbers.get(n-1);
        }
        return fasitPeakElement(numbers, 1, n-2);
    }

    //fra oppgavegjennomgang 
    private int fasitPeakElement(List<Integer> numbers, int left, int right) {
        int middleIndex = (left+right)/2;
        int prev = numbers.get(middleIndex-1);
        int middle = numbers.get(middleIndex);
        int next = numbers.get(middleIndex+1);

        if (isPeak(prev, middle, next)) {
            return middle;
        }
        if (middleIndex-1 >= 0 && prev > middle) {
            return fasitPeakElement(numbers, left, middleIndex-1);
        }
        if (middleIndex+1 < numbers.size() && next > middle) {
            return fasitPeakElement(numbers, middleIndex+1, right);
        }
        throw new IllegalArgumentException("No peak element");
    }
}
