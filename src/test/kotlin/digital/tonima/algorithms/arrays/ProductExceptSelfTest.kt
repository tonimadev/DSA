package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Test cases for LeetCode #238 - Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 */
class ProductExceptSelfTest {

    private val solution = ProductExceptSelfSolution()

    @Test
    fun `test productExceptSelf - LeetCode Example 1`() {
        // Input: nums = [1,2,3,4]
        // Output: [24,12,8,6]
        // Explanation: answer[0] = 2*3*4 = 24, answer[1] = 1*3*4 = 12, answer[2] = 1*2*4 = 8, answer[3] = 1*2*3 = 6
        val nums = intArrayOf(1, 2, 3, 4)
        val result = solution.productExceptSelf(nums)

        assertTrue(result.contentEquals(intArrayOf(24, 12, 8, 6)), "Expected [24, 12, 8, 6]")
    }

    @Test
    fun `test productExceptSelf - LeetCode Example 2`() {
        // Input: nums = [-1,1,0,-3,3]
        // Output: [0,0,9,0,0]
        val nums = intArrayOf(-1, 1, 0, -3, 3)
        val result = solution.productExceptSelf(nums)

        assertTrue(result.contentEquals(intArrayOf(0, 0, 9, 0, 0)), "Expected [0, 0, 9, 0, 0]")
    }

    @Test
    fun `test productExceptSelf - Array with two elements`() {
        // Input: nums = [2, 3]
        // Output: [3, 2]
        val nums = intArrayOf(2, 3)
        val result = solution.productExceptSelf(nums)

        assertTrue(result.contentEquals(intArrayOf(3, 2)), "Expected [3, 2]")
    }

    @Test
    fun `test productExceptSelf - Array with single zero`() {
        // Input: nums = [0, 4, 9]
        // Output: [36, 0, 0]
        val nums = intArrayOf(0, 4, 9)
        val result = solution.productExceptSelf(nums)

        assertTrue(result.contentEquals(intArrayOf(36, 0, 0)), "Expected [36, 0, 0]")
    }

    @Test
    fun `test productExceptSelf - All positive numbers`() {
        // Input: nums = [1, 1, 1, 1]
        // Output: [1, 1, 1, 1]
        val nums = intArrayOf(1, 1, 1, 1)
        val result = solution.productExceptSelf(nums)

        assertTrue(result.contentEquals(intArrayOf(1, 1, 1, 1)), "Expected [1, 1, 1, 1]")
    }

    @Test
    fun `test productExceptSelf - Mix of positive and negative`() {
        // Input: nums = [2, -3, 4, 5]
        // answer[0] = -3*4*5 = -60
        // answer[1] = 2*4*5 = 40
        // answer[2] = 2*-3*5 = -30
        // answer[3] = 2*-3*4 = -24
        val nums = intArrayOf(2, -3, 4, 5)
        val result = solution.productExceptSelf(nums)

        assertEquals(4, result.size, "Result size should match input size")
        assertEquals(-60, result[0], "answer[0] should be -3*4*5 = -60")
        assertEquals(40, result[1], "answer[1] should be 2*4*5 = 40")
        assertEquals(-30, result[2], "answer[2] should be 2*-3*5 = -30")
        assertEquals(-24, result[3], "answer[3] should be 2*-3*4 = -24")
    }

    @Test
    fun `test productExceptSelf - Large numbers`() {
        // Input: nums = [100, 200]
        // Output: [200, 100]
        val nums = intArrayOf(100, 200)
        val result = solution.productExceptSelf(nums)

        assertTrue(result.contentEquals(intArrayOf(200, 100)), "Expected [200, 100]")
    }

    @Test
    fun `test productExceptSelf - Result array is correct size`() {
        // Input: nums = [1, 2, 3, 4, 5]
        // Output array should have same size as input
        val nums = intArrayOf(1, 2, 3, 4, 5)
        val result = solution.productExceptSelf(nums)

        assertEquals(nums.size, result.size, "Result array should have same size as input array")
    }

    @Test
    fun `test productExceptSelf - Array with duplicate values`() {
        // Input: nums = [5, 5]
        // answer[0] = 5 (element at index 1)
        // answer[1] = 5 (element at index 0)
        val nums = intArrayOf(5, 5)
        val result = solution.productExceptSelf(nums)

        assertEquals(2, result.size, "Result size should be 2")
        assertEquals(5, result[0], "answer[0] should be 5")
        assertEquals(5, result[1], "answer[1] should be 5")
    }

    @Test
    fun `test productExceptSelf - Array with multiple duplicate values`() {
        // Input: nums = [2, 2, 3]
        // answer[0] = 2*3 = 6
        // answer[1] = 2*3 = 6
        // answer[2] = 2*2 = 4
        val nums = intArrayOf(2, 2, 3)
        val result = solution.productExceptSelf(nums)

        assertEquals(3, result.size, "Result size should be 3")
        assertEquals(6, result[0], "answer[0] should be 2*3 = 6")
        assertEquals(6, result[1], "answer[1] should be 2*3 = 6")
        assertEquals(4, result[2], "answer[2] should be 2*2 = 4")
    }

}
