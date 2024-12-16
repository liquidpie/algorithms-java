package com.vivek.heap;

import java.util.*;
import java.util.PriorityQueue;

/**
 * The system should accept the scores of players in the form of streaming events and
 * return the top k players with the highest score anytime.
 */
public class TopKLeaderboard {

    private static final int K = 3;
    private final Map<String, Player> playerScores;
    private final PriorityQueue<Player> minHeap;

    public TopKLeaderboard() {
        playerScores = new HashMap<>();
        minHeap = new PriorityQueue<>(Comparator.comparingInt(Player::getScore));
    }

    public void addPlayerScore(String id, int score) {
        // update hashmap
        playerScores.computeIfAbsent(id, k -> new Player(id)).addScore(score);

        // remove this player from heap
        minHeap.removeIf(player -> player.getId().equals(id));

        // add updated player to heap
        minHeap.add(playerScores.get(id));

        // if heap size exceeds k, remove the lowest score player
        if (minHeap.size() > K) {
            minHeap.poll();
        }
    }

    public List<String> topK() {
        // make a copy of minheap, iterate on the copy to add the items in List
        List<String> topK = new ArrayList<>(K);
        var tempMinHeap = new PriorityQueue<>(minHeap);
        while (!tempMinHeap.isEmpty()) {
            topK.add(0, tempMinHeap.poll().getId());
        }
        return topK;
    }

    private static class Player {
        String id;
        int score;

        public Player(String id) {
            this.id = id;
        }

        public void addScore(int score) {
            this.score += score;
        }

        public String getId() {
            return id;
        }

        public int getScore() {
            return score;
        }
    }

    public static void main(String[] args) {
        TopKLeaderboard leaderboard = new TopKLeaderboard();

        leaderboard.addPlayerScore("Player1", 73);
        leaderboard.addPlayerScore("Player2", 56);
        leaderboard.addPlayerScore("Player3", 39);
        leaderboard.addPlayerScore("Player4", 51);
        leaderboard.addPlayerScore("Player5", 41);
        leaderboard.addPlayerScore("Player6", 60);

        System.out.println(leaderboard.topK());

        leaderboard.addPlayerScore("Player7", 85);

        System.out.println(leaderboard.topK());

        leaderboard.addPlayerScore("Player8", 25);

        System.out.println(leaderboard.topK());

        leaderboard.addPlayerScore("Player9", 63);

        System.out.println(leaderboard.topK());

        leaderboard.addPlayerScore("Player1", 13);

        System.out.println(leaderboard.topK());
    }

}
