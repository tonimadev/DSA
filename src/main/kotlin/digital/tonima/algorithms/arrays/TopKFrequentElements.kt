package digital.tonima.algorithms.arrays

/**
 * Top K Frequent Elements Solution
 *
 * Problem: Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Explanation: 1 appears 3 times, 2 appears 2 times, 3 appears 1 time
 * The 2 most frequent elements are 1 and 2
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Example 3:
 * Input: nums = [4,1,-1,2,-1,2,3], k = 2
 * Output: [-1,2]
 * Explanation: -1 and 2 both appear 2 times (most frequent)
 *
 * Link: https://leetcode.com/problems/top-k-frequent-elements/
 */
class TopKFrequentElementsSolution {
    /**
     * Finds the k most frequent elements using bucket sort approach.
     *
     * Time Complexity: O(n)
     *   - Building frequency map: O(n) where n is the number of elements
     *   - Building buckets: O(m) where m is the number of unique elements (m <= n)
     *   - Collecting results: O(n) in worst case when traversing all buckets
     *   - Total: O(n + m) = O(n)
     *
     * Space Complexity: O(m)
     *   - HashMap for frequencies: O(m) where m is the number of unique elements
     *   - Buckets array: O(n + 1)
     *   - Result array: O(k)
     *   - Total: O(n + m) = O(n)
     *
     * Algorithm:
     * 1. Count frequency of each element using HashMap - O(n)
     * 2. Create buckets array where index = frequency and value = list of elements with that frequency
     * 3. Traverse buckets from highest frequency to lowest
     * 4. Collect k elements from the buckets
     *
     * @param nums Integer array to find top k frequent elements
     * @param k Number of frequent elements to return
     * @return IntArray containing k most frequent elements
     */
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        // Step 1: Count frequency of each element - O(n)
        val countMap = HashMap<Int, Int>()
        for (n in nums) {
            countMap[n] = countMap.getOrDefault(n, 0) + 1
        }

        // Step 2: Create buckets where index = frequency - O(m)
        // Index 0 is unused, indices 1 to nums.size represent possible frequencies
        val buckets = Array<MutableList<Int>?>(nums.size + 1) { null }

        // Step 3: Place elements into buckets based on their frequency - O(m)
        for ((num, frequency) in countMap) {
            if (buckets[frequency] == null) {
                buckets[frequency] = ArrayList()
            }
            buckets[frequency]?.add(num)
        }

        // Step 4: Collect k elements from highest frequency buckets - O(n)
        val result = IntArray(k)
        var indexResult = 0

        // Traverse buckets from highest frequency (size) to lowest (1)
        for (i in buckets.size - 1 downTo 0) {
            val currentBucket = buckets[i]

            if (currentBucket != null) {
                for (num in currentBucket) {
                    result[indexResult++] = num

                    // Early exit when we have collected k elements
                    if (indexResult == k) {
                        return result
                    }
                }
            }
        }

        return result
    }
}
