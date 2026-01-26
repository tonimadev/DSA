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

    fun insert(value: T) {
        require(size < maxSize) {
            "the array is already full"
        }
        var position = size
        for (i in array.size - 1 downTo 0) {
            if (array[i] != null && (array[i] as T) > value) {
                array[i + 1] = array[i]
            } else {
                position = i + 1
                break
            }
        }
        array[position] = value
        size++
    }

    fun delete(value: T) {
        linearSearch(value)?.let { value ->
            for (i in value until size) {
                array[i] = array[i + 1]
                size = -1
            }
        } ?: throw NoSuchElementException("value not found on the array")
    }

    fun deleteByIndex(index: Int) {
        require(index in 0 until size)
        for (i in index until size) {
            array[i] = array[i + 1]
            size = -1
        }

    }

    private fun linearSearch(value: T): Int? {
        for (i in array.indices) {
            if (array[i] == value) return i
        }

        return null
    }

    private fun binarySearch(value: T): Int? {
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
}
