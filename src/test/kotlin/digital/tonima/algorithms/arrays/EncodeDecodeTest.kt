package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Encode and Decode Strings Tests")
class EncodeDecodeTest {

    private val solution = EncodeDecodeSolution()

    @Test
    @DisplayName("Example 1: Basic list of strings")
    fun testExample1() {
        val strs = listOf("neet", "code", "love", "you")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Example 2: Single string")
    fun testExample2() {
        val strs = listOf("we", "say", ":", "yes")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Empty list")
    fun testEmptyList() {
        val strs = emptyList<String>()
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
        assertEquals("", encoded)
    }

    @Test
    @DisplayName("Single empty string")
    fun testSingleEmptyString() {
        val strs = listOf("")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Multiple empty strings")
    fun testMultipleEmptyStrings() {
        val strs = listOf("", "", "")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Strings with special characters")
    fun testSpecialCharacters() {
        val strs = listOf("hello#world", "test@123", "a#b#c")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Strings with numbers")
    fun testStringsWithNumbers() {
        val strs = listOf("123", "456", "789")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Single character strings")
    fun testSingleCharacterStrings() {
        val strs = listOf("a", "b", "c", "d")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Very long string")
    fun testVeryLongString() {
        val strs = listOf("a".repeat(1000), "b".repeat(500))
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Strings with delimiter character")
    fun testStringsWithDelimiter() {
        val strs = listOf("a#b", "c#d#e", "#")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Mixed length strings")
    fun testMixedLengthStrings() {
        val strs = listOf("short", "a", "this is a longer string", "mid")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Encode produces correct format")
    fun testEncodeFormat() {
        val strs = listOf("abc", "de")
        val encoded = solution.encode(strs)
        assertEquals("3#abc2#de", encoded)
    }

    @Test
    @DisplayName("Strings with spaces")
    fun testStringsWithSpaces() {
        val strs = listOf("hello world", "  ", "a b c")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Strings with newlines")
    fun testStringsWithNewlines() {
        val strs = listOf("line1\nline2", "single", "a\nb\nc")
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Large list of strings")
    fun testLargeList() {
        val strs = (1..100).map { "string$it" }
        val encoded = solution.encode(strs)
        val decoded = solution.decode(encoded)
        assertEquals(strs, decoded)
    }

    @Test
    @DisplayName("Idempotency: encode-decode-encode-decode")
    fun testIdempotency() {
        val strs = listOf("test", "data", "here")
        val encoded1 = solution.encode(strs)
        val decoded1 = solution.decode(encoded1)
        val encoded2 = solution.encode(decoded1)
        val decoded2 = solution.decode(encoded2)
        assertEquals(strs, decoded1)
        assertEquals(strs, decoded2)
        assertEquals(encoded1, encoded2)
    }
}
