package digital.tonima.search.benchmark

/**
 * Classe utilitária que fornece dados para testes de benchmark.
 */
object BenchmarkDataGenerator {

    /**
     * Gera uma lista de inteiros não ordenada.
     */
    fun generateUnorderedList(size: Int, maxValue: Int = Int.MAX_VALUE): List<Int> {
        return (1..size).map { (Math.random() * maxValue).toInt() }
    }

    /**
     * Gera uma lista de inteiros ordenada (ideal para binary search).
     */
    fun generateOrderedList(size: Int): List<Int> {
        return (1..size).map { it * 2 }
    }

    /**
     * Gera uma lista de Strings não ordenada.
     */
    fun generateUnorderedStrings(size: Int): List<String> {
        return (1..size).map { "item_${(Math.random() * 10000).toInt()}" }
    }

    /**
     * Gera uma lista de Strings ordenada.
     */
    fun generateOrderedStrings(size: Int): List<String> {
        return (1..size).map { index -> "item_${String.format("%05d", index)}" }
    }
}


