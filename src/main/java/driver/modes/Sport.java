package driver.modes;

import driver.*;

public class Sport implements DriveMode {
    private final Rpm rpm;
    private final Characteristics characteristics;
    private final GearShifter gearShifter;
    private final Aggressiveness aggressiveness = new Aggressiveness();

    public Sport(Rpm rpm, Characteristics characteristics, GearShifter gearShifter) {
        this.rpm = rpm;
        this.characteristics = characteristics;
        this.gearShifter = gearShifter;
    }

    public void handle(Threshold threshold) {
        if (shouldReduceInKickdown(threshold)) {
            handleKickdown(threshold);
        } else if (shouldReduce()) {
            gearShifter.reduce();
        } else if (shouldShiftIntoHigherGear()) {
            gearShifter.shiftIntoHigher();
        }
    }

    public void increaseAggressiveness() {
        aggressiveness.increase();
    }

    public void decreaseAggressiveness() {
        aggressiveness.decrease();
    }

    private void handleKickdown(Threshold threshold) {
        if (isHardKickdown(threshold)) {
            gearShifter.reduceTwo();
        } else {
            gearShifter.reduce();
        }
    }

    private boolean shouldShiftIntoHigherGear() {
        return rpm.get() > characteristics.sportRpmIncreaseThreshold(aggressiveness);
    }

    private boolean shouldReduceInKickdown(Threshold threshold) {
        return isSoftKickdown(threshold) || isHardKickdown(threshold);
    }

    private boolean shouldReduce() {
        return rpm.get() < characteristics.sportRpmReduceThreshold(aggressiveness);
    }

    private boolean isSoftKickdown(Threshold threshold) {
        return threshold.get() > characteristics.sportKickdownThreshold() && rpm.get() < characteristics.sportSoftKickdownRpmReduceThreshold(aggressiveness);
    }

    private boolean isHardKickdown(Threshold threshold) {
        return threshold.get() > characteristics.sportHardKickDownThreshold() && rpm.get() < characteristics.sportHardKickdownRpmReduceThreshold(aggressiveness);
    }
}
