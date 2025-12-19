class Solution {
    fun reverseList(head: ListNode?): ListNode? {
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

    /**
     * LeetCode #19 - Remove Nth Node From End of List
     * Given the head of a linked list, remove the nth node from the end of the list and return its head.
     *
     * Example:
     * Input: head = [1,2,3,4,5], n = 2
     * Output: [1,2,3,5]
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head

        var fast: ListNode? = dummy
        var slow: ListNode? = dummy

        // Avançar fast n+1 posições
        for (i in 0..n) {
            fast = fast?.next
        }

        // Mover ambos até fast chegar ao final
        while (fast != null) {
            fast = fast.next
            slow = slow?.next
        }

        // Remover o nó
        slow?.next = slow?.next?.next

        return dummy.next
    }
}
