package digital.tonima.dataestructures.graph

import java.util.PriorityQueue

data class Edge<T>(val node: T, val weight: Int = 1)

class Graph<T> {
    // Map where Key is the vertex and Value is the list of neighbors
    private val adjacencyList = mutableMapOf<T, MutableList<Edge<T>>>()

    /**
     * Adds an edge to the graph.
     * @param source The source node.
     * @param destination The destination node.
     * @param bidirectional If true, creates a return edge (undirected graph).
     */
    fun addEdge(
        source: T,
        destination: T,
        weight: Int = 1,
        bidirectional: Boolean = false
    ) {
        // getOrPut is an idiomatic Kotlin function:
        // If the key doesn't exist, it creates a new list, puts it in the map, and returns it.
        adjacencyList.getOrPut(source) { mutableListOf() }.add(Edge(destination, weight))

        if (bidirectional) {
            adjacencyList.getOrPut(destination) { mutableListOf() }.add(Edge(source, weight))
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

            for (edge in neighbors) {
                // If neighbor has not been visited yet
                if (edge.node !in visited) {
                    visited.add(edge.node)// Mark as visited
                    queue.add(edge.node)// Enqueue for future visit
                }
            }
            println("End")
        }
    }

    /**
     * Performs Dijkstra's shortest path algorithm to find the minimum cost path
     * between a start node and an end node in a weighted graph.
     *
     * Algorithm steps:
     * 1. Initialize distances to all nodes as infinite, except start node (0)
     * 2. Use a priority queue to always process the node with minimum distance first
     * 3. For each node, explore all neighbors and update their costs if a shorter path is found
     * 4. Track parent nodes to reconstruct the shortest path
     *
     * Time Complexity: O((V + E) log V) where V is vertices and E is edges
     * Space Complexity: O(V)
     *
     * @param startNode The starting node for the path search
     * @param endNode The destination node
     * @param verbose If true, prints detailed step-by-step execution (default: false)
     */
    fun dijkstra(startNode: T, endNode: T, verbose: Boolean = false) {
        if (verbose) {
            println("\n╔═══════════════════════════════════════════════════════════════╗")
            println("║          DIJKSTRA'S ALGORITHM - STEP BY STEP                  ║")
            println("╚═══════════════════════════════════════════════════════════════╝")
            println("Searching for shortest path from '$startNode' to '$endNode'\n")
        }

        // Map to store the minimum cost to reach each node from the start
        // Default value is Int.MAX_VALUE (representing infinity)
        val costs = mutableMapOf<T, Int>().withDefault { Int.MAX_VALUE }

        // Map to track the parent of each node in the shortest path
        // Used to reconstruct the path from start to end
        val parents = mutableMapOf<T, T?>()

        // Set to track which nodes have been fully processed
        val visited = mutableSetOf<T>()

        // Priority queue to always process the node with the smallest cost first
        // Stores pairs of (node, cost) ordered by cost (ascending)
        val priorityQueue = PriorityQueue<Pair<T, Int>>(compareBy { it.second })

        // Initialize: cost to reach start node is 0
        costs[startNode] = 0
        priorityQueue.add(startNode to 0)

        if (verbose) {
            println("📍 STEP 0: INITIALIZATION")
            println("   • Start node: $startNode (cost = 0)")
            println("   • Priority Queue: [$startNode(0)]")
            println("   • All other nodes have cost = ∞")
            println()
        }

        var step = 1

        // Main loop: process nodes in order of increasing distance from start
        while (priorityQueue.isNotEmpty()) {
            // Get the node with minimum cost from the priority queue
            val (currentNode, currentCost) = priorityQueue.poll()

            // Skip if this node has already been processed
            // (Priority queue may contain duplicate entries with different costs)
            if (currentNode in visited) {
                if (verbose) {
                    println("   ⏭  Skipping node '$currentNode' (already visited)")
                }
                continue
            }

            if (verbose) {
                println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
                println("📍 STEP $step: PROCESSING NODE '$currentNode'")
                println("   • Current cost to reach '$currentNode': $currentCost")
                println("   • Visited nodes so far: $visited")
            }

            visited.add(currentNode)

            // Optimization: stop early if we reached the destination
            if (currentNode == endNode) {
                if (verbose) {
                    println("   🎯 DESTINATION REACHED! Stopping early.")
                    println()
                }
                break
            }

            // Get all outgoing edges from the current node
            val edges = adjacencyList[currentNode] ?: emptyList()

            if (verbose) {
                println("   • Neighbors of '$currentNode': ${edges.map { "${it.node}(weight=${it.weight})" }}")
                if (edges.isEmpty()) {
                    println("     → No neighbors to explore")
                }
            }

            // Explore all neighbors of the current node
            for (edge in edges) {
                val neighbor = edge.node
                val weight = edge.weight

                // Calculate the cost to reach this neighbor through the current node
                val newCost = currentCost + weight

                // Get the current best known cost to reach this neighbor
                val currentNeighborCost = costs[neighbor] ?: Int.MAX_VALUE
                val currentNeighborCostStr = if (currentNeighborCost == Int.MAX_VALUE) "∞" else currentNeighborCost.toString()

                if (verbose) {
                    println()
                    println("   🔍 Exploring neighbor '$neighbor':")
                    println("      • Edge weight: $weight")
                    println("      • New cost: $currentCost + $weight = $newCost")
                    println("      • Current best cost: $currentNeighborCostStr")
                }

                // If we found a cheaper path to the neighbor, update it
                if (newCost < currentNeighborCost) {
                    costs[neighbor] = newCost
                    parents[neighbor] = currentNode // Track the path
                    priorityQueue.add(neighbor to newCost) // Add to queue for processing

                    if (verbose) {
                        println("      ✅ UPDATED! New cost ($newCost) is better than $currentNeighborCostStr")
                        println("      • Updated path: ... → $currentNode → $neighbor")
                        println("      • Added to priority queue: $neighbor($newCost)")
                    }
                } else {
                    if (verbose) {
                        println("      ❌ No update. Current cost ($currentNeighborCostStr) is already better")
                    }
                }
            }

            if (verbose) {
                println()
                println("   📊 Current state after processing '$currentNode':")
                val costsSummary = costs.entries.sortedBy { it.value }
                    .joinToString(", ") { "${it.key}=${if (it.value == Int.MAX_VALUE) "∞" else it.value}" }
                println("      • Known costs: $costsSummary")
                val queueContents = priorityQueue.toList().sortedBy { it.second }
                    .joinToString(", ") { "${it.first}(${it.second})" }
                println("      • Priority queue: [$queueContents]")
                println()
            }

            step++
        }

        if (verbose) {
            println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            println("📍 RECONSTRUCTION PHASE")
            println("   • Tracing path backwards using parent pointers...")
            println()
        }

        // Print the result (shortest path and cost)
        printDijkstraResult(startNode, endNode, costs, parents, verbose)
    }

