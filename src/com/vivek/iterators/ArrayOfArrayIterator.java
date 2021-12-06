package com.vivek.iterators;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implement an iterator which can iterate on an array of array of integers.
 *
 * Array of array could contain empty subarrays
 *
 * remove() should remove the element last accessed by next() method.
 * In case no element was accessed, remove() should throw an exception.
 */
public class ArrayOfArrayIterator implements Iterator<Integer> {

    private final List<List<Integer>> arrayList;
    private int row;
    private int col;
    private int accessedRow;
    private int accessedCol;

    public ArrayOfArrayIterator(List<List<Integer>> arrayList) {
        this.arrayList = arrayList;
        this.row = 0;
        this.col = 0;
        this.accessedRow = -1;
        this.accessedCol = -1;
    }

    @Override
    public boolean hasNext() {
        if (col < arrayList.get(row).size()) {
            return true;
        }

        while (row < arrayList.size() && (arrayList.get(row).isEmpty() || col >= arrayList.get(row).size())) {
            row++;
            col = 0;
        }

        return row != arrayList.size();
    }

    @Override
    public Integer next() {
        if (!hasNext())
            throw new NoSuchElementException();
        Integer value = arrayList.get(row).get(col);
        accessedRow = row;
        accessedCol = col;
        col++;
        return value;
    }

    @Override
    public void remove() {
        if (accessedRow == -1 || accessedCol == -1) {
            throw new IllegalStateException();
        }
        col--;
        arrayList.get(accessedRow).remove(accessedCol);
    }

    public static void main(String[] args) {
        int[][] arrays = { {}, {1, 2}, {3, 4, 5}, {}, {}, {6}, {7, 8}, {}, {9}, {10}, {} };
        List<List<Integer>> arrayList = new ArrayList<>();
        for (int[] array : arrays) {
            arrayList.add(Arrays.stream(array)
                    .boxed()
                    .collect(Collectors.toList()));
        }

        Iterator<Integer> iterator = new ArrayOfArrayIterator(arrayList);

        // Remove even elements
        int i = 1;
        while (iterator.hasNext()) {
            iterator.next();
            if (i % 2 == 0) {
                iterator.remove();
            }
            i++;
        }

        Iterator<Integer> iterator2 = new ArrayOfArrayIterator(arrayList);

        while (iterator2.hasNext()) {
            System.out.print(" " + iterator2.next());
        }
    }

}
