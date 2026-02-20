package digital.tonima.algorithms.tree

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class InvertBinaryTreeTest {

    @Test
    fun `test invert binary tree with root 4,2,7,1,3,6,9`() {
        val solution = InvertBinaryTreeSolution()

        // Create tree: [4,2,7,1,3,6,9]
        val root = TreeNode(4)
        root.left = TreeNode(2)
        root.right = TreeNode(7)
        root.left?.left = TreeNode(1)
        root.left?.right = TreeNode(3)
        root.right?.left = TreeNode(6)
        root.right?.right = TreeNode(9)

        val result = solution.invertTree(root)

        assertEquals(4, result?.`val`)
        assertEquals(7, result?.left?.`val`)
        assertEquals(2, result?.right?.`val`)
        assertEquals(9, result?.left?.left?.`val`)
        assertEquals(6, result?.left?.right?.`val`)
        assertEquals(3, result?.right?.left?.`val`)
        assertEquals(1, result?.right?.right?.`val`)
    }

    @Test
    fun `test invert binary tree with root 2,1,3`() {
        val solution = InvertBinaryTreeSolution()

        // Create tree: [2,1,3]
        val root = TreeNode(2)
        root.left = TreeNode(1)
        root.right = TreeNode(3)

        val result = solution.invertTree(root)

        assertEquals(2, result?.`val`)
        assertEquals(3, result?.left?.`val`)
        assertEquals(1, result?.right?.`val`)
    }

    @Test
    fun `test invert binary tree with empty tree`() {
        val solution = InvertBinaryTreeSolution()

        val result = solution.invertTree(null)

        assertNull(result)
    }

    @Test
    fun `test invert binary tree with single node`() {
        val solution = InvertBinaryTreeSolution()

        val root = TreeNode(1)

        val result = solution.invertTree(root)

        assertEquals(1, result?.`val`)
        assertNull(result?.left)
        assertNull(result?.right)
    }

    @Test
    fun `test invert binary tree with only left children`() {
        val solution = InvertBinaryTreeSolution()

        // Create tree: [1,2,null,3]
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.left?.left = TreeNode(3)

        val result = solution.invertTree(root)

        assertEquals(1, result?.`val`)
        assertNull(result?.left)
        assertEquals(2, result?.right?.`val`)
        assertEquals(3, result?.right?.right?.`val`)
    }

    @Test
    fun `test invert binary tree with only right children`() {
        val solution = InvertBinaryTreeSolution()

        // Create tree: [1,null,2,null,3]
        val root = TreeNode(1)
        root.right = TreeNode(2)
        root.right?.right = TreeNode(3)

        val result = solution.invertTree(root)

        assertEquals(1, result?.`val`)
        assertEquals(2, result?.left?.`val`)
        assertEquals(3, result?.left?.left?.`val`)
        assertNull(result?.right)
    }
}

