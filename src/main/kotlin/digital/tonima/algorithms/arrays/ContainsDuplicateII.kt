package digital.tonima.algorithms.arrays

class ContainsDuplicateIISolution {
    // LeetCode #219: https://leetcode.com/problems/contains-duplicate-ii/
    // Complexity: Time O(n), Space O(n)
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val lastSeen = hashMapOf<Int, Int>()
        for (index in nums.indices) {
            val value = nums[index]
            val previousIndex = lastSeen[value]
            if (previousIndex != null && index - previousIndex <= k) {
                return true
            }
            lastSeen[value] = index
        }
        return false
    }
}
