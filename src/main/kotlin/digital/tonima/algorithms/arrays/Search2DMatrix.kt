package digital.tonima.algorithms.arrays

/**
 * Search a 2D Matrix (LeetCode #74)
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * Time Complexity: O(n + log m)
 *   - n: number of rows (worst case, iterate through all rows)
 *   - m: number of columns (binary search on the row)
 *
 * Space Complexity: O(1) - constant space, no extra data structures
 */
class Search2DMatrixSolution {
    /**
     * Searches for a target value in a 2D sorted matrix.
     * First finds the correct row, then performs binary search on that row.
     */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var currentRow = 0

        while (currentRow < matrix.size) {
            val lastValueInRow = matrix[currentRow][matrix[currentRow].size - 1]
            if (lastValueInRow == target) {
                return true
            } else if (lastValueInRow < target) {
                currentRow++
            } else {
                var left = 0
                var right = matrix[currentRow].size - 1

                while (left <= right) {
                    val guessIndex = (left + right) / 2
                    val guess = matrix[currentRow][guessIndex]

                    if (guess == target) {
                        return true
                    } else if (guess > target) {
                        right = guessIndex - 1
                    } else {
                        left = guessIndex + 1
                    }
                }
                return false
            }
        }

        return false
    }
}
