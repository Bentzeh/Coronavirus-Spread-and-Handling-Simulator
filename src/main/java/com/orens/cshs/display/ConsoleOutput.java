package com.orens.cshs.display;

import com.orens.cshs.infra.logger.IReports;
import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.models.Board;
import com.orens.cshs.models.Pixel;

/**
 * this class implements the abstract class for output the simulation data
 */
public class ConsoleOutput extends AbstractOutput {

    private IReports logger;

    /**
     *  constructor
     * @param board the board that the output mechanism reads and output
     */
    public ConsoleOutput(Board board) {
        super(board);
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered ConsoleOutput.Constructor()");
    }


    /**
     * implementation of the abstract class method that initialize the internal object of this class
     */
    @Override
    public void InitializeDisplay() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered ConsoleOutput.InitializeDisplay()");
        this.logger = LoggerHandler.getInstance();
    }

    /**
     * implementation of the abstract class method that updates the internal object of this class
     */
    @Override
    public void updateDisplayView() {
        //LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered ConsoleOutput.updateDisplayView()");
        Pixel[][] field = board.getField();

        StringBuilder fieldAsString = new StringBuilder();
        for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field[0].length; ++j) {
                Pixel pixel = field[i][j];
                fieldAsString.append(pixel.toString()).append(", ");
            }
            fieldAsString.append("\n");
        }

        logger.log(ReportLevel.INFO,"\n"+fieldAsString.toString());
    }

}