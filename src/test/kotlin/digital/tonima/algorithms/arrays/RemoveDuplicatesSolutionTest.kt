package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RemoveDuplicatesSolutionTest {

    private val solution = RemoveDuplicatesSolution()

    @Test
    fun `test with duplicates`() {
        val nums = intArrayOf(1, 1, 2)
        val expectedLength = 2
        val result = solution.removeDuplicates(nums)
        assertEquals(expectedLength, result)
        val expectedNums = intArrayOf(1, 2)
        for (i in 0 until expectedLength) {
            assertEquals(expectedNums[i], nums[i])
        }
    }

    @Test
    fun `test with no duplicates`() {
        val nums = intArrayOf(1, 2, 3, 4, 5)
        val expectedLength = 5
        val result = solution.removeDuplicates(nums)
        assertEquals(expectedLength, result)
        val expectedNums = intArrayOf(1, 2, 3, 4, 5)
        for (i in 0 until expectedLength) {
            assertEquals(expectedNums[i], nums[i])
        }
    }

    @Test
    fun `test with empty array`() {
        val nums = intArrayOf()
        val expectedLength = 0
        val result = solution.removeDuplicates(nums)
        assertEquals(expectedLength, result)
    }

    @Test
    fun `test with all elements same`() {
        val nums = intArrayOf(1, 1, 1, 1, 1)
        val expectedLength = 1
        val result = solution.removeDuplicates(nums)
        assertEquals(expectedLength, result)
        assertEquals(1, nums[0])
    }

    @Test
    fun `test with duplicates at the end`() {
        val nums = intArrayOf(1, 2, 3, 3, 3)
        val expectedLength = 3
        val result = solution.removeDuplicates(nums)
        assertEquals(expectedLength, result)
        val expectedNums = intArrayOf(1, 2, 3)
        for (i in 0 until expectedLength) {
            assertEquals(expectedNums[i], nums[i])
        }
    }

    @Test
    fun `test with more complex array`() {
        val nums = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
        val expectedLength = 5
        val result = solution.removeDuplicates(nums)
        assertEquals(expectedLength, result)
        val expectedNums = intArrayOf(0, 1, 2, 3, 4)
        for (i in 0 until expectedLength) {
            assertEquals(expectedNums[i], nums[i])
        }
    }
}

