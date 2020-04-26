package com.orens.cshs.logic.strategy;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.RandomGenerator;
import com.orens.cshs.models.Board;
import com.orens.cshs.models.Participant;
import com.orens.cshs.models.ParticipantsData;
import com.orens.cshs.models.pojos.Direction;
import com.orens.cshs.models.pojos.Location;

public abstract class AbstractLogicStrategy {

    protected Board board;
    protected ParticipantsData participantsData;

    public AbstractLogicStrategy(Board board, ParticipantsData participantsData) {
        this.board = board;
        this.participantsData = participantsData;
    }

    public abstract void executeLogic(Participant participant);

    public void initializeBoardWithParticipant(Participant participant){
        //board.removeParticipantFromLocation(participant);
        boolean isAdded = board.addParticipantToLocation(participant);
    }

    public Location getNextRandomLocation(Location location) {
        int x = location.getX();
        int y = location.getY();

        Direction randomDirection = RandomGenerator.getRandomDirection();
        int stepDistanceLimit = PropertiesFileReader.getStepDistance();
        int width = PropertiesFileReader.getBoardWidth();
        int height = PropertiesFileReader.getBoardHeight();


        int checkedX = 0;
        int checkedY = 0;

        switch (randomDirection) {
            case Left:
                checkedX = checkWithinLimits(x - stepDistanceLimit, width);
                checkedY = checkWithinLimits(y, height);
                break;
            case LeftUp:
                checkedX = checkWithinLimits(x - stepDistanceLimit, width);
                checkedY = checkWithinLimits(y - stepDistanceLimit, height);
                break;
            case Up:
                checkedX = checkWithinLimits(x, width);
                checkedY = checkWithinLimits(y - stepDistanceLimit, height);
                break;
            case RightUp:
                checkedX = checkWithinLimits(x + stepDistanceLimit, width);
                checkedY = checkWithinLimits(y - stepDistanceLimit, height);
                break;
            case Right:
                checkedX = checkWithinLimits(x + stepDistanceLimit, width);
                checkedY = checkWithinLimits(y, height);
                break;
            case RightDown:
                checkedX = checkWithinLimits(x + stepDistanceLimit, width);
                checkedY = checkWithinLimits(y + stepDistanceLimit, height);
                break;
            case Down:
                checkedX = checkWithinLimits(x, width);
                checkedY = checkWithinLimits(y + stepDistanceLimit, height);
                break;
            case LeftDown:
                checkedX = checkWithinLimits(x - stepDistanceLimit, width);
                checkedY = checkWithinLimits(y + stepDistanceLimit, height);
                break;
            default: // Direction.SameSpot:
                checkedX = x;
                checkedY = y;
                break;

        }
        return new Location(checkedY, checkedX);
    }

    private int checkWithinLimits(int num, int limit){
        if (num < 0){
            return 0;
        }else if (num >= limit){
            return limit - 1;
        }
        else {
            return num;
        }
    }
}
