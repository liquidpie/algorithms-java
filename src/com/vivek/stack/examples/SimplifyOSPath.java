package com.vivek.stack.examples;

import java.util.Stack;

/**
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 *     The path starts with a single slash '/'.
 *     Any two directories are separated by a single slash '/'.
 *     The path does not end with a trailing '/'.
 *     The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 *
 * Return the simplified canonical path.
 *
 *
 *
 * Example 1:
 *
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 *
 * Example 2:
 *
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 *
 * Example 3:
 *
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 *
 * https://leetcode.com/problems/simplify-path/
 */
public class SimplifyOSPath {

    public static void main(String[] args) {
        System.out.printf(simplifyPath("/home/../abc/"));
    }

    static String simplifyPath(String path) {
        String[] a = path.split("/");
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < a.length; i++) {
            if (a[i].isEmpty() || a[i].equals("."))
                continue;

            if (a[i].equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(a[i]);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("/");
        Stack<String> reversedStack = new Stack<>();
        while (!stack.isEmpty()) {
            reversedStack.push(stack.pop());
        }

        while (!reversedStack.isEmpty()) {
            builder.append(reversedStack.pop()).append("/");
        }

        if (builder.length() > 1) {
            builder.setLength(builder.length() - 1);
        }

        return builder.toString();
    }

}
