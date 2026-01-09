package digital.tonima.dataestructures.doublylinkedlist

// Helper functions used by DoublyLinkedListTest to inspect lists without exposing internals

fun <T> getLength(list: DoublyLinkedList<T>): Int {
    var i = 0
    while (list.get(i) != null) i++
    return i
}

fun <T> getHeadValue(list: DoublyLinkedList<T>): T? = list.get(0)?.value

fun <T> getTailValue(list: DoublyLinkedList<T>): T? {
    val len = getLength(list)
    if (len == 0) return null
    return list.get(len - 1)?.value
}

fun <T> getNextValue(list: DoublyLinkedList<T>, value: T): T? {
    var i = 0
    while (true) {
        val node = list.get(i) ?: return null
        if (node.value == value) return node.next?.value
        i++
    }
}

fun getNextStringValue(list: DoublyLinkedList<String>, value: String): String? = getNextValue(list, value)

fun <T> getPreviousValue(list: DoublyLinkedList<T>, value: T): T? {
    var i = 0
    while (true) {
        val node = list.get(i) ?: return null
        if (node.value == value) return node.previous?.value
        i++
    }
}

fun <T> getNextOfTail(list: DoublyLinkedList<T>): T? {
    val len = getLength(list)
    if (len == 0) return null
    return list.get(len - 1)?.next?.value
}

fun <T> getPreviousOfHead(list: DoublyLinkedList<T>): T? = list.get(0)?.previous?.value

fun <T> getNodeValue(node: DoublyLinkedList<T>.Node?): T? = node?.value

