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

## 🛠️ Technologies

- **Language**: Kotlin 2.1.20
- **Build Tool**: Gradle
- **JVM**: Java 23+
- **Testing**: JUnit 5 + Kotlin Test

## 📦 Installation and Setup

### Requirements
- JDK 23 or higher
- Gradle (included via wrapper)

### Running the Project

```bash
# Clone the repository
git clone <your-repository>
cd DSA

# Build the project
./gradlew build

# Run tests
./gradlew test

# Build and clean (macOS/Linux)
./gradlew clean build
```

## ✅ Tests

The project includes comprehensive tests for all implementations:

```bash
# Run all tests
./gradlew test

# View test report
open build/reports/tests/test/index.html
```

### Included Tests

- `TwoSumTest`: Validates the Two Sum algorithm
- `BinaryLinkedListToDecimalTest`: Tests binary to decimal conversion
- `FindMiddleNodeTest`: Tests the Find Middle algorithm
- `HasCycleTest`: Tests cycle detection in linked lists
- `RemoveNthFromEndTest`: Tests removal of nth element
- `LinkedListTest`: General tests for LinkedList structure
- `DoublyLinkedListTest`: Tests for DoublyLinkedList operations
- `QueueTest`: Tests for Queue operations
- `StackTest`: Tests for Stack operations

## 🔄 CI/CD Pipeline

This project uses **GitHub Actions** to automatically run tests on every push and pull request.

### Automated Workflows

The following workflows are configured:

1. **Tests** (`.github/workflows/tests.yml`)
   - Runs on: `push` and `pull_request` to `main`, `master`, `develop`
   - Builds the project with Gradle
   - Executes all unit tests
   - Uploads test reports as artifacts

2. **Build & Test Report** (`.github/workflows/build-test.yml`)
   - Full build and test execution
   - Generates detailed HTML test reports
   - Comments on PRs with test results

### Workflow Details

- **Trigger**: Automatically runs when code is pushed to GitHub
- **Environment**: Ubuntu Latest with JDK 23
- **Caching**: Gradle dependencies are cached for faster builds
- **Artifacts**: Test reports are available for download
- **Status**: Check the "Actions" tab in your GitHub repository to see results

### Example: Push Code to Trigger Pipeline

```bash
git add .
git commit -m "Add new feature"
git push origin main
```

The pipeline will automatically:
1. ✅ Build the project
2. ✅ Run all tests
3. ✅ Generate test reports
4. ✅ Upload artifacts to GitHub

## 💡 Usage Examples

### Two Sum

```kotlin
val solution = Solution()
val nums = intArrayOf(2, 7, 11, 15)
val target = 9
val result = solution.twoSum(nums, target) // [0, 1]
```

### Linked List

```kotlin
val list = LinkedList(1)
list.append(2)
list.append(3)
list.prepend(0)
println(list.size) // 4
```

### Doubly Linked List

```kotlin
val list = DoublyLinkedList<Int>()
list.append(10)
list.append(20)
list.append(30)
list.prepend(5)

// Access elements
println(list.get(0)?.value)  // 5
println(list.get(2)?.value)  // 20

// Insert at specific position
list.insert(1, 7)  // [5, 7, 10, 20, 30]

// Remove elements
list.remove(2)      // Removes 10
list.removeFirst()  // Removes 5
list.removeLast()   // Removes 30

// Update value
list.set(0, 100)  // Changes first element to 100
```

### Queue

```kotlin
val queue = Queue<Int>()
queue.enqueue(1)
queue.enqueue(2)
queue.enqueue(3)

// Remove elements
println(queue.dequeue()) // 1
println(queue.dequeue()) // 2
```

### Stack

```kotlin
val stack = Stack<Int>()
stack.push(1)
stack.push(2)
stack.push(3)

// Remove elements
println(stack.pop()) // 3
println(stack.pop()) // 2
```

## 📊 Complexity Analysis

| Algorithm/Operation | Time | Space | Details |
|-----------|------|-------|---------|
| Two Sum | O(n) | O(n) | HashMap lookup |
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

## 🔗 LeetCode Problems

All algorithms in this project are based on classic LeetCode problems:

- **#1**: [Two Sum](https://leetcode.com/problems/two-sum/) - Easy
- **#19**: [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) - Medium
- **#141**: [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) - Easy
- **#876**: [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) - Easy
- **#1290**: [Convert Binary Number in a Linked List to Integer](https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/) - Easy

## 📝 License

This project is open source and can be freely used for educational purposes.

---

**Last Updated**: December 2025
