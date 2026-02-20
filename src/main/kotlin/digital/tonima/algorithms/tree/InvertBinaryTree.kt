package digital.tonima.algorithms.tree

/**
 * 226. Invert Binary Tree
 * https://leetcode.com/problems/invert-binary-tree/
 *
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Example:
 * Input: root = [4,2,7,1,3,6,9]
 *        4
 *      /   \
 *     2     7
 *    / \   / \
 *   1   3 6   9
 *
 * Output: [4,7,2,9,6,3,1]
 *        4
 *      /   \
 *     7     2
 *    / \   / \
 *   9   6 3   1
 *
 * Time Complexity: O(n) - where n is the number of nodes in the tree
 *                  We visit each node exactly once
 * Space Complexity: O(h) - where h is the height of the tree
 *                   Due to the recursive call stack
 *                   Worst case: O(n) for skewed tree
 *                   Best case: O(log n) for balanced tree
 */

class InvertBinaryTreeSolution {
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null

        // Recursively invert left and right subtrees
        val left = invertTree(root.left)
        val right = invertTree(root.right)

        // Swap the left and right children
        root.left = right
        root.right = left

        return root
    }
}