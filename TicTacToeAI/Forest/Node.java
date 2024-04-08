package Forest;

/*
 * This is the Base Node class.
 * This class has a list of children.
 * This class prints its adress since it has no information.
 *
 * Derived classes should:
 *   - have some information.
 *   - override toString()
 */
import java.util.ArrayList;
import java.util.List;

public class Node {

    // Activity #1: Code along
    // Add the following:
    // - List of Nodes for the children
    // - constructor to create empty List
    // - addChild(node)
    // - getChildren();
    // - toString()

    private List<Node> children;

    public Node() {
        this.children = new ArrayList<>();
    }

    public boolean hasChildren() {
        return children.size() > 0;
    }

    public Node getRandomChild() {
        if (!hasChildren()) {
            return this;
        }
        return children.get((int) (Math.random() * children.size()));
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public void clickEvent() {
        System.out.println("That tickled!");
    }
}
