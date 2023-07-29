package com.vivek.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Write method logic for two function
 *
 *      withRoute(String path, String result) return void
 *      route(String path) return result
 *
 * https://leetcode.com/discuss/interview-question/object-oriented-design/2167390/atlassian-ood-design-a-middelware-router
 */
public class DesignRouter {

    public static void main(String[] args) {
        Router Router = new Router();

        Router.withRoute("/bar", "result");
        Router.route("/bar");

        Router.withRoute("/bar/abc", "abc");
        Router.route("/bar/abc");
        Router.route("/bar/abc/dd");

        Router.withRoute("/bar/abc/dd", "dd");
        Router.withRoute("/bar/abc/cde/dd", "ee");
        Router.route("/bar/abc/dd");
        Router.route("/bar/*/dd");
    }

    static class Router {

        static class TrieNode {
            Map<String, TrieNode> children;
            String value;
            boolean endOfWord;

            public TrieNode() {
                this.children = new HashMap<>();
                this.value = null;
                this.endOfWord = false;
            }
        }

        private final TrieNode root;

        public Router() {
            this.root = new TrieNode();
        }

        void withRoute(String path, String result) {
            if (path.isEmpty())
                throw new RuntimeException("path should not be empty");

            String[] pathArr = path.split("/");
            register(pathArr, result);
        }

        String route(String path) {
            if (path.isEmpty())
                throw new RuntimeException("path should not be empty");

            String[] pathArr = path.split("/");
            TrieNode findNode = find(pathArr, 0, root);
            System.out.println(findNode != null ? findNode.value : null);
            return findNode != null ? findNode.value : null;
        }

        private void register(String[] components, String result) {
            TrieNode crawlNode = root;
            for (String path : components) {
                if (!crawlNode.children.containsKey(path)) {
                    crawlNode.children.put(path, new TrieNode());
                }
                crawlNode = crawlNode.children.get(path);
            }

            crawlNode.value = result;
            crawlNode.endOfWord = true;
         }

         private TrieNode find(String[] components, int index, TrieNode crawlNode) {
             if (index == components.length) {
                 return crawlNode != null && crawlNode.endOfWord ? crawlNode : null;
             }

             TrieNode resultNode = null;
             if ("*".equals(components[index])) {
                 for (Map.Entry<String, TrieNode> entry : crawlNode.children.entrySet()) {
                     TrieNode tempNode = entry.getValue();
                     resultNode = find(components, index + 1, tempNode);
                     if (resultNode != null)
                         return resultNode;
                 }
             } else if (crawlNode != null && crawlNode.children != null &&
                        crawlNode.children.containsKey(components[index])) {

                crawlNode = crawlNode.children.get(components[index]);
                resultNode = find(components, index + 1, crawlNode);
             }

             return resultNode;
         }

    }

}
