package driver;

import driver.modes.DriveMode;

public class GearboxDriver {
    private final State state;
    private final GearShifter gearShifter;
    private DriveMode driveMode;

    public GearboxDriver(State state, GearShifter gearShifter, DriveMode driveMode) {
        this.state = state;
        this.gearShifter = gearShifter;
        this.driveMode = driveMode;
    }

    public void adjustGear(Threshold threshold) {
        if (state.isDrive()) {
            driveMode.handle(threshold);
        }
    }

    public void manualShiftToHigherGear() {
        gearShifter.shiftIntoHigher();
    }

    public void manualReduceGear() {
        gearShifter.reduce();
    }

    public void increaseAggressiveness() {
        driveMode.increaseAggressiveness();
    }

    public void decreaseAggressiveness() {
        driveMode.decreaseAggressiveness();
    }

    public void changeDriveMode(DriveMode driveMode) {
        this.driveMode = driveMode;
    }
}
