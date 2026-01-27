package digital.tonima.dataestructures.array

class UnsortedArray<T>(
    maxSize: Int,

    ) {

    init {
        require(maxSize > 0) {
            "maxSize deve ser maior que 0"
        }
    }

    var size = 0
        private set
    var array = arrayOfNulls<Any?>(maxSize)

    /**
     * Insert value at the end
     * Time Complexity: O(1) - direct insertion at end
     * Space Complexity: O(1)
     */
    fun insert(value: T) {
        if (size >= array.size) {
            throw IllegalStateException("Array está cheio")
        } else {
            array[size] = value
            size++
        }
    }

    /**
     * Remove element at index (replaces with last element)
     * Time Complexity: O(1) - no shifting needed
     * Space Complexity: O(1)
     */
    fun remove(index: Int): T? {
        require(index in 0 until size) {
            "index não pode ser maior que o tamanho da lista nem negativo"
        }
        val value = array[index]
        array[index] = array[size - 1]
        size--

        return value as T?
    }

    /**
     * Find element and return its index
     * Time Complexity: O(n) - linear search
     * Space Complexity: O(1)
     */
    fun get(target: T): Int? {
        for (i in 0 until size) {
            if (array[i] == target) return i
        }

        return null
    }

    /**
     * Traverse all elements applying operation
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun traverse(op: (T?) -> Unit) {
        array.forEach {
            op(it as? T?)
        }

    }

    /**
     * Print all elements
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun printIt() {
        traverse { println(it) }
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        for (i in 0 until size) {
            stringBuilder.append(" ")
            stringBuilder.append("${array[i]}")
        }
        return stringBuilder.toString()

    }
}
