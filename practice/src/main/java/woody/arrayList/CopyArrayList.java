package woody.arrayList;

import java.util.Arrays;

public class CopyArrayList<E> implements CopyList<E>{
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

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
        elementData = EMPTY_ELEMENTDATA;
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
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    public void add(int index, E e) {
        rangeCheck(index);

        final int s;
        Object[] copy;
        //по идее должно улучшить производительность и избавить от ошибок при многопоточном доступе

        if ((s = size) == (copy = elementData).length)
            copy = grow();

        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        copy[index] = e;
        size++;
    }

    private Object[] grow(){
        final int oldSize = elementData.length;

        if (oldSize > 0){
            return elementData = Arrays.copyOf(elementData, oldSize + DEFAULT_CAPACITY);
        }else {
            return elementData = new Object[DEFAULT_CAPACITY];
        }
    }

    @Override
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    private void rangeCheck(int index){
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("illegal index");
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = elementData(index);

        elementData[index] = element;

        return oldValue;
    }

    @Override
    public boolean remove(Object o) {
        final Object[] copy = elementData;
        final int size = this.size;
        //по идее должно улучшить производительность и избавить от ошибок при многопоточном доступе

        int i = 0;

        found: {
            if (o == null){
                for (; i < size; i++) {
                    if (copy[i] == null)
                        break found;
                }
            }else {
                for (; i < size; i++) {
                    if (copy[i].equals(o))
                        break found;
                }
            }
        }
        remove(i);
        return true;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = elementData(index);

        final int s = size;
        Object[] copy = elementData;
        //по идее должно улучшить производительность и избавить от ошибок при многопоточном доступе

        System.arraycopy(copy, index + 1,
                copy, index,
                s - index-1);

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        Object[] copy = elementData;
        //по идее должно улучшить производительность и избавить от ошибок при многопоточном доступе

        if (o == null){
            for (int i = 0; i < size; i++) {
                if (copy[i] == null)
                    return i;
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (copy[i].equals(o))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Object[] copy = elementData;
        //по идее должно улучшить производительность и избавить от ошибок при многопоточном доступе

        if (o == null){
            for (int i = size- 1; i >= 0; i--) {
                if (copy[i] == null)
                    return i;
            }
        }else {
            for (int i = size- 1; i >= 0; i--) {
                if (copy[i].equals(o))
                    return i;
            }
        }
        return -1;
    }

    E elementData(int index) {
        return (E) elementData[index];
    }
}
