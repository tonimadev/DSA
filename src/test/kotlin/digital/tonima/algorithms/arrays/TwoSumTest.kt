package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Test cases for LeetCode #1 - Two Sum
 * https://leetcode.com/problems/two-sum/
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers
 * such that they add up to target.
 *
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 */
class TwoSumTest {

    private val solution = Solution()

    @Test
    fun `test twoSum - LeetCode Example 1`() {
        // Input: nums = [2,7,11,15], target = 9
        // Output: [0,1]
        // Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
        val nums = intArrayOf(2, 7, 11, 15)
        val target = 9
        val result = solution.twoSum(nums, target)

        assertNotNull(result, "Result should not be null")
        assertEquals(2, result.size, "Result should have 2 elements")
        assertTrue(result.contentEquals(intArrayOf(0, 1)), "Expected [0, 1]")
    }

    @Test
    fun `test twoSum - LeetCode Example 2`() {
        // Input: nums = [3,2,4], target = 6
        // Output: [1,2]
        val nums = intArrayOf(3, 2, 4)
        val target = 6
        val result = solution.twoSum(nums, target)

        assertNotNull(result, "Result should not be null")
        assertEquals(2, result.size, "Result should have 2 elements")
        assertTrue(result.contentEquals(intArrayOf(1, 2)), "Expected [1, 2]")
    }

    @Test
    fun `test twoSum - LeetCode Example 3`() {
        // Input: nums = [3,3], target = 6
        // Output: [0,1]
        val nums = intArrayOf(3, 3)
        val target = 6
        val result = solution.twoSum(nums, target)

        assertNotNull(result, "Result should not be null")
        assertEquals(2, result.size, "Result should have 2 elements")
        assertTrue(result.contentEquals(intArrayOf(0, 1)), "Expected [0, 1]")
    }

    @Test
    fun `test twoSum - negative numbers`() {
        // Input: nums = [-1,-2,-3,-4,-5], target = -8
        // Output: [2,4]
        // Explanation: nums[2] + nums[4] = -3 + -5 = -8
        val nums = intArrayOf(-1, -2, -3, -4, -5)
        val target = -8
        val result = solution.twoSum(nums, target)

        assertNotNull(result, "Result should not be null")
        assertEquals(2, result.size, "Result should have 2 elements")
        assertTrue(result.contentEquals(intArrayOf(2, 4)), "Expected [2, 4]")
    }

    @Test
    fun `test twoSum - mix of positive and negative`() {
        // Input: nums = [-3,4,3,90], target = 0
        // Output: [0,2]
        // Explanation: nums[0] + nums[2] = -3 + 3 = 0
        val nums = intArrayOf(-3, 4, 3, 90)
        val target = 0
        val result = solution.twoSum(nums, target)

        assertNotNull(result, "Result should not be null")
        assertEquals(2, result.size, "Result should have 2 elements")
        assertTrue(result.contentEquals(intArrayOf(0, 2)), "Expected [0, 2]")
    }

    @Test
    fun `test twoSum - large array`() {
        // Input: nums = [1,2,3,4,5,6,7,8,9,10], target = 19
        // Output: [8,9]
        // Explanation: nums[8] + nums[9] = 9 + 10 = 19
        val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val target = 19
        val result = solution.twoSum(nums, target)

        assertNotNull(result, "Result should not be null")
        assertEquals(2, result.size, "Result should have 2 elements")
        assertTrue(result.contentEquals(intArrayOf(8, 9)), "Expected [8, 9]")
    }

    @Test
    fun `test twoSum - zeros`() {
        // Input: nums = [0,4,3,0], target = 0
        // Output: [0,3]
        // Explanation: nums[0] + nums[3] = 0 + 0 = 0
        val nums = intArrayOf(0, 4, 3, 0)
        val target = 0
        val result = solution.twoSum(nums, target)

        assertNotNull(result, "Result should not be null")
        assertEquals(2, result.size, "Result should have 2 elements")
        assertTrue(result.contentEquals(intArrayOf(0, 3)), "Expected [0, 3]")
    }

    @Test
    fun `test twoSum - no solution exists`() {
        // Input: nums = [1,2,3], target = 10
        // Output: null
        // Explanation: No two numbers add up to 10
        val nums = intArrayOf(1, 2, 3)
        val target = 10
        val result = solution.twoSum(nums, target)

        assertNull(result, "Expected null when no solution exists")
    }

    @Test
    fun `test twoSum - first and last elements`() {
        // Input: nums = [5,1,2,3,10], target = 15
        // Output: [0,4]
        // Explanation: nums[0] + nums[4] = 5 + 10 = 15
        val nums = intArrayOf(5, 1, 2, 3, 10)
        val target = 15
        val result = solution.twoSum(nums, target)

        assertNotNull(result, "Result should not be null")
        assertEquals(2, result.size, "Result should have 2 elements")
        assertTrue(result.contentEquals(intArrayOf(0, 4)), "Expected [0, 4]")
    }

    @Test
    fun `test twoSum - duplicate values but different indices`() {
        // Input: nums = [2,5,5,11], target = 10
        // Output: [1,2]
        // Explanation: nums[1] + nums[2] = 5 + 5 = 10
        val nums = intArrayOf(2, 5, 5, 11)
        val target = 10
        val result = solution.twoSum(nums, target)

        assertNotNull(result, "Result should not be null")
        assertEquals(2, result.size, "Result should have 2 elements")
        assertTrue(result.contentEquals(intArrayOf(1, 2)), "Expected [1, 2]")
    }
}

