package digital.tonima.algorithms.tree

/**
 * LeetCode #104 - Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * Complexity:
 * - Time: O(n) - where n is the number of nodes in the tree (visit each node once).
 * - Space: O(h) - where h is the height of the tree due to recursion stack.
 *   - Worst case: O(n) for a skewed tree.
 *   - Best case: O(log n) for a balanced tree.
 */
class MaxDepthSolution {
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return maxOf(maxDepth(root.left), maxDepth(root.right)) + 1
    }
}
