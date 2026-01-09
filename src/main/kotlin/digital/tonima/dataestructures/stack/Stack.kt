package digital.tonima.dataestructures.stack

class Stack<T>(val value: T) {
    private var top: Node? = null
    private var height: Int = 0

    init {
        val newNode = Node(value = value)
        top = newNode
        height = 1
    }

    inner class Node(val value: T) {
        var next: Node? = null
    }

    fun push(value: T) {
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


}
