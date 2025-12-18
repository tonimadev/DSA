package digital.tonima.dataestructures

import digital.tonima.dataestructures.linkedlist.LinkedList
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LinkedListTest {

    // ===== APPEND TESTS =====
    @Test
    fun `append should add element to empty list`() {
        val list = LinkedList<Int>()
        list.append(10)

        assertEquals(1, list.size)
        assertEquals(10, list.get(0)?.value)
    }

    @Test
    fun `append should add multiple elements in order`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        assertEquals(3, list.size)
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    fun `append should maintain head and tail pointers correctly`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)

        assertEquals(10, list.get(0)?.value) // head
        assertEquals(20, list.get(1)?.value) // tail
    }

    // ===== PREPEND TESTS =====
    @Test
    fun `prepend should add element to empty list`() {
        val list = LinkedList<Int>()
        list.prepend(10)

        assertEquals(1, list.size)
        assertEquals(10, list.get(0)?.value)
    }

    @Test
    fun `prepend should add elements at the beginning`() {
        val list = LinkedList<Int>()
        list.prepend(30)
        list.prepend(20)
        list.prepend(10)

        assertEquals(3, list.size)
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    fun `prepend should maintain tail pointer correctly`() {
        val list = LinkedList<Int>()
        list.prepend(10)
        list.prepend(20)

        assertEquals(10, list.get(1)?.value) // tail
    }

    // ===== REMOVE LAST TESTS =====
    @Test
    fun `removeLast should return null on empty list`() {
        val list = LinkedList<Int>()
        val removed = list.removeLast()

        assertNull(removed)
        assertEquals(0, list.size)
    }

    @Test
    fun `removeLast should remove the last element`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        val removed = list.removeLast()

        assertEquals(30, removed?.value)
        assertEquals(2, list.size)
        assertEquals(20, list.get(1)?.value)
    }

    @Test
    fun `removeLast should handle single element list`() {
        val list = LinkedList<Int>()
        list.append(10)

        val removed = list.removeLast()

        assertEquals(10, removed?.value)
        assertEquals(0, list.size)
    }

    @Test
    fun `removeLast should set head and tail to null when list becomes empty`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.removeLast()

        assertNull(list.get(0))
        assertEquals(0, list.size)
    }

    // ===== REMOVE FIRST TESTS =====
    @Test
    fun `removeFirst should return null on empty list`() {
        val list = LinkedList<Int>()
        val removed = list.removeFirst()

        assertNull(removed)
        assertEquals(0, list.size)
    }

    @Test
    fun `removeFirst should remove the first element`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        val removed = list.removeFirst()

        assertEquals(10, removed?.value)
        assertEquals(2, list.size)
        assertEquals(20, list.get(0)?.value)
    }

    @Test
    fun `removeFirst should handle single element list`() {
        val list = LinkedList<Int>()
        list.append(10)

        val removed = list.removeFirst()

        assertEquals(10, removed?.value)
        assertEquals(0, list.size)
    }

    // ===== GET TESTS =====
    @Test
    fun `get should return null for invalid indices`() {
        val list = LinkedList<Int>()
        list.append(10)

        assertNull(list.get(-1))
        assertNull(list.get(1))
        assertNull(list.get(10))
    }

    @Test
    fun `get should return element at valid index`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    fun `get should work on empty list`() {
        val list = LinkedList<Int>()

        assertNull(list.get(0))
    }

    // ===== SET TESTS =====
    @Test
    fun `set should update value at valid index`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        val result = list.set(1, 99)

        assertTrue(result)
        assertEquals(99, list.get(1)?.value)
    }

    @Test
    fun `set should return false for invalid index`() {
        val list = LinkedList<Int>()
        list.append(10)

        val result = list.set(10, 99)

        assertFalse(result)
    }

    @Test
    fun `set should handle negative indices`() {
        val list = LinkedList<Int>()
        list.append(10)

        val result = list.set(-1, 99)

        assertFalse(result)
    }

    // ===== INSERT TESTS =====
    @Test
    fun `insert should add element at beginning`() {
        val list = LinkedList<Int>()
        list.append(20)
        list.append(30)

        val result = list.insert(0, 10)

        assertTrue(result)
        assertEquals(3, list.size)
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    fun `insert should add element at end`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)

        val result = list.insert(list.size, 30)

        assertTrue(result)
        assertEquals(3, list.size)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    fun `insert should add element in the middle`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(30)

        val result = list.insert(1, 20)

        assertTrue(result)
        assertEquals(3, list.size)
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    fun `insert should return false for invalid index`() {
        val list = LinkedList<Int>()
        list.append(10)

        val result1 = list.insert(-1, 20)
        val result2 = list.insert(10, 20)

        assertFalse(result1)
        assertFalse(result2)
    }

    @Test
    fun `insert should work on empty list`() {
        val list = LinkedList<Int>()

        val result = list.insert(0, 10)

        assertTrue(result)
        assertEquals(1, list.size)
        assertEquals(10, list.get(0)?.value)
    }

    // ===== REMOVE TESTS =====
    @Test
    fun `remove should return null on empty list`() {
        val list = LinkedList<Int>()
        val removed = list.remove(0)

        assertNull(removed)
    }

    @Test
    fun `remove should return null for invalid index`() {
        val list = LinkedList<Int>()
        list.append(10)

        assertNull(list.remove(-1))
        assertNull(list.remove(10))
    }

    @Test
    fun `remove should remove element from beginning`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        val removed = list.remove(0)

        assertEquals(10, removed?.value)
        assertEquals(2, list.size)
        assertEquals(20, list.get(0)?.value)
    }

    @Test
    fun `remove should remove element from middle`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        val removed = list.remove(1)

        assertEquals(20, removed?.value)
        assertEquals(2, list.size)
        assertEquals(10, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
    }

    @Test
    fun `remove should remove element from end`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        val removed = list.remove(2)

        assertEquals(30, removed?.value)
        assertEquals(2, list.size)
        assertEquals(20, list.get(1)?.value)
    }

    // ===== REVERSE TESTS =====
    @Test
    fun `reverse should reverse empty list`() {
        val list = LinkedList<Int>()
        list.reverse()

        assertEquals(0, list.size)
    }

    @Test
    fun `reverse should reverse single element list`() {
        val list = LinkedList<Int>()
        list.append(10)

        list.reverse()

        assertEquals(1, list.size)
        assertEquals(10, list.get(0)?.value)
    }

    @Test
    fun `reverse should reverse list with multiple elements`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.reverse()

        assertEquals(4, list.size)
        assertEquals(40, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
        assertEquals(20, list.get(2)?.value)
        assertEquals(10, list.get(3)?.value)
    }

    @Test
    fun `reverse should swap head and tail correctly`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)

        list.reverse()

        assertEquals(20, list.get(0)?.value) // new head (old tail)
        assertEquals(10, list.get(1)?.value) // new tail (old head)
    }

    // ===== SIZE TESTS =====
    @Test
    fun `size should be 0 for empty list`() {
        val list = LinkedList<Int>()

        assertEquals(0, list.size)
    }

    @Test
    fun `size should increase with append`() {
        val list = LinkedList<Int>()

        list.append(10)
        assertEquals(1, list.size)

        list.append(20)
        assertEquals(2, list.size)

        list.append(30)
        assertEquals(3, list.size)
    }

    @Test
    fun `size should decrease with removeLast`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        list.removeLast()
        assertEquals(2, list.size)

        list.removeLast()
        assertEquals(1, list.size)
    }

    // ===== INTEGRATION TESTS =====
    @Test
    fun `mixed operations should maintain list integrity`() {
        val list = LinkedList<Int>()

        list.append(10)
        list.append(20)
        list.prepend(5)
        list.insert(2, 15)

        assertEquals(4, list.size)
        assertEquals(5, list.get(0)?.value)
        assertEquals(10, list.get(1)?.value)
        assertEquals(15, list.get(2)?.value)
        assertEquals(20, list.get(3)?.value)
    }

    @Test
    fun `operations after remove should work correctly`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        list.remove(1)
        list.append(40)

        assertEquals(3, list.size)
        assertEquals(10, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
        assertEquals(40, list.get(2)?.value)
    }

    @Test
    fun `reverse then operations should work correctly`() {
        val list = LinkedList<Int>()
        list.append(10)
        list.append(20)
        list.append(30)

        list.reverse()
        list.append(40)

        assertEquals(4, list.size)
        assertEquals(30, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(10, list.get(2)?.value)
        assertEquals(40, list.get(3)?.value)
    }

    // ===== STRING TYPE TESTS =====
    @Test
    fun `linkedList should work with String type`() {
        val list = LinkedList<String>()
        list.append("Hello")
        list.append("World")

        assertEquals(2, list.size)
        assertEquals("Hello", list.get(0)?.value)
        assertEquals("World", list.get(1)?.value)
    }

    @Test
    fun `linkedList should work with different types`() {
        val intList = LinkedList<Int>()
        intList.append(10)

        val stringList = LinkedList<String>()
        stringList.append("test")

        val doubleList = LinkedList<Double>()
        doubleList.append(3.14)

        assertEquals(10, intList.get(0)?.value)
        assertEquals("test", stringList.get(0)?.value)
        assertEquals(3.14, doubleList.get(0)?.value)
    }
}

