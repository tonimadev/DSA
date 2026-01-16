package digital.tonima.search

import digital.tonima.search.algorithms.BinarySearch
import digital.tonima.search.algorithms.LinearSearch
import digital.tonima.search.benchmark.SearchBenchmark
import digital.tonima.search.core.SearchStrategy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertTrue
import kotlin.test.assertNotNull
import kotlin.test.assertEquals

class SearchBenchmarkTest {

    private lateinit var benchmark: SearchBenchmark
    private lateinit var linearSearch: SearchStrategy<Int>
    private lateinit var binarySearch: SearchStrategy<Int>

    @BeforeEach
    fun setUp() {
        benchmark = SearchBenchmark()
        linearSearch = LinearSearch()
        binarySearch = BinarySearch()
    }

    @Test
    fun testBenchmarkResultIsCreated() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = benchmark.benchmark(linearSearch, list, 3)

        assertNotNull(result)
        assertEquals("Linear Search", result.algorithmName)
        assertEquals(5, result.collectionSize)
        assertEquals(2, result.indexFound)
        assertTrue(result.targetFound)
        assertTrue(result.executionTimeNanos >= 0)
    }

    @Test
    fun testBenchmarkMultipleStrategies() {
        val list = listOf(10, 20, 30, 40, 50)
        val strategies = listOf(linearSearch as SearchStrategy<Int>)

        val results = benchmark.benchmarkAll(strategies, list, 30)

        assertEquals(1, results.size)
        assertEquals("Linear Search", results[0].algorithmName)
    }

    @Test
    fun testGetFastest() {
        val list1 = (1..100).toList()
        val list2 = (1..10_000).toList()

        benchmark.benchmark(linearSearch, list1, 50)    // Faster (smaller list)
        benchmark.benchmark(linearSearch, list2, 5_000)  // Slower (larger list)

        val fastest = benchmark.getFastest()
        assertNotNull(fastest)
        assertEquals(100, fastest.collectionSize)
    }

    @Test
    fun testGetSlowest() {
        val list1 = (1..100).toList()
        val list2 = (1..10_000).toList()

        benchmark.benchmark(linearSearch, list1, 50)
        benchmark.benchmark(linearSearch, list2, 5_000)

        val slowest = benchmark.getSlowest()
        assertNotNull(slowest)
        assertEquals(10_000, slowest.collectionSize)
    }

    @Test
    fun testAverageTime() {
        val list = listOf(1, 2, 3, 4, 5)

        benchmark.benchmark(linearSearch, list, 2)
        benchmark.benchmark(linearSearch, list, 3)
        benchmark.benchmark(linearSearch, list, 4)

        val avg = benchmark.getAverageTime("Linear Search")
        assertTrue(avg > 0)
    }

    @Test
    fun testReset() {
        val list = listOf(1, 2, 3, 4, 5)
        benchmark.benchmark(linearSearch, list, 3)

        assertEquals(1, benchmark.getAllResults().size)

        benchmark.reset()

        assertEquals(0, benchmark.getAllResults().size)
    }

    @Test
    fun testElementNotFound() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = benchmark.benchmark(linearSearch, list, 100)

        assertEquals(-1, result.indexFound)
        assertEquals(false, result.targetFound)
    }

    @Test
    fun testBinarySearchBenchmark() {
        val list = (1..100).toList()  // Sorted list
        val result = benchmark.benchmark(binarySearch, list, 50)

        assertEquals("Binary Search", result.algorithmName)
        assertTrue(result.targetFound)
        assertTrue(result.executionTimeNanos >= 0)
    }
}

