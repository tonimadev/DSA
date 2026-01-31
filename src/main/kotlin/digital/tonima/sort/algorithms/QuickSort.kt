package digital.tonima.sort.algorithms

import digital.tonima.sort.core.SortStrategy

/**
 * Base class for Quick Sort algorithm.
 * Uses divide and conquer strategy to sort elements.
 * Selects a pivot and partitions the array around it.
 *
 * Complexity:
 * - Best case: O(n log n) - pivot divides array evenly
 * - Average case: O(n log n)
 * - Worst case: O(n²) - pivot is always smallest or largest
 *
 * Characteristics:
 * - Fast average-case performance
 * - Not stable (relative order of equal elements may change)
 * - In-place sorting (O(log n) space for recursion stack)
 * - Cache-friendly due to sequential access patterns
 * - Generally faster than merge sort in practice
 *
 * Stability: NOT stable
 * Space Complexity: O(log n) - recursion stack
 *
 * Pivot Selection Strategies:
 * - First element
 * - Last element
 * - Middle element
 * - Random element
 * - Median of three
 */
class QuickSort<T : Comparable<T>> : SortStrategy<T> {

    override fun sort(collection: List<T>): List<T> {
        if (collection.isEmpty()) return collection
        val mutableList = collection.toMutableList()
        quickSort(mutableList, 0, mutableList.size - 1)
        return mutableList
    }

    private fun quickSort(list: MutableList<T>, low: Int, high: Int) {
        if (low < high) {
            val pivotIndex = partition(list, low, high)
            quickSort(list, low, pivotIndex - 1)
            quickSort(list, pivotIndex + 1, high)
        }
    }

    /**
     * Partitions array around pivot (Lomuto partition scheme)
     *
     * Visual representation:
     * ┌─────────────────────────────────────────┐
     * │ ≤ pivot │ pivot │ > pivot              │
     * └─────────────────────────────────────────┘
     *           ^
     *    returns this index
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private fun partition(list: MutableList<T>, low: Int, high: Int): Int {
        val pivot = list[high]
        val partitionIndex = moveElementsAroundPivot(list, low, high, pivot)
        placePivotInCorrectPosition(list, partitionIndex, high)
        return partitionIndex + 1  // Return pivot's final position
    }

    /**
     * Moves elements <= pivot to left, others to right
     * Returns the index where pivot should be placed
     *
     * Time Complexity: O(n)
     */
    private fun moveElementsAroundPivot(
        list: MutableList<T>,
        low: Int,
        high: Int,
        pivot: T
    ): Int {
        var lastSmallElementIndex = low - 1

        for (currentIndex in low until high) {
            if (list[currentIndex] <= pivot) {
                lastSmallElementIndex++
                list.swap(lastSmallElementIndex, currentIndex)
            }
        }

        return lastSmallElementIndex
    }

    /**
     * Places pivot in its final sorted position
     *
     * Time Complexity: O(1)
     */
    private fun placePivotInCorrectPosition(
        list: MutableList<T>,
        partitionIndex: Int,
        pivotPosition: Int
    ) {
        list.swap(partitionIndex + 1, pivotPosition)
    }

    private fun MutableList<T>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    override fun name(): String = "Quick Sort"
    override fun timeComplexity(): String = "O(n log n) average, O(n²) worst"
    override fun spaceComplexity(): String = "O(log n)"
    override fun isStable(): Boolean = false
}
