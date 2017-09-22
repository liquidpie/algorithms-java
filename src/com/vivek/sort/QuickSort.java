package com.vivek.sort;

public class QuickSort {
	
	/* Quick Sort */
	void quickSort(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}

		int partitionIndex = partition(arr, start, end);
		quickSort(arr, start, partitionIndex - 1);
		quickSort(arr, partitionIndex + 1, end);
	}

	private int partition(int[] arr, int start, int end) {
		int pivot = arr[end];
		int partitionIndex = start;

		for (int i = start; i < end - 1; i++) {
			if (arr[i] <= pivot) {
				int temp = arr[partitionIndex];
				arr[partitionIndex] = arr[i];
				arr[i] = temp;
				partitionIndex++;
			}
		}

		int temp = arr[partitionIndex];
		arr[partitionIndex] = arr[end];
		arr[end] = temp;

		return partitionIndex;
	}

}
