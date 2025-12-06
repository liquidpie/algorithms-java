package com.vivek.graph.pattern.bfs;

import java.util.*;

/**
 * 433. Minimum Genetic Mutation
 *
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 *
 * Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.
 *
 *     For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 *
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 *
 * Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.
 *
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 *
 * Example 1:
 *
 * Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
 * Output: 1
 *
 * Example 2:
 *
 * Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * Output: 2
 *
 * Solution:
 * It's same as Word Ladder problem
 *
 * Reference:
 * https://leetcode.com/problems/minimum-genetic-mutation
 */
public class MinimumGeneticMutation {

    public static void main(String[] args) {
        MinimumGeneticMutation helper = new MinimumGeneticMutation();
        String startGene = "AAAACCCC";
        String endGene = "CCCCCCCC";
        String[] bank = {"AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"};

        System.out.println(helper.minMutation(startGene, endGene, bank));
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        List<Character> chars = List.of('A', 'C', 'G', 'T');
        Set<String> genes = new HashSet<>();
        for (String gene : bank)
            genes.add(gene);

        if (!genes.contains(endGene))
            return -1;

        int mutations = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(startGene);
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            mutations++;
            for (int i = 0; i < qSize; i++) {
                StringBuilder gene = new StringBuilder(queue.poll());
                for (int j = 0; j < gene.length(); j++) {
                    char cache = gene.charAt(j);
                    for (char ch : chars) {
                        if (ch != cache) {
                            gene.setCharAt(j, ch);
                            String newGene = gene.toString();
                            if (newGene.equals(endGene))
                                return mutations;
                            if (genes.remove(newGene))
                                queue.add(newGene);
                        }
                        gene.setCharAt(j, cache);
                    }
                }
            }
        }

        return -1;
    }

}
