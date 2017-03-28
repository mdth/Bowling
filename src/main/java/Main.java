import java.util.Stack;

/**
 * Created by mailan on 27.03.17.
 */
public class Main {
    private static final int TOTAL_FRAMES = 10;
    /**
     *
     * @param args unused command argument line
     */
    public static void main(String args []) {
        Stack<BowlingFrame> gameStack = new Stack<>();

        for(int i = 0; i < TOTAL_FRAMES; i++) {
            if (i == TOTAL_FRAMES - 1 ) {
                int firstCount = Utility.createRandomNumber();
                if (firstCount == BowlingFrame.ALL_PINS) {
                    int secondCount = Utility.createRandomNumber();
                    int thirdCount;
                    if(secondCount == BowlingFrame.ALL_PINS) {
                        thirdCount = Utility.createRandomNumber();
                    } else {
                        thirdCount = Utility.createRandomNumberWithRemainingPins(secondCount);
                    }
                    int lastScore = updateLastBowlingFrame(gameStack, firstCount, secondCount);
                    gameStack.add(i, new BowlingFrame(i, lastScore, firstCount, secondCount, thirdCount));
                } else {
                    int secondCount = Utility.createRandomNumberWithRemainingPins(firstCount);
                    if(firstCount + secondCount == BowlingFrame.ALL_PINS) {
                        int thirdCount = Utility.createRandomNumber();
                        int lastScore = updateLastBowlingFrame(gameStack, firstCount, secondCount);
                        gameStack.add(i, new BowlingFrame(i, lastScore, firstCount, secondCount, thirdCount));
                    } else {
                        int lastScore = updateLastBowlingFrame(gameStack, firstCount, secondCount);
                        gameStack.add(i, new BowlingFrame(i, lastScore, firstCount, secondCount));
                    }
                }
            } else {
                int firstCount = Utility.createRandomNumber();
                int secondCount = Utility.createRandomNumberWithRemainingPins(firstCount);

                if (i == 0) {
                    // first frame
                    gameStack.add(i, new BowlingFrame(i, 0, firstCount, secondCount));
                } else {
                    int lastScore = updateLastBowlingFrame(gameStack, firstCount, secondCount);
                    gameStack.add(i, new BowlingFrame(i, lastScore, firstCount, secondCount));
                }
            }
        }
        // output of the game score
        gameStack.forEach(item -> System.out.println(item.getFirstCount() + " - " + item.getSecondCount() + " --- " + item.getFrameNumber()
                + ": " + item.getScore()));
    }

    private static int updateLastBowlingFrame(Stack<BowlingFrame> gameStack, int firstCount, int secondCount) {
        BowlingFrame lastFrame = gameStack.lastElement();
        // update
        if (lastFrame.needSpareUpdate()) {
            lastFrame.updateSpareScore(firstCount);
        } else if (lastFrame.needStrikeUpdate()) {
            lastFrame.updateStrikeScore(firstCount, secondCount);
        }
        return lastFrame.getScore();
    }
}
