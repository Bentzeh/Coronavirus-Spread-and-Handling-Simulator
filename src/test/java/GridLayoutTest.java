import com.orens.cshs.models.Pixel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GridLayoutTest {


    public static void foo(Pixel[][] board){

        // frame
        JFrame frame = new JFrame("Pedigree Builder");
        frame.setPreferredSize(new Dimension(400, 300));

        // menu bar
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // "file" menu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);

        // exit button for "file" menu
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });
        fileMenu.add(exitMenuItem);

        // content pane = grid
        JPanel contentPane = new JPanel(new GridLayout(0, 3));
        frame.setContentPane(contentPane);


        JPanel[][] fieldArray = new JPanel[board.length][board[0].length];

        for (int i = 0; i < board.length; ++i) {
            fieldArray[i] = new JPanel[board[0].length];
            for (int j = 0; j < fieldArray[0].length; ++j) {
                fieldArray[i][j] = new JPanel();
                contentPane.add(fieldArray[i][j]);

                if (!board[i][j].getValue().equals("(0)")){
                    fieldArray[i][j].setBackground(Color.green);
                }
            }
        }

       /* // init array of fields for the grid
        JPanel[] fieldArray = new JPanel[9];
        for (int i = 0; i < fieldArray.length; i++){
            fieldArray[i] = new JPanel();
            contentPane.add(fieldArray[i]);
        }*/

        // modify content of particular cell (in this case: bottom right)
        //fieldArray[8].setBackground(Color.green);

        frame.pack();
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        // frame
        JFrame frame = new JFrame("Pedigree Builder");
        frame.setPreferredSize(new Dimension(400, 300));

        // menu bar
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // "file" menu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);

        // exit button for "file" menu
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });
        fileMenu.add(exitMenuItem);


        final int borderWidth = 1;
        int gridWidth = 10;
        int gridHeight = 10;

        GridLayout gridLayout = new GridLayout(gridHeight, gridWidth);
        JPanel grid = new JPanel(gridLayout);
        frame.setContentPane(grid);
        grid.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        JPanel[][] displayMat = new JPanel[gridLayout.getRows()][gridLayout.getColumns()];
        for (int i = 0; i < gridHeight; ++i) {
            displayMat[i] = new JPanel[gridWidth];
            for (int j = 0; j < displayMat[0].length; ++j) {
                displayMat[i][j] = new JPanel();
                JPanel panelBox = displayMat[i][j];

                if (i == 0) {
                    if (j == 0) {
                        // Top left corner, draw all sides
                        panelBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    }
                    else {
                        // Top edge, draw all sides except left edge
                        panelBox.setBorder(BorderFactory.createMatteBorder(borderWidth,
                                0,
                                borderWidth,
                                borderWidth,
                                Color.BLACK));
                    }
                }
                else {
                    if (j == 0) {
                        // Left-hand edge, draw all sides except top
                        panelBox.setBorder(BorderFactory.createMatteBorder(0,
                                borderWidth,
                                borderWidth,
                                borderWidth,
                                Color.BLACK));
                    }
                    else {
                        // Neither top edge nor left edge, skip both top and left lines
                        panelBox.setBorder(BorderFactory.createMatteBorder(0,
                                0,
                                borderWidth,
                                borderWidth,
                                Color.BLACK));
                    }
                }
                grid.add(panelBox);


                panelBox.setBackground(new Color(199, 5, 5));
                JLabel label = new JLabel("Label");
                //panelBox.setText("");
                //panelBox.add(label, BorderLayout.CENTER);
                panelBox.add(label);

                int x = ((int)(panelBox.getSize().getWidth()) - (int)(label.getSize().getWidth())) / 2;
                label.setLocation(new Point(x, label.getLocation().y));

            }
        }

        for (int i = 0; i < displayMat.length; ++i) {
            for (int j = 0; j < displayMat[0].length; ++j) {

                /// update grid by reading from board.field
                displayMat[1][9].setBackground(new Color(i+60, j+70 ,200));
                if ( (int)( (Math.random() * 200) + 3) < 100 ){
                    displayMat[i][j].setBackground(new Color(i+80, j+9 ,150));

                }
                ////
            }
        }


        frame.pack();
        frame.setVisible(true);
    }

//
//    private JFrame initFrame(Pixel[][] field){ // for reference
//
//        // menu bar
//        JMenuBar menubar = new JMenuBar();
//        frame.setJMenuBar(menubar);
//
//        // "file" menu
//        JMenu fileMenu = new JMenu("File");
//        menubar.add(fileMenu);
//
//        // exit button for "file" menu
//        JMenuItem exitMenuItem = new JMenuItem("Exit");
//        exitMenuItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent event) {
//                System.exit(0);
//            }
//
//        });
//        fileMenu.add(exitMenuItem);
//
//        // content pane = grid
//        JPanel contentPane = new JPanel(new GridLayout(field.length, field[0].length));
//        frame.setContentPane(contentPane);
//
//
//        displayMat = new JPanel[field.length][field[0].length];
//
//        for (int i = 0; i < field.length; ++i) {
//            displayMat[i] = new JPanel[field[0].length];
//            for (int j = 0; j < displayMat[0].length; ++j) {
//                displayMat[i][j] = new JPanel();
//                contentPane.add(displayMat[i][j]);
//            }
//        }
//
//        for (int i = 0; i < field.length; ++i) {
//            for (int j = 0; j < displayMat[0].length; ++j) {
//                displayMat[1][9].setBackground(new Color(i+60, j+70 ,200));
//                if (!field[i][j].getValue().equals("(0)")){
//                    displayMat[i][j].setBackground(new Color(i+80, j+9 ,150));
//                }
//            }
//        }
//
//
//       /* // init array of fields for the grid
//        JPanel[] fieldArray = new JPanel[9];
//        for (int i = 0; i < fieldArray.length; i++){
//            fieldArray[i] = new JPanel();
//            contentPane.add(fieldArray[i]);
//        }*/
//
//        // modify content of particular cell (in this case: bottom right)
//        //fieldArray[8].setBackground(Color.green);
//
//        frame.pack();
//        frame.setVisible(true);
//
//        return frame;
//    }
//
//    public void update(JFrame frame, Pixel[][] field){
//
//        for (int i = 0; i < displayMat.length; ++i) {
//            for (int j = 0; j < displayMat[0].length; ++j) {
//                displayMat[1][9].setBackground(new Color(i+60, j+70 ,200));
//                if (!field[i][j].getValue().equals("(0)")){
//                    displayMat[i][j].setBackground(new Color(i+80, j+9 ,150));
//                }
//            }
//        }
//
//
//        SwingUtilities.updateComponentTreeUI(frame);
//    }


}