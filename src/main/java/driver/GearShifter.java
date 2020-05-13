package driver;

import gearbox.Gearbox;

public class GearShifter {
    private final Gearbox gearbox;

    public GearShifter(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public void shiftIntoHigher() {
        if (canIncrease()) {
            gearbox.setCurrentGear(currentGear() + 1);
        }
    }

    public void reduce() {
        if (canReduceOne()) {
            gearbox.setCurrentGear(currentGear() - 1);
        }
    }

    public void reduceTwo() {
        if (canReduceTwo()) {
            gearbox.setCurrentGear(currentGear() - 2);
        } else if (canReduceOne()) {
            gearbox.setCurrentGear(currentGear() - 1);
        }
    }

    private boolean canReduceTwo() {
        return currentGear() > 2;
    }

    private boolean canIncrease() {
        return currentGear() < gearbox.getMaxDrive();
    }

    private boolean canReduceOne() {
        return currentGear() > 1;
    }

    private Integer currentGear() {
        return (Integer) gearbox.getCurrentGear();
    }
}
