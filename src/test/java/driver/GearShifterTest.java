package driver;

import gearbox.Gearbox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GearShifterTest {
    private static final int GEARBOX_MAX_DRIVE = 6;
    private final Gearbox gearbox = new Gearbox();

    private final GearShifter gearShifter = new GearShifter(gearbox);

    @BeforeEach
    public void setUp() {
        gearbox.setMaxDrive(GEARBOX_MAX_DRIVE);
    }

    @Test
    public void whenCurrentGearLowerThanMax_ShouldShiftIntoHigher() {
        gearbox.setCurrentGear(4);

        gearShifter.shiftIntoHigher();

        assertThat(gearbox.getCurrentGear()).isEqualTo(5);
    }

    @Test
    public void whenCurrentGearIsMax_ShouldNotShiftIntoHigher() {
        gearbox.setCurrentGear(GEARBOX_MAX_DRIVE);

        gearShifter.shiftIntoHigher();

        assertThat(gearbox.getCurrentGear()).isEqualTo(GEARBOX_MAX_DRIVE);
    }

    @Test
    public void whenCurrentGearIsGreaterThanOne_ShouldReduce() {
        gearbox.setCurrentGear(2);

        gearShifter.reduce();

        assertThat(gearbox.getCurrentGear()).isEqualTo(1);
    }

    @Test
    public void whenCurrentGearIsEqualToOne_ShouldNotReduce() {
        gearbox.setCurrentGear(1);

        gearShifter.reduce();

        assertThat(gearbox.getCurrentGear()).isEqualTo(1);
    }

    @Test
    public void whenCurrentGearGreaterThanTwo_ShouldReduceTwo() {
        gearbox.setCurrentGear(3);

        gearShifter.reduceTwo();

        assertThat(gearbox.getCurrentGear()).isEqualTo(1);
    }

    @Test
    public void whenCurrentGearEqualToTwo_ShouldReduceOne() {
        gearbox.setCurrentGear(2);

        gearShifter.reduceTwo();

        assertThat(gearbox.getCurrentGear()).isEqualTo(1);
    }

    @Test
    public void whenCurrentGearIsEqualToOne_ShouldNotReduceTwo() {
        gearbox.setCurrentGear(1);

        gearShifter.reduceTwo();

        assertThat(gearbox.getCurrentGear()).isEqualTo(1);
    }
}