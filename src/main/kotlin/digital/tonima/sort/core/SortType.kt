package digital.tonima.sort.core

/**
 * Enumeration that specifies the types of sorting algorithms available.
 *
 * Available types:
 * - BUBBLE: Bubble sort, simple but inefficient - O(n²)
 * - SELECTION: Selection sort - O(n²)
 * - INSERTION: Insertion sort - O(n²), efficient for small lists
 * - MERGE: Merge sort - O(n log n), stable algorithm
 * - QUICK: Quick sort - O(n log n) average, O(n²) worst case
 * - HEAP: Heap sort - O(n log n)
 * - SHELL: Shell sort - O(n log n) or O(n^1.25) depending on gap sequence
 * - COUNTING: Counting sort - O(n + k), for non-negative integers
 * - RADIX: Radix sort - O(nk), for integers with k digits
 * - BUCKET: Bucket sort - O(n + k) average case
 */
enum class SortType {
    /**
     * Bubble Sort - O(n²)
     * Simple but inefficient algorithm, good for learning.
     */
    BUBBLE,

    /**
     * Selection Sort - O(n²)
     * Finds the minimum element and places it at the beginning.
     */
    SELECTION,

    /**
     * Insertion Sort - O(n²)
     * Efficient for small datasets and nearly sorted arrays.
     */
    INSERTION,

    /**
     * Merge Sort - O(n log n)
     * Divide and conquer algorithm, stable sorting.
     */
    MERGE,

    /**
     * Quick Sort - O(n log n) average, O(n²) worst case
     * Fast in-place sorting algorithm with good average performance.
     */
    QUICK,

    /**
     * Heap Sort - O(n log n)
     * Uses a heap data structure, space-efficient.
     */
    HEAP,

    /**
     * Shell Sort - O(n log n) or O(n^1.25) depending on gap sequence
     * Generalization of insertion sort with performance improvements.
     */
    SHELL,

    /**
     * Counting Sort - O(n + k)
     * Non-comparative algorithm for non-negative integers.
     */
    COUNTING,

    /**
     * Radix Sort - O(nk)
     * Sorts integers by processing individual digits.
     */
    RADIX,

    /**
     * Bucket Sort - O(n + k) average case
     * Distributes elements into buckets then sorts each bucket.
     */
    BUCKET
}
