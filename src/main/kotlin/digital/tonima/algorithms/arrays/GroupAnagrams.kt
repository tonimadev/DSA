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
    /**
     * Groups anagrams together based on character frequency
     * Time Complexity: O(n * k) where n = number of strings, k = max string length
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val anagramGroups = mutableMapOf<List<Int>, MutableList<String>>()

        for (word in strs) {
            val frequencyKey = createFrequencyKey(word)
            anagramGroups.getOrPut(frequencyKey) { mutableListOf() }.add(word)
        }

        return anagramGroups.values.toList()
    }

    /**
     * Creates a frequency key for a word where anagrams produce the same key
     *
     * Example: "eat" -> [1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]
     *          "tea" -> [1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]
     *
     * Time Complexity: O(k) where k is the length of the word
     * Space Complexity: O(1) - always 26 letters
     */
    private fun createFrequencyKey(word: String): List<Int> {
        val frequencies = IntArray(26)

        word.forEach { char ->
            frequencies[charToIndex(char)] += 1
        }

        return frequencies.toList()
    }

    /**
     * Converts a character to its index in the alphabet [0-25]
     *
     * Example: 'a' or 'A' -> 0, 'z' or 'Z' -> 25
     *
     * Time Complexity: O(1)
     */
    private fun charToIndex(char: Char): Int =
        char.lowercaseChar().code - 'a'.code
}
