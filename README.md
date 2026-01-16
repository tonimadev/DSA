# DSA - Data Structures and Algorithms

A Kotlin project implementing classic data structures and algorithms with automated tests, focusing on efficiency and code clarity.

## 📋 About the Project

This project implements fundamental data structures and algorithms with emphasis on:
- **Performance**: Time and space complexity analysis
- **Quality**: Automated tests for each component
- **Clarity**: Well-documented and easy-to-understand code
- **Kotlin**: Leverages modern language features

## 🏗️ Project Structure

### Data Structures (`dataestructures/`)

#### Linked List
- Generic Linked List implementation
- Support for any data type (`<T>`)
- Operations: append, prepend, insert, remove, search
- Optimized complexity for operations

#### Doubly Linked List
- Generic Doubly Linked List implementation with bidirectional traversal
- Support for any data type (`<T>`)
- **Operations**:
  - `append(value)` - O(1) - Add element at the end
  - `prepend(value)` - O(1) - Add element at the beginning
  - `insert(index, value)` - O(n) - Insert at specific position (O(1) for index 0)
  - `remove(index)` - O(n) - Remove at specific position (O(1) for first/last)
  - `removeFirst()` - O(1) - Remove first element
  - `removeLast()` - O(1) - Remove last element
  - `get(index)` - O(n/2) - Access element (optimized to traverse from closest end)
  - `set(index, value)` - O(n) - Update element value
- **Optimizations**:
  - Bidirectional traversal (previous/next pointers)
  - `get()` traverses from closest end (head or tail)
  - Direct references to head and tail for O(1) operations
  - Specialized methods for edge cases

#### Queue
- Generic Queue implementation (FIFO - First In, First Out)
- Support for any data type (`<T>`)
- **Operations**:
  - `enqueue(value)` - O(1) - Add element to the end
  - `dequeue()` - O(1) - Remove and return the first element
- **Characteristics**:
  - FIFO order
  - Implemented with linked list for efficient O(1) operations
  - Supports generic data types

#### Stack
- Generic Stack implementation (LIFO - Last In, First Out)
- Support for any data type (`<T>`)
- **Operations**:
  - `push(value)` - O(1) - Add element to the top
  - `pop()` - O(1) - Remove and return the top element
- **Characteristics**:
  - LIFO order
  - Efficiently implemented with a top pointer
  - Supports generic data types

### Algorithms (`algorithms/`)

