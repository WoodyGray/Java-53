package woody.hashMap;

import java.util.Collection;
import java.util.Set;

public interface Map<K, V> {
    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    V get(K key);

    V put(K key, V value);

    V remove(V value);

    void clear();

    Set<K> keySet();

    Collection<V> values();

    Set<Map.Entry<K, V>> entrySet();

    interface Entry<K, V>{
        K getKey();
        V getValue();
        V setValue();
        boolean equals(Object o);
        int hashCode();
    }

//    default V replace(K key, V value) {
//        V curValue;
//        if (((curValue = get(key)) != null) || containsKey(key)) {
//            curValue = put(key, value);
//        }
//        return curValue;
//    }

}
