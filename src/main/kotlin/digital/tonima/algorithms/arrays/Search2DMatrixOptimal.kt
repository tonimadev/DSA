package digital.tonima.algorithms.arrays

/**
 * Search a 2D Matrix - Optimal Solution (LeetCode #74)
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * Treats the 2D matrix as a 1D sorted array and applies a single binary search.
 * Uses index conversion: row = mid / cols, col = mid % cols
 *
 * Time Complexity: O(log(m * n))
 *   - m: number of rows
 *   - n: number of columns
 *   - Single binary search on virtual 1D array of size m*n
 *
 * Space Complexity: O(1) - constant space, no extra data structures
 */
class Search2DMatrixOptimalSolution {
    /**
     * Searches for a target value in a 2D sorted matrix using binary search.
     *
     * Algorithm:
     * 1. Treat the 2D matrix as a 1D sorted array
     * 2. Convert 1D index to 2D coordinates: row = mid / cols, col = mid % cols
     * 3. Perform standard binary search on the virtual 1D array
     *
     * @param matrix 2D sorted array (left to right, top to bottom)
     * @param target Value to search for
     * @return true if target is found, false otherwise
     */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val rows = matrix.size
        val cols = matrix[0].size
        var left = 0
        // right aponta para o último índice (índice total - 1)
        // Example: matrix 3×4 → right = 3*4-1 = 11
        var right = rows * cols - 1

        while (left <= right) {
            val mid = (left + right) / 2
            // Convert 1D index to 2D coordinates
            // Matrix 3×4 example:
            //   col: 0    1    2    3
            // row 0: [1] [2] [3] [4]
            // row 1: [5] [6] [7] [8]
            // row 2: [9][10][11][12]
            //
            // Indices: 0,1,2,3,4,5,6,7,8,9,10,11
            // mid=5  → row=5/4=1, col=5%4=1 → matrix[1][1]=6
            // mid=10 → row=10/4=2, col=10%4=2 → matrix[2][2]=11 (target=10 → move LEFT)
            val row = mid / cols
            val col = mid % cols
            val guess = matrix[row][col]

            when {
                guess == target -> return true
                guess < target -> left = mid + 1  // ▶ guess=6, target=10 → move RIGHT
                else -> right = mid - 1  // ◀ guess=11, target=10 → move LEFT
            }
        }

        return false
    }
}


