package digital.tonima.algorithms.tree

/**
 * LeetCode #543 - Diameter of Binary Tree
 * https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * Complexity:
 * - Time: O(n) - where n is the number of nodes in the tree (visit each node once).
 * - Space: O(h) - where h is the height of the tree due to recursion stack.
 *   - Worst case: O(n) for a skewed tree.
 *   - Best case: O(log n) for a balanced tree.
 */
class DiameterOfBinaryTreeSolution {
    private var maxDiameter = 0
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        maxDiameter = 0
        calculateHeight(root)
        return maxDiameter
    }
    private fun calculateHeight(node: TreeNode?): Int {
        if (node == null) return 0
        val leftHeight = calculateHeight(node.left)
        val rightHeight = calculateHeight(node.right)

        // The diameter at a specific node is the sum of the heights of its left and right subtrees
        maxDiameter = maxOf(maxDiameter, leftHeight + rightHeight)

        // Returns the height of this node (1 + maximum between left and right height)
        return 1 + maxOf(leftHeight, rightHeight)
    }
}
