package digital.tonima.algorithms.stack

/**
 * Valid Parentheses
 *
 * Determines if a string containing parentheses '()', braces '{}', and brackets '[]' is valid.
 * An input string is valid if:
 * - Open brackets must be closed by the same type of brackets
 * - Open brackets must be closed in the correct order
 * - Every close bracket has a corresponding open bracket of the same type
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * Explanation: Opening '(' does not match closing ']'
 *
 * Example 4:
 * Input: s = "([)]"
 * Output: false
 * Explanation: Brackets are not closed in correct order
 *
 * Example 5:
 * Input: s = "{[]}"
 * Output: true
 *
 * Problem: https://leetcode.com/problems/valid-parentheses/
 *
 * Time Complexity: O(n) - where n is the length of the string
 * Space Complexity: O(n) - in the worst case, the stack can contain all characters
 */
class ValidParenthesesSolution {
    fun isValid(s: String): Boolean {
        // Stack to store opening brackets
        val stack = ArrayDeque<Char>()

        // Map closing brackets to their corresponding opening brackets
        // Lookup time: O(1)
        val closingToOpening = mutableMapOf(
            ')' to '(',
            '}' to '{',
            ']' to '['
        )

        // Time: O(n) - iterate through each character once
        for (char in s) {
            if (char in closingToOpening) {
                if (stack.isEmpty() || stack.removeLast() != closingToOpening[char]) return false
            } else {
                stack.add(char)
            }
        }

        // Valid if all brackets were matched (stack is empty)
        return stack.isEmpty()
    }
}
