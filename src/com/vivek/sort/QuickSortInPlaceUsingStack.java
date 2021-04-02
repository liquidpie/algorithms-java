package com.vivek.sort;

import java.util.Arrays;
import java.util.Stack;

public class QuickSortInPlaceUsingStack {

    static void quickSort(int[] arr) {
        // create a stack for storing subarray start and end index
        Stack<Pair> stack = new Stack<>();

        int start = 0;
        int end = arr.length - 1;

        // push the start and end index of the array into the stack
        stack.push(new Pair(start, end));

        while (!stack.isEmpty()) {
            Pair top = stack.pop();
            start = top.l;
            end = top.r;

            // rearrange elements across pivot
            int pivotIndex = partition(arr, start, end);

            // push subarray indices containing elements that are less than the current pivot to stack
            if (pivotIndex - 1 > start) {
                stack.push(new Pair(start, pivotIndex - 1));
            }
            // push subarray indices containing elements that are more than the current pivot to stack
            if (pivotIndex + 1 < end) {
                stack.push(new Pair(pivotIndex + 1, end));
            }
        }

    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];

        // elements less than the pivot will go to the left of `pIndex`
        // elements more than the pivot will go to the right of `pIndex`
        // equal elements can go either way
        int pIndex = start;

        for (int i = start; i < end; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, pIndex);
                pIndex++;
            }
        }

        // swap `pIndex` with pivot
        swap(arr, pIndex, end);

        // return `pIndex` (index of the pivot element)
        return pIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static class Pair {
        int l;
        int r;

        public Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 8, 7, 12, 10, 9, 1, 2};
        System.out.println(Arrays.toString(arr));

        quickSort(arr);

        System.out.println(Arrays.toString(arr));

    }

}
