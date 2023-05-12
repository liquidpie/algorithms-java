package com.vivek.recursion;

/**
 * https://levelup.gitconnected.com/how-to-think-recursively-solving-recursion-problems-in-4-steps-95a6d07aa866
 */
public class ReverseString {

    public static void main(String[] args) {
        System.out.println(reverse("Hello"));
    }

    static String reverse(String str) {
        if (str.isEmpty())
            return "";

        String subProblem = str.substring(1);
        String reversedSubProblem = reverse(subProblem);
        return reversedSubProblem + str.charAt(0);

    }

}
