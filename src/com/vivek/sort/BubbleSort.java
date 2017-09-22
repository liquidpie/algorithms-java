package com.vivek.sort;

public class BubbleSort {

	/* Bubble Sort */
	void bubbleSort(int[] arr) {
		int n = arr.length;
		
		for (int i = 1; i < n; i++) {
			boolean flag = false;
			for (int j = 0; j < n - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;

					flag = true;
				}
			}

			if (!flag) {
				break;
			}
		}
	}
	
}
