import java.util.function.BiPredicate;
import java.util.function.IntPredicate;

/**
 * Utility class.
 */
class Utility {

    /**
     * Check whether the given number is a valid number.
     * @param number number to check
     * @return <b>true</b> if number is valid, <b>false</b> otherwise
     */
    static boolean checkNumber(int number) {
        IntPredicate isValidNumber = x -> x >= 0 && x <= 10;
        return isValidNumber.test(number);
    }

    /**
     * Check whether the sum of the given numbers is less than the maximum number of pins available.
     * @param firstCount first try pin count
     * @param secondCount second try pin count
     * @return <b>true</b> if sum is valid, <b>false</b> otherwise
     */
    static boolean checkIfPinCountIsLessThanAllPins(int firstCount, int secondCount) {
        BiPredicate<Integer, Integer> isLess12 = (x, y) -> (x + y) <= BowlingFrame.ALL_PINS;
        return isLess12.test(firstCount, secondCount);
    }
}
