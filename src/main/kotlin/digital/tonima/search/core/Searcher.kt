package digital.tonima.search.core

/**
 * Public interface that clients should use for searching.
 * This interface abstracts away the internal search strategy implementation.
 *
 * Usage:
 * ```kotlin
 * val searcher = SearchFactory.createSearcher<Int>(SearchType.BINARY)
 * val index = searcher.search(list, target)
 * ```
 *
 * @param T Generic type of the element (must be Comparable)
 */
interface Searcher<T : Comparable<T>> {

    /**
     * Performs a search for an element in the collection.
     *
     * @param collection Collection where to search (must be sorted when necessary)
     * @param target Element to search for
     * @return The index of the element if found, -1 otherwise
     */
    fun search(collection: List<T>, target: T): Int

    /**
     * Returns a descriptive name of the search algorithm.
     *
     * @return String with the algorithm name
     */
    fun name(): String
}
