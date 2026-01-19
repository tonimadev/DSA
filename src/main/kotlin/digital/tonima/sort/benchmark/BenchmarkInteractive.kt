package digital.tonima.sort.benchmark

import digital.tonima.sort.algorithms.QuickSort
import digital.tonima.sort.algorithms.SelectionSort
import digital.tonima.sort.core.SortStrategy

/**
 * Interactive benchmark utility for sorting algorithms.
 * Allows users to run custom benchmarks with different configurations.
 */
class BenchmarkInteractive {

    private val benchmark = SortBenchmark()
    private val algorithms = mutableMapOf<String, SortStrategy<Int>>()

    init {
        // Register available algorithms
        algorithms["Selection Sort"] = SelectionSort()
        algorithms["Quick Sort"] = QuickSort()
    }

    /**
     * Runs a quick comparison between all available algorithms.
     */
    fun quickComparison(size: Int = 1000) {
        println("\n⚡ QUICK COMPARISON - All Algorithms")
        println("Collection size: $size elements")
        println("-".repeat(80))

        val data = BenchmarkDataGenerator.generateRandomList(size)

        algorithms.forEach { (name, strategy) ->
            val result = benchmark.benchmark(strategy, data)
            println("$name: ${String.format("%.4f", result.executionTimeMillis)} ms")
        }
    }

    /**
     * Runs a benchmark for a specific algorithm across multiple sizes.
     */
    fun benchmarkAcrossSizes(algorithmName: String, sizeRange: IntProgression) {
        val strategy = algorithms[algorithmName] ?: run {
            println("❌ Algorithm '$algorithmName' not found!")
            return
        }

        println("\n📈 SCALING BENCHMARK - $algorithmName")
        println("-".repeat(80))

        val results = benchmark.benchmarkBySize(strategy, sizeRange) { size ->
            BenchmarkDataGenerator.generateRandomList(size)
        }

        println("\nSize → Time")
        results.forEach { result ->
            val bar = "█".repeat((result.executionTimeMillis / 10).toInt().coerceIn(0, 50))
            println("${String.format("%7d", result.collectionSize)} → " +
                    "${String.format("%8.4f", result.executionTimeMillis)} ms $bar")
        }
    }

    /**
     * Runs a benchmark for all data patterns.
     */
    fun benchmarkAllPatterns(size: Int = 1000) {
        println("\n🎨 PATTERN BENCHMARK - All Data Patterns")
        println("Collection size: $size elements")
        println("-".repeat(80))

        val patterns = BenchmarkDataGenerator.getAllPatterns(size)

        algorithms.forEach { (algorithmName, strategy) ->
            println("\n🔄 $algorithmName")
            patterns.forEach { (patternName, data) ->
                val result = benchmark.benchmark(strategy, data)
                println("  ${patternName.padEnd(20)} → ${String.format("%.4f", result.executionTimeMillis)} ms")
            }
        }
    }

    /**
     * Compares two specific algorithms.
     */
    fun compareAlgorithms(algorithm1: String, algorithm2: String, size: Int = 1000) {
        val strategy1 = algorithms[algorithm1]
        val strategy2 = algorithms[algorithm2]

        if (strategy1 == null || strategy2 == null) {
            println("❌ One or both algorithms not found!")
            return
        }

        println("\n⚔️  HEAD-TO-HEAD COMPARISON")
        println("$algorithm1 vs $algorithm2")
        println("Collection size: $size elements")
        println("-".repeat(80))

        val data = BenchmarkDataGenerator.generateRandomList(size)

        val result1 = benchmark.benchmark(strategy1, data)
        val result2 = benchmark.benchmark(strategy2, data)

        println("\n$algorithm1:")
        println("  Time: ${String.format("%.4f", result1.executionTimeMillis)} ms")
        println("  Complexity: ${result1.timeComplexity}")
        println("  Space: ${result1.spaceComplexity}")
        println("  Stable: ${if (result1.isStable) "✅ Yes" else "❌ No"}")

        println("\n$algorithm2:")
        println("  Time: ${String.format("%.4f", result2.executionTimeMillis)} ms")
        println("  Complexity: ${result2.timeComplexity}")
        println("  Space: ${result2.spaceComplexity}")
        println("  Stable: ${if (result2.isStable) "✅ Yes" else "❌ No"}")

        val faster = if (result1.executionTimeNanos < result2.executionTimeNanos) algorithm1 else algorithm2
        val speedup = maxOf(result1.executionTimeNanos, result2.executionTimeNanos).toDouble() /
                      minOf(result1.executionTimeNanos, result2.executionTimeNanos)

        println("\n⚡ Winner: $faster (${String.format("%.2f", speedup)}x faster)")
    }

    /**
     * Displays information about all available algorithms.
     */
    fun showAlgorithms() {
        println("\n📚 AVAILABLE ALGORITHMS")
        println("-".repeat(80))

        algorithms.forEach { (name, strategy) ->
            println("\n🔄 $name")
            println("  Time Complexity: ${strategy.timeComplexity()}")
            println("  Space Complexity: ${strategy.spaceComplexity()}")
            println("  Stable: ${if (strategy.isStable()) "✅ Yes" else "❌ No"}")
        }
    }

    /**
     * Generates a full benchmark report.
     */
    fun generateFullReport(sizes: List<Int> = listOf(100, 500, 1000, 5000)) {
        println("\n📊 FULL BENCHMARK REPORT")
        println("=".repeat(80))

        benchmark.reset()

        sizes.forEach { size ->
            println("\n📌 Testing with $size elements...")
            val data = BenchmarkDataGenerator.generateRandomList(size)

            algorithms.forEach { (_, strategy) ->
                benchmark.benchmark(strategy, data)
            }
        }

        benchmark.printComparativeReport()
    }

    /**
     * Displays the benchmark menu.
     */
    fun displayMenu() {
        println("\n" + "=".repeat(80))
        println("🔄 SORTING ALGORITHM BENCHMARK UTILITY")
        println("=".repeat(80))
        println("\nAvailable Commands:")
        println("  1. Quick Comparison")
        println("  2. Benchmark Across Sizes")
        println("  3. Benchmark All Patterns")
        println("  4. Compare Two Algorithms")
        println("  5. Show Available Algorithms")
        println("  6. Generate Full Report")
        println("  7. Exit")
        println("-".repeat(80))
    }
}

/**
 * Main function to run interactive benchmark.
 */
fun main() {
    val interactive = BenchmarkInteractive()

    println("🔄 SORTING ALGORITHMS - INTERACTIVE BENCHMARK")
    println("=" .repeat(80))

    // Run some predefined benchmarks
    interactive.showAlgorithms()
    interactive.quickComparison(1000)
    interactive.compareAlgorithms("Selection Sort", "Quick Sort", 1000)
    interactive.benchmarkAcrossSizes("Quick Sort", 1000..10000 step 1000)
    interactive.benchmarkAllPatterns(1000)
    interactive.generateFullReport(listOf(100, 500, 1000, 2000))

    println("\n✅ Benchmark completed!")
}
