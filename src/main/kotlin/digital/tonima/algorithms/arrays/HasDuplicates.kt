package digital.tonima.algorithms.arrays

/**
 * LeetCode #217 - Contains Duplicate
 * https://leetcode.com/problems/contains-duplicate/
 *
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 *
 * Time Complexity: O(n) - where n is the length of the array
 *   - We iterate through the array once
 *   - HashSet add() and contains() operations are O(1) on average
 *
 * Space Complexity: O(n) - where n is the length of the array
 *   - In the worst case (no duplicates), we store all n elements in the HashSet
 */
class HasDuplicatesSolution {
    fun hasDuplicate(nums: IntArray): Boolean {
        val set = mutableSetOf<Int>()

        for(num in nums) {
            if(!set.add(num)) {
                return true
            }
        }
        return false

    }
}
