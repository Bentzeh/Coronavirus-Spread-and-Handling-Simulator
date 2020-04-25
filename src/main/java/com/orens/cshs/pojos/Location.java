package com.orens.cshs.pojos;

public class Location {

    private int x;
    private int y;

    public Location() {
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

    public Location getNextRandomLocation() {
        // TODO: 24/04/2020 choose location randomly (x+d or x-d or y+d or y-d) and return
        return new Location(x + 2, y);
    }
}