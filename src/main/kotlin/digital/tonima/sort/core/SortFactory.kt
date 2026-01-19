package digital.tonima.sort.core

import digital.tonima.sort.algorithms.QuickSort
import digital.tonima.sort.algorithms.SelectionSort

/**
 * Factory that creates instances of different sorting algorithms.
 * Implements the Factory pattern to centralize strategy creation.
 *
 * Usage:
 * ```kotlin
 * val strategy = SortFactory.create<Int>(SortType.QUICK)
 * val sorted = strategy.sort(list)
 * ```
 */
object SortFactory {

    /**
     * Creates an instance of the specified sorting algorithm.
     *
     * @param T Generic type of the element (must be Comparable)
     * @param type Type of sorting algorithm desired (BUBBLE, MERGE, QUICK, etc.)
     * @return Instance of SortStrategy configured
     */
    fun <T : Comparable<T>> create(type: SortType): SortStrategy<T> {
        return when (type) {
            SortType.SELECTION -> SelectionSort()
            SortType.QUICK -> QuickSort()
            // Add implementations here as you create them:
            // SortType.BUBBLE -> BubbleSort()
            // SortType.INSERTION -> InsertionSort()
            // SortType.MERGE -> MergeSort()
            // SortType.HEAP -> HeapSort()
            // SortType.SHELL -> ShellSort()
            // SortType.COUNTING -> CountingSort()
            // SortType.RADIX -> RadixSort()
            // SortType.BUCKET -> BucketSort()
            else -> throw UnsupportedOperationException("Sort algorithm ${type.name} not yet implemented")
        }
    }
}
