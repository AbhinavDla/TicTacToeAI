package Forest;

public class TicTacTwoNode extends Node {

    public char[][] board;
    public int xWins = 0;
    public int oWins = 0;
    public int draws = 0;

    /*
     * Position grid is formatted like:
     * X | O
     * -----
     * O | X
     *
     */

    public TicTacTwoNode() {
        board = new char[][] { { ' ', ' ' }, { ' ', ' ' } };
    }

    @Override
    public String toString() {
        return drawBoard();
    }

    public String drawBoard() {
        String result = "";
        result += board[0][0];
        result += " | ";
        result += board[0][1];
        result += "\n--+--\n";
        result += board[1][0];
        result += " | ";
        result += board[1][1];
        result += "\n";
        result += "X: " + (xWins) + "\nO: " + (oWins) + "\nDraws: " + (draws);
        return result;

    }

    public void addChildrens() {
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                if (board[i][j] == ' ') {
                    TicTacTwoNode child = new TicTacTwoNode();
                    addChild(child);
                    char[][] clone1 = clone(board);
                    add(clone1, i, j);
                    child.board = clone1;
                    child.addChildrens();
                }
            }
        }
    }

    public void add(char[][] clone1, int row, int col) {
        int moveNum = 0;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                if (board[i][j] == ' ') {
                    moveNum++;
                }
            }
        }
        if (moveNum % 2 == 0) {
            clone1[row][col] = 'X';
        } else {
            clone1[row][col] = 'O';
        }
    }

    public char[][] clone(char[][] board) {
        char[][] result = new char[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[i][j] = board[i][j];
            }
        }
        return result;
    }

    public void getGameScores() {
        int[][] xPoints = { { 1, 2 }, { 3, 4 } };
        int[][] oPoints = { { 4, 2 }, { 3, 1 } };
        int x = 0;
        int o = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (board[i][j] == 'X') {
                    x += xPoints[i][j];
                } else if (board[i][j] == 'O') {
                    o += oPoints[i][j];
                }
            }
        }
        int xScore = x - o;
        int oScore = o - x;

        if (xScore > oScore) {
            this.xWins = 1;
        } else if (oScore > xScore) {
            this.oWins = 1;
        } else {
            this.draws = 1;
        }
    }

    public void getGameStats() {
        if (!hasChildren()) {
            getGameScores();

        } else {
            for (Node node : getChildren()) {
                TicTacTwoNode child = (TicTacTwoNode) node;
                child.getGameStats();
                xWins += child.xWins;
                oWins += child.oWins;
                draws += child.draws;
            }
        }

    }
}
