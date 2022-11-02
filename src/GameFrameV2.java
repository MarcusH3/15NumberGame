

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GameFrameV2 extends JFrame implements ActionListener {

    private final int X_SIZE = 96;
    private final int Y_SIZE = 90;
    private Point p = new Point();
    JButton[][] button;
    int[][] board;
    int row;
    int col;
    JFrame frame;
    JLabel[][] label;
    JPanel mainPanel;

    public GameFrameV2() {
        col = 4;
        row = 4;
        board = new int[row][col];
        frameGUI();
    }

    public void frameGUI() {

        frame = new JFrame("15-GAME");
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 4));
        button = new JButton[row][col];
        label = new JLabel[row][col];

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        this.pack();
        this.getContentPane().setBackground(Color.GRAY);
        /*Används ej i denna version
        JMenuBar jmb = new JMenuBar();
        setJMenuBar(jmb);
        JMenu menu1 = new JMenu("Actions");
        menu1.setSize(150, 15);
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem solveBoard = new JMenuItem("Solve Board");
        JMenuItem exitGame = new JMenuItem("Exit");*/

        this.newGame();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                button[i][j] = new JButton();
                String text = Integer.toString(board[i][j]);
                button[i][j].setText(text);
                button[i][j].addActionListener(this);
                int val = board[i][j];

                label[i][j] = new JLabel("");
                {

                }
                button[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                button[i][j].setBackground(Color.LIGHT_GRAY);
                mainPanel.add(button[i][j]);
            }
        }
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void newGame() {
        shuffleArray();
    }

    Boolean isSolved() {
        int count = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != count && board[i][j] != -1) {
                    return false;
                }
                count = count + 1;
            }
        }
        return true;
    }

    public void solvedItIs()
    {
        JFrame frame = new JFrame("You win ");
        frame.setLayout(new GridLayout(1, 1));
        frame.setSize(300, 300);
        frame.setBackground(Color.white);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Boolean solution = isSolved();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(button[i][j]==ae.getSource()) {
                    System.out.println(i +""+j);
                    swapTiles(button[i][j]);
                }
            }
        }
    }


    public boolean hasNeighbour(JButton b) {
        int xValue = b.getX() / X_SIZE;
        int yValue = b.getY() / Y_SIZE;

            try {

                if (board[yValue][xValue + 1] == 16) {
                    p.setLocation(1+(1+xValue*X_SIZE),1+(yValue*Y_SIZE));
                    return true;
                }
                else if (board[yValue][xValue - 1] == 16) {
                    p.setLocation(1+((xValue-1)*X_SIZE),1+(yValue*Y_SIZE));
                    return true;
                }
                else if (board[yValue + 1][xValue] == 16) {
                    p.setLocation(1+((xValue)*X_SIZE),1+(1+yValue*Y_SIZE));
                    return true;
                }
                else if (board[yValue - 1][xValue] == 16) {
                    p.setLocation(1+((xValue)*X_SIZE),1+(1-yValue*Y_SIZE));
                    return true;
                }
                return false;
            }
            catch (ArrayIndexOutOfBoundsException ae){
                return false;
            }
        }

        public void swapTiles(JButton b) {

            if (hasNeighbour(b) == true) {
                int getX = (int)p.getX();
                int getY = (int)p.getY();

                int xValue = getX / X_SIZE;
                int yValue = getY / Y_SIZE;
                int x = b.getX();
                int y = b.getY();
                b.setLocation(p);
                button[yValue][xValue].setLocation(x, y);
            }
        }

        public int[][] shuffleArray(){

            int[] arr = new int[board.length* board.length];
            for (int i = 0; i < board.length* board.length; i++) {
                arr[i] = i+1;
            }

            Random r = new Random();

            for (int i = 0; i < board.length* board.length; i++) {
                    int randomIndexToSwap = r.nextInt(arr.length);
                    int temp = arr[randomIndexToSwap];
                    arr[randomIndexToSwap] = arr[i];
                    arr[i] = temp;
            }
            int k = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    board[i][j] = arr[k];
                            k++;
                }
            }
            return board;
        }



        public static void main (String[]args){
        GameFrameV2 GF = new GameFrameV2();
    }
}

