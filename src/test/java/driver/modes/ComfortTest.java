package driver.modes;

import driver.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComfortTest {

    @Mock
    private Rpm rpm;
    @Mock
    private GearShifter gearShifter;
    @Spy
    private final Characteristics characteristics = new Characteristics();

    @InjectMocks
    private Comfort driveMode;

    @Test
    public void whenRpmToHigh_shouldShiftIntoHigherGear() {
        final Double increaseThreshold = characteristics.comfortRpmIncreaseThreshold(new Aggressiveness());
        final Double kickdownThreshold = characteristics.comfortKickdownThreshold();

        when(rpm.get()).thenReturn(increaseThreshold + 500);
        Threshold threshold = new Threshold(kickdownThreshold - 0.1);

        driveMode.handle(threshold);

        verify(gearShifter).shiftIntoHigher();
    }

    @Test
    public void whenRpmTooLow_shouldReduceGear() {
        final Double reduceRpmThreshold = characteristics.comfortRpmReduceThreshold(new Aggressiveness());
        final Double kickdownThreshold = characteristics.comfortKickdownThreshold();

        when(rpm.get()).thenReturn(reduceRpmThreshold - 500);
        Threshold threshold = new Threshold(kickdownThreshold - 0.1);

        driveMode.handle(threshold);

        verify(gearShifter).reduce();
    }

    @Test
    public void withIncreasedAggressiveness_shouldNotShiftIntoHigher() {
        final Double increaseThreshold = characteristics.comfortRpmIncreaseThreshold(new Aggressiveness());
        final Double kickdownThreshold = characteristics.comfortKickdownThreshold();

        when(rpm.get()).thenReturn(increaseThreshold + 100);
        Threshold threshold = new Threshold(kickdownThreshold - 0.1);

        driveMode.increaseAggressiveness();
        driveMode.handle(threshold);

        verify(gearShifter, never()).shiftIntoHigher();
    }

    @Test
    public void withIncreasedAggressiveness_shouldNotReduce() {
        final Double reduceThreshold = characteristics.comfortRpmReduceThreshold(new Aggressiveness());
        final Double kickdownThreshold = characteristics.comfortKickdownThreshold();

        when(rpm.get()).thenReturn(reduceThreshold + 100);
        Threshold threshold = new Threshold(kickdownThreshold - 0.1);

        driveMode.increaseAggressiveness();
        driveMode.handle(threshold);

        verify(gearShifter).reduce();
    }

    @Test
    public void whenKickdownAndRpmNotExceeded_shouldReduce(){
        final Double reduceThreshold = characteristics.comfortKickdownRpmReduceThreshold(new Aggressiveness());
        final Double kickdownThreshold = characteristics.comfortKickdownThreshold();

        when(rpm.get()).thenReturn(reduceThreshold - 100);
        Threshold threshold = new Threshold(kickdownThreshold + 0.1);

        driveMode.handle(threshold);

        verify(gearShifter).reduce();
    }

    @Test
    public void whenKickdownAndRpmExceeded_shouldNotReduce(){
        final Double reduceThreshold = characteristics.comfortKickdownRpmReduceThreshold(new Aggressiveness());
        final Double kickdownThreshold = characteristics.comfortKickdownThreshold();

        when(rpm.get()).thenReturn(reduceThreshold + 100);
        Threshold threshold = new Threshold(kickdownThreshold + 0.1);

        driveMode.handle(threshold);

        verify(gearShifter, never()).reduce();
    }
}