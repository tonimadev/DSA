package digital.tonima.dataestructures.queue

/**
 * Queue (Fila) - Estrutura de dados FIFO (First In, First Out)
 * Implementada usando lista ligada para operações O(1)
 */
class Queue<T>(value: T) {
    private var first: Node? = null
    private var last: Node? = null
    private var length = 0

    init {
        val newNode = Node(value)
        first = newNode
        last = newNode
        length = 1
    }

    /**
     * Node with immutable value following Data-Oriented Programming principles.
     * The value is immutable (val) for better reasoning about data.
     * The next pointer remains mutable (var) for efficient queue operations.
     */
    inner class Node(val value: T) {  // Immutable data
        var next: Node? = null  // Mutable structure pointer
    }

    /**
     * Adiciona um elemento ao final da fila
     * Complexidade de Tempo: O(1)
     * Complexidade de Espaço: O(1)
     */
    fun enqueue(value: T) {
        val newNode = Node(value)
        if (length == 0) {
            last = newNode
            first = newNode
        } else {
            last?.next = newNode
            last = newNode
        }

        length++
    }

    /**
     * Remove e retorna o primeiro elemento da fila
     * Complexidade de Tempo: O(1)
     * Complexidade de Espaço: O(1)
     * @return Node removido ou null se a fila estiver vazia
     */
    fun dequeue(): Node? {
        if (length == 0) return null
        val temp = first
        if (length == 1) {
            first = null
            last = null

        } else {
            first = first?.next
            temp?.next = null
        }

        length--
        return temp
    }
}
