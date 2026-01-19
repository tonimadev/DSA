package digital.tonima.sort.benchmark

/**
 * Utility class that provides data for benchmark tests.
 */
object BenchmarkDataGenerator {

    /**
     * Generates an unordered list of integers.
     */
    fun generateRandomList(size: Int, maxValue: Int = Int.MAX_VALUE): List<Int> {
        return (1..size).map { (Math.random() * maxValue).toInt() }
    }

    /**
     * Generates a sorted list of integers (best case for some algorithms).
     */
    fun generateSortedList(size: Int): List<Int> {
        return (1..size).toList()
    }

    /**
     * Generates a reverse sorted list (worst case for some algorithms).
     */
    fun generateReverseSortedList(size: Int): List<Int> {
        return (size downTo 1).toList()
    }

    /**
     * Generates a nearly sorted list (only a few elements out of order).
     */
    fun generateNearlySortedList(size: Int, swaps: Int = 10): List<Int> {
        val list = (1..size).toMutableList()
        repeat(swaps) {
            val i = (Math.random() * size).toInt()
            val j = (Math.random() * size).toInt()
            val temp = list[i]
            list[i] = list[j]
            list[j] = temp
        }
        return list
    }

    /**
     * Generates a list with many duplicate values.
     */
    fun generateListWithDuplicates(size: Int, uniqueValues: Int = 10): List<Int> {
        return (1..size).map { (Math.random() * uniqueValues).toInt() }
    }

    /**
     * Generates a list where all elements are the same.
     */
    fun generateUniformList(size: Int, value: Int = 42): List<Int> {
        return List(size) { value }
    }

    /**
     * Generates an unordered list of Strings.
     */
    fun generateRandomStrings(size: Int): List<String> {
        return (1..size).map { "item_${(Math.random() * 10000).toInt()}" }
    }

    /**
     * Generates a sorted list of Strings.
     */
    fun generateSortedStrings(size: Int): List<String> {
        return (1..size).map { index -> "item_${String.format("%05d", index)}" }
    }

    /**
     * Generates a list with a specific distribution pattern.
     */
    fun generateGaussianDistribution(size: Int, mean: Double = 0.0, stdDev: Double = 1.0): List<Int> {
        return (1..size).map {
            val u1 = Math.random()
            val u2 = Math.random()
            val z0 = kotlin.math.sqrt(-2.0 * kotlin.math.ln(u1)) * kotlin.math.cos(2.0 * Math.PI * u2)
            (mean + z0 * stdDev).toInt()
        }
    }

    /**
     * Returns a map of common data patterns for benchmarking.
     */
    fun getAllPatterns(size: Int): Map<String, List<Int>> {
        return mapOf(
            "Random" to generateRandomList(size),
            "Sorted" to generateSortedList(size),
            "Reverse Sorted" to generateReverseSortedList(size),
            "Nearly Sorted" to generateNearlySortedList(size),
            "Many Duplicates" to generateListWithDuplicates(size),
            "All Same" to generateUniformList(size)
        )
    }
}
