package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.TimerUtils;
import com.orens.cshs.logic.observer.IInvocable;
import com.orens.cshs.logic.state.AbstractHealthState;
import com.orens.cshs.logic.state.CarryingState;
import com.orens.cshs.logic.state.HealthyState;
import com.orens.cshs.logic.state.SickState;
import com.orens.cshs.logic.strategy.AbstractLogicStrategy;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * this abstract class represents a participant in this simulation
 * it implements the IInvocable interface
 */
public abstract class Participant implements IInvocable {

    protected Color participantColor;

    protected static long globalId = 0L;
    protected final long id;
    protected String name;

    protected Location location;
    protected Map<AbstractHealthState.State, AbstractHealthState> healthStates;
    protected AbstractHealthState currentHealthState;
    protected AbstractLogicStrategy logicStrategy;

    protected long currentScheduledExecutionTime; //beginning of tick
    protected long locationChangeTimeStamp;

    protected Map<AbstractHealthState.State, Color> participantColors;

    /**
     * constructor
     * @param logicStrategy a strategy object that holds the data and logic of this simulation
     */
    public Participant(AbstractLogicStrategy logicStrategy) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.Constructor()");

        this.participantColors = new EnumMap<>(AbstractHealthState.State.class);
        this.participantColor = Pixel.emptyColor;

        this.id = ++globalId;
        this.name = "defaultName with id: "+id;

        location = new Location().getRandomLocation();
        setLocation(new Location().getRandomLocation());
        initHealthStates();
        this.logicStrategy = logicStrategy;

        this.currentScheduledExecutionTime = 0L;

    }

    /**
     * initialize the health state of this participant
     */
    private void initHealthStates(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.initHealthStates()");

        this.healthStates = new EnumMap<>(AbstractHealthState.State.class);
        healthStates.put(AbstractHealthState.State.Healthy,  new HealthyState());
        healthStates.put(AbstractHealthState.State.Carrying, new CarryingState());
        healthStates.put(AbstractHealthState.State.Sick,     new SickState());

        setCurrentHealthState(AbstractHealthState.State.Healthy);
    }

    /**
     * retrieves the health state of this participant
     * @return a health state
     */
    public AbstractHealthState getCurrentHealthState() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.getCurrentHealthState()");
        return currentHealthState;
    }

    /**
     * set a health state for this participant
     * @param HealthState the health state that we want to set
     */
    public void setCurrentHealthState(AbstractHealthState.State HealthState) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.setCurrentHealthState()");
        setColor(HealthState);
        this.currentHealthState = healthStates.get(HealthState);
        this.currentHealthState.takeStateChangedTimestamp();
    }


    /**
     * update the time from the start of this tick
     * @param scheduledExecutionTime time from the start of the execution
     */
    @Override
    public void updateCurrentScheduledExecutionTime(long scheduledExecutionTime){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.updateCurrentScheduledExecutionTime()");
        this.currentScheduledExecutionTime = scheduledExecutionTime;
    }

    /**
     * retrieves the time Passed From Beginning Of Current Iteration
     * @return a time frame of the time passed from the beginning of this iteration
     */
    public TimeFrame getTimeFrameOfCurrentScheduledExecutionTillNow() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.getTimeFrameOfCurrentScheduledExecutionTillNow()");
        return new TimeFrame(currentScheduledExecutionTime, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
    }

    /**
     * retrieves the time Passed From Last Location Change
     * @return a time frame of the time passed from the last location change
     */
    public TimeFrame getTimeFrameOfLastLocationChangeTillNow(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.getTimeFrameOfLastLocationChangeTillNow()");
        return new TimeFrame(locationChangeTimeStamp, TimerUtils.getCurrentTimeStampAsRawLongFromSystem());
    }

    /**
     * set a new location
     * @param newLocation a new location that we want to set this participant on
     */
    public void setLocation(Location newLocation) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.setLocation()");
        if (!location.equals(newLocation)){
            this.location = newLocation;
            this.locationChangeTimeStamp = TimerUtils.getCurrentTimeStampAsRawLongFromSystem();
        }
    }

    /**
     * retrieves the location of this participant
     * @return the location of this participant
     */
    public Location getLocation() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.getLocation()");
        return location;
    }

    /**
     * retrieves the id of this participant
     * @return the id of this participant
     */
    public long getId() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.getId()");
        return id;
    }

    /**
     * set the name of this participant
     * @param name the name that we want to set
     */
    public void setName(String name) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.setName()");
        this.name = name;
    }

    /**
     * retrieves the name of this participant
     * @return the name of this participant
     */
    public String getName() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.getName()");
        return name;
    }

    /**
     * retrieves the strategy of this participant
     * @return the strategy of this participant
     */
    public AbstractLogicStrategy getLogicStrategy() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.getLogicStrategy()");
        return logicStrategy;
    }

    /**
     * set the strategy of this participant
     * @param logicStrategy the strategy that we want to set
     */
    public void setLogicStrategy(AbstractLogicStrategy logicStrategy) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.setLogicStrategy()");
        this.logicStrategy = logicStrategy;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument
     *
     */
    @Override
    public boolean equals(Object o) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.equals()");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id;
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.hashCode()");
        return Objects.hash(id);
    }

    /**
     * set the health state of this participant
     * @param state the health state that we want to set
     */
    protected abstract void setColor(AbstractHealthState.State state);

    /**
     * retrieves the color of this participant
     * @return the color of this participant
     */
    public Color getColor(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Participant.getColor()");
        return participantColor;
    }
}
