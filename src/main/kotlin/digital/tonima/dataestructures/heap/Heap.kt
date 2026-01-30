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
    private val mElements: ArrayList<T>

    init {
        // O(n) if elements provided (heapify), O(1) if empty
        if (!elements.isNullOrEmpty()) {
            mElements = ArrayList(elements)
            heapify(mElements)
        } else {
            mElements = ArrayList()
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
     * Bubbles down an element from given index to maintain heap property
     * Moves element down the tree while it has lower priority than its children
     * Time Complexity: O(log n) - worst case travels from root to leaf (height of tree)
     * Space Complexity: O(1) - only uses local variables
     */
    private fun bubbleDown(index: Int) {
        var currentIndex = index
        val element = mElements[currentIndex]

        while (true) {
            val leftIndex = leftChildIndex(currentIndex) // O(1)
            val rightIndex = rightChildIndex(currentIndex) // O(1)
            var highestPriorityIndex = currentIndex

            // Check if left child has higher priority than current element
            if (leftIndex < size() &&
                hasHigherPriority(mElements[leftIndex], element)
            ) {
                highestPriorityIndex = leftIndex
            }

            // Check if right child has higher priority than the highest so far
            if (rightIndex < size() &&
                hasHigherPriority(
                    mElements[rightIndex],
                    if (highestPriorityIndex == currentIndex) element else mElements[highestPriorityIndex]
                )
            ) {
                highestPriorityIndex = rightIndex
            }

            // If current element is already in correct position, stop
            if (highestPriorityIndex == currentIndex) {
                break
            }

            // Move highest priority child up
            mElements[currentIndex] = mElements[highestPriorityIndex]
            currentIndex = highestPriorityIndex
        }

        mElements[currentIndex] = element
    }

    /**
     * Removes and returns the highest priority element (root of heap)
     * Time Complexity: O(log n) - bubbles down at most the height of the tree
     * Space Complexity: O(1) - only uses a constant amount of extra space
     */
    fun top(): T? {
        if (isEmpty()) return null

        if (size() == 1) {
            return mElements.removeLast() // O(1) - equivalent to pop()
        }

        val top = mElements[0] // Save the root element
        val last = mElements.removeLast() // Remove last element (equivalent to pop())
        mElements[0] = last // Move last element to root
        bubbleDown(0) // Restore heap property

        return top
    }

    /**
     * Returns the highest priority element without removing it
     * Time Complexity: O(1)
     */
    fun peek(): T? {
        return if (isEmpty()) null else mElements[0]
    }

    /**
     * Returns the number of elements in the heap
     * Time Complexity: O(1)
     */
    fun size() = mElements.size

    /**
     * Checks if the heap is empty
     * Time Complexity: O(1)
     */
    fun isEmpty() = mElements.isEmpty()

    /**
     * Converts an arbitrary list into a valid heap in-place
     * Time Complexity: O(n) - more efficient than n insertions
     * Space Complexity: O(1) - in-place operation
     * Starts from last non-leaf node and bubbles down each element
     */
    private fun heapify(elements: ArrayList<T>) {
        if (size()<= 1) return

        // Start from last non-leaf node and work backwards to root
        // Last non-leaf node is at index (size/2 - 1)
        val startIndex = (elements.size / 2) - 1
        for (i in startIndex downTo 0) {
            bubbleDown(i)
        }
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
     * Calculates right child index in the heap array
     * Time Complexity: O(1)
     */
    private fun rightChildIndex(index: Int) = index * 2 + 2

    /**
     * Calculates parent index in the heap array
     * Time Complexity: O(1)
     */
    fun parentIndex(index: Int) = (index - 1).floorDiv(2)

    private fun highestPriorityChildIndex(index: Int): Int? {
        val firstIndex = leftChildIndex(index)

        if (firstIndex >= size()) {
            return null
        }
        if (firstIndex + 1 >= size()) {
            return firstIndex
        }
        if (hasHigherPriority(mElements[firstIndex], mElements[firstIndex + 1])) {
            return firstIndex
        } else {
            return firstIndex + 1
        }
    }
}
