package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;

public class Person extends Participants {

    public Person(Board board) {
        super(board);
    }


    private void step(){
        // move to random spot
        this.location = location.getRandomLocation();
        int x = location.getX();
        int y = location.getY();

        // activate Logic
        String s =  "I'm in step, with id : " + id + "  ("+x+", "+y+")";
        LoggerHandler.getInstance().log(ReportLevel.INFO, s);
        board.setValueAt(x, y, ""+id);

    }

}
