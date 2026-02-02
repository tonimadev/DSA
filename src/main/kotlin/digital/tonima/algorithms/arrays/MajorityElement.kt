package digital.tonima.algorithms.arrays

/**
 * LeetCode #169 - Majority Element
 * https://leetcode.com/problems/majority-element/
 *
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * Boyer-Moore Voting Algorithm:
 * - Maintains a candidate and a count
 * - If count is 0, select current element as candidate
 * - If current element matches candidate, increment count
 * - Otherwise, decrement count
 * - The majority element will always be the final candidate
 *
 * Time Complexity: O(n) - Single pass through the array
 * Space Complexity: O(1) - Only uses two variables (candidate and count)
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 */
class MajorityElementSolution {
    /**
     * Finds the majority element using Boyer-Moore Voting Algorithm
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun majorityElement(nums: IntArray): Int {
        var candidate = 0
        var count = 0

        // O(n) - Single pass through array
        nums.forEach { item ->
            if (count == 0) {
                candidate = item  // O(1) - Select new candidate when count reaches 0
            }
            // O(1) - Increment if matches candidate, decrement otherwise
            count += if (item == candidate) 1 else -1
        }

        return candidate  // O(1) - Return the majority element
    }
}
