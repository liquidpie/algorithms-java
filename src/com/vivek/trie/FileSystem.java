package com.vivek.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Design File System
 *
 * You are asked to design a file system that allows you to create new paths and associate them with different values.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
 * For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
 *
 * Implement the FileSystem class:
 *
 *     bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true.
 *     Returns false if the path already exists or its parent path doesn't exist.
 *     int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
 *
 * Example 1:
 *
 *     Input:
 *     ["FileSystem","createPath","get"]
 *     [[],["/a",1],["/a"]]
 *     Output:
 *     [null,true,1]
 *
 *     Explanation:
 *     FileSystem fileSystem = new FileSystem();
 *
 *     fileSystem.createPath("/a", 1); // return true
 *     fileSystem.get("/a"); // return 1
 *
 * Example 2:
 *
 *     Input:
 *     ["FileSystem","createPath","createPath","get","createPath","get"]
 *     [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 *     Output:
 *     [null,true,true,2,false,-1]
 *
 *     Explanation:
 *     FileSystem fileSystem = new FileSystem();
 *
 *     fileSystem.createPath("/leet", 1); // return true
 *     fileSystem.createPath("/leet/code", 2); // return true
 *     fileSystem.get("/leet/code"); // return 2
 *     fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 *     fileSystem.get("/c"); // return -1 because this path doesn't exist.
 *
 * https://leetcode.com/problems/design-file-system
 */
public class FileSystem {

    public static void main(String[] args) {
        Approach1 fileSystem = new Approach1();

        {
            boolean result = fileSystem.createPath("/a", 1); // return true
            System.out.println(result);
            int value = fileSystem.get("/a"); // return 1
            System.out.println(value);
        }
        {
            boolean result = fileSystem.createPath("/a/b", 2); // return true
            System.out.println(result);
            int value = fileSystem.get("/a/b"); // return 2
            System.out.println(value);
        }
        {
            boolean result = fileSystem.createPath("/c/d", 3); // return false
            System.out.println(result);
            int value = fileSystem.get("/c/d"); // return -1
            System.out.println(value);
        }
    }

    static class Approach1 {
        static class TrieNode {
            Map<String, TrieNode> children;
            int value;
            boolean isLeaf;

            TrieNode() {
                this.children = new HashMap<>();
                this.value = -1;
                this.isLeaf = false;
            }
        }

        private final TrieNode root;

        public Approach1() {
            this.root = new TrieNode();
        }

        public boolean createPath(String path, int value) {
            if (path.isEmpty() || path.equalsIgnoreCase("/"))
                return false;
            String[] pathArr = path.split("/");
            pathArr = Arrays.copyOfRange(pathArr, 1, pathArr.length);

            return register(pathArr, value);
        }

        public int get(String path) {
            if (path.isEmpty() || path.equalsIgnoreCase("/"))
                return -1;

            String[] pathArr = path.split("/");
            pathArr = Arrays.copyOfRange(pathArr, 1, pathArr.length);
            TrieNode findNode = find(pathArr, 0, root);
            return findNode != null ? findNode.value : -1;
        }

        private boolean register(String[] components, int value) {
            TrieNode crawlNode = root;
            String path = components[components.length - 1];
            for (int i = 0; i < components.length - 1; i++) {
                if (!crawlNode.children.containsKey(components[i])) {
                    return false;
                }
                crawlNode = crawlNode.children.get(components[i]);
            }

            if (crawlNode.children.containsKey(path))
                return false;

            TrieNode newNode = new TrieNode();
            newNode.value = value;
            newNode.isLeaf = true;
            crawlNode.children.put(path, newNode);

            return true;
        }

        private TrieNode find(String[] components, int index, TrieNode crawlNode) {
            if (index == components.length) {
                return crawlNode;
            }

            TrieNode resultNode = null;
            if (crawlNode != null && crawlNode.children != null &&
                    crawlNode.children.containsKey(components[index])) {

                crawlNode = crawlNode.children.get(components[index]);
                resultNode = find(components, index + 1, crawlNode);
            }

            return resultNode;
        }
    }

    static class Approach2 {
        Map<String, Integer> file = new HashMap<>();
        public Approach2() {
            file.put("", -1);
        }

        public boolean create(String path, int value) {
            int idx = path.lastIndexOf("/");
            String parent = path.substring(0, idx);
            if (!file.containsKey(parent)) { return false; }
            return file.putIfAbsent(path, value) == null;
        }

        public int get(String path) {
            return file.getOrDefault(path, -1);
        }

    }

}
