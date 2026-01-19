package digital.tonima.sort.benchmark

import digital.tonima.sort.algorithms.QuickSort
import digital.tonima.sort.algorithms.SelectionSort

/**
 * Interactive demonstration of sorting algorithm benchmarks.
 * Run this file to see sorting algorithms in action and compare their performance.
 */
fun main() {
    println("🔄 SORTING ALGORITHMS BENCHMARK DEMO")
    println("=" .repeat(80))

    val benchmark = SortBenchmark()

    // Create algorithm instances
    val selectionSort = SelectionSort<Int>()
    val quickSort = QuickSort<Int>()

    // Scenario 1: Small random list
    println("\n📊 Scenario 1: Small Random List (100 elements)")
    println("-".repeat(80))
    val smallList = BenchmarkDataGenerator.generateRandomList(100, 1000)

    val result1 = benchmark.benchmark(selectionSort, smallList)
    println(result1)

    val result2 = benchmark.benchmark(quickSort, smallList)
    println(result2)

    // Scenario 2: Medium random list
    println("\n📊 Scenario 2: Medium Random List (1,000 elements)")
    println("-".repeat(80))
    val mediumList = BenchmarkDataGenerator.generateRandomList(1000, 10000)

    benchmark.benchmark(selectionSort, mediumList)
    benchmark.benchmark(quickSort, mediumList)

    // Scenario 3: Large random list
    println("\n📊 Scenario 3: Large Random List (10,000 elements)")
    println("-".repeat(80))
    val largeList = BenchmarkDataGenerator.generateRandomList(10000, 100000)

    benchmark.benchmark(selectionSort, largeList)
    benchmark.benchmark(quickSort, largeList)

    // Scenario 4: Different data patterns (medium size)
    println("\n📊 Scenario 4: Different Data Patterns (1,000 elements each)")
    println("-".repeat(80))

    val patterns = mapOf<String, (Int) -> List<Int>>(
        "Random" to { size -> BenchmarkDataGenerator.generateRandomList(size) },
        "Sorted" to { size -> BenchmarkDataGenerator.generateSortedList(size) },
        "Reverse Sorted" to { size -> BenchmarkDataGenerator.generateReverseSortedList(size) },
        "Nearly Sorted" to { size -> BenchmarkDataGenerator.generateNearlySortedList(size) },
        "Many Duplicates" to { size -> BenchmarkDataGenerator.generateListWithDuplicates(size) }
    )

    patterns.forEach { (patternName, generator) ->
        println("\n  Pattern: $patternName")
        val data = generator(1000)
        benchmark.benchmark(selectionSort, data)
        benchmark.benchmark(quickSort, data)
    }

    // Display comparative report
    benchmark.printComparativeReport()

    // Display table report
    benchmark.printTableReport()

    // Compare two algorithms
    benchmark.printComparisonChart("Selection Sort", "Quick Sort")

    // Performance by size
    println("\n📈 Performance Scaling: Quick Sort")
    println("-".repeat(80))
    val sizeBenchmark = SortBenchmark()
    val sizeResults = sizeBenchmark.benchmarkBySize(
        quickSort,
        1000..10000 step 1000
    ) { size -> BenchmarkDataGenerator.generateRandomList(size) }

    sizeResults.forEach { result ->
        println("Size: ${String.format("%6d", result.collectionSize)} | " +
                "Time: ${String.format("%8.4f", result.executionTimeMillis)} ms")
    }

    println("\n✅ Benchmark completed!")
}
