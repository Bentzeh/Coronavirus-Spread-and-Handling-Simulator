package com.orens.cshs.models.pojos;

import com.orens.cshs.models.Participant;
import com.orens.cshs.models.SimulationParticipantsList;

import java.util.Iterator;

public class Pixel {

    private Location location;
    //private List<Participant> participantsAtThisPosition;
    private SimulationParticipantsList<Participant> participantsAtThisPosition;
    private String val;

    public Pixel(int x, int y) {
        this.location = new Location(y, x);
        //this.participantsAtThisPosition = new ArrayList<>();
        this.participantsAtThisPosition = new SimulationParticipantsList<>();
        this.val = "(-)";
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
}
