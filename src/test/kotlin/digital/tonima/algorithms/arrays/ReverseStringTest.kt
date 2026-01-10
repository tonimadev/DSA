package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

/**
 * Test cases for LeetCode #344 - Reverse String
 * https://leetcode.com/problems/reverse-string/
 *
 * Write a function that reverses a string. The input string is given as an array of characters s.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 */
class ReverseStringTest {

    private val solution = ReverseStringSolution()

    @Test
    fun `test reverseString - LeetCode Example 1`() {
        // Input: s = ["h","e","l","l","o"]
        // Output: ["o","l","l","e","h"]
        val s = charArrayOf('h', 'e', 'l', 'l', 'o')
        solution.reverseString(s)
        assertContentEquals(charArrayOf('o', 'l', 'l', 'e', 'h'), s)
    }

    @Test
    fun `test reverseString - LeetCode Example 2`() {
        // Input: s = ["H","a","n","n","a","h"]
        // Output: ["h","a","n","n","a","H"]
        val s = charArrayOf('H', 'a', 'n', 'n', 'a', 'h')
        solution.reverseString(s)
        assertContentEquals(charArrayOf('h', 'a', 'n', 'n', 'a', 'H'), s)
    }

    @Test
    fun `test reverseString - single character`() {
        // Input: s = ["a"]
        // Output: ["a"]
        val s = charArrayOf('a')
        solution.reverseString(s)
        assertContentEquals(charArrayOf('a'), s)
    }

    @Test
    fun `test reverseString - two characters`() {
        // Input: s = ["a","b"]
        // Output: ["b","a"]
        val s = charArrayOf('a', 'b')
        solution.reverseString(s)
        assertContentEquals(charArrayOf('b', 'a'), s)
    }

    @Test
    fun `test reverseString - empty array`() {
        // Input: s = []
        // Output: []
        val s = charArrayOf()
        solution.reverseString(s)
        assertContentEquals(charArrayOf(), s)
    }

    @Test
    fun `test reverseString - palindrome`() {
        // Input: s = ["a","b","a"]
        // Output: ["a","b","a"]
        val s = charArrayOf('a', 'b', 'a')
        solution.reverseString(s)
        assertContentEquals(charArrayOf('a', 'b', 'a'), s)
    }

    @Test
    fun `test reverseString - all same characters`() {
        // Input: s = ["a","a","a","a"]
        // Output: ["a","a","a","a"]
        val s = charArrayOf('a', 'a', 'a', 'a')
        solution.reverseString(s)
        assertContentEquals(charArrayOf('a', 'a', 'a', 'a'), s)
    }

    @Test
    fun `test reverseString - numbers as characters`() {
        // Input: s = ["1","2","3","4","5"]
        // Output: ["5","4","3","2","1"]
        val s = charArrayOf('1', '2', '3', '4', '5')
        solution.reverseString(s)
        assertContentEquals(charArrayOf('5', '4', '3', '2', '1'), s)
    }

    @Test
    fun `test reverseString - special characters`() {
        // Input: s = ["!","@","#","$"]
        // Output: ["$","#","@","!"]
        val s = charArrayOf('!', '@', '#', '$')
        solution.reverseString(s)
        assertContentEquals(charArrayOf('$', '#', '@', '!'), s)
    }

    @Test
    fun `test reverseString - mixed case letters`() {
        // Input: s = ["A","b","C","d"]
        // Output: ["d","C","b","A"]
        val s = charArrayOf('A', 'b', 'C', 'd')
        solution.reverseString(s)
        assertContentEquals(charArrayOf('d', 'C', 'b', 'A'), s)
    }
}

