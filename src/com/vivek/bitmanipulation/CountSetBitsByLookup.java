package com.vivek.bitmanipulation;

/**
 * Created by VJaiswal on 26/09/17.
 */
public class CountSetBitsByLookup {

    public static void main(String[] args) {
        // counting set bits using a loop is a naive approach
        // for better performance, it is better to set a lookup table
        // lookup table has entries corresponding to numbers betweeen 0-256 bits

        int x = 567673;
        final int[] LOOKUP = initLookup();

        int count = LOOKUP[x & 0xff] +
                    LOOKUP[(x >> 8) & 0xff] +
                    LOOKUP[(x >> 16) & 0xff] +
                    LOOKUP[(x >> 24) & 0xff];

        System.out.println(count);

    }

    private static int[] initLookup() {
        int[] lookup = new int[256];
        lookup[0] = 0;
        for (int i = 1; i < 256; i++) {
            lookup[i] = (i & 1) + lookup[i / 2];
        }

        return lookup;
    }
}
