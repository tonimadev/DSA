package digital.tonima.algorithms.dinamicprograming

/**
 * Fibonacci Number - Dynamic Programming Solution
 *
 * Problem: Calculate the nth Fibonacci number where F(0) = 0, F(1) = 1,
 * and F(n) = F(n-1) + F(n-2) for n > 1.
 *
 * LeetCode: https://leetcode.com/problems/fibonacci-number/
 * Difficulty: Easy
 * Problem #509
 *
 * This implementation uses Bottom-Up Dynamic Programming (Tabulation):
 * - Builds solution iteratively from base cases up to target
 * - Avoids recursion overhead
 * - Uses O(n) space for the table
 *
 * Time Complexity: O(n) - Single pass from 2 to n
 * Space Complexity: O(n) - Array of size n+1
 *
 * Alternative approaches:
 * - Recursive (naive): O(2^n) time, O(n) space (call stack)
 * - Memoization (Top-Down DP): O(n) time, O(n) space
 * - Space-optimized: O(n) time, O(1) space (only keep last 2 values)
 * - Matrix exponentiation: O(log n) time, O(1) space
 */
class FibonacciSolution {
    /**
     * Calculate the nth Fibonacci number using dynamic programming.
     *
     * Time Complexity: O(n) - Iterates from 2 to n once
     * Space Complexity: O(n) - Table array of size n+1
     *
     * @param n The position in Fibonacci sequence (0-indexed)
     * @return The nth Fibonacci number
     *
     * Example:
     * ```
     * fib(0) -> 0
     * fib(1) -> 1
     * fib(2) -> 1
     * fib(5) -> 5
     * fib(10) -> 55
     * ```
     */
    fun fib(n: Int): Int {
        // Base cases: F(0) = 0, F(1) = 1
        // Time: O(1), Space: O(1)
        if (n <= 1) return n

        // Initialize DP table
        // Time: O(n), Space: O(n)
        val table = IntArray(n + 1)
        table[0] = 0
        table[1] = 1

        // Build solution bottom-up
        // Time: O(n) - single loop from 2 to n
        // Space: O(1) - no additional space besides table
        for (i in 2..n) {
            table[i] = table[i - 1] + table[i - 2]
        }

        // Return result
        // Time: O(1), Space: O(1)
        return table[n]
    }
}
