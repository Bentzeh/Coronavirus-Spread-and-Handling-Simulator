package com.orens.cshs.display;

import com.orens.cshs.infra.logger.IReports;
import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.models.Board;
import com.orens.cshs.models.Pixel;

public class ConsoleOutput extends AbstractOutput {

    private IReports logger;

    public ConsoleOutput(Board board) {
        super(board);
    }


    @Override
    public void InitializeDisplay() {
        this.logger = LoggerHandler.getInstance();
    }

    @Override
    public void updateDisplayView() {
        Pixel[][] field = board.getField();

        StringBuilder fieldAsString = new StringBuilder();
        //for (Pixel[] pixels : field) {
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