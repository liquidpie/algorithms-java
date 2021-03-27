package com.vivek.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fenwick Tree also known as Binary Indexed Tree (BIT). It is a useful data structure for implementing dynamic cumulative frequency tables.
 * The cumulative frequency table can also be used as a solution to the Range Sum Query
 * (RSQ) problem
 *
 * We can then obtain the answer to the RSQ for an arbitrary range RSQ(i, j) when i != 1 by subtracting RSQ(1, j) - RSQ(1, i - 1).
 * For example, RSQ(4, 6) = RSQ(1, 6) - RSQ(1, 3)
 *
 * Instead of using a Segment Tree to implement a dynamic cumulative frequency table,
 * we can implement the far simpler Fenwick Tree instead
 *
 * Let the name of the Fenwick Tree array be ft. Then, the element at index i is responsible
 * for elements in the range [i-LSOne(i)+1..i] and ft[i] stores the cumulative frequency
 * of elements {i-LSOne(i)+1, i-LSOne(i)+2, i-LSOne(i)+3, .., i}.
 *
 * ft[4] = 2 is responsible for range [4-4+1..4] = [1..4], ft[6] = 5 is responsible
 * for range [6-2+1..6] = [5..6], ft[7] = 2 is responsible for range [7-1+1..7] = [7..7],
 * ft[8] = 10 is responsible for range [8-8+1..8] = [1..8] etc32.
 *
 * With such an arrangement, if we want to obtain the cumulative frequency between
 * [1..b], i.e. rsq(b), we simply add ft[b], ft[b’], ft[b’’], . . . until index bi is 0. This
 * sequence of indices is obtained via subtracting the Least Significant One-bit via the bit manipulation
 * expression: b’ = b - LSOne(b). Iteration of this bit manipulation effectively
 * strips off the least significant one-bit of b at each step. As an integer b only has O(log b)
 * bits, rsq(b) runs in O(log n) time when b = n.
 * For ex: rsq(6) = ft[6] + ft[4]
 *
 * With rsq(b) available, obtaining the cumulative frequency between two indices [a..b]
 * where a != 1 is simple, just evaluate rsq(a, b) = rsq(b) - rsq(a - 1). For example, if
 * we want to compute rsq(4, 6), we can simply return rsq(6) - rsq(3) = (5+2) - (0+1)
 * = 7 - 1 = 6. Again, this operation runs in O(2 × log b) ≈ O(log n) time when b = n.
 *
 * When updating the value of the element at index k by adjusting its value by v (note
 * that v can be either positive or negative), i.e. calling adjust(k, v), we have to update
 * ft[k], ft[k’], ft[k’’], . . . until index ki exceeds n. This sequence of indices are obtained
 * via this similar iterative bit manipulation expression: k’ = k + LSOne(k).
 *
 * In summary, Fenwick Tree supports both RSQ and update operations in just O(n) space and
 * O(log n) time given a set of m integer keys that ranges from [1..n].
 *
 */
public class FenwickTree {

    private final List<Integer> ft;

    FenwickTree(final int n) {
        this.ft = new ArrayList<>(Collections.nCopies(n + 1, 0));
    }

    // Returns RSQ(1, b)
    int rsq(int b) {
        int sum = 0;
        for (; b > 0; b -= LSOne(b)) {
            sum += ft.get(b);
        }

        return sum;
    }

    // Returns RSQ(a, b)
    int rsq(int a, int b) {
        return rsq(b) - (a == 1 ? 0 : rsq(a -1 ));
    }

    // Adjusts value of the k-th element by v (v can be +ve/inc or -ve/dec)
    void adjust(int k, int v) {
        for (; k < ft.size(); k += LSOne(k)) {
            ft.set(k, ft.get(k) + v);
        }
    }

    private int LSOne(int k) {
        return k & -k;
    }

    public static void main(String[] args) {
        int[] f = {2, 4, 5, 5, 6, 6, 6, 7, 7, 8, 9};
        FenwickTree ft = new FenwickTree(10);
        for (int i = 0; i < 11; i++) {
            ft.adjust(f[i], 1);
        }

        System.out.println(ft.rsq(1,1));
        System.out.println(ft.rsq(1,6));
    }

}
