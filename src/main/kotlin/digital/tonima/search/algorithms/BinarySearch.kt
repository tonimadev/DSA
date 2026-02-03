package digital.tonima.search.algorithms

import digital.tonima.search.core.SearchStrategy

/**
 * Implementation of Binary Search.
 * Requires the collection to be sorted.
 * Uses the "divide and conquer" strategy to search for the element.
 *
 * Complexity:
 * - Best case: O(1) - element in the middle
 * - Average case: O(log n)
 * - Worst case: O(log n)
 *
 * Characteristics:
 * - Requires sorted list
 * - Very efficient for large lists
 * - Ideal for multiple searches
 *
 * @throws IllegalStateException if the list is not sorted (optional validation)
 */
internal class BinarySearch<T : Comparable<T>> : SearchStrategy<T> {

    override fun search(collection: List<T>, target: T): Int {
        var lowIndex = 0
        var highIndex = collection.size - 1

        while (lowIndex <= highIndex) {
            val guessIndex = (lowIndex + highIndex) / 2
            val guess = collection[guessIndex]

            if (guess == target) return guessIndex
            if (guess > target) {
                highIndex = guessIndex - 1
            } else {
                lowIndex = guessIndex + 1
            }
        }
        return -1
    }

    override fun name(): String = "Binary Search"
}

