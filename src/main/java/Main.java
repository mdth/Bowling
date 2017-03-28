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
            int firstCount = (int) (Math.random() * BowlingFrame.ALL_PINS);
            int secondCount = (int) Math.round((Math.random() * (BowlingFrame.ALL_PINS - firstCount)));
            System.out.println(firstCount + " -- " + secondCount);

            if (i == 0) {
                // first frame
                gameStack.add(i, new BowlingFrame(i, 0, firstCount, secondCount));
            } else {
                BowlingFrame lastFrame = gameStack.lastElement();
                // update
                if (lastFrame.needSpareUpdate()) {
                    lastFrame.updateSpareScore(firstCount);
                } else if (lastFrame.needStrikeUpdate()) {
                    lastFrame.updateStrikeScore(firstCount, secondCount);
                }
                gameStack.add(i, new BowlingFrame(i, lastFrame.getScore(), firstCount, secondCount));
            }
        }
        // output of the game score
        gameStack.forEach(item -> System.out.println(item.getFrameNumber() + ": " + item.getScore()));
    }
}
