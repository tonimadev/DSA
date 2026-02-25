package digital.tonima.algorithms.tree

class SameBinarySolution {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        return if (p?.`val` != q?.`val`) {
            false
        } else {
            isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
        }
    }
}