#### Arrays
- **[TwoSum](https://leetcode.com/problems/two-sum/)** - Find two numbers in an array that sum to a target value
  - Time: O(n), Space: O(n)
  - Uses HashMap for constant-time lookup
  - LeetCode #1
  - **Complexity Details**:
    - **Time O(n)**: Single pass through array with O(1) HashMap lookups
    - **Space O(n)**: HashMap stores up to n numbers in worst case

- **[Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)** - Check if any value appears at least twice in an array
  - Time: O(n), Space: O(n)
  - Uses HashSet for O(1) lookups and insertions
  - LeetCode #217

- **[Reverse String](https://leetcode.com/problems/reverse-string/)** - Reverse a string in-place
  - Time: O(n), Space: O(1)
  - Uses two-pointers technique (swap from both ends)
  - LeetCode #344

- **[Group Anagrams](https://leetcode.com/problems/group-anagrams/)** - Group strings that are anagrams of each other
  - Time: O(n * k), Space: O(n * k)
  - Uses HashMap with character frequency array as key
  - Optimized approach: counts character frequencies instead of sorting
  - LeetCode #49

#### Strings
- **[Valid Anagram](https://leetcode.com/problems/valid-anagram/)** - Check if two strings are anagrams
  - Time: O(n), Space: O(1)
  - Uses HashMap for character frequency count
  - LeetCode #242

#### Linked List
- **[Binary Number to Decimal](https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/)** - Convert binary number in a linked list to integer
  - Time: O(n), Space: O(1)
  - Uses bit manipulation (left shift and OR operations)
  - LeetCode #1290

- **[Find Middle Node](https://leetcode.com/problems/middle-of-the-linked-list/)** - Find the middle node of a linked list
  - Time: O(n), Space: O(1)
  - Uses two-pointers technique (slow/fast)
  - LeetCode #876

- **[Has Cycle](https://leetcode.com/problems/linked-list-cycle/)** - Detect if a linked list contains a cycle
  - Time: O(n), Space: O(1)
  - Implements Floyd's cycle detection algorithm (tortoise and hare)
  - LeetCode #141

- **[Remove Nth From End](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)** - Remove the nth node from the end of the list
  - Time: O(n), Space: O(1)
  - Uses two-pointers with defined distance
  - LeetCode #19

#### Stack
- **[Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)** - Determine if a string containing parentheses is valid
  - Time: O(n), Space: O(n)
  - Uses stack data structure for bracket matching
  - HashMap for O(1) lookups
  - LeetCode #20

### Search Algorithms (`search/`)

A complete framework for implementing and benchmarking search algorithms, organized with professional design patterns.

#### 📦 Package Structure

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

#### 🔍 Implemented Algorithms

**Linear Search**
- **Complexity**: O(n) time, O(1) space
- **Use Case**: Unsorted lists, small datasets, single searches
- **Characteristics**:
  - Best case: O(1) - element at the beginning
  - Average case: O(n/2) ≈ O(n)
  - Worst case: O(n) - element at the end or not found
  - Works with any list (sorted or unsorted)

**Binary Search**
- **Complexity**: O(log n) time, O(1) space
- **Use Case**: Sorted lists, large datasets, multiple searches
- **Requirements**: List must be sorted
- **Characteristics**:
  - Best case: O(1) - element in the middle
  - Average case: O(log n)
  - Worst case: O(log n)
  - Divides search space by half each iteration

#### 🎯 Design Patterns

**Strategy Pattern**
```kotlin
interface SearchStrategy<T : Comparable<T>> {
    fun search(collection: List<T>, target: T): Int
    fun name(): String
}
```

**Factory Pattern**
```kotlin
val strategy = SearchFactory.create<Int>(SearchType.BINARY)
val index = strategy.search(sortedList, target)
```

**Facade Pattern**
```kotlin
val list = SearchableList(listOf(1, 2, 3, 4, 5))
val index = list.find(3)  // Returns 2
```

#### 💡 Usage Examples

**Basic Usage with Facade**
```kotlin
import digital.tonima.search.core.SearchableList

val list = SearchableList(listOf(5, 2, 8, 1, 9, 3))
val index = list.find(8)  // Returns 2 (uses LINEAR by default)
```

**Using Specific Algorithm**
```kotlin
import digital.tonima.search.core.SearchType

val sortedList = SearchableList(listOf(1, 2, 3, 5, 8, 9))
sortedList.sort()  // Ensure list is sorted for binary search
val index = sortedList.find(5, SearchType.BINARY)  // O(log n)
```

**Direct Algorithm Usage**
```kotlin
import digital.tonima.search.algorithms.LinearSearch

val search = LinearSearch<Int>()
val index = search.search(listOf(1, 2, 3), 2)  // Returns 1
```

#### 📊 Benchmark System

Complete performance testing framework with:
- Precise measurements (nanoseconds and milliseconds)
- Automatic comparison between algorithms
- Scalability analysis (different data sizes)
- Visual reports (comparative and table formats)
- Statistics (fastest, slowest, average)

**Running Benchmarks**
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

**Interactive Benchmark Application**
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

**Expected Performance**

| Dataset Size | Linear Search | Binary Search | Speed-up |
|--------------|---------------|---------------|----------|
| 1,000        | ~0.001 ms     | ~0.0001 ms    | 10x      |
| 10,000       | ~0.01 ms      | ~0.0002 ms    | 50x      |
| 100,000      | ~0.1 ms       | ~0.0003 ms    | 333x     |
| 1,000,000    | ~1.0 ms       | ~0.0005 ms    | **2000x** |

#### 🔧 Features

- **Generic Implementation**: Works with any `Comparable<T>` type
- **Type-Safe**: Full Kotlin type system support
- **Well-Documented**: KDoc comments on all public APIs
- **Tested**: Comprehensive test suite included
- **Extensible**: Easy to add new search algorithms
- **Production-Ready**: Clean architecture and error handling

#### 📚 Adding New Algorithms

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

## 🛠️ Technologies

- **Language**: Kotlin 2.1.20
- **Build Tool**: Gradle
- **JVM**: Java 23+
- **Testing**: JUnit 5 + Kotlin Test


## 📊 Complexity Analysis

| Algorithm/Operation | Time | Space | Details |
|-----------|------|-------|---------|
| Two Sum | O(n) | O(n) | HashMap lookup |
| Reverse String | O(n) | O(1) | Two pointers swap |
| Group Anagrams | O(n * k) | O(n * k) | Character frequency array |
| Binary to Decimal | O(n) | O(1) | Bit manipulation |
| Find Middle | O(n) | O(1) | Two pointers |
| Has Cycle | O(n) | O(1) | Floyd algorithm |
| Remove Nth | O(n) | O(1) | Two pointers |
| Palindrome Linked List | O(n) | O(1) | Two pointers + reversal |
| LinkedList Append | O(1) | O(1) | Tail reference |
| **DoublyLinkedList Operations** | | | |
| DLL Append | O(1) | O(1) | Direct tail reference |
| DLL Prepend | O(1) | O(1) | Direct head reference |
| DLL RemoveFirst | O(1) | O(1) | Direct head reference |
| DLL RemoveLast | O(1) | O(1) | Direct tail.previous reference |
| DLL Get | O(n/2) | O(1) | Traverses from closest end |
| DLL Insert | O(n) | O(1) | O(1) for index 0 |
| DLL Remove | O(n) | O(1) | O(1) for first/last |
| DLL Set | O(n) | O(1) | Uses get() internally |
| **Queue Operations** | | | |
| Enqueue | O(1) | O(1) | Add to tail |
| Dequeue | O(1) | O(1) | Remove from head |
| **Stack Operations** | | | |
| Push | O(1) | O(1) | Add to top |
| Pop | O(1) | O(1) | Remove from top |
| Valid Parentheses | O(n) | O(n) | Stack + HashMap lookup |
| **Search Algorithms** | | | |
| Linear Search | O(n) | O(1) | Sequential traversal |
| Binary Search | O(log n) | O(1) | Divide and conquer (requires sorted list) |
| Search Benchmark | O(1) | O(k) | k = number of results stored |

## 📚 References and Concepts

- **Two Pointers**: Technique for solving problems in arrays and linked lists
- **HashMap Optimization**: Reducing complexity with auxiliary data structures
- **Floyd's Cycle Detection**: Algorithm to detect cycles without extra space
- **Doubly Linked Lists**: Data structure with bidirectional traversal capabilities
  - Each node has references to both next and previous nodes
  - Enables O(1) operations at both ends
  - Optimized traversal from closest end (head or tail)
- **Generic Types**: Reusable implementations in Kotlin
- **Queue and Stack**: Fundamental data structures for managing collections of elements
  - Queue: FIFO (First In, First Out) - elements added to the end and removed from the front
  - Stack: LIFO (Last In, First Out) - elements added and removed from the top
- **Search Algorithms**: Techniques for finding elements in collections
  - **Linear Search**: Sequential traversal, works on any list, O(n)
  - **Binary Search**: Divide and conquer, requires sorted list, O(log n)
  - **Scalability**: Binary search is exponentially faster for large datasets
- **Design Patterns**: Applied patterns in search module
  - **Strategy Pattern**: Defines a family of interchangeable algorithms
  - **Factory Pattern**: Centralizes object creation logic
  - **Facade Pattern**: Provides simplified interface to complex subsystems
- **Performance Benchmarking**: Empirical analysis of algorithm efficiency
  - Measure execution time (nanoseconds precision)
  - Compare multiple algorithms on same dataset
  - Analyze scalability with varying input sizes

## 🔗 LeetCode Problems

All algorithms in this project are based on classic LeetCode problems:

- **#1**: [Two Sum](https://leetcode.com/problems/two-sum/) - Easy
- **#19**: [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) - Medium
- **#20**: [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) - Easy
- **#49**: [Group Anagrams](https://leetcode.com/problems/group-anagrams/) - Medium
- **#141**: [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) - Easy
- **#217**: [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) - Easy
- **#242**: [Valid Anagram](https://leetcode.com/problems/valid-anagram/) - Easy
- **#344**: [Reverse String](https://leetcode.com/problems/reverse-string/) - Easy
- **#876**: [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) - Easy
- **#1290**: [Convert Binary Number in a Linked List to Integer](https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/) - Easy

## 📝 License

This project is open source and can be freely used for educational purposes.

---

**Last Updated**: January 2026
