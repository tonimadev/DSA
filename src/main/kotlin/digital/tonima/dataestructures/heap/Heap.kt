package digital.tonima.dataestructures.heap

/**
 * Generic Heap (Priority Queue) implementation
 * Maintains elements in a binary heap structure where parent nodes have higher priority than children
 *
 * @param T the type of elements in the heap
 * @param elements optional initial elements to heapify
 * @param elementPriority function to extract priority from elements (default: element itself as Comparable)
 *
 * Time Complexity:
 * - Constructor with elements: O(n) via heapify
 * - Constructor empty: O(1)
 */
class Heap<T>(
    elements: MutableList<T>? = null,
    private val elementPriority: (T) -> Comparable<*> = { it as Comparable<*> }
) {
    private var mElements: MutableList<T>

    init {
        // O(n) if elements provided (heapify), O(1) if empty
        if (!elements.isNullOrEmpty()) {
            mElements = elements.toMutableList()
            heapify(mElements)
        } else {
            mElements = mutableListOf()
        }
    }

    /**
     * Inserts an element into the heap maintaining heap property
     * Time Complexity: O(log n) - bubbles up at most the height of the tree
     * Space Complexity: O(1) - only uses a constant amount of extra space
     */
    fun insert(element: T) {
        mElements.add(element) // O(1)
        bubbleUp(mElements.size - 1) // O(log n)
    }

    /**
     * Bubbles up an element from given index to maintain heap property
     * Moves element up the tree while it has higher priority than its parent
     * Time Complexity: O(log n) - worst case travels from leaf to root (height of tree)
     * Space Complexity: O(1) - only uses local variables
     */
    private fun bubbleUp(index: Int) {
        var index = index
        val element = mElements[index]
        while (index > 0) {
            val parentIndex = parentIndex(index) // O(1)
            val parent = mElements[parentIndex]
            if (hasHigherPriority(element, parent)) { // O(1)
                mElements[index] = parent
                index = parentIndex
            } else {
                break
            }
        }
        mElements[index] = element
    }

    /**
     * Converts an arbitrary list into a valid heap in-place
     * Time Complexity: O(n) - more efficient than n insertions
     * Space Complexity: O(1) - in-place operation
     * TODO: Implement bottom-up heapify starting from last non-leaf node
     */
    private fun heapify(elements: MutableList<T>) {
        // TODO: Implement heapify algorithm
        // This will convert an arbitrary list into a valid heap
    }

    /**
     * Checks if element1 has lower priority than element2
     * Time Complexity: O(1) - single comparison operation
     */
    @Suppress("UNCHECKED_CAST")
    private fun hasLowerPriority(element: T, element2: T): Boolean {
        val priority1 = elementPriority(element) as Comparable<Any>
        val priority2 = elementPriority(element2)
        return priority1.compareTo(priority2) < 0
    }

    /**
     * Checks if element1 has higher priority than element2
     * Time Complexity: O(1) - single comparison operation
     */
    @Suppress("UNCHECKED_CAST")
    private fun hasHigherPriority(element: T, element2: T): Boolean {
        val priority1 = elementPriority(element) as Comparable<Any>
        val priority2 = elementPriority(element2)
        return priority1.compareTo(priority2) > 0
    }

    /**
     * Calculates left child index in the heap array
     * Time Complexity: O(1)
     */
    private fun leftChildIndex(index: Int) = index * 2 + 1

    /**
     * Calculates parent index in the heap array
     * Time Complexity: O(1)
     */
    fun parentIndex(index: Int) = (index - 1).floorDiv(2)
}
