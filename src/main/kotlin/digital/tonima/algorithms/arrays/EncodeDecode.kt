package digital.tonima.algorithms.arrays

/**
 * Encode and Decode Strings
 *
 * LeetCode: https://leetcode.com/problems/encode-and-decode-strings/ (Premium)
 * NeetCode: https://neetcode.io/problems/string-encode-and-decode
 *
 * Design an algorithm to encode a list of strings to a single string and decode it back.
 * The encoded string is then sent over the network and decoded back to the original list of strings.
 */
class EncodeDecodeSolution() {

    /**
     * Encodes a list of strings to a single string using length prefix protocol.
     * Format: "{length}#{string}{length}#{string}..."
     *
     * Time Complexity: O(n) where n is the total number of characters in all strings
     * Space Complexity: O(n) for the resulting encoded string
     */
    fun encode(strs: List<String>): String {
        return strs.joinToString("") { "${it.length}#$it" }
    }

    /**
     * Decodes a single string back to a list of strings using length prefix protocol.
     *
     * Time Complexity: O(n) where n is the length of the encoded string
     * Space Complexity: O(n) for the resulting list of strings
     */
    fun decode(str: String): List<String> {
        val result = mutableListOf<String>()
        var i = 0
        while (i < str.length) {
            // Find the delimiter to read the length
            val delimiterIndex = str.indexOf('#', i)
            val length = str.substring(i, delimiterIndex).toInt()
            i = delimiterIndex + 1
            // Extract the string of the specified length
            result.add(str.substring(i, i + length))
            i += length
        }
        return result
    }

}
