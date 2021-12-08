package com.vivek.graph.examples;

/**
 * Todo
 *
 * You are given a ‘m x n’ 2D grid initialized with these three possible values.
 *
 * -1 : A wall or an obstacle.
 * 0 : A gate.
 * INF : Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 *
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a Gate, that room should remain filled with INF.
 *
 * Example:
 * The grid-
 *
 * INF  -1   0   INF
 * INF  INF INF  -1
 * INF  -1  INF  -1
 *  0   -1  INF  INF
 *
 * Output-
 *
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 *
 * Explanation:
 * For position [0,0] nearest reachable gate is at position [3,0] which is at 3 units of distance,
 * similarly for the position [3,3] nearest reachable gate is at position [0,2] which is at 4 units of distance.
 *
 *  Approach:
 *        The idea is to use multisource Breadth-First Search.
 *        Consider each cell as a node and each boundary between any two adjacent cells be an edge.
 *        Number each cell from 1 to N*M.
 *        Now, push all the node whose corresponding cell value is 1 in the matrix in the queue.
 *        Apply BFS using this queue to find the minimum distance of the adjacent node.
 *  Algorithm:
 *      - Create a graph with values assigned from 1 to M*N to all vertices.
 *        The purpose is to store position and adjacent information.
 *      - Create an empty queue.
 *      - Traverse all matrix elements and insert positions of all 1s in queue.
 *      - Now do a BFS traversal of graph using above created queue.
 *      - Run a loop till the size of the queue is greater than 0 then extract the front node of the queue and
 *        remove it and insert all its adjacent and unmarked elements.
 *        Update the minimum distance as distance of current node +1 and insert the element in the queue.
 *
 *  Reference: https://www.geeksforgeeks.org/distance-nearest-cell-1-binary-matrix/
 */
public class DistanceToNearestGate {
    //  we can solve the problem using BFS. First stored all the gate positions in a queue and then from each gate position finds the distance to INF positions.

    private final int ROW;
    private final int COL;

    public DistanceToNearestGate(int ROW, int COL) {
        this.ROW = ROW;
        this.COL = COL;
    }
    
    // Function to create graph with Row * Col nodes considering each cell as a node and each boundary as an edge.
    private void createGraph() {
        
    }



}
