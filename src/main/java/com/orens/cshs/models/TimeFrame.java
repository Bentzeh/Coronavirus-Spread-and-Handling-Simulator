package com.orens.cshs.models;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.TimerUtils;

/**
 * a class that represent a time frame to manage the time overlap between two participants
 */
public class TimeFrame {

    private long startTime;
    private long endTime;
    private long periodInSeconds;

    /**
     * Constructor
     * @param startTime timestamp of the start time of this frame
     * @param endTime timestamp of the end time of this frame
     */
    public TimeFrame(long startTime, long endTime) {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimeFrame.Constructor()");
        this.startTime = startTime;
        this.endTime = endTime;
        this.periodInSeconds = TimerUtils.getMillisTimeGapInSeconds(startTime, endTime);
    }

    /**
     * retrieves the start time of this frame
     * @return start time of this frame
     */
    public long getStartTime() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimeFrame.getStartTime()");
        return startTime;
    }

    /**
     * retrieves the end time of this frame
     * @return end time of this frame
     */
    public long getEndTime() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimeFrame.getEndTime()");
        return endTime;
    }

    /**
     * retrieves the period of this time frame
     * @return the period of this time frame
     */
    public long getPeriodInSeconds() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimeFrame.getPeriodInSeconds()");
        return periodInSeconds;
    }

    /**
     * this function calculates the overlapping time between two time frames
     * @param timeFrameA first time frame
     * @param timeFrameB second time frame
     * @return the overlapping time between the two time-frames
     */
    public static long getOverlappingTime(TimeFrame timeFrameA, TimeFrame timeFrameB){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimeFrame.getOverlappingTime()");

        if (timeFrameA.startTime < timeFrameB.endTime && timeFrameB.startTime < timeFrameA.endTime){ // they overlap
            if (timeFrameA.startTime < timeFrameB.startTime){
                if (timeFrameA.endTime < timeFrameB.endTime){
                    // b start, a end
                    return TimerUtils.getMillisTimeGapInSeconds(timeFrameB.startTime, timeFrameA.endTime);
                }
                else {// (timeFrameA.endTime >= timeFrameB.endTime)
                    // b start, b end
                    return TimerUtils.getMillisTimeGapInSeconds(timeFrameB.startTime, timeFrameB.endTime);
                }
            }
            else { // (timeFrameA.startTime >= timeFrameB.startTime)
                if (timeFrameA.endTime < timeFrameB.endTime){
                    // a start, a end
                    return TimerUtils.getMillisTimeGapInSeconds(timeFrameA.startTime, timeFrameA.endTime);
                }
                else {// (timeFrameA.endTime >= timeFrameB.endTime)
                    // a start, b end
                    return TimerUtils.getMillisTimeGapInSeconds(timeFrameA.startTime, timeFrameB.endTime);
                }
            }
        }
        return 0L;
    }

}
