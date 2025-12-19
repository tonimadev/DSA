package digital.tonima.algorithms.linkedlist

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RemoveNthFromEndTest {

    private val solution = RemoveNthFromEndSolution()

    // Helper function to create a linked list from an array
    private fun createLinkedList(values: IntArray): ListNode? {
        if (values.isEmpty()) return null
        val head = ListNode(values[0])
        var current = head
        for (i in 1 until values.size) {
            current.next = ListNode(values[i])
            current = current.next!!
        }
        return head
    }

    // Helper function to convert linked list to array for easy comparison
    private fun linkedListToArray(head: ListNode?): IntArray {
        val result = mutableListOf<Int>()
        var current = head
        while (current != null) {
            result.add(current.`val`)
            current = current.next
        }
        return result.toIntArray()
    }

    @Test
    fun `test findKthFromEnd - basic case`() {
        // [1, 2, 3, 4, 5], k = 2 -> should return node with value 4
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        val result = solution.findKthFromEnd(head, 2)
        assertEquals(4, result?.`val`)
    }

    @Test
    fun `test findKthFromEnd - first from end`() {
        // [1, 2, 3, 4, 5], k = 1 -> should return node with value 5
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        val result = solution.findKthFromEnd(head, 1)
        assertEquals(5, result?.`val`)
    }

    @Test
    fun `test findKthFromEnd - last element (head)`() {
        // [1, 2, 3, 4, 5], k = 5 -> should return node with value 1
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        val result = solution.findKthFromEnd(head, 5)
        assertEquals(1, result?.`val`)
    }

    @Test
    fun `test findKthFromEnd - k greater than list size`() {
        // [1, 2, 3], k = 5 -> should return null
        val head = createLinkedList(intArrayOf(1, 2, 3))
        val result = solution.findKthFromEnd(head, 5)
        assertNull(result)
    }

    @Test
    fun `test findKthFromEnd - single element list`() {
        // [1], k = 1 -> should return node with value 1
        val head = createLinkedList(intArrayOf(1))
        val result = solution.findKthFromEnd(head, 1)
        assertEquals(1, result?.`val`)
    }

    @Test
    fun `test findKthFromEnd - empty list`() {
        // [], k = 1 -> should return null
        val head = createLinkedList(intArrayOf())
        val result = solution.findKthFromEnd(head, 1)
        assertNull(result)
    }

    @Test
    fun `test findKthFromEnd - invalid k (zero)`() {
        // [1, 2, 3], k = 0 -> should return null
        val head = createLinkedList(intArrayOf(1, 2, 3))
        val result = solution.findKthFromEnd(head, 0)
        assertNull(result)
    }

    @Test
    fun `test removeNthFromEnd - LeetCode Example 1`() {
        // Input: head = [1,2,3,4,5], n = 2
        // Output: [1,2,3,5]
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        val result = solution.removeNthFromEnd(head, 2)
        val expected = intArrayOf(1, 2, 3, 5)
        assertEquals(expected.toList(), linkedListToArray(result).toList())
    }

    @Test
    fun `test removeNthFromEnd - LeetCode Example 2`() {
        // Input: head = [1], n = 1
        // Output: []
        val head = createLinkedList(intArrayOf(1))
        val result = solution.removeNthFromEnd(head, 1)
        val expected = intArrayOf()
        assertEquals(expected.toList(), linkedListToArray(result).toList())
    }

    @Test
    fun `test removeNthFromEnd - LeetCode Example 3`() {
        // Input: head = [1,2], n = 1
        // Output: [1]
        val head = createLinkedList(intArrayOf(1, 2))
        val result = solution.removeNthFromEnd(head, 1)
        val expected = intArrayOf(1)
        assertEquals(expected.toList(), linkedListToArray(result).toList())
    }

    @Test
    fun `test removeNthFromEnd - remove head`() {
        // Input: head = [1,2], n = 2
        // Output: [2]
        val head = createLinkedList(intArrayOf(1, 2))
        val result = solution.removeNthFromEnd(head, 2)
        val expected = intArrayOf(2)
        assertEquals(expected.toList(), linkedListToArray(result).toList())
    }

    @Test
    fun `test removeNthFromEnd - remove from middle`() {
        // Input: head = [1,2,3,4,5], n = 3
        // Output: [1,2,4,5]
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        val result = solution.removeNthFromEnd(head, 3)
        val expected = intArrayOf(1, 2, 4, 5)
        assertEquals(expected.toList(), linkedListToArray(result).toList())
    }
}

