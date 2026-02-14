package digital.tonima.algorithms.arrays

class BestTimeToBuyAndSellStockSolution {
    // LeetCode #121: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    // Complexity: Time O(n), Space O(1)
    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        var minPrice = prices[0]
        var maxProfit = 0

        for (i in 1 until prices.size) {
            val price = prices[i]
            if (price < minPrice) {
                minPrice = price
            } else {
                val profit = price - minPrice
                if (profit > maxProfit) {
                    maxProfit = profit
                }
            }
        }

        return maxProfit
    }

}
