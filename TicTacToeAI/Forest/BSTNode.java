package Forest;

/*
 * This class:
 *    - is a node for a Binary Search Tree... a BSTNode.
 *    - inherits from Node so that the BST can be drawn.
 *    - IS-A Node that HAS-A integer
 * 
 * This class will:
 *   - provide an abstraction for Left and Right children.
 *   - have getters/setters for Left/Right children.
 *   - have a default constructor to hold a simple zero integer value.
 *   - have a constructor to set this node to a specific integer value.
 *   - implement Comparable<> for convenient comparisons.
 *   - override toString() to display information nicely.
 *
 */
public class BSTNode extends Node implements Comparable<BSTNode> {

    // TODO: add instance fields as needed
    private int value;
    public BSTNode() {
        this(0);
    }

    public BSTNode(int value) {
        super();
        // Add two null children nodes
        getChildren().add(null);
        getChildren().add(null);
        // add value to this node's state
        this.value = value;
    }

    public void setLeft(BSTNode node) {
        getChildren().set(0, node);
    }

    public void setRight(BSTNode node) {
        getChildren().set(1, node);
    }

    public BSTNode getLeft() {
        return (BSTNode) getChildren().get(0);
    }

    public BSTNode getRight() {
        return (BSTNode) getChildren().get(1);
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public void clickEvent() {
        // print this sub-tree in-order
        // processInOrder(this);
        // System.out.println(nodeCounts(this));
        // System.out.println(this.nodeCount());
        // System.out.println(sums(this));
        System.out.println(getHeight(this));

    }

    public int getHeight(BSTNode node) {
        if (node == null) { 
            return 0;
        }
        int left = nodeCounts(node.getLeft());
        int right = nodeCounts(node.getRight());
        return Math.max(left, right);
    }

    public int sums(BSTNode node) {
        if (node == null) { 
            return 0;
        }
        int left = sums(node.getLeft());
        int right = sums(node.getRight());
        return left + right + node.getValue();
    }

    public int nodeCount() {
        // EXIT CASE - BASE CASE
        int left = 0;
        if (this.getLeft() != null) {
            left = this.getLeft().nodeCount();
        }
        int right = 0;
        if (this.getRight() != null) {
            right = this.getRight().nodeCount();
        }
        return left + right + 1;
    }

    public int nodeCounts(BSTNode node) {
        // EXIT CASE - BASE CASE
        if (node == null) {
            return 0;
        }
        // ask left child
        int left = nodeCounts(node.getLeft());
        // ask right child
        int right = nodeCounts(node.getRight());
        return left + right + 1;
    }

    public void processInOrder(BSTNode node) {
            // Exit Case!!!
        if (node == null) { 
            return;
        }
        processInOrder(node.getLeft()); // recursion!
        System.out.print(node.value + " ");
        processInOrder(node.getRight());// recursion!

    }

    // override toString()
    @Override
    public String toString() {
        return "  " + value;
    }

    @Override
    public int compareTo(BSTNode o) {
        // Not yet implemented
        return this.value - o.getValue();
    }
}
