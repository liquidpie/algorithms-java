package com.vivek.bitmanipulation;

import java.util.BitSet;
import java.util.Scanner;

/**
 * John has discovered various rocks. Each rock is composed of various elements, and each element is represented
 * by a lower-case Latin letter from 'a' to 'z'. An element can be present multiple times in a rock.
 * An element is called a gem-element if it occurs at least once in each of the rocks.
 *
 * Given the list of  rocks with their compositions, display the number of gem-elements that exist in those rocks.
 *
 */
public class NbOfCommonCharInStrings {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            final int numElements = 26;
            BitSet bitSet = new BitSet(numElements);
            bitSet.set(0, numElements);
            for (int i = 0; i < n; i++) {
                String s = in.next();
                BitSet currBitSet = new BitSet(numElements);
                for (int j = 0; j < s.length(); j++) {
                    currBitSet.set(s.charAt(j) - 'a');
                }
                bitSet.and(currBitSet);
            }

            System.out.println(bitSet.cardinality());
        }
    }

}
