package digital.tonima.dataestructures.graph

/**
 * Demo application showing Dijkstra's algorithm step-by-step execution.
 * Run this file to see detailed visualization of how the algorithm works.
 */
fun main() {
    println("═══════════════════════════════════════════════════════════════")
    println("    DIJKSTRA'S ALGORITHM - INTERACTIVE DEMONSTRATION")
    println("═══════════════════════════════════════════════════════════════")
    println()

    // Example 1: Simple graph
    println("EXAMPLE 1: Simple Graph")
    println("─────────────────────────")
    val graph1 = Graph<String>().apply {
        addEdge("A", "B", weight = 4)
        addEdge("A", "C", weight = 2)
        addEdge("B", "D", weight = 5)
        addEdge("C", "D", weight = 8)
        addEdge("C", "E", weight = 10)
        addEdge("D", "E", weight = 2)
    }

    println("Graph structure:")
    println("  A --4--> B --5--> D")
    println("  |                 |")
    println("  2                 2")
    println("  |                 |")
    println("  C --8--> D --10-> E")
    println()

    // Run WITHOUT verbose mode (normal output)
    println("▶ Running WITHOUT verbose mode:")
    graph1.dijkstra("A", "E")

    println("\n" + "═".repeat(63) + "\n")

    // Run WITH verbose mode (detailed step-by-step)
    println("▶ Running WITH verbose mode (verbose=true):")
    graph1.dijkstra("A", "E", verbose = true)

    println("\n" + "═".repeat(63) + "\n")

    // Example 2: More complex graph
    println("EXAMPLE 2: Complex Graph with Multiple Paths")
    println("─────────────────────────────────────────────")
    val graph2 = Graph<Int>().apply {
        addEdge(1, 2, weight = 7)
        addEdge(1, 3, weight = 9)
        addEdge(1, 6, weight = 14)
        addEdge(2, 3, weight = 10)
        addEdge(2, 4, weight = 15)
        addEdge(3, 4, weight = 11)
        addEdge(3, 6, weight = 2)
        addEdge(4, 5, weight = 6)
        addEdge(5, 6, weight = 9)
    }

    println("Graph structure: 1 → {2(7), 3(9), 6(14)}")
    println("                 2 → {3(10), 4(15)}")
    println("                 3 → {4(11), 6(2)}")
    println("                 4 → {5(6)}")
    println("                 5 → {6(9)}")
    println()

    println("▶ Finding path from 1 to 5 (verbose mode):")
    graph2.dijkstra(1, 5, verbose = true)

    println("\n" + "═".repeat(63) + "\n")

    // Example 3: No path exists
    println("EXAMPLE 3: Disconnected Graph (No Path)")
    println("────────────────────────────────────────")
    val graph3 = Graph<String>().apply {
        addEdge("A", "B", weight = 5)
        addEdge("B", "C", weight = 3)
        addEdge("X", "Y", weight = 2)
    }

    println("Graph has two disconnected components:")
    println("  Component 1: A → B → C")
    println("  Component 2: X → Y")
    println()

    println("▶ Trying to find path from A to X (verbose mode):")
    graph3.dijkstra("A", "X", verbose = true)

    println("\n" + "═".repeat(63))
    println("Demo completed! Try creating your own graphs and exploring them.")
    println("═".repeat(63))
}
