package digital.tonima.dataestructures.tree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BinarySearchTreeTest {

    private fun buildSampleTree(): Pair<BinarySearchTree<Int>, BinarySearchTree.Node<Int>> {
        val leftLeft = BinarySearchTree.Node(2)
        val leftRight = BinarySearchTree.Node(7)
        val left = BinarySearchTree.Node(5, leftLeft, leftRight)
        val rightLeft = BinarySearchTree.Node(12)
        val rightRight = BinarySearchTree.Node(20)
        val right = BinarySearchTree.Node(15, rightLeft, rightRight)
        val root = BinarySearchTree.Node(10, left, right)
        return BinarySearchTree(root = root) to root
    }

    @Test
    fun `findMinInTree returns leftmost node and its parent`() {
        val (tree, root) = buildSampleTree()

        val (node, parent) = root.findMinInTree()
        assertNotNull(node)
        assertEquals(2, node?.value)
        assertNotNull(parent)
        assertEquals(5, parent?.value)

        val (rightMin, rightMinParent) = root.right!!.findMinInTree()
        assertEquals(12, rightMin?.value)
        assertEquals(15, rightMinParent?.value)

        val (leafMin, leafMinParent) = root.left!!.left!!.findMinInTree()
        assertEquals(2, leafMin?.value)
        assertNull(leafMinParent)
    }

    @Test
    fun `findMaxInTree returns rightmost node and its parent`() {
        val (tree, root) = buildSampleTree()

        val (node, parent) = root.findMaxInTree()
        assertNotNull(node)
        assertEquals(20, node?.value)
        assertNotNull(parent)
        assertEquals(15, parent?.value)

        val (leftMax, leftMaxParent) = root.left!!.findMaxInTree()
        assertEquals(7, leftMax?.value)
        assertEquals(5, leftMaxParent?.value)

        val (leafMax, leafMaxParent) = root.left!!.left!!.findMaxInTree()
        assertEquals(2, leafMax?.value)
        assertNull(leafMaxParent)
    }

    @Test
    fun `search finds node and parent or returns nulls`() {
        val (tree, root) = buildSampleTree()

        val (foundRoot, rootParent) = tree.search(10)
        assertEquals(10, foundRoot?.value)
        assertNull(rootParent)

        val (foundLeft, leftParent) = tree.search(5)
        assertEquals(5, foundLeft?.value)
        assertEquals(10, leftParent?.value)

        val (foundRightLeft, rightLeftParent) = tree.search(12)
        assertEquals(12, foundRightLeft?.value)
        assertEquals(15, rightLeftParent?.value)

        val (missing, missingParent) = tree.search(999)
        assertNull(missing)
        assertNull(missingParent)
    }

    @Test
    fun `insert returns new tree without modifying original`() {
        val emptyTree = BinarySearchTree<Int>()
        assertNull(emptyTree.root)

        val tree1 = emptyTree.insert(10)
        assertNull(emptyTree.root, "Original tree should remain unchanged")
        assertNotNull(tree1.root)
        assertEquals(10, tree1.root?.value)

        val tree2 = tree1.insert(5)
        assertEquals(10, tree1.root?.value, "tree1 should remain unchanged")
        assertNull(tree1.root?.left, "tree1 should remain unchanged")
        assertEquals(10, tree2.root?.value)
        assertEquals(5, tree2.root?.left?.value)

        val tree3 = tree2.insert(15)
        assertNull(tree2.root?.right, "tree2 should remain unchanged")
        assertEquals(15, tree3.root?.right?.value)
    }

    @Test
    fun `insert maintains BST property`() {
        val tree = BinarySearchTree<Int>()
            .insert(10)
            .insert(5)
            .insert(15)
            .insert(3)
            .insert(7)
            .insert(12)
            .insert(20)

        assertEquals(10, tree.root?.value)
        assertEquals(5, tree.root?.left?.value)
        assertEquals(15, tree.root?.right?.value)
        assertEquals(3, tree.root?.left?.left?.value)
        assertEquals(7, tree.root?.left?.right?.value)
        assertEquals(12, tree.root?.right?.left?.value)
        assertEquals(20, tree.root?.right?.right?.value)
    }

    @Test
    fun `insert handles duplicates by placing in left subtree`() {
        val tree = BinarySearchTree<Int>()
            .insert(10)
            .insert(10)
            .insert(10)

        assertEquals(10, tree.root?.value)
        assertEquals(10, tree.root?.left?.value)
        assertEquals(10, tree.root?.left?.left?.value)
    }
}
