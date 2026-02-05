package digital.tonima.algorithms.arrays

/**
 * LeetCode #238 - Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Given: nums = [1, 2, 3, 4]
 * Return: [24, 12, 8, 6]
 *
 * Explanation: For each position i, calculate product of ALL elements except nums[i]
 * - result[0] = 2 × 3 × 4 = 24  (everything except 1)
 * - result[1] = 1 × 3 × 4 = 12  (everything except 2)
 * - result[2] = 1 × 2 × 4 = 8   (everything except 3)
 * - result[3] = 1 × 2 × 3 = 6   (everything except 4)
 *
 * Algorithm: Two-Pass Prefix/Suffix Approach
 * Key Insight: result[i] = (product of LEFT elements) × (product of RIGHT elements)
 *
 * Example walkthrough for nums = [1, 2, 3, 4]:
 *
 * Pass 1 (→): Build prefix products
 *   i=0: result[0] = 1              (nothing to the left)
 *   i=1: result[1] = 1 × 1 = 1      (product: 1)
 *   i=2: result[2] = 1 × 2 = 2      (product: 1 × 2)
 *   i=3: result[3] = 2 × 3 = 6      (product: 1 × 2 × 3)
 *   After Pass 1: [1, 1, 2, 6]
 *
 * Pass 2 (←): Multiply by suffix products
 *   suffix = 1 initially (nothing to the right of last element)
 *   i=3: result[3] = 6 × 1 = 6,    suffix = 1 × 4 = 4
 *   i=2: result[2] = 2 × 4 = 8,    suffix = 4 × 3 = 12
 *   i=1: result[1] = 1 × 12 = 12,  suffix = 12 × 2 = 24
 *   i=0: result[0] = 1 × 24 = 24,  suffix = 24 × 1 = 24
 *   Final: [24, 12, 8, 6] ✓
 *
 * Time Complexity: O(n) - two separate passes through the array
 * Space Complexity: O(1) - only uses output array and one variable (suffix)
 */
class ProductExceptSelfSolution {
    fun productExceptSelf(nums: IntArray): IntArray {
        val result = IntArray(nums.size)

        // ═══════════════════════════════════════════════════════════
        // PASS 1: Build Prefix Products (Left → Right)
        // ═══════════════════════════════════════════════════════════
        // For each position i, store the product of all elements to its LEFT
        //
        // Example: [1, 2, 3, 4]
        //           ↓  ↓  ↓  ↓
        // result = [1, 1, 2, 6]
        //           │  │  │  └─ 1 × 2 × 3 = 6
        //           │  │  └─ 1 × 2 = 2
        //           │  └─ 1 (only element before index 1)
        //           └─ 1 (no elements before index 0)

        result[0] = 1  // Base case: nothing to the left of first element

        for (i in 1 until nums.size) {
            // result[i] = product of all elements before position i
            result[i] = result[i - 1] * nums[i - 1]
            //          └─────────┬────────┘  └────┬────┘
            //          previous prefix     element just before i
        }

        // ═══════════════════════════════════════════════════════════
        // PASS 2: Multiply by Suffix Products (Right → Left)
        // ═══════════════════════════════════════════════════════════
        // Traverse backwards, multiplying each position by the product
        // of all elements to its RIGHT
        //
        // Example: Starting with [1, 1, 2, 6] from Pass 1
        //          nums =        [1, 2, 3, 4]
        //
        // i=3: 6 × 1 = 6,   suffix becomes 4
        // i=2: 2 × 4 = 8,   suffix becomes 12 (4 × 3)
        // i=1: 1 × 12 = 12, suffix becomes 24 (12 × 2)
        // i=0: 1 × 24 = 24, suffix becomes 24 (24 × 1)
        //
        // Final: [24, 12, 8, 6]

        var suffix = 1  // Running product of elements to the right

        for (i in nums.size - 1 downTo 0) {
            // Multiply current prefix product by suffix product
            result[i] = result[i] * suffix
            //          └────┬────┘   └──┬──┘
            //          prefix from   product of
            //          Pass 1        elements to right

            // Update suffix for next iteration (moving left)
            suffix *= nums[i]
            // suffix now includes nums[i] for positions to the left
        }

        return result
    }
}
