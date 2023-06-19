package Project;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class MineSweeper {
    private static final int NUM_MINES=35;
    private static final int SIZE= 20;

    public static void main(String[] args){
        JFrame frame= new JFrame("Mine Sweeper | # of mines: "+ NUM_MINES);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.add(new MineSweeperGUI(SIZE,SIZE,NUM_MINES));
        frame.setVisible(true);
    }

}
