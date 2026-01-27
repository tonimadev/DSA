package digital.tonima.dataestructures.array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UnsortedArrayTest {
    private lateinit var unsortedArray: UnsortedArray<Int>

    @BeforeEach
    fun setUp() {
        unsortedArray = UnsortedArray(10)
    }

    @Test
    fun `test insert adds element at end`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)
        unsortedArray.insert(30)

        assertEquals(3, unsortedArray.size)
        assertEquals(0, unsortedArray.get(10))
        assertEquals(1, unsortedArray.get(20))
        assertEquals(2, unsortedArray.get(30))
    }

    @Test
    fun `test insert maintains insertion order`() {
        unsortedArray.insert(5)
        unsortedArray.insert(2)
        unsortedArray.insert(8)
        unsortedArray.insert(1)

        assertEquals(" 5 2 8 1", unsortedArray.toString())
    }

    @Test
    fun `test insert throws when array is full`() {
        val smallArray = UnsortedArray<Int>(3)
        smallArray.insert(1)
        smallArray.insert(2)
        smallArray.insert(3)

        val exception = assertThrows<IllegalStateException> {
            smallArray.insert(4)
        }
        assertEquals("Array está cheio", exception.message)
    }

    @Test
    fun `test remove returns element and replaces with last`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)
        unsortedArray.insert(30)
        unsortedArray.insert(40)

        val removed = unsortedArray.remove(1)

        assertEquals(20, removed)
        assertEquals(3, unsortedArray.size)
        assertEquals(" 10 40 30", unsortedArray.toString())
    }

    @Test
    fun `test remove first element`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)
        unsortedArray.insert(30)

        val removed = unsortedArray.remove(0)

        assertEquals(10, removed)
        assertEquals(2, unsortedArray.size)
        assertEquals(" 30 20", unsortedArray.toString())
    }

    @Test
    fun `test remove last element`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)
        unsortedArray.insert(30)

        val removed = unsortedArray.remove(2)

        assertEquals(30, removed)
        assertEquals(2, unsortedArray.size)
        assertEquals(" 10 20", unsortedArray.toString())
    }

    @Test
    fun `test remove only element`() {
        unsortedArray.insert(10)

        val removed = unsortedArray.remove(0)

        assertEquals(10, removed)
        assertEquals(0, unsortedArray.size)
    }

    @Test
    fun `test remove throws on invalid index`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)

        assertThrows<IllegalArgumentException> {
            unsortedArray.remove(-1)
        }

        assertThrows<IllegalArgumentException> {
            unsortedArray.remove(2)
        }

        assertThrows<IllegalArgumentException> {
            unsortedArray.remove(10)
        }
    }

    @Test
    fun `test get finds existing element`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)
        unsortedArray.insert(30)

        assertEquals(0, unsortedArray.get(10))
        assertEquals(1, unsortedArray.get(20))
        assertEquals(2, unsortedArray.get(30))
    }

    @Test
    fun `test get returns null for non-existing element`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)

        assertNull(unsortedArray.get(30))
        assertNull(unsortedArray.get(0))
    }

    @Test
    fun `test get on empty array`() {
        assertNull(unsortedArray.get(10))
    }

    @Test
    fun `test get finds first occurrence`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)
        unsortedArray.insert(10)
        unsortedArray.insert(30)

        assertEquals(0, unsortedArray.get(10))
    }

    @Test
    fun `test size tracking`() {
        assertEquals(0, unsortedArray.size)

        unsortedArray.insert(10)
        assertEquals(1, unsortedArray.size)

        unsortedArray.insert(20)
        assertEquals(2, unsortedArray.size)

        unsortedArray.remove(0)
        assertEquals(1, unsortedArray.size)

        unsortedArray.remove(0)
        assertEquals(0, unsortedArray.size)
    }

    @Test
    fun `test toString with empty array`() {
        assertEquals("", unsortedArray.toString())
    }

    @Test
    fun `test toString with elements`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)
        unsortedArray.insert(30)

        assertEquals(" 10 20 30", unsortedArray.toString())
    }

    @Test
    fun `test constructor throws on invalid maxSize`() {
        assertThrows<IllegalArgumentException> {
            UnsortedArray<Int>(0)
        }

        assertThrows<IllegalArgumentException> {
            UnsortedArray<Int>(-1)
        }
    }

    @Test
    fun `test with strings`() {
        val stringArray = UnsortedArray<String>(5)
        stringArray.insert("hello")
        stringArray.insert("world")
        stringArray.insert("test")

        assertEquals(0, stringArray.get("hello"))
        assertEquals(1, stringArray.get("world"))
        assertEquals(2, stringArray.get("test"))
        assertEquals(" hello world test", stringArray.toString())
    }

    @Test
    fun `test traverse executes operation on all elements`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)
        unsortedArray.insert(30)

        val results = mutableListOf<Int?>()
        unsortedArray.traverse { results.add(it) }

        // traverse processes entire internal array, not just used elements
        assertTrue(results.contains(10))
        assertTrue(results.contains(20))
        assertTrue(results.contains(30))
    }

    @Test
    fun `test complex sequence of operations`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)
        unsortedArray.insert(30)
        unsortedArray.insert(40)
        unsortedArray.insert(50)

        assertEquals(5, unsortedArray.size)
        assertEquals(" 10 20 30 40 50", unsortedArray.toString())

        unsortedArray.remove(2) // removes 30, replaces with 50
        assertEquals(4, unsortedArray.size)
        assertEquals(" 10 20 50 40", unsortedArray.toString())

        unsortedArray.insert(60)
        assertEquals(5, unsortedArray.size)
        assertEquals(" 10 20 50 40 60", unsortedArray.toString())

        assertEquals(1, unsortedArray.get(20))
        assertNull(unsortedArray.get(30))
    }

    @Test
    fun `test remove all elements one by one`() {
        unsortedArray.insert(10)
        unsortedArray.insert(20)
        unsortedArray.insert(30)

        unsortedArray.remove(0)
        assertEquals(2, unsortedArray.size)

        unsortedArray.remove(0)
        assertEquals(1, unsortedArray.size)

        unsortedArray.remove(0)
        assertEquals(0, unsortedArray.size)
    }

    @Test
    fun `test fill array to capacity`() {
        val smallArray = UnsortedArray<Int>(3)
        smallArray.insert(1)
        smallArray.insert(2)
        smallArray.insert(3)

        assertEquals(3, smallArray.size)
        assertEquals(" 1 2 3", smallArray.toString())
    }
}
