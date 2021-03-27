package com.vivek.stack;

import java.util.Stack;

/**

 We define a variable minEle that stores the current minimum element in the stack. Now the interesting part is, how to handle the case when minimum element is removed. To handle this, we push “2x – minEle” into the stack instead of x so that previous minimum element can be retrieved using current minEle and its value stored in stack. Below are detailed steps and explanation of working.

 Push(x) : Inserts x at the top of stack.

 If stack is empty, insert x into the stack and make minEle equal to x.
 If stack is not empty, compare x with minEle. Two cases arise:
 	- If x is greater than or equal to minEle, simply insert x.
 	- If x is less than minEle, insert (2*x – minEle) into the stack and make minEle equal to x. For example, let previous minEle was 3. Now we want to insert 2. We update minEle as 2 and insert 2*2 – 3 = 1 into the stack.

 Pop() : Removes an element from top of stack.

 Remove element from top. Let the removed element be y. Two cases arise:
 	- If y is greater than or equal to minEle, the minimum element in the stack is still minEle.
 	- If y is less than minEle, the minimum element now becomes (2*minEle – y), so update (minEle = 2*minEle – y). This is where we retrieve previous minimum from current minimum and its value in stack. For example, let the element to be removed be 1 and minEle be 2. We remove 1 and update minEle as 2*2 – 1 = 3.


 */
public class GetMinValueWithoutAuxiliaryStack {

	// main stack to store elements
	private Stack<Integer> s = new Stack<>();

	// variable to store the minimum element
	private int min;

	// Inserts a given element on top of the stack
	public void push(int x) {
		if (s.empty()) {
			s.push(x);
			min = x;
		} else if (x > min) {
			s.push(x);
		} else {
			s.push(2*x - min);
			min = x;
		}
	}

	// Removes the top element from the stack and returns it
	public int pop() {
		if (s.empty()) {
			System.out.println("Stack underflow!!");
		}

		int top = s.peek();
		if (top < min) {
			min = 2*min - top;
		}
		return s.pop();
	}

	// Returns the minimum element from the stack in constant time
	public int min() {
		return min;
	}
	
	public static void main (String[] args) {
		var s = new GetMinValueWithoutAuxiliaryStack();

		s.push(6);
		System.out.println(s.min());

		s.push(7);
		System.out.println(s.min());

		s.push(5);
		System.out.println(s.min());

		s.push(3);
		System.out.println(s.min());

		s.pop();
		System.out.println(s.min());

		s.pop();
		System.out.println(s.min());
	}
}
