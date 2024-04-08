// You must KEEP a few methods as commented below.
// Otherwise, add methods to implement a GUI version of TicTacToe

package GUI;
/*
 This class is responsible for:
    - drawing the TicTacToe board.
    - receiving user clicks and interaction (user events)
    - forwarding all user events appropriately

 All user interactions should be abstracted and forwarded to TicTacToe classes.
*/

import Forest.Node;
import Forest.Tree;
import TicTacToe.Board;
import TicTacToe.Move;
import TicTacToe.GameTreeAI;
import TicTacToe.TTTNode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {

    // TODO: have instance fields for the current turn and what the AI is (X or O)
    private JButton[][] buttons = new JButton[3][3];
    private static final int BTN_SIZE = 150;
    private Font fonts = new Font("Comic Sans", Font.BOLD, 30);
    private Color colors = new Color(255, 255, 255);
    public String mover = "X";
    public int moveCount = 1;
    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;
    public boolean aiStart = false;

    // Keep this instance field
    private GameTreeAI ai = null;

    // Have a board or something like it
    public static Board board;

    public GamePanel() {
        ai = new GameTreeAI();
        ai.learn();

        board = new Board();
        // moveSquare = JButton
        for (int ibtn = 0; ibtn < 3; ibtn++) {
            for (int ibtn2 = 0; ibtn2 < 3; ibtn2++) {
                JButton btn = new JButton();
                addButton(btn, ibtn, ibtn2);
                buttons[ibtn][ibtn2] = btn;
                // STRIDE: Below is your broken code. See the problem?
                // addButton(new JButton(), ibtn, ibtn2);
                // buttons[ibtn][ibtn2] = new JButton();
            }
        }
        this.setLayout(null);
        board = new Board();
        board.initializeBoard();
    }

    public void updateBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!buttons[i][j].getText().isEmpty()) {
                    board.makeMove(i, j, buttons[i][j].getText().charAt(0));
                }
            }
        }
    }

    public void addButton(JButton btn, int row, int col) {
        // Question: Explain the parameters of setBounds
        // Answer: x: the new x-coordinate of this button
        // y: the new y-coordinate of this button
        // width: the new width of this button
        // height: the new height of this button
        btn.setBounds(col * (BTN_SIZE + 1) + 2, row * (BTN_SIZE + 1), BTN_SIZE, BTN_SIZE);
        // Question: What does the method 'add' do?
        // Answer: The method add appends the btn to the current object that is under
        // this. In this
        // case it appends btn to the end of the 2D array created for the button on the
        // calculator.
        btn.setForeground(Color.BLACK);
        btn.setFont(fonts);
        btn.setBackground(colors);

        this.add(btn);
        // AS A CLASS: Review what this does
        btn.addActionListener(this);
    }

    // Keep this method!
    public Tree getGameTree() {
        return ai;
    }

    // Keep this method!
    public void setAIStarts(boolean aiMovesFirst) {
        // moveCount = aiMovesFirst ? 0 : 1;
        if (aiMovesFirst) {
          
            aiStart = true;
            JButton move = buttons[1][1];
            move.setText("X");
            move.setEnabled(false);
            updateBoard();
            mover = "O";
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color
        // Draw scenery
        // if ai wins make it aiDrawScenery
        // if player wins make it playerDrawScenery
        // random number from 1 to 2
        // if random number is 1 then draw aiDrawScenery
        // if random number is 2 then draw DrawScenery
        int num = (int) (Math.random() * 2 + 1);
        if (num == 1) {
            aiDrawScenery(g);
        } else {
            drawScenery(g);
        }
    }
    private void drawScenery(Graphics g) {
        // Draw mountains
        this.setBackground(new Color(135, 206, 235)); // Light blue background
        g.setColor(new Color(96, 96, 96)); // Dark gray for mountains
        int[] xPoints = {600, 800, 1000}; // X-coordinates for the mountain peaks
        int[] yPoints = {300, 100, 300}; // Y-coordinates for the mountain peaks
        g.fillPolygon(xPoints, yPoints, 3); // Draw the mountain shape

        // Draw trees
        g.setColor(new Color(0, 102, 0)); // Dark green for trees
        g.fillRect(850, 250, 20, 100); // Tree trunk
        g.setColor(new Color(0, 204, 0)); // Light green for leaves
        g.fillOval(820, 200, 80, 80); // Tree leaves
        g.fillOval(870, 180, 80, 80); // Tree leaves

        // Draw sun
        g.setColor(new Color(255, 255, 102)); // Yellow for sun
        g.fillOval(950, 50, 100, 100); // Draw sun

        // Draw clouds
        g.setColor(new Color(255, 255, 255)); // White for clouds
        g.fillOval(700, 100, 80, 40); // Cloud 1
        g.fillOval(750, 120, 100, 50); // Cloud 2

        // Draw grass on the right side
        g.setColor(new Color(0, 153, 0)); // Dark green for grass
        g.fillRect(0, 300, getWidth(), getHeight() - 300); // Draw grass area
    }

    private void aiDrawScenery(Graphics g) {
        // Draw volcano
        g.setColor(new Color(96, 96, 96)); // Dark gray for volcano
        int[] xPoints = {800, 900, 1000}; // X-coordinates for the volcano base
        int[] yPoints = {400, 200, 400}; // Y-coordinates for the volcano base
        g.fillPolygon(xPoints, yPoints, 3); // Draw the volcano base
        g.setColor(new Color(255, 69, 0)); // Red for lava
        g.fillOval(875, 200, 50, 50); // Draw lava at the top of the volcano

        // Draw flames
        g.setColor(new Color(255, 140, 0)); // Orange for flames
        g.fillOval(860, 150, 30, 30); // Draw flame 1
        g.fillOval(900, 150, 30, 30); // Draw flame 2
        g.fillOval(880, 130, 30, 30); // Draw flame 3

        // Draw rocks around volcano
        g.setColor(new Color(128, 128, 128)); // Gray for rocks
        g.fillOval(820, 390, 30, 30); // Draw rock 1
        g.fillOval(940, 380, 40, 40); // Draw rock 2

        // Draw fiery sky
        g.setColor(new Color(255, 69, 0, 100)); // Semi-transparent red for fiery sky
        g.fillRect(0, 0, getWidth(), getHeight()); // Fill the entire panel with fiery sky

        // Draw lava flowing down the volcano
        g.setColor(new Color(255, 69, 0)); // Red for lava
        g.fillRect(0, 400, getWidth(), getHeight() - 300); // Draw lava
    }

    /**
     * This allows this dialog to be drawn at a good size.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT);
    }

    /**
     * This is called when a new game is started. This will
     * reset our state back to a new game and redraw the board.
     */
  public void resetBoard() {
      // Reset button texts and enable them
      for (int i = 0; i < buttons.length; i++) {
          for (int j = 0; j < buttons[i].length; j++) {
              JButton btn = buttons[i][j];
              btn.setText("");
              btn.setEnabled(true);
          }
      }

      // Reset move count and current mover
      moveCount = 1;
      mover = "X";
      aiStart = false;
      // Repaint the panel to reflect changes
      this.revalidate();
      this.repaint();

      // Reset the game board
      board.initializeBoard();
  }

    // public void actionPerformed(ActionEvent e) {
    // // AS A CLASS: Review this code. How does it get called?

    // }

    public void actionPerformed(ActionEvent e) {
        if (!board.checkWin('X') && !board.checkWin('O') && !board.isBoardFull()) {
            JButton btn = (JButton) e.getSource();
            btn.setText(mover);
            moveCount--;
            btn.setEnabled(false);
            updateBoard();

            if (board.checkWin('X')) {
                JOptionPane.showMessageDialog(null, "X Wins!");
                xWins++;
                return; // Exit actionPerformed after player's win
            }
            if (board.checkWin('O')) {
                JOptionPane.showMessageDialog(null, "O Wins!");
                oWins++;
                return; // Exit actionPerformed after player's win
            }
            if (board.isBoardFull()) {
                JOptionPane.showMessageDialog(null, "Draw!");
                draws++;
            }

            if (!board.checkWin('O')) {
                TTTNode bestNode = ai.getBestMove(aiStart);
                int newX = 0;
                int newY = 0;
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        if (board.getBoardVal(row, col) != bestNode.board.getBoardVal(row, col)) {
                            newX = row;
                            newY = col;
                            break;
                        }
                    }
                }
                JButton move = buttons[newX][newY];
                move.setText("" + bestNode.board.getBoardVal(newX, newY));
                move.setEnabled(false);
                updateBoard();

                if (board.checkWin('O')) {
                    JOptionPane.showMessageDialog(null, "O Wins!");
                    oWins++;
                    return; // Exit actionPerformed after AI's win
                }
            }
        } if (board.checkWin('X') || board.checkWin('O')) {
            if (board.checkWin('X')) {
                JOptionPane.showMessageDialog(null, "X Wins!");
                xWins++;
            } else if (board.checkWin('O')) {
                JOptionPane.showMessageDialog(null, "O Wins!");
                oWins++;
            }
        } 
        else if (board.isBoardFull()) {
            JOptionPane.showMessageDialog(null, "Draw!");
            draws++;
        }
    }
}
