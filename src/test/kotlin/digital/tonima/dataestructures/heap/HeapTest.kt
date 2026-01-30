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
}
