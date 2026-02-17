package digital.tonima.algorithms.linkedlist

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Test cases for LeetCode Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 *
 * Add two numbers represented by linked lists where digits are stored in reverse order
 */
class AddTwoNumbersTest {

    private val solution = AddTwoNumbersSolution()

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
    fun `test example 1 - 342 + 465 = 807`() {
        // Input: l1 = [2,4,3], l2 = [5,6,4]
        // Output: [7,0,8]
        // Explanation: 342 + 465 = 807
        val l1 = createLinkedList(intArrayOf(2, 4, 3))
        val l2 = createLinkedList(intArrayOf(5, 6, 4))

        val result = solution.addTwoNumbers(l1, l2)

        assertNotNull(result)
        val resultArray = linkedListToArray(result)
        assertEquals(intArrayOf(7, 0, 8).toList(), resultArray.toList())
    }

    @Test
    fun `test example 2 - 0 + 0 = 0`() {
        // Input: l1 = [0], l2 = [0]
        // Output: [0]
        val l1 = createLinkedList(intArrayOf(0))
        val l2 = createLinkedList(intArrayOf(0))

        val result = solution.addTwoNumbers(l1, l2)

        assertNotNull(result)
        val resultArray = linkedListToArray(result)
        assertEquals(intArrayOf(0).toList(), resultArray.toList())
    }

    @Test
    fun `test example 3 - 9999999 + 9999 = 10009998`() {
        // Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
        // Output: [8,9,9,9,0,0,0,1]
        // Explanation: 9999999 + 9999 = 10009998
        val l1 = createLinkedList(intArrayOf(9, 9, 9, 9, 9, 9, 9))
        val l2 = createLinkedList(intArrayOf(9, 9, 9, 9))

        val result = solution.addTwoNumbers(l1, l2)

        assertNotNull(result)
        val resultArray = linkedListToArray(result)
        assertEquals(intArrayOf(8, 9, 9, 9, 0, 0, 0, 1).toList(), resultArray.toList())
    }

    @Test
    fun `test single digit addition - 5 + 5 = 10`() {
        // Input: l1 = [5], l2 = [5]
        // Output: [0,1]
        val l1 = createLinkedList(intArrayOf(5))
        val l2 = createLinkedList(intArrayOf(5))

        val result = solution.addTwoNumbers(l1, l2)

        assertNotNull(result)
        val resultArray = linkedListToArray(result)
        assertEquals(intArrayOf(0, 1).toList(), resultArray.toList())
    }

    @Test
    fun `test different lengths - 99 + 1 = 100`() {
        // Input: l1 = [9,9], l2 = [1]
        // Output: [0,0,1]
        val l1 = createLinkedList(intArrayOf(9, 9))
        val l2 = createLinkedList(intArrayOf(1))

        val result = solution.addTwoNumbers(l1, l2)

        assertNotNull(result)
        val resultArray = linkedListToArray(result)
        assertEquals(intArrayOf(0, 0, 1).toList(), resultArray.toList())
    }

    @Test
    fun `test no carry needed - 123 + 456 = 579`() {
        // Input: l1 = [3,2,1], l2 = [6,5,4]
        // Output: [9,7,5]
        val l1 = createLinkedList(intArrayOf(3, 2, 1))
        val l2 = createLinkedList(intArrayOf(6, 5, 4))

        val result = solution.addTwoNumbers(l1, l2)

        assertNotNull(result)
        val resultArray = linkedListToArray(result)
        assertEquals(intArrayOf(9, 7, 5).toList(), resultArray.toList())
    }

    @Test
    fun `test large numbers - multiple carries`() {
        // Input: l1 = [9,9,9], l2 = [9,9,9]
        // Output: [8,9,9,1]
        // Explanation: 999 + 999 = 1998
        val l1 = createLinkedList(intArrayOf(9, 9, 9))
        val l2 = createLinkedList(intArrayOf(9, 9, 9))

        val result = solution.addTwoNumbers(l1, l2)

        assertNotNull(result)
        val resultArray = linkedListToArray(result)
        assertEquals(intArrayOf(8, 9, 9, 1).toList(), resultArray.toList())
    }

    @Test
    fun `test one number much longer - 1 + 99999 = 100000`() {
        // Input: l1 = [1], l2 = [9,9,9,9,9]
        // Output: [0,0,0,0,0,1]
        val l1 = createLinkedList(intArrayOf(1))
        val l2 = createLinkedList(intArrayOf(9, 9, 9, 9, 9))

        val result = solution.addTwoNumbers(l1, l2)

        assertNotNull(result)
        val resultArray = linkedListToArray(result)
        assertEquals(intArrayOf(0, 0, 0, 0, 0, 1).toList(), resultArray.toList())
    }
}

