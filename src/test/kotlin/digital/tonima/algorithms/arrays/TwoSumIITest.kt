package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Test cases for LeetCode #167 - Two Sum II - Input Array Is Sorted
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
class TwoSumIITest {

    private val solution = TwoSumIISolution()

    @Test
    fun `twoSum - LeetCode example 1`() {
        val numbers = intArrayOf(2, 7, 11, 15)
        val target = 9
        val result = solution.twoSum(numbers, target)

        assertNotNull(result)
        assertEquals(2, result.size)
        assertTrue(result.contentEquals(intArrayOf(1, 2)))
    }

    @Test
    fun `twoSum - LeetCode example 2`() {
        val numbers = intArrayOf(2, 3, 4)
        val target = 6
        val result = solution.twoSum(numbers, target)

        assertNotNull(result)
        assertEquals(2, result.size)
        assertTrue(result.contentEquals(intArrayOf(1, 3)))
    }

    @Test
    fun `twoSum - LeetCode example 3`() {
        val numbers = intArrayOf(-1, 0)
        val target = -1
        val result = solution.twoSum(numbers, target)

        assertNotNull(result)
        assertEquals(2, result.size)
        assertTrue(result.contentEquals(intArrayOf(1, 2)))
    }

    @Test
    fun `twoSum - duplicates`() {
        val numbers = intArrayOf(1, 2, 3, 4, 4, 9, 56, 90)
        val target = 8
        val result = solution.twoSum(numbers, target)

        assertNotNull(result)
        assertEquals(2, result.size)
        assertTrue(result.contentEquals(intArrayOf(4, 5)))
    }

    @Test
    fun `twoSum - negative and positive`() {
        val numbers = intArrayOf(-10, -3, 0, 2, 4, 7, 11)
        val target = 1
        val result = solution.twoSum(numbers, target)

        assertNotNull(result)
        assertEquals(2, result.size)
        assertTrue(result.contentEquals(intArrayOf(1, 7)))
    }
}
