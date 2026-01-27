package digital.tonima.dataestructures.array

class UnsortedDynamicArray<T>(
    initialCapacity: Int,

    ) {

    init {
        require(initialCapacity > 0) {
            "maxSize deve ser maior que 0"
        }
    }

    var capacity = initialCapacity
    var size = 0
        private set
    var array = arrayOfNulls<Any?>(initialCapacity)

    /**
     * Insert value at the end (auto-resizes if full)
     * Time Complexity: O(1) amortized - O(n) when resizing
     * Space Complexity: O(1) amortized
     */
    fun insert(value: T) {
        if (size == array.size) {
            doubleSize()
        }
        array[size] = value
        size++
    }

    /**
     * Remove first occurrence of target value
     * Time Complexity: O(n) - needs to search and shift elements
     * Space Complexity: O(1)
     */
    fun remove(target: T) {
        find(target)?.let { index ->
            for (i in index until size - 1) {
                array[i] = array[i + 1]
            }
            array[size - 1] = null
            size--
        }
        if (capacity > 1 && size <= capacity / 4) halfSize()
    }

    /**
     * Find element and return its index
     * Time Complexity: O(n) - linear search
     * Space Complexity: O(1)
     */
    fun find(target: T): Int? {
        for (i in 0 until size) {
            if (array[i] == target) return i
        }

        return null
    }

    /**
     * Double the capacity of the array
     * Time Complexity: O(n) - copies all elements
     * Space Complexity: O(n) - new array with double capacity
     */
    private fun doubleSize() {
        val oldArray = array
        array = arrayOfNulls<Any?>(capacity * 2)
        capacity *= 2
        for (i in 0 until size) {
            array[i] = oldArray[i]
        }

    }

    /**
     * Halve the capacity of the array
     * Time Complexity: O(n) - copies all elements
     * Space Complexity: O(n/2) - new array with half capacity
     */
    private fun halfSize() {
        val oldArray = array
        array = arrayOfNulls<Any?>(capacity / 2)
        capacity /= 2
        for (i in 0 until size) {
            array[i] = oldArray[i]
        }
    }

}
