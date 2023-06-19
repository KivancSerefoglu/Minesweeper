package Project;

import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


class MineSweeperGUI extends JPanel {
    private MineGrid grid;
    private JLabel counterFlags;
    private JLabel face;
    private JLabel topOfTop;

    public MineSweeperGUI(int numRows, int numCols, int numMines) {

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        Color colorBack = Color.GRAY.brighter();

        topPanel.setPreferredSize(new Dimension(topPanel.getWidth(), 118));
        topPanel.setLayout(null);

        this.topOfTop = new JLabel(new ImageIcon("Minesweeper/topPanel.png"));
        this.topOfTop.setOpaque(true);
        this.topOfTop.setBounds(0, 22, 1000, 96);
        topPanel.add(topOfTop);


        JLabel Label = new JLabel(new ImageIcon("Minesweeper/Label.png"));
        add(Label, BorderLayout.EAST);
        JLabel Label2 = new JLabel(new ImageIcon("Minesweeper/Label.png"));
        add(Label2, BorderLayout.WEST);
        JLabel LabelBottom = new JLabel(new ImageIcon("Minesweeper/bottom.png"));
        LabelBottom.setPreferredSize(new Dimension(1000, 12));
        add(LabelBottom, BorderLayout.SOUTH);


        JButton button1 = new JButton("Game");
        JButton button2 = new JButton("Help");
        button1.setBackground(colorBack);
        button2.setBackground(colorBack);
        button1.setBounds(2, 2, 80, 20);
        button2.setBounds(100, 2, 80, 20);
        topPanel.add(button1);
        topPanel.add(button2);


        JLabel timeLabel = new JLabel();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int second = 0;

            public void run() {
                second++;
                String time = String.valueOf(second);
                timeLabel.setText(time);
                timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);

                String filename = "digital-7.ttf";

                Font font = null;
                try {
                    font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
                } catch (FontFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                font = font.deriveFont(Font.BOLD, 60);

                timeLabel.setFont(font);
                timeLabel.setBackground(Color.BLACK);
                timeLabel.setOpaque(true);
                timeLabel.setForeground(Color.RED);
                timeLabel.setPreferredSize(new Dimension(100, 60));
                timeLabel.setBounds(850, 16, 100, 60);
                topOfTop.add(timeLabel);

            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);


        grid = new MineGrid(numRows, numCols, numMines);
        addLabels();

        JPanel mainPanel = new JPanel();

        JButton[][] buttons = new JButton[numRows][numCols];
        mainPanel.setLayout(new GridLayout(numRows, numCols));
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                JButton button = new JButton();
                buttons[i][j] = button;
                mainPanel.add(button);
                button.addMouseListener(new rightClick (grid, i, j, buttons, counterFlags, face) {
                });
            }
        }


//      topOfTop.add(new rightClick(grid,numRows,numCols,buttons).getCounterFlags());
//        topOfTop.add(new rightClick(grid,numRows,numCols,buttons).getFace());


        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);


    }

    public void addLabels() {
        counterFlags = new JLabel(String.valueOf(grid.getRestOfFlags()));
        counterFlags.setHorizontalAlignment(SwingConstants.RIGHT);

        String filename = "digital-7.ttf";

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
        } catch (FontFormatException ev) {
            ev.printStackTrace();
        } catch (IOException ev) {
            ev.printStackTrace();
        }
        font = font.deriveFont(Font.BOLD, 60);

        counterFlags.setFont(font);
        counterFlags.setBackground(Color.BLACK);
        counterFlags.setOpaque(true);
        counterFlags.setForeground(Color.RED);
        counterFlags.setPreferredSize(new Dimension(100, 60));
        counterFlags.setBounds(50, 16, 100, 60);


        face = new JLabel();
        face.setIcon(new ImageIcon("Minesweeper/png-transsmiley.png"));
        face.setOpaque(true);
        face.setPreferredSize(new Dimension(61, 60));
        face.setBounds(470, 16, 61, 60);

        topOfTop.add(counterFlags);
        topOfTop.add(face);


    }

}
