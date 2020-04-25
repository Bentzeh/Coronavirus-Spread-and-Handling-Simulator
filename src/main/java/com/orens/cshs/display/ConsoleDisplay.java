package com.orens.cshs.display;

import com.orens.cshs.infra.logger.IReports;
import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.models.Board;
import com.orens.cshs.pojos.Pixel;

public class ConsoleDisplay implements IDisplay {
    private Board board;
    private IReports logger;
    public ConsoleDisplay(Board board) {
        this.logger = LoggerHandler.getInstance();
        this.board = board;
    }


    @Override
    public void drawBoard(){
        Pixel[][] field = board.getField();
        StringBuilder fieldAsString = new StringBuilder();
        for (Pixel[] pixels : field) {
            for (int j = 0; j < field[0].length; ++j) {
                fieldAsString.append(pixels[j].toString()).append(", ");
            }
            fieldAsString.append("\n");
        }

        logger.log(ReportLevel.INFO,"\n"+fieldAsString.toString());
    }

}