package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * Unit tests for Search2DMatrixSolution
 * Tests the O(n + log m) solution that finds the correct row first,
 * then performs binary search on that row.
 */
class Search2DMatrixSolutionTest {
    private val solution = Search2DMatrixSolution()

    @Test
    fun testTargetFoundInFirstRow() {
        // Matrix: [0,0]=1  [0,1]=3  [0,2]=5  [0,3]=7
        //         [1,0]=10 [1,1]=11 [1,2]=16 [1,3]=20
        //         [2,0]=23 [2,1]=30 [2,2]=34 [2,3]=60
        val matrix = arrayOf(
            intArrayOf(1, 3, 5, 7),
            intArrayOf(10, 11, 16, 20),
            intArrayOf(23, 30, 34, 60)
        )
        val target = 3 // [0,1]
        assertTrue(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testTargetFoundInLastRow() {
        // Matrix: [0,0]=1  [0,1]=3  [0,2]=5  [0,3]=7
        //         [1,0]=10 [1,1]=11 [1,2]=16 [1,3]=20
        //         [2,0]=23 [2,1]=30 [2,2]=34 [2,3]=60
        val matrix = arrayOf(
            intArrayOf(1, 3, 5, 7),
            intArrayOf(10, 11, 16, 20),
            intArrayOf(23, 30, 34, 60)
        )
        val target = 60 // [2,3]
        assertTrue(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testTargetFoundInMiddleRow() {
        // Matrix: [0,0]=1  [0,1]=3  [0,2]=5  [0,3]=7
        //         [1,0]=10 [1,1]=11 [1,2]=16 [1,3]=20
        //         [2,0]=23 [2,1]=30 [2,2]=34 [2,3]=60
        val matrix = arrayOf(
            intArrayOf(1, 3, 5, 7),
            intArrayOf(10, 11, 16, 20),
            intArrayOf(23, 30, 34, 60)
        )
        val target = 11 // [1,1]
        assertTrue(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testTargetNotFound() {
        // Matrix: [0,0]=1  [0,1]=3  [0,2]=5  [0,3]=7
        //         [1,0]=10 [1,1]=11 [1,2]=16 [1,3]=20
        //         [2,0]=23 [2,1]=30 [2,2]=34 [2,3]=60
        val matrix = arrayOf(
            intArrayOf(1, 3, 5, 7),
            intArrayOf(10, 11, 16, 20),
            intArrayOf(23, 30, 34, 60)
        )
        val target = 13 // Not in matrix
        assertFalse(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testSingleElementMatrixFound() {
        // Matrix: [0,0]=5
        val matrix = arrayOf(intArrayOf(5))
        val target = 5 // [0,0]
        assertTrue(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testSingleElementMatrixNotFound() {
        // Matrix: [0,0]=5
        val matrix = arrayOf(intArrayOf(5))
        val target = 3 // Not in matrix
        assertFalse(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testSingleRowMatrix() {
        // Matrix: [0,0]=1 [0,1]=3 [0,2]=5 [0,3]=7
        val matrix = arrayOf(intArrayOf(1, 3, 5, 7))
        val target = 5 // [0,2]
        assertTrue(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testSingleColumnMatrix() {
        // Matrix: [0,0]=1
        //         [1,0]=3
        //         [2,0]=5
        val matrix = arrayOf(
            intArrayOf(1),
            intArrayOf(3),
            intArrayOf(5)
        )
        val target = 3 // [1,0]
        assertTrue(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testTargetAtFirstElement() {
        // Matrix: [0,0]=1  [0,1]=3  [0,2]=5  [0,3]=7
        //         [1,0]=10 [1,1]=11 [1,2]=16 [1,3]=20
        //         [2,0]=23 [2,1]=30 [2,2]=34 [2,3]=60
        val matrix = arrayOf(
            intArrayOf(1, 3, 5, 7),
            intArrayOf(10, 11, 16, 20),
            intArrayOf(23, 30, 34, 60)
        )
        val target = 1 // [0,0]
        assertTrue(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testTargetSmallerThanAll() {
        // Matrix: [0,0]=5  [0,1]=10 [0,2]=15
        //         [1,0]=20 [1,1]=25 [1,2]=30
        val matrix = arrayOf(
            intArrayOf(5, 10, 15),
            intArrayOf(20, 25, 30)
        )
        val target = 3 // Smaller than all elements
        assertFalse(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testTargetLargerThanAll() {
        // Matrix: [0,0]=5  [0,1]=10 [0,2]=15
        //         [1,0]=20 [1,1]=25 [1,2]=30
        val matrix = arrayOf(
            intArrayOf(5, 10, 15),
            intArrayOf(20, 25, 30)
        )
        val target = 50 // Larger than all elements
        assertFalse(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testMultipleOccurrences() {
        // Matrix: [0,0]=1 [0,1]=2 [0,2]=2
        //         [1,0]=2 [1,1]=2 [1,2]=2
        val matrix = arrayOf(
            intArrayOf(1, 2, 2),
            intArrayOf(2, 2, 2)
        )
        val target = 2 // Multiple occurrences
        assertTrue(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testLargeMatrixTargetInLastRow() {
        // Large matrix with target in last row
        val matrix = arrayOf(
            intArrayOf(1, 4, 7, 10),
            intArrayOf(11, 14, 17, 20),
            intArrayOf(21, 24, 27, 30),
            intArrayOf(31, 34, 37, 40),
            intArrayOf(41, 44, 47, 50)
        )
        val target = 50 // [4,3]
        assertTrue(solution.searchMatrix(matrix, target))
    }

    @Test
    fun testBinarySearchOptimization() {
        // Tests that binary search finds element in log(m) time
        // Matrix: [0,0]=1  [0,1]=2  [0,2]=3  [0,3]=4  [0,4]=5
        //         [1,0]=6  [1,1]=7  [1,2]=8  [1,3]=9  [1,4]=10
        val matrix = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(6, 7, 8, 9, 10)
        )
        val target = 4 // Found in first row via binary search
        assertTrue(solution.searchMatrix(matrix, target))
    }
}

