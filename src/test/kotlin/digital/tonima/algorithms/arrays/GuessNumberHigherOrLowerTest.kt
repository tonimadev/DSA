package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import kotlin.test.assertEquals

/**
 * Test cases for LeetCode #374 - Guess Number Higher or Lower
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 */
class GuessNumberHigherOrLowerTest {

    @Test
    @DisplayName("guessNumber - LeetCode Example 1")
    fun testExample1() {
        // Input: n = 10, pick = 6
        // Output: 6
        val game = GuessNumberHigherOrLower()
        game.setPicked(6)
        assertEquals(6, game.guessNumber(10))
    }

    @Test
    @DisplayName("guessNumber - LeetCode Example 2")
    fun testExample2() {
        // Input: n = 1, pick = 1
        // Output: 1
        val game = GuessNumberHigherOrLower()
        game.setPicked(1)
        assertEquals(1, game.guessNumber(1))
    }

    @Test
    @DisplayName("guessNumber - LeetCode Example 3")
    fun testExample3() {
        // Input: n = 2, pick = 1
        // Output: 1
        val game = GuessNumberHigherOrLower()
        game.setPicked(1)
        assertEquals(1, game.guessNumber(2))
    }

    @Test
    @DisplayName("guessNumber - pick is at the beginning")
    fun testPickAtBeginning() {
        val game = GuessNumberHigherOrLower()
        game.setPicked(1)
        assertEquals(1, game.guessNumber(100))
    }

    @Test
    @DisplayName("guessNumber - pick is at the end")
    fun testPickAtEnd() {
        val game = GuessNumberHigherOrLower()
        game.setPicked(100)
        assertEquals(100, game.guessNumber(100))
    }

    @Test
    @DisplayName("guessNumber - pick is in the middle")
    fun testPickInMiddle() {
        val game = GuessNumberHigherOrLower()
        game.setPicked(50)
        assertEquals(50, game.guessNumber(100))
    }

    @Test
    @DisplayName("guessNumber - large range with pick at beginning")
    fun testLargeRangePickAtBeginning() {
        val game = GuessNumberHigherOrLower()
        game.setPicked(1)
        assertEquals(1, game.guessNumber(1000))
    }

    @Test
    @DisplayName("guessNumber - large range with pick at end")
    fun testLargeRangePickAtEnd() {
        val game = GuessNumberHigherOrLower()
        game.setPicked(1000)
        assertEquals(1000, game.guessNumber(1000))
    }

    @Test
    @DisplayName("guessNumber - large range with pick in middle")
    fun testLargeRangePickInMiddle() {
        val game = GuessNumberHigherOrLower()
        game.setPicked(500)
        assertEquals(500, game.guessNumber(1000))
    }

    @Test
    @DisplayName("guessNumber - n = 2, pick = 2")
    fun testN2Pick2() {
        val game = GuessNumberHigherOrLower()
        game.setPicked(2)
        assertEquals(2, game.guessNumber(2))
    }

    @Test
    @DisplayName("guessNumber - random picks in small range")
    fun testRandomPicksSmallRange() {
        val picks = listOf(3, 7, 5, 9, 1)
        val n = 10

        for (pick in picks) {
            val game = GuessNumberHigherOrLower()
            game.setPicked(pick)
            assertEquals(pick, game.guessNumber(n))
        }
    }

    @Test
    @DisplayName("guessNumber - random picks in large range")
    fun testRandomPicksLargeRange() {
        val picks = listOf(250, 750, 500, 999, 1)
        val n = 1000

        for (pick in picks) {
            val game = GuessNumberHigherOrLower()
            game.setPicked(pick)
            assertEquals(pick, game.guessNumber(n))
        }
    }
}

