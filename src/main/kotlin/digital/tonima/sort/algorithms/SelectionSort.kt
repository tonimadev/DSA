package digital.tonima.sort.algorithms

import digital.tonima.sort.core.SortStrategy

/**
 * Base class for Selection Sort algorithm.
 * Finds the minimum element and places it at the beginning.
 *
 * Complexity:
 * - Best case: O(n²)
 * - Average case: O(n²)
 * - Worst case: O(n²)
 *
 * Characteristics:
 * - Simple and easy to understand
 * - Not stable
 * - In-place sorting (O(1) space)
 * - Good for small datasets
 * - Makes minimal swaps compared to bubble sort
 *
 * Stability: NOT stable
 * Space Complexity: O(1) - in-place
 */
class SelectionSort<T : Comparable<T>> : SortStrategy<T> {

    override fun sort(collection: List<T>): List<T> {
        val mutableList = collection.toMutableList()

        for (i in mutableList.indices) {
            val minIndex = findLowerIndex(i, mutableList)
            // Swap the minimum element with the current position
            val temp = mutableList[i]
            mutableList[i] = mutableList[minIndex]
            mutableList[minIndex] = temp
        }
        return mutableList
    }

    private fun findLowerIndex(i: Int, mutableList: MutableList<T>): Int {
        var minIndex = i
        for (j in i + 1 until mutableList.size) {
            if (mutableList[j] < mutableList[minIndex]) {
                minIndex = j
            }
        }
        return minIndex
    }

    override fun name(): String = "Selection Sort"

    override fun timeComplexity(): String = "O(n²)"

    override fun spaceComplexity(): String = "O(1)"

    override fun isStable(): Boolean = false
}
