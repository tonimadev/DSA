package digital.tonima.algorithms.arrays

class IsMonotonicSolution {
    fun isMonotonic(nums: IntArray): Boolean {
        var increasing = true
        var decreasing = true

        for (i in 0 until nums.size - 1) {
            if (nums[i] > nums[i+1]) increasing = false
            if (nums[i] < nums[i+1]) decreasing = false

            if (!increasing && !decreasing) return false
        }

        return increasing || decreasing
    }
}
