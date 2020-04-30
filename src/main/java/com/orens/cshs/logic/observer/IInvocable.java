package com.orens.cshs.logic.observer;

/**
 * an interface for an object that can register as an observer to the tick time triggers
 */
public interface IInvocable {

    /**
     * pass to the invocable object the time from the execution
     * @param scheduledExecutionTime time from the start of the execution
     */
    void updateCurrentScheduledExecutionTime(long scheduledExecutionTime);

    /**
     * activate a logic block on the invocable object
     */
    void iteration();
}
