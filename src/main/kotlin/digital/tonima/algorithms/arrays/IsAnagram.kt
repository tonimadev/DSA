package digital.tonima.algorithms.arrays

/**
 * LeetCode #242: Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 *
 * Problem: Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An anagram is a word or phrase formed by rearranging the letters of another.
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Explanation: Both have same character frequencies: a=3, n=1, g=1, r=1, m=1
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 * Explanation: Different character frequencies
 *
 * Example 3:
 * Input: s = "listen", t = "silent"
 * Output: true
 *
 * Approach: HashMap (Character Frequency Count)
 * - Time Complexity: O(n) - Two passes through strings
 * - Space Complexity: O(1) - At most 26 lowercase English letters in HashMap
 *
 * Algorithm:
 * 1. Check if strings have different lengths (can't be anagrams)
 * 2. Count character frequencies in first string
 * 3. Decrement frequencies while iterating second string
 * 4. Verify all frequencies are zero
 */
class IsAnagramSolution {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val charCount = mutableMapOf<Char, Int>()

        s.forEach { char ->
            charCount[char] = charCount.getOrDefault(char, 0) + 1
        }

        t.forEach { char ->
            charCount[char] = charCount.getOrDefault(char, 0) - 1
        }

        return charCount.values.all { it == 0 }
    }
}
