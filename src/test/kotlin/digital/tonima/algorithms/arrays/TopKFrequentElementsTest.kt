package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DisplayName("TopKFrequentElements Tests")
class TopKFrequentElementsTest {

    private val solution = TopKFrequentElementsSolution()

    @Test
    @DisplayName("topKFrequent - Single element array")
    fun testSingleElementArray() {
        val nums = intArrayOf(1)
        val k = 1

        val result = solution.topKFrequent(nums, k)

        assertEquals(1, result.size)
        assertTrue(result.contains(1))
    }

    @Test
    @DisplayName("topKFrequent - k equals number of unique elements")
    fun testKEqualsUniqueElements() {
        val nums = intArrayOf(1, 1, 1, 2, 2, 3)
        val k = 3

        val result = solution.topKFrequent(nums, k)

        assertEquals(3, result.size)
        assertTrue(result.contains(1))
        assertTrue(result.contains(2))
        assertTrue(result.contains(3))
    }

    @Test
    @DisplayName("topKFrequent - k equals 1")
    fun testKEqualsOne() {
        val nums = intArrayOf(4, 4, 4, 1, 1, 2)
        val k = 1

        val result = solution.topKFrequent(nums, k)

        assertEquals(1, result.size)
        assertTrue(result.contains(4))
    }

    @Test
    @DisplayName("topKFrequent - Large frequency differences")
    fun testLargeFrequencyDifferences() {
        val nums = intArrayOf(1, 1, 1, 1, 1, 2, 3)
        val k = 2

        val result = solution.topKFrequent(nums, k)

        assertEquals(2, result.size)
        assertTrue(result.contains(1))
    }

    @Test
    @DisplayName("topKFrequent - Array with duplicates at end")
    fun testArrayWithDuplicatesAtEnd() {
        val nums = intArrayOf(1, 2, 2, 3, 3, 3)
        val k = 1

        val result = solution.topKFrequent(nums, k)

        assertEquals(1, result.size)
        assertTrue(result.contains(3))
    }

    @Test
    @DisplayName("topKFrequent - Result should contain valid elements")
    fun testResultContainsValidElements() {
        val nums = intArrayOf(1, 1, 1, 2, 2, 3, 3, 3, 3)
        val k = 2

        val result = solution.topKFrequent(nums, k)

        for (element in result) {
            assertTrue(nums.contains(element), "Result contains element not in input array")
        }
    }
}
