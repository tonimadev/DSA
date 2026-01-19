package digital.tonima.sort.algorithms

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Unit tests for Quick Sort algorithm.
 * Tests various scenarios to ensure the sorting algorithm works correctly.
 */
class QuickSortTest {

    private val quickSort = QuickSort<Int>()

    @Test
    fun `test sort - empty list`() {
        val input = emptyList<Int>()
        val result = quickSort.sort(input)
        assertEquals(emptyList(), result)
    }

    @Test
    fun `test sort - single element`() {
        val input = listOf(5)
        val result = quickSort.sort(input)
        assertEquals(listOf(5), result)
    }

    @Test
    fun `test sort - two elements`() {
        val input = listOf(2, 1)
        val result = quickSort.sort(input)
        assertEquals(listOf(1, 2), result)
    }

    @Test
    fun `test sort - already sorted list`() {
        val input = listOf(1, 2, 3, 4, 5)
        val result = quickSort.sort(input)
        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }

    @Test
    fun `test sort - reverse sorted list`() {
        val input = listOf(5, 4, 3, 2, 1)
        val result = quickSort.sort(input)
        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }

    @Test
    fun `test sort - unsorted list`() {
        val input = listOf(3, 1, 4, 1, 5, 9, 2, 6)
        val result = quickSort.sort(input)
        assertEquals(listOf(1, 1, 2, 3, 4, 5, 6, 9), result)
    }

    @Test
    fun `test sort - list with duplicates`() {
        val input = listOf(4, 2, 4, 2, 4, 2)
        val result = quickSort.sort(input)
        assertEquals(listOf(2, 2, 2, 4, 4, 4), result)
    }

    @Test
    fun `test sort - list with negative numbers`() {
        val input = listOf(3, -1, 4, -5, 2)
        val result = quickSort.sort(input)
        assertEquals(listOf(-5, -1, 2, 3, 4), result)
    }

    @Test
    fun `test sort - list with mixed positive and negative`() {
        val input = listOf(-3, 5, -1, 2, -10, 8)
        val result = quickSort.sort(input)
        assertEquals(listOf(-10, -3, -1, 2, 5, 8), result)
    }

    @Test
    fun `test sort - large list`() {
        val input = (100 downTo 1).toList()
        val result = quickSort.sort(input)
        val expected = (1..100).toList()
        assertEquals(expected, result)
    }

    @Test
    fun `test sort - list with strings`() {
        val input = listOf("zebra", "apple", "banana", "cherry")
        val stringSorter = QuickSort<String>()
        val result = stringSorter.sort(input)
        assertEquals(listOf("apple", "banana", "cherry", "zebra"), result)
    }

    @Test
    fun `test sort - case sensitive strings`() {
        val input = listOf("Zebra", "apple", "Banana", "cherry")
        val stringSorter = QuickSort<String>()
        val result = stringSorter.sort(input)
        assertEquals(listOf("Banana", "Zebra", "apple", "cherry"), result)
    }

    @Test
    fun `test sort - does not modify original list`() {
        val input = listOf(3, 1, 4, 1, 5, 9, 2, 6)
        val originalInput = input.toList()
        quickSort.sort(input)
        assertEquals(originalInput, input, "Original list should not be modified")
    }

    @Test
    fun `test sort - returns a sorted and valid list`() {
        val input = listOf(3, 1, 4, 1, 5, 9, 2, 6)
        val result = quickSort.sort(input)
        for (i in 0 until result.size - 1) {
            assertTrue(result[i] <= result[i + 1], "List should be sorted in ascending order")
        }
    }

    @Test
    fun `test sort - list with all same elements`() {
        val input = listOf(5, 5, 5, 5, 5)
        val result = quickSort.sort(input)
        assertEquals(listOf(5, 5, 5, 5, 5), result)
    }

    @Test
    fun `test name returns correct algorithm name`() {
        assertEquals("Quick Sort", quickSort.name())
    }

    @Test
    fun `test timeComplexity returns correct complexity`() {
        assertEquals("O(n log n) average, O(n²) worst", quickSort.timeComplexity())
    }

    @Test
    fun `test spaceComplexity returns correct complexity`() {
        assertEquals("O(log n)", quickSort.spaceComplexity())
    }

    @Test
    fun `test isStable returns false`() {
        assertEquals(false, quickSort.isStable())
    }

    @Test
    fun `test sort - double values`() {
        val doubleSorter = QuickSort<Double>()
        val input = listOf(3.5, 1.2, 4.8, 1.2, 5.1)
        val result = doubleSorter.sort(input)
        assertEquals(listOf(1.2, 1.2, 3.5, 4.8, 5.1), result)
    }

    @Test
    fun `test sort - float values`() {
        val floatSorter = QuickSort<Float>()
        val input = listOf(3.5f, 1.2f, 4.8f, 1.2f, 5.1f)
        val result = floatSorter.sort(input)
        assertEquals(listOf(1.2f, 1.2f, 3.5f, 4.8f, 5.1f), result)
    }

    @Test
    fun `test sort - random large dataset`() {
        val input = List(1000) { (0..10000).random() }
        val result = quickSort.sort(input)
        for (i in 0 until result.size - 1) {
            assertTrue(result[i] <= result[i + 1], "List should be sorted in ascending order")
        }
    }

    @Test
    fun `test sort - worst case scenario - already sorted`() {
        // This is worst case for Quick Sort with first element as pivot
        val input = (1..50).toList()
        val result = quickSort.sort(input)
        assertEquals(input, result)
    }
}
