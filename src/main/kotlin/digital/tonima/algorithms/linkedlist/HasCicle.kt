package digital.tonima.algorithms.linkedlist

/**
 * LeetCode #141 - Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached
 * again by continuously following the next pointer.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1 (cycle at node with value 2)
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed)
 *
 * Example 2:
 * Input: head = [1,2], pos = 0 (cycle at node with value 1)
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node
 *
 * Example 3:
 * Input: head = [1], pos = -1 (no cycle)
 * Output: false
 * Explanation: There is no cycle in the linked list
 *
 * Time Complexity: O(n) - where n is the number of nodes in the list
 * Space Complexity: O(1) - only using two pointers
 */

/**
 * Definition for singly-linked list node
 */

class HasCycleSolution {
    /**
     * Detects if a linked list has a cycle using Floyd's Cycle Detection Algorithm (Tortoise and Hare)
     * Time Complexity: O(n) - single pass with two pointers
     * Space Complexity: O(1) - only using two pointers
     *
     * Algorithm: Two-pointer approach (slow and fast)
     * - Slow pointer moves 1 step at a time
     * - Fast pointer moves 2 steps at a time
     * - If there's a cycle, fast will eventually meet slow
     * - If there's no cycle, fast will reach the end (null)
     */
    fun hasCycle(head: ListNode?): Boolean {
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
