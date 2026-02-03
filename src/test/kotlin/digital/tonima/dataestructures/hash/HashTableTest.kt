package digital.tonima.dataestructures.hash

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("HashTable Tests")
class HashTableTest {

    @Test
    @DisplayName("Insert and search for integer values")
    fun testInsertAndSearch() {
        val hashTable = HashTable<Int>(16)

        hashTable.insert(10)
        hashTable.insert(20)
        hashTable.insert(30)

        assertEquals(10, hashTable.search(10))
        assertEquals(20, hashTable.search(20))
        assertEquals(30, hashTable.search(30))
        assertNull(hashTable.search(999))
    }

    @Test
    @DisplayName("Insert and check size")
    fun testSize() {
        val hashTable = HashTable<Int>(16)
        assertTrue(hashTable.isEmpty())

        hashTable.insert(1)
        assertEquals(1, hashTable.size)

        hashTable.insert(2)
        hashTable.insert(3)
        assertEquals(3, hashTable.size)
    }

    @Test
    @DisplayName("Contains method")
    fun testContains() {
        val hashTable = HashTable<Int>(16)

        hashTable.insert(10)
        hashTable.insert(20)

        assertTrue(hashTable.contains(10))
        assertTrue(hashTable.contains(20))
        assertFalse(hashTable.contains(30))
    }

    @Test
    @DisplayName("Delete existing element")
    fun testDelete() {
        val hashTable = HashTable<Int>(16)

        hashTable.insert(10)
        hashTable.insert(20)
        hashTable.insert(30)

        assertEquals(3, hashTable.size)

        hashTable.delete(20)

        assertEquals(2, hashTable.size)
        assertNull(hashTable.search(20))
        assertEquals(10, hashTable.search(10))
        assertEquals(30, hashTable.search(30))
    }

    @Test
    @DisplayName("Delete throws error for non-existent element")
    fun testDeleteNonExistent() {
        val hashTable = HashTable<Int>(16)

        hashTable.insert(10)

        assertThrows(IllegalArgumentException::class.java) {
            hashTable.delete(999)
        }
    }

    @Test
    @DisplayName("Custom key extraction function")
    fun testCustomKeyExtraction() {
        data class Person(val id: Int, val name: String) : Comparable<Person> {
            override fun compareTo(other: Person): Int = this.id.compareTo(other.id)
        }

        val hashTable = HashTable<Person>(16) { it.id }

        val person1 = Person(1, "Alice")
        val person2 = Person(2, "Bob")
        val person3 = Person(3, "Charlie")

        hashTable.insert(person1)
        hashTable.insert(person2)
        hashTable.insert(person3)

        assertEquals(person1, hashTable.search(1))
        assertEquals(person2, hashTable.search(2))
        assertEquals(person3, hashTable.search(3))

        hashTable.delete(person2)
        assertNull(hashTable.search(2))
    }

    @Test
    @DisplayName("Hash collision handling with chaining")
    fun testCollisionHandling() {
        // Create small hash table to force collisions
        val hashTable = HashTable<String>(2)

        hashTable.insert("key1")
        hashTable.insert("key2")
        hashTable.insert("key3")
        hashTable.insert("key4")

        assertEquals(4, hashTable.size)
        assertEquals("key1", hashTable.search(hashTable.getKey("key1")))
        assertEquals("key2", hashTable.search(hashTable.getKey("key2")))
        assertEquals("key3", hashTable.search(hashTable.getKey("key3")))
        assertEquals("key4", hashTable.search(hashTable.getKey("key4")))
    }

    @Test
    @DisplayName("Insert duplicate values with same key")
    fun testDuplicateInsert() {
        val hashTable = HashTable<Int>(16)

        hashTable.insert(10)
        hashTable.insert(10)
        hashTable.insert(10)

        // Should find the value even though it's stored multiple times
        assertEquals(10, hashTable.search(10))
        assertEquals(3, hashTable.size)
    }

    @Test
    @DisplayName("Throw error for invalid bucket count")
    fun testInvalidBucketCount() {
        assertThrows(IllegalArgumentException::class.java) {
            HashTable<Int>(0)
        }

        assertThrows(IllegalArgumentException::class.java) {
            HashTable<Int>(-5)
        }
    }

    @Test
    @DisplayName("Empty table is empty")
    fun testEmptyTable() {
        val hashTable = HashTable<Int>(16)

        assertTrue(hashTable.isEmpty())
        assertEquals(0, hashTable.size)
    }
}
