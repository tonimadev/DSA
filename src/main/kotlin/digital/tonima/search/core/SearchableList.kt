package digital.tonima.search.core

/**
 * Facade that represents a list with search capabilities.
 * Implements the Facade pattern to provide a simplified interface to the client.
 *
 * Usage:
 * ```kotlin
 * val list = SearchableList(listOf(1, 2, 3, 4, 5))
 * val index = list.find(3)  // Returns 2
 * ```
 *
 * @param T Generic type of the element in the list (must be Comparable)
 * @param items Initial list of elements (default: empty)
 * @param defaultSearcher Default searcher to use (default: BINARY search)
 */
class SearchableList<T : Comparable<T>>(
    items: List<T> = emptyList(),
    private val defaultSearcher: Searcher<T> = SearchFactory.create(SearchAlgorithm.BINARY)
) {

    private val data: MutableList<T> = items.toMutableList()

    /**
     * Searches for an element in the list using the default searcher.
     *
     * @param item Element to search for
     * @return The index of the element if found, -1 otherwise
     */
    fun find(item: T): Int {
        return defaultSearcher.search(data, item)
    }

    /**
     * Searches for an element in the list using a specific search algorithm.
     *
     * @param item Element to search for
     * @param searchAlgorithm Type of search algorithm to use
     * @return The index of the element if found, -1 otherwise
     */
    fun find(item: T, searchAlgorithm: SearchAlgorithm): Int {
        val searcher = SearchFactory.create<T>(searchAlgorithm)
        return searcher.search(data, item)
    }

    /**
     * Returns the size of the list.
     */
    fun size(): Int = data.size

    /**
     * Checks if the list is empty.
     */
    fun isEmpty(): Boolean = data.isEmpty()

    /**
     * Adds an element to the list.
     */
    fun add(item: T) {
        data.add(item)
    }

    /**
     * Removes an element from the list.
     * @return true if the element was removed, false otherwise
     */
    fun remove(item: T): Boolean {
        return data.remove(item)
    }

    /**
     * Returns the element at the specified index.
     */
    fun get(index: Int): T? {
        return if (index >= 0 && index < data.size) data[index] else null
    }

    /**
     * Returns an immutable copy of the list.
     */
    fun toList(): List<T> = data.toList()

    /**
     * Clears all elements from the list.
     */
    fun clear() {
        data.clear()
    }

    /**
     * Sorts the list (useful before using BinarySearch).
     */
    fun sort() {
        data.sort()
    }
}

