package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;

import java.awt.*;
import java.util.Iterator;

/**
 * this class represent a point on the simulation board
 */
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

    /**
     * constructor
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     */
    public Pixel(int x, int y) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.Constructor()");
        this.location = new Location(y, x);
        this.participantsAtThisPosition = new SimulationParticipantsList<>();
        this.val = "(-)";
        setPixelColorEmpty();
    }

    /**
     * retrieves a string representation of this object
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.toString()");
        return "("+getInPlaceParticipantIds()+")";
    }

    /**
     * retrieves the id of the participant in this current location
     * @return the id of the participant in this current location as a string
     */
    public String getInPlaceParticipantIds(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.getInPlaceParticipantIds()");
        return getInPlaceParticipantIds(", ");
    }

    /**
     * retrieves the id of the participant in this current location separated by a given delimiter
     * @param del given delimiter for the id string separation
     * @return the id of the participant in this current location as a string
     */
    public String getInPlaceParticipantIds(String del){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.getInPlaceParticipantIds()");
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

    /**
     * set the value of this pixel
     * @param val value to be set
     */
    public void setVal(String val) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.setVal()");
        this.val = val;
    }

    /**
     * retrieves the location of this pixel
     * @return the location of this pixel
     */
    public Location getLocation() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.getLocation()");
        return location;
    }

    /**
     *  add a participant to this location
     * @param participant a participant to add
     * @return true if succeeded, false otherwise
     */
    public boolean addParticipantToPixel(Participant participant) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.addParticipantToPixel()");

        boolean isAdded = participantsAtThisPosition.add(participant);
        if (isAdded){
            setVal(toString());
        }
        return isAdded;
    }

    /**
     * remove a given participant from this pixel location
     * @param participant participant to be removed
     * @return true if succeeded, false otherwise
     */
    public boolean removeParticipantFromPixel(Participant participant) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.removeParticipantFromPixel()");

        boolean isRemoved = participantsAtThisPosition.remove(participant);
        if (isRemoved){
            setVal(toString());
        }
        return isRemoved;
    }

    /**
     * retrieves the pixel value
     * @return the pixel value
     */
    public String getValue() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.getValue()");
        return val;
    }

    /**
     * checks that there are participants in this pixel location
     * @return true if there is, false otherwise
     */
    public boolean hasParticipant() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.hasParticipant()");
        return participantsAtThisPosition.size() > 0;
    }

    /**
     * checks that there are more then one participants in this pixel location
     * @return true if there is, false otherwise
     */
    public boolean hasMoreThenOneParticipant() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.hasMoreThenOneParticipant()");
        return participantsAtThisPosition.size() > 1;
    }

    /**
     * retrieves the first participant in this location
     * @return the first participant in this location
     */
    public Participant getFirstParticipant(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.getFirstParticipant()");
        if (participantsAtThisPosition.size() <= 0) { return null; }
        return participantsAtThisPosition.getFirstInList();
    }

    /**
     * set the pixel color
     * @param pixelColor the color that we want to set
     */
    public void setPixelColor(Color pixelColor) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.setPixelColor()");
        this.pixelColor = pixelColor;
    }

    /**
     * set the color of the pixel as an predefined color for empty pixels
     */
    public void setPixelColorEmpty() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.setPixelColorEmpty()");
        this.pixelColor = emptyColor;
    }

    /**
     * set the color of the pixel as an predefined color for populated (more then one) pixels
     */
    public void setPixelColorPopulated() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.setPixelColorPopulated()");
        this.pixelColor = populatedColor;
    }

    /**
     * retrieves the pixel color
     * @return the pixel color
     */
    public Color getPixelColor() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered Pixel.getPixelColor()");
        return pixelColor;
    }
}
