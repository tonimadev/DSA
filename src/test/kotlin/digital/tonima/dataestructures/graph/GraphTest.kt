package digital.tonima.dataestructures.graph

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GraphTest {

    private fun captureStdOut(block: () -> Unit): String {
        val originalOut = System.out
        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        System.setOut(ps)
        try {
            block()
        } finally {
            System.out.flush()
            System.setOut(originalOut)
        }
        return baos.toString().replace("\r\n", "\n").trimEnd()
    }

    @Test
    fun `breadthFirstSearch should traverse simple linear graph`() {
        val graph = Graph<String>()
        graph.addEdge("A", "B")
        graph.addEdge("B", "C")
        graph.addEdge("C", "D")

        val output = captureStdOut {
            graph.breadthFirstSearch("A")
        }

        assertTrue(output.contains("Starting BFS from 'A':"))
        assertTrue(output.contains("A ->"))
        assertTrue(output.contains("B ->"))
        assertTrue(output.contains("C ->"))
        assertTrue(output.contains("D ->"))
    }

    @Test
    fun `breadthFirstSearch should traverse graph with multiple branches`() {
        val graph = Graph<Int>()
        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 4)
        graph.addEdge(2, 5)
        graph.addEdge(3, 6)

        val output = captureStdOut {
            graph.breadthFirstSearch(1)
        }

        assertTrue(output.contains("Starting BFS from '1':"))
        assertTrue(output.contains("1 ->"))
        assertTrue(output.contains("2 ->"))
        assertTrue(output.contains("3 ->"))
        assertTrue(output.contains("4 ->"))
        assertTrue(output.contains("5 ->"))
        assertTrue(output.contains("6 ->"))
    }

    @Test
    fun `breadthFirstSearch should handle undirected graph`() {
        val graph = Graph<String>()
        graph.addEdge("A", "B", bidirectional = true)
        graph.addEdge("B", "C", bidirectional = true)
        graph.addEdge("C", "A", bidirectional = true)

        val output = captureStdOut {
            graph.breadthFirstSearch("A")
        }

        assertTrue(output.contains("Starting BFS from 'A':"))
        assertTrue(output.contains("A ->"))
        assertTrue(output.contains("B ->"))
        assertTrue(output.contains("C ->"))

        // Each node should appear only once despite cycles
        val nodeACount = output.split("A ->").size - 1
        val nodeBCount = output.split("B ->").size - 1
        val nodeCCount = output.split("C ->").size - 1

        assertEquals(1, nodeACount, "Node A should be visited exactly once")
        assertEquals(1, nodeBCount, "Node B should be visited exactly once")
        assertEquals(1, nodeCCount, "Node C should be visited exactly once")
    }

    @Test
    fun `breadthFirstSearch should handle graph with cycles`() {
        val graph = Graph<Int>()
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        graph.addEdge(3, 4)
        graph.addEdge(4, 2) // Creates a cycle back to 2

        val output = captureStdOut {
            graph.breadthFirstSearch(1)
        }

        assertTrue(output.contains("Starting BFS from '1':"))

        // Verify each node appears exactly once
        val node1Count = output.split("1 ->").size - 1
        val node2Count = output.split("2 ->").size - 1
        val node3Count = output.split("3 ->").size - 1
        val node4Count = output.split("4 ->").size - 1

        assertEquals(1, node1Count, "Node 1 should be visited exactly once")
        assertEquals(1, node2Count, "Node 2 should be visited exactly once despite cycle")
        assertEquals(1, node3Count, "Node 3 should be visited exactly once")
        assertEquals(1, node4Count, "Node 4 should be visited exactly once")
    }

    @Test
    fun `breadthFirstSearch should handle single node graph`() {
        val graph = Graph<String>()
        graph.addEdge("A", "A") // Self-loop

        val output = captureStdOut {
            graph.breadthFirstSearch("A")
        }

        assertTrue(output.contains("Starting BFS from 'A':"))
        assertTrue(output.contains("A ->"))

        // Should visit A only once
        val nodeACount = output.split("A ->").size - 1
        assertEquals(1, nodeACount, "Node A should be visited exactly once")
    }

    @Test
    fun `breadthFirstSearch should handle disconnected components`() {
        val graph = Graph<String>()
        // Component 1
        graph.addEdge("A", "B")
        graph.addEdge("B", "C")

        // Component 2 (disconnected)
        graph.addEdge("X", "Y")
        graph.addEdge("Y", "Z")

        val output = captureStdOut {
            graph.breadthFirstSearch("A")
        }

        // Should only visit nodes in the connected component
        assertTrue(output.contains("A ->"))
        assertTrue(output.contains("B ->"))
        assertTrue(output.contains("C ->"))
        assertFalse(output.contains("X ->"))
        assertFalse(output.contains("Y ->"))
        assertFalse(output.contains("Z ->"))
    }

    @Test
    fun `breadthFirstSearch should handle non-existent start node`() {
        val graph = Graph<String>()
        graph.addEdge("A", "B")
        graph.addEdge("B", "C")

        val output = captureStdOut {
            graph.breadthFirstSearch("Z")
        }

        assertTrue(output.contains("The start node does not exist in the graph."))
        assertFalse(output.contains("Starting BFS"))
    }

    @Test
    fun `breadthFirstSearch should handle empty graph`() {
        val graph = Graph<Int>()

        val output = captureStdOut {
            graph.breadthFirstSearch(1)
        }

        assertTrue(output.contains("The start node does not exist in the graph."))
    }

    @Test
    fun `breadthFirstSearch should traverse complex graph correctly`() {
        val graph = Graph<Char>()
        // Creating a more complex graph structure
        graph.addEdge('A', 'B')
        graph.addEdge('A', 'C')
        graph.addEdge('A', 'D')
        graph.addEdge('B', 'E')
        graph.addEdge('C', 'F')
        graph.addEdge('C', 'G')
        graph.addEdge('D', 'H')
        graph.addEdge('E', 'I')

        val output = captureStdOut {
            graph.breadthFirstSearch('A')
        }

        assertTrue(output.contains("Starting BFS from 'A':"))

        // All nodes should be visited
        val nodes = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I')
        nodes.forEach { node ->
            assertTrue(output.contains("$node ->"), "Node $node should be visited")
        }

        // Each node should be visited exactly once
        nodes.forEach { node ->
            val count = output.split("$node ->").size - 1
            assertEquals(1, count, "Node $node should be visited exactly once")
        }
    }

    @Test
    fun `breadthFirstSearch should visit nodes in breadth-first order`() {
        val graph = Graph<Int>()
        // Tree structure: level 0 -> 1, level 1 -> 2,3, level 2 -> 4,5,6,7
        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 4)
        graph.addEdge(2, 5)
        graph.addEdge(3, 6)
        graph.addEdge(3, 7)

        val output = captureStdOut {
            graph.breadthFirstSearch(1)
        }

        // Extract the order of nodes visited
        val lines = output.split("\n").filter { it.contains(" ->") && !it.contains("Starting BFS") }

        assertTrue(lines.isNotEmpty())

        // Level 0 (root)
        assertTrue(lines[0].contains("1 ->"))

        // Level 1 should come before Level 2
        val node2Index = output.indexOf("2 ->")
        val node3Index = output.indexOf("3 ->")
        val node4Index = output.indexOf("4 ->")
        val node5Index = output.indexOf("5 ->")
        val node6Index = output.indexOf("6 ->")
        val node7Index = output.indexOf("7 ->")

        // Nodes at level 1 (2, 3) should appear before nodes at level 2 (4, 5, 6, 7)
        assertTrue(node2Index < node4Index)
        assertTrue(node2Index < node5Index)
        assertTrue(node3Index < node6Index)
        assertTrue(node3Index < node7Index)
    }

    @Test
    fun `addEdge should create unidirectional edge by default`() {
        val graph = Graph<String>()
        graph.addEdge("A", "B")

        val output = captureStdOut {
            graph.printGraph()
        }

        assertTrue(output.contains("A: [Edge(node=B, weight=1)]"))
        assertFalse(output.contains("B: [Edge(node=A, weight=1)]"))
    }

    @Test
    fun `addEdge should create bidirectional edge when specified`() {
        val graph = Graph<String>()
        graph.addEdge("A", "B", bidirectional = true)

        val output = captureStdOut {
            graph.printGraph()
        }

        assertTrue(output.contains("A: [Edge(node=B, weight=1)]"))
        assertTrue(output.contains("B: [Edge(node=A, weight=1)]"))
    }

    @Test
    fun `printGraph should display graph structure correctly`() {
        val graph = Graph<Int>()
        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 4)

        val output = captureStdOut {
            graph.printGraph()
        }

        println(output)

        assertTrue(output.contains("1: [Edge(node=2, weight=1), Edge(node=3, weight=1)]"))
        assertTrue(output.contains("2: [Edge(node=4, weight=1)]"))
    }

    // Dijkstra's Algorithm Tests

    @Test
    fun `dijkstra should find shortest path in simple linear graph`() {
        val graph = Graph<String>()
        graph.addEdge("A", "B", weight = 5)
        graph.addEdge("B", "C", weight = 3)
        graph.addEdge("C", "D", weight = 2)

        val output = captureStdOut {
            graph.dijkstra("A", "D")
        }

        assertTrue(output.contains("Custo Mínimo: 10"))
        assertTrue(output.contains("Caminho: A -> B -> C -> D"))
    }

    @Test
    fun `dijkstra should find shortest path when multiple routes exist`() {
        val graph = Graph<String>()
        // Route 1: A -> B -> D (cost: 5 + 10 = 15)
        graph.addEdge("A", "B", weight = 5)
        graph.addEdge("B", "D", weight = 10)

        // Route 2: A -> C -> D (cost: 2 + 3 = 5) - SHORTER
        graph.addEdge("A", "C", weight = 2)
        graph.addEdge("C", "D", weight = 3)

        val output = captureStdOut {
            graph.dijkstra("A", "D")
        }

        assertTrue(output.contains("Custo Mínimo: 5"))
        assertTrue(output.contains("Caminho: A -> C -> D"))
    }

    @Test
    fun `dijkstra should handle graph with single node path`() {
        val graph = Graph<Int>()
        graph.addEdge(1, 2, weight = 10)

        val output = captureStdOut {
            graph.dijkstra(1, 2)
        }

        assertTrue(output.contains("Custo Mínimo: 10"))
        assertTrue(output.contains("Caminho: 1 -> 2"))
    }

    @Test
    fun `dijkstra should find optimal path in complex graph`() {
        val graph = Graph<String>()
        // Complex graph with multiple paths
        graph.addEdge("A", "B", weight = 4)
        graph.addEdge("A", "C", weight = 2)
        graph.addEdge("B", "C", weight = 1)
        graph.addEdge("B", "D", weight = 5)
        graph.addEdge("C", "D", weight = 8)
        graph.addEdge("C", "E", weight = 10)
        graph.addEdge("D", "E", weight = 2)

        // Shortest path: A -> B -> D -> E (cost: 4 + 5 + 2 = 11)
        // Alternative: A -> C -> D -> E (cost: 2 + 8 + 2 = 12)
        // Alternative: A -> C -> E (cost: 2 + 10 = 12)

        val output = captureStdOut {
            graph.dijkstra("A", "E")
        }

        assertTrue(output.contains("Custo Mínimo: 11"))
        assertTrue(output.contains("Caminho: A -> B -> D -> E"))
    }

    @Test
    fun `dijkstra should handle start and end being the same node`() {
        val graph = Graph<String>()
        graph.addEdge("A", "B", weight = 5)
        graph.addEdge("B", "C", weight = 3)

        val output = captureStdOut {
            graph.dijkstra("A", "A")
        }

        assertTrue(output.contains("Custo Mínimo: 0"))
        assertTrue(output.contains("Caminho: A"))
    }

    @Test
    fun `dijkstra should handle no path between nodes`() {
        val graph = Graph<String>()
        // Component 1
        graph.addEdge("A", "B", weight = 5)
        graph.addEdge("B", "C", weight = 3)

        // Component 2 (disconnected)
        graph.addEdge("X", "Y", weight = 2)

        val output = captureStdOut {
            graph.dijkstra("A", "X")
        }

        assertTrue(output.contains("Não há caminho entre A e X"))
    }

    @Test
    fun `dijkstra should work with integer node types`() {
        val graph = Graph<Int>()
        graph.addEdge(1, 2, weight = 7)
        graph.addEdge(1, 3, weight = 9)
        graph.addEdge(1, 6, weight = 14)
        graph.addEdge(2, 3, weight = 10)
        graph.addEdge(2, 4, weight = 15)
        graph.addEdge(3, 4, weight = 11)
        graph.addEdge(3, 6, weight = 2)
        graph.addEdge(4, 5, weight = 6)
        graph.addEdge(5, 6, weight = 9)

        val output = captureStdOut {
            graph.dijkstra(1, 5)
        }


        assertTrue(output.contains("Custo Mínimo: 26"))
        // Shortest path: 1 -> 3 -> 4 -> 5 (cost: 9 + 11 + 6 = 26)
    }

    @Test
    fun `dijkstra should handle weighted undirected graph`() {
        val graph = Graph<String>()
        graph.addEdge("A", "B", weight = 4, bidirectional = true)
        graph.addEdge("A", "C", weight = 2, bidirectional = true)
        graph.addEdge("B", "D", weight = 5, bidirectional = true)
        graph.addEdge("C", "D", weight = 1, bidirectional = true)

        val output = captureStdOut {
            graph.dijkstra("A", "D")
        }

        assertTrue(output.contains("Custo Mínimo: 3"))
        assertTrue(output.contains("Caminho: A -> C -> D"))
    }

    @Test
    fun `dijkstra should find path with varying edge weights`() {
        val graph = Graph<Char>()
        graph.addEdge('A', 'B', weight = 1)
        graph.addEdge('A', 'C', weight = 100)
        graph.addEdge('B', 'C', weight = 1)

        val output = captureStdOut {
            graph.dijkstra('A', 'C')
        }

        assertTrue(output.contains("Custo Mínimo: 2"))
        assertTrue(output.contains("Caminho: A -> B -> C"))
    }

    @Test
    fun `dijkstra should handle graph with cycles correctly`() {
        val graph = Graph<String>()
        // Create a cycle: A -> B -> C -> A
        graph.addEdge("A", "B", weight = 1)
        graph.addEdge("B", "C", weight = 2)
        graph.addEdge("C", "A", weight = 3)
        graph.addEdge("B", "D", weight = 5)

        val output = captureStdOut {
            graph.dijkstra("A", "D")
        }

        assertTrue(output.contains("Custo Mínimo: 6"))
        assertTrue(output.contains("Caminho: A -> B -> D"))
    }

    @Test
    fun `dijkstra should prefer shorter path over more hops`() {
        val graph = Graph<Int>()
        // Direct path with high cost
        graph.addEdge(1, 5, weight = 100)

        // Longer path with lower total cost
        graph.addEdge(1, 2, weight = 1)
        graph.addEdge(2, 3, weight = 1)
        graph.addEdge(3, 4, weight = 1)
        graph.addEdge(4, 5, weight = 1)

        val output = captureStdOut {
            graph.dijkstra(1, 5)
        }

        assertTrue(output.contains("Custo Mínimo: 4"))
        assertTrue(output.contains("Caminho: 1 -> 2 -> 3 -> 4 -> 5"))
    }

    @Test
    fun `dijkstra should work with large weight values`() {
        val graph = Graph<String>()
        graph.addEdge("Start", "Mid", weight = 1000)
        graph.addEdge("Mid", "End", weight = 2000)

        val output = captureStdOut {
            graph.dijkstra("Start", "End")
        }

        assertTrue(output.contains("Custo Mínimo: 3000"))
        assertTrue(output.contains("Caminho: Start -> Mid -> End"))
    }
}
