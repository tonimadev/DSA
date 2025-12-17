package digital.tonima.dataestructures

class LinkedList<T : Any?>(
    private val value: T
) {
    private var head: Node? = null
    private var tail: Node? = null
    private var length: Int = 0

    init {
        val node = Node(value)
        head = node
        tail = node
        length = 1
    }

    inner class Node(
        val value: T,
    ) {
        var next: Node? = null
    }

    fun printList() {
        var temp = head
        while (temp != null) {
            println(temp.value)
            temp = temp.next
        }
    }

    fun getHead(){
        println("Head ${head?.value}")
    }
    fun getTail() {
        println("Tail ${tail?.value}")
    }
    fun getLength(){
        println("Length $length")
    }
}
