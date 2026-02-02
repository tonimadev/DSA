package digital.tonima.algorithms.linkedlist

/**
 * LeetCode #234 - Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * Given the head of a singly linked list, return true if the linked list is a palindrome,
 * and false otherwise.
 *
 * A palindrome is a sequence that reads the same forward and backward.
 *
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 *
 * Example 3:
 * Input: head = [9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,1]
 * Output: false
 *
 * Constraints:
 * - The number of nodes in the list is in the range [1, 10^5]
 * - 0 <= Node.val <= 9
 */

class PalindromeLinkedListSolution {
    /**
     * Definition for singly-linked list node
     */
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
     * Determine if a singly linked list is a palindrome
     *
     * Example process for head=[1,2,2,1]:
     * - Find middle: slow at node 2 (first 2)
     * - Reverse second half: [2,1] becomes [1,2]
     * - Compare: 1==1, 2==2 -> true
     *
     * Example process for head=[1,2,3]:
     * - Find middle: slow at node 2
     * - Reverse second half: [3] stays [3]
     * - Compare: 1≠3 -> false
     *
     * Time Complexity: O(n) - Three passes: find middle O(n/2), reverse O(n/2), compare O(n/2)
     * Space Complexity: O(1) - Only using pointers, no additional data structures
     *
     * Algorithm:
     * 1. Use slow and fast pointer technique to find the middle
     * 2. Reverse the second half of the list
     * 3. Compare values from both halves
     *
     * @param head the head of the singly linked list
     * @return true if the list is a palindrome, false otherwise
     */
    fun isPalindrome(head: ListNode?): Boolean {
        if (head?.next == null) return true

        // Find the middle of the list using slow and fast pointers
        var slow = head
        var fast = head

        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        // Reverse the second half of the list
        val secondHalf = reverseList(slow?.next)
        var firstHalf = head

        // Compare both halves
        var temp = secondHalf
        while (temp != null) {
            if (firstHalf?.`val` != temp.`val`) {
                return false
            }
            firstHalf = firstHalf.next
            temp = temp.next
        }

        return true
    }

    /**
     * Helper function to reverse a linked list
     * Time Complexity: O(n) - single pass through the list
     * Space Complexity: O(1) - only using pointers
     */
    private fun reverseList(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var current = head

        while (current != null) {
            val next = current.next
            current.next = prev
            prev = current
            current = next
        }

        return prev
    }
}

