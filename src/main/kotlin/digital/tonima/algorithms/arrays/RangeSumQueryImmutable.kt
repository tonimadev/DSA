package digital.tonima.algorithms.arrays

/**
 * LeetCode #303 - Range Sum Query - Immutable
 * https://leetcode.com/problems/range-sum-query-immutable/
 */
class NumArray(nums: IntArray) {

    val prefixSum = IntArray(nums.size + 1)

    init {
        // Example:
        // nums = [2, 5, 3]
        // prefixSum starts as [0, 0, 0, 0]
        // i=0, num=2 -> prefixSum[1] = prefixSum[0] + 2 = 0 + 2 = 2
        // prefixSum = [0, 2, 0, 0]
        // i=1, num=5 -> prefixSum[2] = prefixSum[1] + 5 = 2 + 5 = 7
        // prefixSum = [0, 2, 7, 0]
        // i=2, num=3 -> prefixSum[3] = prefixSum[2] + 3 = 7 + 3 = 10
        // prefixSum = [0, 2, 7, 10]
        // Time: O(n), Space: O(n) for prefix sums.
        nums.forEachIndexed { i, num ->
            prefixSum[i + 1] = prefixSum[i] + num
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        // Time: O(1), Space: O(1) per query.
        return prefixSum[right + 1] - prefixSum[left]
    }

}
