package digital.tonima.search.algorithms

import digital.tonima.search.core.SearchStrategy

/**
 * Implementation of Linear Search.
 * Traverses the collection sequentially until the element is found.
 *
 * Complexity:
 * - Best case: O(1) - element at the first position
 * - Average case: O(n/2) ≈ O(n)
 * - Worst case: O(n) - element at the end or doesn't exist
 *
 * Characteristics:
 * - Works with sorted or unsorted lists
 * - Simple to implement
 * - Ideal for small lists or single searches
 */
internal class LinearSearch<T : Comparable<T>> : SearchStrategy<T> {

    override fun search(collection: List<T>, target: T): Int {
        collection.forEachIndexed { index, t ->
            if (t == target) return index
        }
        return -1
    }

    override fun name(): String = "Linear Search"
}

