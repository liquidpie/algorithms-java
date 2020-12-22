package com.vivek.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Segment Tree can efficiently answer range queries.
 * One such range query is the problem of finding the index of the minimum element in an array within range [i, j].
 * This is more commonly known as the Range Minimum Query (RMQ) problem.
 *
 * Segment Tree, which is another way to arrange data in a binary tree. There are several ways to implement the
 * Segment Tree. Our implementation uses the same concept as the 1-based compact array in
 * the binary heap where we use List<Integer> st to represent the binary tree.
 * Index 1 (skipping index 0) is the root and the left and right children of index p are index 2 × p and (2 × p) + 1 respectively.
 * The value of st[p] is the RMQ value of the segment associated with index p.
 * The root of segment tree represents segment [0, n-1]. For each segment [L,
 *
 * The root of segment tree represents segment [0, n-1]. For each segment [L, R] stored
 * in index p where L != R, the segment will be split into [L, (L+R)/2] and [(L+R)/2+1, R]
 * in a left and right vertices. The left sub-segment and right sub-segment will be stored in index
 * 2×p and (2×p)+1 respectively. When L = R, it is clear that st[p] = L (or R). Otherwise, we
 * will recursively build the segment tree, comparing the minimum value of the left and the right
 * sub-segments and updating the st[p] of the segment.
 *
 * If the array is static (i.e. unchanged after it is instantiated), then using a Segment
 * Tree to solve the RMQ problem is overkill as there exists a Dynamic Programming (DP)
 * solution that requires O(n log n) one-time pre-processing and allows for O(1) per RMQ.
 */
public class SegmentTree {

    private final int n;
    private final List<Integer> st;
    private final List<Integer> array;

    SegmentTree(final List<Integer> array) {
        this.array = array;
        this.n = array.size();
        this.st = new ArrayList<>(Collections.nCopies(4 * n, 0));
        build(1, 0, n - 1);
    }

    int rmq(int i, int j) {
        return rmq(1, 0, n - 1, i , j);
    }

    private int left(int p) {
        return p << 1;
    }

    private int right(int p) {
        return (p << 1) + 1;
    }

    private void build(int p, int l, int r) {
        if (l == r) {
            st.set(p, l);
        }
        else {
            build(left(p), l, (l + r) / 2);
            build(right(p), (l + r) / 2 + 1, r);
            int p1 = st.get(left(p));
            int p2 = st.get(right(p));
            st.set(p, array.get(p1) <= array.get(p2) ? p1 : p2);
        }
    }

    private int rmq(int p, int l, int r, int i, int j) {
        if (i > r || j < l) {  // current segment outside query range
            return -1;
        }
        if (i <= l && j >= r) {  // inside query range
            return st.get(p);
        }

        // compute the min position in the left and right part of the interval
        int p1 = rmq(left(p), l, (l + r) / 2, i, j);
        int p2 = rmq(right(p),(l + r) / 2 + 1, r, i, j);

        if (p1 == -1) return p2;  // if we try to access segment outside query
        if (p2 == -1) return p1;  // same as above
        return array.get(p1) <= array.get(p2) ? p1 : p2;
    }

    public static void main(String[] args) {
        var array = List.of(18, 17, 13, 19, 15, 11, 20);
        var tree = new SegmentTree(array);
        System.out.println(tree.rmq(1, 3));  // answer = index 2
        System.out.println(tree.rmq(4, 6));  // answer = index 5
    }

}
