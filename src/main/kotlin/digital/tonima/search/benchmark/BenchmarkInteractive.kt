package digital.tonima.search.benchmark

import digital.tonima.search.algorithms.BinarySearch
import digital.tonima.search.algorithms.LinearSearch
import digital.tonima.search.core.SearchStrategy

/**
 * Interactive application to test and visualize benchmarks.
 * Run the main() function to use it.
 */
fun main() {
    println("\n╔${"═".repeat(78)}╗")
    println("║ " + "🎯 SEARCH ALGORITHMS BENCHMARK SYSTEM".padEnd(76) + " ║")
    println("╚${"═".repeat(78)}╝\n")

    while (true) {
        println("\n${"─".repeat(80)}")
        println("MAIN MENU")
        println("${"─".repeat(80)}")
        println("1. Quick Test (1,000 elements)")
        println("2. Medium Test (100,000 elements)")
        println("3. Large Test (1,000,000 elements)")
        println("4. Custom Test (define the size)")
        println("5. Scalability Test")
        println("6. Compare Worst and Best Case")
        println("7. Exit")
        println("${"─".repeat(80)}")
        print("Choose an option (1-7): ")

        when (readLine()?.toIntOrNull()) {
            1 -> quickTest()
            2 -> mediumTest()
            3 -> largeTest()
            4 -> customTest()
            5 -> scalabilityTest()
            6 -> extremeCaseTest()
            7 -> {
                println("\n👋 Goodbye!\n")
                break
            }
            else -> println("❌ Invalid option! Try again.")
        }
    }
}

fun quickTest() {
    println("\n🔧 Running Quick Test (1,000 elements)...\n")

    val benchmark = SearchBenchmark()
    val list = BenchmarkDataGenerator.generateUnorderedList(1_000)
    val target = list[500]

    val linear = LinearSearch<Int>()
    val binary = BinarySearch<Int>()

    benchmark.benchmark(linear, list, target)
    benchmark.benchmark(binary, list, target)

    benchmark.printComparativeReport()
}

fun mediumTest() {
    println("\n🔧 Running Medium Test (100,000 elements)...\n")

    val benchmark = SearchBenchmark()
    val list = BenchmarkDataGenerator.generateUnorderedList(100_000)
    val target = list[50_000]

    val linear = LinearSearch<Int>()
    val binary = BinarySearch<Int>()

    benchmark.benchmark(linear, list, target)
    benchmark.benchmark(binary, list, target)

    benchmark.printComparativeReport()
}

fun largeTest() {
    println("\n🔧 Running Large Test (1,000,000 elements)...\n")
    println("⏳ This may take a few seconds...\n")

    val benchmark = SearchBenchmark()
    val list = BenchmarkDataGenerator.generateUnorderedList(1_000_000)
    val target = list[500_000]

    val linear = LinearSearch<Int>()
    val binary = BinarySearch<Int>()

    println("  Testing LinearSearch...")
    benchmark.benchmark(linear, list, target)

    println("  Testing BinarySearch...")
    benchmark.benchmark(binary, list, target)

    println()
    benchmark.printComparativeReport()
}

fun customTest() {
    println("\n🔧 Custom Test")
    print("Enter list size: ")
    val size = readLine()?.toIntOrNull() ?: 10_000

    if (size < 1) {
        println("❌ Size must be greater than 0")
        return
    }

    println("\n⏳ Generating list with $size elements...")
    val benchmark = SearchBenchmark()
    val list = BenchmarkDataGenerator.generateUnorderedList(size)
    val target = list[size / 2]

    val linear = LinearSearch<Int>()
    val binary = BinarySearch<Int>()

    println("  Testing LinearSearch...")
    benchmark.benchmark(linear, list, target)

    println("  Testing BinarySearch...")
    benchmark.benchmark(binary, list, target)

    println()
    benchmark.printComparativeReport()
}

