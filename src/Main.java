import java.util.Stack;

/**
 * Created by mailan on 27.03.17.
 */
public class Main {
    public static final int TOTAL_FRAMES = 10;
    /**
     *
     * @param args unused command argument line
     */
    public static void main(String args []) {
        Stack<BowlingFrame> gameStack = new Stack<>();

        for(int i = 0; i < TOTAL_FRAMES; i++) {
            int firstCount = (int) (Math.random() * BowlingFrame.ALL_PINS);
            int secondCount = (int) (Math.random() * (BowlingFrame.ALL_PINS - firstCount));
            System.out.println("firstCount: " + firstCount + " - secondCount: " + secondCount);

            if (i == 0) {
                // first frame
                gameStack.add(i, new BowlingFrame(i, 0, firstCount, secondCount));
            } else {
                // following frames
                BowlingFrame lastFrame = gameStack.lastElement();
                if (lastFrame.needSpareUpdate()) {
                    lastFrame.updateSpareScore(firstCount);
                } else if (lastFrame.needStrikeUpdate()) {
                    lastFrame.updateStrikeScore(firstCount, secondCount);
                } else {
                    // do nothing
                }
                gameStack.add(i, new BowlingFrame(i, lastFrame.getScore(), firstCount, secondCount));
            }
        }
    }
}
