package digital.tonima.algorithms.linkedlist

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertNull

/**
 * Test cases for LeetCode #21 - Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Merges two sorted linked lists into one sorted linked list
 */
class MergeTwoSortedLinkedListsTest {

    private val solution = MergeTwoSortedLinkedListsSolution()

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
    fun testMergeTwoBothEmpty() {
        // Test case: [] + [] = []
        // Expected: []
        val result = solution.mergeTwoLists(null, null)
        assertNull(result)
    }

    @Test
    fun testMergeFirstListEmpty() {
        // Test case: [] + [0] = [0]
        // Expected: [0]
        val list1 = null
        val list2 = createLinkedList(intArrayOf(0))
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(0), linkedListToArray(result))
    }

    @Test
    fun testMergeSecondListEmpty() {
        // Test case: [5] + [] = [5]
        // Expected: [5]
        val list1 = createLinkedList(intArrayOf(5))
        val list2 = null
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(5), linkedListToArray(result))
    }

    @Test
    fun testMergeTwoSortedListsExample1() {
        // Test case: [1,2,4] + [1,3,4] = [1,1,2,3,4,4]
        // Expected: [1,1,2,3,4,4]
        val list1 = createLinkedList(intArrayOf(1, 2, 4))
        val list2 = createLinkedList(intArrayOf(1, 3, 4))
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(1, 1, 2, 3, 4, 4), linkedListToArray(result))
    }

    @Test
    fun testMergeTwoSortedListsExample2() {
        // Test case: [] + [] = []
        // Expected: []
        val result = solution.mergeTwoLists(null, null)
        assertNull(result)
    }

    @Test
    fun testMergeTwoSortedListsExample3() {
        // Test case: [] + [0] = [0]
        // Expected: [0]
        val list1 = null
        val list2 = createLinkedList(intArrayOf(0))
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(0), linkedListToArray(result))
    }

    @Test
    fun testMergeTwoSortedListsSingleNodes() {
        // Test case: [1] + [2] = [1,2]
        // Expected: [1,2]
        val list1 = createLinkedList(intArrayOf(1))
        val list2 = createLinkedList(intArrayOf(2))
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(1, 2), linkedListToArray(result))
    }

    @Test
    fun testMergeTwoSortedListsReverseOrder() {
        // Test case: [2] + [1] = [1,2]
        // Expected: [1,2]
        val list1 = createLinkedList(intArrayOf(2))
        val list2 = createLinkedList(intArrayOf(1))
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(1, 2), linkedListToArray(result))
    }

    @Test
    fun testMergeTwoSortedListsAllFromFirstList() {
        // Test case: [1,2,3] + [4,5,6] = [1,2,3,4,5,6]
        // Expected: [1,2,3,4,5,6]
        val list1 = createLinkedList(intArrayOf(1, 2, 3))
        val list2 = createLinkedList(intArrayOf(4, 5, 6))
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5, 6), linkedListToArray(result))
    }

    @Test
    fun testMergeTwoSortedListsAllFromSecondList() {
        // Test case: [4,5,6] + [1,2,3] = [1,2,3,4,5,6]
        // Expected: [1,2,3,4,5,6]
        val list1 = createLinkedList(intArrayOf(4, 5, 6))
        val list2 = createLinkedList(intArrayOf(1, 2, 3))
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5, 6), linkedListToArray(result))
    }

    @Test
    fun testMergeTwoSortedListsDifferentLengths() {
        // Test case: [1,3,5,7] + [2,4] = [1,2,3,4,5,7]
        // Expected: [1,2,3,4,5,7]
        val list1 = createLinkedList(intArrayOf(1, 3, 5, 7))
        val list2 = createLinkedList(intArrayOf(2, 4))
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5, 7), linkedListToArray(result))
    }

    @Test
    fun testMergeTwoSortedListsWithDuplicates() {
        // Test case: [1,1,1] + [1,1,1] = [1,1,1,1,1,1]
        // Expected: [1,1,1,1,1,1]
        val list1 = createLinkedList(intArrayOf(1, 1, 1))
        val list2 = createLinkedList(intArrayOf(1, 1, 1))
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(1, 1, 1, 1, 1, 1), linkedListToArray(result))
    }

    @Test
    fun testMergeTwoSortedListsLargeNumbers() {
        // Test case: [100, 200, 300] + [50, 150, 250] = [50, 100, 150, 200, 250, 300]
        // Expected: [50, 100, 150, 200, 250, 300]
        val list1 = createLinkedList(intArrayOf(100, 200, 300))
        val list2 = createLinkedList(intArrayOf(50, 150, 250))
        val result = solution.mergeTwoLists(list1, list2)
        assertContentEquals(intArrayOf(50, 100, 150, 200, 250, 300), linkedListToArray(result))
    }
}

