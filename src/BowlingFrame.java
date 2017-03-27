import java.util.function.IntPredicate;
import java.util.function.BiPredicate;


/**
 * Created by mailan on 27.03.17.
 */
public class BowlingFrame {
    /*Number of pins per frame you can knock down.*/
    public static final int ALL_PINS = 10;
    private static final int STRIKE_POINTS = 10;

    private int score;
    private int lastScore;
    private int frameNumber;
    private int firstCount;
    private int secondCount;
    private boolean waitingForStrikeUpdate = false;
    private boolean waitingForSpareUpdate = false;

    public BowlingFrame(int frameNumber, int lastScore, int firstCount, int secondCount) {
        if(checkIfNumbers(firstCount) && checkIfNumbers(secondCount) && checkIfPinCountIsLessThanAllPins(
                firstCount,secondCount)){
            this.frameNumber = frameNumber;
            if(frameNumber == 0) {
                this.lastScore = 0;
            } else {
                this.lastScore = lastScore;
            }
            this.firstCount = firstCount;
            this.secondCount = secondCount;
            calculateScore();
        } else {
            throw new IllegalArgumentException("Given arguments are not int numbers.");
        }
    }

    private void calculateScore() {
        boolean secondTry = false;
        int score = 0;
        if (this.firstCount < ALL_PINS){
            secondTry = true;
        } else {
            // strike
            score = STRIKE_POINTS;
            this.waitingForStrikeUpdate = true;
        }

        if (secondTry && (this.firstCount + this.secondCount) < ALL_PINS) {
            score = firstCount + secondCount;
        } else {
            // spare
            score = STRIKE_POINTS;
            this.waitingForSpareUpdate = true;
        }

        if (frameNumber > 0) {
            score = score + this.lastScore;
        }

        System.out.println(frameNumber + ": " + score);
       this.score = score;
    }

    public boolean needStrikeUpdate() {
        return this.waitingForStrikeUpdate;
    }

    public boolean needSpareUpdate() {
        return  this.waitingForSpareUpdate;
    }

    public void updateStrikeScore(int nextFirstCount, int nextSecondCount) {
        this.score = this.score + nextFirstCount + nextSecondCount;
        System.out.println("update: " + this.score);
    }

    public void updateSpareScore(int nextFirstCount) {
        this.score = this.score + nextFirstCount;
        System.out.println("update: " + this.score);
    }

    public int getScore() {
        return this.score;
    }

    private static boolean checkIfNumbers(int number) {
        IntPredicate isNumber = x -> x >= 0 && x <= 10;
        return isNumber.test(number);
    }

    private static boolean checkIfPinCountIsLessThanAllPins(int firstCount, int secondCount) {
        BiPredicate<Integer, Integer> isLess12 = (x,y) -> (x + y) <= ALL_PINS;
        return isLess12.test(firstCount, secondCount);
    }
}
