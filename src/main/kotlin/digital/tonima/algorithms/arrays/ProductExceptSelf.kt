package digital.tonima.algorithms.arrays

/**
 * LeetCode #238 - Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Time Complexity: O(n) - three passes through the array
 * Space Complexity: O(1) - excluding the output array
 */
class ProductExceptSelfSolution {
    fun productExceptSelf(nums: IntArray): IntArray {
        val result = IntArray(nums.size)

        // First pass: calculate prefix products (product of all elements to the left)
        // result[i] = product of all elements before index i
        result[0] = 1
        for (i in 1 until nums.size) {
            result[i] = result[i - 1] * nums[i - 1]
        }

        // Second pass: calculate suffix products and multiply with prefix
        // suffix = product of all elements to the right
        var suffix = 1
        for (i in nums.size - 1 downTo 0) {
            result[i] = result[i] * suffix
            suffix *= nums[i]
        }

        return result
    }
}
