# DSA - Data Structures and Algorithms

Kotlin implementations of classic data structures and algorithms with unit tests and complexity analysis.

## About

This is a learning project covering fundamental computer science concepts. Each implementation includes time/space complexity analysis and comprehensive test coverage.

## Project Structure

### Data Structures

#### Arrays

Three array implementations optimized for different use cases:

<details>
<summary><strong>SortedArray</strong></summary>

Fixed-size array that maintains sorted order on insertion. Generic implementation with `Comparable<T>` constraint.

**Operations:**
- `insert(value)` - O(n) - Inserts value maintaining sorted order (requires shifting elements)
- `delete(value)` - O(n) - Removes value using binary search + shifting
- `deleteByIndex(index)` - O(n) - Removes element at index (requires shifting)
- `binarySearch(value)` - O(log n) - Efficient search on sorted array
- `get(index)` - O(1) - Direct access by index

**Characteristics:**
- Fixed capacity set at initialization
- Always maintains sorted order
- Optimized for search operations (binary search)
- Slower insertions due to maintaining order
- Space Complexity: O(n)

**Use Cases:**
- When data must be kept sorted at all times
- Frequent search operations with fewer insertions
- Small to medium datasets with known maximum size
- Priority-based systems requiring ordered access

**Example:**
```kotlin
val sortedArray = SortedArray<Int>(10)
sortedArray.insert(5)
sortedArray.insert(2)
sortedArray.insert(8)
println(sortedArray) // Output: [2, 5, 8]
sortedArray.binarySearch(5) // Returns index 1
```

</details>

<details>
<summary><strong>UnsortedArray</strong></summary>

Fixed-size array with fast insertions and removals. Generic implementation supporting any data type.

