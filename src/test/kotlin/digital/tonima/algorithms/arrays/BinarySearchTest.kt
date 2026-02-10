package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Test cases for LeetCode #704 - Binary Search
 * https://leetcode.com/problems/binary-search/
 *
 * Given an array of integers nums which is sorted in ascending order, and an integer target,
 * write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 */
class BinarySearchTest {

    private val solution = BinarySearchSolution()

    @Test
    fun `test binarySearch - LeetCode Example 1`() {
        // Input: nums = [-1,0,3,5,9,12], target = 9
        // Output: 4
        val nums = intArrayOf(-1, 0, 3, 5, 9, 12)
        val target = 9

        val result = solution.search(nums, target)

        assertEquals(4, result)
    }

    @Test
    fun `test binarySearch - LeetCode Example 2`() {
        // Input: nums = [-1,0,3,5,9,12], target = 2
        // Output: -1
        val nums = intArrayOf(-1, 0, 3, 5, 9, 12)
        val target = 2

        val result = solution.search(nums, target)

        assertEquals(-1, result)
    }

    @Test
    fun `test binarySearch - target at first index`() {
        val nums = intArrayOf(1, 3, 5, 7, 9)
        val target = 1

        val result = solution.search(nums, target)

        assertEquals(0, result)
    }

    @Test
    fun `test binarySearch - target at last index`() {
        val nums = intArrayOf(1, 3, 5, 7, 9)
        val target = 9

        val result = solution.search(nums, target)

        assertEquals(4, result)
    }

    @Test
    fun `test binarySearch - single element found`() {
        val nums = intArrayOf(8)
        val target = 8

        val result = solution.search(nums, target)

        assertEquals(0, result)
    }

    @Test
    fun `test binarySearch - single element not found`() {
        val nums = intArrayOf(8)
        val target = 3

        val result = solution.search(nums, target)

        assertEquals(-1, result)
    }

    @Test
    fun `test binarySearch - empty array`() {
        val nums = intArrayOf()
        val target = 10

        val result = solution.search(nums, target)

        assertEquals(-1, result)
    }
}

