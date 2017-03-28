/**
 * A game state for a bowling scoring system.
 */
class BowlingFrame {
    /*Number of pins per frame you can knock down.*/
    static final int ALL_PINS = 10;
    private static final int STRIKE_POINTS = 10;

    private int score;
    private int lastScore;
    private int frameNumber;
    private int firstCount;
    private int secondCount;
    private boolean waitingForStrikeUpdate = false;
    private boolean waitingForSpareUpdate = false;

    /**
     * Create a BowlingFrame that stores important information of a game state.
     * @param frameNumber number of the frame
     * @param lastScore score of the last @link{BowlingFrame} if there exists one
     * @param firstCount count of the knocked pins on the first try
     * @param secondCount count of the knocked pins on the second try
     */
    BowlingFrame(int frameNumber, int lastScore, int firstCount, int secondCount) {
        initializeFrameNumberAndLastScore(frameNumber, lastScore);

        if(Utility.checkNumber(firstCount) && Utility.checkNumber(secondCount)) {
            if (Utility.checkIfPinCountIsLessThanAllPins(firstCount, secondCount)) {
                this.firstCount = firstCount;
                this.secondCount = secondCount;
                calculateScore();
            } else {
                throw new IllegalArgumentException("Sum of the given arguments are greater than number of all pins.");
            }
        } else {
            throw new IllegalArgumentException("Given arguments are not valid int numbers.");
        }
    }

    private void initializeFrameNumberAndLastScore(int frameNumber, int lastScore) {
        this.frameNumber = frameNumber;
        // first BowlingFrame score does not have a previous score to add to
        if (frameNumber == 0) {
            this.lastScore = 0;
        } else {
            this.lastScore = lastScore;
        }
    }

    private void calculateScore() {
        boolean secondTry = false;
        int score = 0;
        if (this.firstCount < ALL_PINS){
            secondTry = true;
        } else {
            // strike
            this.waitingForStrikeUpdate = true;
            score = STRIKE_POINTS;
        }

        if (secondTry && (this.firstCount + this.secondCount) < ALL_PINS) {
            score = firstCount + secondCount;
        } else if (secondTry && this.firstCount + this.secondCount == ALL_PINS){
            // spare
            score = STRIKE_POINTS;
            this.waitingForSpareUpdate = true;
        }

        if (frameNumber > 0) {
            score = score + this.lastScore;
        }
       this.score = score;
    }

    /**
     * Return if this BowlingFrame needs a score update due to a strike.
     * @return <b>true</b> if update is needed, <b>false</b> otherwise
     */
    boolean needStrikeUpdate() {
        return this.waitingForStrikeUpdate;
    }

    /**
     * Return if this BowlingFrame needs a score update due to a spare.
     * @return <b>true</b> if update is needed, <b>false</b> otherwise
     */
    boolean needSpareUpdate() {
        return this.waitingForSpareUpdate;
    }

    /**
     * Updates strike score by adding the next two knocked pin counts to the strike score.
     */
    void updateStrikeScore(int nextFirstCount, int nextSecondCount) {
        this.score = this.score + nextFirstCount + nextSecondCount;
    }

    /**
     * Updates spare score by adding the next two knocked pin counts to the spare score.
     */
    void updateSpareScore(int nextFirstCount) {
        this.score = this.score + nextFirstCount;
    }

    /**
     * Return the score of the BowlingFrame.
     * @return score of the BowlingFrame
     */
    int getScore() {
        return this.score;
    }

    /**
     * Return frame number of the BowlingFrame.
     * @return frame number of the BowlingFrame
     */
    int getFrameNumber() {
        return this.frameNumber;
    }
}
