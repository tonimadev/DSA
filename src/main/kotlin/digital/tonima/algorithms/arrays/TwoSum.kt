package digital.tonima.algorithms.arrays

/**
 * Two Sum
 *
 * LeetCode: https://leetcode.com/problems/two-sum/
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers
 * such that they add up to target.
 *
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1]
 *
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * Time Complexity: O(n) - Single pass through array with HashMap lookup O(1)
 * Space Complexity: O(n) - HashMap stores up to n numbers in worst case
 */
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray? {
        // Map structure: Key = Number (Value), Value = Index
        // We name it 'seenNumbers' to indicate it holds the history of what we traversed.
        val seenNumbers = HashMap<Int, Int>()

        for (currentIndex in nums.indices) {
            val currentNumber = nums[currentIndex]
            val neededComplement = target - currentNumber

            // Check if the complement (the number we need) exists in the history
            if (seenNumbers.containsKey(neededComplement)) {
                val complementIndex = seenNumbers[neededComplement]!!

                // Return: [Index of the number found previously, Current Index]
                return intArrayOf(complementIndex, currentIndex)
            }

            // Store: Current Number (Key) -> Current Index (Value)
            seenNumbers[currentNumber] = currentIndex
        }

        return null
    }
}

