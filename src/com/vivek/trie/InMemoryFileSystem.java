package com.vivek.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 588. Design In-Memory File System
 *
 * Design a data structure that simulates an in-memory file system.
 *
 * Implement the FileSystem class:
 *
 * - FileSystem() Initializes the object of the system.
 * - List<String> ls(String path)
 *      - If path is a file path, returns a list that only contains this file's name.
 *      - If path is a directory path, returns the list of file and directory names in this directory.
 *      The answer should in lexicographic order.
 * - void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist.
 *   If the middle directories in the path do not exist, you should create them as well.
 * - void addContentToFile(String filePath, String content)
 *      - If filePath does not exist, creates that file containing given content.
 *      - If filePath already exists, appends the given content to original content.
 * - String readContentFromFile(String filePath) Returns the content in the file at filePath.
 *
 *
 * Example 1:
 *    Input:
 *    ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
 *    [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
 *    Output:
 *    [null, [], null, null, ["a"], "hello"]
 *
 *    Explanation:
 *    FileSystem fileSystem = new FileSystem();
 *    fileSystem.ls("/");                         // return []
 *    fileSystem.mkdir("/a/b/c");
 *    fileSystem.addContentToFile("/a/b/c/d", "hello");
 *    fileSystem.ls("/");                         // return ["a"]
 *    fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 *
 *
 * Constraints:
 *
 * - 1 <= path.length, filePath.length <= 100
 * - path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
 * - You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
 * - You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
 * - You can assume that the parent directory for the file in addContentToFile will exist.
 *
 * Reference:
 * https://leetcode.com/problems/design-in-memory-file-system/description/
 */
public class InMemoryFileSystem {

    private final FsNode root;

    public InMemoryFileSystem() {
        this.root = new FsNode(""); // root directory "/"
    }

    public List<String> ls(String path) {
        FsNode currentNode = getFsNode(path);
        if (currentNode.isFile)
            return List.of(currentNode.name);
        else
            return currentNode.children.keySet().stream().sorted().toList();
    }

    public void mkdir(String path) {
        List<String> tokens = tokenize(path);
        FsNode currentNode = root;
        for (String token : tokens) {
            currentNode.children.putIfAbsent(token, new FsNode(token));
            currentNode = currentNode.children.get(token);
        }
    }

    public void addContentToFile(String filePath, String content) {
        List<String> tokens = tokenize(filePath);
        String fileName = tokens.get(tokens.size() - 1);
        FsNode dirNode = getFsNode(tokens.stream().limit(tokens.size() - 1).toList());
        if (dirNode.children.containsKey(fileName) && dirNode.children.get(fileName).isFile) {
            dirNode.children.get(fileName).content += content;
        } else {
            FsNode file = new FsNode(fileName);
            file.isFile = true;
            file.content = content;
            dirNode.children.put(fileName, file);
        }
    }

    public String readContentFromFile(String filePath) {
        FsNode file = getFsNode(filePath);
        if (file.isFile) {
            return file.content;
        }
        return null;
    }

    private FsNode getFsNode(List<String> tokens) {
        FsNode currentNode = root;
        for (String token : tokens) {
            currentNode = currentNode.children.get(token);
        }
        return currentNode;
    }

    private FsNode getFsNode(String path) {
        List<String> tokens = tokenize(path);
        return getFsNode(tokens);
    }

    private List<String> tokenize(String path) {
        return Arrays.stream(path.split("/")).filter(s -> !s.isBlank()).toList();
    }

    private static class FsNode {
        private String name;
        private boolean isFile;
        private String content;
        private Map<String, FsNode> children;

        public FsNode(String name) {
            this.name = name;
            this.isFile = false;
            this.children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        System.out.println(fileSystem.ls("/"));
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fileSystem.ls("/"));
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d"));
    }

}
