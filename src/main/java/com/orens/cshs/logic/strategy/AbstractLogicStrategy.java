package com.orens.cshs.logic.strategy;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.models.*;

/**
 * this abstract class represents the current logic that a participant activate on each iteration
 */
public abstract class AbstractLogicStrategy {

    protected static final long CONTAGIOUS_TIME   = PropertiesFileReader.getContagiousTimeInMilliseconds();
    protected static final int  CONTAGIOUS_RADIUS = PropertiesFileReader.getContagiousRadius();

    protected Board board;
    protected ParticipantsData participantsData;

    /**
     * constructor
     * @param participantsData the "data" object that holds the data that needs to be displayed
     */
    public AbstractLogicStrategy(ParticipantsData participantsData) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractLogicStrategy.Constructor()");

        this.participantsData = participantsData;
        this.board = participantsData.getBoard();
    }

    /**
     * generate a random location
     * @param location get the current location inorder to have the option to chose the same location aswell
     * @return a new Location
     */
    public Location getNextRandomLocation(Location location) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered AbstractLogicStrategy.getNextRandomLocation()");
        int y = location.getY();
        int x = location.getX();

        Direction randomDirection = RandomGenerator.getRandomDirection();
        int stepDistanceLimit = PropertiesFileReader.getStepDistance();

        int Y = 0;
        int X = 0;

        switch (randomDirection) {
            case Left:
                Y = y;
                X = x - stepDistanceLimit;
                break;
            case LeftUp:
                Y = y - stepDistanceLimit;
                X = x - stepDistanceLimit;
                break;
            case Up:
                Y = y - stepDistanceLimit;
                X = x;
                break;
            case RightUp:
                Y = y - stepDistanceLimit;
                X = x + stepDistanceLimit;
                break;
            case Right:
                Y = y;
                X = x + stepDistanceLimit;
                break;
            case RightDown:
                Y = y + stepDistanceLimit;
                X = x + stepDistanceLimit;
                break;
            case Down:
                Y = y + stepDistanceLimit;
                X = x;
                break;
            case LeftDown:
                Y = y + stepDistanceLimit;
                X = x - stepDistanceLimit;
                break;
            default: // Direction.SameSpot:
                Y = y;
                X = x;
                break;

        }
        return new Location(Y, X);
    }

    /**
     * the logic that a normalPerson should activate on each iteration
     * @param normalPerson the participant that should activate this method logic
     */
    public abstract void executeLogic(NormalPerson normalPerson);

    /**
     * the logic that a inspectorPerson should activate on each iteration
     * @param inspectorPerson the participant that should activate this method logic
     */
    public abstract void executeLogic(InspectorPerson inspectorPerson);

}
