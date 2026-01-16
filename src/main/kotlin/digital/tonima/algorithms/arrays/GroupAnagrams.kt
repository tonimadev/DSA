package digital.tonima.algorithms.arrays

/**
 * LeetCode #49 - Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 *
 * Given an array of strings strs, group the anagrams together.
 *
 * Time Complexity: O(n * k) where n is the number of strings and k is the maximum length of a string
 * Space Complexity: O(n * k) for storing all strings in the result
 */
class GroupAnagramsSolution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        // Map to store anagram groups: key = character frequency array, value = list of words
        val resultMap = mutableMapOf<List<Int>, MutableList<String>>()

        // Process each word
        for (wrd in strs) {
            // Create frequency array for 26 letters (a-z)
            val charList = IntArray(26)

            // Count frequency of each character
            wrd.forEach { char ->
                // Convert to lowercase first to handle uppercase letters
                val lowerChar = char.lowercaseChar()
                // Get index: 'a' = 0, 'b' = 1, ..., 'z' = 25
                charList[lowerChar.code - 'a'.code] += 1
            }

            // Use frequency array as key (anagrams have same frequency)
            val key = charList.toList()
            resultMap.getOrPut(key) { mutableListOf() }.add(wrd)
        }

        return resultMap.values.toList()
    }
}
