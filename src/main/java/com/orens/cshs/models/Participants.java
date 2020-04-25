package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.state.HealthyState;
import com.orens.cshs.pojos.Location;

public abstract class Participants implements IInvocable {

    protected static long globalId = 0L;
    protected final long id;
    protected String name;
    protected final Board board;
    protected Location location;
    protected AbstractHealthState currentHealthState;

    protected int bodyHeat; //  isSick <- if high than 38 ? true : false ;
    protected boolean isCarry;
    protected boolean isDead;
    protected boolean isInspector;


    public Participants(Board board) {
        this.id = ++globalId;
        this.name = "name id: "+id;
        this.location = new Location().getRandomLocation();
        this.board = board;

        this.currentHealthState = new HealthyState();
    }

    @Override
    public void iteration() {
        step();
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

    public AbstractHealthState getCurrentHealthState() {
        return currentHealthState;
    }

    public void setCurrentHealthState(AbstractHealthState currentHealthState) {
        this.currentHealthState = currentHealthState;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
