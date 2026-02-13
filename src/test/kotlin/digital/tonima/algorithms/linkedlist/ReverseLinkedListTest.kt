package digital.tonima.algorithms.linkedlist

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertNull

/**
 * Test cases for LeetCode #206 - Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * Reverses a singly linked list
 */
class ReverseLinkedListTest {

    private val solution = ReverseLinkedListSolution()

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
    fun testReverseEmptyList() {
        // Test case: empty list (null)
        val result = solution.reverseList(null)
        assertNull(result)
    }

    @Test
    fun testReverseSingleNodeList() {
        // Test case: [1]
        // Expected: [1]
        val head = createLinkedList(intArrayOf(1))
        val result = solution.reverseList(head)
        assertContentEquals(intArrayOf(1), linkedListToArray(result))
    }

    @Test
    fun testReverseTwoNodeList() {
        // Test case: [1, 2]
        // Expected: [2, 1]
        val head = createLinkedList(intArrayOf(1, 2))
        val result = solution.reverseList(head)
        assertContentEquals(intArrayOf(2, 1), linkedListToArray(result))
    }

    @Test
    fun testReverseThreeNodeList() {
        // Test case: [1, 2, 3]
        // Expected: [3, 2, 1]
        val head = createLinkedList(intArrayOf(1, 2, 3))
        val result = solution.reverseList(head)
        assertContentEquals(intArrayOf(3, 2, 1), linkedListToArray(result))
    }

    @Test
    fun testReverseFiveNodeList() {
        // Test case: [1, 2, 3, 4, 5]
        // Expected: [5, 4, 3, 2, 1]
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        val result = solution.reverseList(head)
        assertContentEquals(intArrayOf(5, 4, 3, 2, 1), linkedListToArray(result))
    }

    @Test
    fun testReverseListWithDuplicates() {
        // Test case: [1, 2, 2, 1]
        // Expected: [1, 2, 2, 1]
        val head = createLinkedList(intArrayOf(1, 2, 2, 1))
        val result = solution.reverseList(head)
        assertContentEquals(intArrayOf(1, 2, 2, 1), linkedListToArray(result))
    }

    @Test
    fun testReverseListWithAllSameValues() {
        // Test case: [5, 5, 5]
        // Expected: [5, 5, 5]
        val head = createLinkedList(intArrayOf(5, 5, 5))
        val result = solution.reverseList(head)
        assertContentEquals(intArrayOf(5, 5, 5), linkedListToArray(result))
    }

    @Test
    fun testReverseListWithNegativeNumbers() {
        // Test case: [-1, -2, -3]
        // Expected: [-3, -2, -1]
        val head = createLinkedList(intArrayOf(-1, -2, -3))
        val result = solution.reverseList(head)
        assertContentEquals(intArrayOf(-3, -2, -1), linkedListToArray(result))
    }

    @Test
    fun testReverseListWithMixedNumbers() {
        // Test case: [0, -1, 1, 2]
        // Expected: [2, 1, -1, 0]
        val head = createLinkedList(intArrayOf(0, -1, 1, 2))
        val result = solution.reverseList(head)
        assertContentEquals(intArrayOf(2, 1, -1, 0), linkedListToArray(result))
    }

    @Test
    fun testReverseListWithLargeNumbers() {
        // Test case: [100, 200, 300]
        // Expected: [300, 200, 100]
        val head = createLinkedList(intArrayOf(100, 200, 300))
        val result = solution.reverseList(head)
        assertContentEquals(intArrayOf(300, 200, 100), linkedListToArray(result))
    }

    @Test
    fun testReverseAndReverseAgain() {
        // Test case: reverse [1, 2, 3, 4] twice should return [1, 2, 3, 4]
        var head = createLinkedList(intArrayOf(1, 2, 3, 4))
        head = solution.reverseList(head)
        head = solution.reverseList(head)
        assertContentEquals(intArrayOf(1, 2, 3, 4), linkedListToArray(head))
    }
}

