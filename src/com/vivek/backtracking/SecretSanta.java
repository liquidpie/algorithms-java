package com.vivek.backtracking;

import java.util.*;

/**
 * Designing a Secret Santa program with rules for couples requires ensuring that no person is assigned their own partner
 * while maintaining the core Secret Santa functionality of randomly assigning gift recipients. Here's a solution:
 *
 * Requirements:
 * Each participant is randomly assigned another participant to give a gift.
 * No one can be assigned to themselves.
 * No one can be assigned to their partner if they're a couple.
 *
 * Example:
 *
 * participants = ["Alice", "Bob", "Charlie", "Dana", "Eve", "Frank"]
 * couples = {
 *     "Alice": "Bob",
 *     "Bob": "Alice",
 *     "Charlie": "Dana",
 *     "Dana": "Charlie",
 *     "Eve": "Frank",
 *     "Frank": "Eve"
 * }
 *
 * Approach 0: Shifting the array (when couples constraint is not applied)
 *
 * Approach 1: Using a queue
 *
 * - Iterate for each participant
 * - Get participant from the queue, if it satisfies the constraints, we move to next participant
 * - If not, we check another participant from the queue, adding the previous participant to the end of the queue
 *
 * Time Complexity: O(n^2)
 *
 * Approach 2: Optimized Approach Using Backtracking
 * Key Idea: Instead of random shuffling with retries, the program incrementally builds a valid assignment by considering the constraints at each step.
 * Input:
 * A list of participants.
 * A mapping of couples.
 * Output:
 * A valid assignment of gift-givers and receivers.
 * Algorithm Steps
 * Track Constraints:
 *
 * Maintain a set of available participants to assign as receivers.
 * Ensure a participant is not their own receiver or their partner's receiver.
 * Backtracking:
 *
 * Assign a receiver to each participant.
 * If a constraint is violated, backtrack and try a different receiver.
 * Terminate Early:
 *
 * Once all participants are assigned valid receivers, terminate the algorithm.
 *
 * Complexity ->
 * Time Complexity:
 *      - Worst-case: O(n!), where n is the number of participants, as all permutations may be checked.
 *      - Optimized with constraints: Significantly less than n! due to pruning invalid assignments early.
 * Space Complexity:
 *      O(n) for the recursive stack and tracking assignments.
 *
 * Reference:
 * https://github.com/volkodavs/secret-santa
 */
public class SecretSanta {

    public static void main(String[] args) {
        List<String> participants = Arrays.asList("Alice", "Bob", "Charlie", "Dana", "Eve", "Frank");
        Map<String, String> couples = new HashMap<>();
        couples.put("Alice", "Bob");
        couples.put("Bob", "Alice");
        couples.put("Charlie", "Dana");
        couples.put("Dana", "Charlie");
        couples.put("Eve", "Frank");
        couples.put("Frank", "Eve");

        secretSantaUsingQueue(participants, couples);
    }

    static void secretSantaUsingQueue(List<String> participants, Map<String, String> couples) {
        Map<String, String> assignments = new HashMap<>();

        Queue<String> queue = new LinkedList<>(participants);

        for (String giver : participants) {
            while (!queue.isEmpty()) {
                String receiver = queue.poll();

                if (!giver.equals(receiver) && !couples.get(giver).equals(receiver)) {
                    assignments.put(giver, receiver);
                    break;
                } else {
                    queue.add(receiver);
                }
            }
        }

        System.out.println("Secret Santa Assignments:");
        for (Map.Entry<String, String> entry : assignments.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }


    static void secretSantaUsingBacktracking(List<String> participants, Map<String, String> couples) {
        Map<String, String> assignments = new HashMap<>();
        Set<String> used = new HashSet<>();
        boolean found = backtrack(participants, couples, assignments, used, 0);

        if (found) {
            System.out.println("Secret Santa Assignments:");
            for (Map.Entry<String, String> entry : assignments.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        } else {
            System.out.println("No valid Secret Santa assignments found.");
        }
    }

    private static boolean backtrack(List<String> participants, Map<String, String> couples, Map<String, String> assignments, Set<String> used, int start) {
        if (start == participants.size())
            return true;

        String giver = participants.get(start);
        for (String receiver : participants) {
            if (used.contains(receiver) || giver.equals(receiver) || couples.get(giver).equals(receiver))
                continue;

            assignments.put(giver, receiver);
            used.add(receiver);
            if (backtrack(participants, couples, assignments, used, start + 1))
                return true;
            assignments.remove(giver);
            used.remove(receiver);
        }

        return false; // No valid assignment found for this giver
    }

}
