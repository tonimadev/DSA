package digital.tonima.algorithms.linkedlist

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Test cases for LeetCode #141 - Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 */
class HasCycleTest {

    private val solution = HasCycleSolution()

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

    // Helper function to create a cycle at a specific position
    private fun createCycle(head: ListNode?, pos: Int) {
        if (head == null || pos < 0) return

        var tail: ListNode? = head
        var cycleNode: ListNode? = null
        var index = 0

        while (tail?.next != null) {
            if (index == pos) {
                cycleNode = tail
            }
            tail = tail.next
            index++
        }

        // If tail is at pos, use it as cycle node
        if (index == pos) {
            cycleNode = tail
        }

        // Create the cycle
        tail?.next = cycleNode
    }

    @Test
    fun `test hasCycle - LeetCode Example 1`() {
        // Input: head = [3,2,0,-4], pos = 1
        // Output: true
        // Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
        val head = createLinkedList(intArrayOf(3, 2, 0, -4))
        createCycle(head, 1)
        val result = solution.hasCycle(head)
        assertTrue(result ?: false, "Expected cycle to be detected")
    }

    @Test
    fun `test hasCycle - LeetCode Example 2`() {
        // Input: head = [1,2], pos = 0
        // Output: true
        // Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
        val head = createLinkedList(intArrayOf(1, 2))
        createCycle(head, 0)
        val result = solution.hasCycle(head)
        assertTrue(result ?: false, "Expected cycle to be detected")
    }

    @Test
    fun `test hasCycle - LeetCode Example 3`() {
        // Input: head = [1], pos = -1
        // Output: false
        // Explanation: There is no cycle in the linked list.
        val head = createLinkedList(intArrayOf(1))
        createCycle(head, -1)
        val result = solution.hasCycle(head)
        assertFalse(result ?: true, "Expected no cycle")
    }

    @Test
    fun `test hasCycle - no cycle in list`() {
        // Input: head = [1,2,3,4,5], pos = -1
        // Output: false
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        val result = solution.hasCycle(head)
        assertFalse(result ?: true, "Expected no cycle")
    }

    @Test
    fun `test hasCycle - empty list`() {
        // Input: head = [], pos = -1
        // Output: false
        val head = createLinkedList(intArrayOf())
        val result = solution.hasCycle(head)
        assertFalse(result ?: true, "Expected no cycle in empty list")
    }

    @Test
    fun `test hasCycle - single node with cycle`() {
        // Input: head = [1], pos = 0
        // Output: true
        // The node points to itself
        val head = ListNode(1)
        head.next = head
        val result = solution.hasCycle(head)
        assertTrue(result ?: false, "Expected cycle to be detected")
    }

    @Test
    fun `test hasCycle - cycle at the end`() {
        // Input: head = [1,2,3,4,5], pos = 4
        // Output: true
        // The tail connects to itself
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        createCycle(head, 4)
        val result = solution.hasCycle(head)
        assertTrue(result ?: false, "Expected cycle to be detected")
    }

    @Test
    fun `test hasCycle - cycle at the beginning`() {
        // Input: head = [1,2,3,4,5], pos = 0
        // Output: true
        // The tail connects to the head
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        createCycle(head, 0)
        val result = solution.hasCycle(head)
        assertTrue(result ?: false, "Expected cycle to be detected")
    }

    @Test
    fun `test hasCycle - two nodes no cycle`() {
        // Input: head = [1,2], pos = -1
        // Output: false
        val head = createLinkedList(intArrayOf(1, 2))
        val result = solution.hasCycle(head)
        assertFalse(result ?: true, "Expected no cycle")
    }
}

