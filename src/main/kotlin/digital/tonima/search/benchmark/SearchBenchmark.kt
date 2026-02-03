package digital.tonima.search.benchmark

import digital.tonima.search.core.SearchStrategy
import digital.tonima.search.core.Searcher

/**
 * Data class that encapsulates the results of a search benchmark.
 */
data class BenchmarkResult(
    val algorithmName: String,
    val collectionSize: Int,
    val targetFound: Boolean,
    val executionTimeNanos: Long,
    val executionTimeMillis: Double = executionTimeNanos / 1_000_000.0,
    val indexFound: Int
) {
    override fun toString(): String {
        return """
            |┌─ Benchmark: $algorithmName
            |├─ Collection size: $collectionSize elements
            |├─ Element found: $targetFound (index: $indexFound)
            |├─ Execution time: ${String.format("%.4f", executionTimeMillis)} ms
            |├─ Execution time: $executionTimeNanos ns
            |└─────────────────────────
        """.trimMargin()
    }
}

/**
 * Class responsible for performing benchmarks of search algorithms.
 * Measures execution time and provides comparisons between strategies.
 */
class SearchBenchmark {

    private val results: MutableList<BenchmarkResult> = mutableListOf()

    /**
     * Executes a benchmark for a specific algorithm (internal SearchStrategy).
     *
     * @param strategy Internal search strategy to test
     * @param collection List where to search
     * @param target Element to search for
     * @return BenchmarkResult with execution data
     */
    internal fun <T : Comparable<T>> benchmark(
        strategy: SearchStrategy<T>,
        collection: List<T>,
        target: T
    ): BenchmarkResult {
        val startTime = System.nanoTime()
        val indexFound = strategy.search(collection, target)
        val endTime = System.nanoTime()

        val result = BenchmarkResult(
            algorithmName = strategy.name(),
            collectionSize = collection.size,
            targetFound = indexFound != -1,
            executionTimeNanos = endTime - startTime,
            indexFound = indexFound
        )

        results.add(result)
        return result
    }

    /**
     * Executes a benchmark for a specific algorithm (public Searcher).
     *
     * @param searcher Public searcher to test
     * @param collection List where to search
     * @param target Element to search for
     * @return BenchmarkResult with execution data
     */
    fun <T : Comparable<T>> benchmark(
        searcher: Searcher<T>,
        collection: List<T>,
        target: T
    ): BenchmarkResult {
        val startTime = System.nanoTime()
        val indexFound = searcher.search(collection, target)
        val endTime = System.nanoTime()

        val result = BenchmarkResult(
            algorithmName = searcher.name(),
            collectionSize = collection.size,
            targetFound = indexFound != -1,
            executionTimeNanos = endTime - startTime,
            indexFound = indexFound
        )

        results.add(result)
        return result
    }

    /**
     * Executes a benchmark for multiple strategies.
     *
     * @param strategies List of internal strategies to test
     * @param collection List where to search
     * @param target Element to search for
     * @return List of BenchmarkResults
     */
    internal fun <T : Comparable<T>> benchmarkAll(
        strategies: List<SearchStrategy<T>>,
        collection: List<T>,
        target: T
    ): List<BenchmarkResult> {
        return strategies.map { strategy ->
            benchmark(strategy, collection, target)
        }
    }

    /**
     * Executes a benchmark for multiple searchers.
     *
     * @param searchers List of public searchers to test
     * @param collection List where to search
     * @param target Element to search for
     * @return List of BenchmarkResults
     */
    fun <T : Comparable<T>> benchmarkAll(
        searchers: List<Searcher<T>>,
        collection: List<T>,
        target: T
    ): List<BenchmarkResult> {
        return searchers.map { searcher ->
            benchmark(searcher, collection, target)
        }
    }

