package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.RandomGenerator;

import java.util.Objects;

public class Location {

    private static final int WIDTH = PropertiesFileReader.getBoardWidth();
    private static final int HEIGHT = PropertiesFileReader.getBoardHeight();

    private int x;
    private int y;


    /**
     * Constructor - no arguments
     * initialize the location object to (0, 0)
     *
     */
    public Location() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.Constructor()");
        this.x = 0;
        this.y = 0;
    }


    /**
     * Constructor
     * @param y the y coordinate of the location
     * @param x the x coordinate of the location
     */
    public Location(int y, int x) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.Constructor(y, x)");
        this.x = checkWithinLimits(x, WIDTH);
        this.y = checkWithinLimits(y, HEIGHT);
    }


    /**
     * getting the x coordinate of this location
     * @return the x coordinate of this location
     */
    public int getX() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.getX()");
        return x;
    }

    /**
     * setting the x coordinate of this location
     * @param x the new coordinate you want to set
     */
    public void setX(int x) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.setX()");
        this.x = checkWithinLimits(x, WIDTH);
    }

    /**
     * getting the y coordinate of this location
     * @return the y coordinate of this location
     */
    public int getY() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.getY()");
        return y;
    }

    /**
     * setting the y coordinate of this location
     * @param y the new coordinate you want to set
     */
    public void setY(int y) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.setY()");
        this.y = checkWithinLimits(y, HEIGHT);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.equals()");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.hashCode()");
        return Objects.hash(x, y);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.toString()");
        return "(y="+y+", x="+x+")";
    }

    /**
     * generating a random location
     * @return a random location
     */
    public Location getRandomLocation() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.getRandomLocation()");
        return new Location(RandomGenerator.getRandomYWithinBorders(), RandomGenerator.getRandomXWithinBorders());
    }

    /**
     * checks that the given number is within the limit, if not it returns the borders of the board
     * @param num a number
     * @param limit a limit can be width or height of the board
     * @return the number after the limit check
     */
    private static int checkWithinLimits(int num, int limit){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.checkWithinLimits()");
        if (num < 0){
            return 0;
        }
        else if (num >= limit){
            return limit - 1;
        }
        else {
            return num;
        }
    }

    /**
     * checks that the two locations are within the same radius
     * @param loc1 location one
     * @param loc2 location two
     * @param radius the radius on which the two location are within
     * @return true if the two locations are within the radius, false otherwise
     */
    public static boolean isWithinRadius(Location loc1, Location loc2, int radius){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Location.isWithinRadius()");
        return (Math.abs(loc1.getX() - loc2.getX()) <= radius) && (Math.abs(loc1.getY() - loc2.getY()) <= radius);
    }

}
