package com.vivek.backtracking;

import com.vivek.trie.Trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Todo
 *
 * Given an NxN grid of characters and a dictionary, 5nd all words which can be made from the characters in the grid, which are
 * present in the given dictionary.
 *
 * A word can start and end at any character in the grid. The next character must be adjacent to the previous
 * character in any of the directions: up, down, left, right and diagonal.
 * The character at each position in the grid can be used only once while making a word.
 *
 * Suppose we have a 3 × 3 grid and a dictionary as input. Six words can be made from the grid which are
 * present in the dictionary. Green highlighted characters indicate that they form the word EAT in the grid
 * which is also present in the dictionary.
 *
 * C A T
 * R R E
 * T O N
 *
 * In the grid, we start at character E, then move to upper diagonal for A and then right to T to make EAT.
 * Dictionary
 * [cat cater art toon moon not eat ton]
 * Output Words
 * [not cat art cater ton eat]
 *
 * Approach:
 *
 * This is a backtracking problem. We start with one character in the grid and try to make as many words as
 * possible starting with that character. We repeat the same process for all characters in the grid.
 * To find all the words starting with a character, we use an algorithm very similar to depth-first search. As
 * every character from the grid can appear only once in a word, we’ll need to maintain a boolean matrix to
 * indicate if the corresponding character in the grid is used to make this word.
 *
 * We can really speed up our algorithm if we have a prefix method available for our dictionary.
 *
 * Let’s consider an example below where we want to find all words starting with character E (in 2 row and
 * 3 column of the example grid above).
 *
 * For this example we’ll assume that the dictionary has a prefix() method which returns true if a
 * string is a prefix of a valid word.
 *  - We’ll start with E. From our dictionary we know that E is a prefix of a word, so we’ll keep E and
 *      continue after marking E as used in the boolean matrix.
 *  - E has 5 unused neighbors, i.e., A, T, R, O, N. First we’ll proceed with first neighbor i.e. A. EA is a
 *      valid prefix.
 *  - A has 4 unused neighbors i.e. C, T, R, R. E is a neighbor of A, but we’ll not consider it as it is already
 *      used. First, we’ll try C but EAC is neither a valid word nor a prefix to valid words in our dictionary. So,
 *      we’ll move to the next neighbor, T and EAT is a valid word.
 *  - We’ll add EAT to the output words list and then continue to neighbors of T. But no neighbor of T can
 *      be part of a prefix or a valid word, so we’ll backtrack and continue with remaining neighbors of A from
 *      the previous step.
 *  - In this case, they are R, R and EAR, EAR is neither a valid word nor prefix a to a valid word in the
 *      dictionary, so we’ll backtrack.
 *  - While backtracking we always reset used flags so that this character can be used in some other path.
 *      We’ll backtrack once again and will look at other neighbors of E, i.e., T, R, O, N. We’ll continue until all
 *      paths starting from E are explored.
 *
 * Time Complexity: The runtime complexity of this solution is exponential, O(N^N), where N is the dimension of the grid.
 *                      The recurrence relation for the runtime complexity is:
 *                          T(N) = N T(N − 1)
 * Space Complexity: O(n^2)
 *
 * Reference: https://www.geeksforgeeks.org/boggle-set-2-using-trie
 */
public class Boggle {

    char[][] grid;
    Trie trie;
    boolean[][] visited;

    Boggle(char[][] grid, Set<String> dictionary) {
        this.grid = grid;
        this.trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }
        visited = new boolean[grid.length][grid.length];
    }

    Set<String> findAllWords() {
        Set<String> words = new HashSet<>();
        Trie.TrieNode root = trie.getRoot();

        String str = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                // we start searching for word in dictionary if we found a character which is child of Trie root
                if (root.getChild(grid[i][j] - 'a') != null) {
                    str = str + grid[i][j];
                    findWordRec(root.getChild(grid[i][j] - 'a'), i, j, str, words);
                    str = "";
                }
            }
        }
        return words;
    }

    private void findWordRec(Trie.TrieNode root, int i, int j, String current, Set<String> words) {
        if (root.isEndOfWord())
            words.add(current);

        // Neighbor indices
        int[] row = {-1, 1, 0, 0, -1};
        int[] col = {0, 0, -1, 1,  1};

        if (isSafe(i, j)) {
            visited[i][j] = true;

            // traverse all child of current root
            for (int k = 0; k < 26; k++) { // 26 is the number children in Trie for each alphabet
                if (root.getChild(k) != null) {
                    char ch = (char) (k + 'a');
                    // traverse for all neighbors and check if it's part of any prefix
                    for (int m = 0; m < 5; m++) {
                        if (isSafe(i + row[m], j + col[m]) && grid[i + row[m]][j + col[m]] == ch) {
                            current += ch;
                            findWordRec(root.getChild(k), i + row[m], j + col[m], current, words);
                        }
                    }
                }

            }
            // make current element unvisited
            visited[i][j] = false;
        }
    }

    private boolean isSafe(int i, int j) {
        return i >= 0 && i < grid.length &&
                j >= 0 && j < grid.length &&
                !visited[i][j];
    }


    public static void main(String[] args) {
        char[][] grid = {
                {'c', 'a', 't'},
                {'r', 'r', 'e'},
                {'t', 'o', 'n'}
        };

        String[] dict = {"cat", "cater", "cartoon", "art", "toon", "moon", "eat", "ton"};
        Set<String> dictionary = new HashSet<>(Arrays.asList(dict));
        Boggle boggle = new Boggle(grid, dictionary);
        Set<String> words = boggle.findAllWords();

        System.out.println(words);

    }

}
