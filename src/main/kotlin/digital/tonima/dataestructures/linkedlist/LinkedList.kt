package digital.tonima.dataestructures.linkedlist

class LinkedList<T>() {

    private var head: Node? = null
    private var tail: Node? = null
    private var length: Int = 0

    val size: Int
        get() = length

    constructor(value: T) : this() {
        append(value)
    }

    /**
     * Node with immutable value following Data-Oriented Programming principles.
     * The value is immutable (val) for better reasoning about data.
     * The next pointer remains mutable (var) for efficient list operations.
     * This is a pragmatic balance between immutability and performance.
     */
    inner class Node(
        val value: T,  // Immutable data
    ) {
        var next: Node? = null  // Mutable structure pointer

        override fun toString(): String {
            return "Value $value - Next ${next?.value}"
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
            tail = newNode
        }
        length++
    }

    // O(1) - constant operation since we have direct reference to head
    fun prepend(value: T) {
        val newNode = Node(value)
        if (length == 0) {
            head = newNode
            tail = newNode
        } else {
            newNode.next = head
            head = newNode
        }
        length++
    }

    // O(n) - linear operation since we need to traverse the list to find the second-to-last node
    fun removeLast(): Node? {
        if (length == 0) return null

        // Special case: single element in the list
        if (length == 1) {
            val removed = head
            head = null
            tail = null
            length--
            return removed
        }

        // Traverse to find second-to-last node
        var current = head
        var previous: Node? = null
        while (current?.next != null) {
            previous = current
            current = current.next
        }

        // Remove the last node
        tail = previous
        tail?.next = null
        length--
        return current

    }

    // O(1) - constant operation since we only modify the head pointer
    fun removeFirst(): Node? {
        if (length == 0) return null
        val removedNode = head
        head = removedNode?.next
        removedNode?.next = null
        length--
        if (length == 0) tail = null
        return removedNode
    }

    // O(n) - linear operation since we traverse up to the index position
    fun get(index: Int): Node? {
        if (index !in 0..<length) return null
        var currentNode = head
        repeat(index) {
            currentNode = currentNode?.next
        }
        return currentNode
    }

    // O(n) - linear operation since we traverse up to the index position
    // Note: Since Node.value is immutable, we need to replace the entire node
    fun set(index: Int, value: T): Boolean {
        if (index !in 0..<length) return false

        if (index == 0) {
            val oldHead = head
            val newHead = Node(value)
            newHead.next = oldHead?.next
            head = newHead
            if (length == 1) {
                tail = head
            }
            return true
        }

        val previous = get(index - 1) ?: return false
        val oldNode = previous.next ?: return false
        val newNode = Node(value)
        newNode.next = oldNode.next
        previous.next = newNode

        if (index == length - 1) {
            tail = newNode
        }

        return true
    }

    // O(n) - linear operation since we may traverse up to the index position using get()
    fun insert(index: Int, value: T): Boolean {
        if (index !in 0..length) return false
        if (index == 0) {
            prepend(value)
            return true
        }
        if (index == length) {
            append(value)
            return true
        }
        val newNode = Node(value)
        val previousNode = get(index - 1)
        newNode.next = previousNode?.next
        previousNode?.next = newNode
        length++
        return true
    }

    // O(n) - linear operation since we may traverse up to the index position using get()
    fun remove(index: Int): Node? {
        if (index !in 0..<length) return null
        if (index == 0) return removeFirst()
        if (index == length - 1) return removeLast()
        val previousNode = get(index - 1)
        val removedNode = previousNode?.next
        previousNode?.next = removedNode?.next
        removedNode?.next = null
        length--
        return removedNode

    }

    /**
     * Remove element by value (not index)
     * Time Complexity: O(n) - may traverse entire list
     * Space Complexity: O(1)
     */
    fun removeByValue(value: T): Boolean {
        // Case 1: First element
        if (head?.value == value) {
            removeFirst()
            return true
        }

        // Case 2: Search in middle or end
        var current = head
        var previous: Node? = null

        while (current != null) {
            if (current.value == value) {
                // Found it, unlink from chain
                previous?.next = current.next
                if (current == tail) {
                    tail = previous
                }
                length--
                return true
            }
            previous = current
            current = current.next
        }

        return false  // Not found
    }

    // O(n) - linear operation since we traverse the entire list once to reverse pointers
    fun reverse() {
        var currentNode = head
        head = tail
        tail = currentNode

        var nextNode: LinkedList<T>.Node?
        var previousNode: Node? = null

        repeat(length) {
            nextNode = currentNode?.next
            currentNode?.next = previousNode
            previousNode = currentNode
            currentNode = nextNode
        }
    }

    fun findKthFromEnd(k: Int): Node? {
        if (head == null || k <= 0) return null

        var slow: Node? = head
        var fast: Node? = head

        // 1. Move o ponteiro 'fast' k passos à frente
        repeat(k) {
            // Se k for maior que o tamanho da lista, retorna null
            if (fast == null) return null
            fast = fast.next
        }

        // 2. Move ambos os ponteiros até que 'fast' chegue ao fim
        // Quando 'fast' chegar no null, 'slow' estará exatamente no k-ésimo do fim
        while (fast != null) {
            slow = slow?.next
            fast = fast.next
        }

        return slow
    }

    fun removeDuplicates() {
        val values = mutableSetOf<T>()
        var previous: Node? = null
        var current = head

        while (current != null) {
            if (values.contains(current.value)) {
                previous?.next = current.next
                length--
            } else {
                values.add(current.value)
                previous = current
            }
            current = current.next
        }

        // Atualiza tail caso o último elemento tenha sido removido
        tail = previous
    }


    fun printList() {
        println("Items:")
        var currentNode = head
        while (currentNode != null) {
            println("${currentNode.value}" + " next value: ${currentNode.next?.value}")
            currentNode = currentNode.next
        }
    }

    fun LinkedList<Int>.binaryToDecimal(): Int {
        if (head == null) return 0

        var num = 0
        var current = head

        while (current != null) {
            // Multiplica o que já temos por 2 e soma o bit atual
            num = (num * 2) + current.value


            // Move para o próximo nó (evita o loop infinito)
            current = current.next
        }

        return num
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

    fun printAll() {
        println("LinkedList Info:")
        printList()
        getHead()
        getTail()
        getLength()
    }
}
