package digital.tonima.algorithms.arrays

class ThreeSumSolution {
    /**
     * LeetCode 15: https://leetcode.com/problems/3sum/
     * Time: O(n²) - Sorting O(n log n) + nested loop O(n²).
     * Space: O(1) excluding the output list.
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        if (nums.size < 3) return result

        nums.sort()

        for (i in nums.indices) {
            // Skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1]) continue

            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]

                when {
                    sum == 0 -> {
                        result.add(listOf(nums[i], nums[left], nums[right]))

                        // Skip duplicates for the second number
                        while (left < right && nums[left] == nums[left + 1]) left++
                        // Skip duplicates for the third number
                        while (left < right && nums[right] == nums[right - 1]) right--

                        left++
                        right--
                    }
                    sum < 0 -> left++
                    else -> right--
                }
            }
        }

        return result
    }
}
