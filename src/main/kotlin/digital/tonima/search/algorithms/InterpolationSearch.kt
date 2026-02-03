package digital.tonima.search.algorithms

import digital.tonima.search.core.SearchStrategy
import kotlin.math.min

/**
 * Implementation of Interpolation Search.
 * Requires the collection to be sorted.
 * Estimates the position of the element based on uniform data distribution.
 *
 * Complexity:
 * - Best case: O(1) - element at first estimated position
 * - Average case: O(log log n) - uniformly distributed data
 * - Worst case: O(n) - non-uniformly distributed data
 *
 * Characteristics:
 * - Requires sorted list
 * - Works best with uniformly distributed data
 * - Worse performance than binary search on random data
 * - Ideal for large datasets with uniform distribution
 *
 * @throws IllegalStateException if the list is not sorted (optional validation)
 */
internal class InterpolationSearch<T : Comparable<T>> : SearchStrategy<T> {

    override fun search(collection: List<T>, target: T): Int {
        if (collection.isEmpty()) return -1

        var low = 0
        var high = collection.size - 1

        while (low <= high) {
            // Check if target is outside the range
            val lowValue = collection[low]
            val highValue = collection[high]

            if (target !in lowValue..highValue) {
                return -1
            }

            // If low == high, we found the element
            if (low == high) {
                return if (collection[low] == target) low else -1
            }

            // Calculate interpolated position
            // Formula: pos = low + ((target - collection[low]) / (collection[high] - collection[low])) * (high - low)
            val mid = try {
                low + ((target.hashCode() - lowValue.hashCode()).toDouble() /
                        (highValue.hashCode() - lowValue.hashCode()).toDouble() *
                        (high - low).toDouble()).toInt()
            } catch (e: Exception) {
                // If overflow or division by zero occurs, use simple binary search
                (low + high) / 2
            }

            // Ensure mid is in valid range
            val guessIndex = mid.coerceIn(low, high)
            val guess = collection[guessIndex]

            when {
                guess == target -> return guessIndex
                guess < target -> {
                    low = guessIndex + 1
                }
                else -> {
                    high = guessIndex - 1
                }
            }
        }

        return -1
    }

    override fun name(): String = "Interpolation Search"
}
