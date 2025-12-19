package digital.tonima.dataestructures.linkedlist

class LinkedList<T : Any?> constructor() {

    private var head: Node? = null
    private var tail: Node? = null
    private var length: Int = 0

    val size: Int
        get() = length

    constructor(value: T) : this() {
        append(value)
    }

    inner class Node(
        var value: T,
    ) {
        var next: Node? = null

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
        var current = head
        var previous = head
        while (current?.next != null) {
            previous = current
            current = current.next
        }
        tail = previous
        tail?.next = null
        length--
        if (length == 0) {
            head = null
            tail = null
        }
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
        if (index < 0 || index >= length) return null
        var currentNode = head
        repeat(index) {
            currentNode = currentNode?.next
        }
        return currentNode
    }

    // O(n) - linear operation since we traverse up to the index position
    fun set(index: Int, value: T): Boolean {
        get(index)?.let {
            it.value = value
            return true
        }
        return false
    }

    // O(n) - linear operation since we may traverse up to the index position using get()
    fun insert(index: Int, value: T): Boolean {
        if (index < 0 || index > length) return false
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
        if (index < 0 || index >= length) return null
        if (index == 0) return removeFirst()
        if (index == length - 1) return removeLast()
        val previousNode = get(index - 1)
        val removedNode = previousNode?.next
        previousNode?.next = removedNode?.next
        removedNode?.next = null
        length--
        return removedNode

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

    fun printList() {
        println("Items:")
        var currentNode = head
        while (currentNode != null) {
            println("${currentNode.value}" + " next value: ${currentNode.next?.value}")
            currentNode = currentNode.next
        }
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
