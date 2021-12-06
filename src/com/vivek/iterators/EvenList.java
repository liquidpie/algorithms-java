package com.vivek.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class EvenList<T> implements Iterable<T> {

    private final List<T> list;

    public EvenList(List<T> list) {
        this.list = list;
    }

    @Override
    public Iterator<T> iterator() {
        return new EvenListIterator<>();
    }

    private class EvenListIterator<T> implements Iterator<T> {
        int size = list.size();
        int currentPointer = 0;

        @Override
        public boolean hasNext() {
            return currentPointer < size;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T val = (T) list.get(currentPointer);
            currentPointer += 2;
            return val;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        EvenList<Integer> myList = new EvenList<>(list);

        Iterator<Integer> iter = myList.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
