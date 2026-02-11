package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import kotlin.test.assertEquals

/**
 * Test cases for LeetCode #278 - First Bad Version
 * https://leetcode.com/problems/first-bad-version/
 */
class FirstBadVersionTest {

    @Test
    @DisplayName("firstBadVersion - LeetCode Example 1")
    fun testExample1() {
        // Input: n = 5, bad = 4
        // Output: 4
        // Explanation: call isBadVersion(3) -> false
        //              call isBadVersion(5) -> true
        //              call isBadVersion(4) -> true
        //              Then 4 is the first bad version.
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(4)
        assertEquals(4, solution.firstBadVersion(5))
    }

    @Test
    @DisplayName("firstBadVersion - LeetCode Example 2")
    fun testExample2() {
        // Input: n = 1, bad = 1
        // Output: 1
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(1)
        assertEquals(1, solution.firstBadVersion(1))
    }

    @Test
    @DisplayName("firstBadVersion - first version is bad")
    fun testFirstVersionBad() {
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(1)
        assertEquals(1, solution.firstBadVersion(100))
    }

    @Test
    @DisplayName("firstBadVersion - last version is bad")
    fun testLastVersionBad() {
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(100)
        assertEquals(100, solution.firstBadVersion(100))
    }

    @Test
    @DisplayName("firstBadVersion - middle version is bad")
    fun testMiddleVersionBad() {
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(50)
        assertEquals(50, solution.firstBadVersion(100))
    }

    @Test
    @DisplayName("firstBadVersion - n = 2, bad = 1")
    fun testN2Bad1() {
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(1)
        assertEquals(1, solution.firstBadVersion(2))
    }

    @Test
    @DisplayName("firstBadVersion - n = 2, bad = 2")
    fun testN2Bad2() {
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(2)
        assertEquals(2, solution.firstBadVersion(2))
    }

    @Test
    @DisplayName("firstBadVersion - large range with bad at beginning")
    fun testLargeRangeBadAtBeginning() {
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(1)
        assertEquals(1, solution.firstBadVersion(10000))
    }

    @Test
    @DisplayName("firstBadVersion - large range with bad at end")
    fun testLargeRangeBadAtEnd() {
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(10000)
        assertEquals(10000, solution.firstBadVersion(10000))
    }

    @Test
    @DisplayName("firstBadVersion - large range with bad in middle")
    fun testLargeRangeBadInMiddle() {
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(5000)
        assertEquals(5000, solution.firstBadVersion(10000))
    }

    @Test
    @DisplayName("firstBadVersion - various positions in small range")
    fun testVariousPositionsSmallRange() {
        val testCases = mapOf(
            3 to 10,
            7 to 10,
            5 to 10,
            9 to 10,
            1 to 10
        )

        for ((bad, n) in testCases) {
            val solution = FirstBadVersionSolution()
            solution.setFirstBadVersion(bad)
            assertEquals(bad, solution.firstBadVersion(n))
        }
    }

    @Test
    @DisplayName("firstBadVersion - various positions in large range")
    fun testVariousPositionsLargeRange() {
        val testCases = mapOf(
            250 to 1000,
            750 to 1000,
            500 to 1000,
            999 to 1000,
            1 to 1000
        )

        for ((bad, n) in testCases) {
            val solution = FirstBadVersionSolution()
            solution.setFirstBadVersion(bad)
            assertEquals(bad, solution.firstBadVersion(n))
        }
    }

    @Test
    @DisplayName("firstBadVersion - bad version near beginning")
    fun testBadVersionNearBeginning() {
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(3)
        assertEquals(3, solution.firstBadVersion(100))
    }

    @Test
    @DisplayName("firstBadVersion - bad version near end")
    fun testBadVersionNearEnd() {
        val solution = FirstBadVersionSolution()
        solution.setFirstBadVersion(98)
        assertEquals(98, solution.firstBadVersion(100))
    }
}

