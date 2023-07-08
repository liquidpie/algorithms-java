package com.vivek.heap;

import java.util.PriorityQueue;

/**
 * Find the Median of a Number Stream
 *
 * Design a class to calculate the median of a number stream. The class should have the following two methods:
 * 1. insertNum(int num) : stores the number in the class
 * 2. findMedian() : returns the median of all numbers inserted in the class
 * If the count of numbers inserted in the class is even, the median will be the average of the middle two numbers.
 * Example 1:
 * 1. insertNum(3)
 * 2. insertNum(1)
 * 3. findMedian() -> output: 2
 * 4. insertNum(5)
 * 5. findMedian() -> output: 3
 * 6. insertNum(4)
 * 7. findMedian() -> output: 3.5
 *
 * Solution:
 *
 * Assume ‘x’ is the median of a list. This means that half of the numbers in the list will be smaller than (or equal
 * to) ‘x’ and half will be greater than (or equal to) ‘x’. This leads us to an approach where we can divide the list
 * into two halves: one half to store all the smaller numbers (let’s call it smallNumList ) and one half to store the
 * larger numbers (let’s call it largNumList ). The median of all the numbers will either be the largest number in
 * the smallNumList or the smallest number in the largNumList . If the total number of elements is even, the
 * median will be the average of these two numbers.
 *
 * 1. We can store the first half of numbers (i.e., smallNumList ) in a Max Heap. We should use a Max Heap as
 * we are interested in knowing the largest number in the first half.
 * 2. We can store the second half of numbers (i.e., largeNumList ) in a Min Heap, as we are interested in
 * knowing the smallest number in the second half.
 * 3. At any time, the median of the current list of numbers can be calculated from the top element of the two
 * heaps.
 *
 * Time complexity :
 * The time complexity of the insertNum() will be O(logN) due to the insertion in the heap. The time
 * complexity of the findMedian() will be O(1) as we can find the median from the top elements of the heaps.
 *
 * Space complexity :
 * The space complexity will be O(N) because, as at any time, we will be storing all the numbers.
 *
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class MedianOfAStream {

    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public MedianOfAStream() {
        this.maxHeap = new PriorityQueue<>((a, b) -> b - a);
        this.minHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    void insertNum(int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianOfAStream medianOfAStream = new MedianOfAStream();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
    }

}
