package digital.tonima.dataestructures.tree

class BinarySearchTree<T : Comparable<T>>(
    val root: Node<T>? = null
) {
    /**
     * Immutable Node following Data-Oriented Programming principles.
     * All properties are immutable (val) and transformations return new nodes.
     */
    data class Node<T : Comparable<T>>(
        val value: T,
        val left: Node<T>? = null,
        val right: Node<T>? = null,
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

        return if (value <= node.value) {
            // Create new node with updated left subtree
            node.copy(left = insertNode(node.left, value))
        } else {
            // Create new node with updated right subtree
            node.copy(right = insertNode(node.right, value))
        }
    }

    fun search(value: T): Pair<Node<T>?, Node<T>?> {
        var parent: Node<T>? = null
        var node = root
        while (node != null) {
            val nodeVal = node.value
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
