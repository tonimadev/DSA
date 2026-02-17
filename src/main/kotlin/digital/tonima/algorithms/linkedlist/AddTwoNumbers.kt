package digital.tonima.algorithms.linkedlist

/**
 * LeetCode #2 - Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * Explanation: 9999999 + 9999 = 10009998
 *
 * Constraints:
 * - The number of nodes in each linked list is in the range [1, 100]
 * - 0 <= Node.val <= 9
 * - It is guaranteed that the list represents a number that does not have leading zeros
 */

class AddTwoNumbersSolution {
    /**
     * Add two numbers represented as reversed linked lists
     *
     * Algorithm: Digit-by-digit addition with carry
     * - Use two pointers to traverse both lists simultaneously
     * - Keep track of carry value (0 or 1)
     * - Sum corresponding digits plus carry
     * - Create new node with sum % 10
     * - Update carry as sum / 10
     * - Continue until both lists are exhausted and no carry remains
     *
     * Example process for l1=[2,4,3], l2=[5,6,4] (342 + 465):
     * - Step 1: 2 + 5 + 0 = 7, carry = 0 → [7]
     * - Step 2: 4 + 6 + 0 = 10, carry = 1 → [7,0]
     * - Step 3: 3 + 4 + 1 = 8, carry = 0 → [7,0,8]
     * - Result: [7,0,8] represents 807
     *
     * Time Complexity: O(max(n, m)) - where n and m are lengths of l1 and l2
     * - We visit each node once in a single pass
     * Space Complexity: O(max(n, m)) - for the result linked list
     * - The result list length is at most max(n, m) + 1
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var current = dummy
        var carry = 0

        var p1 = l1
        var p2 = l2

        while (p1 != null || p2 != null || carry != 0) {
            val sum = (p1?.`val` ?: 0) + (p2?.`val` ?: 0) + carry
            carry = sum / 10
            current.next = ListNode(sum % 10)
            current = current.next!!

            p1 = p1?.next
            p2 = p2?.next
        }

        return dummy.next
    }
}
