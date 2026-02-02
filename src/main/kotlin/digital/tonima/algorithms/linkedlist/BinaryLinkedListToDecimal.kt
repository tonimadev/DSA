package digital.tonima.algorithms.linkedlist

/**
 * LeetCode #1290 - Convert Binary Number in a Linked List to Integer
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 *
 * Given head which is a reference node to a singly-linked list. The value of each node
 * in the linked list is either 0 or 1. The linked list holds the binary representation
 * of a number. Return the decimal value of the number in the linked list.
 *
 * Example 1:
 * Input: head = [1,0,1]
 * Output: 5
 * Explanation: (101) in base 2 = (5) in base 10
 *
 * Example 2:
 * Input: head = [0]
 * Output: 0
 *
 * Example 3:
 * Input: head = [1]
 * Output: 1
 *
 * Example 4:
 * Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
 * Output: 18880
 * Explanation: (100100111000000) in base 2 = (18880) in base 10
 */
class BinaryLinkedListToDecimal {
    /**
     * Converts a binary number represented as a linked list to its decimal value
     *
     * Example process for [1,0,1]:
     * - Start: num = 0
     * - Node 1 (val=1): num = (0 << 1) | 1 = 0 | 1 = 1
     * - Node 2 (val=0): num = (1 << 1) | 0 = 2 | 0 = 2
     * - Node 3 (val=1): num = (2 << 1) | 1 = 4 | 1 = 5
     * - Result: 5
     *
     * Time Complexity: O(n) - single pass through the linked list where n is the number of nodes
     * Space Complexity: O(1) - only using constant extra space
     *
     * Algorithm: Bit manipulation approach
     * - Iterate through each node in the linked list
     * - Shift the current result left by 1 (multiply by 2)
     * - Add the current bit value using bitwise OR
     */
    fun getDecimalValue(head: ListNode?): Int {
        var num = 0
        var current = head

        while (current != null) {
            num = (num shl 1) or current.`val`

            current = current.next
        }

        return num
    }
}
