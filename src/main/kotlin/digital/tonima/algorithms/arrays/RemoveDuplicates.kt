package digital.tonima.algorithms.arrays

/**
 * [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class RemoveDuplicatesSolution {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }

        var distinctIndex = 0

        for (i in 1 until nums.size) {
            if (nums[i] != nums[distinctIndex]) {
                distinctIndex++
                nums[distinctIndex] = nums[i]
            }
        }
        return distinctIndex + 1
    }
}
