package com.orens.cshs.infra.logger;

import org.apache.logging.log4j.Level;

import java.util.EnumSet;

public enum ReportLevel {

    /**
     * No events will be logged.
     */
    OFF(Level.OFF.intLevel()),

    /**
     * A severe error that will prevent the application from continuing.
     */
    FATAL(Level.FATAL.intLevel()),

    /**
     * An error in the application, possibly recoverable.
     */
    ERROR(Level.ERROR.intLevel()),

    /**
     * An event that might possible lead to an error.
     */
    WARN(Level.WARN.intLevel()),

    /**
     * An event for informational purposes.
     */
    INFO(Level.INFO.intLevel()),

    /**
     * A general debugging event.
     */
    DEBUG(Level.DEBUG.intLevel()),

    /**
     * A fine-grained debug message, typically capturing the flow through the application.
     */
    TRACE(Level.TRACE.intLevel()),

    /**
     * All events should be logged.
     */
    ALL(Level.ALL.intLevel());

    private static final EnumSet<ReportLevel> LEVELSET = EnumSet.allOf(ReportLevel.class);

    private final int intLevel;

    ReportLevel(final int val) {
        intLevel = val;
    }

    /**
     * Returns the integer value of the Level.
     *
     * @return the integer value of the Level.
     */
    public int intLevel() {
        return intLevel;
    }

    /**
     * Method to convert custom Levels into a StandardLevel for conversion to other systems.
     *
     * @param intLevel The integer value of the Level.
     * @return The ReportLevel.
     */
    public static ReportLevel getStandardLevel(final int intLevel) {
        ReportLevel level = ReportLevel.OFF;
        for (final ReportLevel lvl : LEVELSET) {
            if (lvl.intLevel() > intLevel) {
                break;
            }
            level = lvl;
        }
        return level;
    }

}



