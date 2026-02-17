package digital.tonima.algorithms.linkedlist

/**
 * LRU Cache Implementation using LinkedHashMap
 *
 * Time Complexity:
 * - get(key): O(1)
 * - put(key, value): O(1)
 *
 * Space Complexity: O(capacity)
 *
 * LeetCode: https://leetcode.com/problems/lru-cache/
 *
 * LinkedHashMap maintains insertion order and provides O(1) access.
 * Override removeEldestEntry() for automatic LRU eviction.
 */
class LRUCache(val capacity: Int) {

    // LinkedHashMap maintains insertion order (access order when accessOrder=true)
    // and automatically evicts least recently used entries
    private val cache = object : LinkedHashMap<Int, Int>(capacity, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
            return size > capacity
        }
    }

    /**
     * Get value from cache
     * Time Complexity: O(1)
     * Accessing the key automatically moves it to the end (most recently used)
     */
    fun get(key: Int): Int {
        return cache[key] ?: -1
    }

    /**
     * Put key-value pair in cache
     * Time Complexity: O(1)
     * New entries are added at the end (most recently used).
     * Oldest entry is automatically removed if capacity exceeded.
     */
    fun put(key: Int, value: Int) {
        cache[key] = value
    }
}
