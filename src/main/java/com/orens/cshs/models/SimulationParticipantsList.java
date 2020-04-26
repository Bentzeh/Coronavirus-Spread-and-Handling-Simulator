package com.orens.cshs.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimulationParticipantsList<Type> implements Iterable<Type>{

    private List<Type> list;
    private int currentSize;

    public SimulationParticipantsList() {
        this.list = new ArrayList<>();
        this.currentSize = list.size();
    }

    public SimulationParticipantsList(List<Type> newList) {
        this.list = newList;
        this.currentSize = list.size();
    }

    public boolean add(Type element){
        ++currentSize;
        return list.add(element);
    }

    public boolean remove(Type element){
        --currentSize;
        return list.remove(element);
    }

    public int size(){
        return currentSize;
    }

    @Override
    public Iterator<Type> iterator() {
        Iterator<Type> it = new Iterator<Type>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && list.get(currentIndex) != null;
            }

            @Override
            public Type next() {
                return list.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("this iterator does not support element removal");
            }
        };
        return it;
    }



}
