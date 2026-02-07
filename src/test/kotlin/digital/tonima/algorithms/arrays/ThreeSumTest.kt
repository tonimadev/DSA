package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Test cases for LeetCode #15 - Three Sum
 * https://leetcode.com/problems/3sum/
 *
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 */
class ThreeSumTest {

    private val solution = ThreeSumSolution()

    @Test
    fun `threeSum - LeetCode example 1`() {
        // Input: nums = [-1,0,1,2,-1,-4]
        // Output: [[-1,-1,2],[-1,0,1]]
        val nums = intArrayOf(-1, 0, 1, 2, -1, -4)
        val result = solution.threeSum(nums)

        assertEquals(2, result.size)
        assertTrue(result.contains(listOf(-1, -1, 2)))
        assertTrue(result.contains(listOf(-1, 0, 1)))
    }

    @Test
    fun `threeSum - LeetCode example 2`() {
        // Input: nums = [0,1,1]
        // Output: []
        val nums = intArrayOf(0, 1, 1)
        val result = solution.threeSum(nums)

        assertEquals(0, result.size)
    }

    @Test
    fun `threeSum - LeetCode example 3`() {
        // Input: nums = [0,0,0]
        // Output: [[0,0,0]]
        val nums = intArrayOf(0, 0, 0)
        val result = solution.threeSum(nums)

        assertEquals(1, result.size)
        assertTrue(result.contains(listOf(0, 0, 0)))
    }

    @Test
    fun `threeSum - empty array`() {
        val nums = intArrayOf()
        val result = solution.threeSum(nums)

        assertEquals(0, result.size)
    }

    @Test
    fun `threeSum - array with less than 3 elements`() {
        val nums = intArrayOf(1, 2)
        val result = solution.threeSum(nums)

        assertEquals(0, result.size)
    }

    @Test
    fun `threeSum - no valid triplets`() {
        val nums = intArrayOf(1, 2, 3, 4, 5)
        val result = solution.threeSum(nums)

        assertEquals(0, result.size)
    }

    @Test
    fun `threeSum - single triplet with all different elements`() {
        val nums = intArrayOf(-1, 0, 1)
        val result = solution.threeSum(nums)

        assertEquals(1, result.size)
        assertTrue(result.contains(listOf(-1, 0, 1)))
    }

    @Test
    fun `threeSum - multiple triplets`() {
        val nums = intArrayOf(-2, 0, 1, 1, 2)
        val result = solution.threeSum(nums)

        assertEquals(2, result.size)
        assertTrue(result.contains(listOf(-2, 0, 2)))
        assertTrue(result.contains(listOf(-2, 1, 1)))
    }

    @Test
    fun `threeSum - duplicates should be avoided`() {
        val nums = intArrayOf(-1, 0, 1, 0, 1, -1)
        val result = solution.threeSum(nums)

        // Should have only one unique triplet [-1, 0, 1]
        assertEquals(1, result.size)
        assertTrue(result.contains(listOf(-1, 0, 1)))
    }

    @Test
    fun `threeSum - all negative numbers`() {
        val nums = intArrayOf(-5, -4, -3, -2, -1)
        val result = solution.threeSum(nums)

        assertEquals(0, result.size)
    }

    @Test
    fun `threeSum - all positive numbers`() {
        val nums = intArrayOf(1, 2, 3, 4, 5)
        val result = solution.threeSum(nums)

        assertEquals(0, result.size)
    }

    @Test
    fun `threeSum - mix of positive and negative with multiple solutions`() {
        val nums = intArrayOf(-4, -1, -1, 0, 1, 2)
        val result = solution.threeSum(nums)

        assertEquals(2, result.size)
        assertTrue(result.contains(listOf(-1, -1, 2)))
        assertTrue(result.contains(listOf(-1, 0, 1)))
    }

    @Test
    fun `threeSum - larger array with multiple solutions`() {
        val nums = intArrayOf(-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4)
        val result = solution.threeSum(nums)

        // Expected triplets include:
        // [-4, 0, 4], [-4, 1, 3], [-3, -1, 4], [-3, 0, 3], [-3, 1, 2]
        // [-2, -1, 3], [-2, 0, 2], [-1, -1, 2], [-1, 0, 1]
        assertTrue(result.size >= 6) // At least several solutions
        assertTrue(result.contains(listOf(-1, -1, 2)))
        assertTrue(result.contains(listOf(-1, 0, 1)))
    }

    @Test
    fun `threeSum - array with zeros`() {
        val nums = intArrayOf(0, 0, 0, 0)
        val result = solution.threeSum(nums)

        assertEquals(1, result.size)
        assertTrue(result.contains(listOf(0, 0, 0)))
    }
}

