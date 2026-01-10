package digital.tonima.algorithms.stack

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidParenthesesTest {
    private val solution = ValidParenthesesSolution()

    @Test
    fun testExample1_simpleParentheses() {
        val s = "()"
        assertTrue(solution.isValid(s))
    }

    @Test
    fun testExample2_multipleParentheses() {
        val s = "()[]{}"
        assertTrue(solution.isValid(s))
    }

    @Test
    fun testExample3_mismatchedBrackets() {
        val s = "(]"
        assertFalse(solution.isValid(s))
    }

    @Test
    fun testExample4_nestedBrackets() {
        val s = "([])"
        assertTrue(solution.isValid(s))
    }

    @Test
    fun testExample5_wrongOrder() {
        val s = "([)]"
        assertFalse(solution.isValid(s))
    }

    @Test
    fun testOnlyOpenBrackets() {
        val s = "((("
        assertFalse(solution.isValid(s))
    }

    @Test
    fun testOnlyCloseBrackets() {
        val s = ")))"
        assertFalse(solution.isValid(s))
    }

    @Test
    fun testEmptyString() {
        val s = ""
        assertTrue(solution.isValid(s))
    }

    @Test
    fun testSingleOpenBracket() {
        val s = "("
        assertFalse(solution.isValid(s))
    }

    @Test
    fun testSingleCloseBracket() {
        val s = ")"
        assertFalse(solution.isValid(s))
    }

    @Test
    fun testComplexValidSequence() {
        val s = "{[()]}"
        assertTrue(solution.isValid(s))
    }

    @Test
    fun testComplexInvalidSequence() {
        val s = "{[(])}"
        assertFalse(solution.isValid(s))
    }

    @Test
    fun testCurlyBraces() {
        val s = "{}"
        assertTrue(solution.isValid(s))
    }

    @Test
    fun testSquareBrackets() {
        val s = "[]"
        assertTrue(solution.isValid(s))
    }

    @Test
    fun testMismatchedCurly() {
        val s = "{]"
        assertFalse(solution.isValid(s))
    }

    @Test
    fun testMismatchedSquare() {
        val s = "[}"
        assertFalse(solution.isValid(s))
    }

    @Test
    fun testLongValidSequence() {
        val s = "()[]{}([])({})[]({})"
        assertTrue(solution.isValid(s))
    }

    @Test
    fun testLongInvalidSequence() {
        val s = "()[]{}([])({})[]({}]"
        assertFalse(solution.isValid(s))
    }
}

