package digital.tonima.search.core

import digital.tonima.search.algorithms.BinarySearch
import digital.tonima.search.algorithms.LinearSearch

/**
 * Factory que cria instâncias dos diferentes algoritmos de busca.
 * Implementa o padrão Factory para centralizar a criação das estratégias.
 *
 * Uso:
 * ```kotlin
 * val strategy = SearchFactory.create<Int>(SearchType.BINARY)
 * val index = strategy.search(list, target)
 * ```
 */
object SearchFactory {

    /**
     * Cria uma instância do algoritmo de busca especificado.
     *
     * @param T Tipo genérico do elemento (deve ser Comparable)
     * @param type Tipo de busca desejado (LINEAR, BINARY, etc.)
     * @return Instância de SearchStrategy configurada
     */
    fun <T : Comparable<T>> create(type: SearchType): SearchStrategy<T> {
        return when (type) {
            SearchType.LINEAR -> LinearSearch()
            SearchType.BINARY -> BinarySearch()
            // Adicione novos tipos aqui conforme implementar:
            // SearchType.INTERPOLATION -> InterpolationSearch()
            // SearchType.JUMP_SEARCH -> JumpSearch()
            // SearchType.EXPONENTIAL -> ExponentialSearch()
        }
    }
}

