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
- [TwoSum](https://leetcode.com/problems/two-sum/) - Find two numbers in an array that sum to a target value
  - Time: O(n), Space: O(n)
  - Uses HashMap for constant-time lookup
  - LeetCode #1

- [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) - Check if any value appears at least twice in an array
  - Time: O(n), Space: O(n)
  - Uses HashSet for O(1) lookups and insertions
  - LeetCode #217

- [Reverse String](https://leetcode.com/problems/reverse-string/) - Reverse a string in-place
  - Time: O(n), Space: O(1)
  - Uses two-pointers technique (swap from both ends)
  - LeetCode #344

- [Group Anagrams](https://leetcode.com/problems/group-anagrams/) - Group strings that are anagrams of each other
  - Time: O(n * k), Space: O(n * k)
  - Uses HashMap with character frequency array as key
  - Optimized approach: counts character frequencies instead of sorting
  - LeetCode #49

- [Majority Element](https://leetcode.com/problems/majority-element/) - Find the element that appears more than ⌊n/2⌋ times
  - Time: O(n) worst case, O(n/2) best case, Space: O(1)
  - Uses Boyer-Moore Voting Algorithm with early return optimization
  - Can exit early when count reaches ⌊n/2⌋ + 1
  - LeetCode #169

- [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) - Return the k most frequent elements
  - Time: O(n), Space: O(n)
  - Uses bucket sort approach where index represents frequency
  - More efficient than heap-based approach for large datasets
  - LeetCode #347

- [Encode and Decode Strings](https://leetcode.com/problems/encode-and-decode-strings/) - Design an algorithm to encode a list of strings to a single string and decode it back
  - Time: O(n) for both encode and decode, Space: O(n)
  - Uses length prefix protocol: "{length}#{string}{length}#{string}..."
  - Works with any string content including special characters and delimiters
  - LeetCode #271 (Premium) / NeetCode

#### Strings
- [Valid Anagram](https://leetcode.com/problems/valid-anagram/) - Check if two strings are anagrams
  - Time: O(n), Space: O(1)
  - Uses HashMap for character frequency count
  - LeetCode #242

#### Linked List
- [Binary Number to Decimal](https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/) - Convert binary number in a linked list to integer
  - Time: O(n), Space: O(1)
  - Uses bit manipulation (left shift and OR operations)
  - LeetCode #1290

- [Find Middle Node](https://leetcode.com/problems/middle-of-the-linked-list/) - Find the middle node of a linked list
  - Time: O(n), Space: O(1)
  - Uses two-pointers technique (slow/fast)
  - LeetCode #876

- [Has Cycle](https://leetcode.com/problems/linked-list-cycle/) - Detect if a linked list contains a cycle
  - Time: O(n), Space: O(1)
  - Implements Floyd's cycle detection algorithm (tortoise and hare)
  - LeetCode #141

- [Remove Nth From End](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) - Remove the nth node from the end of the list
  - Time: O(n), Space: O(1)
  - Uses two-pointers with defined distance
  - LeetCode #19

#### Stack
- [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) - Determine if a string containing parentheses is valid
  - Time: O(n), Space: O(n)
  - Uses stack data structure for bracket matching
  - HashMap for O(1) lookups
  - LeetCode #20

### Search Algorithms

Framework for implementing and benchmarking search algorithms with design patterns.

#### Package Structure

```
search/
├── algorithms/          # Algorithm implementations
│   ├── LinearSearch     # O(n) - Sequential search
│   └── BinarySearch     # O(log n) - Binary search (requires sorted list)
├── core/                # Framework components
│   ├── SearchStrategy   # Interface for all search algorithms
│   ├── SearchType       # Enum of available algorithms
│   ├── SearchFactory    # Factory pattern for creating strategies
│   └── SearchableList   # Facade for easy searching
└── benchmark/           # Performance testing system
    ├── SearchBenchmark       # Benchmark execution and analysis
    ├── BenchmarkDataGenerator # Test data generation
    ├── BenchmarkDemo         # Automated benchmark demos
    └── BenchmarkInteractive  # Interactive CLI application
```

#### Implemented Algorithms

Linear Search
- Complexity: O(n) time, O(1) space
- Use Case: Unsorted lists, small datasets, single searches
- Characteristics:
  - Best case: O(1) - element at the beginning
  - Average case: O(n/2) ≈ O(n)
  - Worst case: O(n) - element at the end or not found
  - Works with any list (sorted or unsorted)

Binary Search
- Complexity: O(log n) time, O(1) space
- Use Case: Sorted lists, large datasets, multiple searches
- Requirements: List must be sorted
- Characteristics:
  - Best case: O(1) - element in the middle
  - Average case: O(log n)
  - Worst case: O(log n)
  - Divides search space by half each iteration

#### Design Patterns

Strategy Pattern
```kotlin
interface SearchStrategy<T : Comparable<T>> {
    fun search(collection: List<T>, target: T): Int
    fun name(): String
}
```

Factory Pattern
```kotlin
val strategy = SearchFactory.create<Int>(SearchType.BINARY)
val index = strategy.search(sortedList, target)
```

Facade Pattern
```kotlin
val list = SearchableList(listOf(1, 2, 3, 4, 5))
val index = list.find(3)  // Returns 2
```

#### Usage Examples

Basic Usage with Facade
```kotlin
import digital.tonima.search.core.SearchableList

val list = SearchableList(listOf(5, 2, 8, 1, 9, 3))
val index = list.find(8)  // Returns 2 (uses LINEAR by default)
```

Using Specific Algorithm
```kotlin
import digital.tonima.search.core.SearchType

val sortedList = SearchableList(listOf(1, 2, 3, 5, 8, 9))
sortedList.sort()  // Ensure list is sorted for binary search
val index = sortedList.find(5, SearchType.BINARY)  // O(log n)
```

Direct Algorithm Usage
```kotlin
import digital.tonima.search.algorithms.LinearSearch

val search = LinearSearch<Int>()
val index = search.search(listOf(1, 2, 3), 2)  // Returns 1
```

#### Benchmark System

Performance testing framework with:
- Precise measurements (nanoseconds and milliseconds)
- Automatic comparison between algorithms
- Scalability analysis (different data sizes)
- Visual reports (comparative and table formats)
- Statistics (fastest, slowest, average)

Running Benchmarks
```kotlin
import digital.tonima.search.benchmark.SearchBenchmark
import digital.tonima.search.algorithms.LinearSearch
import digital.tonima.search.algorithms.BinarySearch

val benchmark = SearchBenchmark()
val list = (1..1_000_000).toList()

benchmark.benchmark(LinearSearch<Int>(), list, 500_000)
benchmark.benchmark(BinarySearch<Int>(), list, 500_000)

benchmark.printComparativeReport()
```

Interactive Benchmark Application
```bash
# Execute BenchmarkInteractive.kt main() function
# Provides interactive menu with 6 testing options:
# 1. Quick Test (1,000 elements)
# 2. Medium Test (100,000 elements)
# 3. Large Test (1,000,000 elements)
# 4. Custom Test (user-defined size)
# 5. Scalability Test (multiple sizes)
# 6. Extreme Cases Analysis (best/worst/average)
```

Expected Performance

| Dataset Size | Linear Search | Binary Search | Speed-up |
|--------------|---------------|---------------|----------|
| 1,000        | ~0.001 ms     | ~0.0001 ms    | 10x      |
| 10,000       | ~0.01 ms      | ~0.0002 ms    | 50x      |
| 100,000      | ~0.1 ms       | ~0.0003 ms    | 333x     |
| 1,000,000    | ~1.0 ms       | ~0.0005 ms    | 2000x    |

#### Features

- Generic Implementation: Works with any `Comparable<T>` type
- Type-Safe: Full Kotlin type system support
- Well-Documented: KDoc comments on all public APIs
- Tested: Comprehensive test suite included
- Extensible: Easy to add new search algorithms
- Production-Ready: Clean architecture and error handling

#### Adding New Algorithms

```kotlin
// 1. Create new algorithm class
package digital.tonima.search.algorithms

class InterpolationSearch<T : Comparable<T>> : SearchStrategy<T> {
    override fun search(collection: List<T>, target: T): Int {
        // Your implementation here
        return -1
    }
    override fun name(): String = "Interpolation Search"
}

// 2. Add to SearchType enum
enum class SearchType {
    LINEAR,
    BINARY,
    INTERPOLATION  // New!
}

// 3. Update SearchFactory
fun <T : Comparable<T>> create(type: SearchType): SearchStrategy<T> {
    return when (type) {
        SearchType.LINEAR -> LinearSearch()
        SearchType.BINARY -> BinarySearch()
        SearchType.INTERPOLATION -> InterpolationSearch()  // New!
    }
}
```

### Sorting Algorithms

Framework for implementing and benchmarking sorting algorithms with design patterns (similar architecture to search algorithms).

#### Package Structure

```
sort/
├── algorithms/          # Algorithm implementations
│   ├── SelectionSort    # O(n²) - Simple comparison sort
│   └── QuickSort        # O(n log n) - Divide and conquer sort
├── core/                # Framework components
│   ├── SortStrategy     # Interface for all sorting algorithms
│   ├── SortType         # Enum of available algorithms
│   ├── SortFactory      # Factory pattern for creating strategies
│   └── SortableList     # Facade for easy sorting
└── benchmark/           # Performance testing system
    ├── SortBenchmark         # Benchmark execution and analysis
    ├── BenchmarkDataGenerator # Test data generation (multiple patterns)
    ├── BenchmarkDemo         # Automated benchmark demos
    ├── BenchmarkInteractive  # Interactive CLI application
    └── BenchmarkExample      # Simple usage examples
```

#### Implemented Algorithms

Selection Sort
- Complexity: O(n²) time, O(1) space
- Use Case: Small datasets, minimal memory usage
- Characteristics:
  - Best/Average/Worst case: O(n²)
  - In-place sorting (no extra space)
  - Not stable (relative order of equal elements may change)
  - Minimal number of swaps (n-1 at most)
  - Simple and easy to understand

Quick Sort
- Complexity: O(n log n) average, O(n²) worst case, O(log n) space
- Use Case: Large datasets, general purpose sorting
- Characteristics:
  - Best/Average case: O(n log n)
  - Worst case: O(n²) (when pivot is always min/max)
  - Space: O(log n) for recursion stack
  - Not stable
  - Fast in practice, cache-friendly
  - Uses divide and conquer strategy

#### Design Patterns

Strategy Pattern
```kotlin
interface SortStrategy<T : Comparable<T>> {
    fun sort(collection: List<T>): List<T>
    fun name(): String
    fun timeComplexity(): String
    fun spaceComplexity(): String
    fun isStable(): Boolean
}
```

Factory Pattern
```kotlin
val strategy = SortFactory.create<Int>(SortType.QUICK)
val sorted = strategy.sort(unsortedList)
```

Facade Pattern
```kotlin
val list = SortableList(listOf(5, 2, 8, 1, 9))
val sorted = list.sort()  // Uses QUICK by default
```

#### Usage Examples

Basic Usage with Facade
```kotlin
import digital.tonima.sort.core.SortableList

val list = SortableList(listOf(5, 2, 8, 1, 9, 3))
val sorted = list.sort()  // Returns [1, 2, 3, 5, 8, 9] (uses QUICK by default)
```

Using Specific Algorithm
```kotlin
import digital.tonima.sort.core.SortType

val list = SortableList(listOf(5, 2, 8, 1, 9))
val sorted = list.sort(SortType.SELECTION)  // Use Selection Sort
val sorted2 = list.sort(SortType.QUICK)     // Use Quick Sort
```

Direct Algorithm Usage
```kotlin
import digital.tonima.sort.algorithms.QuickSort

val sorter = QuickSort<Int>()
val sorted = sorter.sort(listOf(5, 2, 8, 1, 9))  // Returns [1, 2, 5, 8, 9]
println(sorter.name())             // "Quick Sort"
println(sorter.timeComplexity())   // "O(n log n) average, O(n²) worst"
println(sorter.spaceComplexity())  // "O(log n)"
println(sorter.isStable())         // false
```

#### Benchmark System

Performance testing framework with:
- Precise measurements (nanoseconds and milliseconds)
- Automatic comparison between algorithms
- Multiple data patterns (random, sorted, reverse, nearly sorted, etc.)
- Scalability analysis (different data sizes)
- Visual reports (comparative, table, and comparison charts)
- Statistics (fastest, slowest, average)

Data Patterns Available
- Random: Unordered random data
- Sorted: Already sorted (best case for some algorithms)
- Reverse Sorted: Worst case for many algorithms
- Nearly Sorted: Few elements out of order
- Many Duplicates: Tests handling of equal elements
- Uniform: All elements the same

Running Benchmarks
```kotlin
import digital.tonima.sort.benchmark.SortBenchmark
import digital.tonima.sort.benchmark.BenchmarkDataGenerator
import digital.tonima.sort.algorithms.SelectionSort
import digital.tonima.sort.algorithms.QuickSort

val benchmark = SortBenchmark()
val data = BenchmarkDataGenerator.generateRandomList(1000)

benchmark.benchmark(SelectionSort<Int>(), data)
benchmark.benchmark(QuickSort<Int>(), data)

benchmark.printComparativeReport()
```

Pattern Analysis
```kotlin
val patterns = mapOf<String, (Int) -> List<Int>>(
    "Random" to { size -> BenchmarkDataGenerator.generateRandomList(size) },
    "Sorted" to { size -> BenchmarkDataGenerator.generateSortedList(size) },
    "Reverse" to { size -> BenchmarkDataGenerator.generateReverseSortedList(size) }
)

benchmark.benchmarkByPattern(strategy, 1000, patterns)
```

Expected Performance

| Dataset Size | Selection Sort | Quick Sort | Speed-up |
|--------------|----------------|------------|----------|
| 100          | ~0.01 ms       | ~0.005 ms  | 2x       |
| 1,000        | ~1.0 ms        | ~0.05 ms   | 20x      |
| 10,000       | ~100 ms        | ~0.5 ms    | 200x     |
| 100,000      | ~10,000 ms     | ~5 ms      | 2000x    |

#### Features

- Generic Implementation: Works with any `Comparable<T>` type
- Type-Safe: Full Kotlin type system support
- Well-Documented: KDoc comments on all public APIs
- Tested: 61 comprehensive unit tests
- Immutable: Original lists are never modified
- Extensible: Easy to add new sorting algorithms
- Production-Ready: Clean architecture and error handling
- Rich Metadata: Each algorithm reports complexity and stability

#### Adding New Algorithms

The framework supports adding 8 additional algorithms:

```kotlin
// 1. Create new algorithm class
package digital.tonima.sort.algorithms

class MergeSort<T : Comparable<T>> : SortStrategy<T> {
    override fun sort(collection: List<T>): List<T> {
        // Your implementation here
        return collection
    }
    override fun name(): String = "Merge Sort"
    override fun timeComplexity(): String = "O(n log n)"
    override fun spaceComplexity(): String = "O(n)"
    override fun isStable(): Boolean = true
}

// 2. Add to SortType enum
enum class SortType {
    BUBBLE,
    SELECTION,
    INSERTION,
    MERGE,      // New!
    QUICK,
    HEAP,
    // ... etc
}

// 3. Update SortFactory
fun <T : Comparable<T>> create(type: SortType): SortStrategy<T> {
    return when (type) {
        SortType.SELECTION -> SelectionSort()
        SortType.QUICK -> QuickSort()
        SortType.MERGE -> MergeSort()  // New!
        // ...
    }
}
```

Ready to Implement:
- Bubble Sort - O(n²)
- Insertion Sort - O(n²)
- Merge Sort - O(n log n)
- Heap Sort - O(n log n)
- Shell Sort - O(n log n) or O(n^1.25)
- Counting Sort - O(n + k)
- Radix Sort - O(nk)
- Bucket Sort - O(n + k)

## Technologies

- Language: Kotlin 2.1.20
- Build Tool: Gradle
- JVM: Java 23+
- Testing: JUnit 5 + Kotlin Test

## Complexity Analysis

| Algorithm/Operation | Time | Space | Details |
|-----------|------|-------|---------|
| Two Sum | O(n) | O(n) | HashMap lookup |
| Reverse String | O(n) | O(1) | Two pointers swap |
| Group Anagrams | O(n * k) | O(n * k) | Character frequency array |
| Majority Element | O(n) worst, O(n/2) best | O(1) | Boyer-Moore with early return |
| Top K Frequent Elements | O(n) | O(n) | Bucket sort by frequency |
| Binary to Decimal | O(n) | O(1) | Bit manipulation |
| Find Middle | O(n) | O(1) | Two pointers |
| Has Cycle | O(n) | O(1) | Floyd algorithm |
| Remove Nth | O(n) | O(1) | Two pointers |
| Palindrome Linked List | O(n) | O(1) | Two pointers + reversal |
| LinkedList Append | O(1) | O(1) | Tail reference |
| DoublyLinkedList Operations | | | |
| DLL Append | O(1) | O(1) | Direct tail reference |
| DLL Prepend | O(1) | O(1) | Direct head reference |
| DLL RemoveFirst | O(1) | O(1) | Direct head reference |
| DLL RemoveLast | O(1) | O(1) | Direct tail.previous reference |
| DLL Get | O(n/2) | O(1) | Traverses from closest end |
| DLL Insert | O(n) | O(1) | O(1) for index 0 |
| DLL Remove | O(n) | O(1) | O(1) for first/last |
| DLL Set | O(n) | O(1) | Uses get() internally |
| Queue Operations | | | |
| Enqueue | O(1) | O(1) | Add to tail |
| Dequeue | O(1) | O(1) | Remove from top |
| Stack Operations | | | |
| Push | O(1) | O(1) | Add to top |
| Pop | O(1) | O(1) | Remove from top |
| Valid Parentheses | O(n) | O(n) | Stack + HashMap lookup |
| Graph Operations | | | |
| Add Edge | O(1) | O(1) | Add to adjacency list |
| Breadth First Search | O(V + E) | O(V) | V = vertices, E = edges, space for visited set and queue |
| Dijkstra's Algorithm | O((V + E) log V) | O(V) | Shortest path with priority queue, space for costs/parents/visited |
| Print Graph | O(V + E) | O(1) | Iterate through adjacency list |
| Search Algorithms | | | |
| Linear Search | O(n) | O(1) | Sequential traversal |
| Binary Search | O(log n) | O(1) | Divide and conquer (requires sorted list) |
| Search Benchmark | O(1) | O(k) | k = number of results stored |
| Sorting Algorithms | | | |
| Selection Sort | O(n²) | O(1) | In-place, minimal swaps, not stable |
| Quick Sort | O(n log n) avg, O(n²) worst | O(log n) | Divide and conquer, not stable, fast in practice |
| Sort Benchmark | O(1) | O(k) | k = number of results stored |

## References and Concepts

- Two Pointers: Technique for solving problems in arrays and linked lists
- HashMap Optimization: Reducing complexity with auxiliary data structures
- Floyd's Cycle Detection: Algorithm to detect cycles without extra space
- Doubly Linked Lists: Data structure with bidirectional traversal capabilities
  - Each node has references to both next and previous nodes
  - Enables O(1) operations at both ends
  - Optimized traversal from closest end (head or tail)
- Generic Types: Reusable implementations in Kotlin
- Queue and Stack: Fundamental data structures for managing collections of elements
  - Queue: FIFO (First In, First Out) - elements added to the end and removed from the front
  - Stack: LIFO (Last In, First Out) - elements added and removed from the top
- Graph: Non-linear data structure representing relationships between objects
  - Adjacency List: Space-efficient representation using HashMap (O(V + E) space)
  - Directed vs Undirected: Edges can be one-way or bidirectional
  - Weighted Graphs: Edges have associated costs/weights for shortest path problems
  - Breadth-First Search (BFS): Level-order traversal using queue
    - Explores all neighbors before moving to next level
    - Uses visited set to prevent cycles and duplicate visits
    - Applications: shortest path, level-order traversal, connected components
  - Dijkstra's Shortest Path Algorithm: Greedy algorithm for finding shortest path in weighted graphs
    - Uses priority queue to always process minimum-cost node first
    - Maintains costs map to track minimum distance to each node
    - Parent tracking enables path reconstruction
    - Guarantees optimal solution for non-negative weights
    - Applications: GPS navigation, network routing, game pathfinding
    - Time: O((V + E) log V), Space: O(V)
  - Graph Terminology:
    - Vertices (V): Nodes in the graph
    - Edges (E): Connections between vertices
    - Weight: Cost associated with an edge
    - Path: Sequence of vertices connected by edges
    - Cycle: Path that starts and ends at same vertex
    - Connected Component: Set of vertices where each vertex is reachable from any other
- Search Algorithms: Techniques for finding elements in collections
  - Linear Search: Sequential traversal, works on any list, O(n)
  - Binary Search: Divide and conquer, requires sorted list, O(log n)
  - Scalability: Binary search is exponentially faster for large datasets
- Design Patterns: Applied patterns in search module
  - Strategy Pattern: Defines a family of interchangeable algorithms
  - Factory Pattern: Centralizes object creation logic
  - Facade Pattern: Provides simplified interface to complex subsystems
- Performance Benchmarking: Empirical analysis of algorithm efficiency
  - Measure execution time (nanoseconds precision)
  - Compare multiple algorithms on same dataset
  - Analyze scalability with varying input sizes
- Sorting Algorithms: Techniques for ordering elements in collections
  - Selection Sort: Simple O(n²) algorithm, finds minimum and places it at the beginning
  - Quick Sort: Efficient O(n log n) average, uses divide and conquer with pivot partitioning
  - Stability: Whether algorithm maintains relative order of equal elements
  - In-place Sorting: Algorithms that use O(1) extra space
  - Comparison Sorting: Algorithms that sort by comparing elements
- Algorithm Analysis: Understanding performance characteristics
  - Best/Average/Worst Case: Different scenarios affect algorithm performance
  - Time-Space Tradeoff: Balancing execution speed vs memory usage
  - Scalability: How algorithm performs as input size grows
  - Data Patterns: Random, sorted, reverse-sorted, nearly-sorted affect performance

## LeetCode Problems

LeetCode problems implemented in this project:

- #1: [Two Sum](https://leetcode.com/problems/two-sum/) - Easy
- #19: [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) - Medium
- #20: [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) - Easy
- #49: [Group Anagrams](https://leetcode.com/problems/group-anagrams/) - Medium
- #141: [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) - Easy
- #169: [Majority Element](https://leetcode.com/problems/majority-element/) - Easy
- #217: [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) - Easy
- #242: [Valid Anagram](https://leetcode.com/problems/valid-anagram/) - Easy
- #344: [Reverse String](https://leetcode.com/problems/reverse-string/) - Easy
- #347: [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) - Medium
- #876: [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) - Easy
- #1290: [Convert Binary Number in a Linked List to Integer](https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/) - Easy

## License

This project is open source and can be freely used for educational purposes.

---

Last Updated: February 2026
