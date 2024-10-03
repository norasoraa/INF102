package INF102.lab3.sumList;

import java.util.List;

public class SumRecursive implements ISum {

    private long sum = 0;

    @Override
    public long sum(List<Long> list) {
        int index = list.size()-1;
        if (!list.isEmpty()) {
            this.sum += list.get(index);
            list.remove(index); 
            return sum(list);
        }
        long copysum = this.sum;
        this.sum = 0;
        return copysum;
    }

    //fra oppgavegjennomgang 
    public long fasitSum(List<Long> list) {
        if (list.isEmpty()) {
            return 0;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        int n = list.size();
        Long first = list.get(n-1);
        list.remove(n-1);
        return first + sum(list);
    }
}
