package com.vivek.stack;

import java.util.Stack;

/**
 * 155. Min Stack
 *
 * To retrieve the current minimum, just return the top element from minimum stack.
 * Each time you perform a push operation, check if the pushed element is a new minimum. If it is, push it to the minimum stack too.
 * When you perform a pop operation, check if the popped element is the same as the current minimum. If it is, pop it off the minimum stack too.
 * If the value being pushed is equal to the current minimum, you push that value to the minimum stack as well.
 *
 * Reference:
 * https://leetcode.com/problems/min-stack/description/
 */
public class GetMinValue {
	
	Stack<Integer> stack = new Stack<>();
	Stack<Integer> minStack = new Stack<>();
	
	public static void main(String... args) {
		GetMinValue obj = new GetMinValue();
		obj.push(9);
		obj.push(2);
		obj.push(4);
		obj.push(7);
		
		System.out.println(obj.getMin());
		obj.pop();
		
	}
	
	private void push(int n) {
		stack.push(n);
		if (minStack.isEmpty() || n <= minStack.peek()) {
			minStack.push(n);
		}
	}
	
	private int pop() {
		if (stack.peek().equals(minStack.peek())) {
			minStack.pop();
		}
			
		return stack.pop();
	}
	
	private int getMin() {
		return minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
	}

}
