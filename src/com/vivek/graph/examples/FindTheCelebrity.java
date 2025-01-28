package com.vivek.graph.examples;

import java.util.*;

/**
 * 277. Find The Celebrity
 *
 * Suppose you are at a party with n people labeled from 0 to n - 1 and among them, there may exist one celebrity.
 * The definition of a celebrity is that all the other n - 1 people know the celebrity, but the celebrity does not know any of them.
 *
 * Now you want to find out who the celebrity is or verify that there is not one. You are only allowed to ask questions like:
 * "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one)
 * by asking as few questions as possible (in the asymptotic sense).
 *
 * You are given an integer n and a helper function bool knows(a, b) that tells you whether a knows b.
 * Implement a function int findCelebrity(n). There will be exactly one celebrity if they are at the party.
 *
 * Return the celebrity's label if there is a celebrity at the party. If there is no celebrity, return -1.
 *
 * Note that the n x n 2D array graph given as input is not directly available to you,
 * and instead only accessible through the helper function knows. graph[i][j] == 1 represents person i knows person j,
 * wherease graph[i][j] == 0 represents person j does not know person i.
 *
 * Example 1:
 *
 *              0,
 *           ,/   \
 *          1 ,--- 2
 *
 *  where , means direction
 *
 *  Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
 * Output: 1
 * Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j,
 * otherwise graph[i][j] = 0 means person i does not know person j.
 * The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
 *
 * Example 2:
 *           ,0
 *          /   \,
 *        1 ,--- 2
 *
 * Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
 * Output: -1
 * Explanation: There is no celebrity.
 *
 * Approach:
 *
 * Approach 0: Brute Force O(n^2)
 * 1. Find indegree for all nodes and similarly outdegree
 * 2. iterate over indegree to find the node with n - 1 connections
 * 3. check id this candidate doesn't have any outdegree
 * 4. return if there is only one candidate remaining
 *  Time Complexity: O(n^2)
 *  Space Complexity : O(n)
 *
 * Approach 1: Brute Force
 *
 * As per the problem statement, for a given person i, we can check whether or not i is a celebrity by using the knows(...)
 * API to see if everybody knows i, and that i know nobody.
 *
 * Therefore, the simplest way of solving this problem is to go through each of the people in turn, and check whether or not they are a celebrity.
 *
 * Algorithm:
 * It's best to define a separate isCelebrity(...) function that takes the id number of a specific person and
 * returns true if they are a celebrity and false if not. This avoids the need for complex loop-break conditions, thus keeping the code cleaner.
 *
 * One edge case we need to be cautious of is not asking person i if they know themselves.
 * This can be handled by a check for i == j at the start of the main loop of isCelebrity(...) and then simply continue-ing when it is true.
 *
 * Approach 2: Logical Deduction
 *
 * We can do far better than the above approach. Let's start by looking at another way of representing the problem,
 * which is a great way for approaching it in an interview. What we actually have in this problem is a graph,
 * where a directed edge going from person A to person B means that we have confirmed that A knows B.
 *
 * On the graph representation, a celebrity is a person who has exactly n - 1 directed edges going in (everybody knows them) and 0 edges going out (they know nobody).
 *
 * On the first example we looked at above, person 4 is a celebrity because they have 5 directed edges going in, which is n - 1.
 * They have no directed edges going out. Note that 3 is not a celebrity because they have 5 outgoing edges, not 5 ingoing.
 *
 * On the second example, there is no celebrity. Person 4 is not a celebrity, because person 2 doesn't know them. There are only n - 2 directed edges going into 4.
 *
 * On the third example, there is also no celebrity. Person 0 is not a celebrity, because they know person 5, as represented by the directed edge going from 0 to 5.
 *
 * At the start, we only know the nodes of the graph. The edges are all hidden. We can "uncover" edges by making calls to the knows(...) API.
 * In the first approach, we uncovered all the edges this way. So, the question we need to ask now is... was it actually
 * necessary to uncover all of them? A good way to answer this question in an interview is to work through an example on the whiteboard,
 * where you decide which edges you want to ask for, and then draw them as you go.
 *
 * When you do your own example, you'll of course need to know what the full graph behind your example is, or at least the important aspects of it,
 * but you also need to focus on what information you've "uncovered" by using the knows(...) API.
 *
 * The following algorithm can, therefore, be used to rule out n - 1 of the people in O(n) time.
 * We start by guessing that 0 might be a celebrityCandidate, and then we check if 0 knows 1 (within the loop).
 * If true, then we know 0 isn't a celebrity (they know somebody), but 1 might be.
 * We update the celebrityCandidate variable to 1 to reflect this. Otherwise, we know 1 is not a celebrity (somebody doesn't know them),
 * but we haven't ruled out 0, yet, so keep them as the celebrityCandidate. Whoever we kept is then asked if they know 2, and so forth.
 *
 * celebrity_candidate = 0
 * for i in range(1, n):
 *     if knows(celebrity_candidate, i):
 *         celebrity_candidate = i
 *
 * At the end, the only person we haven't ruled out is in the celebrityCandidate variable.
 * But do we actually know for sure that this person is a celebrity? (Remember, it's possible there's no celebrity, in which case we'd return -1).
 * Nope! It's still possible that 0 doesn't know 4, or perhaps 4 knows 3. We can't rule these possibilities out from the information we have uncovered so far.
 * So, what can we do? We can use our isCelebrity(...) function on 4 to check whether or not they are a celebrity. If they are, our function will return 4.
 * If not, then it should return -1.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Reference:
 * https://leetcode.com/problems/find-the-celebrity/description/
 */
public class FindTheCelebrity {

    public static void main(String[] args) {
        {
            int[][] graph = {
                    {1,1,0},
                    {0,1,0},
                    {1,1,1}
            };

            Celebrity celebrity = new Celebrity(graph);
            System.out.println(celebrity.findCelebrity(3)); // Expected: 1
        }
        {
            int[][] graph = {
                    {1, 1, 0},
                    {0, 1, 1},
                    {0, 1, 1}
            };

            Celebrity celebrity = new Celebrity(graph);
            System.out.println(celebrity.findCelebrity(3));
        }
    }

    static class Celebrity extends Relation {

        public Celebrity(int[][] graph) {
            super(graph);
        }

        public int findCelebrity(int n) {
            int candidate = 0;

            // find possible celebrity candidate
            for (int i = 0; i < n; i++) {
                if (knows(candidate, i))
                    candidate = i;
            }

            // check if this candidate doesn't know anyone
            for (int i = 0; i < n; i++) {
                if (i != candidate) {
                    if (knows(candidate, i) || !knows(i, candidate))
                        return -1;
                }
            }

            return candidate;
        }

        public int findCelebrityBruteForce(int n) {
            Map<Integer, Integer> indegree = new HashMap<>();
            Map<Integer, Integer> outdegree = new HashMap<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        if (knows(j, i)) {
                            indegree.put(i, indegree.getOrDefault(i, 0) + 1);
                            outdegree.put(j, outdegree.getOrDefault(j, 0) + 1);
                        }
                    }
                }
            }

            List<Integer> celebrities = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
                if (entry.getValue() == n - 1 && !outdegree.containsKey(entry.getKey()))
                    celebrities.add(entry.getKey());
            }

            return celebrities.size() == 1 ? celebrities.get(0) : -1;
        }
    }

    static class Relation {

        private final int[][] graph;

        public Relation(int[][] graph) {
            this.graph = graph;
        }

        boolean knows(int a, int b) {
            return graph[a][b] == 1;
        }
    }

}
