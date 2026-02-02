package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Majority Element Tests - Boyer-Moore Voting Algorithm")
class MajorityElementTest {

    private val solution = MajorityElementSolution()

    @Test
    @DisplayName("Example 1: [3,2,3] -> 3")
    fun testExample1() {
        val nums = intArrayOf(3, 2, 3)
        val result = solution.majorityElement(nums)
        assertEquals(3, result)
    }

    @Test
    @DisplayName("Example 2: [2,2,1,1,1,2,2] -> 2")
    fun testExample2() {
        val nums = intArrayOf(2, 2, 1, 1, 1, 2, 2)
        val result = solution.majorityElement(nums)
        assertEquals(2, result)
    }

    @Test
    @DisplayName("Single element array -> that element")
    fun testSingleElement() {
        val nums = intArrayOf(1)
        val result = solution.majorityElement(nums)
        assertEquals(1, result)
    }

    @Test
    @DisplayName("Two elements, same -> that element")
    fun testTwoSameElements() {
        val nums = intArrayOf(5, 5)
        val result = solution.majorityElement(nums)
        assertEquals(5, result)
    }

    @Test
    @DisplayName("All same elements -> that element")
    fun testAllSameElements() {
        val nums = intArrayOf(7, 7, 7, 7, 7)
        val result = solution.majorityElement(nums)
        assertEquals(7, result)
    }

    @Test
    @DisplayName("Majority at beginning: [4,4,4,1,2] -> 4")
    fun testMajorityAtBeginning() {
        val nums = intArrayOf(4, 4, 4, 1, 2)
        val result = solution.majorityElement(nums)
        assertEquals(4, result)
    }

    @Test
    @DisplayName("Majority at end: [1,2,4,4,4] -> 4")
    fun testMajorityAtEnd() {
        val nums = intArrayOf(1, 2, 4, 4, 4)
        val result = solution.majorityElement(nums)
        assertEquals(4, result)
    }

    @Test
    @DisplayName("Majority scattered: [1,3,1,3,1,3,1] -> 1")
    fun testMajorityScattered() {
        val nums = intArrayOf(1, 3, 1, 3, 1, 3, 1)
        val result = solution.majorityElement(nums)
        assertEquals(1, result)
    }

    @Test
    @DisplayName("Large array with clear majority")
    fun testLargeArrayClearMajority() {
        val nums = intArrayOf(1, 2, 3, 1, 1, 1, 1, 2, 3, 1, 1)
        val result = solution.majorityElement(nums)
        assertEquals(1, result)
    }

    @Test
    @DisplayName("Negative numbers: [-1,-1,2] -> -1")
    fun testNegativeNumbers() {
        val nums = intArrayOf(-1, -1, 2)
        val result = solution.majorityElement(nums)
        assertEquals(-1, result)
    }

    @Test
    @DisplayName("Mixed positive and negative: [1,-1,1,-1,1] -> 1")
    fun testMixedPositiveNegative() {
        val nums = intArrayOf(1, -1, 1, -1, 1)
        val result = solution.majorityElement(nums)
        assertEquals(1, result)
    }

    @Test
    @DisplayName("Zero as majority: [0,0,1,0] -> 0")
    fun testZeroAsMajority() {
        val nums = intArrayOf(0, 0, 1, 0)
        val result = solution.majorityElement(nums)
        assertEquals(0, result)
    }

    @Test
    @DisplayName("Majority exactly n/2 + 1: [1,1,2,2,1] -> 1")
    fun testExactMajority() {
        val nums = intArrayOf(1, 1, 2, 2, 1)
        val result = solution.majorityElement(nums)
        assertEquals(1, result)
    }

    @Test
    @DisplayName("Large numbers: [1000000,1000000,1,1000000] -> 1000000")
    fun testLargeNumbers() {
        val nums = intArrayOf(1000000, 1000000, 1, 1000000)
        val result = solution.majorityElement(nums)
        assertEquals(1000000, result)
    }

    @Test
    @DisplayName("Alternating pattern ending with majority: [1,2,1,2,1,2,1] -> 1")
    fun testAlternatingPattern() {
        val nums = intArrayOf(1, 2, 1, 2, 1, 2, 1)
        val result = solution.majorityElement(nums)
        assertEquals(1, result)
    }

    @Test
    @DisplayName("Three elements with clear majority: [5,5,3] -> 5")
    fun testThreeElements() {
        val nums = intArrayOf(5, 5, 3)
        val result = solution.majorityElement(nums)
        assertEquals(5, result)
    }

    @Test
    @DisplayName("Complex pattern: [1,2,3,1,1,4,1,1] -> 1")
    fun testComplexPattern() {
        val nums = intArrayOf(1, 2, 3, 1, 1, 4, 1, 1)
        val result = solution.majorityElement(nums)
        assertEquals(1, result)
    }

    @Test
    @DisplayName("Majority appears consecutively: [1,1,1,1,2,3,4] -> 1")
    fun testConsecutiveMajority() {
        val nums = intArrayOf(1, 1, 1, 1, 2, 3, 4)
        val result = solution.majorityElement(nums)
        assertEquals(1, result)
    }

    @Test
    @DisplayName("Even length array: [1,1,2,1] -> 1")
    fun testEvenLengthArray() {
        val nums = intArrayOf(1, 1, 2, 1)
        val result = solution.majorityElement(nums)
        assertEquals(1, result)
    }

    @Test
    @DisplayName("Odd length array: [3,3,4,3,2] -> 3")
    fun testOddLengthArray() {
        val nums = intArrayOf(3, 3, 4, 3, 2)
        val result = solution.majorityElement(nums)
        assertEquals(3, result)
    }

    @Test
    @DisplayName("Bug fix test: [6,5,5] -> 5")
    fun testBugFix() {
        val nums = intArrayOf(6, 5, 5)
        val result = solution.majorityElement(nums)
        assertEquals(5, result, "Should return 5 as it appears 2 times (more than n/2 = 1.5)")
    }
}
