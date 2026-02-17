package digital.tonima.algorithms.linkedlist

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertEquals

class LRUCacheTest {

    private lateinit var cache: LRUCache

    @BeforeEach
    fun setUp() {
        cache = LRUCache(2)
    }

    @Test
    fun testPutAndGet() {
        // Test adding a key-value pair and retrieving it
        cache.put(1, 1)
        assertEquals(1, cache.get(1))
    }

    @Test
    fun testGetNonExistentKey() {
        // Test getting a key that was never added
        assertEquals(-1, cache.get(1))
    }

    @Test
    fun testCapacityEviction() {
        // Test that when capacity is exceeded, the least recently used item is evicted
        cache.put(1, 1)
        cache.put(2, 2)
        cache.put(3, 3)  // This should evict key 1
        assertEquals(-1, cache.get(1))  // Key 1 should be evicted
        assertEquals(2, cache.get(2))   // Key 2 should still be in cache
        assertEquals(3, cache.get(3))   // Key 3 should be in cache
    }

    @Test
    fun testGetUpdatesRecency() {
        // Test that getting a key updates its recency
        cache.put(1, 1)
        cache.put(2, 2)
        cache.get(1)  // Access key 1, making it more recently used
        cache.put(3, 3)  // This should evict key 2, not key 1
        assertEquals(1, cache.get(1))   // Key 1 should still be in cache
        assertEquals(-1, cache.get(2))  // Key 2 should be evicted
        assertEquals(3, cache.get(3))   // Key 3 should be in cache
    }

    @Test
    fun testUpdateExistingKey() {
        // Test updating the value of an existing key
        cache.put(1, 1)
        cache.put(1, 100)
        assertEquals(100, cache.get(1))
    }

    @Test
    fun testMultipleOperations() {
        // Complex scenario with multiple operations
        cache.put(1, 1)
        cache.put(2, 2)
        cache.get(1)
        cache.put(3, 3)
        assertEquals(-1, cache.get(2))
        cache.put(4, 4)
        assertEquals(-1, cache.get(1))
        assertEquals(3, cache.get(3))
        assertEquals(4, cache.get(4))
    }
}

