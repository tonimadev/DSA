package digital.tonima.sort.benchmark

import digital.tonima.sort.algorithms.QuickSort
import digital.tonima.sort.algorithms.SelectionSort
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Test to verify the benchmark functionality works correctly.
 */
class SortBenchmarkTest {

    @Test
    fun `test benchmark execution`() {
        val benchmark = SortBenchmark()
        val quickSort = QuickSort<Int>()
        val data = BenchmarkDataGenerator.generateRandomList(100)

        val result = benchmark.benchmark(quickSort, data)

        assertTrue(result.executionTimeNanos > 0, "Execution time should be positive")
        assertEquals(result.collectionSize, 100, "Collection size should match")
        assertEquals(result.algorithmName, "Quick Sort", "Algorithm name should match")
    }

    @Test
    fun `test benchmark with multiple algorithms`() {
        val benchmark = SortBenchmark()
        val strategies = listOf(SelectionSort(), QuickSort<Int>())
        val data = BenchmarkDataGenerator.generateRandomList(50)

        val results = benchmark.benchmarkAll(strategies, data)

        assertEquals(results.size, 2, "Should have results for both algorithms")
        assertTrue(results.all { it.executionTimeNanos > 0 }, "All execution times should be positive")
    }

    @Test
    fun `test benchmark by size`() {
        val benchmark = SortBenchmark()
        val quickSort = QuickSort<Int>()

        val results = benchmark.benchmarkBySize(
            quickSort,
            10..50 step 10
        ) { size -> BenchmarkDataGenerator.generateRandomList(size) }

        assertEquals(results.size, 5, "Should have 5 results")
        assertTrue(results.all { it.executionTimeNanos > 0 }, "All execution times should be positive")
        assertEquals(results[0].collectionSize, 10, "First result should have size 10")
        assertEquals(results[4].collectionSize, 50, "Last result should have size 50")
    }

    @Test
    fun `test data generator - random list`() {
        val list = BenchmarkDataGenerator.generateRandomList(100, 1000)
        assertEquals(list.size, 100, "List should have 100 elements")
    }

    @Test
    fun `test data generator - sorted list`() {
        val list = BenchmarkDataGenerator.generateSortedList(50)
        assertEquals(list.size, 50, "List should have 50 elements")
        assertEquals(list, list.sorted(), "List should be sorted")
    }

    @Test
    fun `test data generator - reverse sorted list`() {
        val list = BenchmarkDataGenerator.generateReverseSortedList(30)
        assertEquals(list.size, 30, "List should have 30 elements")
        assertEquals(list, list.sortedDescending(), "List should be reverse sorted")
    }

    @Test
    fun `test fastest and slowest results`() {
        val benchmark = SortBenchmark()
        val selectionSort = SelectionSort<Int>()
        val quickSort = QuickSort<Int>()

        benchmark.benchmark(selectionSort, BenchmarkDataGenerator.generateRandomList(1000))
        benchmark.benchmark(quickSort, BenchmarkDataGenerator.generateRandomList(1000))

        val fastest = benchmark.getFastest()
        val slowest = benchmark.getSlowest()

        assertTrue(fastest != null, "Fastest result should exist")
        assertTrue(slowest != null, "Slowest result should exist")
        assertTrue(fastest.executionTimeNanos <= slowest.executionTimeNanos, "Fastest should be faster than slowest")
    }
}
