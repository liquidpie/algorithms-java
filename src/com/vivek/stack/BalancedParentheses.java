package com.vivek.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BalancedParentheses {
	
	public boolean checkParenthesisBalance(String input) {
        Stack<Character> stack = new Stack<Character>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        
        char[] arr = input.toCharArray();
        
        if (arr.length == 0) {
            return true;
        }
        
        for (char c :  arr) {
            if (map.containsKey(c)) {
                stack.push(c);
            }
            else if (map.containsValue(c)) {
                if (stack.empty()) {
                    return false;
                }
                if (map.get(stack.peek()).equals(c)) {
                    stack.pop();
                }
            }
        }
        
        return stack.empty();
    }

}
