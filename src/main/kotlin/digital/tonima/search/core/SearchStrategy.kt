package digital.tonima.search.core

/**
 * Interface que define o contrato para algoritmos de busca.
 * Implementa o padrão Strategy para permitir diferentes estratégias de busca.
 *
 * @param T Tipo genérico do elemento a ser buscado (deve ser comparável)
 */
interface SearchStrategy<T : Comparable<T>> {

    /**
     * Realiza a busca de um elemento na coleção.
     *
     * @param collection Coleção onde buscar (deve estar ordenada quando necessário)
     * @param target Elemento a ser buscado
     * @return O índice do elemento se encontrado, -1 caso contrário
     */
    fun search(collection: List<T>, target: T): Int

    /**
     * Retorna o nome descritivo do algoritmo de busca.
     *
     * @return String com o nome do algoritmo
     */
    fun name(): String
}

