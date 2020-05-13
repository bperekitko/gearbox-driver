package driver;

import gearbox.Gearbox;

public class State {
    private static final int GEARBOX_DRIVE_STATE = 1;
    private final Gearbox gearbox;

    public State(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    boolean isDrive() {
        return ((Integer) gearbox.getState()) == GEARBOX_DRIVE_STATE;
    }
}
