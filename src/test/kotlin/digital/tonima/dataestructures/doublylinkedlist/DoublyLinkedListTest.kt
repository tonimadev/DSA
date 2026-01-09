package digital.tonima.dataestructures.doublylinkedlist

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@DisplayName("DoublyLinkedList Tests")
class DoublyLinkedListTest {

    private lateinit var list: DoublyLinkedList<Int>

    @BeforeEach
    fun setUp() {
        list = DoublyLinkedList()
    }

    // ============ Append Tests ============

    @Test
    @DisplayName("append - Append to empty list should create head and tail")
    fun testAppendToEmptyList() {
        list.append(1)

        assertEquals(1, getLength(list), "Length should be 1 after appending to empty list")
        assertEquals(1, getHeadValue(list), "Head value should be 1")
        assertEquals(1, getTailValue(list), "Tail value should be 1")
        assertNull(getPreviousOfHead(list), "Head previous should be null")
        assertNull(getNextOfTail(list), "Tail next should be null")
    }

    @Test
    @DisplayName("append - Append multiple elements should maintain order")
    fun testAppendMultipleElements() {
        list.append(1)
        list.append(2)
        list.append(3)

        assertEquals(3, getLength(list), "Length should be 3")
        assertEquals(1, getHeadValue(list), "Head value should be 1")
        assertEquals(3, getTailValue(list), "Tail value should be 3")

        // Verify the chain: 1 <-> 2 <-> 3
        assertEquals(2, getNextValue(list, 1), "Second element should be 2")
        assertEquals(3, getNextValue(list, 2), "Third element should be 3")
    }

    @Test
    @DisplayName("append - Append second element should link previous")
    fun testAppendSecondElementLinks() {
        list.append(10)
        list.append(20)

        assertEquals(2, getLength(list), "Length should be 2")

        // First node (10) -> next should be 20
        assertEquals(20, getNextValue(list, 10), "Next of first node should be 20")

        // Second node (20) -> previous should be 10
        assertEquals(10, getPreviousValue(list, 20), "Previous of second node should be 10")
    }

    @Test
    @DisplayName("append - Append should create proper bidirectional links")
    fun testAppendBidirectionalLinks() {
        list.append(5)
        list.append(10)
        list.append(15)

        // Check forward links
        assertEquals(10, getNextValue(list, 5), "5 -> 10")
        assertEquals(15, getNextValue(list, 10), "10 -> 15")
        assertNull(getNextOfTail(list), "15 has no next")

        // Check backward links
        assertNull(getPreviousOfHead(list), "5 has no previous")
        assertEquals(5, getPreviousValue(list, 10), "10 <- 5")
        assertEquals(10, getPreviousValue(list, 15), "15 <- 10")
    }

    @Test
    @DisplayName("append - Append should maintain tail reference correctly")
    fun testAppendMaintainsTailReference() {
        list.append(1)
        assertEquals(1, getTailValue(list))

        list.append(2)
        assertEquals(2, getTailValue(list))

        list.append(3)
        assertEquals(3, getTailValue(list))

        list.append(4)
        assertEquals(4, getTailValue(list))
    }

    @Test
    @DisplayName("append - Append should increment length correctly")
    fun testAppendIncrementsLength() {
        assertEquals(0, getLength(list))

        list.append(1)
        assertEquals(1, getLength(list))

        list.append(2)
        assertEquals(2, getLength(list))

        list.append(3)
        assertEquals(3, getLength(list))

        list.append(4)
        assertEquals(4, getLength(list))
    }

    @Test
    @DisplayName("append - Constructor with value should call append")
    fun testConstructorWithValue() {
        val list2 = DoublyLinkedList(42)

        assertEquals(1, getLength(list2))
        assertEquals(42, getHeadValue(list2))
        assertEquals(42, getTailValue(list2))
    }

    @Test
    @DisplayName("append - Append strings to verify generic implementation")
    fun testAppendStrings() {
        val stringList = DoublyLinkedList<String>()
        stringList.append("Hello")
        stringList.append("World")
        stringList.append("Test")

        assertEquals(3, getLength(stringList))
        assertEquals("Hello", getHeadValue(stringList))
        assertEquals("Test", getTailValue(stringList))
        assertEquals("World", getNextStringValue(stringList, "Hello"))
    }

    @Test
    @DisplayName("append - Append with duplicates should work correctly")
    fun testAppendWithDuplicates() {
        list.append(5)
        list.append(5)
        list.append(5)

        assertEquals(3, getLength(list))
        assertEquals(5, getHeadValue(list))
        assertEquals(5, getTailValue(list))
    }

    @Test
    @DisplayName("append - Append nulls in generic list should work")
    fun testAppendNullableValues() {
        val nullableList = DoublyLinkedList<Int?>()
        nullableList.append(1)
        nullableList.append(null)
        nullableList.append(3)

        assertEquals(3, getLength(nullableList))
        assertEquals(1, getHeadValue(nullableList))
        assertEquals(3, getTailValue(nullableList))
    }

    @Test
    @DisplayName("append - Large number of appends should maintain consistency")
    fun testAppendManyElements() {
        val count = 100
        repeat(count) { i ->
            list.append(i)
        }

        assertEquals(count, getLength(list))
        assertEquals(0, getHeadValue(list))
        assertEquals(count - 1, getTailValue(list))

        // Verify tail is properly set to last element
        assertEquals(count - 1, getTailValue(list))
        assertNull(getNextOfTail(list))
    }

    // ============ Prepend Tests ============

    @Test
    @DisplayName("prepend - Prepend to empty list should create head and tail")
    fun testPrependToEmptyList() {
        list.prepend(1)

        assertEquals(1, getLength(list), "Length should be 1 after prepending to empty list")
        assertEquals(1, getHeadValue(list), "Head value should be 1")
        assertEquals(1, getTailValue(list), "Tail value should be 1")
        assertNull(getPreviousOfHead(list), "Head previous should be null")
        assertNull(getNextOfTail(list), "Tail next should be null")
    }

    @Test
    @DisplayName("prepend - Prepend multiple elements should maintain reverse order")
    fun testPrependMultipleElements() {
        list.prepend(1)
        list.prepend(2)
        list.prepend(3)

        assertEquals(3, getLength(list), "Length should be 3")
        assertEquals(3, getHeadValue(list), "Head value should be 3")
        assertEquals(1, getTailValue(list), "Tail value should be 1")
    }

    @Test
    @DisplayName("prepend - Prepend second element should link previous correctly")
    fun testPrependSecondElementLinks() {
        list.prepend(10)
        list.prepend(20)

        assertEquals(2, getLength(list), "Length should be 2")

        // First node (20) -> next should be 10
        assertEquals(10, getNextValue(list, 20), "Next of first node should be 10")

        // Second node (10) -> previous should be 20
        assertEquals(20, getPreviousValue(list, 10), "Previous of second node should be 20")
    }

    @Test
    @DisplayName("prepend - Prepend should create proper bidirectional links")
    fun testPrependBidirectionalLinks() {
        list.prepend(5)
        list.prepend(10)
        list.prepend(15)

        // Check forward links
        assertEquals(10, getNextValue(list, 15), "15 -> 10")
        assertEquals(5, getNextValue(list, 10), "10 -> 5")
        assertNull(getNextOfTail(list), "5 has no next")

        // Check backward links
        assertNull(getPreviousOfHead(list), "15 has no previous")
        assertEquals(15, getPreviousValue(list, 10), "10 <- 15")
        assertEquals(10, getPreviousValue(list, 5), "5 <- 10")
    }

    @Test
    @DisplayName("prepend - Prepend should maintain head reference correctly")
    fun testPrependMaintainsHeadReference() {
        list.prepend(1)
        assertEquals(1, getHeadValue(list))

        list.prepend(2)
        assertEquals(2, getHeadValue(list))

        list.prepend(3)
        assertEquals(3, getHeadValue(list))

        list.prepend(4)
        assertEquals(4, getHeadValue(list))
    }

    @Test
    @DisplayName("prepend - Prepend should increment length correctly")
    fun testPrependIncrementsLength() {
        assertEquals(0, getLength(list))

        list.prepend(1)
        assertEquals(1, getLength(list))

        list.prepend(2)
        assertEquals(2, getLength(list))

        list.prepend(3)
        assertEquals(3, getLength(list))
    }

    @Test
    @DisplayName("prepend - Prepend with strings should work")
    fun testPrependStrings() {
        val stringList = DoublyLinkedList<String>()
        stringList.prepend("World")
        stringList.prepend("Hello")

        assertEquals(2, getLength(stringList))
        assertEquals("Hello", getHeadValue(stringList))
        assertEquals("World", getTailValue(stringList))
    }

