package digital.tonima.algorithms.stack

/**
 * Evaluate Reverse Polish Notation
 *
 * LeetCode: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Time Complexity: O(n) - where n is the number of tokens, as we iterate through each token once
 * Space Complexity: O(n) - in the worst case, all tokens are numbers and stored in the stack
 */
class ReversePolishNotationSolution {
    val stack = ArrayDeque<Int>()
    fun evalRPN(tokens: Array<String>): Int {
        for (token in tokens) {
            token.toIntOrNull()?.let {
                stack.add(it)
            } ?: run {
                val b = stack.removeLast()
                val a = stack.removeLast()
                val result = when (token) {
                    "+" -> a + b
                    "-" -> a - b
                    "*" -> a * b
                    "/" -> a / b
                    else -> 0
                }
                stack.add(result)
            }
        }

        return stack.removeLast()
    }
}
