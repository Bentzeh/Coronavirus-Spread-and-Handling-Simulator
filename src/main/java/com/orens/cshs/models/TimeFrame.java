package com.orens.cshs.models;

import com.orens.cshs.infra.utils.TimerUtils;

public class TimeFrame {

    private long startTime;
    private long endTime;
    private long periodInSeconds;

    public TimeFrame(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.periodInSeconds = TimerUtils.getMillisTimeGapInSeconds(startTime, endTime);
    }


    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getPeriodInSeconds() {
        return periodInSeconds;
    }

    public static long getOverlappingTime(TimeFrame timeFrameA, TimeFrame timeFrameB){
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
