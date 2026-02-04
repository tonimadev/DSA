package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

/**
 * Test cases for LeetCode #1929 - Concatenation of Array
 * https://leetcode.com/problems/concatenation-of-array/
 */
class ArrayConcatenationTest {

    private val solution = ArrayConcatenationSolution()

    @Test
    fun `test getConcatenation - LeetCode Example 1`() {
        // Input: nums = [1,2,1]
        // Output: [1,2,1,1,2,1]
        val nums = intArrayOf(1, 2, 1)
        val expected = intArrayOf(1, 2, 1, 1, 2, 1)

        val result = solution.getConcatenation(nums)

        assertTrue(
            result.contentEquals(expected),
            "Expected=${expected.joinToString(prefix = "[", postfix = "]")}, " +
                "Actual=${result.joinToString(prefix = "[", postfix = "]")}"
        )
    }

    @Test
    fun `test getConcatenation - LeetCode Example 2`() {
        // Input: nums = [1,3,2,1]
        // Output: [1,3,2,1,1,3,2,1]
        val nums = intArrayOf(1, 3, 2, 1)
        val expected = intArrayOf(1, 3, 2, 1, 1, 3, 2, 1)

        val result = solution.getConcatenation(nums)

        assertTrue(
            result.contentEquals(expected),
            "Expected=${expected.joinToString(prefix = "[", postfix = "]")}, " +
                "Actual=${result.joinToString(prefix = "[", postfix = "]")}"
        )
    }

    @Test
    fun `test getConcatenation - single element`() {
        val nums = intArrayOf(7)
        val expected = intArrayOf(7, 7)

        val result = solution.getConcatenation(nums)

        assertTrue(
            result.contentEquals(expected),
            "Expected=${expected.joinToString(prefix = "[", postfix = "]")}, " +
                "Actual=${result.joinToString(prefix = "[", postfix = "]")}"
        )
    }

    @Test
    fun `test getConcatenation - empty array`() {
        val nums = intArrayOf()
        val expected = intArrayOf()

        val result = solution.getConcatenation(nums)

        assertTrue(
            result.contentEquals(expected),
            "Expected=${expected.joinToString(prefix = "[", postfix = "]")}, " +
                "Actual=${result.joinToString(prefix = "[", postfix = "]")}"
        )
    }
}
