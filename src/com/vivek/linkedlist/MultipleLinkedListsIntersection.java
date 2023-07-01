package com.vivek.linkedlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Linked Lists Intersection
 *
 * You are given a collection of singly-linked lists (SLLs).
 * Return true if any of them share a common node (or “intersect”), or false otherwise.
 *
 * Additional notes
 *
 *     Please don’t use recursion.
 *     Assume the SLLs might be very large in length (in the millions).
 *     Stop traversing and return immediately if you detect a common node.
 *     If a cycle is detected, please throw an error.
 *     Aim to be as efficient as possible from a time complexity perspective.
 *     Implement this function with this signature: DoLinkedListsIntersect(Collection<SinglyLinkedList>) returns bool
 *
 * Input:
 *
 *     The first lines of the input will describe the singly-linked-lists in a directed acyclic graph (DAG) format. The graph description language is a similar idea to the GraphViz graph description language, see: https://en.wikipedia.org/wiki/DOT_(graph_description_language).
 *     Each node is described as a string token, which is a unique identifier for the node. So “a->b” means a DAG with node “a” directionally connected to node “b”.
 *     As we are describing singly-linked-lists, take it as a given that the out degree of every node is either zero or one.
 *     After each of the edges has been described, then each subsequent line will include set of SLL starting nodes to traverse from.
 *
 * Output:
 *
 *     For each SLL print 'True' or 'False' based on the return value of your function
 *     true prints True
 *     false prints False
 *     On throwing an error print Error Thrown!
 *
 * Refer question at https://www.youtube.com/watch?v=zCezJ8QkUL4&ab_channel=MachineLearningStreetTalk &
 * https://codereview.stackexchange.com/questions/283540/detecting-intersections-of-a-collection-of-singly-linked-lists
 *
 */
public class MultipleLinkedListsIntersection {

    public static void main(String[] args) throws Exception {
//        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
//        BufferedReader in = new BufferedReader(reader);
//        String line;
        List<String> lines = new ArrayList<>();
//        while ((line = in.readLine()) != null) {
//            lines.add(line);
//        }

        String[] arr = {
                "a->b",
                "r->s",
                "b->c",
                "x->c",
                "q->r",
                "y->x",
                "w->z",
                "y, z, a, r"
        };

        lines = Arrays.asList(arr);

        Collection<List<SinglyLinkedList>> lists = buildSLL(lines);
        for (List<SinglyLinkedList> list : lists) {
            boolean output = doLinkedListsIntersect(list);
            System.out.println(output ? "True" : "False");
        }

    }

    private static boolean doLinkedListsIntersect(List<SinglyLinkedList> lists) throws Exception {

        int k = lists.size();
        if (k == 0)
            return false;

        int last = k - 1;
        while (last != 0) {
            int i  = 0;
            int j = last;

            while (i < j) {
                boolean out = doLinkedListsIntersect(lists.get(i), lists.get(j));
                if (out)
                    return true;

                i++;
                j--;

                if (i >= j)
                    last = j;
            }
        }

        return false;

    }

    private static boolean doLinkedListsIntersect(SinglyLinkedList sll1, SinglyLinkedList sll2) throws Exception {
        Set<SinglyLinkedList.Node> table = new HashSet<>();

        SinglyLinkedList.Node list1 = sll1.getHead();
        SinglyLinkedList.Node list2 = sll2.getHead();
        while(list1 != null) {
            if (table.contains(list1))
                throw new Exception("Cycle detected");
            table.add(list1);
            list1 = list1.getNext();
        }

        while (list2 != null) {
            if (table.contains(list2))
                return true;
            list2 = list2.getNext();
        }

        return false;
    }

    private static Collection<List<SinglyLinkedList>> buildSLL(List<String> lines) {
        Map<String, SinglyLinkedList.Node> map = new HashMap<>();
        int i;
        for (i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains(","))
                break;

            String[] splits = line.split("->");

            SinglyLinkedList.Node node1 = map.getOrDefault(splits[0], new SinglyLinkedList.Node(splits[0]));
            SinglyLinkedList.Node node2 = map.getOrDefault(splits[1], new SinglyLinkedList.Node(splits[1]));
            node1.setNext(node2);
            if (!map.containsKey(splits[0])) {
                map.put(splits[0], node1);
            }
            if (!map.containsKey(splits[1])) {
                map.put(splits[1], node2);
            }
        }

        Collection<List<SinglyLinkedList>> lists = new ArrayList<>();
        for (; i < lines.size(); i++) {
            List<SinglyLinkedList> list = new ArrayList<>();
            String line = lines.get(i);
            String[] splits = line.split("\\s*,\\s*");
            for (String token : splits)
                list.add(new SinglyLinkedList(map.get(token)));

            lists.add(list);
        }

        return lists;
    }


}
