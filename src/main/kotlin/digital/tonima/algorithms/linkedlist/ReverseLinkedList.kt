package digital.tonima.algorithms.linkedlist

/**
 * LeetCode #206 - Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 *
 * Example 3:
 * Input: head = []
 * Output: []
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 5000]
 * - -5000 <= Node.val <= 5000
 */

class ReverseLinkedListSolution {
    /**
     * Reverse a singly linked list iteratively
     *
     * Time Complexity: O(n) - Single pass through the list, visiting each node once
     * Space Complexity: O(1) - Only using three pointers (previous, current, next)
     *
     * Algorithm: Iterative three-pointer approach
     * 1. Initialize previous as null (will become the new tail)
     * 2. Iterate through the list:
     *    - Save the next node before breaking the link
     *    - Reverse the current node's pointer to point to previous
     *    - Move previous to current (shift window forward)
     *    - Move current to next (shift window forward)
     * 3. Return previous (which is now the new head)
     *
     * Example visualization for [1,2,3]:
     * Initial:     1 -> 2 -> 3 -> null
     * Step 1:      null <- 1    2 -> 3 -> null
     * Step 2:      null <- 1 <- 2    3 -> null
     * Step 3:      null <- 1 <- 2 <- 3
     * Return: 3 (new head)
     */
    fun reverseList(head: ListNode?): ListNode? {
        if (head == null || head.next == null) return head

        // Head
        // [1] -> [2] -> [3] -> null
        //Prev - Current - Next
        //null <- [1] <- [2] <- [3]
        //       Prev - Current - Next
        //null <- [1] <- [2] <- [3]
        //              Prev - Current[new head] - Next Null
        //null <- [1] <- [2] <- [3]

        var current = head
        var previous: ListNode? = null
        while (current != null) {
            val next = current.next
            current.next = previous
            previous = current
            current = next
        }
        return previous
    }
}
