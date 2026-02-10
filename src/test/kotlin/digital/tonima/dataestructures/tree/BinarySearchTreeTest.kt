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
        val (_, root) = buildSampleTree()

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
        val (_, root) = buildSampleTree()

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
        val (tree, _) = buildSampleTree()

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
    fun `insert returns true on successful insertion`() {
        val tree = BinarySearchTree<Int>()

        assertTrue(tree.insert(10))
        assertNotNull(tree.root)
        assertEquals(10, tree.root?.value)

        assertTrue(tree.insert(5))
        assertEquals(5, tree.root?.left?.value)

        assertTrue(tree.insert(15))
        assertEquals(15, tree.root?.right?.value)
    }

    @Test
    fun `insert returns false on duplicate value`() {
        val tree = BinarySearchTree<Int>()

        assertTrue(tree.insert(10))
        assertFalse(tree.insert(10), "Duplicate insertion should return false")
    }

    @Test
    fun `insert maintains BST property`() {
        val tree = BinarySearchTree<Int>()
        tree.insert(10)
        tree.insert(5)
        tree.insert(15)
        tree.insert(3)
        tree.insert(7)
        tree.insert(12)
        tree.insert(20)

        assertEquals(10, tree.root?.value)
        assertEquals(5, tree.root?.left?.value)
        assertEquals(15, tree.root?.right?.value)
        assertEquals(3, tree.root?.left?.left?.value)
        assertEquals(7, tree.root?.left?.right?.value)
        assertEquals(12, tree.root?.right?.left?.value)
        assertEquals(20, tree.root?.right?.right?.value)
    }

    @Test
    fun `insert rejects duplicate values`() {
        val tree = BinarySearchTree<Int>()

        assertTrue(tree.insert(10))
        assertFalse(tree.insert(10))

        assertTrue(tree.insert(5))
        assertFalse(tree.insert(5))

        assertTrue(tree.insert(15))
        assertFalse(tree.insert(15))
    }

    @Test
    fun `contains returns true for existing values`() {
        val (tree, _) = buildSampleTree()

        assertTrue(tree.contains(10), "Root should be found")
        assertTrue(tree.contains(5), "Left child should be found")
        assertTrue(tree.contains(15), "Right child should be found")
        assertTrue(tree.contains(2), "Deep left child should be found")
        assertTrue(tree.contains(7), "Deep right child should be found")
        assertTrue(tree.contains(12), "Right subtree left child should be found")
        assertTrue(tree.contains(20), "Right subtree right child should be found")
    }

    @Test
    fun `contains returns false for non-existing values`() {
        val (tree, _) = buildSampleTree()

        assertFalse(tree.contains(1), "Value less than minimum should not be found")
        assertFalse(tree.contains(21), "Value greater than maximum should not be found")
        assertFalse(tree.contains(6), "Value between existing nodes should not be found")
        assertFalse(tree.contains(100), "Random non-existing value should not be found")
    }

    @Test
    fun `contains returns false for empty tree`() {
        val tree = BinarySearchTree<Int>()

        assertFalse(tree.contains(10), "Empty tree should not contain any value")
    }

    @Test
    fun `contains works correctly after insertions`() {
        val tree = BinarySearchTree<Int>()

        assertFalse(tree.contains(5))

        tree.insert(10)
        assertTrue(tree.contains(10))
        assertFalse(tree.contains(5))

        tree.insert(5)
        assertTrue(tree.contains(5))
        assertTrue(tree.contains(10))

        tree.insert(15)
        tree.insert(3)
        tree.insert(7)
        assertTrue(tree.contains(3))
        assertTrue(tree.contains(7))
        assertTrue(tree.contains(15))
        assertFalse(tree.contains(20))
    }

    @Test
    fun `contains works with single node tree`() {
        val tree = BinarySearchTree<Int>()
        tree.insert(42)

        assertTrue(tree.contains(42))
        assertFalse(tree.contains(41))
        assertFalse(tree.contains(43))
    }

    @Test
    fun `contains works with string values`() {
        val tree = BinarySearchTree<String>()
        tree.insert("apple")
        tree.insert("banana")
        tree.insert("cherry")

        assertTrue(tree.contains("apple"))
        assertTrue(tree.contains("banana"))
        assertTrue(tree.contains("cherry"))
        assertFalse(tree.contains("apricot"))
        assertFalse(tree.contains("zebra"))
    }

    @Test
    fun `delete removes leaf node correctly`() {
        val (tree, _) = buildSampleTree()

        tree.delete(2)  // Leaf node
        assertNull(tree.root?.left?.left)
        assertEquals(5, tree.root?.left?.value)

        tree.delete(7)  // Another leaf node
        assertNull(tree.root?.left?.right)
        assertEquals(5, tree.root?.left?.value)
    }

    @Test
    fun `delete removes node with one child correctly`() {
        val tree = BinarySearchTree<Int>()
        tree.insert(10)
        tree.insert(5)
        tree.insert(3)

        tree.delete(5)  // Has only left child
        assertEquals(3, tree.root?.left?.value)
        assertNull(tree.root?.left?.left)
        assertNull(tree.root?.left?.right)

        // Clean tree for next test
        val tree2 = BinarySearchTree<Int>()
        tree2.insert(10)
        tree2.insert(15)
        tree2.insert(20)

        tree2.delete(15)  // Has only right child
        assertEquals(20, tree2.root?.right?.value)
        assertNull(tree2.root?.right?.left)
        assertNull(tree2.root?.right?.right)
    }

    @Test
    fun `delete removes node with two children correctly`() {
        val (tree, _) = buildSampleTree()

        tree.delete(5)  // Has two children: left=2, right=7
        assertNotNull(tree.root?.left)
        assertEquals(2, tree.root?.left?.value)  // Replaced by max(left subtree) = 2
        assertEquals(7, tree.root?.left?.right?.value)
        assertNull(tree.root?.left?.left)
    }

    @Test
    fun `delete removes root node correctly`() {
        val (tree, _) = buildSampleTree()

        tree.delete(10)  // Root with two children
        assertNotNull(tree.root)
        assertEquals(7, tree.root?.value)  // Replaced by max(left subtree)
        assertEquals(5, tree.root?.left?.value)
        assertEquals(2, tree.root?.left?.left?.value)
        assertNull(tree.root?.left?.right)
        assertEquals(15, tree.root?.right?.value)
    }

    @Test
    fun `delete on empty tree does nothing`() {
        val tree = BinarySearchTree<Int>()
        assertNull(tree.root)

        tree.delete(5)
        assertNull(tree.root)
    }

    @Test
    fun `delete non-existent value does nothing`() {
        val (tree, _) = buildSampleTree()

        tree.delete(999)
        assertEquals(10, tree.root?.value)
        assertEquals(5, tree.root?.left?.value)
        assertEquals(15, tree.root?.right?.value)
    }

    @Test
    fun `delete maintains BST property after deletion`() {
        val (tree, _) = buildSampleTree()

        tree.delete(15)  // Node with two children

        assertEquals(10, tree.root?.value)
        assertEquals(5, tree.root?.left?.value)
        assertEquals(12, tree.root?.right?.value)  // 15 replaced by 12 (max of left subtree)
        assertEquals(20, tree.root?.right?.right?.value)
    }
}
