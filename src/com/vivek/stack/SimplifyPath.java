package com.vivek.stack;

import java.util.Stack;

/**
 * 71. Simplify Path
 *
 * You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.
 *
 * The rules of a Unix-style file system are as follows:
 *
 *     A single period '.' represents the current directory.
 *     A double period '..' represents the previous/parent directory.
 *     Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
 *     Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
 *
 * The simplified canonical path should follow these rules:
 *
 *     The path must start with a single slash '/'.
 *     Directories within the path must be separated by exactly one slash '/'.
 *     The path must not end with a slash '/', unless it is the root directory.
 *     The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
 *
 * Return the simplified canonical path.
 *
 *
 *
 * Example 1:
 *
 * Input: path = "/home/"
 *
 * Output: "/home"
 *
 * Explanation:
 *
 * The trailing slash should be removed.
 *
 * Example 2:
 *
 * Input: path = "/home//foo/"
 *
 * Output: "/home/foo"
 *
 * Explanation:
 *
 * Multiple consecutive slashes are replaced by a single one.
 *
 * Example 3:
 *
 * Input: path = "/home/user/Documents/../Pictures"
 *
 * Output: "/home/user/Pictures"
 *
 * Explanation:
 *
 * A double period ".." refers to the directory up a level (the parent directory).
 *
 * Example 4:
 *
 * Input: path = "/../"
 *
 * Output: "/"
 *
 * Explanation:
 *
 * Going one level up from the root directory is not possible.
 *
 * Example 5:
 *
 * Input: path = "/.../a/../b/c/../d/./"
 *
 * Output: "/.../b/d"
 *
 * Explanation:
 *
 * "..." is a valid name for a directory in this problem.
 *
 * Reference:
 * https://leetcode.com/problems/simplify-path
 */
public class SimplifyPath {

    public static void main(String[] args) {
        SimplifyPath helper = new SimplifyPath();

        System.out.println(helper.simplifyPath("/home/"));
        System.out.println(helper.simplifyPath("/home//foo/"));
        System.out.println(helper.simplifyPath("/home/user/Documents/../Pictures"));
        System.out.println(helper.simplifyPath("/../"));
        System.out.println(helper.simplifyPath("/.../a/../b/c/../d/./"));
    }

    public String simplifyPath(String path) {

        String[] components = path.split("/");

        Stack<String> stack = new Stack<>();
        for (String s : components) {
            if (s.isEmpty() || s.equals("."))
                continue;
            if (s.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!s.equals("..")) {
                stack.push(s);
            }
        }

        StringBuilder simplifiedPath = new StringBuilder();

        if (stack.isEmpty())
            return "/";

        while (!stack.isEmpty()) {
            simplifiedPath.insert(0, "/" + stack.pop());
        }

        return simplifiedPath.toString();
    }

}
