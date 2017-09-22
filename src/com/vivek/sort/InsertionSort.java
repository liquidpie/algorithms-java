package com.vivek.sort;

public class InsertionSort {
	
	/* Insertion Sort */
	void insertionSort(int[] arr) {
		int n = arr.length;
		
		for (int i = 0; i < n; i++) {
			int val = arr[i];
			int index = i;
			while (index > 0 && arr[index - 1] > val) {
				arr[index] = arr[index - 1];
				index--;
			}
			arr[index] = val;
		}
	}

}
