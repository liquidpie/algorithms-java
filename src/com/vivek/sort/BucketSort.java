package com.vivek.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Bucket Sort is a sorting technique that sorts the elements by first dividing the elements into several groups called buckets.
 * The elements inside each bucket are sorted using any of the suitable sorting algorithms or recursively calling the same algorithm.
 *
 * Several buckets are created. Each bucket is filled with a specific range of elements.
 * The elements inside the bucket are sorted using any other algorithm.
 * Finally, the elements of the bucket are gathered to get the sorted array.
 *
 * The process of bucket sort can be understood as a scatter-gather approach.
 * The elements are first scattered into buckets then the elements of buckets are sorted.
 * Finally, the elements are gathered in order.
 *
 * Bucket sort is mainly useful when input is uniformly distributed over a range. For example, consider the following problem.
 * Sort a large set of floating point numbers which are in range from 0.0 to 1.0 and are uniformly distributed across the range. How do we sort the numbers efficiently?
 *
 * https://www.geeksforgeeks.org/bucket-sort-2/
 */
public class BucketSort {

    /**
     * 1) Create n empty buckets (Or lists).
     * 2) Do following for every array element arr[i].
     * .......a) Insert arr[i] into bucket[n*array[i]]
     * 3) Sort individual buckets using insertion sort.
     * 4) Concatenate all sorted buckets.
     */
    static void bucketSortForFloats(float[] arr, int n) {
        if (n <= 0)
            return;

        // 1) Create n empty buckets
        Vector<Float>[] buckets = new Vector[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new Vector<Float>();
        }

        // 2) Put array elements in different buckets
        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n;
            buckets[(int) idx].add(arr[i]);
        }

        // 3) Sort individual buckets
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        // 4) Concatenate all buckets into arr[]
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }

    }

    static void bucketSortWithIntegerParts(float[] arr, int nbOfBuckets) {
        float min = min(arr);
        float max = max(arr);

        float range = (max - min) / nbOfBuckets;

        // 1) Create n empty buckets
        Vector<Float>[] buckets = new Vector[nbOfBuckets];
        for (int i = 0; i < nbOfBuckets; i++) {
            buckets[i] = new Vector<Float>();
        }

        // scatter the array elements into the correct bucket
        for (int i = 0; i < arr.length; i++) {
            float diff = (arr[i] - min) / range - (int) ((arr[i] - min) / range);
            // append the boundary elements to the lower array
            if (diff == 0 && arr[i] != min) {
                buckets[(int) ((arr[i] - min) / range) - 1].add(arr[i]);
            } else {
                buckets[(int) ((arr[i] - min) / range)].add(arr[i]);
            }
        }

        // Sort each bucket individually
        for (int i = 0; i < nbOfBuckets; i++) {
            Collections.sort(buckets[i]);
        }

        // 4) Concatenate all buckets into arr[]
        int index = 0;
        for (int i = 0; i < nbOfBuckets; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }

    }

    private static float max(float[] arr) {
        float max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static float min(float[] arr) {
        float min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }



    public static void main(String[] args) {
        float[] arr = { 0.897f, 0.565f, 0.656f, 0.1234f, 0.665f, 0.3434f };

        int n = arr.length;
        bucketSortForFloats(arr, n);

        System.out.println("Sorted array is ");
        for (float el : arr) {
            System.out.print(el + " ");
        }
        System.out.println();

        float[] arr2 = { 9.8f, 0.6f, 10.1f, 1.9f, 3.07f, 3.04f, 5.0f, 8.0f, 4.8f, 7.68f };

        bucketSortWithIntegerParts(arr2, 5);

        System.out.println("Sorted array is ");
        for (float el : arr2) {
            System.out.print(el + " ");
        }
    }


}
