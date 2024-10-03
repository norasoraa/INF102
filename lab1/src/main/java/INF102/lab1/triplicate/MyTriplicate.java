package INF102.lab1.triplicate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTriplicate<T extends Comparable<T>> implements ITriplicate<T> {

  @Override
  public T findTriplicate(List<T> list) {

    Collections.sort(list);

    for (int i = 2; i < list.size(); i++) {
      if (list.get(i).equals(list.get(i-1)) && list.get(i-1).equals(list.get(i-2))) {
        return list.get(i);
      }
    }
    return null;
  }

  //fra oppgavegjennomgang 
  public T fasitFindTriplicate(List<T> list) {
    Map<T, Integer> counter = new HashMap<>();
    for (T element : list) {
      if (counter.containsKey(element)) {
        counter.put(element, counter.get(element)+1);
        if (counter.get(element) >= 3) {
          return element;
        } 
      }
      else {
        counter.put(element, 1);
      }
    }
    return null;
  } 
}
