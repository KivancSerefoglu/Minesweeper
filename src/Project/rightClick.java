package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;


public class rightClick implements MouseListener {
    private MineGrid grid;
    private JButton[][] buttons;
    private int row, col;
    private JLabel counterFlags;
    private JLabel face;


    public rightClick(MineGrid grid, int row, int col, JButton[][]buttons, JLabel counterFlags, JLabel face){
        this.grid=grid;
        this.row=row;
        this.col=col;
        this.buttons=buttons;
        this.counterFlags=counterFlags;
        this.face=face;

    }



    public void mouseClicked(MouseEvent e) {

        JButton button = (JButton) e.getComponent();
        if (e.getButton() == MouseEvent.BUTTON1){
            System.out.println("sol tık");
            if (grid.isMine(row,col)){
                printMine(buttons.length,buttons[0].length,grid,buttons);
                face.setIcon(new ImageIcon("Minesweeper/sad.png"));
                JOptionPane.showMessageDialog(null,"OOPS!!");
                System.exit(0);
            }else{
                open(row,col, buttons);
            }

        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (grid.getRestOfFlags()>0&&!grid.isFlag(row,col)&&button.isEnabled()) {
                grid.setFlag(row,col);
                button.setIcon(new ImageIcon("Minesweeper/flagged.png"));
                button.setEnabled(false);
                if (grid.getCorrectFlags()== grid.getNumMines()){
                    JOptionPane.showMessageDialog(null,"You're genius.");
                    System.exit(0);
                }
            } else if (grid.isFlag(row,col)){
                button.setEnabled(true);
                grid.removeFlag(row,col);
                button.setIcon(null);
            }
            counterFlags.setText(String.valueOf(grid.getRestOfFlags()));
            System.out.println(grid.getRestOfFlags()+" is rest of flags");

        }

    }


    public void open(int row, int col, JButton[][]buttons){
        if (row<0 || row>=buttons.length ||col<0|| col>=buttons[0].length || !buttons[row][col].isEnabled()){
        }else if (grid.getCellContent(row,col)!=0){
            buttons[row][col].setText(grid.getCellContent(row,col)+"");
            buttons[row][col].setEnabled(false);
        }else{
            buttons[row][col].setText(grid.getCellContent(row,col)+"");
            buttons[row][col].setEnabled(false);
            open(row-1, col,buttons);
            open(row+1, col,buttons);
            open(row, col-1,buttons);
            open(row, col+1,buttons);
        }


    }

    public JLabel getCounterFlags() {
        return counterFlags;
    }

    public JLabel getFace() {
        return face;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public void printMine(int numRows, int numCols,MineGrid grid, JButton[][]buttons){
        for (int i=0; i< numRows; i++){
            for (int j=0; j< numCols; j++){
                if (grid.isMine(i,j)){
                    buttons[i][j].setIcon(new ImageIcon("Minesweeper/bomb.png"));
                }
            }
        }
    }

}
