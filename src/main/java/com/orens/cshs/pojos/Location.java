package com.orens.cshs.pojos;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.RandomGenerator;

public class Location {

    private int x;
    private int y;

    public Location() {
        this.x = 0;
        this.y = 0;
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Location getRandomLocation() {
        Direction randomDirection = RandomGenerator.getRandomDirection();
        int stepDistanceLimit = PropertiesFileReader.getStepDistance();
        int width = PropertiesFileReader.getBoardWidth();
        int height = PropertiesFileReader.getBoardHeight();


        int checkedX = 0;
        int checkedY = 0;

        switch (randomDirection) {
            case Left:
                checkedX = checkWithinLimits(this.x - stepDistanceLimit, width);
                checkedY = checkWithinLimits(this.y, height);
                break;
            case LeftUp:
                checkedX = checkWithinLimits(this.x - stepDistanceLimit, width);
                checkedY = checkWithinLimits(this.y - stepDistanceLimit, height);
                break;
            case Up:
                checkedX = checkWithinLimits(this.x, width);
                checkedY = checkWithinLimits(this.y - stepDistanceLimit, height);
                break;
            case RightUp:
                checkedX = checkWithinLimits(this.x + stepDistanceLimit, width);
                checkedY = checkWithinLimits(this.y - stepDistanceLimit, height);
                break;
            case Right:
                checkedX = checkWithinLimits(this.x + stepDistanceLimit, width);
                checkedY = checkWithinLimits(this.y, height);
                break;
            case RightDown:
                checkedX = checkWithinLimits(this.x + stepDistanceLimit, width);
                checkedY = checkWithinLimits(this.y + stepDistanceLimit, height);
                break;
            case Down:
                checkedX = checkWithinLimits(this.x, width);
                checkedY = checkWithinLimits(this.y + stepDistanceLimit, height);
                break;
            case LeftDown:
                checkedX = checkWithinLimits(this.x - stepDistanceLimit, width);
                checkedY = checkWithinLimits(this.y + stepDistanceLimit, height);
                break;
            default: // Direction.SameSpot:
                checkedX = this.x;
                checkedY = this.y;
                break;

        }
        return new Location(checkedX, checkedY);
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
