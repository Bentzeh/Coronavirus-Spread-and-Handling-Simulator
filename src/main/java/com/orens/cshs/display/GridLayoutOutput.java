package com.orens.cshs.display;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.models.Board;
import com.orens.cshs.models.Pixel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* this class implements the abstract class for output the simulation data
*/
public class GridLayoutOutput extends AbstractOutput {

    private JFrame frame;
    private JPanel[][] displayMat;


    /**
     *  constructor
     * @param board the board that the output mechanism reads and output
     */
    public GridLayoutOutput(Board board) {
        super(board);
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered GridLayoutOutput.Constructor()");
    }


    /**
     * implementation of the abstract class method that initialize the internal object of this class
     */
    @Override
    public void InitializeDisplay() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered GridLayoutOutput.InitializeDisplay()");

        frame = new JFrame("Coronavirus Spread and Handling Simulator");

        frame.setPreferredSize(new Dimension(PropertiesFileReader.getFrameWidth(), PropertiesFileReader.getFrameHeight()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // "file" menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // exit button for "file" menu
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });
        fileMenu.add(exitMenuItem);

        int borderWidth = 1;
        int gridWidth = board.getFieldWidth();
        int gridHeight = board.getFieldHeight();


        GridLayout gridLayout = new GridLayout(gridHeight, gridWidth);
        JPanel grid = new JPanel(gridLayout);
        frame.setContentPane(grid);
        grid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //grid.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));


        displayMat = new JPanel[gridLayout.getRows()][gridLayout.getColumns()];
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

                panelBox.setBackground(Pixel.emptyColor);

                JLabel label = new JLabel("");
                label.setForeground(Color.green);
                panelBox.add(label);

                int x = ((int)(panelBox.getSize().getWidth()) - (int)(label.getSize().getWidth())) / 2;
                label.setLocation(new Point(x, label.getLocation().y));

                grid.add(panelBox);
            }
        }

        frame.pack();
        frame.setVisible(true);
    }


    /**
     * implementation of the abstract class method that updates the internal object of this class
     */
    @Override
    public void updateDisplayView() {
        //LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered GridLayoutOutput.updateDisplayView()");
        Pixel[][] BoardField = board.getField();

        for (int i = 0; i < displayMat.length; ++i) {
            for (int j = 0; j < displayMat[0].length; ++j) {
                JPanel panelBox = displayMat[i][j];
                JLabel label = (JLabel)panelBox.getComponent(0);


                /// update grid by reading from board.field
                Pixel pixel = BoardField[i][j];
                if (pixel.hasParticipant()) {
                    String participantIds = pixel.getInPlaceParticipantIds();
                    label.setText(participantIds);
                    if (pixel.hasMoreThenOneParticipant()){
                        panelBox.setBackground(Pixel.populatedColor);
                    }
                    else {
                        panelBox.setBackground(pixel.getFirstParticipant().getColor());
                    }
                }
                else {
                    label.setText("");
                    panelBox.setBackground(Pixel.emptyColor);
                }
                ////
            }
        }

        SwingUtilities.updateComponentTreeUI(frame);
    }

}