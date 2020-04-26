package com.orens.cshs.logic.observer;

public interface IInvocable {
    void updateTimePassedFromLastLocationChange(long scheduledExecutionTime);
    void iteration();
}
