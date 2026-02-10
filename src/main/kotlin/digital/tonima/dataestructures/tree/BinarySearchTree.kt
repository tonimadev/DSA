package digital.tonima.dataestructures.tree

class BinarySearchTree<T : Comparable<T>>(
    var root: Node<T>? = null
) {
    data class Node<T : Comparable<T>>(
        val value: T,
        var left: Node<T>? = null,
        var right: Node<T>? = null,
    ) {
        private fun findExtreme(selector: (Node<T>) -> Node<T>?): Pair<Node<T>?, Node<T>?> {
            var parent: Node<T>? = null
            var node: Node<T>? = this

            while (selector(node!!) != null) {
                parent = node
                node = selector(node)
            }
            return node to parent
        }

        fun findMaxInTree(): Pair<Node<T>?, Node<T>?> = findExtreme { it.right }

        fun findMinInTree(): Pair<Node<T>?, Node<T>?> = findExtreme { it.left }
    }

    fun insert(value: T): Boolean {
        if (root == null) {
            root = Node(value)
            return true
        }

        var temp = root
        while (temp != null) {
            if (value == temp.value) return false

            if (value < temp.value) {
                if (temp.left == null) {
                    temp.left = Node(value)
                    return true
                }
                temp = temp.left
            } else {
                if (temp.right == null) {
                    temp.right = Node(value)
                    return true
                }
                temp = temp.right
            }
        }
        return false
    }

    fun contains(value: T): Boolean {
        if (root == null) return false
        var temp = root

        while (temp != null) {
            if (temp.value == value) return true
            temp = if (value > temp.value) temp.right else temp.left
        }
        return false
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
            value <= parent.value -> parent.left = child
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
            maxNode!!.value,
            node.left,
            node.right
        )

        if (maxNodeParent == null) {
            replacementNode.left = maxNode.left
        } else {
            maxNodeParent.right = maxNode.left
        }

        linkNodeToParent(replacementNode, parent, value)
    }

    /**
     * Helper: Link a node to its parent
     */
    private fun linkNodeToParent(node: Node<T>, parent: Node<T>?, value: T) {
        when {
            parent == null -> root = node
            value <= parent.value -> parent.left = node
            else -> parent.right = node
        }
    }

    fun search(value: T): Pair<Node<T>?, Node<T>?> {
        var parent: Node<T>? = null
        var node = root
        while (node != null) {
            if (node.value == value) {
                return node to parent
            } else if (value < node.value) {
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
