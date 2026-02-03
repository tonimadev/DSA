package digital.tonima.dataestructures.doublylinkedlist

class DoublyLinkedList<T>() { // removed Number & Comparable bounds to allow broader test coverage
    private var head: Node? = null
    private var tail: Node? = null
    private var length: Int = 0

    constructor(value: T) : this() {
        append(value)
    }

    /**
     * Node with immutable value following Data-Oriented Programming principles.
     * The value is immutable (val) for better reasoning about data.
     * The next and previous pointers remain mutable (var) for efficient list operations.
     * This is a pragmatic balance between immutability and performance.
     */
    inner class Node(
        val value: T,  // Immutable data
    ) {
        var next: Node? = null      // Mutable structure pointer
        var previous: Node? = null  // Mutable structure pointer

        override fun toString(): String {
            return "Value $value"
        }
    }

    // O(1) - constant operation since we have direct reference to tail
    fun append(value: T) {
        val newNode = Node(value)
        if (length == 0) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            newNode.previous = tail
            tail = newNode
        }
        length++
    }

    // O(1) - constant operation since we have direct reference to head
    fun prepend(value: T) {
        if (length == 0) {
            append(value)
            return
        }

        val newNode = Node(value)
        newNode.next = head
        head?.previous = newNode
        head = newNode
        length++
    }

    // O(1) - constant operation since we have direct reference to tail and its previous node
    fun removeLast(): Node? {
        if (length == 0) return null

        val removed = tail

        if (length == 1) {
            head = null
            tail = null
        } else {
            tail = tail?.previous
            tail?.next = null
        }

        length--
        return removed
    }

    // O(1) - constant operation since we have direct reference to head
    fun removeFirst(): Node? {
        if (length == 0) return null

        val removed = head

        if (length == 1) {
            head = null
            tail = null
        } else {
            head = head?.next
            head?.previous = null
        }

        length--
        return removed
    }

    // O(n) - linear operation, traverses from head to the target index
    // Optimization: traverses from the closest end (head or tail)
    fun get(index: Int): Node? {
        if (index !in 0..<length) return null
        var fetchNode: Node? = head

        if (index < length / 2) {
            // Traverse forward from head
            repeat(index) {
                fetchNode = fetchNode?.next
            }
        } else {
            // Traverse backward from tail
            fetchNode = tail
            repeat(length - (index + 1)) {
                fetchNode = fetchNode?.previous
            }
        }

        return fetchNode
    }

    // O(n) - linear operation due to get(index) call
    fun insert(index: Int, value: T): Boolean {
        // Validation
        if (index < 0 || index >= length) return false

        // Optimization: use prepend() for index 0
        if (index == 0) {
            prepend(value)
            return true
        }

        get(index)?.let { current ->
            val newNode = Node(value)

            // Link new node
            newNode.previous = current.previous
            newNode.next = current

            // Update previous node to point to new node
            current.previous?.next = newNode

            // Update current node
            current.previous = newNode


            length++
            return true
        }
        return false
    }

    // O(n) - linear due to get(index), but O(1) for first/last elements
    fun remove(index: Int): Node? {
        // Validation
        if (index < 0 || index >= length) return null

        // Optimization: use specialized methods for edge cases
        if (index == 0) return removeFirst()
        if (index == length - 1) return removeLast()

        // Remove from middle
        return get(index)?.apply {
            // Update links
            next?.previous = previous
            previous?.next = next

            // Clear removed node references
            this.next = null
            this.previous = null

            length--
        }
    }

    fun reverse() {
        if (length <= 1) return
        var previous: Node? = null
        var current = head

        while (current != null) {
            val next = current.next
            current.next = previous
            current.previous = next
            previous = current
            current = next
        }
        val temp = head
        head = tail
        tail = temp
    }

    fun partitionList(value: T) {
        if (length <= 1) return

        var current = head
        var lessHead: Node? = null
        var lessTail: Node? = null
        var greaterHead: Node? = null
        var greaterTail: Node? = null

        while (current != null) {
            val next = current.next
            // detach from current chain
            current.next = null
            current.previous = null

            val goesToLess = try {
                @Suppress("UNCHECKED_CAST")
                val cmp = (current.value as Comparable<T>).compareTo(value)
                cmp < 0
            } catch (_: ClassCastException) {
                // If values are not comparable, keep original relative order by sending to 'greaterOrEqual'
                false
            }

            if (goesToLess) {
                if (lessHead == null) {
                    lessHead = current
                    lessTail = current
                } else {
                    lessTail?.next = current
                    current.previous = lessTail
                    lessTail = current
                }
            } else {
                if (greaterHead == null) {
                    greaterHead = current
                    greaterTail = current
                } else {
                    greaterTail?.next = current
                    current.previous = greaterTail
                    greaterTail = current
                }
            }
            current = next
        }

        // Connect partitions and update head/tail
        if (lessHead != null) {
            head = lessHead
            if (greaterHead != null) {
                lessTail?.next = greaterHead
                greaterHead.previous = lessTail
                tail = greaterTail
            } else {
                tail = lessTail
            }
        } else {
            head = greaterHead
            tail = greaterTail
        }
    }



    // O(n) - linear operation since we traverse up to the index position
    // Note: Since Node.value is immutable, we need to replace the entire node
    fun set(index: Int, value: T): Boolean {
        if (index !in 0..<length) return false

        val oldNode = get(index) ?: return false
        val newNode = Node(value)

        // Update links
        newNode.next = oldNode.next
        newNode.previous = oldNode.previous

        // Update neighbors
        if (oldNode.previous != null) {
            oldNode.previous!!.next = newNode
        } else {
            head = newNode
        }

        if (oldNode.next != null) {
            oldNode.next!!.previous = newNode
        } else {
            tail = newNode
        }

        return true
    }

    fun getHead() {
        println("Head points to ${head?.value}")
    }

    fun getTail() {
        println("Tail points to ${tail?.value}")
    }

    fun getLength() {
        println("Length is $length")
    }

    fun printList() {
        println("Items:")
        var currentNode = head
        while (currentNode != null) {
            println("previous: ${currentNode.previous} - value: ${currentNode.value}" + " - next: ${currentNode.next?.value}")
            currentNode = currentNode.next
        }
    }
}