    fun printGraph() {
        adjacencyList.forEach { (node, neighbors) ->
            println("$node: $neighbors")
        }
    }

    /**
     * Helper function to print the result of Dijkstra's algorithm.
     * Displays the minimum cost and the actual path from start to end.
     *
     * @param start The starting node
     * @param end The destination node
     * @param costs Map containing the minimum cost to reach each node
     * @param parents Map containing the parent of each node in the shortest path
     * @param verbose If true, shows detailed path reconstruction steps
     */
    private fun printDijkstraResult(start: T, end: T, costs: Map<T, Int>, parents: Map<T, T?>, verbose: Boolean = false) {
        val finalCost = costs[end]

        // Check if there's no path to the destination
        if (finalCost == null || finalCost == Int.MAX_VALUE) {
            if (verbose) {
                println("❌ RESULT: No path exists")
                println()
            }
            println("Não há caminho entre $start e $end")
            return
        }

        // Reconstruct the path by following parent pointers backwards from end to start
        val path = mutableListOf<T>()
        var current: T? = end

        if (verbose) {
            println("   Step-by-step path reconstruction (backwards from end):")
        }

        while (current != null) {
            path.add(current)
            val parent = parents[current]

            if (verbose) {
                if (parent != null) {
                    println("      • $current ← parent is $parent")
                } else {
                    println("      • $current ← no parent (this is the start)")
                }
            }

            current = parent
            if (current == start) {
                path.add(start)
                if (verbose) {
                    println("      • $start ← reached start node!")
                }
                break
            }
        }

        // Display the result
        if (verbose) {
            println()
            println("╔═══════════════════════════════════════════════════════════════╗")
            println("║                      FINAL RESULT                             ║")
            println("╚═══════════════════════════════════════════════════════════════╝")
        }

        println("Custo Mínimo: $finalCost")
        println("Caminho: ${path.reversed().joinToString(" -> ")}")

        if (verbose) {
            println()
            println("✅ Algorithm completed successfully!")
            println()
        }
    }
}
