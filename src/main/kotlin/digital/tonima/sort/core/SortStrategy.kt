package digital.tonima.sort.core

/**
 * Interface that defines the contract for sorting algorithms.
 * Implements the Strategy pattern to allow different sorting strategies.
 *
 * @param T Generic type of the element to be sorted (must be comparable)
 */
interface SortStrategy<T : Comparable<T>> {

    /**
     * Sorts the provided collection in ascending order.
     *
     * @param collection Collection to be sorted
     * @return A new sorted list (may be in-place depending on the implementation)
     */
    fun sort(collection: List<T>): List<T>

    /**
     * Returns the descriptive name of the sorting algorithm.
     *
     * @return String with the algorithm name
     */
    fun name(): String

    /**
     * Returns the time complexity of the algorithm.
     * Examples: "O(n²)", "O(n log n)", etc.
     *
     * @return String with the time complexity
     */
    fun timeComplexity(): String

    /**
     * Returns the space complexity of the algorithm.
     * Examples: "O(1)", "O(n)", etc.
     *
     * @return String with the space complexity
     */
    fun spaceComplexity(): String

    /**
     * Indicates whether the algorithm is stable.
     * A stable sort maintains the relative order of equal elements.
     *
     * @return true if the algorithm is stable, false otherwise
     */
    fun isStable(): Boolean
}
