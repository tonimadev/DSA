# DSA - Data Structures and Algorithms

Kotlin implementations of classic data structures and algorithms with unit tests and complexity analysis.

## About

This is a learning project covering fundamental computer science concepts. Each implementation includes time/space complexity analysis and comprehensive test coverage.

## Project Structure

### Data Structures

#### Arrays

Three array implementations optimized for different use cases:

##### SortedArray
Fixed-size array that maintains sorted order on insertion. Generic implementation with `Comparable<T>` constraint.

Operations:
- `insert(value)` - O(n) - Inserts value maintaining sorted order (requires shifting elements)
- `delete(value)` - O(n) - Removes value using binary search + shifting
- `deleteByIndex(index)` - O(n) - Removes element at index (requires shifting)
- `binarySearch(value)` - O(log n) - Efficient search on sorted array
- `get(index)` - O(1) - Direct access by index

Characteristics:
- Fixed capacity set at initialization
- Always maintains sorted order
- Optimized for search operations (binary search)
- Slower insertions due to maintaining order
- Space Complexity: O(n)

Use Cases:
- When data must be kept sorted at all times
- Frequent search operations with fewer insertions
- Small to medium datasets with known maximum size
- Priority-based systems requiring ordered access

Example:
```kotlin
val sortedArray = SortedArray<Int>(10)
sortedArray.insert(5)
sortedArray.insert(2)
sortedArray.insert(8)
println(sortedArray) // Output: [2, 5, 8]
sortedArray.binarySearch(5) // Returns index 1
```

##### UnsortedArray
Fixed-size array with fast insertions and removals. Generic implementation supporting any data type.

