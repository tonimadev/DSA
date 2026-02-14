package digital.tonima.algorithms.linkedlist

/**
 * LeetCode #21 - Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Merges two sorted linked lists into one sorted linked list.
 *
 * Example:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 *
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
class MergeTwoSortedLinkedListsSolution {
    /**
     * Merge two sorted linked lists into a single sorted linked list.
     *
     * Algorithm: Two-pointer approach with dummy node
     * - Use two pointers (p1, p2) to traverse both lists
     * - Compare values and append the smaller node to the result
     * - Append remaining nodes from the non-empty list
     *
     * Time Complexity: O(n + m) where n and m are the lengths of list1 and list2
     * - We visit each node exactly once
     * Space Complexity: O(1) - only using pointers, no extra space needed
     */
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {

        val dummy = ListNode(-1)

        var current = dummy

        var p1 = list1
        var p2 = list2

        while (p1 != null && p2 != null) {
            if (p1.`val` <= p2.`val`) {
                current.next = p1
                p1 = p1.next
            } else {
                current.next = p2
                p2 = p2.next
            }
            current = current.next!!
        }

        if (p1 != null) {
            current.next = p1
        } else if (p2 != null) {
            current.next = p2
        }

        return dummy.next
    }
}
