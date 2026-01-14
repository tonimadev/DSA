package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Test cases for LeetCode #49 - Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 *
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 */
class GroupAnagramsTest {

    private val solution = GroupAnagramsSolution()

    @Test
    fun `test groupAnagrams - LeetCode Example 1`() {
        // Input: strs = ["eat","tea","tan","ate","nat","bat"]
        // Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        val strs = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
        val result = solution.groupAnagrams(strs)

        assertEquals(3, result.size, "Should return 3 groups of anagrams")
    }

    @Test
    fun `test groupAnagrams - LeetCode Example 2`() {
        // Input: strs = [""]
        // Output: [[""]]
        val strs = arrayOf("")
        val result = solution.groupAnagrams(strs)

        assertEquals(1, result.size, "Should return 1 group with empty string")
        assertEquals(listOf(""), result[0], "Should contain empty string")
    }

    @Test
    fun `test groupAnagrams - LeetCode Example 3`() {
        // Input: strs = ["a"]
        // Output: [["a"]]
        val strs = arrayOf("a")
        val result = solution.groupAnagrams(strs)

        assertEquals(1, result.size, "Should return 1 group with single character")
        assertEquals(listOf("a"), result[0], "Should contain single character")
    }

    @Test
    fun `test groupAnagrams - Simple case with two anagrams`() {
        // Input: strs = ["ab", "ba"]
        // Output: [["ab", "ba"]]
        val strs = arrayOf("ab", "ba")
        val result = solution.groupAnagrams(strs)

        assertEquals(1, result.size, "Should group ab and ba together")
    }

    @Test
    fun `test groupAnagrams - No anagrams`() {
        // Input: strs = ["abc", "def", "ghi"]
        // Output: [["abc"], ["def"], ["ghi"]]
        val strs = arrayOf("abc", "def", "ghi")
        val result = solution.groupAnagrams(strs)

        assertEquals(3, result.size, "Should return 3 separate groups")
    }

    @Test
    fun `test groupAnagrams - Multiple anagrams in same group`() {
        // Input: strs = ["listen", "silent", "enlist", "hello"]
        // Output: [["listen", "silent", "enlist"], ["hello"]]
        val strs = arrayOf("listen", "silent", "enlist", "hello")
        val result = solution.groupAnagrams(strs)

        assertEquals(2, result.size, "Should return 2 groups")
    }

    @Test
    fun `test groupAnagrams - Case sensitivity`() {
        // Input: strs = ["OvO", "alta", "OOv", "aalt"]
        // Output: [["OvO", "OOv"], ["alta", "aalt"]]
        val strs = arrayOf("OvO", "alta", "OOv", "aalt")
        val result = solution.groupAnagrams(strs)

        assertEquals(2, result.size, "Should group case-sensitive anagrams")
    }

    @Test
    fun `test groupAnagrams - Duplicate words`() {
        // Input: strs = ["eat", "tea", "ate", "eat"]
        // Output: [["eat", "tea", "ate", "eat"]]
        val strs = arrayOf("eat", "tea", "ate", "eat")
        val result = solution.groupAnagrams(strs)

        assertEquals(1, result.size, "Should group all anagrams including duplicates")
        assertEquals(4, result[0].size, "Should contain all 4 words")
    }

    @Test
    fun `test groupAnagrams - Large input`() {
        // Input: strs = ["z", "a", "z", "a", "b"]
        // Output: [["z", "z"], ["a", "a"], ["b"]]
        val strs = arrayOf("z", "a", "z", "a", "b")
        val result = solution.groupAnagrams(strs)

        assertEquals(3, result.size, "Should return 3 groups")
    }

    @Test
    fun `test groupAnagrams - All same letters`() {
        // Input: strs = ["aaa", "aaa", "aaa"]
        // Output: [["aaa", "aaa", "aaa"]]
        val strs = arrayOf("aaa", "aaa", "aaa")
        val result = solution.groupAnagrams(strs)

        assertEquals(1, result.size, "Should group all identical strings")
        assertEquals(3, result[0].size, "Should contain all 3 strings")
    }
}

