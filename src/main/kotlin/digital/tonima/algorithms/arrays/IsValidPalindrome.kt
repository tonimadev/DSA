package digital.tonima.algorithms.arrays

/**
 * Valid Palindrome
 *
 * LeetCode: https://leetcode.com/problems/valid-palindrome/
 *
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 */
class StringIsValidPalindromeSolution {
    /**
     * Checks if a string is a valid palindrome using two-pointer approach.
     *
     * Time Complexity: O(n) - where n is the length of the string.
     *   - Single pass through the string with two pointers moving towards each other
     *   - Each character is examined at most once
     *
     * Space Complexity: O(1) - only uses two pointer variables, no additional data structures.
     *
     * @param s The input string to validate
     * @return true if the string is a palindrome, false otherwise
     */
    fun isPalindrome(s: String): Boolean {
        if (s.isEmpty()) {
            return true
        }

        var start = 0
        var last = s.length - 1

        while (start <= last) {
            val currFirst = s[start]
            val currLast = s[last]

            // Skip non-alphanumeric characters from the start
            if (!currFirst.isLetterOrDigit()) {
                start++
            }
            // Skip non-alphanumeric characters from the end
            else if (!currLast.isLetterOrDigit()) {
                last--
            }
            // Compare characters (case-insensitive)
            else {
                if (currFirst.lowercaseChar() != currLast.lowercaseChar()) {
                    return false
                }
                start++
                last--
            }
        }

        return true
    }
}
