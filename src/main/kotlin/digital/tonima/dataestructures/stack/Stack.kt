package digital.tonima.dataestructures.stack

class Stack<T>(val value: T? = null) {
    private var top: Node? = null
    private var height: Int = 0

    init {
        val newNode = Node(value = value)
        top = newNode
        height = 1
    }

    /**
     * Node with immutable value following Data-Oriented Programming principles.
     * The value is immutable (val) for better reasoning about data.
     * The next pointer remains mutable (var) for efficient stack operations.
     */
    inner class Node(val value: T?) {  // Immutable data
        var next: Node? = null  // Mutable structure pointer
    }

    fun push(value: T?) {
        val newNode = Node(value)
        newNode.next = top
        top = newNode
        height++
    }

    fun pop(): Node? {
        if (height == 0) return null
        val temp = top
        top = temp?.next
        temp?.next = null
        height--
        return temp
    }

    fun peek(): T? = top?.value

    fun printStack() {
        var temp = top
        while (temp != null) {
            println(temp.value)
            temp = temp.next
        }
    }

    fun getTop() {
        println("Top: ${top?.value}")
    }

    fun getHeight() {
        println("Height: $height")
    }

    fun isEmpty() = height == 0
}
