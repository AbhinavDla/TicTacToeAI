package TicTacToe;

import Forest.Node;

/*
 * This class 
 */
public class TTTNode extends Node {

  // Instance fields should store information about the board,
  // wins, losses, draws and which move is the best
  // TODO: add instance fields here
  private int xWins = 0;
  private int oWins = 0;
  private int draws = 0;
  public Board board;
  public String moveMadeCharacter = "X";

  public TTTNode(TTTNode copyme) {
    this.xWins = copyme.xWins;
    this.oWins = copyme.oWins;
    this.draws = copyme.draws;
    this.board = new Board(copyme.board);
    this.moveMadeCharacter = copyme.moveMadeCharacter;
  }

  // Create Node that represents the board at this stage
  public TTTNode() {
    // Must call super() first!
    super();
    board = new Board();
    board.initializeBoard();
    // TODO: set instance fields as necessary
  }

  public void addChildrens() {
    if ((!(board.checkWin('X'))) && (!(board.checkWin('O')))) {
      for (int i = 0; i <= 2; i++) {
        for (int j = 0; j <= 2; j++) {
          if (board.getBoardVal(i, j) == ' ') {
            TTTNode child = new TTTNode(this);
            moveMadeCharacter = "" + child.board.doMove(i, j);
            addChild(child);
            GameTreeAI.treeNodes.put(child.board.toString(), child);
            // STRIDE: Adding the wrong board to the map
            // GameTreeAI.treeNodes.put(board.toString(), child);
            child.addChildrens();
          }
        }
      }
    }
  }

  // TODO: update the signature of this API to return the best Move
  // as you've designed your classes. Perhaps an int?
  public Move getBestMove() {
    // TODO: return the instance field for the best move
    return null;
  }

  public void getGameScores() {
    if (board.checkWin('X')) {
      this.xWins = 1;
    } else if (board.checkWin('O')) {
      this.oWins = 1;
    } else if (board.isBoardFull()) {
      this.draws = 1;
    }
  }

  public void getGameStats() {
    if (!hasChildren()) {
      getGameScores();

    } else {
      for (Node node : getChildren()) {
        TTTNode child = (TTTNode) node;
        child.getGameStats();
        this.xWins += child.xWins;
        this.oWins += child.oWins;
        this.draws += child.draws;
      }
    }

  }

  public String toString() {
    StringBuilder boardStr = new StringBuilder();
    boardStr.append("---+---+---\n");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        boardStr.append(" ");
        boardStr.append(board.getBoardVal(i, j));
        if (j < 2) {
          boardStr.append(" |");
        }
      }
      boardStr.append("\n---+---+---\n");
    }

    boardStr.append("X: " + (xWins) + "\nO: " + (oWins) + "\nDraws: " + (draws));
    return boardStr.toString();
  }

  public int getOWins() {
    return oWins;
  }

  public int getXWins() {
    return xWins;
  }

  public int getDrawsNum () {
    return draws;
  }
}