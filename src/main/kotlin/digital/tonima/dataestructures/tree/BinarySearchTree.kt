package digital.tonima.dataestructures.tree

class BinarySearchTree<T : Comparable<T>>(
    var root: Node<T>? = null
) {
    data class Node<T : Comparable<T>>(
        val value: T?,
        var left: Node<T>? = null,
        var right: Node<T>? = null,
    ) {
        fun findMaxInTree(): Pair<Node<T>?, Node<T>?> {
            var parent: Node<T>? = null
            var node: Node<T>? = this

            while (node?.right != null) {
                parent = node
                node = node.right
            }
            return node to parent

        }

        fun findMinInTree(): Pair<Node<T>?, Node<T>?> {
            var parent: Node<T>? = null
            var node: Node<T>? = this

            while (node?.left != null) {
                parent = node
                node = node.left
            }

            return node to parent
        }
    }

    /**
     * Returns a new BinarySearchTree with the value inserted.
     * Does not modify the original tree (immutable operation).
     * Time Complexity: O(h) where h is the height of the tree
     */
    fun insert(value: T): BinarySearchTree<T> {
        return BinarySearchTree(insertNode(root, value))
    }

    private fun insertNode(node: Node<T>?, value: T): Node<T> {
        if (node == null) {
            return Node(value)
        }

        return if (node.value?.let { value <= it } == true) {
            // Create new node with updated left subtree
            node.copy(left = insertNode(node.left, value))
        } else {
            // Create new node with updated right subtree
            node.copy(right = insertNode(node.right, value))
        }
    }

    /**
     * Deletes a node with the given value from the BST.
     * Handles three cases:
     * 1. Node is a leaf (no children) - simply remove it
     * 2. Node has one child - replace with that child
     * 3. Node has two children - replace with inorder successor (max from left subtree)
     *
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1)
     */
    fun delete(value: T) {
        if (root == null) return

        val (node, parent) = search(value)
        if (node == null) return

        when {
            node.isLeafOrHasOneChild() -> deleteSimpleNode(node, parent, value)
            else -> deleteNodeWithTwoChildren(node, parent, value)
        }
    }

    /**
     * Helper: Check if node has 0 or 1 child
     */
    private fun Node<T>.isLeafOrHasOneChild(): Boolean =
        left == null || right == null

    /**
     * Helper: Delete node with 0 or 1 child
     */
    private fun deleteSimpleNode(node: Node<T>, parent: Node<T>?, value: T) {
        val child = node.left ?: node.right

        when {
            parent == null -> root = child  // Deleting root
            value <= parent.value!! -> parent.left = child
            else -> parent.right = child
        }
    }

    /**
     * Helper: Delete node with 2 children
     * Strategy: Replace with inorder predecessor (max from left subtree)
     */
    private fun deleteNodeWithTwoChildren(node: Node<T>, parent: Node<T>?, value: T) {
        val (maxNode, maxNodeParent) = node.left!!.findMaxInTree()

        val replacementNode = Node(
            maxNode?.value,
            node.left,
            node.right
        )

        // Adjust left pointer if maxNode was direct left child
        if (maxNodeParent == null) {
            replacementNode.left = maxNode?.left
        } else {
            maxNodeParent.right = maxNode?.left
        }

        // Link replacement node to original parent
        linkNodeToParent(replacementNode, parent, value)
    }

    /**
     * Helper: Link a node to its parent
     */
    private fun linkNodeToParent(node: Node<T>, parent: Node<T>?, value: T) {
        when {
            parent == null -> root = node
            value <= parent.value!! -> parent.left = node
            else -> parent.right = node
        }
    }

    fun search(value: T): Pair<Node<T>?, Node<T>?> {
        var parent: Node<T>? = null
        var node = root
        while (node != null) {
            val nodeVal = node.value ?: return null to null  // Invalid node
            if (nodeVal == value) {
                return node to parent
            } else if (value < nodeVal) {
                parent = node
                node = node.left
            } else {
                parent = node
                node = node.right
            }
        }
        return null to null
    }


}
