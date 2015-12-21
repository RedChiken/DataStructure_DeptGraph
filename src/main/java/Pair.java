class Pair<K extends Comparable<K>,V> implements Comparable<K>{
    K key;
    V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair{" + "key=" + key + ", value=" + value + '}';
    }

    @Override
    public int compareTo(K o) {
        return this.key.compareTo(o);
    }
}
