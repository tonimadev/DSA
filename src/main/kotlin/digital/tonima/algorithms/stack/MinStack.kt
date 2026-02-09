package digital.tonima.algorithms.stack

/**
 * MinStack - A stack data structure that efficiently tracks the minimum element.
 *
 * Implements LIFO (Last In, First Out) with a special feature: retrieval of the minimum
 * element in the stack at any time.
 *
 * LeetCode: https://leetcode.com/problems/min-stack/
 *
 * Characteristics:
 * - Generic stack with linked list implementation
 * - Efficiently retrieves both the top element and the minimum element
 * - Space Complexity: O(n) where n is the number of elements in the stack
 */

/**
 * class representing a node in the linked list.
 *
 * @param value The integer value stored in this node
 * @param next Reference to the next node in the stack, or null if this is the top
 */
data class Node(
    val value: Int,
    var next: Node? = null
)

class MinStack {

    var top: Node? = null
    private var minTop: Node? = null  // Stack separada para mínimos


    /**
     * Push a value onto the stack.
     *
     * Time Complexity: O(1) - Constant time, just creates new node and updates pointer
     * Space Complexity: O(1) - Adds one node per call
     */
    fun push(`val`: Int) {
        val newNode = Node(`val`)
        newNode.next = top
        top = newNode

        // Push para minStack: se vazio ou menor que o mínimo atual
        val minValue = if (minTop == null) `val` else minOf(`val`, minTop!!.value)
        val minNode = Node(minValue)
        minNode.next = minTop
        minTop = minNode

    }

    /**
     * Remove and discard the top element from the stack.
     *
     * Time Complexity: O(1) - Constant time pointer update
     * Space Complexity: O(1) - No additional space used
     */
    fun pop() {
        top = top?.next
        minTop = minTop?.next
    }

    /**
     * Get the value at the top of the stack without removing it.
     *
     * Time Complexity: O(1) - Direct access to top node's value
     * Space Complexity: O(1) - No additional space used
     *
     * Returns: The value at the top of the stack, or null if stack is empty
     */
    fun top(): Int? = top?.value

    /**
     * Get the minimum value in the entire stack.
     *
     * Time Complexity: O(1) - Direct access to the min stack's top
     * Space Complexity: O(1) - No additional space used per call
     *
     * Returns: The minimum value in the stack, or 0 if stack is empty
     */
    fun getMin(): Int = minTop?.value ?: 0
}
