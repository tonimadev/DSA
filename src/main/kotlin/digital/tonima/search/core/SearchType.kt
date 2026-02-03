package digital.tonima.search.core

/**
 * Enumeração que especifica os tipos de algoritmos de busca disponíveis.
 *
 * Tipos disponíveis:
 * - LINEAR: Busca sequencial, funciona em qualquer lista
 * - BINARY: Busca binária, requer lista ordenada
 */
internal enum class SearchType {
    /**
     * Busca Linear - O(n)
     * Funciona com listas ordenadas ou não ordenadas.
     */
    LINEAR,

    /**
     * Busca Binária - O(log n)
     * Requer lista ordenada.
     */
    BINARY,

    // Adicione outros tipos de busca conforme necessário:
    // INTERPOLATION,  // Busca por interpolação
    // JUMP_SEARCH,    // Jump search
    // EXPONENTIAL,    // Busca exponencial
    // FIBONACCI,      // Busca fibonacci
}