    /**
     * Executes a benchmark for different collection sizes.
     * Useful to visualize how the algorithm scales.
     *
     * @param strategy Internal strategy to test
     * @param sizeRange Size range (e.g., 1000..100000 step 10000)
     * @param targetProvider Function to provide target for given size
     * @param collectionProvider Function to provide collection for given size and target
     * @return List of BenchmarkResults for different sizes
     */
    internal fun <T : Comparable<T>> benchmarkBySize(
        strategy: SearchStrategy<T>,
        sizeRange: IntProgression,
        targetProvider: (Int) -> T,
        collectionProvider: (Int, T) -> List<T>
    ): List<BenchmarkResult> {
        return sizeRange.map { size ->
            val target = targetProvider(size)
            val collection = collectionProvider(size, target)
            benchmark(strategy, collection, target)
        }
    }

    /**
     * Returns the fastest result so far.
     */
    fun getFastest(): BenchmarkResult? {
        return results.minByOrNull { it.executionTimeNanos }
    }

    /**
     * Returns the slowest result so far.
     */
    fun getSlowest(): BenchmarkResult? {
        return results.maxByOrNull { it.executionTimeNanos }
    }

    /**
     * Returns the average execution time for a specific algorithm.
     */
    fun getAverageTime(algorithmName: String): Double {
        val filtered = results.filter { it.algorithmName == algorithmName }
        return if (filtered.isNotEmpty()) {
            filtered.map { it.executionTimeMillis }.average()
        } else {
            0.0
        }
    }

    /**
     * Clears the results history.
     */
    fun reset() {
        results.clear()
    }

    /**
     * Returns all captured results.
     */
    fun getAllResults(): List<BenchmarkResult> = results.toList()

    /**
     * Displays a comparative report in text format.
     */
    fun printComparativeReport() {
        if (results.isEmpty()) {
            println("❌ No benchmarks have been executed yet.")
            return
        }

        val grouped = results.groupBy { it.algorithmName }
        val fastest = results.minByOrNull { it.executionTimeNanos }!!

        println("\n" + "=".repeat(80))
        println("📊 BENCHMARK COMPARATIVE REPORT")
        println("=".repeat(80))

        grouped.forEach { (algorithmName, algorithmResults) ->
            println("\n🔍 $algorithmName")
            println("─".repeat(80))

            algorithmResults.forEach { result ->
                val speedFactor = result.executionTimeNanos.toDouble() / fastest.executionTimeNanos
                val speedMarker = when {
                    result == fastest -> "⚡ FASTEST"
                    speedFactor < 1.5 -> "✅ Good"
                    speedFactor < 3.0 -> "⚠️  Moderate"
                    else -> "❌ Slow"
                }

                println(
                    "  • Size: ${String.format("%7d", result.collectionSize)} | " +
                    "Time: ${String.format("%10.4f", result.executionTimeMillis)} ms | " +
                    "Factor: ${String.format("%.2f", speedFactor)}x | " +
                    speedMarker
                )
            }

            val avg = algorithmResults.map { it.executionTimeMillis }.average()
            println("  └─ Average: ${String.format("%.4f", avg)} ms")
        }

        println("\n" + "=".repeat(80))
        println("⚡ FASTEST: ${fastest.algorithmName} (${String.format("%.4f", fastest.executionTimeMillis)} ms)")
        println("=".repeat(80) + "\n")
    }

    /**
     * Displays a report in simple table format.
     */
    fun printTableReport() {
        if (results.isEmpty()) {
            println("❌ No benchmarks have been executed yet.")
            return
        }

        println("\n" + "═".repeat(100))
        println("║ " + "ALGORITHM".padEnd(20) + "║ " + "SIZE".padEnd(10) + "║ " +
            "TIME (ms)".padEnd(15) + "║ " + "FOUND".padEnd(12) + "║ " + "INDEX".padEnd(8) + "║")
        println("═".repeat(100))

        results.forEach { result ->
            println("║ " + result.algorithmName.padEnd(20) + "║ " +
                result.collectionSize.toString().padEnd(10) + "║ " +
                String.format("%.6f", result.executionTimeMillis).padEnd(15) + "║ " +
                result.targetFound.toString().padEnd(12) + "║ " +
                result.indexFound.toString().padEnd(8) + "║")
        }

        println("═".repeat(100) + "\n")
    }
}

