

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameFrameV2 extends JFrame implements ActionListener {
    private final int X_SIZE = 96;
    private final int Y_SIZE = 90;
    JButton[][] button;
    JButton b1 = new JButton("New Game");
    JButton b2 = new JButton("Solve Board");
    int[][] board;
    int row;
    int col;
    JPanel mainPanel;
    JPanel bottomPanel;

    public GameFrameV2() {
        col = 4;
        row = 4;
        board = new int[row][col];
        frameGUI();
    }

    public void frameGUI() {

        setTitle("15 Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        setBounds(100,100,414,500);
        Border br = BorderFactory.createLineBorder(Color.black);
        Container c=getContentPane();

        mainPanel=new JPanel();
        bottomPanel=new JPanel();

        makeButtons();

        b1.setBounds(1,200,199,50);
        b1.setBackground(Color.GRAY);
        b1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        b1.addActionListener(this);

        b2.setBounds(200,200,199,50);
        b2.setBackground(Color.GRAY);
        b2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        b2.addActionListener(this);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mainPanel.add(button[i][j]);
            }
        }

        bottomPanel.add(b1);
        bottomPanel.add(b2);

        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBounds(0,1,400,399);

        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setBounds(0,401,400,50);

        mainPanel.setBorder(br);
        mainPanel.setLayout(new GridLayout(row, col));
        c.add(mainPanel);

        bottomPanel.setLayout(new GridLayout());
        c.add(bottomPanel);

        makeBlock();
        setVisible(true);

    }
    public void newGame() {
        shuffleArray();
    }

    Boolean isSolved() {
        int count = 1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] != count) {
                    return false;
                }
                count++;
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
        updateBoard();
        if(ae.getSource()==b1){
            shuffleArray();
            updateBoard();
        }
        else if(ae.getSource()==b2){
            solveBoard();
            updateBoard();
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(button[i][j]==ae.getSource()) {
                    swapTiles(button[i][j]);
                    if(isSolved()){
                        solvedItIs();
                    }
                }
            }
        }
    }

    public boolean hasNeighbour(JButton b) {
        int xValue = b.getX() / X_SIZE;
        int yValue = b.getY() / Y_SIZE;
        int temp;

            try {
                if (board[yValue][xValue + 1] == 16) {
                    temp = Integer.parseInt(b.getText());
                    board[yValue][xValue] = 16;
                    board[yValue][xValue + 1] = temp;
                    return true;
                }
            }
            catch (ArrayIndexOutOfBoundsException ae){

                }
            try{
                 if (board[yValue + 1][xValue] == 16) {
                    temp = Integer.parseInt(b.getText());
                    board[yValue][xValue] = 16;
                    board[yValue + 1][xValue] = temp;
                    return true;
                }
            }
            catch (ArrayIndexOutOfBoundsException ae){

            }
            try{
            if (board[yValue][xValue - 1] == 16) {
                    temp = Integer.parseInt(b.getText());
                    board[yValue][xValue] = 16;
                    board[yValue][xValue - 1] = temp;
                    return true;
                }
            }
            catch (ArrayIndexOutOfBoundsException ae){

            }
            try{
            if (board[yValue - 1][xValue] == 16) {
                    temp = Integer.parseInt(b.getText());
                    board[yValue][xValue] = 16;
                    board[yValue - 1][xValue] = temp;
                    return true;
                }
            }
            catch (ArrayIndexOutOfBoundsException ae){

            }
            return false;
    }

    public void swapTiles(JButton b) {

        if (hasNeighbour(b)) {
                updateBoard();
        }
    }
    public void shuffleArray(){

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
    }
    public void updateBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                button[i][j].setText(Integer.toString(board[i][j]));
                makeBlock();
            }
        }
    }

    public void makeButtons() {
        button = new JButton[row][col];
        this.newGame();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                button[i][j] = new JButton();
                String text = Integer.toString(board[i][j]);
                button[i][j].setText(text);
                button[i][j].addActionListener(this);{}
                button[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                button[i][j].setBackground(Color.LIGHT_GRAY);
                button[i][j].setSize(X_SIZE, Y_SIZE);
            }
        }
    }
    public void makeBlock(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 16) {
                    button[i][j].setText("");
                }
            }
        }
    }
    public void solveBoard(){
        int k = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = k;
                k++;
            }
            if(isSolved()){
                solvedItIs();
            }
        }
        updateBoard();
        isSolved();
    }

    public static void main (String[]args){
        new GameFrameV2();
    }
}

