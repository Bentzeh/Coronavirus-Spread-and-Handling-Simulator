package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * this is an implementation of a generic list
 * @param <Type> type of which we would like to hold in the list
 */
public class SimulationParticipantsList<Type> implements Iterable<Type>{

    private List<Type> list;
    private int currentSize;

    /**
     * constructor
      */
    public SimulationParticipantsList() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationParticipantsList.Constructor()");
        this.list = new ArrayList<>();
        this.currentSize = list.size();
    }

    /**
     * constructor that create this list from a given list
     * @param newList a list that we want to create this object from
     */
    public SimulationParticipantsList(List<Type> newList) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationParticipantsList.Constructor(list)");
        this.list = newList;
        this.currentSize = list.size();
    }

    /**
     * add an element to this list
     * @param element an element that we want to add
     * @return true if succeeded, false otherwise
     */
    public boolean add(Type element){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationParticipantsList.add()");
        ++currentSize;
        return list.add(element);
    }

    /**
     * remove an element from this list
     * @param element an element that we want to add
     * @return true if succeeded, false otherwise
     */
    public boolean remove(Type element){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationParticipantsList.remove()");
        --currentSize;
        return list.remove(element);
    }

    /**
     * retrieves the size of this list
     * @return the size of this list
     */
    public int size(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationParticipantsList.size()");
        return currentSize;
    }

    /**
     * retrieves an iterator for this list
     * @return an iterator for this list
     */
    @Override
    public Iterator<Type> iterator() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationParticipantsList.iterator()");
        Iterator<Type> it = new Iterator<Type>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationParticipantsList.iterator.hasNext()");
                return currentIndex < currentSize && list.get(currentIndex) != null;
            }

            @Override
            public Type next() {
                LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationParticipantsList.iterator.next()");
                return list.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("this iterator does not support element removal");
            }
        };
        return it;
    }

    /**
     * retrieves the first element of this list
     * @return the first element of this list, if list empty it will return a null object
     */
    public Type getFirstInList() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered SimulationParticipantsList.getFirstInList()");
        return currentSize > 0 ? list.get(0) : null;
    }
}
