package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class IsMonotonicTest {
    private val solution = IsMonotonicSolution()

    @Test
    fun `test monotonic increasing array`() {
        val nums = intArrayOf(1, 2, 2, 3)
        assertTrue(solution.isMonotonic(nums))
    }

    @Test
    fun `test monotonic decreasing array`() {
        val nums = intArrayOf(6, 5, 4, 4)
        assertTrue(solution.isMonotonic(nums))
    }

    @Test
    fun `test non-monotonic array`() {
        val nums = intArrayOf(1, 3, 2)
        assertFalse(solution.isMonotonic(nums))
    }

    @Test
    fun `test single element array`() {
        val nums = intArrayOf(1)
        assertTrue(solution.isMonotonic(nums))
    }

    @Test
    fun `test two elements increasing`() {
        val nums = intArrayOf(1, 2)
        assertTrue(solution.isMonotonic(nums))
    }

    @Test
    fun `test two elements decreasing`() {
        val nums = intArrayOf(2, 1)
        assertTrue(solution.isMonotonic(nums))
    }

    @Test
    fun `test all same elements`() {
        val nums = intArrayOf(5, 5, 5, 5)
        assertTrue(solution.isMonotonic(nums))
    }

    @Test
    fun `test strictly increasing`() {
        val nums = intArrayOf(1, 2, 3, 4, 5)
        assertTrue(solution.isMonotonic(nums))
    }

    @Test
    fun `test strictly decreasing`() {
        val nums = intArrayOf(5, 4, 3, 2, 1)
        assertTrue(solution.isMonotonic(nums))
    }

    @Test
    fun `test large monotonic increasing array`() {
        val nums = intArrayOf(1, 1, 1, 3, 3, 4, 5, 5, 5)
        assertTrue(solution.isMonotonic(nums))
    }

    @Test
    fun `test large monotonic decreasing array`() {
        val nums = intArrayOf(9, 9, 8, 7, 7, 6, 5, 5, 5)
        assertTrue(solution.isMonotonic(nums))
    }

    @Test
    fun `test non-monotonic with increase then decrease`() {
        val nums = intArrayOf(1, 2, 3, 2, 1)
        assertFalse(solution.isMonotonic(nums))
    }

    @Test
    fun `test non-monotonic with decrease then increase`() {
        val nums = intArrayOf(5, 4, 3, 4, 5)
        assertFalse(solution.isMonotonic(nums))
    }
}

