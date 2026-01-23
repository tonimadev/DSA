package digital.tonima.dataestructures.graph

fun main() {
    val grafo = Graph<String>()

    grafo.addEdge("Casa", "Escola", weight = 10)
    grafo.addEdge("Escola", "Padaria", weight = 2)
    grafo.addEdge("Casa", "Padaria", weight = 15) // Caminho mais longo direto

    grafo.dijkstra("Casa", "Padaria")
}
