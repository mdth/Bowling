import java.util.Scanner;
import java.util.Stack;

/**
 * Main class to start the Bowling Scoring System with interactive command-line input.
 */
public class MainInput {
    private static final int TOTAL_FRAMES = 10;
    /**
     *
     * @param args unused command argument line
     */
    public static void main(String args []) {
        Stack<BowlingFrame> gameStack = new Stack<>();
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < TOTAL_FRAMES; i++) {
            if (i == TOTAL_FRAMES - 1 ) {
                int firstCount = sc.nextInt();
                // successive strikes in the last round
                if (firstCount == BowlingFrame.ALL_PINS) {
                    int secondCount = sc.nextInt();
                    int thirdCount = sc.nextInt();
                    int lastScore = updateLastBowlingFrame(gameStack, i, firstCount, secondCount);
                    gameStack.add(i, new BowlingFrame(i, lastScore, firstCount, secondCount, thirdCount));
                } else {
                    int secondCount = sc.nextInt();
                    if(firstCount + secondCount == BowlingFrame.ALL_PINS) {
                        int thirdCount = sc.nextInt();
                        int lastScore = updateLastBowlingFrame(gameStack, i, firstCount, secondCount);
                        gameStack.add(i, new BowlingFrame(i, lastScore, firstCount, secondCount, thirdCount));
                    } else {
                        int lastScore = updateLastBowlingFrame(gameStack, i, firstCount, secondCount);
                        gameStack.add(i, new BowlingFrame(i, lastScore, firstCount, secondCount));
                    }
                }
            } else {
                int firstCount = sc.nextInt();
                int secondCount = 0;
                if(firstCount != BowlingFrame.ALL_PINS) {
                    secondCount = sc.nextInt();
                }
                if (i == 0) {
                    // first frame
                    gameStack.add(i, new BowlingFrame(i, 0, firstCount, secondCount));
                } else {
                    int lastScore = updateLastBowlingFrame(gameStack, i, firstCount, secondCount);
                    gameStack.add(i, new BowlingFrame(i, lastScore, firstCount, secondCount));
                }
            }
        }
        // output of the game score
        gameStack.forEach(item -> System.out.println(item.getFirstCount() + " - " + item.getSecondCount() + " --- " + item.getFrameNumber()
                + ": " + item.getScore()));
    }

    private static int updateLastBowlingFrame(Stack<BowlingFrame> gameStack, int i, int firstCount, int secondCount) {
        BowlingFrame lastFrame = gameStack.lastElement();
        int beforeTwo = i - 1;
        if(beforeTwo >= 0) {
            BowlingFrame beforeLastFrame = gameStack.elementAt(beforeTwo);

            // update
            if (lastFrame.needSpareUpdate()) {
                lastFrame.updateSpareScore(firstCount);
            } else if (lastFrame.needStrikeUpdate() && firstCount == BowlingFrame.ALL_PINS) {
                lastFrame.updateStrikeScore(firstCount, secondCount);
                if (i < BowlingFrame.ALL_PINS - 1) {
                    beforeLastFrame.updateStrikeScore(0, firstCount);
                }
            } else if (lastFrame.needStrikeUpdate()) {
            lastFrame.updateStrikeScore(firstCount, secondCount);
            }
        }
        return lastFrame.getScore();
    }
}
