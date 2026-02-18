package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Test cases for LeetCode #724 - Find Pivot Index
 * https://leetcode.com/problems/find-pivot-index/
 */
class FindPivotIndexTest {

    private val solution = FindPivotIndexSolution()

    @Test
    fun `test pivotIndex - LeetCode example 1`() {
        // Input: nums = [1,7,3,6,5,6]
        // Output: 3
        val nums = intArrayOf(1, 7, 3, 6, 5, 6)
        assertEquals(3, solution.pivotIndex(nums))
    }

    @Test
    fun `test pivotIndex - LeetCode example 2`() {
        // Input: nums = [1,2,3]
        // Output: -1
        val nums = intArrayOf(1, 2, 3)
        assertEquals(-1, solution.pivotIndex(nums))
    }

    @Test
    fun `test pivotIndex - LeetCode example 3`() {
        // Input: nums = [2,1,-1]
        // Output: 0
        val nums = intArrayOf(2, 1, -1)
        assertEquals(0, solution.pivotIndex(nums))
    }

    @Test
    fun `test pivotIndex - single element`() {
        // Input: nums = [0]
        // Output: 0
        val nums = intArrayOf(0)
        assertEquals(0, solution.pivotIndex(nums))
    }
}