Operations:
- `insert(value)` - O(1) - Appends to end of array
- `remove(index)` - O(1) - Removes by replacing with last element (doesn't maintain order)
- `get(target)` - O(n) - Linear search for element
- `traverse(operation)` - O(n) - Applies operation to all elements

Characteristics:
- Fixed capacity set at initialization
- No ordering guarantees
- Removal replaces deleted element with last element (very fast)
- Simple and efficient for basic operations
- Space Complexity: O(n)

Use Cases:
- When order doesn't matter
- Frequent insertions and deletions needed
- Simple collection without search requirements
- Temporary storage or buffers

Example:
```kotlin
val unsortedArray = UnsortedArray<Int>(10)
unsortedArray.insert(10)
unsortedArray.insert(20)
unsortedArray.insert(30)
unsortedArray.remove(1) // Replaces 20 with 30, size becomes 2
println(unsortedArray) // Output:  10 30
```

##### UnsortedDynamicArray
Self-resizing array that grows and shrinks automatically. Generic implementation supporting any data type.

Operations:
- `insert(value)` - O(1) amortized - Appends to end, doubles capacity when full
- `remove(target)` - O(n) - Finds and removes element, halves capacity when size ≤ capacity/4
- `find(target)` - O(n) - Linear search for element

Resizing Strategy:
- Doubling: When `size == capacity`, doubles capacity to `capacity * 2`
- Halving: When `size ≤ capacity / 4`, halves capacity to `capacity / 2`
- Amortized O(1) insertions: While individual resize operations cost O(n), they happen infrequently enough that average cost per insertion is constant
- Space efficiency: Shrinks to avoid wasting memory with sparse arrays

Complexity Analysis:
- `insert(value)` - O(1) amortized, O(n) worst case when resizing
- `remove(target)` - O(n) for search + shift, O(n) for potential resize
- `find(target)` - O(n) linear search
- Space Complexity: O(n), with capacity bounded by 2n

Characteristics:
- Automatic capacity management
- No fixed size limit
- Memory efficient with dynamic shrinking
- Maintains insertion order
- Prevents overflow errors

Use Cases:
- When size is unknown or highly variable
- Need array performance without capacity planning
- Long-lived collections that grow and shrink
- General-purpose dynamic collections

Example:
```kotlin
val dynamicArray = UnsortedDynamicArray<Int>(initialCapacity = 2)
dynamicArray.insert(10)
dynamicArray.insert(20)
// capacity = 2, size = 2

dynamicArray.insert(30) // Triggers doubling
// capacity = 4, size = 3

dynamicArray.remove(10)
dynamicArray.remove(20) // Triggers halving when size ≤ capacity/4
// capacity = 2, size = 1
```

Performance Comparison:

| Operation | SortedArray | UnsortedArray | UnsortedDynamicArray |
|-----------|-------------|---------------|----------------------|
| Insert | O(n) | O(1) | O(1) amortized |
| Remove | O(n) | O(1) | O(n) |
| Search | O(log n) | O(n) | O(n) |
| Access by index | O(1) | O(1) | N/A |
| Space | O(n) fixed | O(n) fixed | O(n) dynamic |
| Resizing | No | No | Yes |

#### Linked List
Generic Linked List implementation supporting any data type. Operations include append, prepend, insert, remove, and search with optimized complexity.

#### Doubly Linked List
Generic Doubly Linked List with bidirectional traversal.

Operations:
- `append(value)` - O(1) - Add element at the end
- `prepend(value)` - O(1) - Add element at the beginning
- `insert(index, value)` - O(n) - Insert at specific position (O(1) for index 0)
- `remove(index)` - O(n) - Remove at specific position (O(1) for first/last)
- `removeFirst()` - O(1) - Remove first element
- `removeLast()` - O(1) - Remove last element
- `get(index)` - O(n/2) - Access element (optimized to traverse from closest end)
- `set(index, value)` - O(n) - Update element value

Optimizations:
  - Bidirectional traversal (previous/next pointers)
  - `get()` traverses from closest end (head or tail)
  - Direct references to head and tail for O(1) operations
  - Specialized methods for edge cases

#### Queue
Generic Queue implementation (FIFO - First In, First Out).

Operations:
- `enqueue(value)` - O(1) - Add element to the end
- `dequeue()` - O(1) - Remove and return the first element

Characteristics:
- FIFO order
- Implemented with linked list for efficient O(1) operations
- Supports generic data types

#### Stack
Generic Stack implementation (LIFO - Last In, First Out).

Operations:
- `push(value)` - O(1) - Add element to the top
- `pop()` - O(1) - Remove and return the top element

Characteristics:
- LIFO order
- Efficiently implemented with a top pointer
- Supports generic data types

#### Heap (Priority Queue)
Generic binary heap implementation maintaining priority order. Implemented as a complete binary tree using array representation with customizable priority function.

Operations:
- `insert(element)` - O(log n) - Add element and maintain heap property via bubble-up
- `top()` - O(log n) - Remove and return highest priority element (root), bubble-down to restore heap property
- `peek()` - O(1) - View highest priority element without removing it
- `size()` - O(1) - Return number of elements in the heap
- `isEmpty()` - O(1) - Check if heap contains no elements
- `kLargestElements(k)` - O(k log n) - Get k largest elements in descending order (non-destructive)
- `allElementsDescending()` - O(n log n) - Get all elements in descending order (non-destructive)
- `heapify(elements)` - O(n) - Convert arbitrary list into valid heap (more efficient than n insertions)

Helper Methods:
- `hasHigherPriority(e1, e2)` - O(1) - Compare priorities of two elements
- `hasLowerPriority(e1, e2)` - O(1) - Compare priorities of two elements
- `parentIndex(i)` - O(1) - Calculate parent index: `(i - 1) / 2`
- `leftChildIndex(i)` - O(1) - Calculate left child index: `2 * i + 1`
- `rightChildIndex(i)` - O(1) - Calculate right child index: `2 * i + 2`

Characteristics:
- Heap Property: Parent nodes always have higher priority than their children
- Complete Binary Tree: All levels filled except possibly the last, which fills left-to-right
- Array Representation: Efficient memory usage, no need for node objects
  - Parent of index `i` is at `(i - 1) / 2`
  - Left child of index `i` is at `2 * i + 1`
  - Right child of index `i` is at `2 * i + 2`
- Flexible Priority: Custom `elementPriority` function allows min-heap, max-heap, or complex priorities
- Space Complexity: O(n) for storing elements

Complexity Analysis:
- Constructor: 
  - Empty: O(1)
  - With elements: O(n) via heapify (better than O(n log n) for n insertions)
- Insert: O(log n) - bubble up at most the height of the tree
- Top (Remove): O(log n) - remove root and bubble down to restore heap property
- Peek: O(1) - just returns the root element
- Size/isEmpty: O(1) - simple property access
- kLargestElements(k): O(k log n) - removes k elements, each removal is O(log n), then restores original heap
- allElementsDescending(): O(n log n) - removes all n elements, each removal is O(log n), then restores original heap
- BubbleUp/BubbleDown: O(log n) - traverse at most the height of the tree (log n levels)
- Heapify: O(n) - bottom-up approach more efficient than n insertions
- Space: O(n) - stores all elements in array

Use Cases:
- Priority queues (task scheduling, event simulation)
- Dijkstra's shortest path algorithm
- Heap sort algorithm
- Finding k largest/smallest elements
- Median maintenance in streaming data
- Job scheduling systems
- A* pathfinding algorithm

Example:
```kotlin
// Max heap (default - higher values have higher priority)
val maxHeap = Heap<Int>()
maxHeap.insert(5)
maxHeap.insert(10)
maxHeap.insert(3)
maxHeap.insert(8)
// Internal structure maintains: 10 at root (highest priority)

// Min heap (custom priority - negate values)
val minHeap = Heap<Int>(elementPriority = { -it })
minHeap.insert(5)
minHeap.insert(10)
minHeap.insert(3)
minHeap.insert(8)
// Internal structure maintains: 3 at root (highest priority due to negation)

// Custom priority (e.g., task scheduling by deadline)
data class Task(val name: String, val deadline: Int)
val taskHeap = Heap<Task>(elementPriority = { it.deadline })
taskHeap.insert(Task("Task A", 5))
taskHeap.insert(Task("Task B", 2))
taskHeap.insert(Task("Task C", 8))
// Task B has highest priority (earliest deadline)
```

### Algorithms

#### Arrays

##### [Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

Finds two numbers in a sorted array that sum to a target using a two-pointer approach.

**Complexity:**
- Time: O(n) - Single pass with left/right pointers converging
- Space: O(1) - Constant extra space

**Algorithm:**
1. Initialize two pointers: `left` at start (0) and `right` at end (n-1)
2. While `left < right`:
   - Calculate `sum = numbers[left] + numbers[right]`
   - If `sum == target`: return indices (1-based)
   - If `sum < target`: increment `left` (need larger sum)
   - If `sum > target`: decrement `right` (need smaller sum)

**Example:**
```kotlin
val numbers = intArrayOf(2, 7, 11, 15)
val solution = TwoSumIISolution()
val result = solution.twoSum(numbers, 9)
// result is [1, 2] (indices are 1-based)
```

##### [Three Sum](https://leetcode.com/problems/3sum/)

Finds all unique triplets in an array that sum to zero.

**Complexity:**
- Time: O(n²) - Sorting O(n log n) + nested loop with two pointers O(n²)
- Space: O(1) - Excluding the output list, only constant extra space

**Algorithm:**
1. Sort the array
2. For each element at index `i`:
   - Skip duplicate values of `nums[i]`
   - Use two pointers (`left = i+1`, `right = n-1`) to find pairs that sum to `-nums[i]`
   - If sum is zero, add triplet and skip duplicates for both pointers
   - If sum < 0, increment `left`
   - If sum > 0, decrement `right`

**Example:**
```kotlin
val nums = intArrayOf(-1, 0, 1, 2, -1, -4)
val solution = ThreeSumSolution()
val result = solution.threeSum(nums)
// result is [[-1, -1, 2], [-1, 0, 1]]
```

