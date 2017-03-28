import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mailan on 28.03.17.
 */
class BowlingFrameTest {
    private BowlingFrame testFrame = new BowlingFrame(1,4, 5,3);
    private BowlingFrame strike = new BowlingFrame(2, 12, 10, 0);
    private BowlingFrame spare = new BowlingFrame(3, 32, 4, 6);

    @Test
    void needStrikeUpdateTrue() {
        assertTrue(strike.needStrikeUpdate());
    }

    @Test
    void needStrikeUpdateFalse() {
        assertFalse(testFrame.needStrikeUpdate());
    }

    @Test
    void needSpareUpdateTrue() {
        assertTrue(spare.needSpareUpdate());

    }

    @Test
    void needSpareUpdateFalse() {
        assertFalse(testFrame.needSpareUpdate());
    }

    @Test
    void getScore() {
        assertEquals(12, testFrame.getScore());
    }

    @Test
    void updateStrikeScore() {
        strike.updateStrikeScore(4,6);
        assertEquals(32, strike.getScore());

    }

    @Test
    void updateSpareScore() {
        spare.updateSpareScore(0);
        assertEquals(42, spare.getScore());

    }

    @Test
    void getFrameNumber() {
        assertEquals(1, testFrame.getFrameNumber());
    }

}