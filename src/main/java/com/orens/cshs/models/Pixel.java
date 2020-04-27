package com.orens.cshs.models;

import java.awt.*;
import java.util.Iterator;

public class Pixel {
    public static final Color testColor = new Color(0, 0, 0);

    public static final Color emptyColor = new Color(179, 219, 218);
    public static final Color populatedColor = new Color(196, 24, 215);//more then one

    public static final Color healthyNormalColor = new Color(30, 144, 144);
    public static final Color carryingNormalColor = new Color(220, 81, 10);
    public static final Color sickNormalColor = new Color(203, 10, 10);

    public static final Color healthyInspectorColor = new Color(0, 102, 252);
    public static final Color carryingInspectorColor = new Color(172, 120, 16);
    public static final Color sickInspectorColor = new Color(87, 0, 0);

    private Color pixelColor;

    private Location location;
    private SimulationParticipantsList<Participant> participantsAtThisPosition;
    private String val;

    public Pixel(int x, int y) {
        this.location = new Location(y, x);
        this.participantsAtThisPosition = new SimulationParticipantsList<>();
        this.val = "(-)";
        setPixelColorEmpty();
    }


    @Override
    public String toString() {
        return "("+getInPlaceParticipantIds()+")";
    }

    public String getInPlaceParticipantIds(){
        return getInPlaceParticipantIds(", ");
    }

    public String getInPlaceParticipantIds(String del){
        if (participantsAtThisPosition.size() <= 0){
            return "(-)";
        }

        Iterator<Participant> itr = participantsAtThisPosition.iterator();
        StringBuilder ids = new StringBuilder();
        int i = 0;
        Participant currentParticipant = itr.next();
        while (i < participantsAtThisPosition.size()-1){
            ids.append(currentParticipant.getId()).append(del);

            currentParticipant = itr.next();
            ++i;
        }
        ids.append(currentParticipant.getId());

        return ids.toString();
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Location getLocation() {
        return location;
    }

    public boolean addParticipantToPixel(Participant participant) {
        boolean isAdded = participantsAtThisPosition.add(participant);
        if (isAdded){
            setVal(toString());
        }
        return isAdded;
    }

    public boolean removeParticipantFromPixel(Participant participant) {
        boolean isRemoved = participantsAtThisPosition.remove(participant);
        if (isRemoved){
            setVal(toString());
        }
        return isRemoved;
    }

    public String getValue() {
        return val;
    }

    public boolean hasParticipant() {
        return participantsAtThisPosition.size() > 0;
    }

    public boolean hasMoreThenOneParticipant() {
        return participantsAtThisPosition.size() > 1;
    }

    public Participant getFirstParticipant(){
        if (participantsAtThisPosition.size() <= 0) { return null; }
        return participantsAtThisPosition.getFirstInList();
    }

    public void setPixelColor(Color pixelColor) {
        this.pixelColor = pixelColor;
    }
    public void setPixelColorEmpty() {
        this.pixelColor = emptyColor;
    }
    public void setPixelColorPopulated() {
        this.pixelColor = populatedColor;
    }

    public Color getPixelColor() {
        return pixelColor;
    }
}
