package digital.tonima.dataestructures.stack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class StackTest {

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
    fun `push should set new value as top and increase height`() {
        val stack = Stack(1)
        stack.push(2)

        val output = captureStdOut {
            stack.getTop()
            stack.getHeight()
        }

        val lines = output.split("\n")
        assertEquals("Top: 2", lines.getOrNull(0))
        assertEquals("Height: 2", lines.getOrNull(1))

        // Also verify printStack shows top-to-bottom
        val printOutput = captureStdOut { stack.printStack() }
        val printLines = printOutput.split("\n")
        assertEquals(listOf("2", "1"), printLines)
    }

    @Test
    fun `pop should remove current top and decrease height, returning removed node`() {
        val stack = Stack(1)
        stack.push(2)
        val removed = stack.pop()
        // Validate returned node carries the popped value
        assertEquals(2, removed?.value)

        val output = captureStdOut {
            stack.getTop()
            stack.getHeight()
        }

        val lines = output.split("\n")
        assertEquals("Top: 1", lines.getOrNull(0))
        assertEquals("Height: 1", lines.getOrNull(1))
    }

    @Test
    fun `pop on single-element stack should return node and leave stack empty`() {
        val stack = Stack(10)
        val removed = stack.pop() // remove the only element
        // Returned node should have the removed value
        assertEquals(10, removed?.value)

        val output = captureStdOut {
            stack.getTop()
            stack.getHeight()
        }

        val lines = output.split("\n")
        // Top should be null when the stack becomes empty
        assertEquals("Top: null", lines.getOrNull(0))
        assertEquals("Height: 0", lines.getOrNull(1))

        // printStack should print nothing when empty
        val printOutput = captureStdOut { stack.printStack() }
        assertEquals("", printOutput)

        // Another pop on empty should return null
        val removedEmpty = stack.pop()
        assertNull(removedEmpty)
    }

    @Test
    fun `multiple pushes and pops maintain LIFO order`() {
        val stack = Stack("a")
        stack.push("b")
        stack.push("c")
        val removed = stack.pop() // removes c
        assertEquals("c", removed?.value)

        val output = captureStdOut { stack.printStack() }
        val lines = output.split("\n")
        // Top-to-bottom should be b then a
        assertEquals(listOf("b", "a"), lines)
    }
}
