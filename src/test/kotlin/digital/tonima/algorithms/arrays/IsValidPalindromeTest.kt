package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import kotlin.test.assertEquals

class IsValidPalindromeTest {

    private val solution = StringIsValidPalindromeSolution()

    @Test
    @DisplayName("isPalindrome - Valid palindrome with mixed case and punctuation")
    fun testValidPalindromeWithMixedCaseAndPunctuation() {
        val result = solution.isPalindrome("A man, a plan, a canal: Panama")
        assertEquals(true, result)
    }

    @Test
    @DisplayName("isPalindrome - Not a palindrome")
    fun testNotAPalindrome() {
        val result = solution.isPalindrome("race a car")
        assertEquals(false, result)
    }

    @Test
    @DisplayName("isPalindrome - Empty string is a palindrome")
    fun testEmptyString() {
        val result = solution.isPalindrome("")
        assertEquals(true, result)
    }

    @Test
    @DisplayName("isPalindrome - Single character is a palindrome")
    fun testSingleCharacter() {
        val result = solution.isPalindrome("a")
        assertEquals(true, result)
    }

    @Test
    @DisplayName("isPalindrome - Only non-alphanumeric characters")
    fun testOnlyNonAlphanumeric() {
        val result = solution.isPalindrome(" ")
        assertEquals(true, result)
    }

    @Test
    @DisplayName("isPalindrome - Palindrome with numbers")
    fun testPalindromeWithNumbers() {
        val result = solution.isPalindrome("A1B2B1A")
        assertEquals(true, result)
    }

    @Test
    @DisplayName("isPalindrome - Not a palindrome with numbers")
    fun testNotPalindromeWithNumbers() {
        val result = solution.isPalindrome("A1B2C1A")
        assertEquals(false, result)
    }

    @Test
    @DisplayName("isPalindrome - Multiple spaces and special characters")
    fun testMultipleSpacesAndSpecialChars() {
        val result = solution.isPalindrome(".,")
        assertEquals(true, result)
    }

    @Test
    @DisplayName("isPalindrome - Case insensitive palindrome")
    fun testCaseInsensitivePalindrome() {
        val result = solution.isPalindrome("RaceCar")
        assertEquals(true, result)
    }

    @Test
    @DisplayName("isPalindrome - Long palindrome with punctuation")
    fun testLongPalindromeWithPunctuation() {
        val result = solution.isPalindrome("Was it a car or a cat I saw?")
        assertEquals(true, result)
    }

    @Test
    @DisplayName("isPalindrome - Not a palindrome with uppercase")
    fun testNotPalindromeWithUppercase() {
        val result = solution.isPalindrome("Hello World")
        assertEquals(false, result)
    }

    @Test
    @DisplayName("isPalindrome - Simple palindrome")
    fun testSimplePalindrome() {
        val result = solution.isPalindrome("aba")
        assertEquals(true, result)
    }

    @Test
    @DisplayName("isPalindrome - Even length palindrome")
    fun testEvenLengthPalindrome() {
        val result = solution.isPalindrome("abba")
        assertEquals(true, result)
    }

    @Test
    @DisplayName("isPalindrome - Alphanumeric only")
    fun testAlphanumericOnly() {
        val result = solution.isPalindrome("0P")
        assertEquals(false, result)
    }
}
