package digital.tonima.dataestructures.queue

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class QueueTest {

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
    fun `enqueue should add element to queue and increase length`() {
        val queue = Queue(1)
        queue.enqueue(2)

        // Verify through reflection that length increased
        val lengthField = queue.javaClass.getDeclaredField("length")
        lengthField.isAccessible = true
        val length = lengthField.getInt(queue)

        assertEquals(2, length)
    }

    @Test
    fun `enqueue should add multiple elements maintaining order`() {
        val queue = Queue("first")
        queue.enqueue("second")
        queue.enqueue("third")
        queue.enqueue("fourth")

        // Verify length after multiple enqueues
        val lengthField = queue.javaClass.getDeclaredField("length")
        lengthField.isAccessible = true
        val length = lengthField.getInt(queue)

        assertEquals(4, length)
    }

    @Test
    fun `enqueue on initial queue should have correct structure`() {
        val queue = Queue(10)

        // Initial state: length should be 1
        val lengthField = queue.javaClass.getDeclaredField("length")
        lengthField.isAccessible = true
        val initialLength = lengthField.getInt(queue)

        assertEquals(1, initialLength)

        // After enqueue, length should be 2
        queue.enqueue(20)
        val newLength = lengthField.getInt(queue)

        assertEquals(2, newLength)
    }

    @Test
    fun `enqueue should handle generic types correctly`() {
        val queueInt = Queue(1)
        queueInt.enqueue(2)
        queueInt.enqueue(3)

        val lengthFieldInt = queueInt.javaClass.getDeclaredField("length")
        lengthFieldInt.isAccessible = true
        assertEquals(3, lengthFieldInt.getInt(queueInt))

        val queueString = Queue("a")
        queueString.enqueue("b")
        queueString.enqueue("c")

        val lengthFieldString = queueString.javaClass.getDeclaredField("length")
        lengthFieldString.isAccessible = true
        assertEquals(3, lengthFieldString.getInt(queueString))
    }

    @Test
    fun `enqueue should correctly update first and last pointers`() {
        val queue = Queue(1)

        // Get initial first and last
        val firstField = queue.javaClass.getDeclaredField("first")
        firstField.isAccessible = true
        val lastField = queue.javaClass.getDeclaredField("last")
        lastField.isAccessible = true

        val initialFirst = firstField.get(queue)
        val initialLast = lastField.get(queue)

        // Initially, first and last should point to the same node
        assertEquals(initialFirst, initialLast)

        // After enqueue
        queue.enqueue(2)

        val newFirst = firstField.get(queue)
        val newLast = lastField.get(queue)

        // After enqueue, first and last should be different
        assert(newFirst != newLast)
    }

    @Test
    fun `dequeue em fila vazia deve retornar null`() {
        val queue = Queue(1)
        // remove único elemento
        queue.dequeue()
        // próxima remoção deve ser nula
        val r = queue.dequeue()
        assertNull(r)
        // length deve permanecer 0
        val lengthField = queue.javaClass.getDeclaredField("length").apply { isAccessible = true }
        assertEquals(0, lengthField.getInt(queue))
    }

    @Test
    fun `dequeue em fila com um elemento deve retornar esse elemento e zerar a fila`() {
        val queue = Queue(42)
        val n1 = queue.dequeue()
        // valor retornado
        assertNotNull(n1)
        assertEquals(42, n1?.value)
        // próxima remoção nula
        val n2 = queue.dequeue()
        assertNull(n2)
        // verificar ponteiros e length via reflexão
        val firstField = queue.javaClass.getDeclaredField("first").apply { isAccessible = true }
        val lastField = queue.javaClass.getDeclaredField("last").apply { isAccessible = true }
        val lengthField = queue.javaClass.getDeclaredField("length").apply { isAccessible = true }
        assertNull(firstField.get(queue))
        assertNull(lastField.get(queue))
        assertEquals(0, lengthField.getInt(queue))
    }

    @Test
    fun `dequeue deve manter a ordem FIFO em múltiplos elementos`() {
        val queue = Queue("a")
        queue.enqueue("b")
        queue.enqueue("c")

        val n1 = queue.dequeue()
        val n2 = queue.dequeue()
        val n3 = queue.dequeue()
        val n4 = queue.dequeue()

        assertNotNull(n1)
        assertEquals("a", n1?.value)
        assertNotNull(n2)
        assertEquals("b", n2?.value)
        assertNotNull(n3)
        assertEquals("c", n3?.value)
        assertNull(n4)

        // length após todas remoções
        val lengthField = queue.javaClass.getDeclaredField("length").apply { isAccessible = true }
        assertEquals(0, lengthField.getInt(queue))
    }

    @Test
    fun `dequeue deve atualizar corretamente first e last ao remover elementos`() {
        val queue = Queue(10)
        queue.enqueue(20)
        queue.enqueue(30)

        val firstField = queue.javaClass.getDeclaredField("first").apply { isAccessible = true }
        val lastField = queue.javaClass.getDeclaredField("last").apply { isAccessible = true }
        val lengthField = queue.javaClass.getDeclaredField("length").apply { isAccessible = true }

        // após primeiro dequeue, first deve avançar, last permanece
        val removed1 = queue.dequeue()
        assertNotNull(removed1)
        assertEquals(10, removed1?.value)

        val firstAfter1 = firstField.get(queue)
        val lastAfter1 = lastField.get(queue)
        val lengthAfter1 = lengthField.getInt(queue)
        assertNotNull(firstAfter1)
        assertNotNull(lastAfter1)
        assertEquals(2, lengthAfter1)

        // após segundo dequeue, ainda há um elemento
        val removed2 = queue.dequeue()
        assertNotNull(removed2)
        assertEquals(20, removed2?.value)

        val firstAfter2 = firstField.get(queue)
        val lastAfter2 = lastField.get(queue)
        val lengthAfter2 = lengthField.getInt(queue)
        assertNotNull(firstAfter2)
        assertNotNull(lastAfter2)
        assertEquals(firstAfter2, lastAfter2) // quando há apenas um elemento, first == last
        assertEquals(1, lengthAfter2)

        // após terceiro dequeue, fila vazia: first/last nulos e length 0
        val removed3 = queue.dequeue()
        assertNotNull(removed3)
        assertEquals(30, removed3?.value)

        val firstAfter3 = firstField.get(queue)
        val lastAfter3 = lastField.get(queue)
        val lengthAfter3 = lengthField.getInt(queue)
        assertNull(firstAfter3)
        assertNull(lastAfter3)
        assertEquals(0, lengthAfter3)
    }

    @Test
    fun `dequeue deve desconectar nó removido da lista`() {
        val queue = Queue(1)
        queue.enqueue(2)
        queue.enqueue(3)

        val removed = queue.dequeue()
        assertNotNull(removed)
        assertEquals(1, removed?.value)
        // o nó removido não deve ter referência para o próximo
        assertNull(removed?.next)
    }

    @Test
    fun `dequeue intercalado com enqueue deve funcionar corretamente`() {
        val queue = Queue("a")
        queue.enqueue("b")

        val first = queue.dequeue()
        assertNotNull(first)
        assertEquals("a", first?.value)

        queue.enqueue("c")

        val second = queue.dequeue()
        val third = queue.dequeue()

        assertNotNull(second)
        assertEquals("b", second?.value)
        assertNotNull(third)
        assertEquals("c", third?.value)

        val lengthField = queue.javaClass.getDeclaredField("length").apply { isAccessible = true }
        assertEquals(0, lengthField.getInt(queue))
    }
}
