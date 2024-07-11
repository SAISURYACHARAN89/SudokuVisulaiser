package SPC.Project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUI {
    private static int n;
    private static JButton[][] buttons;
    private static boolean[][] updatedCells;
    private static JSlider speedSlider;

    public static void printBoard(int[][] board) {
        n = board.length;
        buttons = new JButton[n][n]; // Initialize buttons array with correct dimensions
        updatedCells = new boolean[n][n]; // Initialize the array to track updated cells

        // Create the frame
        JFrame frame = new JFrame("Sudoku Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700); // Set the frame size

        // Create a panel with a BorderLayout to center the Sudoku grid
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        // Create a bordered panel to hold the Sudoku grid
        JPanel borderedPanel = new JPanel(new GridLayout(n, n));
        borderedPanel.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, new Color(122, 56, 56))); // Wooden border color
        mainPanel.add(borderedPanel, BorderLayout.CENTER);

        // Initialize buttons and add to bordered panel
        int box = (int) Math.sqrt(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
                borderedPanel.add(buttons[i][j]);

                // Set custom borders for each button
                Border border = BorderFactory.createMatteBorder(
                        i % box == 0 ? box : 1, // Top border: thicker if it's the start of a 3-row block
                        j % box == 0 ? box : 1, // Left border: thicker if it's the start of a 3-column block
                        1, // Bottom border: always 1
                        j == n - 1 ? box : 0, // Right border: thicker if it's the last column of the row
                        Color.BLACK // Default color for all borders
                );
                buttons[i][j].setBorder(border);
            }
        }

        // Create a panel for the speed control slider
        JPanel controlPanel = new JPanel();
        JLabel speedLabel = new JLabel("Time in ms:");
        speedSlider = new JSlider(JSlider.HORIZONTAL, 50, 2000, 450);
        speedSlider.setMajorTickSpacing(500);
        speedSlider.setMinorTickSpacing(100);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);
        JLabel x  = new JLabel("Fast<-------->Slow");
        // Add control panel to the main panel
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);

        // Initial update of the board
        updateBoard(board, -1, -1);
    }

    public static void updateBoard(int[][] board, int updatedRow, int updatedCol) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    buttons[i][j].setText("");
                    buttons[i][j].setBackground(Color.WHITE); // Set background color
                } else if (board[i][j] == -1) {
                    buttons[i][j].setText("");
                    buttons[i][j].setBackground(Color.RED); // Set background color
                } else {
                    buttons[i][j].setText(String.valueOf(board[i][j]));
                    if (updatedCells[i][j]) {
                        buttons[i][j].setBackground(Color.GREEN); // Highlight updated cell in green
                    } else {
                        buttons[i][j].setBackground(new Color(74, 117, 134)); // Set background color
                    }
                }
            }
        }
    }

    public static void setUpdated(int row, int col) {
        updatedCells[row][col] = true;
    }

    public static void resetUpdated(int row, int col) {
        updatedCells[row][col] = false;
    }

    public static int getDelay() {
        return speedSlider.getValue();
    }

    public static void main(String[] args) {
        // Example board initialization
        int[][] board = new int[9][9]; // Provide an actual board initialization
        printBoard(board);
        // Call your solver here to start visualization
    }
}
