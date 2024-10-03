package lecture12;

public class Pair<K,V extends Comparable<? super V>> implements Comparable<Pair<K,V>>{

	K key;
	V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(Pair<K, V> o) {
		if(this == o)
			return 0;
		return this.value.compareTo(o.value);
	}
}
