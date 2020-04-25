package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbsHealthState;
import com.orens.cshs.pojos.Location;

public class Person implements IInvocable {
    private static long globalId = 0L;
    private final long id;
    private final String name;
    private Location location;
    private int bodyHeat; //  isSick <- if high than 38 ? true : false ;
    //private long timeThreshold;
    private AbsHealthState currentHealthState;

    private Board board;

    private boolean isCarry;
    private boolean isDead;


    public Person(Board board) {
        this.id = ++globalId;
        this.name = "default";
        this.location = new Location(2,2);

        this.board = board;
    }



    @Override
    public void iteration() {
        step();
    }


    private void step(){
        // move to random spot
        Location newLocation = location.getNextRandomLocation();
        int x =RandomGenerator.getRandomWithinBorders();
        int y =RandomGenerator.getRandomWithinBorders();

        // activate Logic
        String s =  "I'm in step, with id : " + id + "  ("+x+", "+y+")";
        LoggerHandler.getInstance().log(ReportLevel.INFO, s);
        board.setValueAt(x, y, ""+id);

    }

}
