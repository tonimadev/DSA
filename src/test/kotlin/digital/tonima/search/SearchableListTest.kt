package digital.tonima.search

import digital.tonima.search.core.SearchableList
import digital.tonima.search.core.SearchAlgorithm
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SearchableListTest {

    @Test
    fun testSearchableListWithLinearSearch() {
        // Example using linear search (default)
        val list = SearchableList(listOf(5, 2, 8, 1, 9, 3))

        val result = list.find(8)
        assertEquals(2, result)
    }

    @Test
    fun testSearchableListWithBinarySearch() {
        // Example using binary search
        // For binary search, the list MUST be sorted
        val sortedList = SearchableList(listOf(1, 2, 3, 5, 8, 9))

        val result = sortedList.find(5, SearchAlgorithm.BINARY)
        assertEquals(3, result)
    }

    @Test
    fun testSearchableListNotFound() {
        val list = SearchableList(listOf(5, 2, 8, 1, 9, 3))

        val result = list.find(100)
        assertEquals(-1, result)
    }

    @Test
    fun testSearchableListWithDifferentTypes() {
        // Example with Strings
        val stringList = SearchableList(listOf("apple", "banana", "orange"))

        val result = stringList.find("banana")
        assertEquals(1, result)
    }
}

