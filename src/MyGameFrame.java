

import javax.swing.*;
import javax.xml.transform.Source;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class MyGameFrame extends JFrame implements ActionListener {

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b10;
    Button b11;
    Button b12;
    Button b13;
    Button b14;
    Button b15;
    Button b16;

    protected Integer[] ai = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

    protected int[] ai1 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;

    static final int X_SIZE = 146;
    static final int Y_SIZE = 140;

    MyGameFrame(){
        List<Integer> list = Arrays.asList(ai);
        Collections.shuffle(list);
        //Arrays.sort(ai);

        this.setTitle("15-GAME");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        setLayout(new GridLayout(4,4));
        this.pack();
        this.getContentPane().setBackground(Color.GRAY);


        b1 = new Button(ai[0].toString());
        b2 = new Button(ai[1].toString());
        b3 = new Button(ai[2].toString());
        b4 = new Button(ai[3].toString());
        b5 = new Button(ai[4].toString());
        b6 = new Button(ai[5].toString());
        b7 = new Button(ai[6].toString());
        b8 = new Button(ai[7].toString());
        b9 = new Button(ai[8].toString());
        b10 = new Button(ai[9].toString());
        b11 = new Button(ai[10].toString());
        b12 = new Button(ai[11].toString());
        b13 = new Button(ai[12].toString());
        b14 = new Button(ai[13].toString());
        b15 = new Button(ai[14].toString());
        b16 = new Button("");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b10.addActionListener(this);
        b11.addActionListener(this);
        b12.addActionListener(this);
        b13.addActionListener(this);
        b14.addActionListener(this);
        b15.addActionListener(this);


        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(b10);
        add(b11);
        add(b12);
        add(b13);
        add(b14);
        add(b15);
        add(b16);

        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Point p = new Point();
        if(e.getSource()==b1){
            checkCross(b1);
        }
        else if(e.getSource()==b2){
            checkCross(b2);
        }
        else if(e.getSource()==b3){
            checkCross(b3);
        }
        else if(e.getSource()==b4){
            checkCross(b4);
        }
        else if(e.getSource()==b5){
            checkCross(b5);
        }
        else if(e.getSource()==b6){
            checkCross(b6);
        }
        else if(e.getSource()==b7){
            checkCross(b7);
        }
        else if(e.getSource()==b8){
            checkCross(b8);
        }
        else if(e.getSource()==b9){
            checkCross(b9);
        }
        else if(e.getSource()==b10){
            checkCross(b10);
        }
        else if(e.getSource()==b11){
            checkCross(b11);
        }
        else if(e.getSource()==b12){
            checkCross(b12);
        }
        else if(e.getSource()==b13){
            checkCross(b13);
        }
        else if(e.getSource()==b14){
            checkCross(b14);
        }
        else if(e.getSource()==b15){
            checkCross(b15);
        }


    }

    public void checkCross(Button b){

        Point p = new Point(b.getX(),b.getY());
        Point p1 = new Point();

        if(p.getX()+X_SIZE == b16.getX() && p.getY() == b16.getY()){
            p1 = p.getLocation();
            p.setLocation(p.getX()+X_SIZE, p.getY());
            b.setLocation(p);
            b16.setLocation(p1);

            checkBoard(getGrid(p));

        }
        else if(p.getX()-X_SIZE == b16.getX() && p.getY() == b16.getY()){
            p1 = p.getLocation();
            p.setLocation(p.getX()-X_SIZE, p.getY());
            b.setLocation(p);
            b16.setLocation(p1);

            checkBoard(getGrid(p));
        }
        else if( p.getX() == b16.getX() && p.getY()+Y_SIZE == b16.getY()){
            p1 = p.getLocation();
            p.setLocation(p.getX(), p.getY()+Y_SIZE);
            b.setLocation(p);
            b16.setLocation(p1);

            checkBoard(getGrid(p));
        }
        else if(p.getX() == b16.getX() && p.getY()-Y_SIZE == b16.getY()){
            p1 = p.getLocation();
            p.setLocation(p.getX(), p.getY()-Y_SIZE);
            b.setLocation(p);
            b16.setLocation(p1);

            solveBoard();
            checkBoard(getGrid(p));
        }
    }
    public void checkBoard(int[] i){

        if(ai1[0]==1&&ai1[1]==2&&ai1[2]==3&&ai1[3]==4&&
                ai1[4]==5&&ai1[5]==6&&ai1[6]==7&&ai1[7]==8&&
                ai1[8]==9&&ai1[9]==10&&ai1[10]==11&&ai1[11]==12&&
                ai1[12]==13&&ai1[13]==14&&ai1[14]==15){
            System.out.println("you win");
        }

    }
    public int[] getGrid(Point p){
        if(p.getX() == 1){
            if(p.getY()==1){
                ai1[0]=1;
            }
            else if(p.getY()==1+Y_SIZE){
                ai1[1]=2;
            }
            else if(p.getY()==1+2*Y_SIZE){
                ai1[2]=3;
            }
            else if(p.getY()==1+3*Y_SIZE){
                ai1[3]=4;
            }
        }
        else if(p.getX() == 1+X_SIZE){
            if(p.getY()==1){
                ai1[4]=5;
            }
            else if(p.getY()==1+Y_SIZE){
                ai1[5]=6;
            }
            else if(p.getY()==1+2*Y_SIZE){
                ai1[6]=7;
            }
            else if(p.getY()==1+3*Y_SIZE){
                ai1[7]=8;
            }
        }
        else if (p.getX()==1+2*X_SIZE) {
            if(p.getY()==1){
                ai1[8]=9;
            }
            else if(p.getY()==1+Y_SIZE){
                ai1[9]=10;
            }
            else if(p.getY()==1+2*Y_SIZE){
                ai1[10]=11;
            }
            else if(p.getY()==1+3*Y_SIZE){
                ai1[11]=12;
            }
        }
        else if (p.getX()==1+3*X_SIZE) {
            if(p.getY()==1){
                ai1[12]=13;
            }
            else if(p.getY()==1+Y_SIZE){
                ai1[13]=14;
            }
            else if(p.getY()==1+2*Y_SIZE){
                ai1[14]=15;
            }

        }
        return ai1;
    }
    public void solveBoard(){
        b1.setLabel("1");
        b2.setLabel("2");
        b3.setLabel("3");
        b4.setLabel("4");
        b5.setLabel("5");
        b6.setLabel("6");
        b7.setLabel("7");
        b8.setLabel("8");
        b9.setLabel("9");
        b10.setLabel("10");
        b11.setLabel("11");
        b12.setLabel("12");
        b13.setLabel("13");
        b14.setLabel("14");
        b15.setLabel("15");

        for (int i = 1; i < ai.length; i++) {
            for (int j = 0; j < ai.length; j++) {
                if(ai[i]>ai[j]){
                    int temp = ai[i];
                    ai[i] = ai[j];
                    ai[j] = temp;
                    ai1[i] = ai[i];
                }
            }
        }
        checkBoard(ai1);
    }

}
