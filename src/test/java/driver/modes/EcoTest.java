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
class EcoTest {

    @Mock
    private Rpm rpm;
    @Mock
    private GearShifter gearShifter;
    @Spy
    private final Characteristics characteristics = new Characteristics();
    private final Threshold threshold = new Threshold(0.2);

    @InjectMocks
    private Eco driveMode;

    @Test
    public void whenRpmToHigh_shouldShiftIntoHigherGear() {
        final Double increaseThreshold = characteristics.ecoModeRpmIncreaseThreshold(new Aggressiveness());
        when(rpm.get()).thenReturn(increaseThreshold + 500);

        driveMode.handle(threshold);

        verify(gearShifter).shiftIntoHigher();
    }

    @Test
    public void whenRpmTooLow_shouldReduceGear() {
        final Double reduceThreshold = characteristics.ecoModeRpmReduceThreshold(new Aggressiveness());
        when(rpm.get()).thenReturn(reduceThreshold - 500);

        driveMode.handle(threshold);

        verify(gearShifter).reduce();
    }

    @Test
    public void withIncreasedAggressiveness_shouldNotShiftIntoHigher() {
        final Double increaseThreshold = characteristics.ecoModeRpmIncreaseThreshold(new Aggressiveness());
        when(rpm.get()).thenReturn(increaseThreshold + 100);

        driveMode.increaseAggressiveness();
        driveMode.handle(threshold);

        verify(gearShifter, never()).shiftIntoHigher();
    }

    @Test
    public void withIncreasedAggressiveness_shouldNotReduce() {
        final Double reduceThreshold = characteristics.ecoModeRpmReduceThreshold(new Aggressiveness());
        when(rpm.get()).thenReturn(reduceThreshold + 100);

        driveMode.increaseAggressiveness();
        driveMode.handle(threshold);

        verify(gearShifter).reduce();
    }
}