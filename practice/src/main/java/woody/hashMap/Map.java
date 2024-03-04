package woody.hashMap;

import java.util.Collection;
import java.util.Set;

public interface Map<K, V> {
    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    V get(K key);

    void put(K key, V value);

    V remove(K key);

    void clear();

    Set<K> keySet();

    Set<Map.Entry<K, V>> entrySet();

    interface Entry<K, V>{
        K getKey();
        V getValue();
        V setValue(V value);
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
