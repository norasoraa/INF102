package lecture7;

/**
 * This interface describes a simple datastructure capable of storing
 * a collection of Comparable elements and reporting the median value.
 * 
 * This Datastructure can only grow in size, no removeal of elements needs to be implemented.
 * 
 * @author Martin Vatshelle
 *
 * @param <T>
 */

public interface IMedianFinder<T extends Comparable<? super T>> {
    
    public T getMedian();
    
    public void add(T element);
    
}
