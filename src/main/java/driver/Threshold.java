package driver;

public class Threshold {
    private final double threshold;

    public Threshold(double threshold) {
        if (threshold < 0 || threshold > 1) {
            throw new InvalidThresholdException();
        }
        this.threshold = threshold;
    }

    public double get() {
        return threshold;
    }
}
