package Forest;

import java.util.List;

// Implements a basic Tree 
public class Tree {

    // Activity #1: Code along
    // Add the following:
    // - root Node
    // - 2 constructors: no argument, root argument
    // - setRoot(node)
    // - getRoot()
    // - add(): adds a node to some random location
    // - createSomeTree()
    private Node root;

    public Tree() {
        // call a this constructor
        this(new Node());
    }

    public Tree(Node root) {
        setRoot(root);
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        // Not Yet Implemented
        return this.root;
    }

    /**
     * add one Node randomly to the Tree
     */
    public void add(Node parent, Node child) {
        if (!parent.hasChildren() || Math.random() < .1) {
            parent.addChild(child);
        } else {
            add(parent.getRandomChild(), child);
        }
    }

    public void add() {
        Node child = new Node();
        // 20% of h the time, add to the root
        // otherwise, add to some child of the root
        // always giving the child 20% chance have getting a new child
        add(root, child);
    }

    public static Tree createSomeTree() {
        Tree tree = new Tree();
        // randomly add some number of nodes to this tree
        int random = (int) (Math.random() * 180) + 30;
        while (random-- > 0) {
            tree.add();
        }
        return tree;
    }
}
