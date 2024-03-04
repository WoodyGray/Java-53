package woody.hashMap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V> {
    static final int DEFAULT_CAPACITY = 16;
    static final float LOAD_FACTOR = 0.75f;
    int realCapacity;

    Node<K, V>[] table;
    Set<K> keySet;
    Set<Entry<K, V>> entrySet;
    int size;



    public HashMap() {
        this.realCapacity = DEFAULT_CAPACITY;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(K key) {
        for (K k: keySet
             ) {
            if (k == key || k.equals(key))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (Entry<K, V> e: entrySet
             ) {
            if (value.equals(e.getValue()))
                return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {
        if (table == null){
            table = new Node[DEFAULT_CAPACITY];
            realCapacity = DEFAULT_CAPACITY;
            int i = (realCapacity - 1) & key.hashCode();
            table[i] = new Node<>(key, value);
            keySet = new HashSet<>();
            entrySet = new HashSet<>();
            entrySet.add(table[i]);
            keySet.add(key);
            size = 1;
        }else {
            int i = (realCapacity - 1) & key.hashCode();
            Node<K, V> node = table[i];
            if (node == null){
                table[i] = new Node<>(key, value);
            }else {
                boolean isUpdate = false;
                while (node != null){
                    if (node.hash == key.hashCode() &&
                            (key == node.key || key.equals(node.key))) {
                        node.setValue(value);
                        isUpdate = true;
                        break;
                    }
                    node = node.next;
                }
                if (!isUpdate) {
                    node.next = new Node<>(key, value);
                    keySet.add(key);
                    entrySet.add(node.next);
                }
                size++;
            }
        }
        if (size >= realCapacity * LOAD_FACTOR){
            resize();
        }
    }

    private void resize(){
        Node<K, V>[] oldTab = table;
        int oldCap = oldTab.length;
        int newCap = oldCap << 1;
        table = new Node[newCap];

        for (int i = 0; i < oldCap; i++) {
            Node<K, V> node = oldTab[i];
            if (node != null){
                Node<K,V> loHead = null, loTail = null;
                Node<K,V> hiHead = null, hiTail = null;
                Node<K,V> next;

                while (node != null){
                    if ((node.hash & oldCap) == 0){
                        if (loTail == null)
                            loHead = node;
                        else
                            loTail.next = node;
                        loTail = node;
                    }else {
                        if (hiTail == null)
                            hiHead = node;
                        else
                            hiTail.next = node;
                        hiTail = node;
                    }
                    node = node.next;
                }

                table[i] = loHead;
                table[i + oldCap] = hiHead;
            }
        }
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < realCapacity; i++) {
            Node<K, V> node = table[i];
            Node<K, V> previousNode;
            V oldValue;
            if (node != null){
                while (node != null){
                    previousNode = node;
                    node = node.next;
                    if (node != null)
                        if (key.hashCode() == node.hash && (key == node.key || key.equals(node.key))) {
                            oldValue = node.value;
                            entrySet.remove(node);
                            previousNode.next = node.next;
                            keySet.remove(key);
                            return oldValue;
                        }
                }
            }
        }
        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < realCapacity; i++) {
            table[i] = null;
        }
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        return entrySet;
    }

    static class Node<K,V> implements Map.Entry<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node<K, V> next;

        Node(K key, V value){
            this.key = key;
            hash = key.hashCode();
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }
    }
}
