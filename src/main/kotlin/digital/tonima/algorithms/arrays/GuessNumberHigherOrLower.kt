package digital.tonima.algorithms.arrays

/**
 * LeetCode #374 - Guess Number Higher or Lower
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 *
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 *
 * You call a pre-defined API int guess(int num), which returns three possible results:
 * -1: Your guess is higher than the number I picked (i.e. num > pick).
 *  1: Your guess is lower than the number I picked (i.e. num < pick).
 *  0: your guess is equal to the number I picked (i.e. num == pick).
 *
 * Time Complexity: O(log n) - Binary search halves the search space in each iteration
 * Space Complexity: O(1) - Only uses a constant amount of extra space
 */

class GuessNumberHigherOrLower : GuessGame() {
    fun guessNumber(n: Int): Int {
        var start = 1
        var end = n

        while (start <= end) {
            val guessNumber = start + (end - start) / 2
            val guess = guess(guessNumber)
            when (guess) {
                0 -> return guessNumber
                -1 -> end = guessNumber - 1
                else -> start = guessNumber + 1
            }
        }

        return -1
    }
}

abstract class GuessGame {

    /**
     * The number that was "picked" for the guessing game.
     * This is set internally for testing purposes.
     */
    protected var pickedNumber: Int = 0

    /**
     * Forward declaration of guess API.
     * @param num your guess
     * @return -1 if num is higher than the picked number
     *          1 if num is lower than the picked number
     *          0 if num is equal to the picked number
     */
    fun guess(num: Int): Int {
        return when {
            num > pickedNumber -> -1
            num < pickedNumber -> 1
            else -> 0
        }
    }

    /**
     * Helper method to set the picked number for testing.
     * This would not exist in the actual LeetCode environment.
     */
    fun setPicked(number: Int) {
        pickedNumber = number
    }
}
