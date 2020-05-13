package driver;

public class Characteristics {
    private final Object[] THRESHOLDS = new Object[]{2000d, 1000d, 1000d, 0.5d, 2500d, 4500d, 1500d, 0.5d,
            5000d, 0.7d, 5000d, 5000d, 1500d, 2000d, 3000d, 6500d, 14d};

    public Double ecoModeRpmIncreaseThreshold(Aggressiveness aggressiveness) {
        return (Double) THRESHOLDS[0] * aggressiveness.getMultiplier();
    }

    public Double ecoModeRpmReduceThreshold(Aggressiveness aggressiveness) {
        return (Double) THRESHOLDS[1] * aggressiveness.getMultiplier();
    }

    public Double comfortRpmIncreaseThreshold(Aggressiveness aggressiveness) {
        return (Double) THRESHOLDS[4] * aggressiveness.getMultiplier();
    }

    public Double comfortRpmReduceThreshold(Aggressiveness aggressiveness) {
        return (Double) THRESHOLDS[2] * aggressiveness.getMultiplier();
    }

    public Double comfortKickdownRpmReduceThreshold(Aggressiveness aggressiveness) {
        return (Double) THRESHOLDS[5] * aggressiveness.getMultiplier();
    }

    public Double sportRpmIncreaseThreshold(Aggressiveness aggressiveness) {
        return (Double) THRESHOLDS[8] * aggressiveness.getMultiplier();
    }

    public Double sportRpmReduceThreshold(Aggressiveness aggressiveness) {
        return (Double) THRESHOLDS[6] * aggressiveness.getMultiplier();
    }

    public Double comfortKickdownThreshold() {
        return (Double) THRESHOLDS[3];
    }

    public Double sportKickdownThreshold() {
        return (Double) THRESHOLDS[7];
    }

    public Double sportHardKickDownThreshold() {
        return (Double) THRESHOLDS[9];
    }

    public Double sportSoftKickdownRpmReduceThreshold(Aggressiveness aggressiveness) {
        return (Double) THRESHOLDS[10] * aggressiveness.getMultiplier();
    }

    public Double sportHardKickdownRpmReduceThreshold(Aggressiveness aggressiveness) {
        return (Double) THRESHOLDS[11] * aggressiveness.getMultiplier();
    }
}
