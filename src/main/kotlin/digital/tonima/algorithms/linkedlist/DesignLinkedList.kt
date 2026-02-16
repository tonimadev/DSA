package digital.tonima.algorithms.linkedlist

/**
 * LeetCode #707 - Design Linked List
 * https://leetcode.com/problems/design-linked-list/
 */
class MyLinkedList() {

    var length = 0
    var head: Node? = null
    var tail: Node? = null

    inner class Node(
        val `val`: Int,
        var previous: Node? = null,
        var next: Node? = null,
    )

    // Time: O(n) worst-case, Space: O(1)
    fun get(index: Int): Int {
        if (length == 0) return -1
        if (index !in 0 until length) return -1
        var fetchNode: Node? = head

        if (index < length / 2) {
            repeat(index) {
                fetchNode = fetchNode?.next
            }
        } else {
            fetchNode = tail
            repeat(length - (index + 1)) {
                fetchNode = fetchNode?.previous
            }
        }

        return fetchNode!!.`val`
    }

    // Time: O(n) worst-case, Space: O(1)
    fun getNode(index: Int): Node? {
        if (length == 0) return null
        if (index !in 0 until length) return null
        var fetchNode: Node? = head

        if (index < length / 2) {
            repeat(index) {
                fetchNode = fetchNode?.next
            }
        } else {
            fetchNode = tail
            repeat(length - (index + 1)) {
                fetchNode = fetchNode?.previous
            }
        }

        return fetchNode
    }

    // Time: O(1), Space: O(1)
    fun addAtHead(`val`: Int) {
        val newNode = Node(`val`)
        if (length == 0) {
            head = newNode
            tail = newNode
        } else {
            newNode.next = head
            head?.previous = newNode
            head = newNode

        }
        length++

    }

    // Time: O(1), Space: O(1)
    fun addAtTail(`val`: Int) {
        val newNode = Node(`val`)
        if (length == 0) {
            head = newNode
            tail = newNode
        } else {
            newNode.previous = tail
            tail?.next = newNode
            tail = newNode
        }
        length++
    }

    // Time: O(n) worst-case (index traversal), Space: O(1)
    fun addAtIndex(index: Int, `val`: Int) {
        if (index !in 0..length) return
        when (index) {
            0 -> {
                addAtHead(`val`)
            }

            length -> {
                addAtTail(`val`)
            }

            else -> {
                val newNode = Node(`val`)
                val previousAtIndex = getNode(index)

                newNode.next = previousAtIndex
                newNode.previous = previousAtIndex?.previous
                previousAtIndex?.previous?.next = newNode
                previousAtIndex?.previous = newNode

                length++
            }
        }
    }

    // Time: O(n) worst-case (index traversal), Space: O(1)
    fun deleteAtIndex(index: Int) {
        getNode(index)?.let { deletingNode ->
            val prev = deletingNode.previous
            val next = deletingNode.next

            if (prev == null) {
                head = next
            } else {
                prev.next = next
            }

            if (next == null) {
                tail = prev
            } else {
                next.previous = prev
            }

            deletingNode.next = null
            deletingNode.previous = null

            length--
            if (length == 0) {
                head = null
                tail = null
            }
        }

    }

}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * var obj = MyLinkedList()
 * var param_1 = obj.get(index)
 * obj.addAtHead(`val`)
 * obj.addAtTail(`val`)
 * obj.addAtIndex(index,`val`)
 * obj.deleteAtIndex(index)
 */
