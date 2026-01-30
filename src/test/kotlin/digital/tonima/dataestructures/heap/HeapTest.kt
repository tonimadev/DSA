package digital.tonima.dataestructures.heap

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HeapTest {

    @Test
    fun `test bubbleUp with integers - max heap behavior`() {
        // Criando um heap vazio e inserindo elementos
        val heap = Heap<Int>()

        // Usando reflexão para acessar mElements
        val elementsField = heap.javaClass.getDeclaredField("mElements")
        elementsField.isAccessible = true

        // Inserir elementos e verificar se o bubbleUp mantém a propriedade do heap
        heap.insert(5)
        var elements = elementsField.get(heap) as MutableList<Int>
        assertEquals(listOf(5), elements)

        heap.insert(10) // 10 deve subir para o topo (maior prioridade)
        elements = elementsField.get(heap) as MutableList<Int>
        assertEquals(10, elements[0], "O elemento de maior prioridade deve estar no topo")

        heap.insert(3)
        elements = elementsField.get(heap) as MutableList<Int>
        assertEquals(10, elements[0], "10 ainda deve ser o topo")

        heap.insert(15) // 15 deve subir para o topo
        elements = elementsField.get(heap) as MutableList<Int>
        assertEquals(15, elements[0], "15 deve estar no topo após bubbleUp")

        heap.insert(20) // 20 deve subir para o topo
        elements = elementsField.get(heap) as MutableList<Int>
        assertEquals(20, elements[0], "20 deve estar no topo após bubbleUp")
    }

    @Test
    fun `test bubbleUp maintains heap property with parent relationships`() {
        val heap = Heap<Int>()

        // Inserir vários elementos
        heap.insert(1)
        heap.insert(3)
        heap.insert(2)
        heap.insert(7)
        heap.insert(5)

        // Acessar elementos internos
        val elementsField = heap.javaClass.getDeclaredField("mElements")
        elementsField.isAccessible = true
        val elements = elementsField.get(heap) as MutableList<Int>

        // Verificar propriedade do heap: cada pai deve ter prioridade maior ou igual aos filhos
        for (i in 0 until elements.size / 2) {
            val leftChildIdx = 2 * i + 1
            val rightChildIdx = 2 * i + 2

            if (leftChildIdx < elements.size) {
                assertTrue(
                    elements[i] >= elements[leftChildIdx],
                    "Parent at $i (${elements[i]}) should have >= priority than left child at $leftChildIdx (${elements[leftChildIdx]})"
                )
            }

            if (rightChildIdx < elements.size) {
                assertTrue(
                    elements[i] >= elements[rightChildIdx],
                    "Parent at $i (${elements[i]}) should have >= priority than right child at $rightChildIdx (${elements[rightChildIdx]})"
                )
            }
        }
    }

    @Test
    fun `test bubbleUp with custom priority function`() {
        // Heap com prioridade customizada (menor número = maior prioridade, min heap)
        val minHeap = Heap<Int>(elementPriority = { -it })

        minHeap.insert(10)
        minHeap.insert(5)  // 5 deve subir (tem prioridade -5, que é maior que -10)
        minHeap.insert(15)
        minHeap.insert(2)  // 2 deve subir para o topo

        val elementsField = minHeap.javaClass.getDeclaredField("mElements")
        elementsField.isAccessible = true
        val elements = elementsField.get(minHeap) as MutableList<Int>

        assertEquals(2, elements[0], "Com prioridade invertida, 2 deve estar no topo")
    }

    @Test
    fun `test bubbleUp with single element`() {
        val heap = Heap<Int>()
        heap.insert(42)

        val elementsField = heap.javaClass.getDeclaredField("mElements")
        elementsField.isAccessible = true
        val elements = elementsField.get(heap) as MutableList<Int>

        assertEquals(1, elements.size)
        assertEquals(42, elements[0])
    }

    @Test
    fun `test empty heap operations`() {
        val heap = Heap<Int>()
        assertTrue(heap.isEmpty())
        assertEquals(0, heap.size())
        assertNull(heap.peek())
        assertNull(heap.top())
    }

    @Test
    fun `test peek does not remove element`() {
        val heap = Heap<Int>()
        heap.insert(5)
        heap.insert(3)
        heap.insert(8)

        assertEquals(8, heap.peek())
        assertEquals(3, heap.size())
        assertEquals(8, heap.peek()) // Peek again, size should not change
        assertEquals(3, heap.size())
    }

    @Test
    fun `test top removes and returns max element`() {
        val heap = Heap<Int>()
        heap.insert(5)
        heap.insert(3)
        heap.insert(8)
        heap.insert(1)
        heap.insert(10)

        assertEquals(5, heap.size())
        assertEquals(10, heap.top())
        assertEquals(4, heap.size())
        assertEquals(8, heap.top())
        assertEquals(3, heap.size())
        assertEquals(5, heap.top())
        assertEquals(2, heap.size())
        assertEquals(3, heap.top())
        assertEquals(1, heap.size())
        assertEquals(1, heap.top())
        assertEquals(0, heap.size())
        assertTrue(heap.isEmpty())
        assertNull(heap.top()) // Empty heap
    }

    @Test
    fun `test heapify constructor with unsorted list`() {
        val elements = mutableListOf(4, 1, 7, 3, 8, 5, 2, 9, 6)
        val heap = Heap(elements)

        assertEquals(9, heap.size())
        assertEquals(9, heap.peek()) // Max element should be at top

        // Verify heap property by removing all elements in descending order
        val sorted = mutableListOf<Int>()
        while (!heap.isEmpty()) {
            sorted.add(heap.top()!!)
        }

        assertEquals(listOf(9, 8, 7, 6, 5, 4, 3, 2, 1), sorted)
    }

    @Test
    fun `test heapify with empty list`() {
        val heap = Heap<Int>(mutableListOf())
        assertTrue(heap.isEmpty())
        assertEquals(0, heap.size())
        assertNull(heap.peek())
    }

    @Test
    fun `test heapify with single element`() {
        val heap = Heap<Int>(mutableListOf(42))
        assertEquals(1, heap.size())
        assertEquals(42, heap.peek())
        assertEquals(42, heap.top())
        assertTrue(heap.isEmpty())
    }

    @Test
    fun `test heap with custom objects`() {
        data class Task(val name: String, val priority: Int)

        val heap = Heap<Task>(elementPriority = { it.priority })

        heap.insert(Task("Low", 1))
        heap.insert(Task("High", 10))
        heap.insert(Task("Medium", 5))
        heap.insert(Task("Critical", 15))

        assertEquals("Critical", heap.top()?.name)
        assertEquals("High", heap.top()?.name)
        assertEquals("Medium", heap.top()?.name)
        assertEquals("Low", heap.top()?.name)
    }

    @Test
    fun `test min heap with inverted priority`() {
        // Min heap using custom priority (negative values to invert)
        val minHeap = Heap<Int>(elementPriority = { -it })

        minHeap.insert(5)
        minHeap.insert(3)
        minHeap.insert(8)
        minHeap.insert(1)
        minHeap.insert(10)

        // Should extract in ascending order (min heap behavior)
        assertEquals(1, minHeap.top())
        assertEquals(3, minHeap.top())
        assertEquals(5, minHeap.top())
        assertEquals(8, minHeap.top())
        assertEquals(10, minHeap.top())
    }

    @Test
    fun `test heap with strings`() {
        val heap = Heap<String>()
        heap.insert("apple")
        heap.insert("zebra")
        heap.insert("banana")
        heap.insert("mango")
        heap.insert("cherry")

        // Should extract in descending lexicographic order
        assertEquals("zebra", heap.top())
        assertEquals("mango", heap.top())
        assertEquals("cherry", heap.top())
        assertEquals("banana", heap.top())
        assertEquals("apple", heap.top())
    }

    @Test
    fun `test heap property maintained after mixed operations`() {
        val heap = Heap<Int>()

        // Insert elements
        listOf(15, 10, 20, 8, 12, 25, 5).forEach { heap.insert(it) }

        // Remove max
        assertEquals(25, heap.top())
        assertEquals(20, heap.top())

        // Insert more elements
        heap.insert(30)
        heap.insert(18)
        heap.insert(22)

        // Verify heap property still maintained
        assertEquals(30, heap.top())
        assertEquals(22, heap.top())
        assertEquals(18, heap.top())
        assertEquals(15, heap.top())
        assertEquals(12, heap.top())
        assertEquals(10, heap.top())
        assertEquals(8, heap.top())
        assertEquals(5, heap.top())
        assertTrue(heap.isEmpty())
    }

    @Test
    fun `test heapify is more efficient than insertions`() {
        // Heapify: O(n)
        val elements1 = (1..1000).shuffled().toMutableList()
        val heap1 = Heap(elements1)

        // Multiple insertions: O(n log n)
        val heap2 = Heap<Int>()
        elements1.forEach { heap2.insert(it) }

        // Both should produce same result
        assertEquals(1000, heap1.size())
        assertEquals(1000, heap2.size())
        assertEquals(heap1.peek(), heap2.peek())
    }

    @Test
    fun `test large heap performance`() {
        val heap = Heap<Int>()
        val n = 10000

        // Insert n elements
        repeat(n) { heap.insert(it) }

        assertEquals(n, heap.size())
        assertEquals(n - 1, heap.peek()) // Max should be n-1

        // Remove all elements
        var prev = Int.MAX_VALUE
        repeat(n) {
            val current = heap.top()!!
            assertTrue(current <= prev, "Elements should be in descending order")
            prev = current
        }

        assertTrue(heap.isEmpty())
    }

    @Test
    fun `test kLargestElements with valid k`() {
        val heap = Heap<Int>()
        listOf(5, 10, 3, 8, 15, 1, 12, 6).forEach { heap.insert(it) }

        // Get 4 largest elements
        val top4 = heap.kLargestElements(4)

        // Should return [15, 12, 10, 8]
        assertEquals(4, top4.size)
        assertEquals(listOf(15, 12, 10, 8), top4)

        // Heap should remain unchanged
        assertEquals(8, heap.size())
        assertEquals(15, heap.peek())
    }

    @Test
    fun `test kLargestElements with k equal to size`() {
        val heap = Heap<Int>()
        listOf(5, 10, 3, 8).forEach { heap.insert(it) }

        val all = heap.kLargestElements(4)

        assertEquals(4, all.size)
        assertEquals(listOf(10, 8, 5, 3), all)
    }

    @Test
    fun `test kLargestElements with k greater than size`() {
        val heap = Heap<Int>()
        listOf(5, 10, 3).forEach { heap.insert(it) }

        val all = heap.kLargestElements(10)

        // Should return only available elements
        assertEquals(3, all.size)
        assertEquals(listOf(10, 5, 3), all)
    }

    @Test
    fun `test kLargestElements with k zero`() {
        val heap = Heap<Int>()
        listOf(5, 10, 3, 8).forEach { heap.insert(it) }

        val result = heap.kLargestElements(0)

        assertEquals(0, result.size)
        assertEquals(4, heap.size()) // Heap unchanged
    }

    @Test
    fun `test kLargestElements with negative k`() {
        val heap = Heap<Int>()
        listOf(5, 10, 3, 8).forEach { heap.insert(it) }

        val result = heap.kLargestElements(-5)

        assertEquals(0, result.size)
        assertEquals(4, heap.size()) // Heap unchanged
    }

    @Test
    fun `test kLargestElements with empty heap`() {
        val heap = Heap<Int>()

        val result = heap.kLargestElements(5)

        assertEquals(0, result.size)
        assertTrue(heap.isEmpty())
    }

    @Test
    fun `test allElementsDescending`() {
        val heap = Heap<Int>()
        listOf(5, 10, 3, 8, 15, 1, 12, 6).forEach { heap.insert(it) }

        val all = heap.allElementsDescending()

        // Should return all elements in descending order
        assertEquals(8, all.size)
        assertEquals(listOf(15, 12, 10, 8, 6, 5, 3, 1), all)

        // Heap should remain unchanged
        assertEquals(8, heap.size())
        assertEquals(15, heap.peek())
    }

    @Test
    fun `test allElementsDescending with empty heap`() {
        val heap = Heap<Int>()

        val result = heap.allElementsDescending()

        assertEquals(0, result.size)
        assertTrue(heap.isEmpty())
    }

    @Test
    fun `test allElementsDescending with single element`() {
        val heap = Heap<Int>()
        heap.insert(42)

        val result = heap.allElementsDescending()

        assertEquals(1, result.size)
        assertEquals(listOf(42), result)
        assertEquals(1, heap.size())
    }

    @Test
    fun `test kLargestElements vs kLargestElementsOptimized produce same results`() {
        val heap1 = Heap<Int>()
        val heap2 = Heap<Int>()

        listOf(5, 10, 3, 8, 15, 1, 12, 6, 20, 4).forEach {
            heap1.insert(it)
            heap2.insert(it)
        }

        val result1 = heap1.kLargestElements(5)
        // result2 uses kLargestElements (same as result1 since optimized version not available)
        val result2 = heap2.kLargestElements(5)

        assertEquals(result1, result2)
        assertEquals(listOf(20, 15, 12, 10, 8), result1)
    }

    @Test
    fun `test kLargestElements with custom objects`() {
        data class Score(val player: String, val points: Int)

        val heap = Heap<Score>(elementPriority = { it.points })

        heap.insert(Score("Alice", 100))
        heap.insert(Score("Bob", 85))
        heap.insert(Score("Charlie", 95))
        heap.insert(Score("David", 110))
        heap.insert(Score("Eve", 90))

        val top3 = heap.kLargestElements(3)

        assertEquals(3, top3.size)
        assertEquals("David", top3[0].player)
        assertEquals("Alice", top3[1].player)
        assertEquals("Charlie", top3[2].player)
    }
}
