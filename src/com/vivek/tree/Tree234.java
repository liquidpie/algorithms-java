package com.vivek.tree;

import java.util.Scanner;

/**
 * An implementation of 2-3-4 Multiway tree
 */
public class Tree234 {

    private Node root = new Node();

    /**
     Searching for a data item with a specified key is carried out by the find() routine. It
     starts at the root and at each node calls that node’s findItem() routine to see whether
     the item is there. If so, it returns the index of the item within the node’s item array.
     If find() is at a leaf and can’t find the item, the search has failed, so it returns –1
     */
    int find(int key) {
        Node curNode = root;
        int childNumber;

        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1) {
                return childNumber;
            } else if (curNode.isLeaf()) {
                return -1;
            } else {
                curNode = getNextChild(curNode, key);
            }
        }
    }

    /**
     The insert() method starts with code similar to find(), except that it splits a full
     node if it finds one. Also, it assumes it can’t fail; it keeps looking, going to deeper
     and deeper levels, until it finds a leaf node. At this point the method inserts the new
     data item into the leaf. (There is always room in the leaf; otherwise, the leaf would
     have been split.)
     */
    void insert(int dValue) {
        Node curNode = root;
        DataItem tempItem = new DataItem(dValue);

        while (true) {
            if (curNode.isFull()) {                        // if node full
                split(curNode);                            // split it
                curNode = curNode.getParent();

                curNode = getNextChild(curNode, dValue);
            } else if (curNode.isLeaf()) {
                break;
            } else {                                        // node is not full, not a leaf; so go to lower level
                curNode = getNextChild(curNode, dValue);
            }
        }

        curNode.insertItem(tempItem);                       // insert new DataItem
    }

    /**
     It is passed the node that will be split as an argument.
     First, the two rightmost data items are removed from the node and stored.
     Then the two rightmost children are disconnected; their references are also stored.
     A new node, called newRight, is created. It will be placed to the right of the node
     being split. If the node being split is the root, an additional new node is created: a
     new root.

     Next, appropriate connections are made to the parent of the node being split. It may
     be a pre-existing parent, or if the root is being split, it will be the newly created root
     node. Assume the three data items in the node being split are called A, B, and C.
     Item B is inserted in this parent node. If necessary, the parent’s existing children are
     disconnected and reconnected one position to the right to make room for the new
     data item and new connections. The newRight node is connected to this parent.

     Now the focus shifts to the newRight node. Data Item C is inserted in it, and child 2
     and child 3, which were previously disconnected from the node being split, are
     connected to it. The split is now complete, and the split() routine returns.
     */
    void split(Node node) {
        // split the node
        DataItem itemB, itemC;
        Node parent, child2, child3;
        int itemIndex;

        itemC = node.removeItem();                  // remove items from node
        itemB = node.removeItem();

        child2 = node.unlinkChild(2);               // remove children from node
        child3 = node.unlinkChild(3);

        Node newRight = new Node();

        if (node == root) {                         // if node is the root,
            root = new Node();                      // make new root
            parent = root;                          // root is our parent
            root.linkChild(0, node);      // connect to parent
        } else {                                    // node is not the root
            parent = node.getParent();              // get parent
        }

        // deal with parent
        itemIndex = parent.insertItem(itemB);       // item B to parent
        int n = parent.getNumItems();

        for (int i = n - 1; i > itemIndex; i--) {   // move parent’s childs
            Node temp = parent.unlinkChild(i);      // one child to the right
            parent.linkChild(i + 1, temp);
        }

        // connect newRight to parent
        parent.linkChild(itemIndex + 1, newRight);

        // deal with newRight
        newRight.insertItem(itemC);                 // item C to newRight
        newRight.linkChild(0, child2);    // connect to 0 and 1 on newRight
        newRight.linkChild(1, child3);
    }

    Node getNextChild(Node node, int value) {
        int numItems = node.getNumItems();
        int i;
        for (i = 0; i < numItems; i++) {
            if (value < node.getItem(i).dData) {
                return node.getChild(i);
            }
        }
        return node.getChild(i);
    }

    void displayTree() {
        reDisplayTree(root, 0, 0);
    }

    void reDisplayTree(Node thisNode, int level, int childNumber) {
        System.out.println("level=" + level + " child=" + childNumber + "");
        thisNode.displayNode();

        int numItems = thisNode.getNumItems();
        for (int i = 0; i < numItems + 1; i++) {
            Node nextNode = thisNode.getChild(i);
            if (nextNode != null)
                reDisplayTree(nextNode, level + 1, i);
            else
                return;
        }
    }


    ////////////////////
    // Schema classes //
    ////////////////////

    /**
     * Objects of the DataItem class represent the data items stored in nodes
     */
    static class DataItem {
        int dData;

        DataItem(int dData) {
            this.dData = dData;
        }

        @Override
        public String toString() {
            return Integer.toString(dData);
        }
    }

    /**
     * The Node class contains two arrays: childArray and itemArray. The first is four cells
     long and holds references to whatever children the node might have. The second is
     three cells long and holds references to objects of type DataItem contained in the
     node.

     Note that the data items in itemArray comprise an ordered array. New items are
     added, or existing ones removed, in the same way they would be in any ordered
     array.

     Items may need to be shifted to make
     room to insert a new item in order, or to close an empty cell when an item is
     removed.

     We’ve chosen to store the number of items currently in the node (numItems) and the
     node’s parent (parent) as fields in this class.

     */
    static class Node {
        private static final int ORDER = 4;
        private int numItems; // current number of data items in Node
        private Node parent;
        private Node[] childArray = new Node[ORDER]; // childs linked to Node
        private DataItem[] itemArray = new DataItem[ORDER - 1]; // data items in Node

        void linkChild(int childNum, Node child) {
            childArray[childNum] = child;
            if (child != null)
                child.parent = this;
        }

        Node unlinkChild(int childNum) {
            Node tempNode = childArray[childNum];
            childArray[childNum] = null;
            return tempNode;
        }

        Node getChild(int childNum) {
            return childArray[childNum];
        }

        Node getParent() {
            return parent;
        }

        boolean isLeaf() {
            return childArray[0] == null;
        }

        int getNumItems() {
            return numItems;
        }

        DataItem getItem(int index) {
            return itemArray[index];
        }

        boolean isFull() {
            return numItems == ORDER - 1;
        }

        int findItem(int key) {
            // return index of item within Node
            for (int i = 0; i < ORDER - 1; i++) {
                if (itemArray[i] == null)
                    break;
                else if (itemArray[i].dData == key)
                    return i;
            }
            return -1;
        }

        int insertItem(DataItem newItem) {
            // assumes node is not full
            numItems++;                                 // will add new item
            int newKey = newItem.dData;                 // key of new item

            for (int i = ORDER - 2; i >= 0; i--) {      // start on right,
                if (itemArray[i] == null)               // if item null,
                    continue;                           // go left one cell
                else {                                  // not null,
                    int itsKey = itemArray[i].dData;    // get its key
                    if (newKey < itsKey) {              // if it’s bigger
                        itemArray[i + 1] = itemArray[i];// shift it right
                    } else {
                        itemArray[i + 1] = newItem;     // insert new item
                        return i + 1;                   // return index
                    }
                }
            }

            itemArray[0] = newItem;
            return 0;
        }

        DataItem removeItem() {
            // remove largest item
            DataItem temp = itemArray[numItems - 1];
            itemArray[numItems - 1] = null;
            numItems--;
            return temp;
        }

        void displayNode() {
            for (int i = 0; i < numItems; i++) {
                System.out.print(itemArray[i].toString() + "/");
            }
            System.out.println();
        }
    }


    // Test 2-3-4 Tree Implementation
    public static void main(String[] args) {
        Tree234 theTree = new Tree234();
        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);

        Scanner in = new Scanner(System.in);

        while(true)
        {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, or find: ");
            String choice = in.next().trim();
            switch(choice) {
                case "s":
                    theTree.displayTree();
                    break;
                case "i":
                    System.out.print("Enter value to insert: ");
                    theTree.insert(in.nextInt());
                    break;
                case "f":
                    System.out.print("Enter value to find: ");
                    int value = in.nextInt();
                    int found = theTree.find(value);
                    if(found != -1)
                        System.out.println("Found "+value);
                    else
                        System.out.println("Could not find "+value);
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

}
