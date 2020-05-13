package driver.modes;

import driver.*;

public class Eco implements DriveMode {
    private final Rpm rpm;
    private final Characteristics characteristics;
    private final GearShifter gearShifter;
    private final Aggressiveness aggressiveness = new Aggressiveness();

    public Eco(Rpm rpm, Characteristics characteristics, GearShifter gearShifter) {
        this.rpm = rpm;
        this.characteristics = characteristics;
        this.gearShifter = gearShifter;
    }

    public void handle(Threshold threshold) {
        if (shouldShiftIntoHigherGear()) {
            gearShifter.shiftIntoHigher();
        } else if (shouldReduceGear()) {
            gearShifter.reduce();
        }
    }

    public void increaseAggressiveness() {
        aggressiveness.increase();
    }

    public void decreaseAggressiveness() {
        aggressiveness.decrease();
    }

    private boolean shouldShiftIntoHigherGear() {
        return rpm.get() > characteristics.ecoModeRpmIncreaseThreshold(aggressiveness);
    }

    private boolean shouldReduceGear() {
        return rpm.get() < characteristics.ecoModeRpmReduceThreshold(aggressiveness);
    }
}
