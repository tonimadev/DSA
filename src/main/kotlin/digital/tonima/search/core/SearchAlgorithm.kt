package digital.tonima.search.core

/**
 * Public enumeration that specifies the available search algorithm types.
 * Clients should use this enum when requesting a searcher from the factory.
 *
 * Available types:
 * - LINEAR: Sequential search, works with any list
 * - BINARY: Binary search, requires sorted list
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

    // Add other search types as needed:
    // INTERPOLATION,  // Interpolation search
    // JUMP_SEARCH,    // Jump search
    // EXPONENTIAL,    // Exponential search
    // FIBONACCI,      // Fibonacci search
}
