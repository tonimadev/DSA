package digital.tonima.search

import digital.tonima.search.core.SearchFactory
import digital.tonima.search.core.SearchAlgorithm
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import kotlin.test.assertEquals

@DisplayName("InterpolationSearch Tests")
class InterpolationSearchTest {

    @Test
    @DisplayName("Find element in sorted list - basic test")
    fun testFindExistingElement() {
        val searcher = SearchFactory.create<Int>(SearchAlgorithm.INTERPOLATION)
        val sortedList = listOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19)

        assertEquals(0, searcher.search(sortedList, 1))
        assertEquals(2, searcher.search(sortedList, 5))
        assertEquals(4, searcher.search(sortedList, 9))
        assertEquals(9, searcher.search(sortedList, 19))
    }

    @Test
    @DisplayName("Return -1 for non-existing elements")
    fun testNonExistingElement() {
        val searcher = SearchFactory.create<Int>(SearchAlgorithm.INTERPOLATION)
        val sortedList = listOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19)

        assertEquals(-1, searcher.search(sortedList, 0))
        assertEquals(-1, searcher.search(sortedList, 2))
        assertEquals(-1, searcher.search(sortedList, 10))
        assertEquals(-1, searcher.search(sortedList, 20))
    }

    @Test
    @DisplayName("Handle empty list")
    fun testEmptyList() {
        val searcher = SearchFactory.create<Int>(SearchAlgorithm.INTERPOLATION)
        val emptyList: List<Int> = emptyList()

        assertEquals(-1, searcher.search(emptyList, 5))
    }

    @Test
    @DisplayName("Handle single element list")
    fun testSingleElement() {
        val searcher = SearchFactory.create<Int>(SearchAlgorithm.INTERPOLATION)
        val list = listOf(5)

        assertEquals(0, searcher.search(list, 5))
        assertEquals(-1, searcher.search(list, 3))
        assertEquals(-1, searcher.search(list, 7))
    }

    @Test
    @DisplayName("Handle two element list")
    fun testTwoElements() {
        val searcher = SearchFactory.create<Int>(SearchAlgorithm.INTERPOLATION)
        val list = listOf(3, 7)

        assertEquals(0, searcher.search(list, 3))
        assertEquals(1, searcher.search(list, 7))
        assertEquals(-1, searcher.search(list, 5))
    }

    @Test
    @DisplayName("Searcher returns correct name")
    fun testSearcherName() {
        val searcher = SearchFactory.create<Int>(SearchAlgorithm.INTERPOLATION)
        assertEquals("Interpolation Search", searcher.name())
    }

    @Test
    @DisplayName("Work with strings")
    fun testWithStrings() {
        val searcher = SearchFactory.create<String>(SearchAlgorithm.INTERPOLATION)
        val sortedList = listOf("apple", "banana", "cherry", "date", "elderberry")

        assertEquals(0, searcher.search(sortedList, "apple"))
        assertEquals(2, searcher.search(sortedList, "cherry"))
        assertEquals(4, searcher.search(sortedList, "elderberry"))
        assertEquals(-1, searcher.search(sortedList, "fig"))
    }

    @Test
    @DisplayName("Handle duplicates")
    fun testDuplicates() {
        val searcher = SearchFactory.create<Int>(SearchAlgorithm.INTERPOLATION)
        val sortedList = listOf(1, 3, 5, 5, 5, 7, 9)

        // Should find at least one occurrence of the duplicated value
        val result = searcher.search(sortedList, 5)
        assertEquals(true, result >= 0)
    }

    @Test
    @DisplayName("Handle large uniformly distributed dataset")
    fun testLargeUniformDataset() {
        val searcher = SearchFactory.create<Int>(SearchAlgorithm.INTERPOLATION)
        val sortedList = (0..1000).toList()

        assertEquals(0, searcher.search(sortedList, 0))
        assertEquals(500, searcher.search(sortedList, 500))
        assertEquals(1000, searcher.search(sortedList, 1000))
        assertEquals(-1, searcher.search(sortedList, 1001))
    }

    @Test
    @DisplayName("Handle negative numbers")
    fun testNegativeNumbers() {
        val searcher = SearchFactory.create<Int>(SearchAlgorithm.INTERPOLATION)
        val sortedList = listOf(-10, -5, 0, 5, 10)

        assertEquals(0, searcher.search(sortedList, -10))
        assertEquals(2, searcher.search(sortedList, 0))
        assertEquals(4, searcher.search(sortedList, 10))
        assertEquals(-1, searcher.search(sortedList, 3))
    }

    @Test
    @DisplayName("Compare with binary search results")
    fun testConsistencyWithBinarySearch() {
        val interpolationSearcher = SearchFactory.create<Int>(SearchAlgorithm.INTERPOLATION)
        val binarySearcher = SearchFactory.create<Int>(SearchAlgorithm.BINARY)
        val sortedList = listOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19)

        for (value in sortedList) {
            val interpolResult = interpolationSearcher.search(sortedList, value)
            val binaryResult = binarySearcher.search(sortedList, value)
            assertEquals(interpolResult, binaryResult, "Results should match for value $value")
        }

        // Test non-existing values
        val nonExisting = listOf(0, 2, 4, 6, 8, 20)
        for (value in nonExisting) {
            val interpolResult = interpolationSearcher.search(sortedList, value)
            val binaryResult = binarySearcher.search(sortedList, value)
            assertEquals(interpolResult, binaryResult, "Results should both be -1 for value $value")
        }
    }
}
