package digital.tonima.dataestructures.graph

/**
 * EXEMPLO SIMPLES - Execute este arquivo para ver o Dijkstra passo a passo!
 *
 * Para executar:
 * 1. No IntelliJ: Clique com o botão direito neste arquivo e selecione "Run"
 * 2. Ou no terminal: ./gradlew run
 */
fun main() {
    println("╔══════════════════════════════════════════════════════════════╗")
    println("║    EXEMPLO SIMPLES - DIJKSTRA PASSO A PASSO                 ║")
    println("╚══════════════════════════════════════════════════════════════╝")
    println()

    // Criar um grafo simples
    val graph = Graph<String>()

    println("Criando o grafo:")
    println()
    println("        4         5")
    println("    A -----> B -----> D")
    println("    |                 |")
    println("    | 2               | 2")
    println("    |                 |")
    println("    └-----> C         |")
    println("           |          |")
    println("           └-- 8 -----┘")
    println("           |")
    println("           └-- 10 --> E")
    println()

    graph.addEdge("A", "B", weight = 4)
    graph.addEdge("A", "C", weight = 2)
    graph.addEdge("B", "D", weight = 5)
    graph.addEdge("C", "D", weight = 8)
    graph.addEdge("C", "E", weight = 10)
    graph.addEdge("D", "E", weight = 2)

    println("═══════════════════════════════════════════════════════════════")
    println("PARTE 1: Execução NORMAL (sem verbose)")
    println("═══════════════════════════════════════════════════════════════")
    println()

    graph.dijkstra("A", "E")

    println()
    println("═══════════════════════════════════════════════════════════════")
    println("PARTE 2: Execução DETALHADA (com verbose = true)")
    println("═══════════════════════════════════════════════════════════════")

    graph.dijkstra("A", "E", verbose = true)

    println()
    println("╔══════════════════════════════════════════════════════════════╗")
    println("║  AGORA VOCÊ PODE VER COMO O ALGORITMO FUNCIONA! 🎉          ║")
    println("╚══════════════════════════════════════════════════════════════╝")
    println()
    println("Observe que o algoritmo:")
    println("1. Começa no nó A com custo 0")
    println("2. Explora os vizinhos B e C")
    println("3. Sempre escolhe o nó com MENOR CUSTO da fila")
    println("4. Atualiza os custos quando encontra caminhos melhores")
    println("5. Para quando alcança o destino E")
    println("6. Reconstrói o caminho usando os 'parents'")
    println()
    println("Caminho encontrado: A -> B -> D -> E com custo total de 11")
    println("Este é o caminho MAIS BARATO, não necessariamente o com menos nós!")
}
