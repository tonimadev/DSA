package digital.tonima.algorithms.stack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

@DisplayName("Reverse Polish Notation Tests")
class ReversePolishNotationTest {

    private lateinit var solution: ReversePolishNotationSolution

    @BeforeEach
    fun setup() {
        solution = ReversePolishNotationSolution()
    }

    @Test
    @DisplayName("should evaluate simple addition")
    fun testSimpleAddition() {
        val tokens = arrayOf("2", "1", "+")
        val result = solution.evalRPN(tokens)
        assertEquals(3, result)
    }

    @Test
    @DisplayName("should evaluate simple subtraction")
    fun testSimpleSubtraction() {
        val tokens = arrayOf("5", "3", "-")
        val result = solution.evalRPN(tokens)
        assertEquals(2, result)
    }

    @Test
    @DisplayName("should evaluate simple multiplication")
    fun testSimpleMultiplication() {
        val tokens = arrayOf("4", "5", "*")
        val result = solution.evalRPN(tokens)
        assertEquals(20, result)
    }

    @Test
    @DisplayName("should evaluate simple division")
    fun testSimpleDivision() {
        val tokens = arrayOf("10", "2", "/")
        val result = solution.evalRPN(tokens)
        assertEquals(5, result)
    }

    @Test
    @DisplayName("should evaluate complex expression with multiple operations")
    fun testComplexExpression() {
        val tokens = arrayOf("2", "1", "+", "3", "*")
        val result = solution.evalRPN(tokens)
        assertEquals(9, result)
    }

    @Test
    @DisplayName("should evaluate expression with division and multiplication")
    fun testDivisionAndMultiplication() {
        val tokens = arrayOf("4", "13", "5", "/", "+")
        val result = solution.evalRPN(tokens)
        assertEquals(6, result)
    }

    @Test
    @DisplayName("should evaluate expression with all operators")
    fun testAllOperators() {
        val tokens = arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+")
        val result = solution.evalRPN(tokens)
        assertEquals(22, result)
    }

    @Test
    @DisplayName("should handle single number")
    fun testSingleNumber() {
        val tokens = arrayOf("42")
        val result = solution.evalRPN(tokens)
        assertEquals(42, result)
    }

    @Test
    @DisplayName("should handle negative numbers")
    fun testNegativeNumbers() {
        val tokens = arrayOf("-3", "5", "+")
        val result = solution.evalRPN(tokens)
        assertEquals(2, result)
    }

    @Test
    @DisplayName("should handle expression with consecutive operations")
    fun testConsecutiveOperations() {
        val tokens = arrayOf("3", "4", "+", "2", "*", "7", "/")
        val result = solution.evalRPN(tokens)
        assertEquals(2, result)
    }

    @Test
    @DisplayName("should evaluate subtraction with larger second operand")
    fun testSubtractionNegativeResult() {
        val tokens = arrayOf("3", "5", "-")
        val result = solution.evalRPN(tokens)
        assertEquals(-2, result)
    }

    @Test
    @DisplayName("should handle division resulting in truncation")
    fun testDivisionTruncation() {
        val tokens = arrayOf("7", "3", "/")
        val result = solution.evalRPN(tokens)
        assertEquals(2, result)
    }

    @Test
    @DisplayName("should evaluate expression with nested operations")
    fun testNestedOperations() {
        val tokens = arrayOf("15", "7", "1", "1", "+", "-", "/", "3", "*", "2", "1", "1", "+", "+", "-")
        val result = solution.evalRPN(tokens)
        assertEquals(5, result)
    }

    @Test
    @DisplayName("should handle zero result")
    fun testZeroResult() {
        val tokens = arrayOf("5", "5", "-")
        val result = solution.evalRPN(tokens)
        assertEquals(0, result)
    }

    @Test
    @DisplayName("should handle multiplication by zero")
    fun testMultiplicationByZero() {
        val tokens = arrayOf("5", "0", "*")
        val result = solution.evalRPN(tokens)
        assertEquals(0, result)
    }

    @Test
    @DisplayName("should handle two numbers and operator")
    fun testBasicOperation() {
        val tokens = arrayOf("3", "11", "+")
        val result = solution.evalRPN(tokens)
        assertEquals(14, result)
    }
}

