package digital.tonima.dataestructures.hash

import kotlin.math.sqrt
import kotlin.math.floor
import kotlin.math.abs
import java.math.BigDecimal
import digital.tonima.dataestructures.linkedlist.LinkedList

/**
 * A hash table with chaining implementation.
 * Uses the golden ratio for hash function calculation (Knuth's method).
 *
 * Time Complexity:
 * - Average case insert/search/delete: O(1)
 * - Worst case (all collisions): O(n)
 *
 * Space Complexity: O(n + m) where n = elements, m = buckets
 */
class HashTable<T : Comparable<T>>(
    private val bucketCount: Int,
    private val extractKey: (T) -> Int = { it.hashCode() }
) {
    init {
        if (bucketCount <= 0) {
            throw IllegalArgumentException("Invalid size for the hash table (must be positive): $bucketCount")
        }
    }

    /**
     * Golden ratio constant (φ - 1) used in Knuth's multiplicative hash function
     * φ = (sqrt(5) - 1) / 2 ≈ 0.618034
     */
    private val goldenRatio = (sqrt(5f) - 1) / 2

    /**
     * Array of linked lists (chains) for collision resolution
     * Time Complexity: O(m) where m = bucketCount
     */
    private val buckets: Array<LinkedList<T>> = Array(bucketCount) {
        LinkedList()
    }

    /**
     * Returns the number of elements in the hash table
     * Time Complexity: O(n)
     */
    val size: Int
        get() = buckets.sumOf { it.size }

    /**
     * Computes the hash index for a given key using Knuth's multiplicative method
     * Formula: h(k) = floor(m * ((k * A) mod 1))
     * where A = (sqrt(5) - 1) / 2
     *
     * Time Complexity: O(1)
     */
    private fun hash(key: Int): Int {
        val keyAsDecimal = BigDecimal(key.toLong())
        val goldenAsDecimal = BigDecimal(goldenRatio.toDouble())

        // (key * A) mod 1 - get fractional part
        val product = keyAsDecimal.multiply(goldenAsDecimal)
        val fractionalPart = product.remainder(BigDecimal.ONE)

        // floor(m * fractionalPart)
        return floor(abs(bucketCount * fractionalPart.toDouble())).toInt()
    }

    /**
     * Checks if the hash table is empty
     * Time Complexity: O(1)
     */
    fun isEmpty(): Boolean = size == 0

    /**
     * Searches for a value with the given key
     *
     * Parameters:
     *   key: The key to search for
     *
     * Returns: The value if found, null otherwise
     *
     * Time Complexity: O(1) average, O(n) worst case
     */
    fun search(key: Int): T? {
        val index = hash(key)
        val bucket = buckets[index]

        // Search in the linked list for matching key
        var current = bucket.get(0)
        var searchIndex = 0
        while (current != null) {
            if (extractKey(current.value) == key) {
                return current.value
            }
            current = bucket.get(++searchIndex)
        }

        return null
    }

    /**
     * Inserts a value into the hash table
     * If value with same key exists, inserts at front (can have duplicates)
     *
     * Parameters:
     *   value: The value to insert
     *
     * Time Complexity: O(1) average
     */
    fun insert(value: T) {
        val key = extractKey(value)
        val index = hash(key)
        buckets[index].prepend(value)
    }

    /**
     * Checks if a value exists in the hash table
     *
     * Parameters:
     *   value: The value to check
     *
     * Returns: True if value exists, False otherwise
     *
     * Time Complexity: O(1) average, O(n) worst case
     */
    fun contains(value: T): Boolean {
        return search(extractKey(value)) != null
    }

    /**
     * Deletes a value from the hash table
     *
     * Parameters:
     *   value: The value to delete
     *
     * Throws: IllegalArgumentException if value not found
     *
     * Time Complexity: O(1) average, O(n) worst case
     */
    fun delete(value: T) {
        val key = extractKey(value)
        val index = hash(key)
        val bucket = buckets[index]

        if (!bucket.removeByValue(value)) {
            throw IllegalArgumentException("No element with value $value was found in the hash table.")
        }
    }

    /**
     * Returns string representation of the hash table
     */
    override fun toString(): String {
        return "HashTable(size=$size, buckets=$bucketCount)"
    }

    /**
     * Exposes the key extraction function (useful for testing)
     */
    fun getKey(value: T): Int = extractKey(value)
}
