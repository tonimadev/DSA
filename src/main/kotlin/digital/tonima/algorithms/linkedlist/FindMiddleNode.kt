package digital.tonima.algorithms.linkedlist

/**
 * Definition for singly-linked list node
 */
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    /**
     * Find the middle node of a linked list
     * Time Complexity: O(n) - single pass with two pointers
     * Space Complexity: O(1) - only using two pointers
     *
     * Algorithm: Two-pointer approach (slow and fast)
     * - Slow pointer moves 1 step at a time
     * - Fast pointer moves 2 steps at a time
     * - When fast reaches the end, slow is at the middle
     */
    fun middleNode(head: ListNode?): ListNode? {
        if (head == null) return null

        var slow: ListNode? = head
        var fast: ListNode? = head

        // Move fast pointer 2 steps and slow pointer 1 step
        while (fast != null && fast.next != null) {
            slow = slow?.next                // Move 1 step
            fast = fast.next?.next           // Move 2 steps
        }

        return slow
    }
}
