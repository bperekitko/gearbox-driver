package driver.modes;

import driver.*;

public class Comfort implements DriveMode {
    private final Rpm rpm;
    private final Characteristics characteristics;
    private final GearShifter gearShifter;
    private final Aggressiveness aggressiveness = new Aggressiveness();

    public Comfort(Rpm rpm, Characteristics characteristics, GearShifter gearShifter) {
        this.rpm = rpm;
        this.characteristics = characteristics;
        this.gearShifter = gearShifter;
    }

    public void handle(Threshold threshold) {
        if (shouldReduceGear(threshold)) {
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

    private boolean shouldShiftIntoHigherGear() {
        return rpm.get() > characteristics.comfortRpmIncreaseThreshold(aggressiveness);
    }

    private boolean shouldReduceGear(Threshold threshold) {
        return shouldReduceInKickdown(threshold) || rpm.get() < characteristics.comfortRpmReduceThreshold(aggressiveness);
    }

    private boolean shouldReduceInKickdown(Threshold threshold) {
        return threshold.get() > characteristics.comfortKickdownThreshold() && rpm.get() < characteristics.comfortKickdownRpmReduceThreshold(aggressiveness);
    }
}
