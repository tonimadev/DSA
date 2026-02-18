package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Test cases for LeetCode #303 - Range Sum Query - Immutable
 * https://leetcode.com/problems/range-sum-query-immutable/
 */
class RangeSumQueryImmutableTest {

    @Test
    fun `test sumRange - LeetCode example`() {
        // Input: nums = [-2,0,3,-5,2,-1]
        val numArray = NumArray(intArrayOf(-2, 0, 3, -5, 2, -1))

        // Output: 1
        assertEquals(1, numArray.sumRange(0, 2))

        // Output: -1
        assertEquals(-1, numArray.sumRange(2, 5))

        // Output: -3
        assertEquals(-3, numArray.sumRange(0, 5))
    }

    @Test
    fun `test sumRange - single element`() {
        val numArray = NumArray(intArrayOf(5))
        assertEquals(5, numArray.sumRange(0, 0))
    }

    @Test
    fun `test sumRange - left equals right in larger array`() {
        val numArray = NumArray(intArrayOf(4, -1, 7))
        assertEquals(-1, numArray.sumRange(1, 1))
    }

    @Test
    fun `test sumRange - full range`() {
        val numArray = NumArray(intArrayOf(1, 2, 3, 4))
        assertEquals(10, numArray.sumRange(0, 3))
    }
}

