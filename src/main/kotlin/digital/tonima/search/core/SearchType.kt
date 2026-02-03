package digital.tonima.search.core

/**
 * Enumeration that specifies the available search algorithm types.
 *
 * Available types:
 * - LINEAR: Sequential search, works with any list
 * - BINARY: Binary search, requires sorted list
 * - INTERPOLATION: Interpolation search, requires sorted list with uniform distribution
 */
internal enum class SearchType {
    /**
     * Linear Search - O(n)
     * Works with sorted or unsorted lists.
     */
    LINEAR,

    /**
     * Binary Search - O(log n)
     * Requires sorted list.
     */
    BINARY,

    /**
     * Interpolation Search - O(log log n) average
     * Requires sorted list with uniform distribution.
     */
    INTERPOLATION,

    // Add other search types as needed:
    // JUMP_SEARCH,    // Jump search
    // EXPONENTIAL,    // Exponential search
    // FIBONACCI,      // Fibonacci search
}