    @Test
    @DisplayName("prepend - Prepend followed by append should work correctly")
    fun testPrependFollowedByAppend() {
        list.prepend(10)
        list.prepend(5)

        list.append(15)
        list.append(20)

        assertEquals(4, getLength(list))
        assertEquals(5, getHeadValue(list))
        assertEquals(20, getTailValue(list))
        assertEquals(10, getNextValue(list, 5))
        assertEquals(15, getNextValue(list, 10))
    }

    @Test
    @DisplayName("prepend - Append followed by prepend should work correctly")
    fun testAppendFollowedByPrepend() {
        list.append(10)
        list.append(20)

        list.prepend(5)
        list.prepend(1)

        assertEquals(4, getLength(list))
        assertEquals(1, getHeadValue(list))
        assertEquals(20, getTailValue(list))
    }

    @Test
    @DisplayName("prepend - Large number of prepends should maintain consistency")
    fun testPrependManyElements() {
        val count = 50
        repeat(count) { i ->
            list.prepend(i)
        }

        assertEquals(count, getLength(list))
        assertEquals(count - 1, getHeadValue(list))
        assertEquals(0, getTailValue(list))
        assertNull(getPreviousOfHead(list))
    }

    // ============ RemoveFirst Tests ============

    @Test
    @DisplayName("removeFirst - RemoveFirst from empty list should return null")
    fun testRemoveFirstFromEmptyList() {
        val removed = list.removeFirst()

        assertEquals(null, removed)
        assertEquals(0, getLength(list))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst from single element list should clear head and tail")
    fun testRemoveFirstFromSingleElementList() {
        list.append(42)
        assertEquals(1, getLength(list))

        val removed = list.removeFirst()

        assertEquals(0, getLength(list))
        assertNotNull(removed)
        assertEquals(42, getNodeValue(removed))
        assertNull(getHeadValue<Int>(list))
        assertNull(getTailValue<Int>(list))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst from two element list should work correctly")
    fun testRemoveFirstFromTwoElementList() {
        list.append(10)
        list.append(20)
        assertEquals(2, getLength(list))

        val removed = list.removeFirst()

        assertEquals(1, getLength(list))
        assertEquals(20, getHeadValue(list))
        assertEquals(20, getTailValue(list))
        assertNotNull(removed)
        assertEquals(10, getNodeValue(removed))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst should return the removed node")
    fun testRemoveFirstReturnsCorrectNode() {
        list.append(1)
        list.append(2)
        list.append(3)

        val removed = list.removeFirst()

        assertNotNull(removed)
        assertEquals(1, getNodeValue(removed))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst should decrement length correctly")
    fun testRemoveFirstDecrementsLength() {
        list.append(1)
        list.append(2)
        list.append(3)
        assertEquals(3, getLength(list))

        list.removeFirst()
        assertEquals(2, getLength(list))

        list.removeFirst()
        assertEquals(1, getLength(list))

        list.removeFirst()
        assertEquals(0, getLength(list))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst should update head correctly")
    fun testRemoveFirstUpdatesHeadCorrectly() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        assertEquals(10, getHeadValue(list))

        list.removeFirst()
        assertEquals(20, getHeadValue(list))

        list.removeFirst()
        assertEquals(30, getHeadValue(list))

        list.removeFirst()
        assertEquals(40, getHeadValue(list))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst should clear previous pointer of new head")
    fun testRemoveFirstClearsPreviousPointerOfNewHead() {
        list.append(1)
        list.append(2)
        list.append(3)

        list.removeFirst()

        assertNull(getPreviousOfHead(list))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst should maintain list integrity")
    fun testRemoveFirstMaintainsListIntegrity() {
        list.append(5)
        list.append(10)
        list.append(15)

        list.removeFirst()

        assertEquals(2, getLength(list))
        assertEquals(10, getHeadValue(list))
        assertEquals(15, getTailValue(list))

        // Verify forward links are correct
        val headNode = getHeadValue(list)
        assertEquals(15, getNextValue(list, 10))
    }

    @Test
    @DisplayName("removeFirst - Multiple consecutive removeFirst should work correctly")
    fun testMultipleConsecutiveRemoveFirst() {
        val values = intArrayOf(1, 2, 3, 4, 5)
        values.forEach { list.append(it) }

        assertEquals(5, getLength(list))

        // Remove first 3 elements
        list.removeFirst()
        list.removeFirst()
        list.removeFirst()

        assertEquals(2, getLength(list))
        assertEquals(4, getHeadValue(list))
        assertEquals(5, getTailValue(list))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst followed by append should work correctly")
    fun testRemoveFirstFollowedByAppend() {
        list.append(10)
        list.append(20)
        list.append(30)

        list.removeFirst()
        assertEquals(2, getLength(list))
        assertEquals(20, getHeadValue(list))

        list.append(40)
        assertEquals(3, getLength(list))
        assertEquals(40, getTailValue(list))

        // Verify the link from 30 to 40
        assertEquals(40, getNextValue(list, 30))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst all elements one by one should reset list")
    fun testRemoveFirstAllElements() {
        list.append(1)
        list.append(2)
        list.append(3)

        list.removeFirst()
        list.removeFirst()
        list.removeFirst()

        assertEquals(0, getLength(list))
        assertNull(getHeadValue<Int>(list))
        assertNull(getTailValue<Int>(list))

        // Should be able to append again
        list.append(99)
        assertEquals(1, getLength(list))
        assertEquals(99, getHeadValue(list))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst on list with strings should work")
    fun testRemoveFirstWithStrings() {
        val stringList = DoublyLinkedList<String>()
        stringList.append("Alice")
        stringList.append("Bob")
        stringList.append("Charlie")

        val removed = stringList.removeFirst()

        assertEquals(2, getLength(stringList))
        assertEquals("Bob", getHeadValue(stringList))
        assertNotNull(removed)
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst should handle large list correctly")
    fun testRemoveFirstLargeList() {
        val count = 50
        repeat(count) { i ->
            list.append(i)
        }

        assertEquals(count, getLength(list))

        repeat(30) {
            list.removeFirst()
        }

        assertEquals(count - 30, getLength(list))
        assertEquals(30, getHeadValue(list))
    }

    @Test
    @DisplayName("removeFirst - RemoveFirst and removeLast should work together")
    fun testRemoveFirstAndRemoveLast() {
        repeat(10) { i ->
            list.append(i)
        }

        assertEquals(10, getLength(list))

        list.removeFirst()
        list.removeLast()

        assertEquals(8, getLength(list))
        assertEquals(1, getHeadValue(list))
        assertEquals(8, getTailValue(list))
    }

    // ============ RemoveLast Tests ============

    @Test
    @DisplayName("removeLast - RemoveLast from empty list should return null")
    fun testRemoveLastFromEmptyList() {
        val removed = list.removeLast()
        assertEquals(null, removed)
        assertEquals(0, getLength(list))
    }

    @Test
    @DisplayName("removeLast - RemoveLast from single element list should clear head and tail")
    fun testRemoveLastFromSingleElementList() {
        list.append(42)
        val removed = list.removeLast()

        assertEquals(0, getLength(list))
        assertNull(getHeadValue<Int>(list))
        assertNull(getTailValue<Int>(list))
        assertNotNull(removed)
    }

    @Test
    @DisplayName("removeLast - RemoveLast from two element list should work correctly")
    fun testRemoveLastFromTwoElementList() {
        list.append(10)
        list.append(20)

        val removed = list.removeLast()

        assertEquals(1, getLength(list))
        assertEquals(10, getHeadValue(list))
        assertEquals(10, getTailValue(list))
        assertNull(getNextOfTail(list))
    }

    @Test
    @DisplayName("removeLast - RemoveLast should return the removed node")
    fun testRemoveLastReturnsCorrectNode() {
        list.append(1)
        list.append(2)
        list.append(3)

        val removed = list.removeLast()

        assertNotNull(removed)
        assertEquals(3, getNodeValue(removed))
    }

    @Test
    @DisplayName("removeLast - RemoveLast should decrement length")
    fun testRemoveLastDecrementsLength() {
        list.append(1)
        list.append(2)
        list.append(3)
        assertEquals(3, getLength(list))

        list.removeLast()
        assertEquals(2, getLength(list))

        list.removeLast()
        assertEquals(1, getLength(list))

        list.removeLast()
        assertEquals(0, getLength(list))
    }

    @Test
    @DisplayName("removeLast - RemoveLast should update tail correctly")
    fun testRemoveLastUpdatesTailCorrectly() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        assertEquals(40, getTailValue(list))

        list.removeLast()
        assertEquals(30, getTailValue(list))

        list.removeLast()
        assertEquals(20, getTailValue(list))

        list.removeLast()
        assertEquals(10, getTailValue(list))
    }

    @Test
    @DisplayName("removeLast - RemoveLast should clear next pointer of new tail")
    fun testRemoveLastClearsNextPointer() {
        list.append(1)
        list.append(2)
        list.append(3)

        list.removeLast()

        assertNull(getNextOfTail(list))
    }

    @Test
    @DisplayName("removeLast - RemoveLast should maintain bidirectional links after removal")
    fun testRemoveLastMaintainsBidirectionalLinks() {
        list.append(5)
        list.append(10)
        list.append(15)

        list.removeLast()

        assertEquals(2, getLength(list))
        assertEquals(5, getHeadValue(list))
        assertEquals(10, getTailValue(list))
        assertEquals(10, getNextValue(list, 5))
        assertEquals(5, getPreviousValue(list, 10))
    }

    @Test
    @DisplayName("removeLast - Multiple consecutive removeLast should work correctly")
    fun testMultipleConsecutiveRemoveLast() {
        val values = intArrayOf(1, 2, 3, 4, 5)
        values.forEach { list.append(it) }

        assertEquals(5, getLength(list))

        // Remove last 3 elements
        list.removeLast()
        list.removeLast()
        list.removeLast()

        assertEquals(2, getLength(list))
        assertEquals(1, getHeadValue(list))
        assertEquals(2, getTailValue(list))
    }

    @Test
    @DisplayName("removeLast - RemoveLast followed by append should work correctly")
    fun testRemoveLastFollowedByAppend() {
        list.append(10)
        list.append(20)
        list.append(30)

        list.removeLast()
        assertEquals(2, getLength(list))

        list.append(40)
        assertEquals(3, getLength(list))
        assertEquals(40, getTailValue(list))
        assertEquals(20, getNextValue(list, 10))
        assertEquals(40, getNextValue(list, 20))
    }

    @Test
    @DisplayName("removeLast - RemoveLast all elements one by one should reset list")
    fun testRemoveLastAllElements() {
        list.append(1)
        list.append(2)
        list.append(3)

        list.removeLast()
        list.removeLast()
        list.removeLast()

        assertEquals(0, getLength(list))
        assertNull(getHeadValue<Int>(list))
        assertNull(getTailValue<Int>(list))

        // Should be able to append again
        list.append(99)
        assertEquals(1, getLength(list))
        assertEquals(99, getHeadValue(list))
    }

    @Test
    @DisplayName("removeLast - RemoveLast on list with strings should work")
    fun testRemoveLastWithStrings() {
        val stringList = DoublyLinkedList<String>()
        stringList.append("Alice")
        stringList.append("Bob")
        stringList.append("Charlie")

        val removed = stringList.removeLast()

        assertEquals(2, getLength(stringList))
        assertEquals("Bob", getTailValue(stringList))
        assertNotNull(removed)
    }

    @Test
    @DisplayName("removeLast - RemoveLast should handle large list correctly")
    fun testRemoveLastLargeList() {
        val count = 50
        repeat(count) { i ->
            list.append(i)
        }

        assertEquals(count, getLength(list))

        repeat(30) {
            list.removeLast()
        }

        assertEquals(count - 30, getLength(list))
        assertEquals(count - 31, getTailValue(list))
        assertNull(getNextOfTail(list))
    }

    // ============ Get Tests ============

    @Test
    @DisplayName("get - Get from empty list should return null")
    fun testGetFromEmptyList() {
        val result = list.get(0)
        assertNull(result)
    }

    @Test
    @DisplayName("get - Get with negative index should return null")
    fun testGetWithNegativeIndex() {
        list.append(10)
        list.append(20)
        list.append(30)

        assertNull(list.get(-1))
        assertNull(list.get(-5))
        assertNull(list.get(-100))
    }

    @Test
    @DisplayName("get - Get with index equal to length should return null")
    fun testGetWithIndexEqualToLength() {
        list.append(10)
        list.append(20)
        list.append(30)

        // Length is 3, so index 3 is out of bounds
        assertNull(list.get(3))
    }

    @Test
    @DisplayName("get - Get with index greater than length should return null")
    fun testGetWithIndexGreaterThanLength() {
        list.append(10)
        list.append(20)

        assertNull(list.get(5))
        assertNull(list.get(10))
        assertNull(list.get(100))
    }

    @Test
    @DisplayName("get - Get first element (index 0) should return head")
    fun testGetFirstElement() {
        list.append(10)
        list.append(20)
        list.append(30)

        val node = list.get(0)
        assertNotNull(node)
        assertEquals(10, node.value)
    }

    @Test
    @DisplayName("get - Get element at index 1 should return second element")
    fun testGetSecondElement() {
        list.append(10)
        list.append(20)
        list.append(30)

        val node = list.get(1)
        assertNotNull(node)
        assertEquals(20, node.value)
    }

    @Test
    @DisplayName("get - Get last element should return tail")
    fun testGetLastElement() {
        list.append(10)
        list.append(20)
        list.append(30)

        val node = list.get(2)
        assertNotNull(node)
        assertEquals(30, node.value)
    }

    @Test
    @DisplayName("get - Get all elements in order should match appended order")
    fun testGetAllElementsInOrder() {
        val values = intArrayOf(5, 10, 15, 20, 25)
        values.forEach { list.append(it) }

        for (i in values.indices) {
            val node = list.get(i)
            assertNotNull(node, "Node at index $i should not be null")
            assertEquals(values[i], node.value, "Value at index $i should be ${values[i]}")
        }
    }

    @Test
    @DisplayName("get - Get from single element list should work correctly")
    fun testGetFromSingleElementList() {
        list.append(42)

        val node = list.get(0)
        assertNotNull(node)
        assertEquals(42, node.value)

        // Index 1 should be null
        assertNull(list.get(1))
    }

    @Test
    @DisplayName("get - Get should work after prepend")
    fun testGetAfterPrepend() {
        list.prepend(30)
        list.prepend(20)
        list.prepend(10)

        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    @DisplayName("get - Get should work after mixed append and prepend")
    fun testGetAfterMixedOperations() {
        list.append(20)
        list.prepend(10)
        list.append(30)
        list.prepend(5)

        // List should be: 5, 10, 20, 30
        assertEquals(5, list.get(0)?.value)
        assertEquals(10, list.get(1)?.value)
        assertEquals(20, list.get(2)?.value)
        assertEquals(30, list.get(3)?.value)
    }

    @Test
    @DisplayName("get - Get should work after removeLast")
    fun testGetAfterRemoveLast() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.removeLast()

        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
        assertNull(list.get(3))
    }

    @Test
    @DisplayName("get - Get should work after removeFirst")
    fun testGetAfterRemoveFirst() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.removeFirst()

        assertEquals(20, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
        assertEquals(40, list.get(2)?.value)
        assertNull(list.get(3))
    }

    @Test
    @DisplayName("get - Get with strings should work correctly")
    fun testGetWithStrings() {
        val stringList = DoublyLinkedList<String>()
        stringList.append("Alice")
        stringList.append("Bob")
        stringList.append("Charlie")

        assertEquals("Alice", stringList.get(0)?.value)
        assertEquals("Bob", stringList.get(1)?.value)
        assertEquals("Charlie", stringList.get(2)?.value)
    }

    @Test
    @DisplayName("get - Get should return correct node with correct links")
    fun testGetReturnsNodeWithCorrectLinks() {
        list.append(10)
        list.append(20)
        list.append(30)

        val node = list.get(1)
        assertNotNull(node)
        assertEquals(20, node.value)

        // Check forward link
        assertNotNull(node.next)
        assertEquals(30, node.next?.value)

        // Check backward link
        assertNotNull(node.previous)
        assertEquals(10, node.previous?.value)
    }

    @Test
    @DisplayName("get - Get multiple times should return same node reference")
    fun testGetMultipleTimesReturnsSameNode() {
        list.append(10)
        list.append(20)
        list.append(30)

        val node1 = list.get(1)
        val node2 = list.get(1)

        assertNotNull(node1)
        assertNotNull(node2)
        // Should be the same object reference
        assertEquals(node1, node2)
    }

    @Test
    @DisplayName("get - Get from large list should work correctly")
    fun testGetFromLargeList() {
        val count = 100
        repeat(count) { i ->
            list.append(i)
        }

        // Test random indices
        assertEquals(0, list.get(0)?.value)
        assertEquals(25, list.get(25)?.value)
        assertEquals(50, list.get(50)?.value)
        assertEquals(75, list.get(75)?.value)
        assertEquals(99, list.get(99)?.value)

        // Out of bounds
        assertNull(list.get(100))
        assertNull(list.get(1000))
    }

    @Test
    @DisplayName("get - Get boundary conditions should work correctly")
    fun testGetBoundaryConditions() {
        list.append(1)
        list.append(2)
        list.append(3)

        // Valid boundaries
        assertNotNull(list.get(0))  // First valid index
        assertNotNull(list.get(2))  // Last valid index

        // Invalid boundaries
        assertNull(list.get(-1))    // Just below valid range
        assertNull(list.get(3))     // Just above valid range
    }

    @Test
    @DisplayName("get - Get after removing all elements should return null")
    fun testGetAfterRemovingAllElements() {
        list.append(10)
        list.append(20)
        list.append(30)

        list.removeFirst()
        list.removeFirst()
        list.removeFirst()

        assertNull(list.get(0))
        assertNull(list.get(1))
    }

    @Test
    @DisplayName("get - Get with nullable types should work")
    fun testGetWithNullableTypes() {
        val nullableList = DoublyLinkedList<Int?>()
        nullableList.append(1)
        nullableList.append(null)
        nullableList.append(3)

        assertEquals(1, nullableList.get(0)?.value)
        assertEquals(null, nullableList.get(1)?.value)
        assertEquals(3, nullableList.get(2)?.value)
    }

    @Test
    @DisplayName("get - Get traverses forward through next pointers")
    fun testGetTraversesForward() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        // Get index 3 requires traversing through 0->1->2->3
        val node = list.get(3)
        assertNotNull(node)
        assertEquals(40, node.value)
    }

    // ============ Set Tests ============

    @Test
    @DisplayName("set - Set on empty list should return false")
    fun testSetOnEmptyList() {
        val result = list.set(0, 100)
        assertFalse(result)
    }

    @Test
    @DisplayName("set - Set with negative index should return false")
    fun testSetWithNegativeIndex() {
        list.append(10)
        list.append(20)
        list.append(30)

        assertFalse(list.set(-1, 100))
        assertFalse(list.set(-5, 100))

        // Values should remain unchanged
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    @DisplayName("set - Set with index equal to length should return false")
    fun testSetWithIndexEqualToLength() {
        list.append(10)
        list.append(20)
        list.append(30)

        val result = list.set(3, 100)
        assertFalse(result)

        // Values should remain unchanged
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    @DisplayName("set - Set with index greater than length should return false")
    fun testSetWithIndexGreaterThanLength() {
        list.append(10)
        list.append(20)

        assertFalse(list.set(5, 100))
        assertFalse(list.set(10, 100))

        // Values should remain unchanged
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
    }

    @Test
    @DisplayName("set - Set first element should update value and return true")
    fun testSetFirstElement() {
        list.append(10)
        list.append(20)
        list.append(30)

        val result = list.set(0, 100)

        assertTrue(result)
        assertEquals(100, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    @DisplayName("set - Set middle element should update value and return true")
    fun testSetMiddleElement() {
        list.append(10)
        list.append(20)
        list.append(30)

        val result = list.set(1, 200)

        assertTrue(result)
        assertEquals(10, list.get(0)?.value)
        assertEquals(200, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    @DisplayName("set - Set last element should update value and return true")
    fun testSetLastElement() {
        list.append(10)
        list.append(20)
        list.append(30)

        val result = list.set(2, 300)

        assertTrue(result)
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(300, list.get(2)?.value)
    }

    @Test
    @DisplayName("set - Set should not change length")
    fun testSetDoesNotChangeLength() {
        list.append(10)
        list.append(20)
        list.append(30)

        assertEquals(3, getLength(list))

        list.set(1, 200)

        assertEquals(3, getLength(list))
    }

    @Test
    @DisplayName("set - Set should not change head reference")
    fun testSetDoesNotChangeHeadReference() {
        list.append(10)
        list.append(20)
        list.append(30)

        val originalHeadValue = getHeadValue(list)

        list.set(1, 200)

        assertEquals(originalHeadValue, getHeadValue(list))
    }

    @Test
    @DisplayName("set - Set should not change tail reference")
    fun testSetDoesNotChangeTailReference() {
        list.append(10)
        list.append(20)
        list.append(30)

        val originalTailValue = getTailValue(list)

        list.set(0, 100)

        assertEquals(originalTailValue, getTailValue(list))
    }

    @Test
    @DisplayName("set - Set should not change node links")
    fun testSetDoesNotChangeNodeLinks() {
        list.append(10)
        list.append(20)
        list.append(30)

        list.set(1, 200)

        // Verify forward links (use updated value 200, not 20)
        assertEquals(200, getNextValue(list, 10))
        assertEquals(30, getNextValue(list, 200))

        // Verify backward links (use updated value 200, not 20)
        assertEquals(10, getPreviousValue(list, 200))
        assertEquals(200, getPreviousValue(list, 30))
    }

    @Test
    @DisplayName("set - Set multiple times should work correctly")
    fun testSetMultipleTimes() {
        list.append(10)
        list.append(20)
        list.append(30)

        assertTrue(list.set(0, 100))
        assertTrue(list.set(1, 200))
        assertTrue(list.set(2, 300))

        assertEquals(100, list.get(0)?.value)
        assertEquals(200, list.get(1)?.value)
        assertEquals(300, list.get(2)?.value)
    }

    @Test
    @DisplayName("set - Set same index multiple times should work")
    fun testSetSameIndexMultipleTimes() {
        list.append(10)
        list.append(20)
        list.append(30)

        assertTrue(list.set(1, 100))
        assertEquals(100, list.get(1)?.value)

        assertTrue(list.set(1, 200))
        assertEquals(200, list.get(1)?.value)

        assertTrue(list.set(1, 300))
        assertEquals(300, list.get(1)?.value)
    }

    @Test
    @DisplayName("set - Set on single element list should work")
    fun testSetOnSingleElementList() {
        list.append(42)

        val result = list.set(0, 99)

        assertTrue(result)
        assertEquals(99, list.get(0)?.value)
        assertEquals(1, getLength(list))
    }

    @Test
    @DisplayName("set - Set with strings should work correctly")
    fun testSetWithStrings() {
        val stringList = DoublyLinkedList<String>()
        stringList.append("Alice")
        stringList.append("Bob")
        stringList.append("Charlie")

        val result = stringList.set(1, "Robert")

        assertTrue(result)
        assertEquals("Alice", stringList.get(0)?.value)
        assertEquals("Robert", stringList.get(1)?.value)
        assertEquals("Charlie", stringList.get(2)?.value)
    }

    @Test
    @DisplayName("set - Set all elements in sequence should work")
    fun testSetAllElementsInSequence() {
        repeat(5) { i -> list.append(i) }

        // Set all elements to new values
        for (i in 0 until 5) {
            assertTrue(list.set(i, i * 10))
        }

        // Verify all values
        for (i in 0 until 5) {
            assertEquals(i * 10, list.get(i)?.value)
        }
    }

    @Test
    @DisplayName("set - Set after prepend should work correctly")
    fun testSetAfterPrepend() {
        list.prepend(30)
        list.prepend(20)
        list.prepend(10)

        assertTrue(list.set(1, 200))

        assertEquals(10, list.get(0)?.value)
        assertEquals(200, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
    }

    @Test
    @DisplayName("set - Set after removeLast should work correctly")
    fun testSetAfterRemoveLast() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.removeLast()

        assertTrue(list.set(2, 300))
        assertEquals(300, list.get(2)?.value)
        assertEquals(3, getLength(list))
    }

    @Test
    @DisplayName("set - Set after removeFirst should work correctly")
    fun testSetAfterRemoveFirst() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.removeFirst()

        assertTrue(list.set(0, 200))
        assertEquals(200, list.get(0)?.value)
        assertEquals(3, getLength(list))
    }

    @Test
    @DisplayName("set - Set with nullable types should work")
    fun testSetWithNullableTypes() {
        val nullableList = DoublyLinkedList<Int?>()
        nullableList.append(1)
        nullableList.append(2)
        nullableList.append(3)

        assertTrue(nullableList.set(1, null))
        assertEquals(1, nullableList.get(0)?.value)
        assertEquals(null, nullableList.get(1)?.value)
        assertEquals(3, nullableList.get(2)?.value)
    }

    @Test
    @DisplayName("set - Set on large list should work correctly")
    fun testSetOnLargeList() {
        val count = 100
        repeat(count) { i -> list.append(i) }

        // Set values at various indices
        assertTrue(list.set(0, 1000))
        assertTrue(list.set(25, 1025))
        assertTrue(list.set(50, 1050))
        assertTrue(list.set(99, 1099))

        assertEquals(1000, list.get(0)?.value)
        assertEquals(1025, list.get(25)?.value)
        assertEquals(1050, list.get(50)?.value)
        assertEquals(1099, list.get(99)?.value)
    }

    @Test
    @DisplayName("set - Set should maintain list integrity")
    fun testSetMaintainsListIntegrity() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.set(1, 200)
        list.set(2, 300)

        // Verify all values
        assertEquals(10, list.get(0)?.value)
        assertEquals(200, list.get(1)?.value)
        assertEquals(300, list.get(2)?.value)
        assertEquals(40, list.get(3)?.value)

        // Verify length
        assertEquals(4, getLength(list))

        // Verify head and tail
        assertEquals(10, getHeadValue(list))
        assertEquals(40, getTailValue(list))
    }

    @Test
    @DisplayName("set - Set boundary conditions should work correctly")
    fun testSetBoundaryConditions() {
        list.append(1)
        list.append(2)
        list.append(3)

        // Valid boundaries
        assertTrue(list.set(0, 100))  // First valid index
        assertTrue(list.set(2, 300))  // Last valid index

        // Invalid boundaries
        assertFalse(list.set(-1, 999))   // Just below valid range
        assertFalse(list.set(3, 999))    // Just above valid range
    }

    @Test
    @DisplayName("set - Set return value should be correct")
    fun testSetReturnValue() {
        list.append(10)
        list.append(20)
        list.append(30)

        // Valid indices should return true
        assertTrue(list.set(0, 100))
        assertTrue(list.set(1, 200))
        assertTrue(list.set(2, 300))

        // Invalid indices should return false
        assertFalse(list.set(-1, 999))
        assertFalse(list.set(3, 999))
        assertFalse(list.set(100, 999))
    }

    @Test
    @DisplayName("set - Set does not create new nodes")
    fun testSetDoesNotCreateNewNodes() {
        list.append(10)
        list.append(20)
        list.append(30)

        val originalLength = getLength(list)
        val node = list.get(1)

        list.set(1, 200)

        val sameNode = list.get(1)

        // Length should not change
        assertEquals(originalLength, getLength(list))

        // Should be the same node object, just with different value
        assertEquals(node, sameNode)
        assertEquals(200, sameNode?.value)
    }

    // ============ Insert Tests ============

    @Test
    @DisplayName("insert - Insert on empty list should return false")
    fun testInsertOnEmptyList() {
        val result = list.insert(0, 100)
        assertFalse(result)
        assertEquals(0, getLength(list))
    }

    @Test
    @DisplayName("insert - Insert with negative index should return false")
    fun testInsertWithNegativeIndex() {
        list.append(10)
        list.append(20)
        list.append(30)

        assertFalse(list.insert(-1, 100))
        assertFalse(list.insert(-5, 100))

        // Length should not change
        assertEquals(3, getLength(list))
    }

    @Test
    @DisplayName("insert - Insert with index equal to length should return false")
    fun testInsertWithIndexEqualToLength() {
        list.append(10)
        list.append(20)
        list.append(30)

        val result = list.insert(3, 100)
        assertFalse(result)
        assertEquals(3, getLength(list))
    }

    @Test
    @DisplayName("insert - Insert with index greater than length should return false")
    fun testInsertWithIndexGreaterThanLength() {
        list.append(10)
        list.append(20)

        assertFalse(list.insert(5, 100))
        assertFalse(list.insert(10, 100))

        // Length should not change
        assertEquals(2, getLength(list))
    }

    @Test
    @DisplayName("insert - Insert at index 0 should place element before head")
    fun testInsertAtIndex0() {
        list.append(20)
        list.append(30)
        list.append(40)

        val result = list.insert(0, 10)

        assertTrue(result)
        assertEquals(4, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
        assertEquals(40, list.get(3)?.value)
    }

    @Test
    @DisplayName("insert - Insert at middle index should place element correctly")
    fun testInsertAtMiddleIndex() {
        list.append(10)
        list.append(30)
        list.append(40)

        val result = list.insert(1, 20)

        assertTrue(result)
        assertEquals(4, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
        assertEquals(40, list.get(3)?.value)
    }

    @Test
    @DisplayName("insert - Insert before last element should work correctly")
    fun testInsertBeforeLastElement() {
        list.append(10)
        list.append(20)
        list.append(40)

        val result = list.insert(2, 30)

        assertTrue(result)
        assertEquals(4, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
        assertEquals(40, list.get(3)?.value)
    }

    @Test
    @DisplayName("insert - Insert should return true on success")
    fun testInsertReturnValue() {
        list.append(10)
        list.append(30)

        assertTrue(list.insert(1, 20))
    }

    @Test
    @DisplayName("insert - Insert should maintain forward links")
    fun testInsertMaintainsForwardLinks() {
        list.append(10)
        list.append(30)

        list.insert(1, 20)

        // Verify forward links
        assertEquals(20, getNextValue(list, 10))
        assertEquals(30, getNextValue(list, 20))
    }

    @Test
    @DisplayName("insert - Insert should maintain backward links")
    fun testInsertMaintainsBackwardLinks() {
        list.append(10)
        list.append(30)

        list.insert(1, 20)

        // Verify backward links
        assertEquals(10, getPreviousValue(list, 20))
        assertEquals(20, getPreviousValue(list, 30))
    }

    @Test
    @DisplayName("insert - Insert should maintain tail reference")
    fun testInsertMaintainsTailReference() {
        list.append(10)
        list.append(20)
        list.append(40)

        list.insert(2, 30)

        assertEquals(40, getTailValue(list))
    }

    @Test
    @DisplayName("insert - Insert at beginning should maintain head")
    fun testInsertAtBeginningMaintainsHead() {
        list.append(20)
        list.append(30)

        list.insert(0, 10)

        // Head should still point to the actual head, but value might change
        assertEquals(10, getHeadValue(list))
    }

    @Test
    @DisplayName("insert - Insert multiple times should work correctly")
    fun testInsertMultipleTimes() {
        list.append(10)
        list.append(40)

        assertTrue(list.insert(1, 20))
        assertTrue(list.insert(2, 30))

        assertEquals(4, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
        assertEquals(40, list.get(3)?.value)
    }

    @Test
    @DisplayName("insert - Insert at consecutive indices should work")
    fun testInsertAtConsecutiveIndices() {
        list.append(10)
        list.append(50)

        assertTrue(list.insert(1, 20))
        assertTrue(list.insert(2, 30))
        assertTrue(list.insert(3, 40))

        assertEquals(5, getLength(list))
        for (i in 0..4) {
            assertEquals((i + 1) * 10, list.get(i)?.value)
        }
    }

    @Test
    @DisplayName("insert - Insert with strings should work correctly")
    fun testInsertWithStrings() {
        val stringList = DoublyLinkedList<String>()
        stringList.append("Alice")
        stringList.append("Charlie")

        val result = stringList.insert(1, "Bob")

        assertTrue(result)
        assertEquals(3, getLength(stringList))
        assertEquals("Alice", stringList.get(0)?.value)
        assertEquals("Bob", stringList.get(1)?.value)
        assertEquals("Charlie", stringList.get(2)?.value)
    }

    @Test
    @DisplayName("insert - Insert should increase length")
    fun testInsertIncreasesLength() {
        list.append(10)
        list.append(20)

        assertEquals(2, getLength(list))

        list.insert(1, 15)

        assertEquals(3, getLength(list))
    }

    @Test
    @DisplayName("insert - Insert on single element list should work")
    fun testInsertOnSingleElementList() {
        list.append(10)

        assertTrue(list.insert(0, 5))

        assertEquals(2, getLength(list))
        assertEquals(5, list.get(0)?.value)
        assertEquals(10, list.get(1)?.value)
    }

    @Test
    @DisplayName("insert - Insert on large list should work correctly")
    fun testInsertOnLargeList() {
        repeat(50) { i ->
            list.append(i * 2)
        }

        // Insert at middle
        assertTrue(list.insert(25, 999))

        assertEquals(51, getLength(list))
        assertEquals(999, list.get(25)?.value)
    }

    @Test
    @DisplayName("insert - Insert after removeFirst should work correctly")
    fun testInsertAfterRemoveFirst() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.removeFirst()

        // After removing 10, list is [20, 30, 40]
        assertTrue(list.insert(1, 25))

        assertEquals(4, getLength(list))
        assertEquals(20, list.get(0)?.value)
        assertEquals(25, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
        assertEquals(40, list.get(3)?.value)
    }

    @Test
    @DisplayName("insert - Insert after removeLast should work correctly")
    fun testInsertAfterRemoveLast() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.removeLast()

        // After removing 40, list is [10, 20, 30]
        assertTrue(list.insert(2, 35))

        assertEquals(4, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(35, list.get(2)?.value)
        assertEquals(30, list.get(3)?.value)
    }

    @Test
    @DisplayName("insert - Insert with nullable types should work")
    fun testInsertWithNullableTypes() {
        val nullableList = DoublyLinkedList<Int?>()
        nullableList.append(1)
        nullableList.append(3)

        val result = nullableList.insert(1, null)

        assertTrue(result)
        assertEquals(3, getLength(nullableList))
        assertEquals(1, nullableList.get(0)?.value)
        assertEquals(null, nullableList.get(1)?.value)
        assertEquals(3, nullableList.get(2)?.value)
    }

    @Test
    @DisplayName("insert - Insert should not affect other elements")
    fun testInsertDoesNotAffectOtherElements() {
        list.append(10)
        list.append(30)
        list.append(40)

        list.insert(1, 20)

        // Verify other elements unchanged
        assertEquals(10, list.get(0)?.value)
        assertEquals(30, list.get(2)?.value)
        assertEquals(40, list.get(3)?.value)
    }

    @Test
    @DisplayName("insert - Insert creates new node")
    fun testInsertCreatesNewNode() {
        list.append(10)
        list.append(30)

        val nodesBefore = getLength(list)
        list.insert(1, 20)
        val nodesAfter = getLength(list)

        assertEquals(nodesBefore + 1, nodesAfter)
    }

    @Test
    @DisplayName("insert - Insert at all valid indices should work")
    fun testInsertAtAllValidIndices() {
        list.append(1)
        list.append(5)

        assertTrue(list.insert(1, 3))
        // Now: [1, 3, 5]

        assertTrue(list.insert(0, 0))
        // Now: [0, 1, 3, 5]

        assertTrue(list.insert(2, 2))
        // Now: [0, 1, 2, 3, 5]

        assertEquals(5, getLength(list))
        assertEquals(0, list.get(0)?.value)
        assertEquals(1, list.get(1)?.value)
        assertEquals(2, list.get(2)?.value)
        assertEquals(3, list.get(3)?.value)
        assertEquals(5, list.get(4)?.value)
    }

    // ============ Remove Tests ============

    @Test
    @DisplayName("remove - Remove from empty list should return null")
    fun testRemoveFromEmptyList() {
        val removed = list.remove(0)
        assertNull(removed)
        assertEquals(0, getLength(list))
    }

    @Test
    @DisplayName("remove - Remove with negative index should return null")
    fun testRemoveWithNegativeIndex() {
        list.append(10)
        list.append(20)
        list.append(30)

        assertNull(list.remove(-1))
        assertNull(list.remove(-5))

        // Length should not change
        assertEquals(3, getLength(list))
    }

    @Test
    @DisplayName("remove - Remove with index equal to length should return null")
    fun testRemoveWithIndexEqualToLength() {
        list.append(10)
        list.append(20)
        list.append(30)

        val removed = list.remove(3)
        assertNull(removed)
        assertEquals(3, getLength(list))
    }

    @Test
    @DisplayName("remove - Remove with index greater than length should return null")
    fun testRemoveWithIndexGreaterThanLength() {
        list.append(10)
        list.append(20)

        assertNull(list.remove(5))
        assertNull(list.remove(10))

        // Length should not change
        assertEquals(2, getLength(list))
    }

    @Test
    @DisplayName("remove - Remove first element should return node and update list")
    fun testRemoveFirstElement() {
        list.append(10)
        list.append(20)
        list.append(30)

        val removed = list.remove(0)

        assertNotNull(removed)
        assertEquals(10, removed.value)
        assertEquals(2, getLength(list))
        assertEquals(20, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
    }

    @Test
    @DisplayName("remove - Remove middle element should return node and update list")
    fun testRemoveMiddleElement() {
        list.append(10)
        list.append(20)
        list.append(30)

        val removed = list.remove(1)

        assertNotNull(removed)
        assertEquals(20, removed.value)
        assertEquals(2, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
    }

    @Test
    @DisplayName("remove - Remove last element should return node and update list")
    fun testRemoveLastElement() {
        list.append(10)
        list.append(20)
        list.append(30)

        val removed = list.remove(2)

        assertNotNull(removed)
        assertEquals(30, removed.value)
        assertEquals(2, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
    }

    @Test
    @DisplayName("remove - Remove should decrease length")
    fun testRemoveDecreasesLength() {
        list.append(10)
        list.append(20)
        list.append(30)
        assertEquals(3, getLength(list))

        list.remove(1)
        assertEquals(2, getLength(list))

        list.remove(0)
        assertEquals(1, getLength(list))

        list.remove(0)
        assertEquals(0, getLength(list))
    }

    @Test
    @DisplayName("remove - Remove should maintain forward links")
    fun testRemoveMaintainsForwardLinks() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.remove(1)

        // After removing 20: [10, 30, 40]
        assertEquals(30, getNextValue(list, 10))
        assertEquals(40, getNextValue(list, 30))
    }

    @Test
    @DisplayName("remove - Remove should maintain backward links")
    fun testRemoveMaintainsBackwardLinks() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.remove(2)

        // After removing 30: [10, 20, 40]
        assertEquals(20, getPreviousValue(list, 40))
        assertEquals(10, getPreviousValue(list, 20))
    }

    @Test
    @DisplayName("remove - Remove should update head when removing first element")
    fun testRemoveUpdatesHead() {
        list.append(10)
        list.append(20)
        list.append(30)

        list.remove(0)

        assertEquals(20, getHeadValue(list))
    }

    @Test
    @DisplayName("remove - Remove should update tail when removing last element")
    fun testRemoveUpdatesTail() {
        list.append(10)
        list.append(20)
        list.append(30)

        list.remove(2)

        assertEquals(20, getTailValue(list))
    }

    @Test
    @DisplayName("remove - Remove from single element list should clear head and tail")
    fun testRemoveFromSingleElementList() {
        list.append(42)

        val removed = list.remove(0)

        assertNotNull(removed)
        assertEquals(42, removed.value)
        assertEquals(0, getLength(list))
        assertNull(getHeadValue<Int>(list))
        assertNull(getTailValue<Int>(list))
    }

    @Test
    @DisplayName("remove - Remove multiple times should work correctly")
    fun testRemoveMultipleTimes() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)
        list.append(50)

        assertNotNull(list.remove(1))  // Remove 20
        assertNotNull(list.remove(2))  // Remove 40

        assertEquals(3, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
        assertEquals(50, list.get(2)?.value)
    }

    @Test
    @DisplayName("remove - Remove all elements one by one should work")
    fun testRemoveAllElements() {
        list.append(10)
        list.append(20)
        list.append(30)

        list.remove(0)
        list.remove(0)
        list.remove(0)

        assertEquals(0, getLength(list))
        assertNull(getHeadValue<Int>(list))
        assertNull(getTailValue<Int>(list))
    }

    @Test
    @DisplayName("remove - Remove with strings should work correctly")
    fun testRemoveWithStrings() {
        val stringList = DoublyLinkedList<String>()
        stringList.append("Alice")
        stringList.append("Bob")
        stringList.append("Charlie")

        val removed = stringList.remove(1)

        assertNotNull(removed)
        assertEquals("Bob", removed.value)
        assertEquals(2, getLength(stringList))
        assertEquals("Alice", stringList.get(0)?.value)
        assertEquals("Charlie", stringList.get(1)?.value)
    }

    @Test
    @DisplayName("remove - Remove after append should work correctly")
    fun testRemoveAfterAppend() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.remove(2)

        assertEquals(3, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(40, list.get(2)?.value)
    }

    @Test
    @DisplayName("remove - Remove after prepend should work correctly")
    fun testRemoveAfterPrepend() {
        list.prepend(30)
        list.prepend(20)
        list.prepend(10)

        list.remove(1)

        assertEquals(2, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
    }

    @Test
    @DisplayName("remove - Remove after insert should work correctly")
    fun testRemoveAfterInsert() {
        list.append(10)
        list.append(30)
        list.insert(1, 20)

        list.remove(1)

        assertEquals(2, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
    }

    @Test
    @DisplayName("remove - Remove on large list should work correctly")
    fun testRemoveOnLargeList() {
        val count = 100
        repeat(count) { i -> list.append(i) }

        // Remove some elements
        list.remove(25)
        list.remove(50)
        list.remove(75)

        assertEquals(count - 3, getLength(list))
    }

    @Test
    @DisplayName("remove - Remove should not affect other elements")
    fun testRemoveDoesNotAffectOtherElements() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.remove(1)

        // Other elements should remain unchanged
        assertEquals(10, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
        assertEquals(40, list.get(2)?.value)
    }

    @Test
    @DisplayName("remove - Remove consecutive indices should work")
    fun testRemoveConsecutiveIndices() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)
        list.append(50)

        list.remove(1)  // Remove 20
        list.remove(1)  // Remove 30 (now at index 1)

        assertEquals(3, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(40, list.get(1)?.value)
        assertEquals(50, list.get(2)?.value)
    }

    @Test
    @DisplayName("remove - Remove should maintain list integrity")
    fun testRemoveMaintainsListIntegrity() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)
        list.append(50)

        list.remove(2)

        // Verify all values
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(40, list.get(2)?.value)
        assertEquals(50, list.get(3)?.value)

        // Verify length
        assertEquals(4, getLength(list))

        // Verify head and tail
        assertEquals(10, getHeadValue(list))
        assertEquals(50, getTailValue(list))
    }

    @Test
    @DisplayName("remove - Remove boundary conditions should work correctly")
    fun testRemoveBoundaryConditions() {
        list.append(1)
        list.append(2)
        list.append(3)

        // Valid boundaries
        assertNotNull(list.remove(0))  // First valid index
        assertNotNull(list.remove(1))  // Last valid index after first removal

        // Invalid boundaries
        assertNull(list.remove(-1))    // Just below valid range
        assertNull(list.remove(2))     // Just above valid range
    }

    @Test
    @DisplayName("remove - Remove returns correct node")
    fun testRemoveReturnsCorrectNode() {
        list.append(10)
        list.append(20)
        list.append(30)

        val removed = list.remove(1)

        assertNotNull(removed)
        assertEquals(20, removed.value)
    }

    @Test
    @DisplayName("remove - Remove with nullable types should work")
    fun testRemoveWithNullableTypes() {
        val nullableList = DoublyLinkedList<Int?>()
        nullableList.append(1)
        nullableList.append(null)
        nullableList.append(3)

        val removed = nullableList.remove(1)

        assertNotNull(removed)
        assertEquals(null, removed.value)
        assertEquals(2, getLength(nullableList))
    }

    @Test
    @DisplayName("remove - Remove followed by append should work correctly")
    fun testRemoveFollowedByAppend() {
        list.append(10)
        list.append(20)
        list.append(30)

        list.remove(1)
        list.append(40)

        assertEquals(3, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(30, list.get(1)?.value)
        assertEquals(40, list.get(2)?.value)
    }

    @Test
    @DisplayName("remove - Remove followed by insert should work correctly")
    fun testRemoveFollowedByInsert() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.remove(2)
        list.insert(1, 25)

        assertEquals(4, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(25, list.get(1)?.value)
        assertEquals(20, list.get(2)?.value)
        assertEquals(40, list.get(3)?.value)
    }

    @Test
    @DisplayName("remove - Remove alternating elements should work")
    fun testRemoveAlternatingElements() {
        repeat(10) { i -> list.append(i) }

        // Remove every other element starting from 1
        list.remove(1)
        list.remove(2)
        list.remove(3)
        list.remove(4)

        assertEquals(6, getLength(list))
    }

    @Test
    @DisplayName("remove - Remove same index multiple times should work")
    fun testRemoveSameIndexMultipleTimes() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)
        list.append(50)

        // Always remove index 1
        list.remove(1)  // Removes 20
        list.remove(1)  // Removes 30
        list.remove(1)  // Removes 40

        assertEquals(2, getLength(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(50, list.get(1)?.value)
    }

    // ============ Reverse Tests ============

    @Test
    @DisplayName("reverse - Reverse empty list should do nothing")
    fun testReverseEmptyList() {
        list.reverse()

        assertEquals(0, getLength(list))
        assertNull(getHeadValue(list))
        assertNull(getTailValue(list))
    }

    @Test
    @DisplayName("reverse - Reverse single element list should not change")
    fun testReverseSingleElementList() {
        list.append(1)

        list.reverse()

        assertEquals(1, getLength(list))
        assertEquals(1, getHeadValue(list))
        assertEquals(1, getTailValue(list))
        assertNull(getPreviousOfHead(list))
        assertNull(getNextOfTail(list))
    }

    @Test
    @DisplayName("reverse - Reverse two element list should swap head and tail")
    fun testReverseTwoElementList() {
        list.append(1)
        list.append(2)

        list.reverse()

        assertEquals(2, getLength(list))
        assertEquals(2, getHeadValue(list))
        assertEquals(1, getTailValue(list))
        assertNull(getPreviousOfHead(list))
        assertNull(getNextOfTail(list))
    }

    @Test
    @DisplayName("reverse - Reverse three element list should reverse order")
    fun testReverseThreeElementList() {
        list.append(1)
        list.append(2)
        list.append(3)

        list.reverse()

        assertEquals(3, getLength(list))
        assertEquals(3, getHeadValue(list))
        assertEquals(1, getTailValue(list))

        // Verify order: 3 -> 2 -> 1
        assertEquals(3, list.get(0)?.value)
        assertEquals(2, list.get(1)?.value)
        assertEquals(1, list.get(2)?.value)
    }

    @Test
    @DisplayName("reverse - Reverse should maintain proper forward links")
    fun testReverseForwardLinks() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.reverse()

        // Check forward links: 40 -> 30 -> 20 -> 10
        assertEquals(30, getNextValue(list, 40))
        assertEquals(20, getNextValue(list, 30))
        assertEquals(10, getNextValue(list, 20))
        assertNull(getNextValue(list, 10))
    }

    @Test
    @DisplayName("reverse - Reverse should maintain proper backward links")
    fun testReverseBackwardLinks() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.reverse()

        // Check backward links: 40 <- 30 <- 20 <- 10
        assertNull(getPreviousValue(list, 40))
        assertEquals(40, getPreviousValue(list, 30))
        assertEquals(30, getPreviousValue(list, 20))
        assertEquals(20, getPreviousValue(list, 10))
    }

    @Test
    @DisplayName("reverse - Reverse should update head correctly")
    fun testReverseUpdatesHead() {
        list.append(1)
        list.append(2)
        list.append(3)
        list.append(4)
        list.append(5)

        assertEquals(1, getHeadValue(list))

        list.reverse()

        assertEquals(5, getHeadValue(list))
        assertNull(getPreviousOfHead(list))
    }

    @Test
    @DisplayName("reverse - Reverse should update tail correctly")
    fun testReverseUpdatesTail() {
        list.append(1)
        list.append(2)
        list.append(3)
        list.append(4)
        list.append(5)

        assertEquals(5, getTailValue(list))

        list.reverse()

        assertEquals(1, getTailValue(list))
        assertNull(getNextOfTail(list))
    }

    @Test
    @DisplayName("reverse - Reverse should not change length")
    fun testReverseDoesNotChangeLength() {
        list.append(1)
        list.append(2)
        list.append(3)
        list.append(4)
        list.append(5)

        assertEquals(5, getLength(list))

        list.reverse()

        assertEquals(5, getLength(list))
    }

    @Test
    @DisplayName("reverse - Reverse twice should restore original order")
    fun testReverseTwiceRestoresOriginal() {
        list.append(10)
        list.append(20)
        list.append(30)
        list.append(40)

        list.reverse()
        list.reverse()

        assertEquals(4, getLength(list))
        assertEquals(10, getHeadValue(list))
        assertEquals(40, getTailValue(list))
        assertEquals(10, list.get(0)?.value)
        assertEquals(20, list.get(1)?.value)
        assertEquals(30, list.get(2)?.value)
        assertEquals(40, list.get(3)?.value)
    }

    @Test
    @DisplayName("reverse - Reverse should work with large list")
    fun testReverseLargeList() {
        val count = 100
        repeat(count) { i -> list.append(i) }

        list.reverse()

        assertEquals(count, getLength(list))
        assertEquals(99, getHeadValue(list))
        assertEquals(0, getTailValue(list))

        // Check first few and last few elements
        assertEquals(99, list.get(0)?.value)
        assertEquals(98, list.get(1)?.value)
        assertEquals(97, list.get(2)?.value)
        assertEquals(2, list.get(97)?.value)
        assertEquals(1, list.get(98)?.value)
        assertEquals(0, list.get(99)?.value)
    }

    @Test
    @DisplayName("reverse - Reverse should work correctly with all nodes")
    fun testReverseAllNodesOrder() {
        list.append(5)
        list.append(4)
        list.append(3)
        list.append(2)
        list.append(1)

        list.reverse()

        // Should now be: 1 -> 2 -> 3 -> 4 -> 5
        for (i in 0 until 5) {
            assertEquals(i + 1, list.get(i)?.value)
        }
    }

    @Test
    @DisplayName("reverse - Reverse should maintain bidirectional integrity")
    fun testReverseMaintainsBidirectionalIntegrity() {
        list.append(1)
        list.append(2)
        list.append(3)
        list.append(4)

        list.reverse()

        // Verify each node's links are properly reversed
        // 4 <-> 3 <-> 2 <-> 1

        // Node with value 4 (now head)
        assertNull(getPreviousValue(list, 4))
        assertEquals(3, getNextValue(list, 4))

        // Node with value 3
        assertEquals(4, getPreviousValue(list, 3))
        assertEquals(2, getNextValue(list, 3))

        // Node with value 2
        assertEquals(3, getPreviousValue(list, 2))
        assertEquals(1, getNextValue(list, 2))

        // Node with value 1 (now tail)
        assertEquals(2, getPreviousValue(list, 1))
        assertNull(getNextValue(list, 1))
    }

    @Test
    @DisplayName("reverse - Operations after reverse should work correctly")
    fun testOperationsAfterReverse() {
        list.append(1)
        list.append(2)
        list.append(3)

        list.reverse()

        // Now list is: 3 -> 2 -> 1

        // Try append
        list.append(0)
        assertEquals(0, getTailValue(list))
        assertEquals(4, getLength(list))

        // Try prepend
        list.prepend(4)
        assertEquals(4, getHeadValue(list))
        assertEquals(5, getLength(list))

        // Try get
        assertEquals(4, list.get(0)?.value)
        assertEquals(3, list.get(1)?.value)
        assertEquals(2, list.get(2)?.value)
        assertEquals(1, list.get(3)?.value)
        assertEquals(0, list.get(4)?.value)
    }

    @Test
    @DisplayName("reverse - Reverse with duplicate values should work")
    fun testReverseWithDuplicateValues() {
        list.append(5)
        list.append(3)
        list.append(3)
        list.append(5)

        list.reverse()

        assertEquals(4, getLength(list))
        assertEquals(5, list.get(0)?.value)
        assertEquals(3, list.get(1)?.value)
        assertEquals(3, list.get(2)?.value)
        assertEquals(5, list.get(3)?.value)
    }

    @Test
    @DisplayName("reverse - Reverse should work with nullable types")
    fun testReverseWithNullableTypes() {
        val nullableList = DoublyLinkedList<Int?>()
        nullableList.append(1)
        nullableList.append(null)
        nullableList.append(3)
        nullableList.append(null)
        nullableList.append(5)

        nullableList.reverse()

        assertEquals(5, getLength(nullableList))
        assertEquals(5, nullableList.get(0)?.value)
        assertEquals(null, nullableList.get(1)?.value)
        assertEquals(3, nullableList.get(2)?.value)
        assertEquals(null, nullableList.get(3)?.value)
        assertEquals(1, nullableList.get(4)?.value)
    }

    @Test
    @DisplayName("reverse - Reverse even length list should work")
    fun testReverseEvenLengthList() {
        list.append(1)
        list.append(2)
        list.append(3)
        list.append(4)
        list.append(5)
        list.append(6)

        list.reverse()

        assertEquals(6, getLength(list))
        assertEquals(6, getHeadValue(list))
        assertEquals(1, getTailValue(list))

        for (i in 0 until 6) {
            assertEquals(6 - i, list.get(i)?.value)
        }
    }

    @Test
    @DisplayName("reverse - Reverse odd length list should work")
    fun testReverseOddLengthList() {
        list.append(1)
        list.append(2)
        list.append(3)
        list.append(4)
        list.append(5)

        list.reverse()

        assertEquals(5, getLength(list))
        assertEquals(5, getHeadValue(list))
        assertEquals(1, getTailValue(list))

        for (i in 0 until 5) {
            assertEquals(5 - i, list.get(i)?.value)
        }
    }

    // ============ PartitionList Tests ============

    @Test
    @DisplayName("partitionList - Example 1: mixed values with x=5")
    fun testPartitionListMixedValues() {
        // Input: 3 <-> 8 <-> 5 <-> 10 <-> 2 <-> 1, x = 5
        list.append(3)
        list.append(8)
        list.append(5)
        list.append(10)
        list.append(2)
        list.append(1)

        list.partitionList(5)

        // Expected output order: 3 <-> 2 <-> 1 <-> 8 <-> 5 <-> 10
        val expected = listOf(3, 2, 1, 8, 5, 10)
        assertEquals(expected.size, getLength(list))
        expected.forEachIndexed { i, v ->
            assertEquals(v, list.get(i)?.value, "Value at index $i should be $v")
        }
        // Check .previous correctness for internal nodes
        assertNull(getPreviousOfHead(list))
        assertEquals(2, list.get(1)?.value)
        assertEquals(3, list.get(1)?.previous?.value)
        assertEquals(1, list.get(2)?.value)
        assertEquals(2, list.get(2)?.previous?.value)
        assertEquals(8, list.get(3)?.value)
        assertEquals(1, list.get(3)?.previous?.value)
    }

    @Test
    @DisplayName("partitionList - Example 2: all less than x stays same order")
    fun testPartitionListAllLessThanX() {
        // Input: 1 <-> 2 <-> 3, x = 5 -> Output: 1 <-> 2 <-> 3
        list.append(1)
        list.append(2)
        list.append(3)

        list.partitionList(5)

        val expected = listOf(1, 2, 3)
        assertEquals(expected.size, getLength(list))
        expected.forEachIndexed { i, v ->
            assertEquals(v, list.get(i)?.value)
        }
        // Head/tail and pointer sanity
        assertEquals(1, getHeadValue(list))
        assertEquals(3, getTailValue(list))
        assertNull(getPreviousOfHead(list))
        assertNull(getNextOfTail(list))
        assertEquals(2, list.get(0)?.next?.value)
        assertEquals(3, list.get(1)?.next?.value)
        assertEquals(2, list.get(2)?.previous?.value)
    }

    @Test
    @DisplayName("partitionList - Example 3: all greater or equal than x stays same order")
    fun testPartitionListAllGreaterOrEqualX() {
        // Input: 6 <-> 7 <-> 8, x = 5 -> Output: 6 <-> 7 <-> 8
        list.append(6)
        list.append(7)
        list.append(8)

        list.partitionList(5)

        val expected = listOf(6, 7, 8)
        assertEquals(expected.size, getLength(list))
        expected.forEachIndexed { i, v ->
            assertEquals(v, list.get(i)?.value)
        }
        // Head/tail and pointer sanity
        assertEquals(6, getHeadValue(list))
        assertEquals(8, getTailValue(list))
        assertNull(getPreviousOfHead(list))
        assertNull(getNextOfTail(list))
        assertEquals(7, list.get(0)?.next?.value)
        assertEquals(8, list.get(1)?.next?.value)
        assertEquals(7, list.get(2)?.previous?.value)
    }

    @Test
    @DisplayName("partitionList - Stability: preserve relative order within each partition")
    fun testPartitionListStability() {
        // Values with duplicates across partitions: < x and >= x
        // Input: 4, 1, 5, 2, 5, 3, 6 with x = 5
        list.append(4)
        list.append(1)
        list.append(5)
        list.append(2)
        list.append(5)
        list.append(3)
        list.append(6)

        list.partitionList(5)

        // Expected: (<5 in original order) 4,1,2,3 then (>=5 in original order) 5,5,6
        val expected = listOf(4, 1, 2, 3, 5, 5, 6)
        assertEquals(expected.size, getLength(list))
        expected.forEachIndexed { i, v ->
            assertEquals(v, list.get(i)?.value)
        }
        // Check a few prev pointers around the boundary
        assertEquals(3, list.get(3)?.value)
        assertEquals(2, list.get(3)?.previous?.value)
        assertEquals(5, list.get(4)?.value)
        assertEquals(3, list.get(4)?.previous?.value)
    }

    @Test
    @DisplayName("partitionList - Edge cases: empty and single-element lists")
    fun testPartitionListEdgeCases() {
        // Empty list
        val empty = DoublyLinkedList<Int>()
        empty.partitionList(5)
        assertEquals(0, getLength(empty))
        assertNull(getHeadValue<Int>(empty))
        assertNull(getTailValue<Int>(empty))

        // Single element list (< x)
        val oneLt = DoublyLinkedList<Int>()
        oneLt.append(1)
        oneLt.partitionList(5)
        assertEquals(1, getLength(oneLt))
        assertEquals(1, getHeadValue(oneLt))
        assertEquals(1, getTailValue(oneLt))

        // Single element list (>= x)
        val oneGe = DoublyLinkedList<Int>()
        oneGe.append(7)
        oneGe.partitionList(5)
        assertEquals(1, getLength(oneGe))
        assertEquals(7, getHeadValue(oneGe))
        assertEquals(7, getTailValue(oneGe))
    }
}

