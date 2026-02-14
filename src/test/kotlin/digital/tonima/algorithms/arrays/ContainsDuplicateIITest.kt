package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Test cases for LeetCode #219 - Contains Duplicate II
 * https://leetcode.com/problems/contains-duplicate-ii/
 */
class ContainsDuplicateIITest {

    private val solution = ContainsDuplicateIISolution()

    @Test
    fun `test containsNearbyDuplicate - LeetCode example 1`() {
        // Input: nums = [1,2,3,1], k = 3
        // Output: true
        val nums = intArrayOf(1, 2, 3, 1)
        assertTrue(solution.containsNearbyDuplicate(nums, 3))
    }

    @Test
    fun `test containsNearbyDuplicate - LeetCode example 2`() {
        // Input: nums = [1,0,1,1], k = 1
        // Output: true
        val nums = intArrayOf(1, 0, 1, 1)
        assertTrue(solution.containsNearbyDuplicate(nums, 1))
    }

    @Test
    fun `test containsNearbyDuplicate - LeetCode example 3`() {
        // Input: nums = [1,2,3,1,2,3], k = 2
        // Output: false
        val nums = intArrayOf(1, 2, 3, 1, 2, 3)
        assertFalse(solution.containsNearbyDuplicate(nums, 2))
    }

    @Test
    fun `test containsNearbyDuplicate - duplicates outside k`() {
        // Input: nums = [1,2,3,1], k = 2
        // Output: false
        val nums = intArrayOf(1, 2, 3, 1)
        assertFalse(solution.containsNearbyDuplicate(nums, 2))
    }

    @Test
    fun `test containsNearbyDuplicate - adjacent duplicates`() {
        // Input: nums = [5,5], k = 1
        // Output: true
        val nums = intArrayOf(5, 5)
        assertTrue(solution.containsNearbyDuplicate(nums, 1))
    }

    @Test
    fun `test containsNearbyDuplicate - k is zero`() {
        // Input: nums = [1,1], k = 0
        // Output: false
        val nums = intArrayOf(1, 1)
        assertFalse(solution.containsNearbyDuplicate(nums, 0))
    }

    @Test
    fun `test containsNearbyDuplicate - single element`() {
        // Input: nums = [7], k = 3
        // Output: false
        val nums = intArrayOf(7)
        assertFalse(solution.containsNearbyDuplicate(nums, 3))
    }
}

