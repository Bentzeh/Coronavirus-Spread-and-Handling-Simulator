package com.orens.cshs.logic.observer;

public interface IInvocable {
    void updateCurrentScheduledExecutionTime(long scheduledExecutionTime);
    void iteration();
}
