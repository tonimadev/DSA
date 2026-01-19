package digital.tonima.sort.benchmark

import digital.tonima.sort.algorithms.QuickSort
import digital.tonima.sort.algorithms.SelectionSort

/**
 * Simple example demonstrating how to use the sorting benchmark.
 * This is a minimal, easy-to-understand demonstration.
 */
fun main() {
    println("🔄 SORTING ALGORITHMS - BENCHMARK EXAMPLE")
    println("=".repeat(80))

    // Create benchmark instance
    val benchmark = SortBenchmark()

    // Create algorithms
    val selectionSort = SelectionSort<Int>()
    val quickSort = QuickSort<Int>()

    // Test with different sizes
    val sizes = listOf(100, 500, 1000, 5000, 10000)

    println("\n📊 Comparing Selection Sort vs Quick Sort")
    println("-".repeat(80))
    println("Size".padEnd(15) + "Selection Sort".padEnd(20) + "Quick Sort".padEnd(20) + "Winner")
    println("-".repeat(80))

    sizes.forEach { size ->
        val data = BenchmarkDataGenerator.generateRandomList(size)

        val result1 = benchmark.benchmark(selectionSort, data)
        val result2 = benchmark.benchmark(quickSort, data)

        val winner = if (result1.executionTimeNanos < result2.executionTimeNanos) {
            "Selection ⚡"
        } else {
            "Quick ⚡"
        }

        val speedup = maxOf(result1.executionTimeNanos, result2.executionTimeNanos).toDouble() /
                      minOf(result1.executionTimeNanos, result2.executionTimeNanos)

        println(
            size.toString().padEnd(15) +
            String.format("%.4f ms", result1.executionTimeMillis).padEnd(20) +
            String.format("%.4f ms", result2.executionTimeMillis).padEnd(20) +
            "$winner (${String.format("%.1f", speedup)}x faster)"
        )
    }

    // Show detailed report
    println("\n" + "=".repeat(80))
    benchmark.printComparativeReport()

    println("\n📈 Performance Analysis:")
    println("-".repeat(80))
    println("• Selection Sort: O(n²) - Performance degrades quadratically")
    println("• Quick Sort: O(n log n) - Much better for large datasets")
    println()
    println("💡 Recommendation: Use Quick Sort for datasets larger than 100 elements")
}
