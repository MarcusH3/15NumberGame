

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameFrameV2 extends JFrame implements ActionListener {
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
        //List<Integer> list = Arrays.asList(ai);
        //Collections.shuffle(list);
        //Arrays.sort(ai);
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
                String text = i + "," + j;
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
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void newGame() {
        Random rand = new Random();
        int[] ar = new int[16];
        for (int i = 0; i < 16; i++) {
            ar[i] = i + 1;
        }
        ar[15] = -1;
        for (int i = 0; i < 16; i++) {
            int index = rand.nextInt(16);
            int temp = ar[i];
            ar[i] = ar[index];
            ar[index] = temp;
        }
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ar[count];
                count = count + 1;
            }
        }
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
    public void actionPerformed(ActionEvent ae)
    {
        Boolean solution = isSolved();
            if (solution == false)
            {
                String s = ae.getActionCommand().toString();
                int r = Integer.parseInt(s.split(",")[0]);
                int c = Integer.parseInt(s.split(",")[1]);
                if (board[r][c] != 1)
                {
                    if (r + 1 < row && board[r + 1][c] == -1)
                    {
                        int temp = board[r][c];
                        board[r][c] = board[r + 1][c];
                        board[r + 1][c] = temp;
                    } else if (r - 1 >= 0 && board[r - 1][c] == -1) {
                        int temp = board[r][c];
                        board[r][c] = board[r - 1][c];
                        board[r - 1][c] = temp;
                    } else if (c + 1 < col && board[r][c + 1] == -1) {
                        int temp = board[r][c];
                        board[r][c] = board[r][c + 1];
                        board[r][c + 1] = temp;
                    } else if (c - 1 >= 0 && board[r][c - 1] == -1) {
                        int temp = board[r][c];
                        board[r][c] = board[r][c - 1];
                        board[r][c - 1] = temp;
                    }
                }
                solution = isSolved();
                if (solution == true)
                {solvedItIs();
                }
            }
        }
        public static void main (String[]args){
        GameFrameV2 GF = new GameFrameV2();
    }
}

