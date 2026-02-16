package digital.tonima.algorithms.linkedlist

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

/**
 * Test cases for LeetCode #707 - Design Linked List
 * https://leetcode.com/problems/design-linked-list/
 */
class DesignLinkedListTest {

    private fun toArray(list: MyLinkedList): IntArray {
        if (list.length == 0) return intArrayOf()
        return IntArray(list.length) { index -> list.get(index) }
    }

    private fun assertNodeLinks(
        node: MyLinkedList.Node?,
        expectedPrev: MyLinkedList.Node?,
        expectedNext: MyLinkedList.Node?,
    ) {
        assertEquals(expectedPrev, node?.previous)
        assertEquals(expectedNext, node?.next)
    }

    @Test
    fun testGetOnEmptyList() {
        val list = MyLinkedList()
        assertEquals(-1, list.get(0))
        assertEquals(-1, list.get(1))
    }

    @Test
    fun testAddAtHead() {
        val list = MyLinkedList()
        list.addAtHead(1)
        list.addAtHead(2)
        list.addAtHead(3)
        assertContentEquals(intArrayOf(3, 2, 1), toArray(list))
        assertEquals(3, list.length)
    }

    @Test
    fun testAddAtTail() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(2)
        list.addAtTail(3)
        assertContentEquals(intArrayOf(1, 2, 3), toArray(list))
        assertEquals(3, list.length)
    }

    @Test
    fun testAddAtIndexMiddle() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(3)
        list.addAtTail(4)
        list.addAtIndex(1, 2)
        assertContentEquals(intArrayOf(1, 2, 3, 4), toArray(list))
    }

    @Test
    fun testAddAtIndexAtHead() {
        val list = MyLinkedList()
        list.addAtTail(2)
        list.addAtTail(3)
        list.addAtIndex(0, 1)
        assertContentEquals(intArrayOf(1, 2, 3), toArray(list))
    }

    @Test
    fun testAddAtIndexAtTail() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(2)
        list.addAtTail(3)
        list.addAtIndex(3, 4)
        assertContentEquals(intArrayOf(1, 2, 3, 4), toArray(list))
    }

    @Test
    fun testAddAtIndexWhenEmpty() {
        val list = MyLinkedList()
        list.addAtIndex(0, 10)
        assertContentEquals(intArrayOf(10), toArray(list))
    }

    @Test
    fun testAddAtIndexGreaterThanLengthNoOp() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(2)
        list.addAtIndex(5, 3)
        assertContentEquals(intArrayOf(1, 2), toArray(list))
    }

    @Test
    fun testDeleteAtIndexHead() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(2)
        list.addAtTail(3)
        list.deleteAtIndex(0)
        assertContentEquals(intArrayOf(2, 3), toArray(list))
    }

    @Test
    fun testDeleteAtIndexTail() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(2)
        list.addAtTail(3)
        list.deleteAtIndex(2)
        assertContentEquals(intArrayOf(1, 2), toArray(list))
    }

    @Test
    fun testDeleteAtIndexMiddle() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(2)
        list.addAtTail(3)
        list.addAtTail(4)
        list.deleteAtIndex(1)
        assertContentEquals(intArrayOf(1, 3, 4), toArray(list))
    }

    @Test
    fun testDeleteUntilEmpty() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(2)
        list.deleteAtIndex(0)
        list.deleteAtIndex(0)
        assertContentEquals(intArrayOf(), toArray(list))
        assertEquals(0, list.length)
        assertEquals(-1, list.get(0))
    }

    @Test
    fun testNodeLinksAfterAddAtHeadAndTail() {
        val list = MyLinkedList()
        list.addAtHead(2)
        list.addAtHead(1)
        list.addAtTail(3)

        val first = list.getNode(0)
        val second = list.getNode(1)
        val third = list.getNode(2)

        assertEquals(first, list.head)
        assertEquals(third, list.tail)
        assertNodeLinks(first, null, second)
        assertNodeLinks(second, first, third)
        assertNodeLinks(third, second, null)
    }

    @Test
    fun testNodeLinksAfterAddAtIndexMiddle() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(3)
        list.addAtTail(4)
        list.addAtIndex(1, 2)

        val first = list.getNode(0)
        val second = list.getNode(1)
        val third = list.getNode(2)
        val fourth = list.getNode(3)

        assertNodeLinks(first, null, second)
        assertNodeLinks(second, first, third)
        assertNodeLinks(third, second, fourth)
        assertNodeLinks(fourth, third, null)
    }

    @Test
    fun testNodeLinksAfterDeleteAtIndexMiddle() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(2)
        list.addAtTail(3)
        list.addAtTail(4)
        list.deleteAtIndex(1)

        val first = list.getNode(0)
        val second = list.getNode(1)
        val third = list.getNode(2)

        assertNodeLinks(first, null, second)
        assertNodeLinks(second, first, third)
        assertNodeLinks(third, second, null)
    }

    @Test
    fun testNodeLinksAfterDeleteHead() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(2)
        list.addAtTail(3)
        list.deleteAtIndex(0)

        val first = list.getNode(0)
        val second = list.getNode(1)

        assertEquals(first, list.head)
        assertEquals(second, list.tail)
        assertNodeLinks(first, null, second)
        assertNodeLinks(second, first, null)
    }

    @Test
    fun testNodeLinksAfterDeleteTail() {
        val list = MyLinkedList()
        list.addAtTail(1)
        list.addAtTail(2)
        list.addAtTail(3)
        list.deleteAtIndex(2)

        val first = list.getNode(0)
        val second = list.getNode(1)

        assertEquals(first, list.head)
        assertEquals(second, list.tail)
        assertNodeLinks(first, null, second)
        assertNodeLinks(second, first, null)
    }
}
