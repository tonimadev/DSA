package digital.tonima.algorithms.linkedlist

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Test cases for LeetCode #234 - Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * Given the head of a singly linked list, return true if the linked list is a palindrome,
 * and false otherwise.
 */
class PalindromeLinkedListTest {

    private val solution = PalindromeLinkedListSolution()

    // Helper function to create a linked list from an array
    private fun createLinkedList(values: IntArray): PalindromeLinkedListSolution.ListNode? {
        if (values.isEmpty()) return null
        val head = PalindromeLinkedListSolution.ListNode(values[0])
        var current = head
        for (i in 1 until values.size) {
            current.next = PalindromeLinkedListSolution.ListNode(values[i])
            current = current.next!!
        }
        return head
    }

    @Test
    fun `test isPalindrome - LeetCode Example 1 - even length palindrome`() {
        // Input: head = [1,2,2,1]
        // Output: true
        // Explanation: The list reads the same forward and backward
        val head = createLinkedList(intArrayOf(1, 2, 2, 1))
        assertTrue(solution.isPalindrome(head), "List [1,2,2,1] should be a palindrome")
    }

    @Test
    fun `test isPalindrome - LeetCode Example 2 - not palindrome`() {
        // Input: head = [1,2]
        // Output: false
        // Explanation: 1 != 2, so it's not a palindrome
        val head = createLinkedList(intArrayOf(1, 2))
        assertFalse(solution.isPalindrome(head), "List [1,2] should not be a palindrome")
    }

    @Test
    fun `test isPalindrome - LeetCode Example 3 - false at end`() {
        // Input: head = [9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,1]
        // Output: false
        val head = createLinkedList(intArrayOf(9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1))
        assertFalse(solution.isPalindrome(head), "List should not be a palindrome")
    }

    @Test
    fun `test isPalindrome - single element`() {
        // Input: head = [1]
        // Output: true
        // Explanation: A single element is always a palindrome
        val head = createLinkedList(intArrayOf(1))
        assertTrue(solution.isPalindrome(head), "Single element list should be a palindrome")
    }

    @Test
    fun `test isPalindrome - two identical elements`() {
        // Input: head = [5,5]
        // Output: true
        val head = createLinkedList(intArrayOf(5, 5))
        assertTrue(solution.isPalindrome(head), "List [5,5] should be a palindrome")
    }

    @Test
    fun `test isPalindrome - two different elements`() {
        // Input: head = [1,5]
        // Output: false
        val head = createLinkedList(intArrayOf(1, 5))
        assertFalse(solution.isPalindrome(head), "List [1,5] should not be a palindrome")
    }

    @Test
    fun `test isPalindrome - odd length palindrome`() {
        // Input: head = [1,2,3,2,1]
        // Output: true
        // Explanation: The list reads the same forward and backward
        val head = createLinkedList(intArrayOf(1, 2, 3, 2, 1))
        assertTrue(solution.isPalindrome(head), "List [1,2,3,2,1] should be a palindrome")
    }

    @Test
    fun `test isPalindrome - odd length not palindrome`() {
        // Input: head = [1,2,3,4,5]
        // Output: false
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
        assertFalse(solution.isPalindrome(head), "List [1,2,3,4,5] should not be a palindrome")
    }

    @Test
    fun `test isPalindrome - longer even palindrome`() {
        // Input: head = [1,0,2,0,1]
        // Output: true
        val head = createLinkedList(intArrayOf(1, 0, 2, 0, 1))
        assertTrue(solution.isPalindrome(head), "List [1,0,2,0,1] should be a palindrome")
    }

    @Test
    fun `test isPalindrome - all same elements`() {
        // Input: head = [7,7,7,7,7]
        // Output: true
        val head = createLinkedList(intArrayOf(7, 7, 7, 7, 7))
        assertTrue(solution.isPalindrome(head), "List with all same elements should be a palindrome")
    }

    @Test
    fun `test isPalindrome - almost palindrome`() {
        // Input: head = [1,2,2,2]
        // Output: false
        val head = createLinkedList(intArrayOf(1, 2, 2, 2))
        assertFalse(solution.isPalindrome(head), "List [1,2,2,2] should not be a palindrome")
    }

    @Test
    fun `test isPalindrome - long palindrome`() {
        // Input: head = [1,2,3,4,5,4,3,2,1]
        // Output: true
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5, 4, 3, 2, 1))
        assertTrue(solution.isPalindrome(head), "List [1,2,3,4,5,4,3,2,1] should be a palindrome")
    }

    @Test
    fun `test isPalindrome - palindrome with zeros`() {
        // Input: head = [0,0,0,0,0]
        // Output: true
        val head = createLinkedList(intArrayOf(0, 0, 0, 0, 0))
        assertTrue(solution.isPalindrome(head), "List with all zeros should be a palindrome")
    }

    @Test
    fun `test isPalindrome - large numbers palindrome`() {
        // Input: head = [9,9,9,9]
        // Output: true
        val head = createLinkedList(intArrayOf(9, 9, 9, 9))
        assertTrue(solution.isPalindrome(head), "List [9,9,9,9] should be a palindrome")
    }

    @Test
    fun `test isPalindrome - not palindrome fail at first comparison`() {
        // Input: head = [2,1]
        // Output: false
        val head = createLinkedList(intArrayOf(2, 1))
        assertFalse(solution.isPalindrome(head), "List [2,1] should not be a palindrome")
    }

    @Test
    fun `test isPalindrome - complex pattern`() {
        // Input: head = [1,2,3,3,2,1]
        // Output: true
        val head = createLinkedList(intArrayOf(1, 2, 3, 3, 2, 1))
        assertTrue(solution.isPalindrome(head), "List [1,2,3,3,2,1] should be a palindrome")
    }

    @Test
    fun `test isPalindrome - false in middle`() {
        // Input: head = [1,2,3,4,2,1]
        // Output: false
        val head = createLinkedList(intArrayOf(1, 2, 3, 4, 2, 1))
        assertFalse(solution.isPalindrome(head), "List [1,2,3,4,2,1] should not be a palindrome")
    }
}

