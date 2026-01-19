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

    private fun partition(list: MutableList<T>, low: Int, high: Int): Int {
        val pivot = list[high]
        var i = low - 1

        for (j in low until high) {
            if (list[j] <= pivot) {
                i++
                list.swap(i, j)
            }
        }
        list.swap(i + 1, high)
        return i + 1
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
