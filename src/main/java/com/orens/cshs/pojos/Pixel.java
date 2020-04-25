package com.orens.cshs.pojos;

import com.orens.cshs.models.Person;

import java.util.ArrayList;
import java.util.List;

public class Pixel {

    private Location location;
    private List<Person> personsAtThisPosition;

    private String val;

    public Pixel(int x, int y) {
        this.location = new Location(x, y);
        this.personsAtThisPosition = new ArrayList<Person>();

        this.val = "(0)";
    }


    @Override
    public String toString() {
        return val;
    }

    public void setValue(String newValue) {
        this.val += "(" + newValue + ")";
    }

    public String getValue() {
        return val;
    }
}
