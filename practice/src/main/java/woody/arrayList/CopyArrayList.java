package woody.arrayList;

public class CopyArrayList<E> implements CopyList<E>{
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;

    private int size;

    public CopyArrayList(int size){
        if (size == 0){
            elementData = EMPTY_ELEMENTDATA;
        }else if (size > 0) {
            elementData = new Object[size];
        }else {
            throw new IllegalArgumentException("Illegal size");
        }
    }

    public CopyArrayList(){
        elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }




    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains() {
        return false;
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public boolean remove() {
        return false;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }
}
