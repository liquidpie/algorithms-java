package com.vivek.stack;

import java.util.Set;
import java.util.Stack;

/**
 * We often deal with arithmetic expressions written in what is called infix notation:
 *
 *          Operand1 op Operand2
 *
 * We have rules to indicate which operations take precedence over others, and we often use parentheses to override those rules.
 *
 * It is also quite possible to write arithmetic expressions using postfix notation:
 *
 *          Operand1 Operand2 op
 *
 * With postfix notation, it is possible to use a stack to find the overall value of an infix expression by first converting it to postfix notation.
 *
 * Example: Suppose we have this infix expression Q:
 *
 *          5 * ( 6 + 2 ) - 12 / 4
 *
 * The equivalent postfix expression P is:
 *
 *          5 6 2 + * 12 4 / -
 *
 * There are two algorithms involved. One converts an infix expression to postfix form, and the other evaluates a postfix expression. Each uses a stack.
 *
 * References:
 * http://faculty.cs.niu.edu/~hutchins/csci241/eval.htm
 * https://www.geeksforgeeks.org/expression-evaluation/
 */
public class EvaluateArithmeticExpression {

    /*
    Evaluate a postfix expression

Suppose P is an arithmetic expression in postfix notation. We will evaluate it using a stack to hold the operands.

     Start with an empty stack.  We scan P from left to right.

     While (we have not reached the end of P)
        If an operand is found
           push it onto the stack
        End-If
        If an operator is found
           Pop the stack and call the value A
           Pop the stack and call the value B
           Evaluate B op A using the operator just found.
           Push the resulting value onto the stack
        End-If
    End-While
    Pop the stack (this is the final value)

Notes:

    At the end, there should be only one element left on the stack.

    This assumes the postfix expression is valid.
     */
    static Integer evaluateExpr(String expr) {
        String postfix = infixToPostfix(expr);
        if (postfix == null) {
            System.out.println("Invalid Expression");
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");
        Set<String> operators = Set.of("+", "-", "*", "/");

        for (String token : tokens) {
            if (!operators.contains(token))
                stack.push(Integer.parseInt(token));
            else {
                int a = stack.pop();
                int b = stack.pop();
                int result = operation(token, a, b);
                stack.push(result);
            }
        }
        return stack.size() == 1 ? stack.pop() : null;
    }

    static Integer evaluateExprUsingTwoStacks(String expr) {
        char[] tokens = expr.toCharArray();
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;

            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuilder builder = new StringBuilder();
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                    builder.append(tokens[i++]);
                }
                values.push(Integer.parseInt(builder.toString()));
                i--;
            } else if (tokens[i] == '(') {
                operators.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    int result = operation(String.valueOf(operators.pop()), values.pop(), values.pop());
                    values.push(result);
                }
                operators.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!operators.isEmpty() && comparePrecedence(tokens[i], operators.peek()) == 1) {
                    int result = operation(String.valueOf(operators.pop()), values.pop(), values.pop());
                    values.push(result);
                }
                operators.push(tokens[i]);
            }
        }

        while (!operators.isEmpty()) {
            int result = operation(String.valueOf(operators.pop()), values.pop(), values.pop());
            values.push(result);
        }

        return values.pop();
    }


    /*
Transform an infix expression to postfix notation

Suppose Q is an arithmetic expression in infix notation. We will create an equivalent postfix expression P by adding items to on the right of P. The new expression P will not contain any parentheses.

We will use a stack in which each item may be a left parenthesis or the symbol for an operation.

     Start with an empty stack.  We scan Q from left to right.

     While (we have not reached the end of Q)
        If (an operand is found)
           Add it to P
        End-If
        If (a left parenthesis is found)
           Push it onto the stack
        End-If
        If (a right parenthesis is found)
           While (the stack is not empty AND the top item is
                  not a left parenthesis)
              Pop the stack and add the popped value to P
           End-While
           Pop the left parenthesis from the stack and discard it
        End-If
        If (an operator is found)
           If (the stack is empty or if the top element is a left
               parenthesis)
              Push the operator onto the stack
           Else
              While (the stack is not empty AND the top of the stack
                     is not a left parenthesis AND precedence of the
                     operator <= precedence of the top of the stack)
                 Pop the stack and add the top value to P
              End-While
              Push the latest operator onto the stack
           End-If
        End-If
     End-While
     While (the stack is not empty)
        Pop the stack and add the popped value to P
     End-While

Notes:

    At the end, if there is still a left parenthesis at the top of the stack, or if we find a right parenthesis when the stack is empty, then Q contained unbalanced parentheses and is in error.
     */
    private static String infixToPostfix(String expr) {
        StringBuilder builder = new StringBuilder(); // We can return Queue instead of String
        Stack<Character> stack = new Stack<>(); // keeps arithmetic and brace temporarily

        for (char ch : expr.toCharArray()) {

            if (ch >= '0' && ch <= '9' || ch == ' ')
                builder.append(ch);
            else if (ch == '(')
                stack.push(ch);
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    builder.append(stack.pop());
                }
                if (stack.isEmpty() || stack.peek() != '(')
                    return null;  // Unbalanced parentheses
                stack.pop();
            }
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!stack.isEmpty() && stack.peek() != '(' && comparePrecedence(ch, stack.peek()) <= 0) {
                    builder.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(' || stack.peek() == ')')
                return null;
            builder.append(' ').append(stack.pop());
        }

        return builder.toString().trim();
    }

    private static int comparePrecedence(char op1, char op2) {
        if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/'))
            return -1;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return 1;
        return 0;
    }

    private static int operation(String operator, int a, int b) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        String expr = "( 21 + 5 ) * 4";
        System.out.println(evaluateExpr(expr));

//        expr = "( 21 + 5 * 4";
//        System.out.println(evaluateExpr(expr));
//
//        expr = "21 + 5 ) * 4";
//        System.out.println(evaluateExpr(expr));

//        expr = "( 21 + ) 5 * 4";
//        System.out.println(evaluateExpr(expr));
//
//        expr = "( 21 + ) 5 * 4";
        System.out.println(evaluateExprUsingTwoStacks(expr));
    }

}
