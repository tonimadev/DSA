package digital.tonima.algorithms.dinamicprograming

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import kotlin.test.assertEquals

@DisplayName("Fibonacci Tests")
class FibonacciTest {

    private val fibonacci = FibonacciSolution()

    @Test
    @DisplayName("fib(0) should return 0")
    fun testFibonacciZero() {
        assertEquals(0, fibonacci.fib(0))
    }

    @Test
    @DisplayName("fib(1) should return 1")
    fun testFibonacciOne() {
        assertEquals(1, fibonacci.fib(1))
    }

    @Test
    @DisplayName("fib(2) should return 1")
    fun testFibonacciTwo() {
        assertEquals(1, fibonacci.fib(2))
    }

    @Test
    @DisplayName("fib(3) should return 2")
    fun testFibonacciThree() {
        assertEquals(2, fibonacci.fib(3))
    }

    @Test
    @DisplayName("fib(4) should return 3")
    fun testFibonacciFour() {
        assertEquals(3, fibonacci.fib(4))
    }

    @Test
    @DisplayName("fib(5) should return 5")
    fun testFibonacciFive() {
        assertEquals(5, fibonacci.fib(5))
    }

    @Test
    @DisplayName("fib(6) should return 8")
    fun testFibonacciSix() {
        assertEquals(8, fibonacci.fib(6))
    }

    @Test
    @DisplayName("fib(10) should return 55")
    fun testFibonacciTen() {
        assertEquals(55, fibonacci.fib(10))
    }

    @Test
    @DisplayName("fib(15) should return 610")
    fun testFibonacciFifteen() {
        assertEquals(610, fibonacci.fib(15))
    }

    @Test
    @DisplayName("fib(20) should return 6765")
    fun testFibonacciTwenty() {
        assertEquals(6765, fibonacci.fib(20))
    }

    @Test
    @DisplayName("fib(30) should return 832040")
    fun testFibonacciThirty() {
        assertEquals(832040, fibonacci.fib(30))
    }

    @Test
    @DisplayName("Test Fibonacci sequence correctness")
    fun testFibonacciSequence() {
        val expected = listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
        val actual = (0..9).map { fibonacci.fib(it) }
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test multiple calls produce consistent results")
    fun testConsistency() {
        val n = 15
        val result1 = fibonacci.fib(n)
        val result2 = fibonacci.fib(n)
        val result3 = fibonacci.fib(n)

        assertEquals(result1, result2)
        assertEquals(result2, result3)
    }

    @Test
    @DisplayName("Test larger Fibonacci numbers")
    fun testLargeFibonacci() {
        assertEquals(102334155, fibonacci.fib(40))
    }
}
