package com.vivek.search;

/**
 * An element in a sorted array can be found in O(log n) time via binary search.
 * But suppose we rotate an ascending order sorted array at some pivot unknown to you beforehand.
 * So for instance, 1 2 3 4 5 might become 3 4 5 1 2.
 * Devise a way to find an element in the rotated array in O(log n) time.
 */
public class RotatedArraySearch {

    /**
     Input arr[] = {3, 4, 5, 1, 2}
     Element to Search = 1
        1) Find out pivot point and divide the array in two sub-arrays. (pivot = 2) Index of 5
        2) Now call binary search for one of the two sub-arrays.
            (a) If element is greater than 0th element then search in left array
            (b) Else Search in right array (1 will go in else as 1 < 0th element(3))
        3) If element is found in selected sub-array then return index. Else return -1.
     */
    static int pivotedBinarySearch(int arr[], int n, int key) {
        int pivot = findPivot(arr, 0, n-1);

        // If we didn't find a pivot, then
        // array is not rotated at all
        if (pivot == -1)
            return binarySearch(arr, 0, n-1, key);

        // If we found a pivot, then first
        // compare with pivot and then
        // search in two subarrays around pivot
        if (arr[pivot] == key)
            return pivot;
        if (arr[0] <= key)
            return binarySearch(arr, 0, pivot-1, key);
        return binarySearch(arr, pivot+1, n-1, key);
    }

    /* Function to get pivot. For array
       3, 4, 5, 6, 1, 2 it returns
       3 (index of 6) */
    private static int findPivot(int arr[], int low, int high) {
        // base cases
        if (high < low)
            return -1;
        if (high == low)
            return low;

       /* low + (high - low)/2; */
        int mid = (low + high)/2;
        if (mid < high && arr[mid] > arr[mid + 1])
            return mid;
        if (mid > low && arr[mid] < arr[mid - 1])
            return (mid-1);
        if (arr[low] >= arr[mid])
            return findPivot(arr, low, mid-1);
        return findPivot(arr, mid + 1, high);
    }

    /* Standard Binary Search function */
    private static int binarySearch(int arr[], int low, int high, int key) {
        if (high < low)
            return -1;

       /* low + (high - low)/2; */
        int mid = (low + high)/2;
        if (key == arr[mid])
            return mid;
        if (key > arr[mid])
            return binarySearch(arr, (mid + 1), high, key);
        return binarySearch(arr, low, (mid -1), key);
    }


    /* Improved Solution Below */

    /**
     1) Find middle point mid = (l + h)/2
     2) If key is present at middle point, return mid.
     3) Else If arr[l..mid] is sorted
        a) If key to be searched lies in range from arr[l]
            to arr[mid], recur for arr[l..mid].
        b) Else recur for arr[mid+1..r]
     4) Else (arr[mid+1..r] must be sorted)
        a) If key to be searched lies in range from arr[mid+1]
            to arr[r], recur for arr[mid+1..r].
        b) Else recur for arr[l..mid]
     */
    static int improvedSearch(int[] arr, int l, int h, int key) {

        int mid = (l + h) / 2;

        // search for key at mid first
        if (arr[mid] == key)
            return mid;

        // if left half is sorted
        if (arr[l] < arr[mid]) {
            if (key >= arr[l] && key < arr[mid]) {
                return improvedSearch(arr, l, mid - 1, key);
            }
            return improvedSearch(arr, mid + 1, h, key);
        }

        if (key > arr[mid] && key <= arr[h])
            return improvedSearch(arr, mid + 1, h, key);

        return improvedSearch(arr, l, mid - 1, key);
    }


    // main function
    public static void main(String args[]) {
        // Let us search 3 in below array
        int arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int n = arr.length;
        int key = 3;
        System.out.println("(PivotedBinarySearch) Index of the element is : " + pivotedBinarySearch(arr, n, key));

        System.out.println("(ImprovedSearch) Index of the element is : " + improvedSearch(arr, 0, n - 1, key));

    }

}
