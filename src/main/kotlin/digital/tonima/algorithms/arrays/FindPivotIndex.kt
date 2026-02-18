package digital.tonima.algorithms.arrays

/**
 * LeetCode #724 - Find Pivot Index
 * https://leetcode.com/problems/find-pivot-index/
 */

class FindPivotIndexSolution {
    fun pivotIndex(nums: IntArray): Int {
        // Time: O(n), Space: O(1) extra.
        val totalSum = nums.sum()
        var leftSum = 0

        for (i in nums.indices) {
            val rightSum = totalSum - leftSum - nums[i]
            if (leftSum == rightSum) return i
            leftSum += nums[i]
        }

        return -1
    }
}
