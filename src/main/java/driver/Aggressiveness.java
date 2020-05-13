package driver;

public class Aggressiveness {
    private int mode = 1;

    public void increase() {
        if (mode < 3) {
            mode++;
        }
    }

    public void decrease() {
        if (mode > 1) {
            mode--;
        }
    }

    public double getMultiplier() {
        switch (mode) {
            case 2:
                return 1.2;
            case 3:
                return 1.3;
            default:
                return 1;
        }
    }
}
