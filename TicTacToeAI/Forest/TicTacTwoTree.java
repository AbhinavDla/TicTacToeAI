package Forest;

public class TicTacTwoTree extends Tree {

    /**
     * Create a Game Tree for TicTacTwo... where there is a
     * very simple 2x2 Board that is similar to TicTacToe.
     * In this 2x2 Board we don't care about wins or loses.
     * We only want to demonstrate that we can create a Tree
     * that represents the board all the moves and configurations.
     */
    public TicTacTwoTree() {
        super(new TicTacTwoNode());
        TicTacTwoNode root = (TicTacTwoNode) getRoot();
        root.addChildrens();
        root.getGameStats();
    }

    public static Tree createGameTree() {
        TicTacTwoTree tree = new TicTacTwoTree();
        return tree;
        // return Tree.createSomeTree();

    }

}