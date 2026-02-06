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

// Initialize with existing elements (heapify)
val elements = mutableListOf(5, 10, 3, 8, 15, 1)
val heap = Heap(elements) // O(n) heapify

// Get k largest elements without modifying heap
val top3 = heap.kLargestElements(3)  // Returns [15, 10, 8]
println(heap.peek()) // Still 15, heap unchanged

// Get all elements in descending order
val all = heap.allElementsDescending()  // Returns [15, 10, 8, 5, 3, 1]
println(heap.size()) // Still 6, heap unchanged
```

Implementation Notes:
- Uses `bubbleUp` to restore heap property after insertion
- Uses `bubbleDown` to restore heap property after removal
- Generic type `T` allows any data type
- `elementPriority` function extracts comparable value from elements
- Default priority is the element itself (for `Int`, `String`, etc.)
- Heap property: `priority(parent) > priority(children)` for max-heap
- `kLargestElements` and `allElementsDescending` are non-destructive (preserve original heap)

#### HashTable
Generic hash table with chaining collision resolution using Knuth's multiplicative hashing with golden ratio. Adapted from Python implementation.

Operations:
- `insert(value)` - O(1) average, O(n) worst case - Add element to hash table
- `search(key)` - O(1) average, O(n) worst case - Find element by key
- `contains(value)` - O(1) average, O(n) worst case - Check if value exists
- `delete(value)` - O(1) average, O(n) worst case - Remove element
- `isEmpty()` - O(1) - Check if hash table is empty
- `size` - O(n) - Get number of elements

Characteristics:
- Hash function: `h(k) = floor(m * ((k * φ) mod 1))` where `φ = (√5 - 1) / 2`
- Collision resolution: Chaining with LinkedList
- Generic type `<T>` for any data type
- Custom key extraction via lambda: `(T) -> Int`
- Load factor: Unlimited (no resizing)
- Dynamic scaling: Buckets count fixed at initialization

Use Cases:
- Fast lookup tables
- Symbol tables in compilers
- Caching systems
- Database indexing
- Associative arrays / Dictionaries
- Remove duplicates from large datasets

Example:
```kotlin
// Simple integer hash table
val hashTable = HashTable<Int>(16)
hashTable.insert(10)
hashTable.insert(20)
hashTable.insert(30)

val value = hashTable.search(10)  // Returns 10
hashTable.delete(20)

// Custom objects with key extraction
data class Person(val id: Int, val name: String)
val peopleTable = HashTable<Person>(16) { it.id }
peopleTable.insert(Person(1, "Alice"))
peopleTable.insert(Person(2, "Bob"))

val person = peopleTable.search(1)  // Returns Person(1, "Alice")
if (peopleTable.contains(Person(2, "Bob"))) {
    peopleTable.delete(Person(2, "Bob"))
}

// Handling collisions (automatic chaining)
val smallTable = HashTable<String>(2)  // Small table forces collisions
smallTable.insert("key1")
smallTable.insert("key2")
smallTable.insert("key3")  // Will chain with key1 or key2
```

Time and Space Complexity:

| Operation | Average | Worst Case |
|-----------|---------|-----------|
| Insert | O(1) | O(n) |
| Search | O(1) | O(n) |
| Delete | O(1) | O(n) |
| Iteration | O(n) | O(n) |

Space Complexity: O(n + m) where n = elements, m = bucket count

Implementation Notes:
- Uses `BigDecimal` for precise golden ratio calculations
- Chaining allows unlimited elements regardless of bucket count
- Worst case (all collisions) degrades to O(n), but rare in practice
- Custom `extractKey` lambda enables flexible key definition
- Thread-unsafe (needs synchronization for concurrent access)

#### Graph
Generic Graph implementation with adjacency list representation.

Operations:
- `addEdge(source, destination, weight, bidirectional)` - O(1) - Add weighted edge between vertices
- `breadthFirstSearch(startNode)` - O(V + E) - Traverse graph level by level
- `dijkstra(startNode, endNode)` - O((V + E) log V) - Find shortest path in weighted graph
- `printGraph()` - O(V + E) - Display graph structure

Characteristics:
- Supports both directed and undirected graphs
- Supports weighted edges for shortest path algorithms
- Efficient adjacency list representation using HashMap
- BFS uses queue for level-order traversal
- Cycle detection with visited set
- Generic type support for vertices

##### Dijkstra's Shortest Path Algorithm
Finds the shortest path between two nodes in a weighted graph.

Algorithm Steps:
1. Initialize distances to all nodes as infinite, except start node (0)
2. Use a priority queue to always process the node with minimum distance first
3. For each node, explore all neighbors and update their costs if a shorter path is found
4. Track parent nodes to reconstruct the shortest path

Complexity Analysis:
- Time: O((V + E) log V) where V = vertices, E = edges
  - Each vertex is processed once: O(V)
  - Each edge is relaxed once: O(E)
  - Priority queue operations: O(log V)
- Space: O(V) for costs, parents, visited set, and priority queue

Use Cases:
- GPS navigation and routing systems
- Network routing protocols
- Social network analysis (shortest connection path)
- Game pathfinding with weighted terrain

Example:
```kotlin
val graph = Graph<String>()
graph.addEdge("A", "B", weight = 4)
graph.addEdge("A", "C", weight = 2)
graph.addEdge("B", "D", weight = 5)
graph.addEdge("C", "D", weight = 8)
graph.addEdge("C", "E", weight = 10)
graph.addEdge("D", "E", weight = 2)

graph.dijkstra("A", "E")
// Output:
// Custo Mínimo: 11
// Caminho: A -> B -> D -> E
```

Key Features:
- Greedy algorithm that always selects the node with minimum cost
- Guarantees optimal solution for graphs with non-negative weights
- Early termination when destination is reached
- Path reconstruction using parent tracking

### Algorithms

#### Arrays

##### [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

This algorithm removes duplicate elements from a sorted array in-place, such that each unique element appears only once. The relative order of the elements is preserved.

**Complexity:**
- Time: O(n) - The algorithm iterates through the array once.
- Space: O(1) - The operation is performed in-place, without using extra space.

**Example:**
```kotlin
val nums = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
val solution = RemoveDuplicatesSolution()
val newLength = solution.removeDuplicates(nums)
// newLength is 5, and nums is modified to [0, 1, 2, 3, 4, _, _, _, _, _]
```

### Data Structures
//... existing code...
