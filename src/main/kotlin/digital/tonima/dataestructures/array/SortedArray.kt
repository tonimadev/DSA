package digital.tonima.dataestructures.array

import digital.tonima.search.core.SearchFactory
import digital.tonima.search.core.SearchAlgorithm
import digital.tonima.search.core.Searcher

class SortedArray<T : Comparable<T>>(
    val maxSize: Int,
    private val searcher: Searcher<T> = SearchFactory.create(SearchAlgorithm.BINARY)
) {
    init {
        require(maxSize > 0) {
            "maxSize must be greater than 0"
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
        val index = searcher.search(array.take(size).map { it as T }, value)

        if (index >= 0) {
            for (i in index until size - 1) {
                array[i] = array[i + 1]
            }
            array[size - 1] = null
            size--
        } else {
            throw NoSuchElementException("value not found on the array")
        }
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
