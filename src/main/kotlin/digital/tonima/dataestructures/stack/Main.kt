package digital.tonima.dataestructures.stack

class Main {


    companion object {
        fun sortStack(stack: Stack<Int?>) {
            val tempStack = Stack<Int?>()

            while (!stack.isEmpty()) {
                val temp = stack.pop()?.value

                while (!tempStack.isEmpty() && tempStack.peek() != null && temp != null && tempStack.peek()!! > temp) {
                    stack.push(tempStack.pop()?.value)
                }

                tempStack.push(temp)
            }

            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop()?.value)
            }
        }


        @JvmStatic
        fun main(args: Array<String>) {

            println("These tests confirm sortStack sorts")
            println("the stack so the TOP is the LOWEST value.")
            println("printStack() shows the stack from top")
            println("to bottom (smallest to largest).")
            println()

            // Test 1: Empty stack
            println("Test 1: Empty Stack")
            var stack = Stack<Int?>()
            sortStack(stack)
            println("Expected (top to bottom): empty")
            stack.printStack()
            println()

            // Test 2: Single element
            println("Test 2: Single Element")
            stack = Stack()
            stack.push(5)
            sortStack(stack)
            println("Expected (top to bottom): 5")
            stack.printStack()
            println()

            // Test 3: Unsorted stack
            println("Test 3: Unsorted Stack")
            stack = Stack()
            stack.push(3)
            stack.push(1)
            stack.push(4)
            stack.push(2)
            sortStack(stack)
            println("Expected (top to bottom): 1, 2, 3, 4")
            stack.printStack()
            println()

            // Test 4: Already sorted
            println("Test 4: Already Sorted Stack")
            stack = Stack()
            stack.push(4)
            stack.push(3)
            stack.push(2)
            stack.push(1)
            sortStack(stack)
            println("Expected (top to bottom): 1, 2, 3, 4")
            stack.printStack()
            println()

            // Test 5: Reverse sorted
            println("Test 5: Reverse Sorted Stack")
            stack = Stack()
            stack.push(1)
            stack.push(2)
            stack.push(3)
            stack.push(4)
            sortStack(stack)
            println("Expected (top to bottom): 1, 2, 3, 4")
            stack.printStack()
            println()

            // Test 6: With duplicates
            println("Test 6: With Duplicates")
            stack = Stack()
            stack.push(3)
            stack.push(1)
            stack.push(3)
            stack.push(2)
            stack.push(1)
            sortStack(stack)
            println("Expected (top to bottom): 1, 1, 2, 3, 3")
            stack.printStack()
            println()

            // Test 7: With negatives
            println("Test 7: With Negatives")
            stack = Stack<Int?>()
            stack.push(-1)
            stack.push(3)
            stack.push(-5)
            stack.push(2)
            sortStack(stack)
            println("Expected (top to bottom): -5, -1, 2, 3")
            stack.printStack()
            println()

            /*
            EXPECTED OUTPUT:
            ----------------
            These tests confirm sortStack sorts
            the stack so the TOP is the LOWEST value.
            printStack() shows the stack from top
            to bottom (smallest to largest).

            Test 1: Empty Stack
            Expected (top to bottom): empty

            Test 2: Single Element
            Expected (top to bottom): 5
            5

            Test 3: Unsorted Stack
            Expected (top to bottom): 1, 2, 3, 4
            1
            2
            3
            4

            Test 4: Already Sorted Stack
            Expected (top to bottom): 1, 2, 3, 4
            1
            2
            3
            4

            Test 5: Reverse Sorted Stack
            Expected (top to bottom): 1, 2, 3, 4
            1
            2
            3
            4

            Test 6: With Duplicates
            Expected (top to bottom): 1, 1, 2, 3, 3
            1
            1
            2
            3
            3

            Test 7: With Negatives
            Expected (top to bottom): -5, -1, 2, 3
            -5
            -1
            2
            3
        */
        }
    }
}

