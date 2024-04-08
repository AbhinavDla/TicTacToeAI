package TicTacToe;

import Forest.Node;
import Forest.Tree;
import GUI.GamePanel;

import java.util.*;

/*
 * This class will:
 *   - create a Tree of Nodes that represent the TicTacToe Board
 *   - find a Node for a given Board and ask it to provide the best
 *     move to make for either X or O.
 */

public class GameTreeAI extends Tree {

  // A very easy way to find a Node for a Board is to have
  // a HashMap of nodes that are indexed by a Boards.toString()
  public static Map<String, TTTNode> treeNodes = new HashMap<>();

  public GameTreeAI() {
    learn();
  }

  /**
   * This method will create the full GameTree
   */
  public void learn() {
    System.out.println("learning...");
    // create an initial Board with no moves completed
    // create an empty Tree
    // recursively add nodes to the tree
    TTTNode tree = new TTTNode();
    setRoot(tree);
    // TTTNode myroot = ;
    // createGameTree(myroot, new Board());
    // TTTNode tree = new TTTNode();
    tree.addChildrens();
    tree.getGameStats();
    // TTTNode myRoot = tree;

    System.out.println("Once a tree is created, I'm Smart!");
  }

  public TTTNode getBestMove(boolean aiStart) {
    TTTNode currentNode = treeNodes.get(GamePanel.board.toString());
    if (currentNode == null) {
      return new TTTNode();
    }

    TTTNode bestChild = null;
    int bestScore = Integer.MIN_VALUE;

    for (Node child : currentNode.getChildren()) {
      TTTNode childActual = (TTTNode) child;
      int score = minimax(childActual, 0, false, aiStart); // Start with maximizing the AI's score

      if (score > bestScore) {
        bestChild = childActual;
        bestScore = score;
      }
    }

    return bestChild;
  }

  private int minimax(TTTNode node, int depth, boolean isMaximizingPlayer, boolean aiStart) {
    if (aiStart) {
      if (node.hasChildren()) {
        // when you do node.hasChildren() the ai starts is smarter when you do
        // !node.hasChildren() the ai starts is dumb
        // fix it
        return evaluateMove(node, "O"); // Evaluate the move for the AI player
      }
    } else {
      if (!node.hasChildren()) {
        // when you do node.hasChildren() the ai starts is smarter when you do
        // !node.hasChildren() the ai starts is dumb
        // fix it
        return evaluateMove(node, "O"); // Evaluate the move for the AI player
      }
    }

    if (isMaximizingPlayer) {
      int maxScore = Integer.MIN_VALUE;
      for (Node child : node.getChildren()) {
        TTTNode childActual = (TTTNode) child;
        int score = minimax(childActual, depth + 1, false, aiStart);
        maxScore = Math.max(maxScore, score);
      }
      return maxScore;
    } else {
      int minScore = Integer.MAX_VALUE;
      for (Node child : node.getChildren()) {
        TTTNode childActual = (TTTNode) child;
        int score = minimax(childActual, depth + 1, true, aiStart);
        minScore = Math.min(minScore, score);
      }
      return minScore;
    }
  }

  private int evaluateMove(TTTNode node, String playerChar) {
    // Check if the AI wins
    if (node.getOWins() == 1) {
      return 10;
    }

    // Check if the opponent wins
    if (node.getXWins() == 1) {
      return -10;
    }

    // Check if it's a draw
    if (node.getDrawsNum() == 1) {
      return 0; // Draw
    }

    // Prioritize blocking opponent's potential winning moves
    int blockOpponentWin = 0;
    for (Node child : node.getChildren()) {
      TTTNode childActual = (TTTNode) child;
      if (childActual.getXWins() == 1) {
        blockOpponentWin++;
      }
    }

    if (blockOpponentWin > 0) {
      // If blocking opponent's win is possible, prioritize it
      return -5; // Arbitrary negative score to prioritize blocking
    }

    // Default case: no wins or draws
    return 0;
  }

}