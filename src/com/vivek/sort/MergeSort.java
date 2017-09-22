package com.vivek.sort;

public class MergeSort {

	/* Merge Sort */
	void mergeSort(int[] arr) {
		if (arr.length < 2) {
			return;
		}

		int mid = arr.length / 2;

		int[] leftArr = new int[mid];
		int[] rightArr = new int[arr.length - mid];

		for (int i = 0; i < leftArr.length; i++) {
			leftArr[i] = arr[i];
		}

		for (int i = 0; i < rightArr.length; i++) {
			rightArr[i] = arr[mid + i];
		}

		mergeSort(leftArr);
		mergeSort(rightArr);
		merge(leftArr, rightArr, arr);
	}

	private void merge(int[] leftArr, int[] rightArr, int[] arr) {
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < leftArr.length && j < rightArr.length) {
			if (leftArr[i] <= rightArr[j]) {
				arr[k] = leftArr[i];
				i++;
			}
			else {
				arr[k] = rightArr[j];
				j++;
			}
			k++;
		}

		while (i < leftArr.length) {
			arr[k] = leftArr[i];
			i++;
			k++;
		}

		while (j < rightArr.length) {
			arr[k] = rightArr[j];
			j++;
			k++;
		}
	}
	
}
