package digital.tonima.search.algorithms

import digital.tonima.search.core.SearchStrategy

/**
 * Implementação da Busca Binária.
 * Requer que a coleção esteja ordenada.
 * Usa a estratégia "dividir para conquistar" para buscar o elemento.
 *
 * Complexidade:
 * - Melhor caso: O(1) - elemento no meio
 * - Caso médio: O(log n)
 * - Pior caso: O(log n)
 *
 * Características:
 * - Requer lista ordenada
 * - Muito eficiente para listas grandes
 * - Ideal para múltiplas buscas
 *
 * @throws IllegalStateException se a lista não estiver ordenada (opcional validação)
 */
internal class BinarySearch<T : Comparable<T>> : SearchStrategy<T> {

    override fun search(collection: List<T>, target: T): Int {
        var lowIndex = 0
        var highIndex = collection.size - 1

        while (lowIndex <= highIndex) {
            val guessIndex = (lowIndex + highIndex) / 2
            val guess = collection[guessIndex]

            if (guess == target) return guessIndex
            if (guess > target) {
                highIndex = guessIndex - 1
            } else {
                lowIndex = guessIndex + 1
            }
        }
        return -1
    }

    override fun name(): String = "Binary Search"
}

