package com.orens.cshs.models;

/**
 * enum for a representation of seconds (for better readability)
 */
public enum Seconds {
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6);

    private final int num;

    Seconds(int num) {
        this.num = num;
    }

    public int getValue() { return num; }
}
