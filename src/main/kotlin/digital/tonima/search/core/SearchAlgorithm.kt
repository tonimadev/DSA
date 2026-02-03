package digital.tonima.search.core

/**
 * Public enumeration that specifies the available search algorithm types.
 * Clients should use this enum when requesting a searcher from the factory.
 *
 * Available types:
 * - LINEAR: Sequential search, works with any list
 * - BINARY: Binary search, requires sorted list
 * - INTERPOLATION: Interpolation search, requires sorted list with uniform distribution
 */
enum class SearchAlgorithm {
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
     * Better than binary search for uniformly distributed data.
     * Worse than binary search for random data.
     */
    INTERPOLATION,

    // Add other search types as needed:
    // JUMP_SEARCH,    // Jump search
    // EXPONENTIAL,    // Exponential search
    // FIBONACCI,      // Fibonacci search
}
