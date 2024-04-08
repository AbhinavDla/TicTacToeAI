package TicTacToe;

/**
 * This class is for consideration only. Mr. Stride had this class
 * in his implementation. Perhaps you have something similar.
 * 
 * Here is a description of Mr. Stride's Board class...
 *
 * This class:
 * - HAS-A array of the pieces on the board
 * - makes changes to the board by doing a Move
 * - can undo a Move, which is helpful in creating a Tree recursively
 * - can determine if a move is valid
 * - HAS-A list of moves one can make
 * - HAS-A list of moves already made
 * - can determine if there is a winner
 * - can determine if the game is over (board is full, possible draw)
 * - provides a nice String representation of itself (i.e. toString())
 * 
 *
 */
public class Board {
  private char[][] cells;
  private int xCount = 0;
  private int oCount = 0;

  public Board() {
    initializeBoard();
  }

  public Board(Board copyme) {
    this.cells = new char[3][3];
    this.xCount = copyme.xCount;
    this.oCount = copyme.oCount;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        cells[i][j] = copyme.cells[i][j];
      }
    }
  }

  /**
   * Initializes a board for the game using an array.
   * Loops through the boards blank spaces adding numbers in each space.
   */
  public void initializeBoard() {
    cells = new char[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        cells[i][j] = ' ';
      }
    }
  }

  public char getBoardVal(int row, int col) {
    return cells[row][col];
  }

  public char doMove(int i, int j) {
    char move = 'O';
    if (xCount == oCount) {
      move = 'X';
      xCount++;
    } else {
      oCount++;
    }
    cells[i][j] = move;
    return move;
  }

  public char[][] getBoard() {
    return cells;
  }

  /**
   * Checks if the cell is empty.
   * If it either has a X or a O then it is empty.
   * 
   * @param move takes the integer value of the move played and assigns an 'X'/'O'
   *             value there.
   * @return true.
   */
  public boolean isCellEmpty(int row, int col) {
    return cells[row][col] != 'X' && cells[row][col] != 'O';
  }

  /**
   * Detects if the user put a certain value in a certain place into the blank
   * spot.
   * It takes the number given (move) and changes the spot in the array.
   * 
   * @param move   takes an integer which is the corresponding number to the move
   *               played on the board.
   * @param symbol takes a character which is the symbol to be placed there based
   *               on the player.
   */
  public void makeMove(int row, int col, char symbol) {
    cells[row][col] = symbol;
  }

  /**
   * Checks if the X's or O's is in a row.
   * Series of if statements to see if they were in a rows or in vertical order.
   * 
   * @param symbol accepts a character which is a symbol either 'X' or 'O'.
   * @return true.
   */
  public boolean checkWin(char symbol) {
    for (int i = 0; i < 3; i++) {
      if ((cells[i][0] == symbol && cells[i][1] == symbol && cells[i][2] == symbol) || // Horizontal
          (cells[0][i] == symbol && cells[1][i] == symbol && cells[2][i] == symbol)) { // Vertical
        return true;
      }
    }
    return (cells[0][0] == symbol && cells[1][1] == symbol && cells[2][2] == symbol) || // Diagonal \
        (cells[0][2] == symbol && cells[1][1] == symbol && cells[2][0] == symbol); // Diagonal /
  }

  /**
   * Checks if the board is full.
   * Loops through the board and if the cell doesn't have an X or O
   * then it determnines the board isn't full.
   * 
   * @return true.
   */
  public boolean isBoardFull() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (cells[i][j] != 'X' && cells[i][j] != 'O') {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Overriden method to print the board whenever the print statement is in the
   * main.
   * Prints the board
   * 
   * @return a String of the whole board in pieces.
   */
  @Override
  public String toString() {
    StringBuilder boardStr = new StringBuilder();
    boardStr.append("\n---+---+---\n");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        boardStr.append(" ");
        boardStr.append(cells[i][j]);
        if (j < 2) {
          boardStr.append(" |");
        }
      }
      boardStr.append("\n---+---+---\n");
    }
    return boardStr.toString();
  }
}