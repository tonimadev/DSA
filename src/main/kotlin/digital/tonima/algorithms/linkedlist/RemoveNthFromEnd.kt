package digital.tonima.algorithms.linkedlist

/**
 * LeetCode #19 - Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 * Constraints:
 * - The number of nodes in the list is sz.
 * - 1 <= sz <= 30
 * - 0 <= Node.val <= 100
 * - 1 <= n <= sz
 */

/**
 * Definition for singly-linked list node
 */
// Using the same ListNode definition as other LeetCode problems
// class ListNode(var `val`: Int) {
//     var next: ListNode? = null
// }

class RemoveNthFromEndSolution {
    /**
     * Find the kth node from the end of a linked list
     * Time Complexity: O(n) - single pass with two pointers
     * Space Complexity: O(1) - only using two pointers
     *
     * Algorithm: Two-pointer approach (slow and fast)
     * - Move fast pointer k steps ahead
     * - Then move both pointers until fast reaches the end
     * - When fast reaches null, slow is at the kth node from end
     *
     * @param head The head of the linked list
     * @param k The position from the end (1-indexed)
     * @return The kth node from the end, or null if k is invalid
     */
    fun findKthFromEnd(head: ListNode?, k: Int): ListNode? {
        if (head == null || k <= 0) return null

        var slow: ListNode? = head
        var fast: ListNode? = head

        // 1. Move the 'fast' pointer k steps ahead
        repeat(k) {
            // If k is greater than the list size, return null
            if (fast == null) return null
            fast = fast.next
        }

        // 2. Move both pointers until 'fast' reaches the end
        // When 'fast' reaches null, 'slow' will be exactly at the kth from end
        while (fast != null) {
            slow = slow?.next
            fast = fast.next
        }

        return slow
    }

    /**
     * Remove the nth node from the end of the list
     * This is the actual LeetCode #19 solution
     *
     * @param head The head of the linked list
     * @param n The position from the end to remove (1-indexed)
     * @return The head of the modified list
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // Create a dummy node to handle edge cases (like removing the head)
        val dummy = ListNode(0)
        dummy.next = head

        var slow: ListNode? = dummy
        var fast: ListNode? = dummy

        // Move fast pointer n+1 steps ahead
        // (n+1 because we need slow to be at the node BEFORE the one to remove)
        repeat(n + 1) {
            fast = fast?.next
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            slow = slow?.next
            fast = fast.next
        }

        // Remove the nth node from end
        slow?.next = slow.next?.next

        return dummy.next
    }
}

