package driver.modes;

import driver.Threshold;

public interface DriveMode {
    void handle(Threshold threshold);

    void increaseAggressiveness();

    void decreaseAggressiveness();
}
