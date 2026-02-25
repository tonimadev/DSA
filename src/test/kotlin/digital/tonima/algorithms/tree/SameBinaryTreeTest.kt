package digital.tonima.algorithms.tree

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertEquals

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class SameBinaryTreeTest {
    private lateinit var solution: SameBinarySolution

    @BeforeEach
    fun setUp() {
        solution = SameBinarySolution()
    }

    @Test
    fun testBothTreesNull() {
        // Both trees are null - should return true
        val result = solution.isSameTree(null, null)
        assertEquals(true, result)
    }

    @Test
    fun testFirstTreeNullSecondTreeNotNull() {
        // First tree is null, second tree is not - should return false
        val q = TreeNode(1)
        val result = solution.isSameTree(null, q)
        assertEquals(false, result)
    }

    @Test
    fun testFirstTreeNotNullSecondTreeNull() {
        // First tree is not null, second tree is null - should return false
        val p = TreeNode(1)
        val result = solution.isSameTree(p, null)
        assertEquals(false, result)
    }

    @Test
    fun testIdenticalSingleNodeTrees() {
        // Both trees have a single node with same value - should return true
        val p = TreeNode(1)
        val q = TreeNode(1)
        val result = solution.isSameTree(p, q)
        assertEquals(true, result)
    }

    @Test
    fun testDifferentSingleNodeTrees() {
        // Both trees have a single node but different values - should return false
        val p = TreeNode(1)
        val q = TreeNode(2)
        val result = solution.isSameTree(p, q)
        assertEquals(false, result)
    }

    @Test
    fun testIdenticalBinaryTrees() {
        // Two identical binary trees
        val p = TreeNode(1)
        p.left = TreeNode(2)
        p.right = TreeNode(3)

        val q = TreeNode(1)
        q.left = TreeNode(2)
        q.right = TreeNode(3)

        val result = solution.isSameTree(p, q)
        assertEquals(true, result)
    }

    @Test
    fun testDifferentStructureLeftChild() {
        // Trees have different structure - first has left child, second doesn't
        val p = TreeNode(1)
        p.left = TreeNode(2)

        val q = TreeNode(1)

        val result = solution.isSameTree(p, q)
        assertEquals(false, result)
    }

    @Test
    fun testDifferentStructureRightChild() {
        // Trees have different structure - first has right child, second doesn't
        val p = TreeNode(1)
        p.right = TreeNode(2)

        val q = TreeNode(1)

        val result = solution.isSameTree(p, q)
        assertEquals(false, result)
    }

    @Test
    fun testDifferentValuesSameStructure() {
        // Trees have same structure but different values in child nodes
        val p = TreeNode(1)
        p.left = TreeNode(2)
        p.right = TreeNode(3)

        val q = TreeNode(1)
        q.left = TreeNode(2)
        q.right = TreeNode(4)

        val result = solution.isSameTree(p, q)
        assertEquals(false, result)
    }

    @Test
    fun testComplexIdenticalTrees() {
        // More complex identical trees with multiple levels
        val p = TreeNode(1)
        p.left = TreeNode(2)
        p.right = TreeNode(1)
        p.left!!.left = TreeNode(3)
        p.left!!.right = TreeNode(4)

        val q = TreeNode(1)
        q.left = TreeNode(2)
        q.right = TreeNode(1)
        q.left!!.left = TreeNode(3)
        q.left!!.right = TreeNode(4)

        val result = solution.isSameTree(p, q)
        assertEquals(true, result)
    }

    @Test
    fun testComplexDifferentTrees() {
        // Complex trees that differ at a deeper level
        val p = TreeNode(1)
        p.left = TreeNode(2)
        p.right = TreeNode(1)
        p.left!!.left = TreeNode(3)
        p.left!!.right = TreeNode(4)

        val q = TreeNode(1)
        q.left = TreeNode(2)
        q.right = TreeNode(1)
        q.left!!.left = TreeNode(3)
        q.left!!.right = TreeNode(5)

        val result = solution.isSameTree(p, q)
        assertEquals(false, result)
    }

    @Test
    fun testOneNodeWithBothChildrenVsOneWithoutRight() {
        // Node with both children vs node with only left child
        val p = TreeNode(1)
        p.left = TreeNode(2)
        p.right = TreeNode(3)

        val q = TreeNode(1)
        q.left = TreeNode(2)

        val result = solution.isSameTree(p, q)
        assertEquals(false, result)
    }

    @Test
    fun testLeftRightStructureDifference() {
        // Trees have same values but different structure (left vs right)
        val p = TreeNode(1)
        p.left = TreeNode(2)

        val q = TreeNode(1)
        q.right = TreeNode(2)

        val result = solution.isSameTree(p, q)
        assertEquals(false, result)
    }
}

