package digital.tonima.dataestructures.array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UnsortedDynamicArrayTest {
    private lateinit var dynamicArray: UnsortedDynamicArray<Int>

    @BeforeEach
    fun setUp() {
        dynamicArray = UnsortedDynamicArray(4)
    }

    @Test
    fun `test insert adds elements`() {
        dynamicArray.insert(10)
        dynamicArray.insert(20)
        dynamicArray.insert(30)

        assertEquals(3, dynamicArray.size)
        assertEquals(4, dynamicArray.capacity)
    }

    @Test
    fun `test insert doubles capacity when full`() {
        dynamicArray.insert(10)
        dynamicArray.insert(20)
        dynamicArray.insert(30)
        dynamicArray.insert(40)

        assertEquals(4, dynamicArray.size)
        assertEquals(4, dynamicArray.capacity)

        dynamicArray.insert(50) // should trigger doubling

        assertEquals(5, dynamicArray.size)
        assertEquals(8, dynamicArray.capacity)
    }

    @Test
    fun `test multiple doublings`() {
        val smallArray = UnsortedDynamicArray<Int>(2)

        smallArray.insert(1)
        smallArray.insert(2)
        assertEquals(2, smallArray.capacity)

        smallArray.insert(3) // doubles to 4
        assertEquals(4, smallArray.capacity)

        smallArray.insert(4)
        smallArray.insert(5) // doubles to 8
        assertEquals(8, smallArray.capacity)

        assertEquals(5, smallArray.size)
    }

    @Test
    fun `test remove finds and removes element`() {
        dynamicArray.insert(10)
        dynamicArray.insert(20)
        dynamicArray.insert(30)
        dynamicArray.insert(40)

        dynamicArray.remove(20)

        assertEquals(3, dynamicArray.size)
        assertNull(dynamicArray.find(20))
        assertEquals(0, dynamicArray.find(10))
        assertEquals(1, dynamicArray.find(30))
        assertEquals(2, dynamicArray.find(40))
    }

    @Test
    fun `test remove first element`() {
        dynamicArray.insert(10)
        dynamicArray.insert(20)
        dynamicArray.insert(30)

        dynamicArray.remove(10)

        assertEquals(2, dynamicArray.size)
        assertNull(dynamicArray.find(10))
        assertEquals(0, dynamicArray.find(20))
    }

    @Test
    fun `test remove last element`() {
        dynamicArray.insert(10)
        dynamicArray.insert(20)
        dynamicArray.insert(30)

        dynamicArray.remove(30)

        assertEquals(2, dynamicArray.size)
        assertNull(dynamicArray.find(30))
    }

    @Test
    fun `test remove non-existing element does nothing`() {
        dynamicArray.insert(10)
        dynamicArray.insert(20)

        dynamicArray.remove(30)

        assertEquals(2, dynamicArray.size)
    }

    @Test
    fun `test remove halves capacity when size is quarter of capacity`() {
        val array = UnsortedDynamicArray<Int>(2)

        // Fill to trigger doubling
        array.insert(1)
        array.insert(2)
        array.insert(3) // capacity now 4
        array.insert(4)
        array.insert(5) // capacity now 8

        assertEquals(8, array.capacity)
        assertEquals(5, array.size)

        // Remove until size <= capacity/4
        array.remove(5) // size 4, capacity 8
        assertEquals(8, array.capacity)

        array.remove(4) // size 3, capacity 8
        assertEquals(8, array.capacity)

        array.remove(3) // size 2, capacity 8 -> should halve to 4
        assertEquals(4, array.capacity)
        assertEquals(2, array.size)
    }

    @Test
    fun `test halving does not go below capacity 1`() {
        val array = UnsortedDynamicArray<Int>(2)
        array.insert(1)
        array.insert(2)

        array.remove(1)
        array.remove(2)

        // capacity should not go below 1
        assertTrue(array.capacity >= 1)
    }

    @Test
    fun `test find returns correct index`() {
        dynamicArray.insert(10)
        dynamicArray.insert(20)
        dynamicArray.insert(30)
        dynamicArray.insert(40)

        assertEquals(0, dynamicArray.find(10))
        assertEquals(1, dynamicArray.find(20))
        assertEquals(2, dynamicArray.find(30))
        assertEquals(3, dynamicArray.find(40))
    }

    @Test
    fun `test find returns null for non-existing element`() {
        dynamicArray.insert(10)
        dynamicArray.insert(20)

        assertNull(dynamicArray.find(30))
        assertNull(dynamicArray.find(0))
    }

    @Test
    fun `test find on empty array`() {
        assertNull(dynamicArray.find(10))
    }

    @Test
    fun `test find returns first occurrence`() {
        dynamicArray.insert(10)
        dynamicArray.insert(20)
        dynamicArray.insert(10)
        dynamicArray.insert(30)

        assertEquals(0, dynamicArray.find(10))
    }

    @Test
    fun `test size tracking`() {
        assertEquals(0, dynamicArray.size)

        dynamicArray.insert(10)
        assertEquals(1, dynamicArray.size)

        dynamicArray.insert(20)
        assertEquals(2, dynamicArray.size)

        dynamicArray.remove(10)
        assertEquals(1, dynamicArray.size)

        dynamicArray.remove(20)
        assertEquals(0, dynamicArray.size)
    }

    @Test
    fun `test constructor throws on invalid initialCapacity`() {
        assertThrows<IllegalArgumentException> {
            UnsortedDynamicArray<Int>(0)
        }

        assertThrows<IllegalArgumentException> {
            UnsortedDynamicArray<Int>(-1)
        }
    }

    @Test
    fun `test with strings`() {
        val stringArray = UnsortedDynamicArray<String>(2)
        stringArray.insert("hello")
        stringArray.insert("world")
        stringArray.insert("test")

        assertEquals(3, stringArray.size)
        assertEquals(0, stringArray.find("hello"))
        assertEquals(1, stringArray.find("world"))
        assertEquals(2, stringArray.find("test"))
    }

    @Test
    fun `test capacity management with single element`() {
        val array = UnsortedDynamicArray<Int>(1)
        assertEquals(1, array.capacity)

        array.insert(10)
        assertEquals(1, array.capacity)

        array.insert(20) // should double to 2
        assertEquals(2, array.capacity)
        assertEquals(2, array.size)
    }

    @Test
    fun `test elements are preserved after resizing`() {
        val array = UnsortedDynamicArray<Int>(2)
        array.insert(10)
        array.insert(20)
        array.insert(30) // triggers doubling
        array.insert(40)

        assertEquals(0, array.find(10))
        assertEquals(1, array.find(20))
        assertEquals(2, array.find(30))
        assertEquals(3, array.find(40))
    }

    @Test
    fun `test elements are preserved after shrinking`() {
        val array = UnsortedDynamicArray<Int>(2)
        array.insert(10)
        array.insert(20)
        array.insert(30) // capacity now 4
        array.insert(40)
        array.insert(50) // capacity now 8

        // Remove to trigger shrinking
        array.remove(50)
        array.remove(40)
        array.remove(30) // should trigger halving

        assertEquals(0, array.find(10))
        assertEquals(1, array.find(20))
        assertNull(array.find(30))
    }

    @Test
    fun `test complex sequence of operations`() {
        val array = UnsortedDynamicArray<Int>(2)

        // Insert phase
        array.insert(1)
        array.insert(2)
        assertEquals(2, array.capacity)

        array.insert(3) // double to 4
        assertEquals(4, array.capacity)
        assertEquals(3, array.size)

        // Remove phase
        array.remove(2)
        assertEquals(2, array.size)
        assertEquals(4, array.capacity)

        array.remove(1) // size=1, capacity should halve to 2
        assertEquals(1, array.size)
        assertEquals(2, array.capacity)

        // Insert again
        array.insert(4)
        array.insert(5) // should double to 4
        assertEquals(3, array.size)
        assertEquals(4, array.capacity)

        assertEquals(0, array.find(3))
        assertEquals(1, array.find(4))
        assertEquals(2, array.find(5))
    }

    @Test
    fun `test large number of insertions`() {
        val array = UnsortedDynamicArray<Int>(2)

        for (i in 1..100) {
            array.insert(i)
        }

        assertEquals(100, array.size)
        assertTrue(array.capacity >= 100)

        // Verify all elements are present
        for (i in 1..100) {
            assertNotNull(array.find(i))
        }
    }

    @Test
    fun `test large number of removals`() {
        val array = UnsortedDynamicArray<Int>(2)

        // Insert many elements
        for (i in 1..100) {
            array.insert(i)
        }

        val capacityAfterInsertions = array.capacity

        // Remove most elements
        for (i in 1..90) {
            array.remove(i)
        }

        assertEquals(10, array.size)
        assertTrue(array.capacity < capacityAfterInsertions)

        // Verify remaining elements
        for (i in 91..100) {
            assertNotNull(array.find(i))
        }
    }
}
