package digital.tonima.algorithms.arrays

class LongestSubstringWithoutRepeatingCharactersSolution {
    // Time Complexity: O(n) where n is the length of the string
    // Space Complexity: O(min(m, n)) where m is the charset size (26 for lowercase letters)
    // LeetCode: https://leetcode.com/problems/longest-substring-without-repeating-characters/
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0

        val charMap = mutableMapOf<Char, Int>()
        var left = 0
        var maxLength = 0

        for (right in s.indices) {
            val char = s[right]

            // If character exists in current window and is after left pointer
            if (charMap.containsKey(char) && charMap[char]!! >= left) {
                left = charMap[char]!! + 1
            }

            // Update the character's last seen position
            charMap[char] = right

            // Update max length
            maxLength = maxOf(maxLength, right - left + 1)
        }

        return maxLength
    }
}
