package digital.tonima.algorithms.arrays

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Test cases for LeetCode #121 - Best Time to Buy and Sell Stock
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
class BestTimeToBuyAndSellStockTest {

    private val solution = BestTimeToBuyAndSellStockSolution()

    @Test
    fun `test maxProfit - LeetCode example 1`() {
        // Input: prices = [7,1,5,3,6,4]
        // Output: 5
        val prices = intArrayOf(7, 1, 5, 3, 6, 4)
        val result = solution.maxProfit(prices)
        assertEquals(5, result)
    }

    @Test
    fun `test maxProfit - LeetCode example 2`() {
        // Input: prices = [7,6,4,3,1]
        // Output: 0
        val prices = intArrayOf(7, 6, 4, 3, 1)
        val result = solution.maxProfit(prices)
        assertEquals(0, result)
    }

    @Test
    fun `test maxProfit - two days profit`() {
        // Input: prices = [1,2]
        // Output: 1
        val prices = intArrayOf(1, 2)
        val result = solution.maxProfit(prices)
        assertEquals(1, result)
    }

    @Test
    fun `test maxProfit - increasing prices`() {
        // Input: prices = [1,2,3,4]
        // Output: 3
        val prices = intArrayOf(1, 2, 3, 4)
        val result = solution.maxProfit(prices)
        assertEquals(3, result)
    }

    @Test
    fun `test maxProfit - flat prices`() {
        // Input: prices = [3,3,3]
        // Output: 0
        val prices = intArrayOf(3, 3, 3)
        val result = solution.maxProfit(prices)
        assertEquals(0, result)
    }

    @Test
    fun `test maxProfit - single day`() {
        // Input: prices = [5]
        // Output: 0
        val prices = intArrayOf(5)
        val result = solution.maxProfit(prices)
        assertEquals(0, result)
    }

    @Test
    fun `test maxProfit - empty input`() {
        // Input: prices = []
        // Output: 0
        val prices = intArrayOf()
        val result = solution.maxProfit(prices)
        assertEquals(0, result)
    }

    @Test
    fun `test maxProfit - valley then peak`() {
        // Input: prices = [2,1,4]
        // Output: 3
        val prices = intArrayOf(2, 1, 4)
        val result = solution.maxProfit(prices)
        assertEquals(3, result)
    }
}

