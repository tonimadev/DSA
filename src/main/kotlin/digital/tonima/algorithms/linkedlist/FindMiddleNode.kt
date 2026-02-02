package digital.tonima.algorithms.linkedlist

/**
 * LeetCode #876 - Middle of the Linked List
 * https://leetcode.com/problems/middle-of-the-linked-list/
 *
 * Given the head of a singly linked list, return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3
 *
 * Example 2:
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes (3 and 4), we return the second one
 *
 * Time Complexity: O(n) - single pass with two pointers
 * Space Complexity: O(1) - only using two pointers
 */

/**
 * Definition for singly-linked list node
 */
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class FindMiddleNodeSolution {
    /**
     * Find the middle node of a linked list
     *
     * Example process for [1,2,3,4,5]:
     * - Initial: slow=1, fast=1
     * - Step 1: slow=2, fast=3
     * - Step 2: slow=3, fast=5
     * - Step 3: fast.next=null, return slow (node 3)
     *
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
