package com.vivek.sort;

import java.util.Arrays;

/**
 Heap Sort Algorithm for sorting in increasing order:
 1. Build a max heap from the input data.
 2. At this point, the largest item is stored at the root of the heap.
 Replace it with the last item of the heap followed by reducing the size of heap by 1.
 Finally, heapify the root of tree.
 3. Repeat above steps while size of heap is greater than 1.

 https://www.geeksforgeeks.org/heap-sort/
 */
public class HeapSort {

    public static void sort(int[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    static void heapify(int[] arr, int n, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public static void main(String... args) {
        int[] arr = {9, 7, 5, 2, 6, 4};
        Arrays.stream(arr).forEach(System.out::print);
        HeapSort.sort(arr);
        System.out.println();
        Arrays.stream(arr).forEach(System.out::print);
    }
}
