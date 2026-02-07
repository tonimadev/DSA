package digital.tonima.algorithms.arrays

class TwoSumIISolution {
    /**
     * LeetCode 167: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
     * Time: O(n) using two pointers on a sorted array.
     * Space: O(1).
     */
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.size - 1

        while (left < right) {
            val sum = numbers[left] + numbers[right]
            when {
                sum == target -> return intArrayOf(left + 1, right + 1)
                sum < target -> left++
                else -> right--
            }
        }

        return intArrayOf()
    }
}
