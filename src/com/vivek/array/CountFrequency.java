package com.vivek.array;

/**
 * Given an unsorted array of n integers which can contain integers from 1 to n.
 * Some elements can be repeated multiple times and some other elements can be absent from the array.
 * Count frequency of all elements that are present and print the missing elements.
 */
public class CountFrequency {

    static void withExtraSpace(int[] arr, int n) {
        int[] aux = new int[n];

        for (int i = 0; i < n; i++) {
            aux[arr[i] - 1] += 1;
        }

        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + " = " + aux[i]);
        }
    }

    /**
         1)  Subtract 1 from every element so that the elements
         become in range from 0 to n-1
         for (int j =0; j < n; j++)
         arr[j] = arr[j]-1;

         2)  Use every element arr[i] as index and add 'n' to
         element present at arr[i]%n to keep track of count of
         occurrences of arr[i]
         for (int i=0; i < n; i++)
         arr[arr[i]%n] = arr[arr[i]%n] + n;

         3)  To print counts, simply print the number of times n
         was added at index corresponding to every element
         for (int i =0; i < n; i++)
         print "(i + 1) -> arr[i] / n"
     */
    static void withoutExtraSpace(int[] arr, int n) {

        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] - 1;
        }

        for (int i = 0; i < n; i++) {
            arr[arr[i] % n] = arr[arr[i] % n] + n;
        }

        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + " = " + arr[i] / n);
        }

    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 3, 2, 5};
        int n = arr.length;

        withExtraSpace(arr, n);

        withoutExtraSpace(arr, n);
    }

}
