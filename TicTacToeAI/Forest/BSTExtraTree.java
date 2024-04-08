package Forest;

/*
 * This class:
 *   - IS-A BSTTree
 *       : Each node HAS-A value
 *       : has a max of two children
 *       : is "sorted" as a Binary Search Tree is sorted
 *   - assures that upon creation that we count all the children nodes
 */
public class BSTExtraTree extends BSTTree {

    /**
     * Create a new node of the correct Base Type for this Tree.
     */
    @Override
    public BSTNode createNewNode(int value) {
        return new BSTExtraNode(value);
    }

    public void info(BSTExtraNode node) {
        if (node == null) {
            return;
        }
        node.countChildren();
        node.countHeight();
        node.sumTotal();
        info((BSTExtraNode) node.getLeft());
        info((BSTExtraNode) node.getRight());
    }

    public static Tree createSomeTree() {
        // TODO: Implement this to create a BSTExtraTree
        // Create an empty tree
        // Add 10-30 nodes with random values
        //
        // Call these after the Tree is created. Call on root node.
        // root.countChildren();
        // root.countHeight();
        // root.sumTotal();

        // This is a placeholder that will be replaced and return the Tree
        // you just created.
        BSTExtraTree tree = new BSTExtraTree();
        for (int i = 0; i < 15; i++) {
            tree.add();
        }

        BSTExtraNode root = (BSTExtraNode) tree.getRoot();
        tree.info(root);
        return tree;
    }
}