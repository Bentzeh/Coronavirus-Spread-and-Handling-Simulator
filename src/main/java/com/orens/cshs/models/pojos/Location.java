package com.orens.cshs.models.pojos;

import com.orens.cshs.infra.utils.PropertiesFileReader;
import com.orens.cshs.infra.utils.RandomGenerator;

import java.util.Objects;

public class Location {

    private static final int WIDTH = PropertiesFileReader.getBoardWidth();
    private static final int HEIGHT = PropertiesFileReader.getBoardHeight();

    private int x;
    private int y;

    public Location() {
        this.x = 0;
        this.y = 0;
    }

    public Location(int y, int x) {
        this.x = checkWithinLimits(x, WIDTH);
        this.y = checkWithinLimits(y, HEIGHT);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = checkWithinLimits(x, WIDTH);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = checkWithinLimits(y, HEIGHT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(y="+y+", x="+x+")";
    }

    public Location getRandomLocation() {
        return new Location(RandomGenerator.getRandomYWithinBorders(), RandomGenerator.getRandomXWithinBorders());
    }

    private static int checkWithinLimits(int num, int limit){
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


    public static boolean isWithinRadius(Location loc1, Location loc2, int radius){
        return (Math.abs(loc1.getX() - loc2.getX()) <= radius) && (Math.abs(loc1.getY() - loc2.getY()) <= radius);
    }

}
