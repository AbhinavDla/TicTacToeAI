package Forest;

/*
 * This class:
 *   - IS-A BSTNode
 *       : Each node HAS-A value
 *       : is "sorted" as a Binary Search Tree is sorted
 *   - HAS-A count of how many total children (all descendants)
 */
public class BSTExtraNode extends BSTNode {
    // TODO:
    // - add children count instance field
    private int count;
    private int height;
    private int total;

    public BSTExtraNode(int value) {
        // Call super constructor!!
        super(value);

    }

    @Override
    public String toString() {
        return "      " + count + "\n      " + total + "\n      " + height;
    }

    public void countChildren() {
        // Not yet implemented
        this.count = this.nodeCount();

    }

    public void countHeight() {
        // Not yet implemented
        this.height = this.getHeight(this);

    }

    public void sumTotal() {
        // Not yet implemented
        this.total = this.sums(this);
    }

}