package com.orens.cshs.logic.state;


public class CarryingState extends AbstractHealthState {

    public CarryingState() {
        super(State.Carrying, SICK_TEMPERATURE_THRESHOLD-1);
    }


//    @Override
//    public boolean isChangeState(NormalPerson normalPerson, Participant otherParticipant) {
//        return isChangeState(normalPerson);
//    }
//
//    @Override
//    public boolean isChangeState(InspectorPerson inspectorPerson, Participant otherParticipant) {
//        return isChangeState(inspectorPerson);
//    }
//
//    private boolean isChangeState(Participant participant){
//        TimeFrame timeFrame = participant.getCurrentHealthState().getTimeFrameOfStateChangedTillNow();
//        long period = timeFrame.getPeriodInSeconds();
//
//        boolean trueWith40Percent = RandomGenerator.trueWith40PercentProbability();
//        boolean trueWith60Percent = RandomGenerator.trueWith60PercentProbability();
//        boolean trueWith80Percent = RandomGenerator.trueWith80PercentProbability();
//
//        return (    ((period == Seconds.One.getValue() || period == Seconds.Five.getValue()) && trueWith40Percent) ||
//                ((period == Seconds.Two.getValue() || period == Seconds.Four.getValue()) && trueWith60Percent) ||
//                (period == Seconds.Three.getValue() && trueWith80Percent) ||
//                (period >= Seconds.Six.getValue()));
//    }

}
