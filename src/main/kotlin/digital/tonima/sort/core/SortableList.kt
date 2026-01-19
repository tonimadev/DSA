package digital.tonima.sort.core

/**
 * Facade that represents a sortable list with different sorting algorithm options.
 * Implements the Facade pattern to provide a simplified interface to the client.
 *
 * Usage:
 * ```kotlin
 * val list = SortableList(listOf(5, 2, 8, 1, 9))
 * val sorted = list.sort()  // Uses default sort type (QUICK)
 * val sorted2 = list.sort(SortType.MERGE)  // Uses Merge Sort
 * ```
 *
 * @param T Generic type of the element in the list (must be Comparable)
 * @param items Initial list of elements (default: empty)
 * @param defaultSortType Default sorting algorithm to use (default: QUICK)
 */
class SortableList<T : Comparable<T>>(
    items: List<T> = emptyList(),
    private val defaultSortType: SortType = SortType.QUICK
) {

    private val data: MutableList<T> = items.toMutableList()

    /**
     * Sorts the list using the default sorting algorithm.
     *
     * @return A new sorted list
     */
    fun sort(): List<T> {
        return sort(defaultSortType)
    }

    /**
     * Sorts the list using a specific sorting algorithm.
     *
     * @param sortType Type of sorting algorithm to use
     * @return A new sorted list
     */
    fun sort(sortType: SortType): List<T> {
        val strategy = SortFactory.create<T>(sortType)
        return strategy.sort(data)
    }

    /**
     * Returns the size of the list.
     */
    fun size(): Int = data.size

    /**
     * Returns the current list as a read-only list.
     */
    fun toList(): List<T> = data.toList()

    /**
     * Adds an element to the list.
     *
     * @param item Element to add
     */
    fun add(item: T) {
        data.add(item)
    }

    /**
     * Adds all elements from another collection to the list.
     *
     * @param items Collection of elements to add
     */
    fun addAll(items: Collection<T>) {
        data.addAll(items)
    }

    /**
     * Clears all elements from the list.
     */
    fun clear() {
        data.clear()
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index Index of the element
     * @return Element at the specified index
     */
    fun get(index: Int): T = data[index]

    /**
     * Returns all elements in the list as a string representation.
     */
    override fun toString(): String = data.toString()
}
