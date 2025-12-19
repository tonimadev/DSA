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

### Algorithms (`algorithms/`)

#### Arrays
- **[TwoSum](https://leetcode.com/problems/two-sum/)** - Find two numbers in an array that sum to a target value
  - Time: O(n), Space: O(n)
  - Uses HashMap for constant-time lookup
  - LeetCode #1

#### Linked List
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
- `FindMiddleNodeTest`: Tests the Find Middle algorithm
- `HasCycleTest`: Tests cycle detection in linked lists
- `RemoveNthFromEndTest`: Tests removal of nth element
- `LinkedListTest`: General tests for LinkedList structure

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

## 📊 Complexity Analysis

| Algorithm | Time | Space | Details |
|-----------|------|-------|---------|
| Two Sum | O(n) | O(n) | HashMap lookup |
| Find Middle | O(n) | O(1) | Two pointers |
| Has Cycle | O(n) | O(1) | Floyd algorithm |
| Remove Nth | O(n) | O(1) | Two pointers |
| LinkedList Append | O(1) | O(1) | Tail reference |

## 📚 References and Concepts

- **Two Pointers**: Technique for solving problems in arrays and linked lists
- **HashMap Optimization**: Reducing complexity with auxiliary data structures
- **Floyd's Cycle Detection**: Algorithm to detect cycles without extra space
- **Generic Types**: Reusable implementations in Kotlin

## 🔗 LeetCode Problems

All algorithms in this project are based on classic LeetCode problems:

- **#1**: [Two Sum](https://leetcode.com/problems/two-sum/) - Medium
- **#19**: [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) - Medium
- **#141**: [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) - Easy
- **#876**: [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) - Easy

## 📝 License

This project is open source and can be freely used for educational purposes.


---

**Last Updated**: December 2025

