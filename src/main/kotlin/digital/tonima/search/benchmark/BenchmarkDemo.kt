package digital.tonima.search.benchmark

import digital.tonima.search.algorithms.BinarySearch
import digital.tonima.search.algorithms.LinearSearch
import digital.tonima.search.core.SearchStrategy

/**
 * Exemplo de uso prático dos benchmarks.
 * Execute a função main() para ver os resultados.
 */
fun main() {
    println("🚀 INICIANDO BENCHMARKS DE ALGORITMOS DE BUSCA\n")

    // ==================== TESTE 1: Comparação Linear vs Binary ====================
    println("\n" + "▓".repeat(80))
    println("TESTE 1: Linear vs Binary Search em Lista ORDENADA")
    println("▓".repeat(80))

    val benchmark1 = SearchBenchmark()
    val orderedList = BenchmarkDataGenerator.generateOrderedList(1_000_000)
    val targetOrdered = orderedList[500_000]

    val linearStrategy: SearchStrategy<Int> = LinearSearch()
    val binaryStrategy: SearchStrategy<Int> = BinarySearch()

    val result1 = benchmark1.benchmark(linearStrategy, orderedList, targetOrdered)
    println(result1)

    val result2 = benchmark1.benchmark(binaryStrategy, orderedList, targetOrdered)
    println(result2)

    benchmark1.printComparativeReport()

    // ==================== TESTE 2: Escalabilidade ====================
    println("\n" + "▓".repeat(80))
    println("TESTE 2: ESCALABILIDADE - Como algoritmos escalam com tamanho")
    println("▓".repeat(80))

    val benchmark2 = SearchBenchmark()
    val sizes = intArrayOf(10_000, 50_000, 100_000, 500_000, 1_000_000)

    println("\n📈 LinearSearch - Diferentes tamanhos de coleção:")
    println("─".repeat(80))
    sizes.forEach { size ->
        val list = BenchmarkDataGenerator.generateUnorderedList(size)
        val target = list[size / 2]
        val result = benchmark2.benchmark(linearStrategy, list, target)
        println("  Size: ${String.format("%7d", size)} | Tempo: ${String.format("%.4f", result.executionTimeMillis)} ms")
    }

    println("\n📈 BinarySearch - Diferentes tamanhos de coleção (lista ordenada):")
    println("─".repeat(80))
    sizes.forEach { size ->
        val list = BenchmarkDataGenerator.generateOrderedList(size)
        val target = list[size / 2]
        val result = benchmark2.benchmark(binaryStrategy, list, target)
        println("  Size: ${String.format("%7d", size)} | Tempo: ${String.format("%.4f", result.executionTimeMillis)} ms")
    }

    benchmark2.printTableReport()

    // ==================== TESTE 3: Pior Caso vs Melhor Caso ====================
    println("\n" + "▓".repeat(80))
    println("TESTE 3: PIOR CASO vs MELHOR CASO")
    println("▓".repeat(80))

    val benchmark3 = SearchBenchmark()
    val list = BenchmarkDataGenerator.generateUnorderedList(100_000)

    // Melhor caso: elemento no início
    val best = list.first()
    println("\n✅ MELHOR CASO (elemento no início):")
    benchmark3.benchmark(linearStrategy, list, best).let { println(it) }

    // Pior caso: elemento no final
    val worst = list.last()
    println("\n❌ PIOR CASO (elemento no final):")
    benchmark3.benchmark(linearStrategy, list, worst).let { println(it) }

    // Elemento não encontrado
    val notFound = -999
    println("\n❌ ELEMENTO NÃO ENCONTRADO:")
    benchmark3.benchmark(linearStrategy, list, notFound).let { println(it) }

    // ==================== RESUMO FINAL ====================
    println("\n" + "╔".padEnd(80, '═') + "╗")
    println("║ " + "BENCHMARKS CONCLUÍDOS COM SUCESSO!".padEnd(76) + " ║")
    println("╚".padEnd(80, '═') + "╝\n")

    println("💡 DICAS:")
    println("  • LinearSearch: O(n) - Bom para listas pequenas ou não ordenadas")
    println("  • BinarySearch: O(log n) - Excelente para listas grandes e ordenadas")
    println("  • Sempre ordene seus dados se souber que fará múltiplas buscas!")
    println("  • Para listas muito grandes, binary é significativamente mais rápido\n")
}

