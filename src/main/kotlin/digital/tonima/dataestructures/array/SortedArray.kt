package digital.tonima.dataestructures.array

class SortedArray<T : Comparable<T>>(
    val maxSize: Int
) {
    init {
        require(maxSize > 0) {
            "maxSize deve ser maior que 0"
        }
    }

    var size = 0
        private set
    var array = arrayOfNulls<Comparable<*>>(maxSize)

    /**
     * Insert value maintaining sorted order
     * Time Complexity: O(n) - needs to shift elements
     * Space Complexity: O(1)
     */
    fun insert(value: T) {
        require(size < maxSize) {
            "the array is already full"
        }

        // Find the position where value should be inserted
        var position = 0
        while (position < size && (array[position] as T) < value) {
            position++
        }

        // Shift elements to the right to make space
        for (i in size - 1 downTo position) {
            array[i + 1] = array[i]
        }

        // Insert the value at the correct position
        array[position] = value
        size++
    }

    /**
     * Delete value from array
     * Time Complexity: O(n) - needs to search and shift elements
     * Space Complexity: O(1)
     */
    fun delete(value: T) {
        binarySearch(value)?.let { index ->
            for (i in index until size - 1) {
                array[i] = array[i + 1]
            }
            array[size - 1] = null
            size--
        } ?: throw NoSuchElementException("value not found on the array")
    }

    /**
     * Delete by index
     * Time Complexity: O(n) - needs to shift elements
     * Space Complexity: O(1)
     */
    fun deleteByIndex(index: Int) {
        require(index in 0 until size) {
            "index must be between 0 and ${size - 1}"
        }
        for (i in index until size - 1) {
            array[i] = array[i + 1]
        }
        array[size - 1] = null
        size--
    }


    /**
     * Binary search for value (optimized for sorted array)
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    fun binarySearch(value: T): Int? {
        var left = 0
        var right = size - 1
        while (left <= right) {
            val guessIndex = (left + right) / 2
            val guess = array[guessIndex] as T
            if (guess == value) return guessIndex
            if (guess > value) {
                right = guessIndex - 1
            } else {
                left = guessIndex + 1
            }
        }
        return null
    }

    /**
     * Get element by index
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    fun get(index: Int): T? {
        require(index in 0 until size) {
            "index must be between 0 and ${size - 1}"
        }
        return array[index] as T?
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder("[")
        for (i in 0 until size) {
            stringBuilder.append(array[i])
            if (i < size - 1) stringBuilder.append(", ")
        }
        stringBuilder.append("]")
        return stringBuilder.toString()
    }
}