**Operations:**
- `insert(value)` - O(1) - Appends to end of array
- `remove(index)` - O(1) - Removes by replacing with last element (doesn't maintain order)
- `get(target)` - O(n) - Linear search for element
- `traverse(operation)` - O(n) - Applies operation to all elements

**Characteristics:**
- Fixed capacity set at initialization
- No ordering guarantees
- Removal replaces deleted element with last element (very fast)
- Simple and efficient for basic operations
- Space Complexity: O(n)

**Use Cases:**
- When order doesn't matter
- Frequent insertions and deletions needed
- Simple collection without search requirements
- Temporary storage or buffers

**Example:**
```kotlin
val unsortedArray = UnsortedArray<Int>(10)
unsortedArray.insert(10)
unsortedArray.insert(20)
unsortedArray.insert(30)
unsortedArray.remove(1) // Replaces 20 with 30, size becomes 2
println(unsortedArray) // Output:  10 30
```

</details>

<details>
<summary><strong>UnsortedDynamicArray</strong></summary>

Self-resizing array that grows and shrinks automatically. Generic implementation supporting any data type.

**Operations:**
- `insert(value)` - O(1) amortized - Appends to end, doubles capacity when full
- `remove(target)` - O(n) - Finds and removes element, halves capacity when size ≤ capacity/4
- `find(target)` - O(n) - Linear search for element

**Resizing Strategy:**
- Doubling: When `size == capacity`, doubles capacity to `capacity * 2`
- Halving: When `size ≤ capacity / 4`, halves capacity to `capacity / 2`
- Amortized O(1) insertions: While individual resize operations cost O(n), they happen infrequently enough that average cost per insertion is constant
- Space efficiency: Shrinks to avoid wasting memory with sparse arrays

**Complexity Analysis:**
- `insert(value)` - O(1) amortized, O(n) worst case when resizing
- `remove(target)` - O(n) for search + shift, O(n) for potential resize
- `find(target)` - O(n) linear search
- Space Complexity: O(n), with capacity bounded by 2n

**Characteristics:**
- Automatic capacity management
- No fixed size limit
- Memory efficient with dynamic shrinking
- Maintains insertion order
- Prevents overflow errors

**Use Cases:**
- When size is unknown or highly variable
- Need array performance without capacity planning
- Long-lived collections that grow and shrink
- General-purpose dynamic collections

**Example:**
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

**Performance Comparison:**

| Operation | SortedArray | UnsortedArray | UnsortedDynamicArray |
|-----------|-------------|---------------|----------------------|
| Insert | O(n) | O(1) | O(1) amortized |
| Remove | O(n) | O(1) | O(n) |
| Search | O(log n) | O(n) | O(n) |
| Access by index | O(1) | O(1) | N/A |
| Space | O(n) fixed | O(n) fixed | O(n) dynamic |
| Resizing | No | No | Yes |

</details>

#### Linked List
Generic Linked List implementation supporting any data type. Operations include append, prepend, insert, remove, and search with optimized complexity.

<details>
<summary><strong>Doubly Linked List</strong></summary>

Generic Doubly Linked List with bidirectional traversal.

**Operations:**
- `append(value)` - O(1) - Add element at the end
- `prepend(value)` - O(1) - Add element at the beginning
- `insert(index, value)` - O(n) - Insert at specific position (O(1) for index 0)
- `remove(index)` - O(n) - Remove at specific position (O(1) for first/last)
- `removeFirst()` - O(1) - Remove first element
- `removeLast()` - O(1) - Remove last element
- `get(index)` - O(n/2) - Access element (optimized to traverse from closest end)
- `set(index, value)` - O(n) - Update element value

**Optimizations:**
- Bidirectional traversal (previous/next pointers)
- `get()` traverses from closest end (head or tail)
- Direct references to head and tail for O(1) operations
- Specialized methods for edge cases

</details>

<details>
<summary><strong>Queue</strong></summary>

Generic Queue implementation (FIFO - First In, First Out).

**Operations:**
- `enqueue(value)` - O(1) - Add element to the end
- `dequeue()` - O(1) - Remove and return the first element

**Characteristics:**
- FIFO order
- Implemented with linked list for efficient O(1) operations
- Supports generic data types

</details>

<details>
<summary><strong>Stack</strong></summary>

Generic Stack implementation (LIFO - Last In, First Out).

**Operations:**
- `push(value)` - O(1) - Add element to the top
- `pop()` - O(1) - Remove and return the top element

**Characteristics:**
- LIFO order
- Efficiently implemented with a top pointer
- Supports generic data types

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/min-stack/">Min Stack</a></strong></summary>

A stack that efficiently tracks both the top element and the minimum element in the stack.

**Operations:**
- `push(value)` - O(1) - Add element to the top
- `pop()` - O(1) - Remove the top element
- `top()` - O(1) - Get the top element without removing it
- `getMin()` - O(n) - Get the minimum value currently in the stack

**Complexity:**
- Time: 
  - `push(value)`: O(1) - Constant time node creation and pointer update
  - `pop()`: O(1) - Constant time pointer update
  - `top()`: O(1) - Direct access to top node's value
  - `getMin()`: O(n) - Must traverse entire stack to find minimum
- Space: O(n) - Stores all n elements in linked list nodes

**Implementation:**
- Linked list-based stack with explicit tracking of top node
- Node structure contains value and next pointer
- getMin() traverses from top to bottom comparing all values

**Note:** This implementation has O(n) getMin() complexity. An optimized version would maintain a separate stack or variable tracking minimum as elements are pushed, achieving O(1) getMin() at the cost of additional space.

**Example:**
```kotlin
val minStack = MinStack()
minStack.push(5)
minStack.push(3)
minStack.push(10)
println(minStack.top())    // Output: 10
println(minStack.getMin()) // Output: 3
minStack.pop()
println(minStack.getMin()) // Output: 3
```

</details>

<details>
<summary><strong>Heap (Priority Queue)</strong></summary>

Generic binary heap implementation maintaining priority order. Implemented as a complete binary tree using array representation with customizable priority function.

**Operations:**
- `insert(element)` - O(log n) - Add element and maintain heap property via bubble-up
- `top()` - O(log n) - Remove and return highest priority element (root), bubble-down to restore heap property
- `peek()` - O(1) - View highest priority element without removing it
- `size()` - O(1) - Return number of elements in the heap
- `isEmpty()` - O(1) - Check if heap contains no elements
- `kLargestElements(k)` - O(k log n) - Get k largest elements in descending order (non-destructive)
- `allElementsDescending()` - O(n log n) - Get all elements in descending order (non-destructive)
- `heapify(elements)` - O(n) - Convert arbitrary list into valid heap (more efficient than n insertions)

**Helper Methods:**
- `hasHigherPriority(e1, e2)` - O(1) - Compare priorities of two elements
- `hasLowerPriority(e1, e2)` - O(1) - Compare priorities of two elements
- `parentIndex(i)` - O(1) - Calculate parent index: `(i - 1) / 2`
- `leftChildIndex(i)` - O(1) - Calculate left child index: `2 * i + 1`
- `rightChildIndex(i)` - O(1) - Calculate right child index: `2 * i + 2`

**Characteristics:**
- Heap Property: Parent nodes always have higher priority than their children
- Complete Binary Tree: All levels filled except possibly the last, which fills left-to-right
- Array Representation: Efficient memory usage, no need for node objects
  - Parent of index `i` is at `(i - 1) / 2`
  - Left child of index `i` is at `2 * i + 1`
  - Right child of index `i` is at `2 * i + 2`
- Flexible Priority: Custom `elementPriority` function allows min-heap, max-heap, or complex priorities
- Space Complexity: O(n) for storing elements

**Complexity Analysis:**
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

**Use Cases:**
- Priority queues (task scheduling, event simulation)
- Dijkstra's shortest path algorithm
- Heap sort algorithm
- Finding k largest/smallest elements
- Median maintenance in streaming data
- Job scheduling systems
- A* pathfinding algorithm

**Example:**
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

</details>

## LeetCode Solutions

This project contains solutions to various LeetCode problems organized by topic and difficulty level. Each solution includes detailed complexity analysis and comprehensive test coverage.

### Arrays & Hashing

#### 🟢 Easy

<details>
<summary><strong><a href="https://leetcode.com/problems/contains-duplicate/">#217 - Contains Duplicate</a></strong></summary>

Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

**Complexity:**
- Time: O(n) - Single pass through array with HashSet operations
- Space: O(n) - HashSet stores all elements in worst case

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/HasDuplicates.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/find-pivot-index/">#724 - Find Pivot Index</a></strong></summary>

Given an integer array nums, return the leftmost pivot index where the sum of the numbers strictly to the left equals the sum of the numbers strictly to the right.

**Complexity:**
- Time: O(n) - Single pass with running sums
- Space: O(1) - Constant extra space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/FindPivotIndex.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/valid-anagram/">#242 - Valid Anagram</a></strong></summary>

Given two strings s and t, return true if t is an anagram of s, and false otherwise. An anagram is a word or phrase formed by rearranging the letters of another.

**Complexity:**
- Time: O(n) - Two passes through strings
- Space: O(1) - At most 26 lowercase English letters in HashMap

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/IsAnagram.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/concatenation-of-array/">#1929 - Concatenation of Array</a></strong></summary>

Returns a new array that is the concatenation of the input array with itself.

**Complexity:**
- Time: O(n) - Single iteration through result array
- Space: O(n) - New array of size 2n

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/ArrayConcatenation.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/contains-duplicate-ii/">#219 - Contains Duplicate II</a></strong></summary>

Given an integer array nums and an integer k, return true if there are two distinct indices i and j such that nums[i] == nums[j] and abs(i - j) <= k.

**Complexity:**
- Time: O(n) - Single pass with hash map for last seen indices
- Space: O(n) - Hash map stores last index for each value

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/ContainsDuplicateII.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">#121 - Best Time to Buy and Sell Stock</a></strong></summary>

Given an array of prices, return the maximum profit from buying once and selling once.

**Complexity:**
- Time: O(n) - Single pass tracking min price and max profit
- Space: O(1) - Constant extra space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/BestTimeToBuyAndSellStock.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/monotonic-array/">#896 - Monotonic Array</a></strong></summary>

Determine if an array is monotonic (either entirely non-increasing or non-decreasing). An array is monotonic if for all i <= j, either nums[i] <= nums[j] or nums[i] >= nums[j].

**Complexity:**
- Time: O(n) - Single pass through array with early termination when both flags become false
- Space: O(1) - Only uses two boolean flags

**Algorithm:**
1. Initialize two flags: `increasing` (true) and `decreasing` (true)
2. Iterate through consecutive pairs:
   - If nums[i] > nums[i+1]: set increasing = false
   - If nums[i] < nums[i+1]: set decreasing = false
   - Early return if both flags are false (array is not monotonic)
3. Return true if either flag remains true

**Example:**
```kotlin
val solution = IsMonotonicSolution()
solution.isMonotonic(intArrayOf(1, 2, 2, 3))    // Returns true (non-decreasing)
solution.isMonotonic(intArrayOf(6, 5, 4, 4))    // Returns true (non-increasing)
solution.isMonotonic(intArrayOf(1, 3, 2))       // Returns false (neither)
solution.isMonotonic(intArrayOf(5, 5, 5, 5))    // Returns true (both)
```

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/IsMonotonic.kt`

</details>

#### 🟡 Medium

<details>
<summary><strong><a href="https://leetcode.com/problems/two-sum/">#1 - Two Sum</a></strong></summary>

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

**Complexity:**
- Time: O(n) - Single pass through array with HashMap lookup O(1)
- Space: O(n) - HashMap stores up to n numbers in worst case

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/TwoSum.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/group-anagrams/">#49 - Group Anagrams</a></strong></summary>

Given an array of strings strs, group the anagrams together. Anagrams are words with the same character frequencies.

**Complexity:**
- Time: O(n * k) - where n is the number of strings and k is the maximum length of a string
- Space: O(n * k) - for storing all strings in the result

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/GroupAnagrams.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/top-k-frequent-elements/">#347 - Top K Frequent Elements</a></strong></summary>

Given an integer array nums and an integer k, return the k most frequent elements using bucket sort approach.

**Complexity:**
- Time: O(n) - Bucket sort approach
- Space: O(n) - For frequency map and buckets

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/TopKFrequentElements.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/product-of-array-except-self/">#238 - Product of Array Except Self</a></strong></summary>

Given an array nums, return an array where each element is the product of all elements except itself. Uses two-pass prefix/suffix approach without division.

**Complexity:**
- Time: O(n) - Two passes through the array
- Space: O(1) - Excluding the output array

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/ProductExceptSelf.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/encode-and-decode-strings/">#271 - Encode and Decode Strings</a></strong></summary>

Design an algorithm to encode a list of strings to a single string and decode it back using length prefix protocol. (LeetCode Premium)

**Complexity:**
- Time: O(n) - where n is the total number of characters
- Space: O(n) - for the resulting encoded string

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/EncodeDecode.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">#3 - Longest Substring Without Repeating Characters</a></strong></summary>

Given a string s, find the length of the longest substring without repeating characters using the sliding window technique.

**Complexity:**
- Time: O(n) - Single pass through the string with HashMap operations
- Space: O(min(m, n)) - HashMap stores at most min(charset size, string length) characters

**Algorithm:**
1. Use a sliding window with left and right pointers
2. Maintain a HashMap to track the last seen index of each character
3. When a character is found in the current window (index >= left):
   - Move left pointer to the position after the previous occurrence
4. Update the character's last seen index
5. Track the maximum window size encountered

**Example:**
```kotlin
val solution = LongestSubstringWithoutRepeatingCharactersSolution()
solution.lengthOfLongestSubstring("abcabcbb") // Returns 3 ("abc")
solution.lengthOfLongestSubstring("bbbbb")   // Returns 1 ("b")
solution.lengthOfLongestSubstring("pwwkew")  // Returns 3 ("wke")
solution.lengthOfLongestSubstring("au")      // Returns 2 ("au")
```

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/LongestSubstringWithoutRepeatingCharacters.kt`

</details>

### Two Pointers

#### 🟢 Easy

<details>
<summary><strong><a href="https://leetcode.com/problems/reverse-string/">#344 - Reverse String</a></strong></summary>

Reverses a string in-place using two-pointer technique with O(1) extra memory.

**Complexity:**
- Time: O(n) - Iterates through half of the array
- Space: O(1) - Only uses constant extra space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/ReverseString.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/">#26 - Remove Duplicates from Sorted Array</a></strong></summary>

Removes duplicates from a sorted array in-place and returns the new length.

**Complexity:**
- Time: O(n) - Single pass through array
- Space: O(1) - Constant space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/RemoveDuplicates.kt`

</details>

#### 🟡 Medium

<details>
<summary><strong><a href="https://leetcode.com/problems/valid-palindrome/">#125 - Valid Palindrome</a></strong></summary>

Checks if a string is a palindrome after converting to lowercase and removing non-alphanumeric characters using two-pointer approach.

**Complexity:**
- Time: O(n) - Single pass with two pointers
- Space: O(1) - Only uses two pointer variables

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/IsValidPalindrome.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/">#167 - Two Sum II - Input Array Is Sorted</a></strong></summary>

Given a sorted array, find two numbers that add up to a target using two pointers.

**Complexity:**
- Time: O(n) - Two pointers on sorted array
- Space: O(1) - Constant space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/TwoSumII.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/3sum/">#15 - 3Sum</a></strong></summary>

Find all unique triplets in the array which gives the sum of zero using sorting and two-pointer technique.

**Complexity:**
- Time: O(n²) - Sorting O(n log n) + nested loop O(n²)
- Space: O(1) - Excluding the output list

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/ThreeSum.kt`

</details>

### Search Algorithms

#### 🟢 Easy

<details>
<summary><strong><a href="https://leetcode.com/problems/binary-search/">#704 - Binary Search</a></strong></summary>

Searches for a target value in a sorted array using the binary search algorithm.

**Complexity:**
- Time: O(log n) - Halves the search space with each step
- Space: O(1) - Constant extra space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/BinarySearch.kt`

</details>

#### 🟡 Medium

<details>
<summary><strong><a href="https://leetcode.com/problems/first-bad-version/">#278 - First Bad Version</a></strong></summary>

Find the first bad version in a sequence where all versions after a bad version are also bad. Uses binary search to minimize API calls.

**Complexity:**
- Time: O(log n) - Binary search halves the search space in each iteration
- Space: O(1) - Only uses a constant amount of extra space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/BadVersion.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/guess-number-higher-or-lower/">#374 - Guess Number Higher or Lower</a></strong></summary>

Guessing game where you need to find a picked number from 1 to n using an API that tells if your guess is higher, lower, or correct.

**Complexity:**
- Time: O(log n) - Binary search halves the search space in each iteration
- Space: O(1) - Only uses a constant amount of extra space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/GuessNumberHigherOrLower.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/search-a-2d-matrix/">#74 - Search a 2D Matrix</a></strong></summary>

Searches for a target value in a 2D sorted matrix. Finds the correct row first, then performs binary search on that row.

**Complexity:**
- Time: O(n + log m) - n rows iteration + binary search on m columns
- Space: O(1) - Constant extra space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/Search2DMatrix.kt`
- `src/main/kotlin/digital/tonima/algorithms/arrays/Search2DMatrixOptimal.kt` (Optimal: O(log(m * n)))


</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/majority-element/">#169 - Majority Element</a></strong></summary>

Find the majority element (appears more than ⌊n/2⌋ times) using Boyer-Moore Voting Algorithm with early return optimization.

**Complexity:**
- Time: O(n) worst case, O(n/2) best case with early return
- Space: O(1) - Only uses three variables

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/MajorityElement.kt`

</details>

### Stack

#### 🟢 Easy

<details>
<summary><strong><a href="https://leetcode.com/problems/valid-parentheses/">#20 - Valid Parentheses</a></strong></summary>

Determines if a string containing parentheses '()', braces '{}', and brackets '[]' is valid. Open brackets must be closed by the same type in correct order.

**Complexity:**
- Time: O(n) - Single pass through string
- Space: O(n) - Stack can hold up to n/2 opening brackets

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/stack/ValidParentheses.kt`

</details>

#### 🟡 Medium

<details>
<summary><strong><a href="https://leetcode.com/problems/min-stack/">#155 - Min Stack</a></strong></summary>

A stack that efficiently tracks both the top element and the minimum element in the stack.

**Complexity:**
- Time: 
  - `push(value)`: O(1) - Constant time node creation and pointer update
  - `pop()`: O(1) - Constant time pointer update
  - `top()`: O(1) - Direct access to top node's value
  - `getMin()`: O(n) - Must traverse entire stack to find minimum
- Space: O(n) - Stores all n elements in linked list nodes

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/stack/MinStack.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/">#150 - Evaluate Reverse Polish Notation</a></strong></summary>

Evaluates an arithmetic expression in Reverse Polish Notation (RPN), also known as postfix notation.

**Complexity:**
- Time: O(n) - Single pass through all tokens
- Space: O(n) - Stack can hold up to n/2 operands in worst case

**Algorithm:**
1. Iterate through each token in the array:
   - If token is a number: push it onto the stack
   - If token is an operator (+, -, *, /):
     - Pop two operands from the stack (b first, then a)
     - Apply the operation: result = a operator b
     - Push the result back onto the stack
2. Return the final value remaining in the stack

**Key Points:**
- Reverse Polish Notation places operators after operands (e.g., "3 4 +" instead of "3 + 4")
- No parentheses needed - order of operations is explicit
- Order matters for non-commutative operations (subtraction and division)
- Division truncates toward zero

**Example:**
```kotlin
val solution = ReversePolishNotationSolution()

// Example 1: (2 + 1) * 3 = 9
val tokens1 = arrayOf("2", "1", "+", "3", "*")
val result1 = solution.evalRPN(tokens1)
// result1 is 9

// Example 2: 4 + (13 / 5) = 4 + 2 = 6
val tokens2 = arrayOf("4", "13", "5", "/", "+")
val result2 = solution.evalRPN(tokens2)
// result2 is 6
```

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/arrays/ReversePolishNotation.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/car-fleet/">#853 - Car Fleet</a></strong></summary>

Determine how many car fleets will arrive at the target destination. Cars starting closer to the target and traveling slower can block faster cars behind them, causing them to merge into fleets.

**Complexity:**
- Time: O(n log n) - Dominated by sorting cars by position
- Space: O(n) - For storing the paired and sorted cars array

**Algorithm:**
1. Sort cars by position in descending order (closest to target first)
2. For each car, calculate time to reach target: `time = (target - position) / speed`
3. Track the current fleet's arrival time
4. If a car's arrival time is strictly greater than the current fleet's time:
   - It cannot catch up to the fleet ahead
   - It forms a new fleet (increment fleet count)
   - Update current fleet time to this car's time
5. Otherwise, the car catches up and merges with the existing fleet

**Key Points:**
- Cars cannot pass each other - if a faster car catches a slower one, they travel together as a fleet
- The slowest car in a fleet determines the fleet's speed
- Cars already at the target (position == target) have time 0.0
- Process cars from closest to target to farthest for correct fleet formation

**Example:**
```kotlin
val solution = CarFleetSolution()

// Example: target = 12, positions = [10,8,0,5,3], speeds = [2,4,1,1,3]
val target = 12
val position = intArrayOf(10, 8, 0, 5, 3)
val speed = intArrayOf(2, 4, 1, 1, 3)
val result = solution.carFleet(target, position, speed)
// result is 3

// Car at position 10 with speed 2: time = (12-10)/2 = 1.0 hour
// Car at position 8 with speed 4: time = (12-8)/4 = 1.0 hour (merges with car at 10)
// Car at position 5 with speed 1: time = (12-5)/1 = 7.0 hours (new fleet)
// Car at position 3 with speed 3: time = (12-3)/3 = 3.0 hours (new fleet)
// Car at position 0 with speed 1: time = (12-0)/1 = 12.0 hours (new fleet)
// Total: 3 fleets
```

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/stack/CarFleet.kt`

</details>

### Linked List

#### 🟢 Easy

<details>
<summary><strong><a href="https://leetcode.com/problems/linked-list-cycle/">#141 - Linked List Cycle</a></strong></summary>

Determine if a linked list has a cycle using Floyd's Cycle Detection Algorithm (tortoise and hare).

**Complexity:**
- Time: O(n) - where n is the number of nodes
- Space: O(1) - Only using two pointers

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/linkedlist/HasCicle.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/reverse-linked-list/">#206 - Reverse Linked List</a></strong></summary>

Reverse a singly linked list iteratively using three-pointer approach.

**Complexity:**
- Time: O(n) - Single pass through the list, visiting each node once
- Space: O(1) - Only using three pointers (previous, current, next)

**Algorithm:**
1. Initialize previous as null (will become the new tail)
2. Iterate through the list:
   - Save the next node before breaking the link
   - Reverse the current node's pointer to point to previous
   - Move previous to current (shift window forward)
   - Move current to next (shift window forward)
3. Return previous (which is now the new head)

**Example visualization for [1,2,3]:**
- Initial:     1 -> 2 -> 3 -> null
- Step 1:      null <- 1    2 -> 3 -> null
- Step 2:      null <- 1 <- 2    3 -> null
- Step 3:      null <- 1 <- 2 <- 3
- Return: 3 (new head)

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/linkedlist/ReverseLinkedList.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/">#1290 - Convert Binary Number in Linked List to Integer</a></strong></summary>

Given a linked list representing a binary number, return its decimal value.

**Complexity:**
- Time: O(n) - Single pass through the list
- Space: O(1) - Constant space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/linkedlist/BinaryLinkedListToDecimal.kt`

</details>

#### 🟡 Medium

<details>
<summary><strong><a href="https://leetcode.com/problems/add-two-numbers/">#2 - Add Two Numbers</a></strong></summary>

Add two numbers represented as reversed linked lists using digit-by-digit addition with carry.

**Complexity:**
- Time: O(max(n, m)) - where n and m are the lengths of the two linked lists
- Space: O(max(n, m)) - for the result linked list

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/linkedlist/AddTwoNumbers.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/merge-two-sorted-lists/">#21 - Merge Two Sorted Lists</a></strong></summary>

Merge two sorted linked lists into a single sorted linked list using a two-pointer approach.

**Complexity:**
- Time: O(n + m) - where n and m are the lengths of list1 and list2
- Space: O(1) - Only using pointers, no extra space needed

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/linkedlist/MergeTwoSortedLinkedLists.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/design-linked-list/">#707 - Design Linked List</a></strong></summary>

Implement a linked list with operations to get, add, and delete nodes by index.

**Complexity:**
- Time: O(1) for add at head/tail, O(n) for get/add/delete by index
- Space: O(1) extra space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/linkedlist/DesignLinkedList.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/middle-of-the-linked-list/">#876 - Middle of the Linked List</a></strong></summary>

Return the middle node of a linked list. If there are two middle nodes, return the second middle node.

**Complexity:**
- Time: O(n) - Single pass with two pointers
- Space: O(1) - Only using two pointers

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/linkedlist/FindMiddleNode.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/">#19 - Remove Nth Node From End of List</a></strong></summary>

Remove the nth node from the end of a linked list using two-pointer technique.

**Complexity:**
- Time: O(n) - Single pass through the list
- Space: O(1) - Constant space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/linkedlist/RemoveNthFromEnd.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/palindrome-linked-list/">#234 - Palindrome Linked List</a></strong></summary>

Determine if a singly linked list is a palindrome by finding middle, reversing second half, and comparing.

**Complexity:**
- Time: O(n) - Three passes through the list
- Space: O(1) - Constant space (in-place reversal)

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/linkedlist/PalindromeLinkedList.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/lru-cache/">#146 - LRU Cache</a></strong></summary>

Implement a Least Recently Used (LRU) Cache with O(1) get and put operations using LinkedHashMap with access-order.

**Complexity:**
- Time: O(1) for both get() and put() operations
- Space: O(capacity) - Stores up to capacity key-value pairs

**Algorithm:**
- LinkedHashMap with `accessOrder=true`: Maintains insertion/access order while providing O(1) lookups
- Override `removeEldestEntry()`: Automatically removes least recently used entry when capacity exceeded
- On get(): Access via HashMap O(1), LinkedHashMap automatically moves accessed key to end (most recent)
- On put(): Insert/update via HashMap O(1), LinkedHashMap automatically evicts oldest if over capacity

**Why LinkedHashMap?**
- Immutable order tracking without manual list manipulation
- Built-in access-order tracking (no O(n) remove operations)
- True O(1) performance for both operations
- Cleaner and more efficient than HashMap + ArrayDeque

**Features:**
- Automatic eviction of least recently used item when capacity exceeded
- Accessing a key updates its recency (accessOrder=true)
- Updating a key's value also updates its recency

**Example:**
```kotlin
val cache = LRUCache(2)
cache.put(1, 1)
cache.put(2, 2)
cache.get(1)        // Returns 1, moves key 1 to most recent
cache.put(3, 3)     // Evicts key 2 (least recently used)
cache.get(2)        // Returns -1 (key 2 was evicted)
cache.put(4, 4)     // Evicts key 1
cache.get(1)        // Returns -1 (key 1 was evicted)
cache.get(3)        // Returns 3
cache.get(4)        // Returns 4
```

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/linkedlist/LRUCache.kt`

</details>

### Binary Tree

#### 🟢 Easy

<details>
<summary><strong><a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">#104 - Maximum Depth of Binary Tree</a></strong></summary>

Given the root of a binary tree, return its maximum depth.

**Complexity:**
- Time: O(n) - Single pass visiting each node exactly once
- Space: O(h) - where h is the height of the tree due to recursive call stack
  - Worst case: O(n) for skewed tree
  - Best case: O(log n) for balanced tree

**Algorithm:**
1. Base case: if root is null, return 0
2. Recursively find max depth of left subtree
3. Recursively find max depth of right subtree
4. Return 1 + max of left and right depths

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/tree/MaxDepth.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/invert-binary-tree/">#226 - Invert Binary Tree</a></strong></summary>

Given the root of a binary tree, invert the tree and return its root (mirror the tree horizontally).

**Complexity:**
- Time: O(n) - where n is the number of nodes in the tree (visit each node exactly once)
- Space: O(h) - where h is the height of the tree due to recursive call stack
  - Worst case: O(n) for skewed tree
  - Best case: O(log n) for balanced tree

**Algorithm:**
1. Base case: if root is null, return null
2. Recursively invert left subtree
3. Recursively invert right subtree
4. Swap the left and right children of the current node
5. Return the root

**Example:**
```
Input:
       4
     /   \
    2     7
   / \   / \
  1   3 6   9

Output:
       4
     /   \
    7     2
   / \   / \
  9   6 3   1
```

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/tree/InvertBinaryTree.kt`

</details>

<details>
<summary><strong><a href="https://leetcode.com/problems/diameter-of-binary-tree/">#543 - Diameter of Binary Tree</a></strong></summary>

Given the root of a binary tree, return the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

**Complexity:**
- Time: O(n) - where n is the number of nodes in the tree
- Space: O(h) - where h is the height of the tree due to recursive call stack

**Algorithm:**
1. Recursively calculate the height of each subtree.
2. At each node, calculate the diameter passing through it (left_height + right_height).
3. Track the maximum diameter found across all nodes.
4. Return 1 + max(left_height, right_height) to represent the height of the current node to its parent.

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/tree/DiameterOfBinaryTree.kt`

</details>

### Dynamic Programming

<details>
<summary><strong><a href="https://leetcode.com/problems/fibonacci-number/">#509 - Fibonacci Number</a></strong></summary>

Calculate the nth Fibonacci number using Bottom-Up Dynamic Programming (Tabulation).

**Complexity:**
- Time: O(n) - Single pass from 2 to n
- Space: O(n) - Array of size n+1

**Alternative approaches:**
- Recursive (naive): O(2^n) time, O(n) space
- Memoization (Top-Down DP): O(n) time, O(n) space
- Space-optimized: O(n) time, O(1) space
- Matrix exponentiation: O(log n) time, O(1) space

**Source:**
- `src/main/kotlin/digital/tonima/algorithms/dinamicprograming/Fibonacci.kt`

</details>

