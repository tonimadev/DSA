package digital.tonima.algorithms.stack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("MinStack Tests")
class MinStackTest {

    @Test
    @DisplayName("push and top - should return the last pushed value")
    fun testPushAndTop() {
        val minStack = MinStack()
        minStack.push(5)

        assertEquals(5, minStack.top())
    }

    @Test
    @DisplayName("push multiple values - should return the most recent pushed value")
    fun testPushMultipleValuesAndTop() {
        val minStack = MinStack()
        minStack.push(5)
        minStack.push(10)
        minStack.push(3)

        assertEquals(3, minStack.top())
    }

    @Test
    @DisplayName("pop - should remove the top element")
    fun testPop() {
        val minStack = MinStack()
        minStack.push(5)
        minStack.push(10)
        minStack.pop()

        assertEquals(5, minStack.top())
    }

    @Test
    @DisplayName("pop until empty - should return null for top")
    fun testPopUntilEmpty() {
        val minStack = MinStack()
        minStack.push(5)
        minStack.pop()

        assertNull(minStack.top())
    }

    @Test
    @DisplayName("getMin - should return the minimum value in the stack")
    fun testGetMin() {
        val minStack = MinStack()
        minStack.push(5)
        minStack.push(3)
        minStack.push(10)

        assertEquals(3, minStack.getMin())
    }

    @Test
    @DisplayName("getMin with negative numbers - should return the smallest value")
    fun testGetMinWithNegativeNumbers() {
        val minStack = MinStack()
        minStack.push(5)
        minStack.push(-3)
        minStack.push(10)

        assertEquals(-3, minStack.getMin())
    }

    @Test
    @DisplayName("getMin with single element - should return that element")
    fun testGetMinWithSingleElement() {
        val minStack = MinStack()
        minStack.push(42)

        assertEquals(42, minStack.getMin())
    }

    @Test
    @DisplayName("getMin on empty stack - should return 0")
    fun testGetMinOnEmptyStack() {
        val minStack = MinStack()

        assertEquals(0, minStack.getMin())
    }

    @Test
    @DisplayName("push after pop - should work correctly")
    fun testPushAfterPop() {
        val minStack = MinStack()
        minStack.push(5)
        minStack.push(10)
        minStack.pop()
        minStack.push(3)

        assertEquals(3, minStack.top())
        assertEquals(3, minStack.getMin())
    }

    @Test
    @DisplayName("multiple operations - complex scenario")
    fun testComplexScenario() {
        val minStack = MinStack()
        minStack.push(-2)
        minStack.push(0)
        minStack.push(-3)

        assertEquals(-3, minStack.getMin())

        minStack.pop()
        assertEquals(0, minStack.top())
        assertEquals(-2, minStack.getMin())

        minStack.pop()
        assertEquals(-2, minStack.top())
        assertEquals(-2, minStack.getMin())
    }

    @Test
    @DisplayName("push same values - should handle duplicates")
    fun testDuplicateValues() {
        val minStack = MinStack()
        minStack.push(5)
        minStack.push(5)
        minStack.push(5)

        assertEquals(5, minStack.getMin())
    }
}

