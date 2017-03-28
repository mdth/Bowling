import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mailan on 28.03.17.
 */
class UtilityTest {
    @Test
    void checkNumberIsNotInRange() {
        assertFalse(Utility.checkNumber(-5));
    }

    @Test
    void checkIfPinCountIsLessThanAllPins() {
        assertFalse(Utility.checkIfPinCountIsLessThanAllPins(4, 9));
    }

    @Test
    void checkIfPinCountIsLessThanAllPins2() {
        assertTrue(Utility.checkIfPinCountIsLessThanAllPins(2));
    }

    @Test
    void createRandomNumber() {
        assertTrue(Utility.checkNumber(Utility.createRandomNumber()));
    }

    @Test
    void createRandomNumberWithRemainingPins() {
        assertTrue(Utility.checkNumber(Utility.createRandomNumberWithRemainingPins(Utility.createRandomNumber())));
    }

}