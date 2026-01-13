package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Test cases for LeetCode #217 - Contains Duplicate
 * https://leetcode.com/problems/contains-duplicate/
 *
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 */
class HasDuplicatesTest {

    private val solution = HasDuplicatesSolution()

    @Test
    fun `test hasDuplicate - LeetCode Example 1`() {
        // Input: nums = [1,2,3,1]
        // Output: true
        // Explanation: 1 appears twice
        val nums = intArrayOf(1, 2, 3, 1)
        val result = solution.hasDuplicate(nums)

        assertTrue(result, "Should return true when array contains duplicates")
    }

    @Test
    fun `test hasDuplicate - LeetCode Example 2`() {
        // Input: nums = [1,2,3,4]
        // Output: false
        // Explanation: All elements are distinct
        val nums = intArrayOf(1, 2, 3, 4)
        val result = solution.hasDuplicate(nums)

        assertFalse(result, "Should return false when all elements are distinct")
    }

    @Test
    fun `test hasDuplicate - LeetCode Example 3`() {
        // Input: nums = [1,1,1,3,3,4,3,2,4,2]
        // Output: true
        // Explanation: Multiple elements appear more than once
        val nums = intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)
        val result = solution.hasDuplicate(nums)

        assertTrue(result, "Should return true when array contains multiple duplicates")
    }

    @Test
    fun `test hasDuplicate - single element array`() {
        // Input: nums = [1]
        // Output: false
        // Explanation: Cannot have duplicates with only one element
        val nums = intArrayOf(1)
        val result = solution.hasDuplicate(nums)

        assertFalse(result, "Should return false for single element array")
    }

    @Test
    fun `test hasDuplicate - two identical elements`() {
        // Input: nums = [5, 5]
        // Output: true
        // Explanation: Both elements are the same
        val nums = intArrayOf(5, 5)
        val result = solution.hasDuplicate(nums)

        assertTrue(result, "Should return true when two elements are the same")
    }

    @Test
    fun `test hasDuplicate - two different elements`() {
        // Input: nums = [1, 2]
        // Output: false
        // Explanation: All elements are distinct
        val nums = intArrayOf(1, 2)
        val result = solution.hasDuplicate(nums)

        assertFalse(result, "Should return false when two elements are different")
    }

    @Test
    fun `test hasDuplicate - negative numbers with duplicates`() {
        // Input: nums = [-1, -2, -3, -1]
        // Output: true
        // Explanation: -1 appears twice
        val nums = intArrayOf(-1, -2, -3, -1)
        val result = solution.hasDuplicate(nums)

        assertTrue(result, "Should return true when negative numbers are duplicated")
    }

    @Test
    fun `test hasDuplicate - negative numbers without duplicates`() {
        // Input: nums = [-1, -2, -3, -4]
        // Output: false
        // Explanation: All elements are distinct
        val nums = intArrayOf(-1, -2, -3, -4)
        val result = solution.hasDuplicate(nums)

        assertFalse(result, "Should return false when all negative numbers are distinct")
    }

    @Test
    fun `test hasDuplicate - mix of positive and negative numbers with duplicates`() {
        // Input: nums = [1, -1, 2, -2, 1]
        // Output: true
        // Explanation: 1 appears twice
        val nums = intArrayOf(1, -1, 2, -2, 1)
        val result = solution.hasDuplicate(nums)

        assertTrue(result, "Should return true when mixed numbers contain duplicates")
    }

    @Test
    fun `test hasDuplicate - large array with duplicate at end`() {
        // Input: nums = [1,2,3,4,5,6,7,8,9,10,1]
        // Output: true
        // Explanation: 1 appears at start and end
        val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1)
        val result = solution.hasDuplicate(nums)

        assertTrue(result, "Should return true when duplicate is at the end")
    }

    @Test
    fun `test hasDuplicate - array with zeros`() {
        // Input: nums = [0, 1, 2, 0]
        // Output: true
        // Explanation: 0 appears twice
        val nums = intArrayOf(0, 1, 2, 0)
        val result = solution.hasDuplicate(nums)

        assertTrue(result, "Should return true when zero is duplicated")
    }

    @Test
    fun `test hasDuplicate - consecutive duplicates`() {
        // Input: nums = [1, 2, 2, 3]
        // Output: true
        // Explanation: 2 appears consecutively
        val nums = intArrayOf(1, 2, 2, 3)
        val result = solution.hasDuplicate(nums)

        assertTrue(result, "Should return true when duplicates are consecutive")
    }

    @Test
    fun `test hasDuplicate - large array without duplicates`() {
        // Input: nums = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
        // Output: false
        // Explanation: All elements are distinct
        val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        val result = solution.hasDuplicate(nums)

        assertFalse(result, "Should return false for large array without duplicates")
    }
}

