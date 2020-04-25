package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbsHealthState;
import com.orens.cshs.pojos.Location;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

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

    private Timer timer;

    public Person() {
        this.id = ++globalId;
        this.name = "default";
        this.location = new Location(2,2);
        this.board = null;
        this.timer = new Timer(id+" - Timer");
    }


    public Person(Board board) {
        this.id = ++globalId;
        this.name = "default";
        this.location = new Location(2,2);
        this.board = board;
        this.timer = new Timer(id+" - Timer");
    }

    public void startSimulation() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String s = "Current time is: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault());
                LoggerHandler.getInstance().log(ReportLevel.INFO, s);
                step();
            }
        },0, PropertiesFileReader.getTickTimeInMilliseconds());
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
        //board.setValueAt(x, y, ""+id);

    }

}



//    private void step(){
//        // move to random spot
//        Location newLocation = location.getNextRandomLocation();
//        int x =RandomGenerator.getRandomWithinBorders();
//        int y =RandomGenerator.getRandomWithinBorders();
//
//
//        // activate Logic
//        String s = "\nCurrent time is: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()) + "\n" +
//                "I'm in step, with id : " + id + "  ("+x+", "+y+")";
//        LoggerHandler.getInstance().log(ReportLevel.INFO, s);
//        board.setValueAt(x, y, ""+id);
//
//    }
