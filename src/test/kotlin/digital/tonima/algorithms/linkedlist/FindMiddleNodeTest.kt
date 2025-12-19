package digital.tonima.algorithms.linkedlist

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Test cases for LeetCode #876 - Middle of the Linked List
 * https://leetcode.com/problems/middle-of-the-linked-list/
 */
class FindMiddleNodeTest {

    private val solution = FindMiddleNodeSolution()

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
    fun `test middleNode - LeetCode Example 1`() {
        // Input: head = [1,2,3,4,5]
        // Output: [3,4,5]
        // Explanation: The middle node of the list is node 3.
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        val result = solution.middleNode(head)
        assertEquals(3, result?.`val`)
        val expected = intArrayOf(3, 4, 5)
        assertEquals(expected.toList(), linkedListToArray(result).toList())
    }

    @Test
    fun `test middleNode - LeetCode Example 2`() {
        // Input: head = [1,2,3,4,5,6]
        // Output: [4,5,6]
        // Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5, 6))
        val result = solution.middleNode(head)
        assertEquals(4, result?.`val`)
        val expected = intArrayOf(4, 5, 6)
        assertEquals(expected.toList(), linkedListToArray(result).toList())
    }

    @Test
    fun `test middleNode - single element`() {
        // Input: head = [1]
        // Output: [1]
        val head = createLinkedList(intArrayOf(1))
        val result = solution.middleNode(head)
        assertEquals(1, result?.`val`)
    }

    @Test
    fun `test middleNode - two elements`() {
        // Input: head = [1,2]
        // Output: [2]
        // When there are two middle nodes, return the second one
        val head = createLinkedList(intArrayOf(1, 2))
        val result = solution.middleNode(head)
        assertEquals(2, result?.`val`)
    }

    @Test
    fun `test middleNode - three elements`() {
        // Input: head = [1,2,3]
        // Output: [2,3]
        val head = createLinkedList(intArrayOf(1, 2, 3))
        val result = solution.middleNode(head)
        assertEquals(2, result?.`val`)
        val expected = intArrayOf(2, 3)
        assertEquals(expected.toList(), linkedListToArray(result).toList())
    }

    @Test
    fun `test middleNode - large list odd length`() {
        // Input: head = [1,2,3,4,5,6,7,8,9]
        // Output: [5,6,7,8,9]
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
        val result = solution.middleNode(head)
        assertEquals(5, result?.`val`)
    }

    @Test
    fun `test middleNode - large list even length`() {
        // Input: head = [1,2,3,4,5,6,7,8]
        // Output: [5,6,7,8]
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8))
        val result = solution.middleNode(head)
        assertEquals(5, result?.`val`)
    }
}

