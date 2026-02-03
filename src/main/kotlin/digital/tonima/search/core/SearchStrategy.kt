package digital.tonima.search.core

/**
 * Interface that defines the contract for search algorithms.
 * Implements the Strategy pattern to allow different search strategies.
 *
 * @param T Generic type of the element to be searched (must be comparable)
 */
internal interface SearchStrategy<T : Comparable<T>> {

    /**
     * Performs a search for an element in the collection.
     *
     * @param collection Collection where to search (must be sorted when necessary)
     * @param target Element to search for
     * @return The index of the element if found, -1 otherwise
     */
    fun search(collection: List<T>, target: T): Int

    /**
     * Returns the descriptive name of the search algorithm.
     *
     * @return String with the algorithm name
     */
    fun name(): String
}

