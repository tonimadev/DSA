package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class LongestSubstringWithoutRepeatingCharactersTest {
    private val solution = LongestSubstringWithoutRepeatingCharactersSolution()

    @Test
    fun testEmptyString() {
        val result = solution.lengthOfLongestSubstring("")
        assertEquals(0, result)
    }

    @Test
    fun testSingleCharacter() {
        val result = solution.lengthOfLongestSubstring("a")
        assertEquals(1, result)
    }

    @Test
    fun testAllUniqueCharacters() {
        val result = solution.lengthOfLongestSubstring("abcdefg")
        assertEquals(7, result)
    }

    @Test
    fun testAllSameCharacters() {
        val result = solution.lengthOfLongestSubstring("aaaa")
        assertEquals(1, result)
    }

    @Test
    fun testWithRepeatingCharactersInMiddle() {
        val result = solution.lengthOfLongestSubstring("abcabcbb")
        assertEquals(3, result)
    }

    @Test
    fun testWithRepeatingCharactersAtEnd() {
        val result = solution.lengthOfLongestSubstring("bbbbb")
        assertEquals(1, result)
    }

    @Test
    fun testLongestSubstringInMiddle() {
        val result = solution.lengthOfLongestSubstring("pwwkew")
        assertEquals(3, result)
    }

    @Test
    fun testLongestSubstringAtStart() {
        val result = solution.lengthOfLongestSubstring("abcdef")
        assertEquals(6, result)
    }

    @Test
    fun testWithSpaces() {
        val result = solution.lengthOfLongestSubstring("au")
        assertEquals(2, result)
    }

    @Test
    fun testComplexCase() {
        val result = solution.lengthOfLongestSubstring("dvdf")
        assertEquals(3, result)
    }
}

