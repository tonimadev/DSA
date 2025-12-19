package digital.tonima.algorithms.linkedlist

/**
 * Definition for singly-linked list node
 */

class HasCycleSolution {
    /**
     * Find the middle node of a linked list
     * Time Complexity: O(n) - single pass with two pointers
     * Space Complexity: O(1) - only using two pointers
     *
     * Algorithm: Two-pointer approach (slow and fast)
     * - a Slow pointer moves 1 step at a time
     * - a Fast pointer moves 2 steps at a time
     * - When fast reaches the end, slow is at the middle
     */
    fun hasCycle(head: ListNode?): Boolean? {
        if (head == null) return false

        var slow: ListNode? = head
        var fast: ListNode? = head

        // Move fast pointer 2 steps and slow pointer 1 step
        while (fast != null && fast.next != null) {
            slow = slow?.next                // Move 1 step
            fast = fast.next?.next           // Move 2 steps
            if (slow == fast) return true
        }

return false
    }
}