fun scalabilityTest() {
    println("\n📈 Scalability Test")
    println("Measuring performance with different list sizes...\n")

    val benchmark = SearchBenchmark()
    val linear = LinearSearch<Int>()
    val binary = BinarySearch<Int>()

    println("╔${"═".repeat(78)}╗")
    println("║ " + "LINEAR SEARCH - Scalability".padStart(50).padEnd(76) + " ║")
    println("╠${"═".repeat(78)}╣")

    val sizes = intArrayOf(1_000, 10_000, 100_000, 1_000_000)
    sizes.forEach { size ->
        val list = BenchmarkDataGenerator.generateUnorderedList(size)
        val result = benchmark.benchmark(linear, list, list[size / 2])
        val time = String.format("%.6f", result.executionTimeMillis)
        println("║  Size: ${String.format("%8d", size)} elements | Time: $time ms${" ".repeat(40 - time.length)}║")
    }

    println("╠${"═".repeat(78)}╣")
    println("║ " + "BINARY SEARCH - Scalability".padStart(50).padEnd(76) + " ║")
    println("╠${"═".repeat(78)}╣")

    sizes.forEach { size ->
        val list = BenchmarkDataGenerator.generateOrderedList(size)
        val result = benchmark.benchmark(binary, list, list[size / 2])
        val time = String.format("%.6f", result.executionTimeMillis)
        println("║  Size: ${String.format("%8d", size)} elements | Time: $time ms${" ".repeat(40 - time.length)}║")
    }

    println("╚${"═".repeat(78)}╝")

    println("\n💡 Observation: See how Linear grows linearly while Binary grows logarithmically!")
}

fun extremeCaseTest() {
    println("\n🔥 Extreme Cases Test (Worst and Best Case)")
    print("Enter list size (default 100,000): ")
    val size = readLine()?.toIntOrNull() ?: 100_000

    println("\n⏳ Generating list with $size elements...\n")

    val benchmark = SearchBenchmark()
    val list = BenchmarkDataGenerator.generateUnorderedList(size)
    val linear = LinearSearch<Int>()

    println("╔${"═".repeat(78)}╗")
    println("║ " + "LINEAR SEARCH - EXTREME CASES ANALYSIS".padStart(50).padEnd(76) + " ║")
    println("╠${"═".repeat(78)}╣")

    // Best case
    println("║ ✅ BEST CASE: Element at the beginning of the list".padEnd(78) + " ║")
    println("╠${"═".repeat(78)}╣")
    val best = benchmark.benchmark(linear, list, list.first())
    println("║  Time: ${String.format("%.6f", best.executionTimeMillis)} ms | Index found: ${best.indexFound}${" ".repeat(42)}║")

    // Average case
    println("╠${"═".repeat(78)}╣")
    println("║ ➡️  AVERAGE CASE: Element in the middle of the list".padEnd(78) + " ║")
    println("╠${"═".repeat(78)}╣")
    val medium = benchmark.benchmark(linear, list, list[size / 2])
    println("║  Time: ${String.format("%.6f", medium.executionTimeMillis)} ms | Index found: ${medium.indexFound}${" ".repeat(42)}║")

    // Worst case
    println("╠${"═".repeat(78)}╣")
    println("║ ❌ WORST CASE: Element at the end of the list".padEnd(78) + " ║")
    println("╠${"═".repeat(78)}╣")
    val worst = benchmark.benchmark(linear, list, list.last())
    println("║  Time: ${String.format("%.6f", worst.executionTimeMillis)} ms | Index found: ${worst.indexFound}${" ".repeat(40)}║")

    // Element not found
    println("╠${"═".repeat(78)}╣")
    println("║ 🔍 NOT FOUND: Element does not exist in the list".padEnd(78) + " ║")
    println("╠${"═".repeat(78)}╣")
    val notFound = benchmark.benchmark(linear, list, -999)
    println("║  Time: ${String.format("%.6f", notFound.executionTimeMillis)} ms | Index: ${notFound.indexFound}${" ".repeat(54)}║")

    println("╠${"═".repeat(78)}╣")
    val ratio = worst.executionTimeMillis / best.executionTimeMillis
    println("║  RATIO (Worst/Best): ${String.format("%.2f", ratio)}x${" ".repeat(60)}║")
    println("╚${"═".repeat(78)}╝")

    println("\n💡 Insight: Linear Search is O(n), so worst case is ~2x slower than best case")
}

