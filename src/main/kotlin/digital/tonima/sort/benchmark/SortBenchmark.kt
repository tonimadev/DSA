package digital.tonima.sort.benchmark

import digital.tonima.sort.core.SortStrategy

/**
 * Data class that encapsulates the results of a sort benchmark.
 */
data class SortBenchmarkResult(
    val algorithmName: String,
    val collectionSize: Int,
    val executionTimeNanos: Long,
    val executionTimeMillis: Double = executionTimeNanos / 1_000_000.0,
    val comparisons: Long = 0,
    val swaps: Long = 0,
    val timeComplexity: String,
    val spaceComplexity: String,
    val isStable: Boolean
) {
    override fun toString(): String {
        return """
            |┌─ Benchmark: $algorithmName
            |├─ Collection size: $collectionSize elements
            |├─ Execution time: ${String.format("%.4f", executionTimeMillis)} ms
            |├─ Execution time: $executionTimeNanos ns
            |├─ Time Complexity: $timeComplexity
            |├─ Space Complexity: $spaceComplexity
            |├─ Stable: ${if (isStable) "✅ Yes" else "❌ No"}
            |└─────────────────────────
        """.trimMargin()
    }
}

/**
 * Class responsible for performing benchmarks of sorting algorithms.
 * Measures execution time and provides comparisons between strategies.
 */
class SortBenchmark {

    private val results: MutableList<SortBenchmarkResult> = mutableListOf()

    /**
     * Executes a benchmark for a specific sorting algorithm.
     *
     * @param strategy Sort strategy to test
     * @param collection List to be sorted
     * @return SortBenchmarkResult with execution data
     */
    fun <T : Comparable<T>> benchmark(
        strategy: SortStrategy<T>,
        collection: List<T>
    ): SortBenchmarkResult {
        val startTime = System.nanoTime()
        strategy.sort(collection)
        val endTime = System.nanoTime()

        val result = SortBenchmarkResult(
            algorithmName = strategy.name(),
            collectionSize = collection.size,
            executionTimeNanos = endTime - startTime,
            timeComplexity = strategy.timeComplexity(),
            spaceComplexity = strategy.spaceComplexity(),
            isStable = strategy.isStable()
        )

        results.add(result)
        return result
    }

    /**
     * Executes a benchmark for multiple sorting strategies.
     *
     * @param strategies List of strategies to test
     * @param collection List to be sorted
     * @return List of SortBenchmarkResults
     */
    fun <T : Comparable<T>> benchmarkAll(
        strategies: List<SortStrategy<T>>,
        collection: List<T>
    ): List<SortBenchmarkResult> {
        return strategies.map { strategy ->
            benchmark(strategy, collection)
        }
    }

    /**
     * Executes a benchmark for different collection sizes.
     * Useful to visualize how the algorithm scales.
     *
     * @param strategy Strategy to test
     * @param sizeRange Size range (e.g., 1000..100000 step 10000)
     * @param collectionProvider Function to provide collection for given size
     * @return List of SortBenchmarkResults for different sizes
     */
    fun <T : Comparable<T>> benchmarkBySize(
        strategy: SortStrategy<T>,
        sizeRange: IntProgression,
        collectionProvider: (Int) -> List<T>
    ): List<SortBenchmarkResult> {
        return sizeRange.map { size ->
            val collection = collectionProvider(size)
            benchmark(strategy, collection)
        }
    }

    /**
     * Executes a benchmark for different data patterns (sorted, reverse, random, etc.).
     *
     * @param strategy Strategy to test
     * @param size Collection size
     * @param patterns Map of pattern names to collection providers
     * @return Map of pattern names to benchmark results
     */
    fun <T : Comparable<T>> benchmarkByPattern(
        strategy: SortStrategy<T>,
        size: Int,
        patterns: Map<String, (Int) -> List<T>>
    ): Map<String, SortBenchmarkResult> {
        return patterns.mapValues { (_, provider) ->
            val collection = provider(size)
            benchmark(strategy, collection)
        }
    }

    /**
     * Returns the fastest result so far.
     */
    fun getFastest(): SortBenchmarkResult? {
        return results.minByOrNull { it.executionTimeNanos }
    }

    /**
     * Returns the slowest result so far.
     */
    fun getSlowest(): SortBenchmarkResult? {
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
    fun getAllResults(): List<SortBenchmarkResult> = results.toList()

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
        println("📊 SORT BENCHMARK COMPARATIVE REPORT")
        println("=".repeat(80))

        grouped.forEach { (algorithmName, algorithmResults) ->
            println("\n🔄 $algorithmName")
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
            val first = algorithmResults.first()
            println("  ├─ Average: ${String.format("%.4f", avg)} ms")
            println("  ├─ Complexity: ${first.timeComplexity}")
            println("  ├─ Space: ${first.spaceComplexity}")
            println("  └─ Stable: ${if (first.isStable) "✅ Yes" else "❌ No"}")
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

        println("\n" + "═".repeat(120))
        println("║ " + "ALGORITHM".padEnd(20) + "║ " + "SIZE".padEnd(10) + "║ " +
            "TIME (ms)".padEnd(15) + "║ " + "COMPLEXITY".padEnd(20) + "║ " +
            "SPACE".padEnd(10) + "║ " + "STABLE".padEnd(8) + "║")
        println("═".repeat(120))

        results.forEach { result ->
            println("║ " + result.algorithmName.padEnd(20) + "║ " +
                result.collectionSize.toString().padEnd(10) + "║ " +
                String.format("%.6f", result.executionTimeMillis).padEnd(15) + "║ " +
                result.timeComplexity.padEnd(20) + "║ " +
                result.spaceComplexity.padEnd(10) + "║ " +
                (if (result.isStable) "✅ Yes" else "❌ No").padEnd(8) + "║")
        }

        println("═".repeat(120) + "\n")
    }

    /**
     * Displays a detailed comparison chart between two algorithms.
     */
    fun printComparisonChart(algorithm1: String, algorithm2: String) {
        val results1 = results.filter { it.algorithmName == algorithm1 }
        val results2 = results.filter { it.algorithmName == algorithm2 }

        if (results1.isEmpty() || results2.isEmpty()) {
            println("❌ Not enough data for comparison.")
            return
        }

        println("\n" + "=".repeat(80))
        println("📊 COMPARISON: $algorithm1 vs $algorithm2")
        println("=".repeat(80))

        val maxSize = maxOf(results1.size, results2.size)
        for (i in 0 until maxSize) {
            val r1 = results1.getOrNull(i)
            val r2 = results2.getOrNull(i)

            if (r1 != null && r2 != null) {
                val diff = ((r1.executionTimeMillis - r2.executionTimeMillis) / r2.executionTimeMillis) * 100
                val winner = if (r1.executionTimeNanos < r2.executionTimeNanos) algorithm1 else algorithm2

                println("\nSize: ${r1.collectionSize}")
                println("  $algorithm1: ${String.format("%.4f", r1.executionTimeMillis)} ms")
                println("  $algorithm2: ${String.format("%.4f", r2.executionTimeMillis)} ms")
                println("  Winner: ⚡ $winner (${String.format("%.2f", kotlin.math.abs(diff))}% ${if (diff > 0) "faster" else "slower"})")
            }
        }

        println("\n" + "=".repeat(80) + "\n")
    }
}
