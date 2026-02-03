package digital.tonima.search.core

import digital.tonima.search.algorithms.BinarySearch
import digital.tonima.search.algorithms.LinearSearch
import digital.tonima.search.algorithms.InterpolationSearch

/**
 * Factory that creates instances of different search algorithms.
 * Implements the Factory pattern to centralize strategy creation.
 * This is the only public API for obtaining search strategies.
 *
 * Usage:
 * ```kotlin
 * val searcher = SearchFactory.create<Int>(SearchAlgorithm.BINARY)
 * val index = searcher.search(list, target)
 * ```
 */
object SearchFactory {

    /**
     * Creates an instance of the specified search algorithm.
     *
     * @param T Generic type of the element (must be Comparable)
     * @param algorithm Type of search algorithm desired (LINEAR, BINARY, INTERPOLATION, etc.)
     * @return Instance of Searcher configured with the specified algorithm
     */
    fun <T : Comparable<T>> create(algorithm: SearchAlgorithm = SearchAlgorithm.LINEAR): Searcher<T> {
        return when (algorithm) {
            SearchAlgorithm.LINEAR -> LinearSearchAdapter()
            SearchAlgorithm.BINARY -> BinarySearchAdapter()
            SearchAlgorithm.INTERPOLATION -> InterpolationSearchAdapter()
            // Add new types here as you implement:
            // SearchAlgorithm.JUMP_SEARCH -> JumpSearchAdapter()
            // SearchAlgorithm.EXPONENTIAL -> ExponentialSearchAdapter()
        }
    }

    /**
     * Adapter to convert internal LinearSearch to public Searcher interface
     */
    private class LinearSearchAdapter<T : Comparable<T>> : Searcher<T> {
        private val strategy = LinearSearch<T>()

        override fun search(collection: List<T>, target: T): Int {
            return strategy.search(collection, target)
        }

        override fun name(): String = strategy.name()
    }

    /**
     * Adapter to convert internal BinarySearch to public Searcher interface
     */
    private class BinarySearchAdapter<T : Comparable<T>> : Searcher<T> {
        private val strategy = BinarySearch<T>()

        override fun search(collection: List<T>, target: T): Int {
            return strategy.search(collection, target)
        }

        override fun name(): String = strategy.name()
    }

    /**
     * Adapter to convert internal InterpolationSearch to public Searcher interface
     */
    private class InterpolationSearchAdapter<T : Comparable<T>> : Searcher<T> {
        private val strategy = InterpolationSearch<T>()

        override fun search(collection: List<T>, target: T): Int {
            return strategy.search(collection, target)
        }

        override fun name(): String = strategy.name()
    }
}
