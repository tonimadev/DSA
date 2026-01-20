package digital.tonima.dataestructures.graph

class Graph<T> {
    // Map where Key is the vertex and Value is the list of neighbors
    private val adjacencyList = mutableMapOf<T, MutableList<T>>()

    /**
     * Adds an edge to the graph.
     * @param source The source node.
     * @param destination The destination node.
     * @param bidirectional If true, creates a return edge (undirected graph).
     */
    fun addEdge(
        source: T,
        destination: T,
        bidirectional: Boolean = false
    ) {
        // getOrPut is an idiomatic Kotlin function:
        // If the key doesn't exist, it creates a new list, puts it in the map, and returns it.
        adjacencyList.getOrPut(source) { mutableListOf() }.add(destination)

        if (bidirectional) {
            adjacencyList.getOrPut(destination) { mutableListOf() }.add(source)
        }
    }

    /**
     * Performs Breadth-First Search (BFS).
     */
    fun breadthFirstSearch(startNode: T) {
        if (!adjacencyList.contains(startNode)) {
            println("The start node does not exist in the graph.")
            return
        }
        // 1. Queue to store nodes to be visited (FIFO)
        // ArrayDeque is more performant than LinkedList for queues in Kotlin
        val queue = ArrayDeque<T>()

        // 2. Set to track visited nodes (prevents infinite cycles)
        val visited = mutableSetOf<T>()

        queue.add(startNode)
        visited.add(startNode)

        println("Starting BFS from '$startNode':")

        while (queue.isNotEmpty()) {
            // Dequeue the first element
            val currentNode = queue.removeFirst()

            // --- PROCESS THE NODE HERE ---
            print("$currentNode -> ")

            // Get neighbors of the current node
            val neighbors = adjacencyList[currentNode] ?: emptyList()

            for (neighbor in neighbors) {
                // If neighbor has not been visited yet
                if (neighbor !in visited) {
                    visited.add(neighbor)// Mark as visited
                    queue.add(neighbor)// Enqueue for future visit
                }
            }
            println("End")
        }
    }

    // Helper method to print the graph structure
    fun printGraph() {
        adjacencyList.forEach { (node, neighbors) ->
            println("$node: $neighbors")
        }
    }
}
