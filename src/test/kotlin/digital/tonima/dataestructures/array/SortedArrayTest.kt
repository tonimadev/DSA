package digital.tonima.dataestructures.array

import digital.tonima.search.core.SearchFactory
import digital.tonima.search.core.SearchAlgorithm
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SortedArrayTest {
    private lateinit var sortedArray: SortedArray<Int>

    @BeforeEach
    fun setUp() {
        sortedArray = SortedArray(10)
    }

    @Test
    fun `test insert maintains sorted order`() {
        sortedArray.insert(5)
        sortedArray.insert(2)
        sortedArray.insert(8)
        sortedArray.insert(1)
        sortedArray.insert(9)

        assertEquals(1, sortedArray.get(0))
        assertEquals(2, sortedArray.get(1))
        assertEquals(5, sortedArray.get(2))
        assertEquals(8, sortedArray.get(3))
        assertEquals(9, sortedArray.get(4))
    }

    @Test
    fun `test insert at beginning`() {
        sortedArray.insert(5)
        sortedArray.insert(3)
        sortedArray.insert(1)

        assertEquals(1, sortedArray.get(0))
        assertEquals(3, sortedArray.get(1))
        assertEquals(5, sortedArray.get(2))
    }

    @Test
    fun `test insert at end`() {
        sortedArray.insert(1)
        sortedArray.insert(3)
        sortedArray.insert(5)

        assertEquals(1, sortedArray.get(0))
        assertEquals(3, sortedArray.get(1))
        assertEquals(5, sortedArray.get(2))
    }

    @Test
    fun `test insert in middle`() {
        sortedArray.insert(1)
        sortedArray.insert(5)
        sortedArray.insert(3)

        assertEquals(1, sortedArray.get(0))
        assertEquals(3, sortedArray.get(1))
        assertEquals(5, sortedArray.get(2))
    }

    @Test
    fun `test insert duplicate values`() {
        sortedArray.insert(5)
        sortedArray.insert(5)
        sortedArray.insert(5)

        assertEquals(5, sortedArray.get(0))
        assertEquals(5, sortedArray.get(1))
        assertEquals(5, sortedArray.get(2))
        assertEquals(3, sortedArray.size)
    }

    @Test
    fun `test insert throws when array is full`() {
        val smallArray = SortedArray<Int>(3)
        smallArray.insert(1)
        smallArray.insert(2)
        smallArray.insert(3)

        val exception = assertThrows<IllegalArgumentException> {
            smallArray.insert(4)
        }
        assertEquals("the array is already full", exception.message)
    }

    @Test
    fun `test delete removes element and maintains order`() {
        sortedArray.insert(1)
        sortedArray.insert(3)
        sortedArray.insert(5)
        sortedArray.insert(7)

        sortedArray.delete(3)

        assertEquals(3, sortedArray.size)
        assertEquals(1, sortedArray.get(0))
        assertEquals(5, sortedArray.get(1))
        assertEquals(7, sortedArray.get(2))
    }

    @Test
    fun `test delete first element`() {
        sortedArray.insert(1)
        sortedArray.insert(3)
        sortedArray.insert(5)

        sortedArray.delete(1)

        assertEquals(2, sortedArray.size)
        assertEquals(3, sortedArray.get(0))
        assertEquals(5, sortedArray.get(1))
    }

    @Test
    fun `test delete last element`() {
        sortedArray.insert(1)
        sortedArray.insert(3)
        sortedArray.insert(5)

        sortedArray.delete(5)

        assertEquals(2, sortedArray.size)
        assertEquals(1, sortedArray.get(0))
        assertEquals(3, sortedArray.get(1))
    }

    @Test
    fun `test delete throws when element not found`() {
        sortedArray.insert(1)
        sortedArray.insert(3)
        sortedArray.insert(5)

        val exception = assertThrows<NoSuchElementException> {
            sortedArray.delete(7)
        }
        assertEquals("value not found on the array", exception.message)
    }

    @Test
    fun `test deleteByIndex removes element at index`() {
        sortedArray.insert(1)
        sortedArray.insert(3)
        sortedArray.insert(5)
        sortedArray.insert(7)

        sortedArray.deleteByIndex(1)

        assertEquals(3, sortedArray.size)
        assertEquals(1, sortedArray.get(0))
        assertEquals(5, sortedArray.get(1))
        assertEquals(7, sortedArray.get(2))
    }

    @Test
    fun `test deleteByIndex first element`() {
        sortedArray.insert(1)
        sortedArray.insert(3)
        sortedArray.insert(5)

        sortedArray.deleteByIndex(0)

        assertEquals(2, sortedArray.size)
        assertEquals(3, sortedArray.get(0))
        assertEquals(5, sortedArray.get(1))
    }

    @Test
    fun `test deleteByIndex last element`() {
        sortedArray.insert(1)
        sortedArray.insert(3)
        sortedArray.insert(5)

        sortedArray.deleteByIndex(2)

        assertEquals(2, sortedArray.size)
        assertEquals(1, sortedArray.get(0))
        assertEquals(3, sortedArray.get(1))
    }

    @Test
    fun `test deleteByIndex throws on invalid index`() {
        sortedArray.insert(1)
        sortedArray.insert(3)

        assertThrows<IllegalArgumentException> {
            sortedArray.deleteByIndex(-1)
        }

        assertThrows<IllegalArgumentException> {
            sortedArray.deleteByIndex(2)
        }
    }


    @Test
    fun `test get returns correct element`() {
        sortedArray.insert(10)
        sortedArray.insert(20)
        sortedArray.insert(30)

        assertEquals(10, sortedArray.get(0))
        assertEquals(20, sortedArray.get(1))
        assertEquals(30, sortedArray.get(2))
    }

    @Test
    fun `test get throws on invalid index`() {
        sortedArray.insert(10)

        assertThrows<IllegalArgumentException> {
            sortedArray.get(-1)
        }

        assertThrows<IllegalArgumentException> {
            sortedArray.get(1)
        }
    }

    @Test
    fun `test size tracking`() {
        assertEquals(0, sortedArray.size)

        sortedArray.insert(1)
        assertEquals(1, sortedArray.size)

        sortedArray.insert(2)
        assertEquals(2, sortedArray.size)

        sortedArray.delete(1)
        assertEquals(1, sortedArray.size)

        sortedArray.deleteByIndex(0)
        assertEquals(0, sortedArray.size)
    }

    @Test
    fun `test toString with empty array`() {
        assertEquals("[]", sortedArray.toString())
    }

    @Test
    fun `test toString with elements`() {
        sortedArray.insert(1)
        sortedArray.insert(3)
        sortedArray.insert(2)

        assertEquals("[1, 2, 3]", sortedArray.toString())
    }

    @Test
    fun `test constructor throws on invalid maxSize`() {
        assertThrows<IllegalArgumentException> {
            SortedArray<Int>(0)
        }

        assertThrows<IllegalArgumentException> {
            SortedArray<Int>(-1)
        }
    }

    @Test
    fun `test with strings`() {
        val stringArray = SortedArray<String>(5)
        stringArray.insert("banana")
        stringArray.insert("apple")
        stringArray.insert("cherry")

        assertEquals("apple", stringArray.get(0))
        assertEquals("banana", stringArray.get(1))
        assertEquals("cherry", stringArray.get(2))
    }

    @Test
    fun `test complex sequence of operations`() {
        sortedArray.insert(5)
        sortedArray.insert(2)
        sortedArray.insert(8)
        sortedArray.insert(1)
        sortedArray.insert(9)
        sortedArray.insert(3)

        assertEquals(6, sortedArray.size)
        assertEquals("[1, 2, 3, 5, 8, 9]", sortedArray.toString())

        sortedArray.delete(3)
        assertEquals(5, sortedArray.size)
        assertEquals("[1, 2, 5, 8, 9]", sortedArray.toString())

        sortedArray.deleteByIndex(2)
        assertEquals(4, sortedArray.size)
        assertEquals("[1, 2, 8, 9]", sortedArray.toString())
    }

    @Test
    fun `test SortedArray with injected binary searcher`() {
        val binarySearcher = SearchFactory.create<Int>(SearchAlgorithm.BINARY)
        val customArray = SortedArray(10, binarySearcher)

        customArray.insert(5)
        customArray.insert(2)
        customArray.insert(8)

        assertEquals(3, customArray.size)
        assertEquals("[2, 5, 8]", customArray.toString())

        customArray.delete(5)
        assertEquals(2, customArray.size)
        assertEquals("[2, 8]", customArray.toString())
    }

    @Test
    fun `test SortedArray delete uses injected searcher correctly`() {
        val binarySearcher = SearchFactory.create<Int>(SearchAlgorithm.BINARY)
        val customArray = SortedArray(10, binarySearcher)

        customArray.insert(10)
        customArray.insert(20)
        customArray.insert(30)
        customArray.insert(40)
        customArray.insert(50)

        customArray.delete(30)
        assertEquals(4, customArray.size)
        assertEquals("[10, 20, 40, 50]", customArray.toString())

        customArray.delete(50)
        assertEquals(3, customArray.size)
        assertEquals("[10, 20, 40]", customArray.toString())
    }

    @Test
    fun `test SortedArray with strings and custom searcher`() {
        val binarySearcher = SearchFactory.create<String>(SearchAlgorithm.BINARY)
        val stringArray = SortedArray<String>(5, binarySearcher)

        stringArray.insert("banana")
        stringArray.insert("apple")
        stringArray.insert("cherry")

        assertEquals(3, stringArray.size)
        assertEquals("[apple, banana, cherry]", stringArray.toString())

        stringArray.delete("banana")
        assertEquals(2, stringArray.size)
        assertEquals("[apple, cherry]", stringArray.toString())
    }
}
