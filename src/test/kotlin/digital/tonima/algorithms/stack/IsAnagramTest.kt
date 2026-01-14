package digital.tonima.algorithms.stack

import digital.tonima.algorithms.arrays.IsAnagramSolution
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IsAnagramTest {
    private val solution = IsAnagramSolution()

    @Test
    fun testAnagramWithSimpleStrings() {
        assertTrue(solution.isAnagram("anagram", "nagaram"))
    }

    @Test
    fun testNotAnagramWithDifferentCharacters() {
        assertFalse(solution.isAnagram("rat", "car"))
    }

    @Test
    fun testEmptyStrings() {
        assertTrue(solution.isAnagram("", ""))
    }

    @Test
    fun testSingleCharacterAnagrams() {
        assertTrue(solution.isAnagram("a", "a"))
    }

    @Test
    fun testSingleCharacterNotAnagram() {
        assertFalse(solution.isAnagram("a", "b"))
    }

    @Test
    fun testDifferentLengths() {
        assertFalse(solution.isAnagram("ab", "abc"))
    }

    @Test
    fun testIdenticalStrings() {
        assertTrue(solution.isAnagram("hello", "hello"))
    }

    @Test
    fun testAnagramWithDuplicateCharacters() {
        assertTrue(solution.isAnagram("aab", "aba"))
    }

    @Test
    fun testAnagramWithSpecialCharacters() {
        assertTrue(solution.isAnagram("a@b", "@ab"))
    }

    @Test
    fun testCaseSensitive() {
        assertFalse(solution.isAnagram("Abc", "abc"))
    }

    @Test
    fun testLongerAnagrams() {
        assertTrue(solution.isAnagram("listen", "silent"))
    }

    @Test
    fun testAnagramWithRepeatedLetters() {
        assertTrue(solution.isAnagram("aabbcc", "abcabc"))
    }

    @Test
    fun testNotAnagramWithRepeatedLetters() {
        assertFalse(solution.isAnagram("aab", "abb"))
    }

    @Test
    fun testWhitespaceAnagrams() {
        assertTrue(solution.isAnagram("a b", "b a"))
    }

    @Test
    fun testNumbersAsStrings() {
        assertTrue(solution.isAnagram("123", "321"))
    }
}

