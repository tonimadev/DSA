package digital.tonima.algorithms.arrays

/**
 * LeetCode #344 - Reverse String
 * https://leetcode.com/problems/reverse-string/
 *
 * Write a function that reverses a string. The input string is given as an array of characters s.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 *
 * Example 2:
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 *
 * Constraints:
 * - 1 <= s.length <= 10^5
 * - s[i] is a printable ascii character.
 */
class ReverseStringSolution {
    fun reverseString(s: CharArray): Unit {
        if (s.isEmpty()) return
        var forwardIndex = 0
        var backwardIndex = s.size - 1

        while (forwardIndex < backwardIndex) {
            val temp = s[forwardIndex]
            s[forwardIndex++] = s[backwardIndex]
            s[backwardIndex--] = temp
        }

    }
}
