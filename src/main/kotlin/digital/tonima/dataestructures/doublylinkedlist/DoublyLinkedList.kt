package digital.tonima.dataestructures.doublylinkedlist

class DoublyLinkedList<T>() {
    private var head: Node? = null
    private var tail: Node? = null
    private var length: Int = 0

    constructor(value: T) : this() {
        append(value)
    }

    inner class Node(
        var value: T,
    ) {
        var next: Node? = null
        var previous: Node? = null

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
        if (index >= length || index < 0) return null
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


    fun set(index: Int, value: T): Boolean {
        get(index)?.let {
            it.value = value
            return true
        }
        return false
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
