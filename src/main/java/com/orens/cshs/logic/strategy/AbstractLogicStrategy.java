package com.orens.cshs.logic.strategy;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.models.Board;
import com.orens.cshs.models.Participant;
import com.orens.cshs.models.ParticipantsData;
import com.orens.cshs.models.pojos.Direction;
import com.orens.cshs.models.pojos.Location;

public abstract class AbstractLogicStrategy {

    protected static final long CONTAGIOUS_TIME            = PropertiesFileReader.getContagiousTimeInMilliseconds();
    protected static final int  CONTAGIOUS_RADIUS          = PropertiesFileReader.getContagiousRadius();

    protected Board board;
    protected ParticipantsData participantsData;

    public AbstractLogicStrategy(Board board, ParticipantsData participantsData) {
        this.board = board;
        this.participantsData = participantsData;
    }

    public abstract void executeLogic(Participant participant);

    public void initializeBoardWithParticipant(Participant participant){
        boolean isAdded = board.addParticipantToLocation(participant);
    }

    public Location getNextRandomLocation(Location location) {
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

}
