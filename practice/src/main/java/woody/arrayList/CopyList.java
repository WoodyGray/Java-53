package woody.arrayList;

public interface CopyList<E> {
    int size();
    boolean isEmpty();
    boolean contains();
    boolean add();
    E get(int index);
    E set(int index, E element);
    boolean remove();
    int indexOf(Object o);
    int lastIndexOf(Object o);
}
