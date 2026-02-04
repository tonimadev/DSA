package digital.tonima.algorithms.arrays

class ArrayConcatenationSolution {
    /**
     * This function takes an integer array `nums` and returns a new array that is the concatenation of `nums` with itself.
     *
     * Time Complexity: O(n), where n is the size of the input array `nums`. This is because we iterate through the array twice.
     * Space Complexity: O(n), as we create a new array `nums2` of size 2 * n.
     *
     * Link to LeetCode problem: https://leetcode.com/problems/concatenation-of-array/
     */
    fun getConcatenation(nums: IntArray): IntArray {
        if (nums.isEmpty()) return intArrayOf()
        val nums2 = IntArray(nums.size * 2)
        for (index in 0 until nums.size) {
            nums2[index] = nums[index]
        }

        for (index in (0 + nums2.size) / 2 until nums2.size) {
            nums2[index] = nums[index % nums.size]
        }

        return nums2
    }
}
