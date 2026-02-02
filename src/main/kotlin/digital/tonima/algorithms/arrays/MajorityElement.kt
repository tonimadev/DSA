package digital.tonima.algorithms.arrays

/**
 * LeetCode #169 - Majority Element
 * https://leetcode.com/problems/majority-element/
 *
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * Boyer-Moore Voting Algorithm with Early Return Optimization:
 * - Maintains a candidate and a count
 * - If count is 0, select current element as candidate
 * - If current element matches candidate, increment count
 * - Otherwise, decrement count
 * - Early return: if count reaches ⌊n/2⌋ + 1, candidate is guaranteed to be the majority
 * - The majority element will always be the final candidate
 *
 * Time Complexity: O(n) worst case, O(n/2) best case - Can exit early when majority is found
 * Space Complexity: O(1) - Only uses three variables (candidate, count, threshold)
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * (Early return after processing all 3 elements)
 *
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * (Early return after 5th element when count reaches 4, which is > 7/2)
 *
 * Example 3:
 * Input: nums = [1,1,1,1,2,3,4]
 * Output: 1
 * (Early return after 4th element when count reaches 4, which is > 7/2)
 */
class MajorityElementSolution {
    /**
     * Finds the majority element using Boyer-Moore Voting Algorithm
     * Time Complexity: O(n) worst case, O(n/2) best case with early return
     * Space Complexity: O(1)
     */
    fun majorityElement(nums: IntArray): Int {
        var candidate = 0
        var count = 0
        val majorityThreshold = nums.size / 2 + 1  // O(1) - Calculate threshold (⌊n/2⌋ + 1)

        // O(n) - Single pass through array (can exit early)
        for (num in nums) {
            if (count == 0) {
                candidate = num  // O(1) - Select new candidate when count reaches 0
            }
            // O(1) - Increment if matches candidate, decrement otherwise
            count += if (num == candidate) 1 else -1

            // O(1) - Early return optimization: if count >= threshold, candidate is definitely the majority
            if (count >= majorityThreshold) {
                return candidate
            }
        }

        return candidate  // O(1) - Return the majority element
    }
}
