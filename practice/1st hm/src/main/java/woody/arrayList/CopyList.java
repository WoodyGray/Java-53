package woody.arrayList;

public interface CopyList<E> {
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    boolean add(E e);
    E get(int index);
    E set(int index, E element);
    boolean remove(Object o);
    E remove(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
}
