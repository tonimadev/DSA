package digital.tonima.search.algorithms

import digital.tonima.search.core.SearchStrategy

/**
 * Implementação da Busca Linear.
 * Percorre sequencialmente a coleção até encontrar o elemento.
 *
 * Complexidade:
 * - Melhor caso: O(1) - elemento na primeira posição
 * - Caso médio: O(n/2) ≈ O(n)
 * - Pior caso: O(n) - elemento no final ou não existe
 *
 * Características:
 * - Funciona com listas ordenadas ou não ordenadas
 * - Simples de implementar
 * - Ideal para listas pequenas ou buscas únicas
 */
class LinearSearch<T : Comparable<T>> : SearchStrategy<T> {

    override fun search(collection: List<T>, target: T): Int {
        collection.forEachIndexed { index, t ->
            if (t == target) return index
        }
        return -1
    }

    override fun name(): String = "Linear Search"
}

