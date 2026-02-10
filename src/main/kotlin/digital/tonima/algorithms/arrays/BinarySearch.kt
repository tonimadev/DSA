package digital.tonima.algorithms.arrays

/**
 * LeetCode #704 - Binary Search
 * https://leetcode.com/problems/binary-search/
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
class BinarySearchSolution {
    fun search(nums: IntArray, target: Int): Int {

        if (nums.size == 1 && target == nums[0]) return 0

        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            var guessIndex = (left + right) / 2
            val guess = nums[guessIndex]

            if (guess == target) {
                return guessIndex
            } else if (guess > target) {
                right = --guessIndex
            } else {
                left = ++guessIndex
            }
        }

        return -1
    }
}
